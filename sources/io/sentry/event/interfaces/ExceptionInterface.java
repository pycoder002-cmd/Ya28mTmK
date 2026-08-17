package io.sentry.event.interfaces;

import java.util.Deque;

/* loaded from: classes2.dex */
public class ExceptionInterface implements SentryInterface {
    public static final String EXCEPTION_INTERFACE = "sentry.interfaces.Exception";
    private final Deque<SentryException> exceptions;

    public ExceptionInterface(Throwable th) {
        this(SentryException.extractExceptionQueue(th));
    }

    public ExceptionInterface(Deque<SentryException> deque) {
        this.exceptions = deque;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.exceptions.equals(((ExceptionInterface) obj).exceptions);
    }

    public Deque<SentryException> getExceptions() {
        return this.exceptions;
    }

    @Override // io.sentry.event.interfaces.SentryInterface
    public String getInterfaceName() {
        return EXCEPTION_INTERFACE;
    }

    public int hashCode() {
        return this.exceptions.hashCode();
    }

    public String toString() {
        return "ExceptionInterface{exceptions=" + this.exceptions + '}';
    }
}
