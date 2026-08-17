package org.springframework.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class MethodParameter {
    private final Constructor<?> constructor;
    private Type genericParameterType;
    private final Method method;
    private int nestingLevel;
    private Annotation[] parameterAnnotations;
    private final int parameterIndex;
    private String parameterName;
    private ParameterNameDiscoverer parameterNameDiscoverer;
    private Class<?> parameterType;
    Map<Integer, Integer> typeIndexesPerLevel;
    Map<TypeVariable, Type> typeVariableMap;

    public MethodParameter(Constructor<?> constructor, int i) {
        this(constructor, i, 1);
    }

    public MethodParameter(Constructor<?> constructor, int i, int i2) {
        this.nestingLevel = 1;
        Assert.notNull(constructor, "Constructor must not be null");
        this.constructor = constructor;
        this.parameterIndex = i;
        this.nestingLevel = i2;
        this.method = null;
    }

    public MethodParameter(Method method, int i) {
        this(method, i, 1);
    }

    public MethodParameter(Method method, int i, int i2) {
        this.nestingLevel = 1;
        Assert.notNull(method, "Method must not be null");
        this.method = method;
        this.parameterIndex = i;
        this.nestingLevel = i2;
        this.constructor = null;
    }

    public MethodParameter(MethodParameter methodParameter) {
        this.nestingLevel = 1;
        Assert.notNull(methodParameter, "Original must not be null");
        this.method = methodParameter.method;
        this.constructor = methodParameter.constructor;
        this.parameterIndex = methodParameter.parameterIndex;
        this.parameterType = methodParameter.parameterType;
        this.genericParameterType = methodParameter.genericParameterType;
        this.parameterAnnotations = methodParameter.parameterAnnotations;
        this.parameterNameDiscoverer = methodParameter.parameterNameDiscoverer;
        this.parameterName = methodParameter.parameterName;
        this.nestingLevel = methodParameter.nestingLevel;
        this.typeIndexesPerLevel = methodParameter.typeIndexesPerLevel;
        this.typeVariableMap = methodParameter.typeVariableMap;
    }

    public static MethodParameter forMethodOrConstructor(Object obj, int i) {
        if (obj instanceof Method) {
            return new MethodParameter((Method) obj, i);
        }
        if (obj instanceof Constructor) {
            return new MethodParameter((Constructor<?>) obj, i);
        }
        throw new IllegalArgumentException("Given object [" + obj + "] is neither a Method nor a Constructor");
    }

    private AnnotatedElement getAnnotatedElement() {
        return this.method != null ? this.method : this.constructor;
    }

    private Member getMember() {
        return this.method != null ? this.method : this.constructor;
    }

    private Map<Integer, Integer> getTypeIndexesPerLevel() {
        if (this.typeIndexesPerLevel == null) {
            this.typeIndexesPerLevel = new HashMap(4);
        }
        return this.typeIndexesPerLevel;
    }

    public void decreaseNestingLevel() {
        getTypeIndexesPerLevel().remove(Integer.valueOf(this.nestingLevel));
        this.nestingLevel--;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MethodParameter)) {
            return false;
        }
        MethodParameter methodParameter = (MethodParameter) obj;
        return this.parameterIndex == methodParameter.parameterIndex && getMember().equals(methodParameter.getMember());
    }

    public Constructor<?> getConstructor() {
        return this.constructor;
    }

    public Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    public Type getGenericParameterType() {
        if (this.genericParameterType == null) {
            if (this.parameterIndex < 0) {
                this.genericParameterType = this.method != null ? this.method.getGenericReturnType() : null;
            } else {
                this.genericParameterType = this.method != null ? this.method.getGenericParameterTypes()[this.parameterIndex] : this.constructor.getGenericParameterTypes()[this.parameterIndex];
            }
        }
        return this.genericParameterType;
    }

    public Method getMethod() {
        return this.method;
    }

    public <T extends Annotation> T getMethodAnnotation(Class<T> cls) {
        return (T) getAnnotatedElement().getAnnotation(cls);
    }

    public Annotation[] getMethodAnnotations() {
        return getAnnotatedElement().getAnnotations();
    }

    public Class<?> getNestedParameterType() {
        if (this.nestingLevel <= 1) {
            return getParameterType();
        }
        Type genericParameterType = getGenericParameterType();
        if (!(genericParameterType instanceof ParameterizedType)) {
            return Object.class;
        }
        Integer typeIndexForCurrentLevel = getTypeIndexForCurrentLevel();
        Type type = ((ParameterizedType) genericParameterType).getActualTypeArguments()[typeIndexForCurrentLevel != null ? typeIndexForCurrentLevel.intValue() : 0];
        if (type instanceof Class) {
            return (Class) type;
        }
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        return rawType instanceof Class ? (Class) rawType : Object.class;
    }

    public int getNestingLevel() {
        return this.nestingLevel;
    }

    public <T extends Annotation> T getParameterAnnotation(Class<T> cls) {
        for (Annotation annotation : getParameterAnnotations()) {
            T t = (T) annotation;
            if (cls.isInstance(t)) {
                return t;
            }
        }
        return null;
    }

    public Annotation[] getParameterAnnotations() {
        if (this.parameterAnnotations == null) {
            Annotation[][] parameterAnnotations = this.method != null ? this.method.getParameterAnnotations() : this.constructor.getParameterAnnotations();
            if (this.parameterIndex < 0 || this.parameterIndex >= parameterAnnotations.length) {
                this.parameterAnnotations = new Annotation[0];
            } else {
                this.parameterAnnotations = parameterAnnotations[this.parameterIndex];
            }
        }
        return this.parameterAnnotations;
    }

    public int getParameterIndex() {
        return this.parameterIndex;
    }

    public String getParameterName() {
        if (this.parameterNameDiscoverer != null) {
            String[] parameterNames = this.method != null ? this.parameterNameDiscoverer.getParameterNames(this.method) : this.parameterNameDiscoverer.getParameterNames(this.constructor);
            if (parameterNames != null) {
                this.parameterName = parameterNames[this.parameterIndex];
            }
            this.parameterNameDiscoverer = null;
        }
        return this.parameterName;
    }

    public Class<?> getParameterType() {
        if (this.parameterType == null) {
            if (this.parameterIndex < 0) {
                this.parameterType = this.method != null ? this.method.getReturnType() : null;
            } else {
                this.parameterType = this.method != null ? this.method.getParameterTypes()[this.parameterIndex] : this.constructor.getParameterTypes()[this.parameterIndex];
            }
        }
        return this.parameterType;
    }

    public Integer getTypeIndexForCurrentLevel() {
        return getTypeIndexForLevel(this.nestingLevel);
    }

    public Integer getTypeIndexForLevel(int i) {
        return getTypeIndexesPerLevel().get(Integer.valueOf(i));
    }

    public <T extends Annotation> boolean hasParameterAnnotation(Class<T> cls) {
        return getParameterAnnotation(cls) != null;
    }

    public boolean hasParameterAnnotations() {
        return getParameterAnnotations().length != 0;
    }

    public int hashCode() {
        return (getMember().hashCode() * 31) + this.parameterIndex;
    }

    public void increaseNestingLevel() {
        this.nestingLevel++;
    }

    public void initParameterNameDiscovery(ParameterNameDiscoverer parameterNameDiscoverer) {
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParameterType(Class<?> cls) {
        this.parameterType = cls;
    }

    public void setTypeIndexForCurrentLevel(int i) {
        getTypeIndexesPerLevel().put(Integer.valueOf(this.nestingLevel), Integer.valueOf(i));
    }
}
