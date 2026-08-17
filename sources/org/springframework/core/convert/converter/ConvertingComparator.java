package org.springframework.core.convert.converter;

import java.util.Comparator;
import java.util.Map;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.Assert;
import org.springframework.util.comparator.ComparableComparator;

/* loaded from: classes2.dex */
public class ConvertingComparator<S, T> implements Comparator<S> {
    private Comparator<T> comparator;
    private Converter<S, T> converter;

    /* loaded from: classes2.dex */
    private static class ConversionServiceConverter<S, T> implements Converter<S, T> {
        private final ConversionService conversionService;
        private final Class<? extends T> targetType;

        public ConversionServiceConverter(ConversionService conversionService, Class<? extends T> cls) {
            Assert.notNull(conversionService, "ConversionService must not be null");
            Assert.notNull(cls, "TargetType must not be null");
            this.conversionService = conversionService;
            this.targetType = cls;
        }

        @Override // org.springframework.core.convert.converter.Converter
        public T convert(S s) {
            return (T) this.conversionService.convert(s, this.targetType);
        }
    }

    public ConvertingComparator(Comparator<T> comparator, ConversionService conversionService, Class<? extends T> cls) {
        this(comparator, new ConversionServiceConverter(conversionService, cls));
    }

    public ConvertingComparator(Comparator<T> comparator, Converter<S, T> converter) {
        Assert.notNull(comparator, "Comparator must not be null");
        Assert.notNull(converter, "Converter must not be null");
        this.comparator = comparator;
        this.converter = converter;
    }

    public ConvertingComparator(Converter<S, T> converter) {
        this(ComparableComparator.INSTANCE, converter);
    }

    public static <K, V> ConvertingComparator<Map.Entry<K, V>, K> mapEntryKeys(Comparator<K> comparator) {
        return new ConvertingComparator<>(comparator, new Converter<Map.Entry<K, V>, K>() { // from class: org.springframework.core.convert.converter.ConvertingComparator.1
            @Override // org.springframework.core.convert.converter.Converter
            public K convert(Map.Entry<K, V> entry) {
                return entry.getKey();
            }
        });
    }

    public static <K, V> ConvertingComparator<Map.Entry<K, V>, V> mapEntryValues(Comparator<V> comparator) {
        return new ConvertingComparator<>(comparator, new Converter<Map.Entry<K, V>, V>() { // from class: org.springframework.core.convert.converter.ConvertingComparator.2
            @Override // org.springframework.core.convert.converter.Converter
            public V convert(Map.Entry<K, V> entry) {
                return entry.getValue();
            }
        });
    }

    @Override // java.util.Comparator
    public int compare(S s, S s2) {
        return this.comparator.compare(this.converter.convert(s), this.converter.convert(s2));
    }
}
