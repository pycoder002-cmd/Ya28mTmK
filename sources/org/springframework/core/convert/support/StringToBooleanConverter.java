package org.springframework.core.convert.support;

import java.util.HashSet;
import java.util.Set;
import org.springframework.core.convert.converter.Converter;

/* loaded from: classes2.dex */
final class StringToBooleanConverter implements Converter<String, Boolean> {
    private static final Set<String> trueValues = new HashSet(4);
    private static final Set<String> falseValues = new HashSet(4);

    static {
        trueValues.add("true");
        trueValues.add("on");
        trueValues.add("yes");
        trueValues.add("1");
        falseValues.add("false");
        falseValues.add("off");
        falseValues.add("no");
        falseValues.add("0");
    }

    @Override // org.springframework.core.convert.converter.Converter
    public Boolean convert(String str) {
        String trim = str.trim();
        if ("".equals(trim)) {
            return null;
        }
        String lowerCase = trim.toLowerCase();
        if (trueValues.contains(lowerCase)) {
            return Boolean.TRUE;
        }
        if (falseValues.contains(lowerCase)) {
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("Invalid boolean value '" + str + "'");
    }
}
