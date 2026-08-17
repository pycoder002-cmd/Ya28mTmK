package org.springframework.web.util;

import java.io.Serializable;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class UriTemplate implements Serializable {
    private static final String DEFAULT_VARIABLE_PATTERN = "(.*)";
    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");
    private final Pattern matchPattern;
    private final UriComponents uriComponents;
    private final String uriTemplate;
    private final List<String> variableNames;

    /* loaded from: classes2.dex */
    private static class Parser {
        private final StringBuilder patternBuilder;
        private final List<String> variableNames;

        private Parser(String str) {
            this.variableNames = new LinkedList();
            this.patternBuilder = new StringBuilder();
            Assert.hasText(str, "'uriTemplate' must not be null");
            Matcher matcher = UriTemplate.NAMES_PATTERN.matcher(str);
            int i = 0;
            while (matcher.find()) {
                this.patternBuilder.append(quote(str, i, matcher.start()));
                String group = matcher.group(1);
                int indexOf = group.indexOf(58);
                if (indexOf == -1) {
                    this.patternBuilder.append(UriTemplate.DEFAULT_VARIABLE_PATTERN);
                    this.variableNames.add(group);
                } else {
                    int i2 = indexOf + 1;
                    if (i2 == group.length()) {
                        throw new IllegalArgumentException("No custom regular expression specified after ':' in \"" + group + "\"");
                    }
                    String substring = group.substring(i2, group.length());
                    this.patternBuilder.append('(');
                    this.patternBuilder.append(substring);
                    this.patternBuilder.append(')');
                    this.variableNames.add(group.substring(0, indexOf));
                }
                i = matcher.end();
            }
            this.patternBuilder.append(quote(str, i, str.length()));
            int length = this.patternBuilder.length() - 1;
            if (length < 0 || this.patternBuilder.charAt(length) != '/') {
                return;
            }
            this.patternBuilder.deleteCharAt(length);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Pattern getMatchPattern() {
            return Pattern.compile(this.patternBuilder.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<String> getVariableNames() {
            return Collections.unmodifiableList(this.variableNames);
        }

        private String quote(String str, int i, int i2) {
            return i == i2 ? "" : Pattern.quote(str.substring(i, i2));
        }
    }

    public UriTemplate(String str) {
        Parser parser = new Parser(str);
        this.uriTemplate = str;
        this.variableNames = parser.getVariableNames();
        this.matchPattern = parser.getMatchPattern();
        this.uriComponents = UriComponentsBuilder.fromUriString(str).build();
    }

    public URI expand(Map<String, ?> map) {
        return this.uriComponents.expand(map).encode().toUri();
    }

    public URI expand(Object... objArr) {
        return this.uriComponents.expand(objArr).encode().toUri();
    }

    public List<String> getVariableNames() {
        return this.variableNames;
    }

    public Map<String, String> match(String str) {
        Assert.notNull(str, "'uri' must not be null");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.variableNames.size());
        Matcher matcher = this.matchPattern.matcher(str);
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                linkedHashMap.put(this.variableNames.get(i - 1), matcher.group(i));
            }
        }
        return linkedHashMap;
    }

    public boolean matches(String str) {
        if (str == null) {
            return false;
        }
        return this.matchPattern.matcher(str).matches();
    }

    public String toString() {
        return this.uriTemplate;
    }
}
