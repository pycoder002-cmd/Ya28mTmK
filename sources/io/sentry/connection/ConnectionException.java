package io.sentry.connection;

/* loaded from: classes2.dex */
public class ConnectionException extends RuntimeException {
    private Long recommendedLockdownTime;
    private Integer responseCode;

    public ConnectionException() {
        this.recommendedLockdownTime = null;
        this.responseCode = null;
    }

    public ConnectionException(String str, Throwable th) {
        super(str, th);
        this.recommendedLockdownTime = null;
        this.responseCode = null;
    }

    public ConnectionException(String str, Throwable th, Long l, Integer num) {
        super(str, th);
        this.recommendedLockdownTime = null;
        this.responseCode = null;
        this.recommendedLockdownTime = l;
        this.responseCode = num;
    }

    public Long getRecommendedLockdownTime() {
        return this.recommendedLockdownTime;
    }

    public Integer getResponseCode() {
        return this.responseCode;
    }
}
