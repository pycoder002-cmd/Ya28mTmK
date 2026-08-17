package io.sentry;

import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import java.lang.Thread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public class SentryUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) SentryClientFactory.class);
    private Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private volatile Boolean enabled = true;

    public SentryUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.defaultExceptionHandler = uncaughtExceptionHandler;
    }

    public static SentryUncaughtExceptionHandler setup() {
        logger.debug("Configuring uncaught exception handler.");
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            logger.debug("default UncaughtExceptionHandler class='" + defaultUncaughtExceptionHandler.getClass().getName() + "'");
        }
        SentryUncaughtExceptionHandler sentryUncaughtExceptionHandler = new SentryUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(sentryUncaughtExceptionHandler);
        return sentryUncaughtExceptionHandler;
    }

    public void disable() {
        this.enabled = false;
        if (Thread.getDefaultUncaughtExceptionHandler() == this) {
            Thread.setDefaultUncaughtExceptionHandler(this.defaultExceptionHandler);
        }
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (this.enabled.booleanValue()) {
            logger.trace("Uncaught exception received.");
            try {
                Sentry.capture(new EventBuilder().withMessage(th.getMessage()).withLevel(Event.Level.FATAL).withSentryInterface(new ExceptionInterface(th)));
            } catch (Exception e) {
                logger.error("Error sending uncaught exception to Sentry.", (Throwable) e);
            }
        }
        if (this.defaultExceptionHandler != null) {
            this.defaultExceptionHandler.uncaughtException(thread, th);
            return;
        }
        if (th instanceof ThreadDeath) {
            return;
        }
        System.err.print("Exception in thread \"" + thread.getName() + "\" ");
        th.printStackTrace(System.err);
    }
}
