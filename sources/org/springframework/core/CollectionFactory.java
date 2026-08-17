package org.springframework.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.springframework.util.ClassUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/* loaded from: classes2.dex */
public abstract class CollectionFactory {
    private static final Set<Class> approximableCollectionTypes = new HashSet(10);
    private static final Set<Class> approximableMapTypes = new HashSet(6);
    private static Class navigableMapClass;
    private static Class navigableSetClass;

    @Deprecated
    /* loaded from: classes2.dex */
    private static class JdkConcurrentHashMap extends ConcurrentHashMap implements ConcurrentMap {
        private JdkConcurrentHashMap(int i) {
            super(i);
        }
    }

    static {
        approximableCollectionTypes.add(Collection.class);
        approximableCollectionTypes.add(List.class);
        approximableCollectionTypes.add(Set.class);
        approximableCollectionTypes.add(SortedSet.class);
        approximableMapTypes.add(Map.class);
        approximableMapTypes.add(SortedMap.class);
        ClassLoader classLoader = CollectionFactory.class.getClassLoader();
        try {
            navigableSetClass = ClassUtils.forName("java.util.NavigableSet", classLoader);
            navigableMapClass = ClassUtils.forName("java.util.NavigableMap", classLoader);
            approximableCollectionTypes.add(navigableSetClass);
            approximableMapTypes.add(navigableMapClass);
        } catch (ClassNotFoundException unused) {
        }
        approximableCollectionTypes.add(ArrayList.class);
        approximableCollectionTypes.add(LinkedList.class);
        approximableCollectionTypes.add(HashSet.class);
        approximableCollectionTypes.add(LinkedHashSet.class);
        approximableCollectionTypes.add(TreeSet.class);
        approximableMapTypes.add(HashMap.class);
        approximableMapTypes.add(LinkedHashMap.class);
        approximableMapTypes.add(TreeMap.class);
    }

    public static Collection createApproximateCollection(Object obj, int i) {
        return obj instanceof LinkedList ? new LinkedList() : obj instanceof List ? new ArrayList(i) : obj instanceof SortedSet ? new TreeSet(((SortedSet) obj).comparator()) : new LinkedHashSet(i);
    }

    public static Map createApproximateMap(Object obj, int i) {
        return obj instanceof SortedMap ? new TreeMap(((SortedMap) obj).comparator()) : new LinkedHashMap(i);
    }

    public static Collection createCollection(Class<?> cls, int i) {
        if (!cls.isInterface()) {
            if (!Collection.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Unsupported Collection type: " + cls.getName());
            }
            try {
                return (Collection) cls.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not instantiate Collection type: " + cls.getName(), e);
            }
        }
        if (List.class.equals(cls)) {
            return new ArrayList(i);
        }
        if (SortedSet.class.equals(cls) || cls.equals(navigableSetClass)) {
            return new TreeSet();
        }
        if (Set.class.equals(cls) || Collection.class.equals(cls)) {
            return new LinkedHashSet(i);
        }
        throw new IllegalArgumentException("Unsupported Collection interface: " + cls.getName());
    }

    @Deprecated
    public static ConcurrentMap createConcurrentMap(int i) {
        return new JdkConcurrentHashMap(i);
    }

    @Deprecated
    public static Map createConcurrentMapIfPossible(int i) {
        return new ConcurrentHashMap(i);
    }

    @Deprecated
    public static <T> Set<T> createCopyOnWriteSet() {
        return new CopyOnWriteArraySet();
    }

    @Deprecated
    public static Map createIdentityMapIfPossible(int i) {
        return new IdentityHashMap(i);
    }

    @Deprecated
    public static Map createLinkedCaseInsensitiveMapIfPossible(int i) {
        return new LinkedCaseInsensitiveMap(i);
    }

    @Deprecated
    public static <K, V> Map<K, V> createLinkedMapIfPossible(int i) {
        return new LinkedHashMap(i);
    }

    @Deprecated
    public static <T> Set<T> createLinkedSetIfPossible(int i) {
        return new LinkedHashSet(i);
    }

    public static Map createMap(Class<?> cls, int i) {
        if (cls.isInterface()) {
            if (Map.class.equals(cls)) {
                return new LinkedHashMap(i);
            }
            if (SortedMap.class.equals(cls) || cls.equals(navigableMapClass)) {
                return new TreeMap();
            }
            if (MultiValueMap.class.equals(cls)) {
                return new LinkedMultiValueMap();
            }
            throw new IllegalArgumentException("Unsupported Map interface: " + cls.getName());
        }
        if (!Map.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported Map type: " + cls.getName());
        }
        try {
            return (Map) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not instantiate Map type: " + cls.getName(), e);
        }
    }

    public static boolean isApproximableCollectionType(Class<?> cls) {
        return cls != null && approximableCollectionTypes.contains(cls);
    }

    public static boolean isApproximableMapType(Class<?> cls) {
        return cls != null && approximableMapTypes.contains(cls);
    }
}
