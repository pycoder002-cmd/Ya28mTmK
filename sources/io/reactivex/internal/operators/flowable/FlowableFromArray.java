package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableFromArray<T> extends Flowable<T> {
    final T[] array;

    /* loaded from: classes.dex */
    static final class ArrayConditionalSubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super T> actual;

        ArrayConditionalSubscription(ConditionalSubscriber<? super T> conditionalSubscriber, T[] tArr) {
            super(tArr);
            this.actual = conditionalSubscriber;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void fastPath() {
            T[] tArr = this.array;
            int length = tArr.length;
            ConditionalSubscriber<? super T> conditionalSubscriber = this.actual;
            for (int i = this.index; i != length; i++) {
                if (this.cancelled) {
                    return;
                }
                T t = tArr[i];
                if (t == null) {
                    conditionalSubscriber.onError(new NullPointerException("array element is null"));
                    return;
                }
                conditionalSubscriber.tryOnNext(t);
            }
            if (this.cancelled) {
                return;
            }
            conditionalSubscriber.onComplete();
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0045, code lost:
        
            r12.index = r4;
            r6 = addAndGet(-r13);
         */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void slowPath(long r13) {
            /*
                r12 = this;
                T[] r0 = r12.array
                r1 = 0
                int r3 = r0.length
                int r4 = r12.index
                io.reactivex.internal.fuseable.ConditionalSubscriber<? super T> r5 = r12.actual
                r6 = r13
            La:
                r13 = r1
            Lb:
                int r8 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
                if (r8 == 0) goto L33
                if (r4 == r3) goto L33
                boolean r8 = r12.cancelled
                if (r8 == 0) goto L16
                return
            L16:
                r8 = r0[r4]
                if (r8 != 0) goto L25
                java.lang.NullPointerException r13 = new java.lang.NullPointerException
                java.lang.String r14 = "array element is null"
                r13.<init>(r14)
                r5.onError(r13)
                return
            L25:
                boolean r8 = r5.tryOnNext(r8)
                if (r8 == 0) goto L30
                r8 = 1
                long r10 = r13 + r8
                r13 = r10
            L30:
                int r4 = r4 + 1
                goto Lb
            L33:
                if (r4 != r3) goto L3d
                boolean r13 = r12.cancelled
                if (r13 != 0) goto L3c
                r5.onComplete()
            L3c:
                return
            L3d:
                long r6 = r12.get()
                int r8 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
                if (r8 != 0) goto Lb
                r12.index = r4
                long r13 = -r13
                long r6 = r12.addAndGet(r13)
                int r13 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r13 != 0) goto La
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableFromArray.ArrayConditionalSubscription.slowPath(long):void");
        }
    }

    /* loaded from: classes.dex */
    static final class ArraySubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super T> actual;

        ArraySubscription(Subscriber<? super T> subscriber, T[] tArr) {
            super(tArr);
            this.actual = subscriber;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void fastPath() {
            T[] tArr = this.array;
            int length = tArr.length;
            Subscriber<? super T> subscriber = this.actual;
            for (int i = this.index; i != length; i++) {
                if (this.cancelled) {
                    return;
                }
                T t = tArr[i];
                if (t == null) {
                    subscriber.onError(new NullPointerException("array element is null"));
                    return;
                }
                subscriber.onNext(t);
            }
            if (this.cancelled) {
                return;
            }
            subscriber.onComplete();
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0042, code lost:
        
            r12.index = r4;
            r6 = addAndGet(-r13);
         */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void slowPath(long r13) {
            /*
                r12 = this;
                T[] r0 = r12.array
                r1 = 0
                int r3 = r0.length
                int r4 = r12.index
                org.reactivestreams.Subscriber<? super T> r5 = r12.actual
                r6 = r13
            La:
                r13 = r1
            Lb:
                int r8 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
                if (r8 == 0) goto L30
                if (r4 == r3) goto L30
                boolean r8 = r12.cancelled
                if (r8 == 0) goto L16
                return
            L16:
                r8 = r0[r4]
                if (r8 != 0) goto L25
                java.lang.NullPointerException r13 = new java.lang.NullPointerException
                java.lang.String r14 = "array element is null"
                r13.<init>(r14)
                r5.onError(r13)
                return
            L25:
                r5.onNext(r8)
                r8 = 1
                long r10 = r13 + r8
                int r4 = r4 + 1
                r13 = r10
                goto Lb
            L30:
                if (r4 != r3) goto L3a
                boolean r13 = r12.cancelled
                if (r13 != 0) goto L39
                r5.onComplete()
            L39:
                return
            L3a:
                long r6 = r12.get()
                int r8 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
                if (r8 != 0) goto Lb
                r12.index = r4
                long r13 = -r13
                long r6 = r12.addAndGet(r13)
                int r13 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r13 != 0) goto La
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableFromArray.ArraySubscription.slowPath(long):void");
        }
    }

    /* loaded from: classes.dex */
    static abstract class BaseArraySubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        final T[] array;
        volatile boolean cancelled;
        int index;

        BaseArraySubscription(T[] tArr) {
            this.array = tArr;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.cancelled = true;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final void clear() {
            this.index = this.array.length;
        }

        abstract void fastPath();

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.array.length;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public final T poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i == tArr.length) {
                return null;
            }
            this.index = i + 1;
            return (T) ObjectHelper.requireNonNull(tArr[i], "array element is null");
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long j) {
            if (SubscriptionHelper.validate(j) && BackpressureHelper.add(this, j) == 0) {
                if (j == LongCompanionObject.MAX_VALUE) {
                    fastPath();
                } else {
                    slowPath(j);
                }
            }
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public final int requestFusion(int i) {
            return i & 1;
        }

        abstract void slowPath(long j);
    }

    public FlowableFromArray(T[] tArr) {
        this.array = tArr;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        if (subscriber instanceof ConditionalSubscriber) {
            subscriber.onSubscribe(new ArrayConditionalSubscription((ConditionalSubscriber) subscriber, this.array));
        } else {
            subscriber.onSubscribe(new ArraySubscription(subscriber, this.array));
        }
    }
}
