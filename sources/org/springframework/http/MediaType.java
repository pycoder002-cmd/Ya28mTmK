package org.springframework.http;

import com.github.mikephil.charting.utils.Utils;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
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
import org.springframework.util.comparator.CompoundComparator;

/* loaded from: classes2.dex */
public class MediaType implements Comparable<MediaType> {
    public static final MediaType ALL;
    public static final String ALL_VALUE = "*/*";
    public static final MediaType APPLICATION_ATOM_XML;
    public static final String APPLICATION_ATOM_XML_VALUE = "application/atom+xml";
    public static final MediaType APPLICATION_FORM_URLENCODED;
    public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";
    public static final MediaType APPLICATION_JSON;
    public static final String APPLICATION_JSON_VALUE = "application/json";
    public static final MediaType APPLICATION_OCTET_STREAM;
    public static final String APPLICATION_OCTET_STREAM_VALUE = "application/octet-stream";
    public static final MediaType APPLICATION_RSS_XML;
    public static final String APPLICATION_RSS_XML_VALUE = "application/rss+xml";
    public static final MediaType APPLICATION_WILDCARD_XML;
    public static final String APPLICATION_WILDCARD_XML_VALUE = "application/*+xml";
    public static final MediaType APPLICATION_XHTML_XML;
    public static final String APPLICATION_XHTML_XML_VALUE = "application/xhtml+xml";
    public static final MediaType APPLICATION_XML;
    public static final String APPLICATION_XML_VALUE = "application/xml";
    public static final MediaType IMAGE_GIF;
    public static final String IMAGE_GIF_VALUE = "image/gif";
    public static final MediaType IMAGE_JPEG;
    public static final String IMAGE_JPEG_VALUE = "image/jpeg";
    public static final MediaType IMAGE_PNG;
    public static final String IMAGE_PNG_VALUE = "image/png";
    public static final MediaType MULTIPART_FORM_DATA;
    public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";
    private static final String PARAM_CHARSET = "charset";
    private static final String PARAM_QUALITY_FACTOR = "q";
    public static final Comparator<MediaType> QUALITY_VALUE_COMPARATOR;
    public static final Comparator<MediaType> SPECIFICITY_COMPARATOR;
    public static final MediaType TEXT_HTML;
    public static final String TEXT_HTML_VALUE = "text/html";
    public static final MediaType TEXT_PLAIN;
    public static final String TEXT_PLAIN_VALUE = "text/plain";
    public static final MediaType TEXT_XML;
    public static final String TEXT_XML_VALUE = "text/xml";
    private static final BitSet TOKEN;
    private static final String WILDCARD_TYPE = "*";
    private final Map<String, String> parameters;
    private final String subtype;
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
        ALL = valueOf("*/*");
        APPLICATION_ATOM_XML = valueOf("application/atom+xml");
        APPLICATION_RSS_XML = valueOf("application/rss+xml");
        APPLICATION_FORM_URLENCODED = valueOf("application/x-www-form-urlencoded");
        APPLICATION_JSON = valueOf("application/json");
        APPLICATION_OCTET_STREAM = valueOf("application/octet-stream");
        APPLICATION_XHTML_XML = valueOf("application/xhtml+xml");
        APPLICATION_XML = valueOf("application/xml");
        APPLICATION_WILDCARD_XML = valueOf("application/*+xml");
        IMAGE_GIF = valueOf("image/gif");
        IMAGE_JPEG = valueOf("image/jpeg");
        IMAGE_PNG = valueOf("image/png");
        MULTIPART_FORM_DATA = valueOf("multipart/form-data");
        TEXT_HTML = valueOf("text/html");
        TEXT_PLAIN = valueOf("text/plain");
        TEXT_XML = valueOf("text/xml");
        SPECIFICITY_COMPARATOR = new Comparator<MediaType>() { // from class: org.springframework.http.MediaType.1
            @Override // java.util.Comparator
            public int compare(MediaType mediaType, MediaType mediaType2) {
                if (mediaType.isWildcardType() && !mediaType2.isWildcardType()) {
                    return 1;
                }
                if (mediaType2.isWildcardType() && !mediaType.isWildcardType()) {
                    return -1;
                }
                if (!mediaType.getType().equals(mediaType2.getType())) {
                    return 0;
                }
                if (mediaType.isWildcardSubtype() && !mediaType2.isWildcardSubtype()) {
                    return 1;
                }
                if (mediaType2.isWildcardSubtype() && !mediaType.isWildcardSubtype()) {
                    return -1;
                }
                if (!mediaType.getSubtype().equals(mediaType2.getSubtype())) {
                    return 0;
                }
                int compare = Double.compare(mediaType2.getQualityValue(), mediaType.getQualityValue());
                if (compare != 0) {
                    return compare;
                }
                int size = mediaType.parameters.size();
                int size2 = mediaType2.parameters.size();
                if (size2 < size) {
                    return -1;
                }
                return size2 == size ? 0 : 1;
            }
        };
        QUALITY_VALUE_COMPARATOR = new Comparator<MediaType>() { // from class: org.springframework.http.MediaType.2
            @Override // java.util.Comparator
            public int compare(MediaType mediaType, MediaType mediaType2) {
                int compare = Double.compare(mediaType2.getQualityValue(), mediaType.getQualityValue());
                if (compare != 0) {
                    return compare;
                }
                if (mediaType.isWildcardType() && !mediaType2.isWildcardType()) {
                    return 1;
                }
                if (mediaType2.isWildcardType() && !mediaType.isWildcardType()) {
                    return -1;
                }
                if (!mediaType.getType().equals(mediaType2.getType())) {
                    return 0;
                }
                if (mediaType.isWildcardSubtype() && !mediaType2.isWildcardSubtype()) {
                    return 1;
                }
                if (mediaType2.isWildcardSubtype() && !mediaType.isWildcardSubtype()) {
                    return -1;
                }
                if (!mediaType.getSubtype().equals(mediaType2.getSubtype())) {
                    return 0;
                }
                int size = mediaType.parameters.size();
                int size2 = mediaType2.parameters.size();
                if (size2 < size) {
                    return -1;
                }
                return size2 == size ? 0 : 1;
            }
        };
    }

    public MediaType(String str) {
        this(str, "*");
    }

    public MediaType(String str, String str2) {
        this(str, str2, (Map<String, String>) Collections.emptyMap());
    }

    public MediaType(String str, String str2, double d) {
        this(str, str2, (Map<String, String>) Collections.singletonMap(PARAM_QUALITY_FACTOR, Double.toString(d)));
    }

    public MediaType(String str, String str2, Charset charset) {
        this(str, str2, (Map<String, String>) Collections.singletonMap(PARAM_CHARSET, charset.name()));
    }

    public MediaType(String str, String str2, Map<String, String> map) {
        Assert.hasLength(str, "type must not be empty");
        Assert.hasLength(str2, "subtype must not be empty");
        checkToken(str);
        checkToken(str2);
        this.type = str.toLowerCase(Locale.ENGLISH);
        this.subtype = str2.toLowerCase(Locale.ENGLISH);
        if (CollectionUtils.isEmpty(map)) {
            this.parameters = Collections.emptyMap();
            return;
        }
        LinkedCaseInsensitiveMap linkedCaseInsensitiveMap = new LinkedCaseInsensitiveMap(map.size(), Locale.ENGLISH);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            checkParameters(key, value);
            linkedCaseInsensitiveMap.put((LinkedCaseInsensitiveMap) key, value);
        }
        this.parameters = Collections.unmodifiableMap(linkedCaseInsensitiveMap);
    }

    public MediaType(MediaType mediaType, Map<String, String> map) {
        this(mediaType.getType(), mediaType.getSubtype(), map);
    }

    private void appendTo(StringBuilder sb) {
        sb.append(this.type);
        sb.append('/');
        sb.append(this.subtype);
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
            if (PARAM_CHARSET.equals(str)) {
                Charset.forName(unquote(str2));
                return;
            } else {
                if (isQuotedString(str2)) {
                    return;
                }
                checkToken(str2);
                return;
            }
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
        if (str.length() < 2) {
            return false;
        }
        return (str.startsWith("\"") && str.endsWith("\"")) || (str.startsWith("'") && str.endsWith("'"));
    }

    public static MediaType parseMediaType(String str) {
        Assert.hasLength(str, "'mediaType' must not be empty");
        String[] strArr = StringUtils.tokenizeToStringArray(str, ";");
        String trim = strArr[0].trim();
        if ("*".equals(trim)) {
            trim = "*/*";
        }
        int indexOf = trim.indexOf(47);
        if (indexOf == -1) {
            throw new InvalidMediaTypeException(str, "does not contain '/'");
        }
        if (indexOf == trim.length() - 1) {
            throw new InvalidMediaTypeException(str, "does not contain subtype after '/'");
        }
        String substring = trim.substring(0, indexOf);
        String substring2 = trim.substring(indexOf + 1, trim.length());
        if ("*".equals(substring) && !"*".equals(substring2)) {
            throw new InvalidMediaTypeException(str, "wildcard type is legal only in '*/*' (all media types)");
        }
        LinkedHashMap linkedHashMap = null;
        if (strArr.length > 1) {
            linkedHashMap = new LinkedHashMap(strArr.length - 1);
            for (int i = 1; i < strArr.length; i++) {
                String str2 = strArr[i];
                int indexOf2 = str2.indexOf(61);
                if (indexOf2 != -1) {
                    linkedHashMap.put(str2.substring(0, indexOf2), str2.substring(indexOf2 + 1, str2.length()));
                }
            }
        }
        try {
            return new MediaType(substring, substring2, linkedHashMap);
        } catch (UnsupportedCharsetException e) {
            throw new InvalidMediaTypeException(str, "unsupported charset '" + e.getCharsetName() + "'");
        } catch (IllegalArgumentException e2) {
            throw new InvalidMediaTypeException(str, e2.getMessage());
        }
    }

    public static List<MediaType> parseMediaTypes(String str) {
        if (!StringUtils.hasLength(str)) {
            return Collections.emptyList();
        }
        String[] split = str.split(",\\s*");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            arrayList.add(parseMediaType(str2));
        }
        return arrayList;
    }

    public static void sortByQualityValue(List<MediaType> list) {
        Assert.notNull(list, "'mediaTypes' must not be null");
        if (list.size() > 1) {
            Collections.sort(list, QUALITY_VALUE_COMPARATOR);
        }
    }

    public static void sortBySpecificity(List<MediaType> list) {
        Assert.notNull(list, "'mediaTypes' must not be null");
        if (list.size() > 1) {
            Collections.sort(list, SPECIFICITY_COMPARATOR);
        }
    }

    public static void sortBySpecificityAndQuality(List<MediaType> list) {
        Assert.notNull(list, "'mediaTypes' must not be null");
        if (list.size() > 1) {
            Collections.sort(list, new CompoundComparator(SPECIFICITY_COMPARATOR, QUALITY_VALUE_COMPARATOR));
        }
    }

    public static String toString(Collection<MediaType> collection) {
        StringBuilder sb = new StringBuilder();
        Iterator<MediaType> it = collection.iterator();
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

    public static MediaType valueOf(String str) {
        return parseMediaType(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(MediaType mediaType) {
        int compareToIgnoreCase = this.type.compareToIgnoreCase(mediaType.type);
        if (compareToIgnoreCase != 0) {
            return compareToIgnoreCase;
        }
        int compareToIgnoreCase2 = this.subtype.compareToIgnoreCase(mediaType.subtype);
        if (compareToIgnoreCase2 != 0) {
            return compareToIgnoreCase2;
        }
        int size = this.parameters.size() - mediaType.parameters.size();
        if (size != 0) {
            return size;
        }
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeSet.addAll(this.parameters.keySet());
        TreeSet treeSet2 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeSet2.addAll(mediaType.parameters.keySet());
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = (String) it2.next();
            int compareToIgnoreCase3 = str.compareToIgnoreCase(str2);
            if (compareToIgnoreCase3 != 0) {
                return compareToIgnoreCase3;
            }
            String str3 = this.parameters.get(str);
            String str4 = mediaType.parameters.get(str2);
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

    public MediaType copyQualityValue(MediaType mediaType) {
        if (!mediaType.parameters.containsKey(PARAM_QUALITY_FACTOR)) {
            return this;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.parameters);
        linkedHashMap.put(PARAM_QUALITY_FACTOR, mediaType.parameters.get(PARAM_QUALITY_FACTOR));
        return new MediaType(this, linkedHashMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaType)) {
            return false;
        }
        MediaType mediaType = (MediaType) obj;
        return this.type.equalsIgnoreCase(mediaType.type) && this.subtype.equalsIgnoreCase(mediaType.subtype) && this.parameters.equals(mediaType.parameters);
    }

    public Charset getCharSet() {
        String parameter = getParameter(PARAM_CHARSET);
        if (parameter != null) {
            return Charset.forName(unquote(parameter));
        }
        return null;
    }

    public String getParameter(String str) {
        return this.parameters.get(str);
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public double getQualityValue() {
        String parameter = getParameter(PARAM_QUALITY_FACTOR);
        if (parameter != null) {
            return Double.parseDouble(unquote(parameter));
        }
        return 1.0d;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return (31 * ((this.type.hashCode() * 31) + this.subtype.hashCode())) + this.parameters.hashCode();
    }

    public boolean includes(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (isWildcardType()) {
            return true;
        }
        if (this.type.equals(mediaType.type)) {
            if (this.subtype.equals(mediaType.subtype)) {
                return true;
            }
            if (isWildcardSubtype()) {
                int indexOf = this.subtype.indexOf(43);
                if (indexOf == -1) {
                    return true;
                }
                int indexOf2 = mediaType.subtype.indexOf(43);
                if (indexOf2 != -1) {
                    String substring = this.subtype.substring(0, indexOf);
                    if (this.subtype.substring(indexOf + 1).equals(mediaType.subtype.substring(indexOf2 + 1)) && "*".equals(substring)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCompatibleWith(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (isWildcardType() || mediaType.isWildcardType()) {
            return true;
        }
        if (this.type.equals(mediaType.type)) {
            if (this.subtype.equals(mediaType.subtype)) {
                return true;
            }
            if (isWildcardSubtype() || mediaType.isWildcardSubtype()) {
                int indexOf = this.subtype.indexOf(43);
                int indexOf2 = mediaType.subtype.indexOf(43);
                if (indexOf == -1 && indexOf2 == -1) {
                    return true;
                }
                if (indexOf != -1 && indexOf2 != -1) {
                    String substring = this.subtype.substring(0, indexOf);
                    String substring2 = mediaType.subtype.substring(0, indexOf2);
                    if (this.subtype.substring(indexOf + 1).equals(mediaType.subtype.substring(indexOf2 + 1)) && ("*".equals(substring) || "*".equals(substring2))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isConcrete() {
        return (isWildcardType() || isWildcardSubtype()) ? false : true;
    }

    public boolean isWildcardSubtype() {
        return "*".equals(this.subtype) || this.subtype.startsWith("*+");
    }

    public boolean isWildcardType() {
        return "*".equals(this.type);
    }

    public MediaType removeQualityValue() {
        if (!this.parameters.containsKey(PARAM_QUALITY_FACTOR)) {
            return this;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.parameters);
        linkedHashMap.remove(PARAM_QUALITY_FACTOR);
        return new MediaType(this, linkedHashMap);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendTo(sb);
        return sb.toString();
    }
}
