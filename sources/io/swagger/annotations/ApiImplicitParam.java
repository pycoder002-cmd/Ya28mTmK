package io.swagger.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface ApiImplicitParam {
    String access() default "";

    boolean allowMultiple() default false;

    String allowableValues() default "";

    String dataType() default "";

    String defaultValue() default "";

    String name() default "";

    String paramType() default "";

    boolean required() default false;

    String value() default "";
}
