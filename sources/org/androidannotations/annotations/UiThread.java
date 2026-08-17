package org.androidannotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface UiThread {

    /* loaded from: classes.dex */
    public enum Propagation {
        ENQUEUE,
        REUSE
    }

    long delay() default 0;

    Propagation propagation() default Propagation.ENQUEUE;
}
