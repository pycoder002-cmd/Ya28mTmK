package io.sentry.time;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class FixedClock implements Clock {
    private Date date;

    public FixedClock(Date date) {
        this.date = date;
    }

    @Override // io.sentry.time.Clock
    public Date date() {
        return this.date;
    }

    @Override // io.sentry.time.Clock
    public long millis() {
        return this.date.getTime();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void tick(long j, TimeUnit timeUnit) {
        this.date = new Date(this.date.getTime() + timeUnit.toMillis(j));
    }
}
