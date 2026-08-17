package org.springframework.core.convert.support;

import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
final class StringToUUIDConverter implements Converter<String, UUID> {
    @Override // org.springframework.core.convert.converter.Converter
    public UUID convert(String str) {
        if (StringUtils.hasLength(str)) {
            return UUID.fromString(str.trim());
        }
        return null;
    }
}
