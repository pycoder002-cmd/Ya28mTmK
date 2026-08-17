package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public final class StringPrefField extends AbstractPrefField {
    private final String defaultValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StringPrefField(SharedPreferences sharedPreferences, String str, String str2) {
        super(sharedPreferences, str);
        this.defaultValue = str2;
    }

    public String get() {
        return getOr(this.defaultValue);
    }

    public String getOr(String str) {
        return this.sharedPreferences.getString(this.key, str);
    }

    public void put(String str) {
        apply(edit().putString(this.key, str));
    }
}
