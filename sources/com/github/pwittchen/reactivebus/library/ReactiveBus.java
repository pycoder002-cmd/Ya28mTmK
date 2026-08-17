package com.github.pwittchen.reactivebus.library;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/* loaded from: classes.dex */
public class ReactiveBus implements Bus {
    private final Subject<Object> bus = PublishSubject.create().toSerialized();

    public static ReactiveBus create() {
        return new ReactiveBus();
    }

    @Override // com.github.pwittchen.reactivebus.library.Bus
    public Flowable<Event> receive() {
        return this.bus.toFlowable(BackpressureStrategy.BUFFER).filter(new Predicate<Object>() { // from class: com.github.pwittchen.reactivebus.library.ReactiveBus.1
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                return obj instanceof Event;
            }
        });
    }

    @Override // com.github.pwittchen.reactivebus.library.Bus
    public void send(Event event) {
        this.bus.onNext(event);
    }
}
