package io.sentry.context;

/* loaded from: classes2.dex */
public class ThreadLocalContextManager implements ContextManager {
    private final ThreadLocal<Context> context = new ThreadLocal<Context>() { // from class: io.sentry.context.ThreadLocalContextManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Context initialValue() {
            return new Context();
        }
    };

    @Override // io.sentry.context.ContextManager
    public void clear() {
        this.context.remove();
    }

    @Override // io.sentry.context.ContextManager
    public Context getContext() {
        return this.context.get();
    }
}
