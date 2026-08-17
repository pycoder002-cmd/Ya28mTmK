package cz.msebera.android.httpclient.config;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@NotThreadSafe
/* loaded from: classes.dex */
public final class RegistryBuilder<I> {
    private final Map<String, I> items = new HashMap();

    RegistryBuilder() {
    }

    public static <I> RegistryBuilder<I> create() {
        return new RegistryBuilder<>();
    }

    public Registry<I> build() {
        return new Registry<>(this.items);
    }

    public RegistryBuilder<I> register(String str, I i) {
        Args.notEmpty(str, "ID");
        Args.notNull(i, "Item");
        this.items.put(str.toLowerCase(Locale.ROOT), i);
        return this;
    }

    public String toString() {
        return this.items.toString();
    }
}
