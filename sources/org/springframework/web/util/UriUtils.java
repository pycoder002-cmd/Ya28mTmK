package org.springframework.web.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;
import org.springframework.web.util.HierarchicalUriComponents;

/* loaded from: classes2.dex */
public abstract class UriUtils {
    private static final String HOST_PATTERN = "([^/?#:]*)";
    private static final String HTTP_PATTERN = "(http|https):";
    private static final String LAST_PATTERN = "(.*)";
    private static final String PATH_PATTERN = "([^?#]*)";
    private static final String PORT_PATTERN = "(\\d*)";
    private static final String QUERY_PATTERN = "([^#]*)";
    private static final String SCHEME_PATTERN = "([^:/?#]+):";
    private static final String USERINFO_PATTERN = "([^@/]*)";
    private static final Pattern URI_PATTERN = Pattern.compile("^(([^:/?#]+):)?(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?([^#]*))?(#(.*))?");
    private static final Pattern HTTP_URL_PATTERN = Pattern.compile("^(http|https):(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?(.*))?");

    public static String decode(String str, String str2) throws UnsupportedEncodingException {
        Assert.notNull(str, "Source must not be null");
        Assert.hasLength(str2, "Encoding must not be empty");
        int length = str.length();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        int i = 0;
        boolean z = false;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '%') {
                int i2 = i + 2;
                if (i2 >= length) {
                    throw new IllegalArgumentException("Invalid encoded sequence \"" + str.substring(i) + "\"");
                }
                char charAt2 = str.charAt(i + 1);
                char charAt3 = str.charAt(i2);
                int digit = Character.digit(charAt2, 16);
                int digit2 = Character.digit(charAt3, 16);
                if (digit == -1 || digit2 == -1) {
                    throw new IllegalArgumentException("Invalid encoded sequence \"" + str.substring(i) + "\"");
                }
                byteArrayOutputStream.write((char) ((digit << 4) + digit2));
                i = i2;
                z = true;
            } else {
                byteArrayOutputStream.write(charAt);
            }
            i++;
        }
        return z ? new String(byteArrayOutputStream.toByteArray(), str2) : str;
    }

    public static String encodeAuthority(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.AUTHORITY);
    }

    public static String encodeFragment(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.FRAGMENT);
    }

    public static String encodeHost(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.HOST_IPV4);
    }

    @Deprecated
    public static String encodeHttpUrl(String str, String str2) throws UnsupportedEncodingException {
        Assert.notNull(str, "HTTP URL must not be null");
        Assert.hasLength(str2, "Encoding must not be empty");
        Matcher matcher = HTTP_URL_PATTERN.matcher(str);
        if (matcher.matches()) {
            return encodeUriComponents(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(5), matcher.group(7), matcher.group(8), matcher.group(10), null, str2);
        }
        throw new IllegalArgumentException("[" + str + "] is not a valid HTTP URL");
    }

    public static String encodePath(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.PATH);
    }

    public static String encodePathSegment(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.PATH_SEGMENT);
    }

    public static String encodePort(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.PORT);
    }

    public static String encodeQuery(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.QUERY);
    }

    public static String encodeQueryParam(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.QUERY_PARAM);
    }

    public static String encodeScheme(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.SCHEME);
    }

    @Deprecated
    public static String encodeUri(String str, String str2) throws UnsupportedEncodingException {
        Assert.notNull(str, "URI must not be null");
        Assert.hasLength(str2, "Encoding must not be empty");
        Matcher matcher = URI_PATTERN.matcher(str);
        if (matcher.matches()) {
            return encodeUriComponents(matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(6), matcher.group(8), matcher.group(9), matcher.group(11), matcher.group(13), str2);
        }
        throw new IllegalArgumentException("[" + str + "] is not a valid URI");
    }

    @Deprecated
    public static String encodeUriComponents(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) throws UnsupportedEncodingException {
        Assert.hasLength(str9, "Encoding must not be empty");
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(encodeScheme(str, str9));
            sb.append(':');
        }
        if (str2 != null) {
            sb.append("//");
            if (str3 != null) {
                sb.append(encodeUserInfo(str3, str9));
                sb.append('@');
            }
            if (str4 != null) {
                sb.append(encodeHost(str4, str9));
            }
            if (str5 != null) {
                sb.append(':');
                sb.append(encodePort(str5, str9));
            }
        }
        sb.append(encodePath(str6, str9));
        if (str7 != null) {
            sb.append('?');
            sb.append(encodeQuery(str7, str9));
        }
        if (str8 != null) {
            sb.append('#');
            sb.append(encodeFragment(str8, str9));
        }
        return sb.toString();
    }

    public static String encodeUserInfo(String str, String str2) throws UnsupportedEncodingException {
        return HierarchicalUriComponents.encodeUriComponent(str, str2, HierarchicalUriComponents.Type.USER_INFO);
    }
}
