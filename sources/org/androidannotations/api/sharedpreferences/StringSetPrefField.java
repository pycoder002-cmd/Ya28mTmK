package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import java.util.Set;

/* loaded from: classes2.dex */
public final class StringSetPrefField extends AbstractPrefField {
    private final Set<String> defaultValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StringSetPrefField(SharedPreferences sharedPreferences, String str, Set<String> set) {
        super(sharedPreferences, str);
        this.defaultValue = set;
    }

    public Set<String> get() {
        return getOr(this.defaultValue);
    }

    public Set<String> getOr(Set<String> set) {
        return SharedPreferencesCompat.getStringSet(this.sharedPreferences, this.key, set);
    }

    public void put(Set<String> set) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        SharedPreferencesCompat.putStringSet(edit, this.key, set);
        apply(edit);
    }
}
