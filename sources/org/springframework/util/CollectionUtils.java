package org.springframework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class CollectionUtils {

    /* loaded from: classes2.dex */
    private static class EnumerationIterator<E> implements Iterator<E> {
        private Enumeration<E> enumeration;

        public EnumerationIterator(Enumeration<E> enumeration) {
            this.enumeration = enumeration;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        @Override // java.util.Iterator
        public E next() {
            return this.enumeration.nextElement();
        }

        @Override // java.util.Iterator
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MultiValueMapAdapter<K, V> implements MultiValueMap<K, V>, Serializable {
        private final Map<K, List<V>> map;

        public MultiValueMapAdapter(Map<K, List<V>> map) {
            Assert.notNull(map, "'map' must not be null");
            this.map = map;
        }

        @Override // org.springframework.util.MultiValueMap
        public void add(K k, V v) {
            List<V> list = this.map.get(k);
            if (list == null) {
                list = new LinkedList<>();
                this.map.put(k, list);
            }
            list.add(v);
        }

        @Override // java.util.Map
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, List<V>>> entrySet() {
            return this.map.entrySet();
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return this.map.equals(obj);
        }

        @Override // java.util.Map
        public List<V> get(Object obj) {
            return this.map.get(obj);
        }

        @Override // org.springframework.util.MultiValueMap
        public V getFirst(K k) {
            List<V> list = this.map.get(k);
            if (list != null) {
                return list.get(0);
            }
            return null;
        }

        @Override // java.util.Map
        public int hashCode() {
            return this.map.hashCode();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            return this.map.keySet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Map
        public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
            return put((MultiValueMapAdapter<K, V>) obj, (List) obj2);
        }

        public List<V> put(K k, List<V> list) {
            return this.map.put(k, list);
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends List<V>> map) {
            this.map.putAll(map);
        }

        @Override // java.util.Map
        public List<V> remove(Object obj) {
            return this.map.remove(obj);
        }

        @Override // org.springframework.util.MultiValueMap
        public void set(K k, V v) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(v);
            this.map.put(k, linkedList);
        }

        @Override // org.springframework.util.MultiValueMap
        public void setAll(Map<K, V> map) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                set(entry.getKey(), entry.getValue());
            }
        }

        @Override // java.util.Map
        public int size() {
            return this.map.size();
        }

        @Override // org.springframework.util.MultiValueMap
        public Map<K, V> toSingleValueMap() {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.map.size());
            for (Map.Entry<K, List<V>> entry : this.map.entrySet()) {
                linkedHashMap.put(entry.getKey(), entry.getValue().get(0));
            }
            return linkedHashMap;
        }

        public String toString() {
            return this.map.toString();
        }

        @Override // java.util.Map
        public Collection<List<V>> values() {
            return this.map.values();
        }
    }

    public static List arrayToList(Object obj) {
        return Arrays.asList(ObjectUtils.toObjectArray(obj));
    }

    public static boolean contains(Enumeration enumeration, Object obj) {
        if (enumeration == null) {
            return false;
        }
        while (enumeration.hasMoreElements()) {
            if (ObjectUtils.nullSafeEquals(enumeration.nextElement(), obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(Iterator it, Object obj) {
        if (it == null) {
            return false;
        }
        while (it.hasNext()) {
            if (ObjectUtils.nullSafeEquals(it.next(), obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAny(Collection collection, Collection collection2) {
        if (isEmpty(collection) || isEmpty(collection2)) {
            return false;
        }
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsInstance(Collection collection, Object obj) {
        if (collection == null) {
            return false;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == obj) {
                return true;
            }
        }
        return false;
    }

    public static Class<?> findCommonElementType(Collection collection) {
        if (isEmpty(collection)) {
            return null;
        }
        Class<?> cls = null;
        for (Object obj : collection) {
            if (obj != null) {
                if (cls == null) {
                    cls = obj.getClass();
                } else if (cls != obj.getClass()) {
                    return null;
                }
            }
        }
        return cls;
    }

    public static Object findFirstMatch(Collection collection, Collection collection2) {
        if (isEmpty(collection) || isEmpty(collection2)) {
            return null;
        }
        for (Object obj : collection2) {
            if (collection.contains(obj)) {
                return obj;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T findValueOfType(Collection<?> collection, Class<T> cls) {
        if (isEmpty(collection)) {
            return null;
        }
        T t = null;
        for (Object obj : collection) {
            if (cls == null || cls.isInstance(obj)) {
                if (t != null) {
                    return null;
                }
                t = obj;
            }
        }
        return t;
    }

    public static Object findValueOfType(Collection<?> collection, Class<?>[] clsArr) {
        if (isEmpty(collection) || ObjectUtils.isEmpty(clsArr)) {
            return null;
        }
        for (Class<?> cls : clsArr) {
            Object findValueOfType = findValueOfType(collection, cls);
            if (findValueOfType != null) {
                return findValueOfType;
            }
        }
        return null;
    }

    public static boolean hasUniqueObject(Collection collection) {
        if (isEmpty(collection)) {
            return false;
        }
        Object obj = null;
        boolean z = false;
        for (Object obj2 : collection) {
            if (!z) {
                obj = obj2;
                z = true;
            } else if (obj != obj2) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static void mergeArrayIntoCollection(Object obj, Collection collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection must not be null");
        }
        for (Object obj2 : ObjectUtils.toObjectArray(obj)) {
            collection.add(obj2);
        }
    }

    public static void mergePropertiesIntoMap(Properties properties, Map map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }
        if (properties != null) {
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String str = (String) propertyNames.nextElement();
                Object property = properties.getProperty(str);
                if (property == null) {
                    property = properties.get(str);
                }
                map.put(str, property);
            }
        }
    }

    public static <A, E extends A> A[] toArray(Enumeration<E> enumeration, A[] aArr) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return (A[]) arrayList.toArray(aArr);
    }

    public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
        return new EnumerationIterator(enumeration);
    }

    public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map<K, List<V>> map) {
        return new MultiValueMapAdapter(map);
    }

    public static <K, V> MultiValueMap<K, V> unmodifiableMultiValueMap(MultiValueMap<? extends K, ? extends V> multiValueMap) {
        Assert.notNull(multiValueMap, "'map' must not be null");
        LinkedHashMap linkedHashMap = new LinkedHashMap(multiValueMap.size());
        for (Map.Entry<? extends K, ? extends V> entry : multiValueMap.entrySet()) {
            linkedHashMap.put(entry.getKey(), Collections.unmodifiableList((List) entry.getValue()));
        }
        return toMultiValueMap(Collections.unmodifiableMap(linkedHashMap));
    }
}
