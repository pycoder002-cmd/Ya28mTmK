package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public abstract class AbstractPrefField {
    protected final String key;
    protected final SharedPreferences sharedPreferences;

    public AbstractPrefField(SharedPreferences sharedPreferences, String str) {
        this.sharedPreferences = sharedPreferences;
        this.key = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void apply(SharedPreferences.Editor editor) {
        SharedPreferencesCompat.apply(editor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SharedPreferences.Editor edit() {
        return this.sharedPreferences.edit();
    }

    public final boolean exists() {
        return this.sharedPreferences.contains(this.key);
    }

    public String key() {
        return this.key;
    }

    public final void remove() {
        apply(edit().remove(this.key));
    }
}
