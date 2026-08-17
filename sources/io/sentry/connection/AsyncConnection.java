package io.sentry.connection;

import io.sentry.SentryClient;
import io.sentry.environment.SentryEnvironment;
import io.sentry.event.Event;
import io.sentry.util.Util;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/* loaded from: classes2.dex */
public class AsyncConnection implements Connection {
    private final Connection actualConnection;
    private volatile boolean closed;
    private final ExecutorService executorService;
    private boolean gracefulShutdown;
    private final ShutDownHook shutDownHook = new ShutDownHook();
    private final long shutdownTimeout;
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) AsyncConnection.class);
    private static final Logger lockdownLogger = LoggerFactory.getLogger(SentryClient.class.getName() + ".lockdown");

    /* loaded from: classes2.dex */
    private final class EventSubmitter implements Runnable {
        private final Event event;
        private Map<String, String> mdcContext;

        private EventSubmitter(Event event, Map<String, String> map) {
            this.event = event;
            this.mdcContext = map;
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
        
            if (r0 != null) goto L9;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r4 = this;
                io.sentry.environment.SentryEnvironment.startManagingThread()
                java.util.Map r0 = org.slf4j.MDC.getCopyOfContextMap()
                java.util.Map<java.lang.String, java.lang.String> r1 = r4.mdcContext
                if (r1 != 0) goto Lf
                org.slf4j.MDC.clear()
                goto L14
            Lf:
                java.util.Map<java.lang.String, java.lang.String> r1 = r4.mdcContext
                org.slf4j.MDC.setContextMap(r1)
            L14:
                io.sentry.connection.AsyncConnection r1 = io.sentry.connection.AsyncConnection.this     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e java.lang.Throwable -> L3b
                io.sentry.connection.Connection r1 = io.sentry.connection.AsyncConnection.access$300(r1)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e java.lang.Throwable -> L3b
                io.sentry.event.Event r2 = r4.event     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e java.lang.Throwable -> L3b
                r1.send(r2)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e java.lang.Throwable -> L3b
                if (r0 != 0) goto L25
            L21:
                org.slf4j.MDC.clear()
                goto L28
            L25:
                org.slf4j.MDC.setContextMap(r0)
            L28:
                io.sentry.environment.SentryEnvironment.stopManagingThread()
                goto L58
            L2c:
                r1 = move-exception
                goto L59
            L2e:
                r1 = move-exception
                org.slf4j.Logger r2 = io.sentry.connection.AsyncConnection.access$400()     // Catch: java.lang.Throwable -> L2c
                java.lang.String r3 = "An exception occurred while sending the event to Sentry."
                r2.error(r3, r1)     // Catch: java.lang.Throwable -> L2c
                if (r0 != 0) goto L25
                goto L21
            L3b:
                org.slf4j.Logger r1 = io.sentry.connection.AsyncConnection.access$400()     // Catch: java.lang.Throwable -> L2c
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2c
                r2.<init>()     // Catch: java.lang.Throwable -> L2c
                java.lang.String r3 = "Dropping an Event due to lockdown: "
                r2.append(r3)     // Catch: java.lang.Throwable -> L2c
                io.sentry.event.Event r3 = r4.event     // Catch: java.lang.Throwable -> L2c
                r2.append(r3)     // Catch: java.lang.Throwable -> L2c
                java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L2c
                r1.debug(r2)     // Catch: java.lang.Throwable -> L2c
                if (r0 != 0) goto L25
                goto L21
            L58:
                return
            L59:
                if (r0 != 0) goto L5f
                org.slf4j.MDC.clear()
                goto L62
            L5f:
                org.slf4j.MDC.setContextMap(r0)
            L62:
                io.sentry.environment.SentryEnvironment.stopManagingThread()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.sentry.connection.AsyncConnection.EventSubmitter.run():void");
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
                        AsyncConnection.this.doClose();
                    } catch (Exception e) {
                        AsyncConnection.logger.error("An exception occurred while closing the connection.", (Throwable) e);
                    }
                } finally {
                    SentryEnvironment.stopManagingThread();
                }
            }
        }
    }

    public AsyncConnection(Connection connection, ExecutorService executorService, boolean z, long j) {
        this.actualConnection = connection;
        if (executorService == null) {
            this.executorService = Executors.newSingleThreadExecutor();
        } else {
            this.executorService = executorService;
        }
        if (z) {
            this.gracefulShutdown = z;
            addShutdownHook();
        }
        this.shutdownTimeout = j;
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(this.shutDownHook);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doClose() throws IOException {
        logger.debug("Gracefully shutting down Sentry async threads.");
        this.closed = true;
        this.executorService.shutdown();
        try {
            try {
                if (this.shutdownTimeout == -1) {
                    while (!this.executorService.awaitTermination(5000L, TimeUnit.MILLISECONDS)) {
                        logger.debug("Still waiting on async executor to terminate.");
                    }
                } else if (!this.executorService.awaitTermination(this.shutdownTimeout, TimeUnit.MILLISECONDS)) {
                    logger.warn("Graceful shutdown took too much time, forcing the shutdown.");
                    logger.warn("{} tasks failed to execute before shutdown.", Integer.valueOf(this.executorService.shutdownNow().size()));
                }
                logger.debug("Shutdown finished.");
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                logger.warn("Graceful shutdown interrupted, forcing the shutdown.");
                logger.warn("{} tasks failed to execute before shutdown.", Integer.valueOf(this.executorService.shutdownNow().size()));
            }
        } finally {
            this.actualConnection.close();
        }
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
        doClose();
    }

    @Override // io.sentry.connection.Connection
    public void send(Event event) {
        if (this.closed) {
            return;
        }
        this.executorService.execute(new EventSubmitter(event, MDC.getCopyOfContextMap()));
    }
}
