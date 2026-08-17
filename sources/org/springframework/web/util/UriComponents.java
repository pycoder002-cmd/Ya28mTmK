package org.springframework.web.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

/* loaded from: classes2.dex */
public abstract class UriComponents implements Serializable {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");
    private final String fragment;
    private final String scheme;

    /* loaded from: classes2.dex */
    private static class MapTemplateVariables implements UriTemplateVariables {
        private final Map<String, ?> uriVariables;

        public MapTemplateVariables(Map<String, ?> map) {
            this.uriVariables = map;
        }

        @Override // org.springframework.web.util.UriComponents.UriTemplateVariables
        public Object getValue(String str) {
            if (this.uriVariables.containsKey(str)) {
                return this.uriVariables.get(str);
            }
            throw new IllegalArgumentException("Map has no value for '" + str + "'");
        }
    }

    /* loaded from: classes2.dex */
    public interface UriTemplateVariables {
        public static final Object SKIP_VALUE = UriTemplateVariables.class;

        Object getValue(String str);
    }

    /* loaded from: classes2.dex */
    private static class VarArgsTemplateVariables implements UriTemplateVariables {
        private final Iterator<Object> valueIterator;

        public VarArgsTemplateVariables(Object... objArr) {
            this.valueIterator = Arrays.asList(objArr).iterator();
        }

        @Override // org.springframework.web.util.UriComponents.UriTemplateVariables
        public Object getValue(String str) {
            if (this.valueIterator.hasNext()) {
                return this.valueIterator.next();
            }
            throw new IllegalArgumentException("Not enough variable values available to expand '" + str + "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UriComponents(String str, String str2) {
        this.scheme = str;
        this.fragment = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String expandUriComponent(String str, UriTemplateVariables uriTemplateVariables) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(Opcodes.LSHR) == -1) {
            return str;
        }
        Matcher matcher = NAMES_PATTERN.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            Object value = uriTemplateVariables.getValue(getVariableName(matcher.group(1)));
            if (!UriTemplateVariables.SKIP_VALUE.equals(value)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(getVariableValueAsString(value)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static String getVariableName(String str) {
        int indexOf = str.indexOf(58);
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }

    private static String getVariableValueAsString(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public final UriComponents encode() {
        try {
            return encode("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public abstract UriComponents encode(String str) throws UnsupportedEncodingException;

    public final UriComponents expand(Map<String, ?> map) {
        Assert.notNull(map, "'uriVariables' must not be null");
        return expandInternal(new MapTemplateVariables(map));
    }

    public final UriComponents expand(UriTemplateVariables uriTemplateVariables) {
        Assert.notNull(uriTemplateVariables, "'uriVariables' must not be null");
        return expandInternal(uriTemplateVariables);
    }

    public final UriComponents expand(Object... objArr) {
        Assert.notNull(objArr, "'uriVariableValues' must not be null");
        return expandInternal(new VarArgsTemplateVariables(objArr));
    }

    abstract UriComponents expandInternal(UriTemplateVariables uriTemplateVariables);

    public final String getFragment() {
        return this.fragment;
    }

    public abstract String getHost();

    public abstract String getPath();

    public abstract List<String> getPathSegments();

    public abstract int getPort();

    public abstract String getQuery();

    public abstract MultiValueMap<String, String> getQueryParams();

    public final String getScheme() {
        return this.scheme;
    }

    public abstract String getSchemeSpecificPart();

    public abstract String getUserInfo();

    public abstract UriComponents normalize();

    public final String toString() {
        return toUriString();
    }

    public abstract URI toUri();

    public abstract String toUriString();
}
