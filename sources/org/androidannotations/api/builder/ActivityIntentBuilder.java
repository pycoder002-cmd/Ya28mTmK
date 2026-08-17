package org.androidannotations.api.builder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import org.androidannotations.api.builder.ActivityIntentBuilder;

/* loaded from: classes2.dex */
public abstract class ActivityIntentBuilder<I extends ActivityIntentBuilder<I>> extends IntentBuilder<I> {
    public ActivityIntentBuilder(Context context, Intent intent) {
        super(context, intent);
    }

    public ActivityIntentBuilder(Context context, Class<?> cls) {
        super(context, cls);
    }

    public void start() {
        this.context.startActivity(this.intent);
    }

    public void startForResult(int i) {
        if (this.context instanceof Activity) {
            ((Activity) this.context).startActivityForResult(this.intent, i);
        } else {
            this.context.startActivity(this.intent);
        }
    }
}
