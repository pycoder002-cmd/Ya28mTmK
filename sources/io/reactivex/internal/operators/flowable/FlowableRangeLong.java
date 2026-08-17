package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableRangeLong extends Flowable<Long> {
    final long end;
    final long start;

    /* loaded from: classes.dex */
    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Long> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final long end;
        long index;

        BaseRangeSubscription(long j, long j2) {
            this.index = j;
            this.end = j2;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.cancelled = true;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final void clear() {
            this.index = this.end;
        }

        abstract void fastPath();

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public final Long poll() {
            long j = this.index;
            if (j == this.end) {
                return null;
            }
            this.index = j + 1;
            return Long.valueOf(j);
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

    /* loaded from: classes.dex */
    static final class RangeConditionalSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super Long> actual;

        RangeConditionalSubscription(ConditionalSubscriber<? super Long> conditionalSubscriber, long j, long j2) {
            super(j, j2);
            this.actual = conditionalSubscriber;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void fastPath() {
            long j = this.end;
            ConditionalSubscriber<? super Long> conditionalSubscriber = this.actual;
            for (long j2 = this.index; j2 != j; j2++) {
                if (this.cancelled) {
                    return;
                }
                conditionalSubscriber.tryOnNext(Long.valueOf(j2));
            }
            if (this.cancelled) {
                return;
            }
            conditionalSubscriber.onComplete();
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0042, code lost:
        
            r17.index = r10;
            r8 = addAndGet(-r3);
         */
        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void slowPath(long r18) {
            /*
                r17 = this;
                r0 = r17
                long r1 = r0.end
                long r3 = r0.index
                io.reactivex.internal.fuseable.ConditionalSubscriber<? super java.lang.Long> r5 = r0.actual
                r6 = 0
                r8 = r18
                r10 = r3
            Ld:
                r3 = r6
            Le:
                int r12 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
                if (r12 == 0) goto L2e
                int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
                if (r12 == 0) goto L2e
                boolean r12 = r0.cancelled
                if (r12 == 0) goto L1b
                return
            L1b:
                java.lang.Long r12 = java.lang.Long.valueOf(r10)
                boolean r12 = r5.tryOnNext(r12)
                r13 = 1
                if (r12 == 0) goto L2a
                long r15 = r3 + r13
                r3 = r15
            L2a:
                long r15 = r10 + r13
                r10 = r15
                goto Le
            L2e:
                int r8 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
                if (r8 != 0) goto L3a
                boolean r1 = r0.cancelled
                if (r1 != 0) goto L39
                r5.onComplete()
            L39:
                return
            L3a:
                long r8 = r17.get()
                int r12 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
                if (r12 != 0) goto Le
                r0.index = r10
                long r3 = -r3
                long r8 = r0.addAndGet(r3)
                int r3 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r3 != 0) goto Ld
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableRangeLong.RangeConditionalSubscription.slowPath(long):void");
        }
    }

    /* loaded from: classes.dex */
    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super Long> actual;

        RangeSubscription(Subscriber<? super Long> subscriber, long j, long j2) {
            super(j, j2);
            this.actual = subscriber;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void fastPath() {
            long j = this.end;
            Subscriber<? super Long> subscriber = this.actual;
            for (long j2 = this.index; j2 != j; j2++) {
                if (this.cancelled) {
                    return;
                }
                subscriber.onNext(Long.valueOf(j2));
            }
            if (this.cancelled) {
                return;
            }
            subscriber.onComplete();
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
        
            r13.index = r7;
            r2 = addAndGet(-r14);
         */
        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void slowPath(long r14) {
            /*
                r13 = this;
                long r0 = r13.end
                long r2 = r13.index
                org.reactivestreams.Subscriber<? super java.lang.Long> r4 = r13.actual
                r5 = 0
                r7 = r2
                r2 = r14
            La:
                r14 = r5
            Lb:
                int r9 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
                if (r9 == 0) goto L28
                int r9 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r9 == 0) goto L28
                boolean r9 = r13.cancelled
                if (r9 == 0) goto L18
                return
            L18:
                java.lang.Long r9 = java.lang.Long.valueOf(r7)
                r4.onNext(r9)
                r9 = 1
                long r11 = r14 + r9
                long r14 = r7 + r9
                r7 = r14
                r14 = r11
                goto Lb
            L28:
                int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r2 != 0) goto L34
                boolean r14 = r13.cancelled
                if (r14 != 0) goto L33
                r4.onComplete()
            L33:
                return
            L34:
                long r2 = r13.get()
                int r9 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
                if (r9 != 0) goto Lb
                r13.index = r7
                long r14 = -r14
                long r2 = r13.addAndGet(r14)
                int r14 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
                if (r14 != 0) goto La
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableRangeLong.RangeSubscription.slowPath(long):void");
        }
    }

    public FlowableRangeLong(long j, long j2) {
        this.start = j;
        this.end = j + j2;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Long> subscriber) {
        if (subscriber instanceof ConditionalSubscriber) {
            subscriber.onSubscribe(new RangeConditionalSubscription((ConditionalSubscriber) subscriber, this.start, this.end));
        } else {
            subscriber.onSubscribe(new RangeSubscription(subscriber, this.start, this.end));
        }
    }
}
