package org.androidannotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface OnActivityResult {

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes.dex */
    public @interface Extra {
        String value() default "";
    }

    int value();
}
