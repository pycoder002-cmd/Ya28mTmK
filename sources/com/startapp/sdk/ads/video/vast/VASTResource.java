package com.startapp.sdk.ads.video.vast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.gotev.uploadservice.ContentType;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VASTResource {
    public static final List<String> a = Arrays.asList("image/jpeg", "image/png", ContentType.IMAGE_BMP, "image/gif");
    public static final List<String> b = Collections.singletonList(ContentType.APPLICATION_JAVASCRIPT);

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum CreativeType {
        NONE,
        IMAGE,
        JAVASCRIPT
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum Type {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE
    }

    public VASTResource(String str, Type type, CreativeType creativeType, int i, int i2) {
    }
}
