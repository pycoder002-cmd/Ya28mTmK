package org.springframework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class ReflectionUtils {
    private static final Map<Class<?>, Method[]> declaredMethodsCache = new ConcurrentReferenceHashMap(256);
    public static FieldFilter COPYABLE_FIELDS = new FieldFilter() { // from class: org.springframework.util.ReflectionUtils.4
        @Override // org.springframework.util.ReflectionUtils.FieldFilter
        public boolean matches(Field field) {
            return (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) ? false : true;
        }
    };
    public static MethodFilter NON_BRIDGED_METHODS = new MethodFilter() { // from class: org.springframework.util.ReflectionUtils.5
        @Override // org.springframework.util.ReflectionUtils.MethodFilter
        public boolean matches(Method method) {
            return !method.isBridge();
        }
    };
    public static MethodFilter USER_DECLARED_METHODS = new MethodFilter() { // from class: org.springframework.util.ReflectionUtils.6
        @Override // org.springframework.util.ReflectionUtils.MethodFilter
        public boolean matches(Method method) {
            return (method.isBridge() || method.getDeclaringClass() == Object.class) ? false : true;
        }
    };

    /* loaded from: classes2.dex */
    public interface FieldCallback {
        void doWith(Field field) throws IllegalArgumentException, IllegalAccessException;
    }

    /* loaded from: classes2.dex */
    public interface FieldFilter {
        boolean matches(Field field);
    }

    /* loaded from: classes2.dex */
    public interface MethodCallback {
        void doWith(Method method) throws IllegalArgumentException, IllegalAccessException;
    }

    /* loaded from: classes2.dex */
    public interface MethodFilter {
        boolean matches(Method method);
    }

    public static boolean declaresException(Method method, Class<?> cls) {
        Assert.notNull(method, "Method must not be null");
        for (Class<?> cls2 : method.getExceptionTypes()) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public static void doWithFields(Class<?> cls, FieldCallback fieldCallback) throws IllegalArgumentException {
        doWithFields(cls, fieldCallback, null);
    }

    public static void doWithFields(Class<?> cls, FieldCallback fieldCallback, FieldFilter fieldFilter) throws IllegalArgumentException {
        do {
            for (Field field : cls.getDeclaredFields()) {
                if (fieldFilter == null || fieldFilter.matches(field)) {
                    try {
                        fieldCallback.doWith(field);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Shouldn't be illegal to access field '" + field.getName() + "': " + e);
                    }
                }
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                return;
            }
        } while (cls != Object.class);
    }

    public static void doWithMethods(Class<?> cls, MethodCallback methodCallback) throws IllegalArgumentException {
        doWithMethods(cls, methodCallback, null);
    }

    public static void doWithMethods(Class<?> cls, MethodCallback methodCallback, MethodFilter methodFilter) throws IllegalArgumentException {
        for (Method method : getDeclaredMethods(cls)) {
            if (methodFilter == null || methodFilter.matches(method)) {
                try {
                    methodCallback.doWith(method);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Shouldn't be illegal to access method '" + method.getName() + "': " + e);
                }
            }
        }
        if (cls.getSuperclass() != null) {
            doWithMethods(cls.getSuperclass(), methodCallback, methodFilter);
            return;
        }
        if (cls.isInterface()) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                doWithMethods(cls2, methodCallback, methodFilter);
            }
        }
    }

    public static Field findField(Class<?> cls, String str) {
        return findField(cls, str, null);
    }

    public static Field findField(Class<?> cls, String str, Class<?> cls2) {
        Assert.notNull(cls, "Class must not be null");
        Assert.isTrue((str == null && cls2 == null) ? false : true, "Either name or type of the field must be specified");
        while (!Object.class.equals(cls) && cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                if ((str == null || str.equals(field.getName())) && (cls2 == null || cls2.equals(field.getType()))) {
                    return field;
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static Method findMethod(Class<?> cls, String str) {
        return findMethod(cls, str, new Class[0]);
    }

    public static Method findMethod(Class<?> cls, String str, Class<?>... clsArr) {
        Assert.notNull(cls, "Class must not be null");
        Assert.notNull(str, "Method name must not be null");
        while (cls != null) {
            for (Method method : cls.isInterface() ? cls.getMethods() : getDeclaredMethods(cls)) {
                if (str.equals(method.getName()) && (clsArr == null || Arrays.equals(clsArr, method.getParameterTypes()))) {
                    return method;
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static Method[] getAllDeclaredMethods(Class<?> cls) throws IllegalArgumentException {
        final ArrayList arrayList = new ArrayList(32);
        doWithMethods(cls, new MethodCallback() { // from class: org.springframework.util.ReflectionUtils.1
            @Override // org.springframework.util.ReflectionUtils.MethodCallback
            public void doWith(Method method) {
                arrayList.add(method);
            }
        });
        return (Method[]) arrayList.toArray(new Method[arrayList.size()]);
    }

    private static Method[] getDeclaredMethods(Class<?> cls) {
        Method[] methodArr = declaredMethodsCache.get(cls);
        if (methodArr != null) {
            return methodArr;
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        declaredMethodsCache.put(cls, declaredMethods);
        return declaredMethods;
    }

    public static Object getField(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            handleReflectionException(e);
            throw new IllegalStateException("Unexpected reflection exception - " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Method[] getUniqueDeclaredMethods(Class<?> cls) throws IllegalArgumentException {
        final ArrayList arrayList = new ArrayList(32);
        doWithMethods(cls, new MethodCallback() { // from class: org.springframework.util.ReflectionUtils.2
            @Override // org.springframework.util.ReflectionUtils.MethodCallback
            public void doWith(Method method) {
                boolean z;
                Method method2;
                Iterator it = arrayList.iterator();
                while (true) {
                    z = false;
                    method2 = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    Method method3 = (Method) it.next();
                    if (method.getName().equals(method3.getName()) && Arrays.equals(method.getParameterTypes(), method3.getParameterTypes())) {
                        if (method3.getReturnType() == method.getReturnType() || !method3.getReturnType().isAssignableFrom(method.getReturnType())) {
                            z = true;
                        } else {
                            method2 = method3;
                        }
                    }
                }
                if (method2 != null) {
                    arrayList.remove(method2);
                }
                if (z) {
                    return;
                }
                arrayList.add(method);
            }
        });
        return (Method[]) arrayList.toArray(new Method[arrayList.size()]);
    }

    public static void handleInvocationTargetException(InvocationTargetException invocationTargetException) {
        rethrowRuntimeException(invocationTargetException.getTargetException());
    }

    public static void handleReflectionException(Exception exc) {
        if (exc instanceof NoSuchMethodException) {
            throw new IllegalStateException("Method not found: " + exc.getMessage());
        }
        if (exc instanceof IllegalAccessException) {
            throw new IllegalStateException("Could not access method: " + exc.getMessage());
        }
        if (exc instanceof InvocationTargetException) {
            handleInvocationTargetException((InvocationTargetException) exc);
        }
        if (!(exc instanceof RuntimeException)) {
            throw new UndeclaredThrowableException(exc);
        }
        throw ((RuntimeException) exc);
    }

    public static Object invokeJdbcMethod(Method method, Object obj) throws SQLException {
        return invokeJdbcMethod(method, obj, new Object[0]);
    }

    public static Object invokeJdbcMethod(Method method, Object obj, Object... objArr) throws SQLException {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            handleReflectionException(e);
            throw new IllegalStateException("Should never get here");
        } catch (InvocationTargetException e2) {
            if (e2.getTargetException() instanceof SQLException) {
                throw ((SQLException) e2.getTargetException());
            }
            handleInvocationTargetException(e2);
            throw new IllegalStateException("Should never get here");
        }
    }

    public static Object invokeMethod(Method method, Object obj) {
        return invokeMethod(method, obj, new Object[0]);
    }

    public static Object invokeMethod(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            handleReflectionException(e);
            throw new IllegalStateException("Should never get here");
        }
    }

    public static boolean isEqualsMethod(Method method) {
        if (method == null || !method.getName().equals("equals")) {
            return false;
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        return parameterTypes.length == 1 && parameterTypes[0] == Object.class;
    }

    public static boolean isHashCodeMethod(Method method) {
        return method != null && method.getName().equals("hashCode") && method.getParameterTypes().length == 0;
    }

    public static boolean isObjectMethod(Method method) {
        if (method == null) {
            return false;
        }
        try {
            Object.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isPublicStaticFinal(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
    }

    public static boolean isToStringMethod(Method method) {
        return method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0;
    }

    public static void makeAccessible(Constructor<?> constructor) {
        if ((Modifier.isPublic(constructor.getModifiers()) && Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) || constructor.isAccessible()) {
            return;
        }
        constructor.setAccessible(true);
    }

    public static void makeAccessible(Field field) {
        if ((Modifier.isPublic(field.getModifiers()) && Modifier.isPublic(field.getDeclaringClass().getModifiers()) && !Modifier.isFinal(field.getModifiers())) || field.isAccessible()) {
            return;
        }
        field.setAccessible(true);
    }

    public static void makeAccessible(Method method) {
        if ((Modifier.isPublic(method.getModifiers()) && Modifier.isPublic(method.getDeclaringClass().getModifiers())) || method.isAccessible()) {
            return;
        }
        method.setAccessible(true);
    }

    public static void rethrowException(Throwable th) throws Exception {
        if (th instanceof Exception) {
            throw ((Exception) th);
        }
        if (!(th instanceof Error)) {
            throw new UndeclaredThrowableException(th);
        }
        throw ((Error) th);
    }

    public static void rethrowRuntimeException(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (!(th instanceof Error)) {
            throw new UndeclaredThrowableException(th);
        }
        throw ((Error) th);
    }

    public static void setField(Field field, Object obj, Object obj2) {
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            handleReflectionException(e);
            throw new IllegalStateException("Unexpected reflection exception - " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void shallowCopyFieldState(final Object obj, final Object obj2) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Source for field copy cannot be null");
        }
        if (obj2 == null) {
            throw new IllegalArgumentException("Destination for field copy cannot be null");
        }
        if (obj.getClass().isAssignableFrom(obj2.getClass())) {
            doWithFields(obj.getClass(), new FieldCallback() { // from class: org.springframework.util.ReflectionUtils.3
                @Override // org.springframework.util.ReflectionUtils.FieldCallback
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    field.set(obj2, field.get(obj));
                }
            }, COPYABLE_FIELDS);
            return;
        }
        throw new IllegalArgumentException("Destination class [" + obj2.getClass().getName() + "] must be same or subclass as source class [" + obj.getClass().getName() + "]");
    }
}
