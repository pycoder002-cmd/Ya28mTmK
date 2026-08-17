package kotlin.jvm.internal;

import com.kingfisher.easy_sharedpreference_library.BuildConfig;
import kotlin.SinceKotlin;
import kotlin.reflect.KMutableProperty;

/* loaded from: classes2.dex */
public abstract class MutablePropertyReference extends PropertyReference implements KMutableProperty {
    public MutablePropertyReference() {
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public MutablePropertyReference(Object obj) {
        super(obj);
    }
}
