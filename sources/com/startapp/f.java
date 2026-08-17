package com.startapp;

import com.startapp.common.parser.TypeParser;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* compiled from: StartAppSDK */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface f {
    boolean complex() default false;

    Class innerValue() default String.class;

    Class key() default String.class;

    String name() default "";

    Class<? extends TypeParser> parser() default TypeParser.class;

    Class type() default Object.class;

    Class value() default String.class;
}
