package org.springframework.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* loaded from: classes2.dex */
public abstract class TypeUtils {
    private static boolean isAssignable(ParameterizedType parameterizedType, ParameterizedType parameterizedType2) {
        if (parameterizedType.equals(parameterizedType2)) {
            return true;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
        if (actualTypeArguments.length != actualTypeArguments2.length) {
            return false;
        }
        int length = actualTypeArguments.length;
        for (int i = 0; i < length; i++) {
            Type type = actualTypeArguments[i];
            Type type2 = actualTypeArguments2[i];
            if (!type.equals(type2) && (!(type instanceof WildcardType) || !isAssignable((WildcardType) type, type2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAssignable(Type type, Type type2) {
        Assert.notNull(type, "Left-hand side type must not be null");
        Assert.notNull(type2, "Right-hand side type must not be null");
        if (type.equals(type2) || type.equals(Object.class)) {
            return true;
        }
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (type2 instanceof Class) {
                return ClassUtils.isAssignable(cls, (Class) type2);
            }
            if (type2 instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type2).getRawType();
                if (rawType instanceof Class) {
                    return ClassUtils.isAssignable(cls, (Class) rawType);
                }
            } else if (cls.isArray() && (type2 instanceof GenericArrayType)) {
                return isAssignable(cls.getComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
        }
        if (type instanceof ParameterizedType) {
            if (type2 instanceof Class) {
                Type rawType2 = ((ParameterizedType) type).getRawType();
                if (rawType2 instanceof Class) {
                    return ClassUtils.isAssignable((Class) rawType2, (Class) type2);
                }
            } else if (type2 instanceof ParameterizedType) {
                return isAssignable((ParameterizedType) type, (ParameterizedType) type2);
            }
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (type2 instanceof Class) {
                Class cls2 = (Class) type2;
                if (cls2.isArray()) {
                    return isAssignable(genericComponentType, cls2.getComponentType());
                }
            } else if (type2 instanceof GenericArrayType) {
                return isAssignable(genericComponentType, ((GenericArrayType) type2).getGenericComponentType());
            }
        }
        if (type instanceof WildcardType) {
            return isAssignable((WildcardType) type, type2);
        }
        return false;
    }

    private static boolean isAssignable(WildcardType wildcardType, Type type) {
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length == 0) {
            upperBounds = new Type[]{Object.class};
        }
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length == 0) {
            lowerBounds = new Type[]{null};
        }
        if (type instanceof WildcardType) {
            WildcardType wildcardType2 = (WildcardType) type;
            Type[] upperBounds2 = wildcardType2.getUpperBounds();
            if (upperBounds2.length == 0) {
                upperBounds2 = new Type[]{Object.class};
            }
            Type[] lowerBounds2 = wildcardType2.getLowerBounds();
            if (lowerBounds2.length == 0) {
                lowerBounds2 = new Type[]{null};
            }
            for (Type type2 : upperBounds) {
                for (Type type3 : upperBounds2) {
                    if (!isAssignableBound(type2, type3)) {
                        return false;
                    }
                }
                for (Type type4 : lowerBounds2) {
                    if (!isAssignableBound(type2, type4)) {
                        return false;
                    }
                }
            }
            for (Type type5 : lowerBounds) {
                for (Type type6 : upperBounds2) {
                    if (!isAssignableBound(type6, type5)) {
                        return false;
                    }
                }
                for (Type type7 : lowerBounds2) {
                    if (!isAssignableBound(type7, type5)) {
                        return false;
                    }
                }
            }
        } else {
            for (Type type8 : upperBounds) {
                if (!isAssignableBound(type8, type)) {
                    return false;
                }
            }
            for (Type type9 : lowerBounds) {
                if (!isAssignableBound(type, type9)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAssignableBound(Type type, Type type2) {
        if (type2 == null) {
            return true;
        }
        if (type == null) {
            return false;
        }
        return isAssignable(type, type2);
    }
}
