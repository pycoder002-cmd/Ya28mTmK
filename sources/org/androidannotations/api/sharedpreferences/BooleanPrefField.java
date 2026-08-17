package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public final class BooleanPrefField extends AbstractPrefField {
    private final boolean defaultValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BooleanPrefField(SharedPreferences sharedPreferences, String str, boolean z) {
        super(sharedPreferences, str);
        this.defaultValue = z;
    }

    public boolean get() {
        return getOr(this.defaultValue);
    }

    public boolean getOr(boolean z) {
        return this.sharedPreferences.getBoolean(this.key, z);
    }

    public void put(boolean z) {
        apply(edit().putBoolean(this.key, z));
    }
}
