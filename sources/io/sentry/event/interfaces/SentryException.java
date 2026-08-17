package io.sentry.event.interfaces;

import io.sentry.jvmti.FrameCache;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class SentryException implements Serializable {
    public static final String DEFAULT_PACKAGE_NAME = "(default)";
    private final String exceptionClassName;
    private final String exceptionMessage;
    private final String exceptionPackageName;
    private final StackTraceInterface stackTraceInterface;

    public SentryException(String str, String str2, String str3, StackTraceInterface stackTraceInterface) {
        this.exceptionMessage = str;
        this.exceptionClassName = str2;
        this.exceptionPackageName = str3;
        this.stackTraceInterface = stackTraceInterface;
    }

    public SentryException(Throwable th, StackTraceElement[] stackTraceElementArr) {
        this.exceptionMessage = th.getMessage();
        this.exceptionClassName = th.getClass().getSimpleName();
        Package r0 = th.getClass().getPackage();
        this.exceptionPackageName = r0 != null ? r0.getName() : null;
        this.stackTraceInterface = new StackTraceInterface(th.getStackTrace(), stackTraceElementArr, FrameCache.get(th));
    }

    public static Deque<SentryException> extractExceptionQueue(Throwable th) {
        ArrayDeque arrayDeque = new ArrayDeque();
        HashSet hashSet = new HashSet();
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[0];
        while (th != null && hashSet.add(th)) {
            arrayDeque.add(new SentryException(th, stackTraceElementArr));
            stackTraceElementArr = th.getStackTrace();
            th = th.getCause();
        }
        return arrayDeque;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SentryException sentryException = (SentryException) obj;
        if (!this.exceptionClassName.equals(sentryException.exceptionClassName)) {
            return false;
        }
        if (this.exceptionMessage == null ? sentryException.exceptionMessage != null : !this.exceptionMessage.equals(sentryException.exceptionMessage)) {
            return false;
        }
        if (this.exceptionPackageName == null ? sentryException.exceptionPackageName == null : this.exceptionPackageName.equals(sentryException.exceptionPackageName)) {
            return this.stackTraceInterface.equals(sentryException.stackTraceInterface);
        }
        return false;
    }

    public String getExceptionClassName() {
        return this.exceptionClassName;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    public String getExceptionPackageName() {
        return this.exceptionPackageName != null ? this.exceptionPackageName : DEFAULT_PACKAGE_NAME;
    }

    public StackTraceInterface getStackTraceInterface() {
        return this.stackTraceInterface;
    }

    public int hashCode() {
        return (31 * (((this.exceptionMessage != null ? this.exceptionMessage.hashCode() : 0) * 31) + this.exceptionClassName.hashCode())) + (this.exceptionPackageName != null ? this.exceptionPackageName.hashCode() : 0);
    }

    public String toString() {
        return "SentryException{exceptionMessage='" + this.exceptionMessage + "', exceptionClassName='" + this.exceptionClassName + "', exceptionPackageName='" + this.exceptionPackageName + "', stackTraceInterface=" + this.stackTraceInterface + '}';
    }
}
