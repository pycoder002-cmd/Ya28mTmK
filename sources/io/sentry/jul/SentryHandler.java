package io.sentry.jul;

import io.sentry.Sentry;
import io.sentry.environment.SentryEnvironment;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import io.sentry.event.interfaces.MessageInterface;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import org.slf4j.MDC;

/* loaded from: classes2.dex */
public class SentryHandler extends Handler {
    public static final String THREAD_ID = "Sentry-ThreadId";
    protected boolean printfStyle;

    /* loaded from: classes2.dex */
    private class DropSentryFilter implements Filter {
        private DropSentryFilter() {
        }

        @Override // java.util.logging.Filter
        public boolean isLoggable(LogRecord logRecord) {
            String loggerName = logRecord.getLoggerName();
            return loggerName == null || !loggerName.startsWith("io.sentry");
        }
    }

    public SentryHandler() {
        retrieveProperties();
        setFilter(new DropSentryFilter());
    }

    protected static List<String> formatMessageParameters(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            arrayList.add(obj != null ? obj.toString() : null);
        }
        return arrayList;
    }

    protected static Event.Level getLevel(Level level) {
        if (level.intValue() >= Level.SEVERE.intValue()) {
            return Event.Level.ERROR;
        }
        if (level.intValue() >= Level.WARNING.intValue()) {
            return Event.Level.WARNING;
        }
        if (level.intValue() >= Level.INFO.intValue()) {
            return Event.Level.INFO;
        }
        if (level.intValue() >= Level.ALL.intValue()) {
            return Event.Level.DEBUG;
        }
        return null;
    }

    private Level parseLevelOrDefault(String str) {
        try {
            return Level.parse(str.trim());
        } catch (Exception unused) {
            return Level.WARNING;
        }
    }

    @Override // java.util.logging.Handler
    public void close() throws SecurityException {
        SentryEnvironment.startManagingThread();
        try {
            try {
                Sentry.close();
            } catch (Exception e) {
                reportError("An exception occurred while closing the Sentry connection", e, 3);
            }
        } finally {
            SentryEnvironment.stopManagingThread();
        }
    }

    protected EventBuilder createEventBuilder(LogRecord logRecord) {
        String str;
        String str2;
        EventBuilder withLogger = new EventBuilder().withSdkIntegration("java.util.logging").withLevel(getLevel(logRecord.getLevel())).withTimestamp(new Date(logRecord.getMillis())).withLogger(logRecord.getLoggerName());
        String message = logRecord.getMessage();
        if (logRecord.getResourceBundle() != null && logRecord.getResourceBundle().containsKey(logRecord.getMessage())) {
            message = logRecord.getResourceBundle().getString(logRecord.getMessage());
        }
        if (logRecord.getParameters() == null) {
            withLogger.withSentryInterface(new MessageInterface(message));
        } else {
            List<String> formatMessageParameters = formatMessageParameters(logRecord.getParameters());
            try {
                str = formatMessage(message, logRecord.getParameters());
                str2 = str;
            } catch (Exception unused) {
                str = null;
                str2 = message;
            }
            withLogger.withSentryInterface(new MessageInterface(message, formatMessageParameters, str));
            message = str2;
        }
        withLogger.withMessage(message);
        Throwable thrown = logRecord.getThrown();
        if (thrown != null) {
            withLogger.withSentryInterface(new ExceptionInterface(thrown));
        }
        Map<String, String> copyOfContextMap = MDC.getMDCAdapter().getCopyOfContextMap();
        if (copyOfContextMap != null) {
            for (Map.Entry<String, String> entry : copyOfContextMap.entrySet()) {
                if (Sentry.getStoredClient().getMdcTags().contains(entry.getKey())) {
                    withLogger.withTag(entry.getKey(), entry.getValue());
                } else {
                    withLogger.withExtra(entry.getKey(), entry.getValue());
                }
            }
        }
        withLogger.withExtra(THREAD_ID, Integer.valueOf(logRecord.getThreadID()));
        return withLogger;
    }

    @Override // java.util.logging.Handler
    public void flush() {
    }

    protected String formatMessage(String str, Object[] objArr) {
        return this.printfStyle ? String.format(str, objArr) : MessageFormat.format(str, objArr);
    }

    @Override // java.util.logging.Handler
    public void publish(LogRecord logRecord) {
        if (!isLoggable(logRecord) || SentryEnvironment.isManagingThread()) {
            return;
        }
        SentryEnvironment.startManagingThread();
        try {
            try {
                Sentry.capture(createEventBuilder(logRecord));
            } catch (Exception e) {
                reportError("An exception occurred while creating a new event in Sentry", e, 1);
            }
        } finally {
            SentryEnvironment.stopManagingThread();
        }
    }

    protected void retrieveProperties() {
        LogManager logManager = LogManager.getLogManager();
        String name = SentryHandler.class.getName();
        setPrintfStyle(Boolean.valueOf(logManager.getProperty(name + ".printfStyle")).booleanValue());
        setLevel(parseLevelOrDefault(logManager.getProperty(name + ".level")));
    }

    public void setPrintfStyle(boolean z) {
        this.printfStyle = z;
    }
}
