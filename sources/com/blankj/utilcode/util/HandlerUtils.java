package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class HandlerUtils {

    /* loaded from: classes.dex */
    public static class HandlerHolder extends Handler {
        WeakReference<Handler.Callback> mListenerWeakReference;

        public HandlerHolder(Handler.Callback callback) {
            this.mListenerWeakReference = new WeakReference<>(callback);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.mListenerWeakReference == null || this.mListenerWeakReference.get() == null) {
                return;
            }
            this.mListenerWeakReference.get().handleMessage(message);
        }
    }

    private HandlerUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
}
