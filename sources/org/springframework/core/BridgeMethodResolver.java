package org.springframework.core;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/* loaded from: classes2.dex */
public abstract class BridgeMethodResolver {
    public static Method findBridgedMethod(Method method) {
        if (method == null || !method.isBridge()) {
            return method;
        }
        ArrayList arrayList = new ArrayList();
        for (Method method2 : ReflectionUtils.getAllDeclaredMethods(method.getDeclaringClass())) {
            if (isBridgedCandidateFor(method2, method)) {
                arrayList.add(method2);
            }
        }
        if (arrayList.size() == 1) {
            return (Method) arrayList.get(0);
        }
        Method searchCandidates = searchCandidates(arrayList, method);
        return searchCandidates != null ? searchCandidates : method;
    }

    private static Method findGenericDeclaration(Method method) {
        for (Class<? super Object> superclass = method.getDeclaringClass().getSuperclass(); superclass != null && !Object.class.equals(superclass); superclass = superclass.getSuperclass()) {
            Method searchForMatch = searchForMatch(superclass, method);
            if (searchForMatch != null && !searchForMatch.isBridge()) {
                return searchForMatch;
            }
        }
        for (Class<?> cls : ClassUtils.getAllInterfacesForClass(method.getDeclaringClass())) {
            Method searchForMatch2 = searchForMatch(cls, method);
            if (searchForMatch2 != null && !searchForMatch2.isBridge()) {
                return searchForMatch2;
            }
        }
        return null;
    }

    static boolean isBridgeMethodFor(Method method, Method method2, Map<TypeVariable, Type> map) {
        if (isResolvedTypeMatch(method2, method, map)) {
            return true;
        }
        Method findGenericDeclaration = findGenericDeclaration(method);
        return findGenericDeclaration != null && isResolvedTypeMatch(findGenericDeclaration, method2, map);
    }

    private static boolean isBridgedCandidateFor(Method method, Method method2) {
        return !method.isBridge() && !method.equals(method2) && method.getName().equals(method2.getName()) && method.getParameterTypes().length == method2.getParameterTypes().length;
    }

    private static boolean isResolvedTypeMatch(Method method, Method method2, Map<TypeVariable, Type> map) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        Class<?>[] parameterTypes = method2.getParameterTypes();
        if (genericParameterTypes.length != parameterTypes.length) {
            return false;
        }
        for (int i = 0; i < genericParameterTypes.length; i++) {
            Type type = genericParameterTypes[i];
            Class<?> cls = parameterTypes[i];
            if (cls.isArray()) {
                Type rawType = GenericTypeResolver.getRawType(type, map);
                if (rawType instanceof GenericArrayType) {
                    return cls.getComponentType().equals(GenericTypeResolver.resolveType(((GenericArrayType) rawType).getGenericComponentType(), map));
                }
            }
            if (!cls.equals(GenericTypeResolver.resolveType(type, map))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVisibilityBridgeMethodPair(Method method, Method method2) {
        if (method == method2) {
            return true;
        }
        return Arrays.equals(method.getParameterTypes(), method2.getParameterTypes()) && method.getReturnType().equals(method2.getReturnType());
    }

    private static Method searchCandidates(List<Method> list, Method method) {
        if (list.isEmpty()) {
            return null;
        }
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(method.getDeclaringClass());
        Iterator<Method> it = list.iterator();
        Method method2 = null;
        boolean z = true;
        while (true) {
            boolean z2 = false;
            if (!it.hasNext()) {
                if (z) {
                    return list.get(0);
                }
                return null;
            }
            Method next = it.next();
            if (isBridgeMethodFor(method, next, typeVariableMap)) {
                return next;
            }
            if (method2 != null) {
                if (z && Arrays.equals(next.getGenericParameterTypes(), method2.getGenericParameterTypes())) {
                    z2 = true;
                }
                z = z2;
            }
            method2 = next;
        }
    }

    private static Method searchForMatch(Class cls, Method method) {
        return ReflectionUtils.findMethod(cls, method.getName(), method.getParameterTypes());
    }
}
