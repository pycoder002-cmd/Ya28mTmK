package io.sentry.event.helper;

import io.sentry.event.Event;

/* loaded from: classes2.dex */
public interface ShouldSendEventCallback {
    boolean shouldSend(Event event);
}
