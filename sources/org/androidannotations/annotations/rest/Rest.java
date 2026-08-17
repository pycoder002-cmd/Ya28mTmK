package org.androidannotations.annotations.rest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface Rest {
    Class<?>[] converters();

    Class<?>[] interceptors() default {};

    Class<?> requestFactory() default Void.class;

    String rootUrl() default "";
}
