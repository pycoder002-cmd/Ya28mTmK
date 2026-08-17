package io.reactivex.internal.operators.flowable;

import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableGenerate<T, S> extends Flowable<T> {
    final Consumer<? super S> disposeState;
    final BiFunction<S, Emitter<T>, S> generator;
    final Callable<S> stateSupplier;

    /* loaded from: classes.dex */
    static final class GeneratorSubscription<T, S> extends AtomicLong implements Emitter<T>, Subscription {
        private static final long serialVersionUID = 7565982551505011832L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final Consumer<? super S> disposeState;
        final BiFunction<S, ? super Emitter<T>, S> generator;
        boolean hasNext;
        S state;
        boolean terminate;

        GeneratorSubscription(Subscriber<? super T> subscriber, BiFunction<S, ? super Emitter<T>, S> biFunction, Consumer<? super S> consumer, S s) {
            this.actual = subscriber;
            this.generator = biFunction;
            this.disposeState = consumer;
            this.state = s;
        }

        private void dispose(S s) {
            try {
                this.disposeState.accept(s);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (BackpressureHelper.add(this, 1L) == 0) {
                S s = this.state;
                this.state = null;
                dispose(s);
            }
        }

        @Override // io.reactivex.Emitter
        public void onComplete() {
            if (this.terminate) {
                return;
            }
            this.terminate = true;
            this.actual.onComplete();
        }

        @Override // io.reactivex.Emitter
        public void onError(Throwable th) {
            if (this.terminate) {
                RxJavaPlugins.onError(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.terminate = true;
            this.actual.onError(th);
        }

        @Override // io.reactivex.Emitter
        public void onNext(T t) {
            if (this.terminate) {
                return;
            }
            if (this.hasNext) {
                onError(new IllegalStateException("onNext already called in this generate turn"));
            } else if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.hasNext = true;
                this.actual.onNext(t);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
        
            r11.state = r0;
            r4 = addAndGet(-r12);
         */
        @Override // org.reactivestreams.Subscription
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void request(long r12) {
            /*
                r11 = this;
                boolean r0 = io.reactivex.internal.subscriptions.SubscriptionHelper.validate(r12)
                if (r0 != 0) goto L7
                return
            L7:
                long r0 = io.reactivex.internal.util.BackpressureHelper.add(r11, r12)
                r2 = 0
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 == 0) goto L12
                return
            L12:
                S r0 = r11.state
                io.reactivex.functions.BiFunction<S, ? super io.reactivex.Emitter<T>, S> r1 = r11.generator
                r4 = r12
            L17:
                r12 = r2
            L18:
                int r6 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
                if (r6 == 0) goto L51
                boolean r6 = r11.cancelled
                r7 = 0
                if (r6 == 0) goto L27
                r11.state = r7
                r11.dispose(r0)
                return
            L27:
                r6 = 0
                r11.hasNext = r6
                r6 = 1
                java.lang.Object r8 = r1.apply(r0, r11)     // Catch: java.lang.Throwable -> L42
                boolean r0 = r11.terminate
                if (r0 == 0) goto L3b
                r11.cancelled = r6
                r11.state = r7
                r11.dispose(r8)
                return
            L3b:
                r6 = 1
                long r9 = r12 + r6
                r0 = r8
                r12 = r9
                goto L18
            L42:
                r12 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r12)
                r11.cancelled = r6
                r11.state = r7
                r11.onError(r12)
                r11.dispose(r0)
                return
            L51:
                long r4 = r11.get()
                int r6 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
                if (r6 != 0) goto L18
                r11.state = r0
                long r12 = -r12
                long r4 = r11.addAndGet(r12)
                int r12 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r12 != 0) goto L17
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableGenerate.GeneratorSubscription.request(long):void");
        }
    }

    public FlowableGenerate(Callable<S> callable, BiFunction<S, Emitter<T>, S> biFunction, Consumer<? super S> consumer) {
        this.stateSupplier = callable;
        this.generator = biFunction;
        this.disposeState = consumer;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        try {
            subscriber.onSubscribe(new GeneratorSubscription(subscriber, this.generator, this.disposeState, this.stateSupplier.call()));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }
}
