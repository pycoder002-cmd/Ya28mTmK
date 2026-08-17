package io.sentry.connection;

import io.sentry.buffer.Buffer;
import io.sentry.environment.SentryEnvironment;
import io.sentry.event.Event;
import io.sentry.util.Util;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public class BufferedConnection implements Connection {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) BufferedConnection.class);
    private Connection actualConnection;
    private Buffer buffer;
    private boolean gracefulShutdown;
    private long shutdownTimeout;
    private final ShutDownHook shutDownHook = new ShutDownHook();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: io.sentry.connection.BufferedConnection.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        }
    });
    private volatile boolean closed = false;

    /* loaded from: classes2.dex */
    private class Flusher implements Runnable {
        private long minAgeMillis;

        Flusher(long j) {
            this.minAgeMillis = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            BufferedConnection.logger.trace("Running Flusher");
            SentryEnvironment.startManagingThread();
            try {
                try {
                    Iterator<Event> events = BufferedConnection.this.buffer.getEvents();
                    while (events.hasNext() && !BufferedConnection.this.closed) {
                        Event next = events.next();
                        long currentTimeMillis = System.currentTimeMillis() - next.getTimestamp().getTime();
                        if (currentTimeMillis < this.minAgeMillis) {
                            BufferedConnection.logger.trace("Ignoring buffered event because it only " + currentTimeMillis + "ms old.");
                            return;
                        }
                        try {
                            BufferedConnection.logger.trace("Flusher attempting to send Event: " + next.getId());
                            BufferedConnection.this.send(next);
                            BufferedConnection.logger.trace("Flusher successfully sent Event: " + next.getId());
                        } catch (Exception e) {
                            BufferedConnection.logger.debug("Flusher failed to send Event: " + next.getId(), (Throwable) e);
                            BufferedConnection.logger.trace("Flusher run exiting early.");
                            return;
                        }
                    }
                    BufferedConnection.logger.trace("Flusher run exiting, no more events to send.");
                } catch (Exception e2) {
                    BufferedConnection.logger.error("Error running Flusher: ", (Throwable) e2);
                }
            } finally {
                SentryEnvironment.stopManagingThread();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class ShutDownHook extends Thread {
        private volatile boolean enabled;

        private ShutDownHook() {
            this.enabled = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.enabled) {
                SentryEnvironment.startManagingThread();
                try {
                    try {
                        BufferedConnection.this.close();
                    } catch (Exception e) {
                        BufferedConnection.logger.error("An exception occurred while closing the connection.", (Throwable) e);
                    }
                } finally {
                    SentryEnvironment.stopManagingThread();
                }
            }
        }
    }

    public BufferedConnection(Connection connection, Buffer buffer, long j, boolean z, long j2) {
        this.actualConnection = connection;
        this.buffer = buffer;
        this.gracefulShutdown = z;
        this.shutdownTimeout = j2;
        if (z) {
            Runtime.getRuntime().addShutdownHook(this.shutDownHook);
        }
        this.executorService.scheduleWithFixedDelay(new Flusher(j), j, j, TimeUnit.MILLISECONDS);
    }

    @Override // io.sentry.connection.Connection
    public void addEventSendCallback(EventSendCallback eventSendCallback) {
        this.actualConnection.addEventSendCallback(eventSendCallback);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.gracefulShutdown) {
            Util.safelyRemoveShutdownHook(this.shutDownHook);
            this.shutDownHook.enabled = false;
        }
        logger.debug("Gracefully shutting down Sentry buffer threads.");
        this.closed = true;
        this.executorService.shutdown();
        try {
            try {
                if (this.shutdownTimeout == -1) {
                    while (!this.executorService.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                        logger.debug("Still waiting on buffer flusher executor to terminate.");
                    }
                } else if (!this.executorService.awaitTermination(this.shutdownTimeout, TimeUnit.MILLISECONDS)) {
                    logger.warn("Graceful shutdown took too much time, forcing the shutdown.");
                    logger.warn("{} tasks failed to execute before the shutdown.", Integer.valueOf(this.executorService.shutdownNow().size()));
                }
                logger.debug("Shutdown finished.");
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                logger.warn("Graceful shutdown interrupted, forcing the shutdown.");
                logger.warn("{} tasks failed to execute before the shutdown.", Integer.valueOf(this.executorService.shutdownNow().size()));
            }
        } finally {
            this.actualConnection.close();
        }
    }

    @Override // io.sentry.connection.Connection
    public void send(Event event) {
        try {
            this.actualConnection.send(event);
            this.buffer.discard(event);
        } catch (ConnectionException e) {
            boolean z = e.getCause() instanceof NotSerializableException;
            Integer responseCode = e.getResponseCode();
            if (z || (responseCode != null && responseCode.intValue() != 429)) {
                this.buffer.discard(event);
            }
            throw e;
        }
    }

    public Connection wrapConnectionWithBufferWriter(final Connection connection) {
        return new Connection() { // from class: io.sentry.connection.BufferedConnection.2
            final Connection wrappedConnection;

            {
                this.wrappedConnection = connection;
            }

            @Override // io.sentry.connection.Connection
            public void addEventSendCallback(EventSendCallback eventSendCallback) {
                this.wrappedConnection.addEventSendCallback(eventSendCallback);
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                this.wrappedConnection.close();
            }

            @Override // io.sentry.connection.Connection
            public void send(Event event) throws ConnectionException {
                try {
                    BufferedConnection.this.buffer.add(event);
                } catch (Exception e) {
                    BufferedConnection.logger.error("Exception occurred while attempting to add Event to buffer: ", (Throwable) e);
                }
                this.wrappedConnection.send(event);
            }
        };
    }
}
