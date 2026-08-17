package org.springframework.core.convert.support;

import java.util.Set;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: classes2.dex */
public abstract class ConversionServiceFactory {
    @Deprecated
    public static void addDefaultConverters(GenericConversionService genericConversionService) {
        DefaultConversionService.addDefaultConverters(genericConversionService);
    }

    @Deprecated
    public static GenericConversionService createDefaultConversionService() {
        return new DefaultConversionService();
    }

    public static void registerConverters(Set<?> set, ConverterRegistry converterRegistry) {
        if (set != null) {
            for (Object obj : set) {
                if (obj instanceof GenericConverter) {
                    converterRegistry.addConverter((GenericConverter) obj);
                } else if (obj instanceof Converter) {
                    converterRegistry.addConverter((Converter<?, ?>) obj);
                } else {
                    if (!(obj instanceof ConverterFactory)) {
                        throw new IllegalArgumentException("Each converter object must implement one of the Converter, ConverterFactory, or GenericConverter interfaces");
                    }
                    converterRegistry.addConverterFactory((ConverterFactory) obj);
                }
            }
        }
    }
}
