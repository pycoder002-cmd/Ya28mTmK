package com.github.pwittchen.reactivebus.library;

import io.reactivex.Flowable;

/* loaded from: classes.dex */
public interface Bus {
    Flowable<Event> receive();

    void send(Event event);
}
