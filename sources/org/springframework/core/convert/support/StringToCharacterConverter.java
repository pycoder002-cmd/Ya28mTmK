package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;

/* loaded from: classes2.dex */
final class StringToCharacterConverter implements Converter<String, Character> {
    @Override // org.springframework.core.convert.converter.Converter
    public Character convert(String str) {
        if (str.length() == 0) {
            return null;
        }
        if (str.length() <= 1) {
            return Character.valueOf(str.charAt(0));
        }
        throw new IllegalArgumentException("Can only convert a [String] with length of 1 to a [Character]; string value '" + str + "'  has length of " + str.length());
    }
}
