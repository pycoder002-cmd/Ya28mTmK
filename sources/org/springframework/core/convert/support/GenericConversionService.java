package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public class GenericConversionService implements ConfigurableConversionService {
    private static final GenericConverter NO_OP_CONVERTER = new NoOpConverter("NO_OP");
    private static final GenericConverter NO_MATCH = new NoOpConverter("NO_MATCH");
    private final Converters converters = new Converters();
    private final Map<ConverterCacheKey, GenericConverter> converterCache = new ConcurrentHashMap(64);

    /* loaded from: classes2.dex */
    private final class ConverterAdapter implements ConditionalGenericConverter {
        private final Converter<Object, Object> converter;
        private final GenericConverter.ConvertiblePair typeInfo;

        public ConverterAdapter(Converter<?, ?> converter, GenericConverter.ConvertiblePair convertiblePair) {
            this.converter = converter;
            this.typeInfo = convertiblePair;
        }

        @Override // org.springframework.core.convert.converter.GenericConverter
        public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            return obj == null ? GenericConversionService.this.convertNullSource(typeDescriptor, typeDescriptor2) : this.converter.convert(obj);
        }

        @Override // org.springframework.core.convert.converter.GenericConverter
        public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(this.typeInfo);
        }

        @Override // org.springframework.core.convert.converter.ConditionalConverter
        public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            if (!this.typeInfo.getTargetType().equals(typeDescriptor2.getObjectType())) {
                return false;
            }
            if (this.converter instanceof ConditionalConverter) {
                return ((ConditionalConverter) this.converter).matches(typeDescriptor, typeDescriptor2);
            }
            return true;
        }

        public String toString() {
            return this.typeInfo + " : " + this.converter;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ConverterCacheKey {
        private final TypeDescriptor sourceType;
        private final TypeDescriptor targetType;

        public ConverterCacheKey(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            this.sourceType = typeDescriptor;
            this.targetType = typeDescriptor2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConverterCacheKey)) {
                return false;
            }
            ConverterCacheKey converterCacheKey = (ConverterCacheKey) obj;
            return ObjectUtils.nullSafeEquals(this.sourceType, converterCacheKey.sourceType) && ObjectUtils.nullSafeEquals(this.targetType, converterCacheKey.targetType);
        }

        public int hashCode() {
            return (ObjectUtils.nullSafeHashCode(this.sourceType) * 29) + ObjectUtils.nullSafeHashCode(this.targetType);
        }

        public String toString() {
            return "ConverterCacheKey [sourceType = " + this.sourceType + ", targetType = " + this.targetType + "]";
        }
    }

    /* loaded from: classes2.dex */
    private final class ConverterFactoryAdapter implements ConditionalGenericConverter {
        private final ConverterFactory<Object, Object> converterFactory;
        private final GenericConverter.ConvertiblePair typeInfo;

        public ConverterFactoryAdapter(ConverterFactory<?, ?> converterFactory, GenericConverter.ConvertiblePair convertiblePair) {
            this.converterFactory = converterFactory;
            this.typeInfo = convertiblePair;
        }

        @Override // org.springframework.core.convert.converter.GenericConverter
        public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            return obj == null ? GenericConversionService.this.convertNullSource(typeDescriptor, typeDescriptor2) : this.converterFactory.getConverter(typeDescriptor2.getObjectType()).convert(obj);
        }

        @Override // org.springframework.core.convert.converter.GenericConverter
        public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(this.typeInfo);
        }

        @Override // org.springframework.core.convert.converter.ConditionalConverter
        public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            boolean matches = this.converterFactory instanceof ConditionalConverter ? ((ConditionalConverter) this.converterFactory).matches(typeDescriptor, typeDescriptor2) : true;
            if (!matches) {
                return matches;
            }
            Object converter = this.converterFactory.getConverter(typeDescriptor2.getType());
            return converter instanceof ConditionalConverter ? ((ConditionalConverter) converter).matches(typeDescriptor, typeDescriptor2) : matches;
        }

        public String toString() {
            return this.typeInfo + " : " + this.converterFactory;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Converters {
        private final Map<GenericConverter.ConvertiblePair, ConvertersForPair> converters;
        private final Set<GenericConverter> globalConverters;

        private Converters() {
            this.globalConverters = new LinkedHashSet();
            this.converters = new LinkedHashMap(36);
        }

        private void addToClassHierarchy(int i, Class<?> cls, boolean z, List<Class<?>> list, Set<Class<?>> set) {
            if (z) {
                cls = Array.newInstance(cls, 0).getClass();
            }
            if (set.add(cls)) {
                list.add(i, cls);
            }
        }

        private List<Class<?>> getClassHierarchy(Class<?> cls) {
            ArrayList arrayList = new ArrayList(20);
            HashSet hashSet = new HashSet(20);
            addToClassHierarchy(0, ClassUtils.resolvePrimitiveIfNecessary(cls), false, arrayList, hashSet);
            boolean isArray = cls.isArray();
            for (int i = 0; i < arrayList.size(); i++) {
                Class<?> cls2 = arrayList.get(i);
                Class<?> componentType = isArray ? cls2.getComponentType() : ClassUtils.resolvePrimitiveIfNecessary(cls2);
                Class<? super Object> superclass = componentType.getSuperclass();
                if (componentType.getSuperclass() != null && superclass != Object.class) {
                    addToClassHierarchy(i + 1, componentType.getSuperclass(), isArray, arrayList, hashSet);
                }
                for (Class<?> cls3 : componentType.getInterfaces()) {
                    addToClassHierarchy(arrayList.size(), cls3, isArray, arrayList, hashSet);
                }
            }
            addToClassHierarchy(arrayList.size(), Object.class, isArray, arrayList, hashSet);
            addToClassHierarchy(arrayList.size(), Object.class, false, arrayList, hashSet);
            return arrayList;
        }

        private List<String> getConverterStrings() {
            ArrayList arrayList = new ArrayList();
            Iterator<ConvertersForPair> it = this.converters.values().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toString());
            }
            Collections.sort(arrayList);
            return arrayList;
        }

        private ConvertersForPair getMatchableConverters(GenericConverter.ConvertiblePair convertiblePair) {
            ConvertersForPair convertersForPair = this.converters.get(convertiblePair);
            if (convertersForPair != null) {
                return convertersForPair;
            }
            ConvertersForPair convertersForPair2 = new ConvertersForPair();
            this.converters.put(convertiblePair, convertersForPair2);
            return convertersForPair2;
        }

        private GenericConverter getRegisteredConverter(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2, GenericConverter.ConvertiblePair convertiblePair) {
            GenericConverter converter;
            ConvertersForPair convertersForPair = this.converters.get(convertiblePair);
            if (convertersForPair != null && (converter = convertersForPair.getConverter(typeDescriptor, typeDescriptor2)) != null) {
                return converter;
            }
            for (GenericConverter genericConverter : this.globalConverters) {
                if (((ConditionalConverter) genericConverter).matches(typeDescriptor, typeDescriptor2)) {
                    return genericConverter;
                }
            }
            return null;
        }

        public void add(GenericConverter genericConverter) {
            Set<GenericConverter.ConvertiblePair> convertibleTypes = genericConverter.getConvertibleTypes();
            if (convertibleTypes == null) {
                Assert.state(genericConverter instanceof ConditionalConverter, "Only conditional converters may return null convertible types");
                this.globalConverters.add(genericConverter);
            } else {
                Iterator<GenericConverter.ConvertiblePair> it = convertibleTypes.iterator();
                while (it.hasNext()) {
                    getMatchableConverters(it.next()).add(genericConverter);
                }
            }
        }

        public GenericConverter find(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            List<Class<?>> classHierarchy = getClassHierarchy(typeDescriptor.getType());
            List<Class<?>> classHierarchy2 = getClassHierarchy(typeDescriptor2.getType());
            for (Class<?> cls : classHierarchy) {
                Iterator<Class<?>> it = classHierarchy2.iterator();
                while (it.hasNext()) {
                    GenericConverter registeredConverter = getRegisteredConverter(typeDescriptor, typeDescriptor2, new GenericConverter.ConvertiblePair(cls, it.next()));
                    if (registeredConverter != null) {
                        return registeredConverter;
                    }
                }
            }
            return null;
        }

        public void remove(Class<?> cls, Class<?> cls2) {
            this.converters.remove(new GenericConverter.ConvertiblePair(cls, cls2));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ConversionService converters =\n");
            for (String str : getConverterStrings()) {
                sb.append('\t');
                sb.append(str);
                sb.append('\n');
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ConvertersForPair {
        private final LinkedList<GenericConverter> converters;

        private ConvertersForPair() {
            this.converters = new LinkedList<>();
        }

        public void add(GenericConverter genericConverter) {
            this.converters.addFirst(genericConverter);
        }

        public GenericConverter getConverter(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            Iterator<GenericConverter> it = this.converters.iterator();
            while (it.hasNext()) {
                GenericConverter next = it.next();
                if (!(next instanceof ConditionalGenericConverter) || ((ConditionalGenericConverter) next).matches(typeDescriptor, typeDescriptor2)) {
                    return next;
                }
            }
            return null;
        }

        public String toString() {
            return StringUtils.collectionToCommaDelimitedString(this.converters);
        }
    }

    /* loaded from: classes2.dex */
    private static class NoOpConverter implements GenericConverter {
        private final String name;

        public NoOpConverter(String str) {
            this.name = str;
        }

        @Override // org.springframework.core.convert.converter.GenericConverter
        public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
            return obj;
        }

        @Override // org.springframework.core.convert.converter.GenericConverter
        public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
            return null;
        }

        public String toString() {
            return this.name;
        }
    }

    private void assertNotPrimitiveTargetType(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (typeDescriptor2.isPrimitive()) {
            throw new ConversionFailedException(typeDescriptor, typeDescriptor2, null, new IllegalArgumentException("A null value cannot be assigned to a primitive type"));
        }
    }

    private GenericConverter.ConvertiblePair getRequiredTypeInfo(Object obj, Class<?> cls) {
        Class<?>[] resolveTypeArguments = GenericTypeResolver.resolveTypeArguments(obj.getClass(), cls);
        if (resolveTypeArguments != null) {
            return new GenericConverter.ConvertiblePair(resolveTypeArguments[0], resolveTypeArguments[1]);
        }
        return null;
    }

    private Object handleConverterNotFound(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            assertNotPrimitiveTargetType(typeDescriptor, typeDescriptor2);
            return obj;
        }
        if (typeDescriptor.isAssignableTo(typeDescriptor2) && typeDescriptor2.getObjectType().isInstance(obj)) {
            return obj;
        }
        throw new ConverterNotFoundException(typeDescriptor, typeDescriptor2);
    }

    private Object handleResult(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2, Object obj) {
        if (obj == null) {
            assertNotPrimitiveTargetType(typeDescriptor, typeDescriptor2);
        }
        return obj;
    }

    private void invalidateCache() {
        this.converterCache.clear();
    }

    @Override // org.springframework.core.convert.converter.ConverterRegistry
    public void addConverter(Class<?> cls, Class<?> cls2, Converter<?, ?> converter) {
        addConverter(new ConverterAdapter(converter, new GenericConverter.ConvertiblePair(cls, cls2)));
    }

    @Override // org.springframework.core.convert.converter.ConverterRegistry
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConvertiblePair requiredTypeInfo = getRequiredTypeInfo(converter, Converter.class);
        Assert.notNull(requiredTypeInfo, "Unable to the determine sourceType <S> and targetType <T> which your Converter<S, T> converts between; declare these generic types.");
        addConverter(new ConverterAdapter(converter, requiredTypeInfo));
    }

    @Override // org.springframework.core.convert.converter.ConverterRegistry
    public void addConverter(GenericConverter genericConverter) {
        this.converters.add(genericConverter);
        invalidateCache();
    }

    @Override // org.springframework.core.convert.converter.ConverterRegistry
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        GenericConverter.ConvertiblePair requiredTypeInfo = getRequiredTypeInfo(converterFactory, ConverterFactory.class);
        if (requiredTypeInfo == null) {
            throw new IllegalArgumentException("Unable to the determine sourceType <S> and targetRangeType R which your ConverterFactory<S, R> converts between; declare these generic types.");
        }
        addConverter(new ConverterFactoryAdapter(converterFactory, requiredTypeInfo));
    }

    public boolean canBypassConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        Assert.notNull(typeDescriptor2, "The targetType to convert to cannot be null");
        return typeDescriptor == null || getConverter(typeDescriptor, typeDescriptor2) == NO_OP_CONVERTER;
    }

    @Override // org.springframework.core.convert.ConversionService
    public boolean canConvert(Class<?> cls, Class<?> cls2) {
        Assert.notNull(cls2, "targetType to convert to cannot be null");
        return canConvert(cls != null ? TypeDescriptor.valueOf(cls) : null, TypeDescriptor.valueOf(cls2));
    }

    @Override // org.springframework.core.convert.ConversionService
    public boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        Assert.notNull(typeDescriptor2, "targetType to convert to cannot be null");
        return typeDescriptor == null || getConverter(typeDescriptor, typeDescriptor2) != null;
    }

    @Override // org.springframework.core.convert.ConversionService
    public <T> T convert(Object obj, Class<T> cls) {
        Assert.notNull(cls, "The targetType to convert to cannot be null");
        return (T) convert(obj, TypeDescriptor.forObject(obj), TypeDescriptor.valueOf(cls));
    }

    public Object convert(Object obj, TypeDescriptor typeDescriptor) {
        return convert(obj, TypeDescriptor.forObject(obj), typeDescriptor);
    }

    @Override // org.springframework.core.convert.ConversionService
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        Assert.notNull(typeDescriptor2, "The targetType to convert to cannot be null");
        if (typeDescriptor == null) {
            Assert.isTrue(obj == null, "The source must be [null] if sourceType == [null]");
            return handleResult(typeDescriptor, typeDescriptor2, convertNullSource(typeDescriptor, typeDescriptor2));
        }
        if (obj == null || typeDescriptor.getObjectType().isInstance(obj)) {
            GenericConverter converter = getConverter(typeDescriptor, typeDescriptor2);
            return converter != null ? handleResult(typeDescriptor, typeDescriptor2, ConversionUtils.invokeConverter(converter, obj, typeDescriptor, typeDescriptor2)) : handleConverterNotFound(obj, typeDescriptor, typeDescriptor2);
        }
        throw new IllegalArgumentException("The source to convert from must be an instance of " + typeDescriptor + "; instead it was a " + obj.getClass().getName());
    }

    protected Object convertNullSource(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return null;
    }

    protected GenericConverter getConverter(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        ConverterCacheKey converterCacheKey = new ConverterCacheKey(typeDescriptor, typeDescriptor2);
        GenericConverter genericConverter = this.converterCache.get(converterCacheKey);
        if (genericConverter != null) {
            if (genericConverter != NO_MATCH) {
                return genericConverter;
            }
            return null;
        }
        GenericConverter find = this.converters.find(typeDescriptor, typeDescriptor2);
        if (find == null) {
            find = getDefaultConverter(typeDescriptor, typeDescriptor2);
        }
        if (find != null) {
            this.converterCache.put(converterCacheKey, find);
            return find;
        }
        this.converterCache.put(converterCacheKey, NO_MATCH);
        return null;
    }

    protected GenericConverter getDefaultConverter(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (typeDescriptor.isAssignableTo(typeDescriptor2)) {
            return NO_OP_CONVERTER;
        }
        return null;
    }

    @Override // org.springframework.core.convert.converter.ConverterRegistry
    public void removeConvertible(Class<?> cls, Class<?> cls2) {
        this.converters.remove(cls, cls2);
        invalidateCache();
    }

    public String toString() {
        return this.converters.toString();
    }
}
