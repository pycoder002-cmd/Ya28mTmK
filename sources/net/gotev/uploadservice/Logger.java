package net.gotev.uploadservice;

/* loaded from: classes2.dex */
public class Logger {
    private LoggerDelegate mDelegate;
    private LogLevel mLogLevel;

    /* loaded from: classes2.dex */
    public enum LogLevel {
        DEBUG,
        INFO,
        ERROR,
        OFF
    }

    /* loaded from: classes2.dex */
    public interface LoggerDelegate {
        void debug(String str, String str2);

        void error(String str, String str2);

        void error(String str, String str2, Throwable th);

        void info(String str, String str2);
    }

    /* loaded from: classes2.dex */
    private static class SingletonHolder {
        private static final Logger instance = new Logger();

        private SingletonHolder() {
        }
    }

    private Logger() {
        this.mLogLevel = LogLevel.OFF;
        this.mDelegate = new DefaultLoggerDelegate();
    }

    public static void debug(String str, String str2) {
        if (SingletonHolder.instance.mLogLevel.compareTo(LogLevel.DEBUG) <= 0) {
            SingletonHolder.instance.mDelegate.debug(str, str2);
        }
    }

    public static void error(String str, String str2) {
        if (SingletonHolder.instance.mLogLevel.compareTo(LogLevel.ERROR) <= 0) {
            SingletonHolder.instance.mDelegate.error(str, str2);
        }
    }

    public static void error(String str, String str2, Throwable th) {
        if (SingletonHolder.instance.mLogLevel.compareTo(LogLevel.ERROR) <= 0) {
            SingletonHolder.instance.mDelegate.error(str, str2, th);
        }
    }

    public static void info(String str, String str2) {
        if (SingletonHolder.instance.mLogLevel.compareTo(LogLevel.INFO) <= 0) {
            SingletonHolder.instance.mDelegate.info(str, str2);
        }
    }

    public static void resetLoggerDelegate() {
        synchronized (Logger.class) {
            SingletonHolder.instance.mDelegate = new DefaultLoggerDelegate();
        }
    }

    public static void setLogLevel(LogLevel logLevel) {
        synchronized (Logger.class) {
            SingletonHolder.instance.mLogLevel = logLevel;
        }
    }

    public static void setLoggerDelegate(LoggerDelegate loggerDelegate) {
        if (loggerDelegate == null) {
            throw new IllegalArgumentException("delegate MUST not be null!");
        }
        synchronized (Logger.class) {
            SingletonHolder.instance.mDelegate = loggerDelegate;
        }
    }
}
