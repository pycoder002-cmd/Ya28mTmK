package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;

/* loaded from: classes2.dex */
final class NumberToCharacterConverter implements Converter<Number, Character> {
    @Override // org.springframework.core.convert.converter.Converter
    public Character convert(Number number) {
        return Character.valueOf((char) number.shortValue());
    }
}
