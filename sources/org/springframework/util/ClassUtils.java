package org.springframework.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class ClassUtils {
    public static final String ARRAY_SUFFIX = "[]";
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    public static final String CLASS_FILE_SUFFIX = ".class";
    private static final char INNER_CLASS_SEPARATOR = '$';
    private static final String INTERNAL_ARRAY_PREFIX = "[";
    private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
    private static final char PACKAGE_SEPARATOR = '.';
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap(8);
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap(8);
    private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap(32);
    private static final Map<String, Class<?>> commonClassCache = new HashMap(32);

    static {
        primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
        primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
        primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
        primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
        primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
        primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
        primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
        primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
            registerCommonClasses(entry.getKey());
        }
        HashSet<Class<?>> hashSet = new HashSet(32);
        hashSet.addAll(primitiveWrapperTypeMap.values());
        hashSet.addAll(Arrays.asList(boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class, long[].class, short[].class));
        hashSet.add(Void.TYPE);
        for (Class<?> cls : hashSet) {
            primitiveTypeNameMap.put(cls.getName(), cls);
        }
        registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class, Float[].class, Integer[].class, Long[].class, Short[].class);
        registerCommonClasses(Number.class, Number[].class, String.class, String[].class, Object.class, Object[].class, Class.class, Class[].class);
        registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class, Error.class, StackTraceElement.class, StackTraceElement[].class);
    }

    public static String addResourcePathToPackagePath(Class<?> cls, String str) {
        Assert.notNull(str, "Resource name must not be null");
        if (str.startsWith("/")) {
            return classPackageAsResourcePath(cls) + str;
        }
        return classPackageAsResourcePath(cls) + "/" + str;
    }

    public static String classNamesToString(Collection<Class> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return ARRAY_SUFFIX;
        }
        StringBuilder sb = new StringBuilder(INTERNAL_ARRAY_PREFIX);
        Iterator<Class> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String classNamesToString(Class... clsArr) {
        return classNamesToString(Arrays.asList(clsArr));
    }

    public static String classPackageAsResourcePath(Class<?> cls) {
        String name;
        int lastIndexOf;
        return (cls == null || (lastIndexOf = (name = cls.getName()).lastIndexOf(46)) == -1) ? "" : name.substring(0, lastIndexOf).replace(PACKAGE_SEPARATOR, '/');
    }

    public static String convertClassNameToResourcePath(String str) {
        Assert.notNull(str, "Class name must not be null");
        return str.replace(PACKAGE_SEPARATOR, '/');
    }

    public static String convertResourcePathToClassName(String str) {
        Assert.notNull(str, "Resource path must not be null");
        return str.replace('/', PACKAGE_SEPARATOR);
    }

    public static Class<?> createCompositeInterface(Class<?>[] clsArr, ClassLoader classLoader) {
        Assert.notEmpty(clsArr, "Interfaces must not be empty");
        Assert.notNull(classLoader, "ClassLoader must not be null");
        return Proxy.getProxyClass(classLoader, clsArr);
    }

    public static Class<?> determineCommonAncestor(Class<?> cls, Class<?> cls2) {
        if (cls == null) {
            return cls2;
        }
        if (cls2 == null || cls.isAssignableFrom(cls2)) {
            return cls;
        }
        if (cls2.isAssignableFrom(cls)) {
            return cls2;
        }
        do {
            cls = cls.getSuperclass();
            if (cls == null || Object.class.equals(cls)) {
                return null;
            }
        } while (!cls.isAssignableFrom(cls2));
        return cls;
    }

    @Deprecated
    public static Class<?> forName(String str) throws ClassNotFoundException, LinkageError {
        return forName(str, getDefaultClassLoader());
    }

    public static Class<?> forName(String str, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        Assert.notNull(str, "Name must not be null");
        Class<?> resolvePrimitiveClassName = resolvePrimitiveClassName(str);
        if (resolvePrimitiveClassName == null) {
            resolvePrimitiveClassName = commonClassCache.get(str);
        }
        if (resolvePrimitiveClassName != null) {
            return resolvePrimitiveClassName;
        }
        if (str.endsWith(ARRAY_SUFFIX)) {
            return Array.newInstance(forName(str.substring(0, str.length() - ARRAY_SUFFIX.length()), classLoader), 0).getClass();
        }
        if (str.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && str.endsWith(";")) {
            return Array.newInstance(forName(str.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), str.length() - 1), classLoader), 0).getClass();
        }
        if (str.startsWith(INTERNAL_ARRAY_PREFIX)) {
            return Array.newInstance(forName(str.substring(INTERNAL_ARRAY_PREFIX.length()), classLoader), 0).getClass();
        }
        if (classLoader == null) {
            classLoader = getDefaultClassLoader();
        }
        try {
            return classLoader != null ? classLoader.loadClass(str) : Class.forName(str);
        } catch (ClassNotFoundException e) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                String str2 = str.substring(0, lastIndexOf) + '$' + str.substring(lastIndexOf + 1);
                try {
                    return classLoader != null ? classLoader.loadClass(str2) : Class.forName(str2);
                } catch (ClassNotFoundException unused) {
                    throw e;
                }
            }
            throw e;
        }
    }

    public static Class<?>[] getAllInterfaces(Object obj) {
        Assert.notNull(obj, "Instance must not be null");
        return getAllInterfacesForClass(obj.getClass());
    }

    public static Set<Class> getAllInterfacesAsSet(Object obj) {
        Assert.notNull(obj, "Instance must not be null");
        return getAllInterfacesForClassAsSet(obj.getClass());
    }

    public static Class<?>[] getAllInterfacesForClass(Class<?> cls) {
        return getAllInterfacesForClass(cls, null);
    }

    public static Class<?>[] getAllInterfacesForClass(Class<?> cls, ClassLoader classLoader) {
        Set<Class> allInterfacesForClassAsSet = getAllInterfacesForClassAsSet(cls, classLoader);
        return (Class[]) allInterfacesForClassAsSet.toArray(new Class[allInterfacesForClassAsSet.size()]);
    }

    public static Set<Class> getAllInterfacesForClassAsSet(Class cls) {
        return getAllInterfacesForClassAsSet(cls, null);
    }

    public static Set<Class> getAllInterfacesForClassAsSet(Class cls, ClassLoader classLoader) {
        Assert.notNull(cls, "Class must not be null");
        if (cls.isInterface() && isVisible(cls, classLoader)) {
            return Collections.singleton(cls);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (cls != null) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                linkedHashSet.addAll(getAllInterfacesForClassAsSet(cls2, classLoader));
            }
            cls = cls.getSuperclass();
        }
        return linkedHashSet;
    }

    public static String getClassFileName(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        String name = cls.getName();
        return name.substring(name.lastIndexOf(46) + 1) + CLASS_FILE_SUFFIX;
    }

    public static <T> Constructor<T> getConstructorIfAvailable(Class<T> cls, Class<?>... clsArr) {
        Assert.notNull(cls, "Class must not be null");
        try {
            return cls.getConstructor(clsArr);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable unused) {
            classLoader = null;
        }
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader classLoader2 = ClassUtils.class.getClassLoader();
        if (classLoader2 != null) {
            return classLoader2;
        }
        try {
            return ClassLoader.getSystemClassLoader();
        } catch (Throwable unused2) {
            return classLoader2;
        }
    }

    public static String getDescriptiveType(Object obj) {
        if (obj == null) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (!Proxy.isProxyClass(cls)) {
            return cls.isArray() ? getQualifiedNameForArray(cls) : cls.getName();
        }
        StringBuilder sb = new StringBuilder(cls.getName());
        sb.append(" implementing ");
        Class<?>[] interfaces = cls.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            sb.append(interfaces[i].getName());
            if (i < interfaces.length - 1) {
                sb.append(',');
            }
        }
        return sb.toString();
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        Assert.notNull(cls, "Class must not be null");
        Assert.notNull(str, "Method name must not be null");
        if (clsArr != null) {
            try {
                return cls.getMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Expected method not found: " + e);
            }
        }
        HashSet hashSet = new HashSet(1);
        for (Method method : cls.getMethods()) {
            if (str.equals(method.getName())) {
                hashSet.add(method);
            }
        }
        if (hashSet.size() == 1) {
            return (Method) hashSet.iterator().next();
        }
        if (hashSet.isEmpty()) {
            throw new IllegalStateException("Expected method not found: " + cls + "." + str);
        }
        throw new IllegalStateException("No unique method found: " + cls + "." + str);
    }

    public static int getMethodCountForName(Class<?> cls, String str) {
        Assert.notNull(cls, "Class must not be null");
        Assert.notNull(str, "Method name must not be null");
        int i = 0;
        for (Method method : cls.getDeclaredMethods()) {
            if (str.equals(method.getName())) {
                i++;
            }
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            i += getMethodCountForName(cls2, str);
        }
        return cls.getSuperclass() != null ? i + getMethodCountForName(cls.getSuperclass(), str) : i;
    }

    public static Method getMethodIfAvailable(Class<?> cls, String str, Class<?>... clsArr) {
        Assert.notNull(cls, "Class must not be null");
        Assert.notNull(str, "Method name must not be null");
        if (clsArr != null) {
            try {
                return cls.getMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }
        HashSet hashSet = new HashSet(1);
        for (Method method : cls.getMethods()) {
            if (str.equals(method.getName())) {
                hashSet.add(method);
            }
        }
        if (hashSet.size() == 1) {
            return (Method) hashSet.iterator().next();
        }
        return null;
    }

    public static Method getMostSpecificMethod(Method method, Class<?> cls) {
        if (method != null && isOverridable(method, cls) && cls != null && !cls.equals(method.getDeclaringClass())) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    try {
                        return cls.getMethod(method.getName(), method.getParameterTypes());
                    } catch (NoSuchMethodException unused) {
                        return method;
                    }
                }
                Method findMethod = ReflectionUtils.findMethod(cls, method.getName(), method.getParameterTypes());
                return findMethod != null ? findMethod : method;
            } catch (SecurityException unused2) {
            }
        }
        return method;
    }

    public static String getPackageName(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return getPackageName(cls.getName());
    }

    public static String getPackageName(String str) {
        Assert.notNull(str, "Class name must not be null");
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : "";
    }

    public static String getQualifiedMethodName(Method method) {
        Assert.notNull(method, "Method must not be null");
        return method.getDeclaringClass().getName() + "." + method.getName();
    }

    public static String getQualifiedName(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return cls.isArray() ? getQualifiedNameForArray(cls) : cls.getName();
    }

    private static String getQualifiedNameForArray(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        while (cls.isArray()) {
            cls = cls.getComponentType();
            sb.append(ARRAY_SUFFIX);
        }
        sb.insert(0, cls.getName());
        return sb.toString();
    }

    public static String getShortName(Class<?> cls) {
        return getShortName(getQualifiedName(cls));
    }

    public static String getShortName(String str) {
        Assert.hasLength(str, "Class name must not be empty");
        int lastIndexOf = str.lastIndexOf(46);
        int indexOf = str.indexOf(CGLIB_CLASS_SEPARATOR);
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return str.substring(lastIndexOf + 1, indexOf).replace('$', PACKAGE_SEPARATOR);
    }

    public static Method getStaticMethod(Class<?> cls, String str, Class<?>... clsArr) {
        Assert.notNull(cls, "Class must not be null");
        Assert.notNull(str, "Method name must not be null");
        try {
            Method method = cls.getMethod(str, clsArr);
            if (Modifier.isStatic(method.getModifiers())) {
                return method;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Class<?> getUserClass(Class<?> cls) {
        Class<? super Object> superclass;
        return (cls == null || !cls.getName().contains(CGLIB_CLASS_SEPARATOR) || (superclass = cls.getSuperclass()) == null || Object.class.equals(superclass)) ? cls : superclass;
    }

    public static Class<?> getUserClass(Object obj) {
        Assert.notNull(obj, "Instance must not be null");
        return getUserClass(obj.getClass());
    }

    public static boolean hasAtLeastOneMethodWithName(Class<?> cls, String str) {
        Assert.notNull(cls, "Class must not be null");
        Assert.notNull(str, "Method name must not be null");
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getName().equals(str)) {
                return true;
            }
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (hasAtLeastOneMethodWithName(cls2, str)) {
                return true;
            }
        }
        return cls.getSuperclass() != null && hasAtLeastOneMethodWithName(cls.getSuperclass(), str);
    }

    public static boolean hasConstructor(Class<?> cls, Class<?>... clsArr) {
        return getConstructorIfAvailable(cls, clsArr) != null;
    }

    public static boolean hasMethod(Class<?> cls, String str, Class<?>... clsArr) {
        return getMethodIfAvailable(cls, str, clsArr) != null;
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2) {
        Assert.notNull(cls, "Left-hand side type must not be null");
        Assert.notNull(cls2, "Right-hand side type must not be null");
        if (cls.isAssignableFrom(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            Class<?> cls3 = primitiveWrapperTypeMap.get(cls2);
            return cls3 != null && cls.equals(cls3);
        }
        Class<?> cls4 = primitiveTypeToWrapperMap.get(cls2);
        return cls4 != null && cls.isAssignableFrom(cls4);
    }

    public static boolean isAssignableValue(Class<?> cls, Object obj) {
        Assert.notNull(cls, "Type must not be null");
        return obj != null ? isAssignable(cls, obj.getClass()) : !cls.isPrimitive();
    }

    public static boolean isCacheSafe(Class<?> cls, ClassLoader classLoader) {
        Assert.notNull(cls, "Class must not be null");
        try {
            ClassLoader classLoader2 = cls.getClassLoader();
            if (classLoader2 == null || classLoader == classLoader2) {
                return true;
            }
            while (classLoader != null) {
                classLoader = classLoader.getParent();
                if (classLoader == classLoader2) {
                    return true;
                }
            }
            return false;
        } catch (SecurityException unused) {
            return true;
        }
    }

    public static boolean isCglibProxy(Object obj) {
        return isCglibProxyClass(obj.getClass());
    }

    public static boolean isCglibProxyClass(Class<?> cls) {
        return cls != null && isCglibProxyClassName(cls.getName());
    }

    public static boolean isCglibProxyClassName(String str) {
        return str != null && str.contains(CGLIB_CLASS_SEPARATOR);
    }

    private static boolean isOverridable(Method method, Class<?> cls) {
        if (Modifier.isPrivate(method.getModifiers())) {
            return false;
        }
        if (Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) {
            return true;
        }
        return getPackageName(method.getDeclaringClass()).equals(getPackageName(cls));
    }

    @Deprecated
    public static boolean isPresent(String str) {
        return isPresent(str, getDefaultClassLoader());
    }

    public static boolean isPresent(String str, ClassLoader classLoader) {
        try {
            forName(str, classLoader);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isPrimitiveArray(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return cls.isArray() && cls.getComponentType().isPrimitive();
    }

    public static boolean isPrimitiveOrWrapper(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return cls.isPrimitive() || isPrimitiveWrapper(cls);
    }

    public static boolean isPrimitiveWrapper(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return primitiveWrapperTypeMap.containsKey(cls);
    }

    public static boolean isPrimitiveWrapperArray(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return cls.isArray() && isPrimitiveWrapper(cls.getComponentType());
    }

    public static boolean isVisible(Class<?> cls, ClassLoader classLoader) {
        if (classLoader == null) {
            return true;
        }
        try {
            return cls == classLoader.loadClass(cls.getName());
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean matchesTypeName(Class<?> cls, String str) {
        return str != null && (str.equals(cls.getName()) || str.equals(cls.getSimpleName()) || (cls.isArray() && str.equals(getQualifiedNameForArray(cls))));
    }

    public static ClassLoader overrideThreadContextClassLoader(ClassLoader classLoader) {
        Thread currentThread = Thread.currentThread();
        ClassLoader contextClassLoader = currentThread.getContextClassLoader();
        if (classLoader == null || classLoader.equals(contextClassLoader)) {
            return null;
        }
        currentThread.setContextClassLoader(classLoader);
        return contextClassLoader;
    }

    private static void registerCommonClasses(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            commonClassCache.put(cls.getName(), cls);
        }
    }

    public static Class<?> resolveClassName(String str, ClassLoader classLoader) throws IllegalArgumentException {
        try {
            return forName(str, classLoader);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Cannot find class [" + str + "]", e);
        } catch (LinkageError e2) {
            throw new IllegalArgumentException("Error loading class [" + str + "]: problem with class file or dependent class.", e2);
        }
    }

    public static Class<?> resolvePrimitiveClassName(String str) {
        if (str == null || str.length() > 8) {
            return null;
        }
        return primitiveTypeNameMap.get(str);
    }

    public static Class<?> resolvePrimitiveIfNecessary(Class<?> cls) {
        Assert.notNull(cls, "Class must not be null");
        return (!cls.isPrimitive() || cls == Void.TYPE) ? cls : primitiveTypeToWrapperMap.get(cls);
    }

    public static Class<?>[] toClassArray(Collection<Class<?>> collection) {
        if (collection == null) {
            return null;
        }
        return (Class[]) collection.toArray(new Class[collection.size()]);
    }
}
