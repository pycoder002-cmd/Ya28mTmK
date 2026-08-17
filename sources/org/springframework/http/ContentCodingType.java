package org.springframework.http;

import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public class ContentCodingType implements Comparable<ContentCodingType> {
    public static final ContentCodingType ALL;
    public static final String ALL_VALUE = "*";
    public static final ContentCodingType GZIP;
    public static final String GZIP_VALUE = "gzip";
    public static final ContentCodingType IDENTITY;
    public static final String IDENTITY_VALUE = "identity";
    private static final String PARAM_QUALITY_FACTOR = "q";
    public static final Comparator<ContentCodingType> QUALITY_VALUE_COMPARATOR;
    private static final BitSet TOKEN;
    private static final String WILDCARD_TYPE = "*";
    private final Map<String, String> parameters;
    private final String type;

    static {
        BitSet bitSet = new BitSet(128);
        for (int i = 0; i <= 31; i++) {
            bitSet.set(i);
        }
        bitSet.set(Opcodes.LAND);
        BitSet bitSet2 = new BitSet(128);
        bitSet2.set(40);
        bitSet2.set(41);
        bitSet2.set(60);
        bitSet2.set(62);
        bitSet2.set(64);
        bitSet2.set(44);
        bitSet2.set(59);
        bitSet2.set(58);
        bitSet2.set(92);
        bitSet2.set(34);
        bitSet2.set(47);
        bitSet2.set(91);
        bitSet2.set(93);
        bitSet2.set(63);
        bitSet2.set(61);
        bitSet2.set(Opcodes.LSHR);
        bitSet2.set(Opcodes.LUSHR);
        bitSet2.set(32);
        bitSet2.set(9);
        TOKEN = new BitSet(128);
        TOKEN.set(0, 128);
        TOKEN.andNot(bitSet);
        TOKEN.andNot(bitSet2);
        ALL = valueOf("*");
        IDENTITY = valueOf("identity");
        GZIP = valueOf(GZIP_VALUE);
        QUALITY_VALUE_COMPARATOR = new Comparator<ContentCodingType>() { // from class: org.springframework.http.ContentCodingType.1
            @Override // java.util.Comparator
            public int compare(ContentCodingType contentCodingType, ContentCodingType contentCodingType2) {
                int compare = Double.compare(contentCodingType2.getQualityValue(), contentCodingType.getQualityValue());
                if (compare != 0) {
                    return compare;
                }
                if (contentCodingType.isWildcardType() && !contentCodingType2.isWildcardType()) {
                    return 1;
                }
                if (!contentCodingType2.isWildcardType() || contentCodingType.isWildcardType()) {
                    return !contentCodingType.getType().equals(contentCodingType2.getType()) ? 0 : 0;
                }
                return -1;
            }
        };
    }

    public ContentCodingType(String str) {
        this(str, (Map<String, String>) Collections.emptyMap());
    }

    public ContentCodingType(String str, double d) {
        this(str, (Map<String, String>) Collections.singletonMap(PARAM_QUALITY_FACTOR, Double.toString(d)));
    }

    public ContentCodingType(String str, Map<String, String> map) {
        Assert.hasLength(str, "'type' must not be empty");
        checkToken(str);
        this.type = str.toLowerCase(Locale.ENGLISH);
        if (CollectionUtils.isEmpty(map)) {
            this.parameters = Collections.emptyMap();
            return;
        }
        LinkedCaseInsensitiveMap linkedCaseInsensitiveMap = new LinkedCaseInsensitiveMap(map.size(), Locale.ENGLISH);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            checkParameters(key, value);
            linkedCaseInsensitiveMap.put((LinkedCaseInsensitiveMap) key, unquote(value));
        }
        this.parameters = Collections.unmodifiableMap(linkedCaseInsensitiveMap);
    }

    private void appendTo(StringBuilder sb) {
        sb.append(this.type);
        appendTo(this.parameters, sb);
    }

    private void appendTo(Map<String, String> map, StringBuilder sb) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(';');
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
        }
    }

    private void checkParameters(String str, String str2) {
        Assert.hasLength(str, "parameter attribute must not be empty");
        Assert.hasLength(str2, "parameter value must not be empty");
        checkToken(str);
        if (!PARAM_QUALITY_FACTOR.equals(str)) {
            if (isQuotedString(str2)) {
                return;
            }
            checkToken(str2);
            return;
        }
        String unquote = unquote(str2);
        double parseDouble = Double.parseDouble(unquote);
        Assert.isTrue(parseDouble >= Utils.DOUBLE_EPSILON && parseDouble <= 1.0d, "Invalid quality value \"" + unquote + "\": should be between 0.0 and 1.0");
    }

    private void checkToken(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!TOKEN.get(charAt)) {
                throw new IllegalArgumentException("Invalid token character '" + charAt + "' in token \"" + str + "\"");
            }
        }
    }

    private boolean isQuotedString(String str) {
        return str.length() > 1 && str.startsWith("\"") && str.endsWith("\"");
    }

    public static ContentCodingType parseCodingType(String str) {
        LinkedHashMap linkedHashMap;
        Assert.hasLength(str, "'codingType' must not be empty");
        String[] strArr = StringUtils.tokenizeToStringArray(str, ";");
        String trim = strArr[0].trim();
        if (strArr.length > 1) {
            linkedHashMap = new LinkedHashMap(strArr.length - 1);
            for (int i = 1; i < strArr.length; i++) {
                String str2 = strArr[i];
                int indexOf = str2.indexOf(61);
                if (indexOf != -1) {
                    linkedHashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1, str2.length()));
                }
            }
        } else {
            linkedHashMap = null;
        }
        return new ContentCodingType(trim, linkedHashMap);
    }

    public static List<ContentCodingType> parseCodingTypes(String str) {
        if (!StringUtils.hasLength(str)) {
            return Collections.emptyList();
        }
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            arrayList.add(parseCodingType(str2));
        }
        return arrayList;
    }

    public static void sortByQualityValue(List<ContentCodingType> list) {
        Assert.notNull(list, "'codingTypes' must not be null");
        if (list.size() > 1) {
            Collections.sort(list, QUALITY_VALUE_COMPARATOR);
        }
    }

    public static String toString(Collection<ContentCodingType> collection) {
        StringBuilder sb = new StringBuilder();
        Iterator<ContentCodingType> it = collection.iterator();
        while (it.hasNext()) {
            it.next().appendTo(sb);
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private String unquote(String str) {
        if (str == null) {
            return null;
        }
        return isQuotedString(str) ? str.substring(1, str.length() - 1) : str;
    }

    public static ContentCodingType valueOf(String str) {
        return parseCodingType(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(ContentCodingType contentCodingType) {
        int compareToIgnoreCase = this.type.compareToIgnoreCase(contentCodingType.type);
        if (compareToIgnoreCase != 0) {
            return compareToIgnoreCase;
        }
        int size = this.parameters.size() - contentCodingType.parameters.size();
        if (size != 0) {
            return size;
        }
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeSet.addAll(this.parameters.keySet());
        TreeSet treeSet2 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeSet2.addAll(contentCodingType.parameters.keySet());
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = (String) it2.next();
            int compareToIgnoreCase2 = str.compareToIgnoreCase(str2);
            if (compareToIgnoreCase2 != 0) {
                return compareToIgnoreCase2;
            }
            String str3 = this.parameters.get(str);
            String str4 = contentCodingType.parameters.get(str2);
            if (str4 == null) {
                str4 = "";
            }
            int compareTo = str3.compareTo(str4);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContentCodingType)) {
            return false;
        }
        ContentCodingType contentCodingType = (ContentCodingType) obj;
        return this.type.equalsIgnoreCase(contentCodingType.type) && this.parameters.equals(contentCodingType.parameters);
    }

    public String getParameter(String str) {
        return this.parameters.get(str);
    }

    public double getQualityValue() {
        String parameter = getParameter(PARAM_QUALITY_FACTOR);
        if (parameter != null) {
            return Double.parseDouble(parameter);
        }
        return 1.0d;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return (31 * this.type.hashCode()) + this.parameters.hashCode();
    }

    public boolean includes(ContentCodingType contentCodingType) {
        if (contentCodingType == null) {
            return false;
        }
        return isWildcardType() || this.type.equals(contentCodingType.type);
    }

    public boolean isCompatibleWith(ContentCodingType contentCodingType) {
        if (contentCodingType == null) {
            return false;
        }
        return isWildcardType() || contentCodingType.isWildcardType() || this.type.equals(contentCodingType.type);
    }

    public boolean isWildcardType() {
        return "*".equals(this.type);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendTo(sb);
        return sb.toString();
    }
}
