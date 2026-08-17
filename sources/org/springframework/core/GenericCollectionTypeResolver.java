package org.springframework.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class GenericCollectionTypeResolver {
    private static Class<?> extractType(Type type, Class<?> cls, int i, Map<TypeVariable, Type> map, Map<Integer, Integer> map2, int i2, int i3) {
        Type type2;
        if ((type instanceof TypeVariable) && map != null && (type2 = map.get(type)) != null) {
            type = type2;
        }
        if (type instanceof ParameterizedType) {
            return extractTypeFromParameterizedType((ParameterizedType) type, cls, i, map, map2, i2, i3);
        }
        if (type instanceof Class) {
            return extractTypeFromClass((Class) type, cls, i, map, map2, i2, i3);
        }
        if (type instanceof GenericArrayType) {
            return extractType(((GenericArrayType) type).getGenericComponentType(), cls, i, map, map2, i2, i3 + 1);
        }
        return null;
    }

    private static Class<?> extractTypeFromClass(Class<?> cls, Class<?> cls2, int i) {
        return extractTypeFromClass(cls, cls2, i, null, null, 1, 1);
    }

    private static Class<?> extractTypeFromClass(Class<?> cls, Class<?> cls2, int i, Map<TypeVariable, Type> map, Map<Integer, Integer> map2, int i2, int i3) {
        if (cls.getName().startsWith("java.util.")) {
            return null;
        }
        if (cls.getSuperclass() != null && isIntrospectionCandidate(cls.getSuperclass())) {
            try {
                return extractType(cls.getGenericSuperclass(), cls2, i, map, map2, i2, i3);
            } catch (MalformedParameterizedTypeException unused) {
            }
        }
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces != null) {
            for (Type type : genericInterfaces) {
                Type rawType = type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type;
                if ((rawType instanceof Class) && isIntrospectionCandidate((Class) rawType)) {
                    return extractType(type, cls2, i, map, map2, i2, i3);
                }
            }
        }
        return null;
    }

    private static Class<?> extractTypeFromParameterizedType(ParameterizedType parameterizedType, Class<?> cls, int i, Map<TypeVariable, Type> map, Map<Integer, Integer> map2, int i2, int i3) {
        Type type;
        if (!(parameterizedType.getRawType() instanceof Class)) {
            return null;
        }
        Class<?> cls2 = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (i2 - i3 > 0) {
            int i4 = i3 + 1;
            Integer num = map2 != null ? map2.get(Integer.valueOf(i4)) : null;
            return extractType(actualTypeArguments[num != null ? num.intValue() : actualTypeArguments.length - 1], cls, i, map, map2, i2, i4);
        }
        if (cls != null && !cls.isAssignableFrom(cls2)) {
            return null;
        }
        Class<?> extractTypeFromClass = extractTypeFromClass(cls2, cls, i, map, map2, i2, i3);
        if (extractTypeFromClass != null) {
            return extractTypeFromClass;
        }
        if (actualTypeArguments == null || i >= actualTypeArguments.length) {
            return null;
        }
        Type type2 = actualTypeArguments[i];
        if ((type2 instanceof TypeVariable) && map != null && (type = map.get(type2)) != null) {
            type2 = type;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (upperBounds == null || upperBounds.length <= 0 || Object.class.equals(upperBounds[0])) {
                Type[] lowerBounds = wildcardType.getLowerBounds();
                if (lowerBounds != null && lowerBounds.length > 0 && !Object.class.equals(lowerBounds[0])) {
                    type2 = lowerBounds[0];
                }
            } else {
                type2 = upperBounds[0];
            }
        }
        if (type2 instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type2).getRawType();
        }
        if (type2 instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type2).getGenericComponentType();
            if (genericComponentType instanceof Class) {
                return Array.newInstance((Class<?>) genericComponentType, 0).getClass();
            }
        } else if (type2 instanceof Class) {
            return (Class) type2;
        }
        return null;
    }

    public static Class<?> getCollectionFieldType(Field field) {
        return getGenericFieldType(field, Collection.class, 0, null, 1);
    }

    public static Class<?> getCollectionFieldType(Field field, int i) {
        return getGenericFieldType(field, Collection.class, 0, null, i);
    }

    public static Class<?> getCollectionFieldType(Field field, int i, Map<Integer, Integer> map) {
        return getGenericFieldType(field, Collection.class, 0, map, i);
    }

    public static Class<?> getCollectionParameterType(MethodParameter methodParameter) {
        return getGenericParameterType(methodParameter, Collection.class, 0);
    }

    public static Class<?> getCollectionReturnType(Method method) {
        return getGenericReturnType(method, Collection.class, 0, 1);
    }

    public static Class<?> getCollectionReturnType(Method method, int i) {
        return getGenericReturnType(method, Collection.class, 0, i);
    }

    public static Class<?> getCollectionType(Class<? extends Collection> cls) {
        return extractTypeFromClass(cls, Collection.class, 0);
    }

    private static Class<?> getGenericFieldType(Field field, Class<?> cls, int i, Map<Integer, Integer> map, int i2) {
        return extractType(field.getGenericType(), cls, i, null, map, i2, 1);
    }

    private static Class<?> getGenericParameterType(MethodParameter methodParameter, Class<?> cls, int i) {
        return extractType(GenericTypeResolver.getTargetType(methodParameter), cls, i, methodParameter.typeVariableMap, methodParameter.typeIndexesPerLevel, methodParameter.getNestingLevel(), 1);
    }

    private static Class<?> getGenericReturnType(Method method, Class<?> cls, int i, int i2) {
        return extractType(method.getGenericReturnType(), cls, i, null, null, i2, 1);
    }

    public static Class<?> getMapKeyFieldType(Field field) {
        return getGenericFieldType(field, Map.class, 0, null, 1);
    }

    public static Class<?> getMapKeyFieldType(Field field, int i) {
        return getGenericFieldType(field, Map.class, 0, null, i);
    }

    public static Class<?> getMapKeyFieldType(Field field, int i, Map<Integer, Integer> map) {
        return getGenericFieldType(field, Map.class, 0, map, i);
    }

    public static Class<?> getMapKeyParameterType(MethodParameter methodParameter) {
        return getGenericParameterType(methodParameter, Map.class, 0);
    }

    public static Class<?> getMapKeyReturnType(Method method) {
        return getGenericReturnType(method, Map.class, 0, 1);
    }

    public static Class<?> getMapKeyReturnType(Method method, int i) {
        return getGenericReturnType(method, Map.class, 0, i);
    }

    public static Class<?> getMapKeyType(Class<? extends Map> cls) {
        return extractTypeFromClass(cls, Map.class, 0);
    }

    public static Class<?> getMapValueFieldType(Field field) {
        return getGenericFieldType(field, Map.class, 1, null, 1);
    }

    public static Class<?> getMapValueFieldType(Field field, int i) {
        return getGenericFieldType(field, Map.class, 1, null, i);
    }

    public static Class<?> getMapValueFieldType(Field field, int i, Map<Integer, Integer> map) {
        return getGenericFieldType(field, Map.class, 1, map, i);
    }

    public static Class<?> getMapValueParameterType(MethodParameter methodParameter) {
        return getGenericParameterType(methodParameter, Map.class, 1);
    }

    public static Class<?> getMapValueReturnType(Method method) {
        return getGenericReturnType(method, Map.class, 1, 1);
    }

    public static Class<?> getMapValueReturnType(Method method, int i) {
        return getGenericReturnType(method, Map.class, 1, i);
    }

    public static Class<?> getMapValueType(Class<? extends Map> cls) {
        return extractTypeFromClass(cls, Map.class, 1);
    }

    private static boolean isIntrospectionCandidate(Class cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }
}
