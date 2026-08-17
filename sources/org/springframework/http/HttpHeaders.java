package org.springframework.http;

import java.io.Serializable;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.springframework.util.Assert;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public class HttpHeaders implements MultiValueMap<String, String>, Serializable {
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String AGE = "Age";
    public static final String ALLOW = "Allow";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONNECTION = "Connection";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LOCATION = "Content-Location";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String COOKIE = "Cookie";
    public static final String DATE = "Date";
    public static final String ETAG = "ETag";
    public static final String EXPECT = "Expect";
    public static final String EXPIRES = "Expires";
    public static final String FROM = "From";
    public static final String HOST = "Host";
    public static final String IF_MATCH = "If-Match";
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String IF_RANGE = "If-Range";
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String LINK = "Link";
    public static final String LOCATION = "Location";
    public static final String MAX_FORWARDS = "Max-Forwards";
    public static final String ORIGIN = "Origin";
    public static final String PRAGMA = "Pragma";
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
    public static final String RANGE = "Range";
    public static final String REFERER = "Referer";
    public static final String RETRY_AFTER = "Retry-After";
    public static final String SERVER = "Server";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String SET_COOKIE2 = "Set-Cookie2";
    public static final String TE = "TE";
    public static final String TRAILER = "Trailer";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String UPGRADE = "Upgrade";
    public static final String USER_AGENT = "User-Agent";
    public static final String VARY = "Vary";
    public static final String VIA = "Via";
    public static final String WARNING = "Warning";
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final long serialVersionUID = -8578554704772377436L;
    private final Map<String, List<String>> headers;
    private static final String[] DATE_FORMATS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM dd HH:mm:ss yyyy"};
    private static TimeZone GMT = TimeZone.getTimeZone("GMT");

    public HttpHeaders() {
        this(new LinkedCaseInsensitiveMap(8, Locale.ENGLISH), false);
    }

    private HttpHeaders(Map<String, List<String>> map, boolean z) {
        Assert.notNull(map, "'headers' must not be null");
        if (!z) {
            this.headers = map;
            return;
        }
        LinkedCaseInsensitiveMap linkedCaseInsensitiveMap = new LinkedCaseInsensitiveMap(map.size(), Locale.ENGLISH);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            linkedCaseInsensitiveMap.put((LinkedCaseInsensitiveMap) entry.getKey(), (String) Collections.unmodifiableList(entry.getValue()));
        }
        this.headers = Collections.unmodifiableMap(linkedCaseInsensitiveMap);
    }

    public static HttpHeaders readOnlyHttpHeaders(HttpHeaders httpHeaders) {
        return new HttpHeaders(httpHeaders, true);
    }

    @Override // org.springframework.util.MultiValueMap
    public void add(String str, String str2) {
        List<String> list = this.headers.get(str);
        if (list == null) {
            list = new LinkedList<>();
            this.headers.put(str, list);
        }
        list.add(str2);
    }

    @Override // java.util.Map
    public void clear() {
        this.headers.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.headers.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.headers.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, List<String>>> entrySet() {
        return this.headers.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HttpHeaders) {
            return this.headers.equals(((HttpHeaders) obj).headers);
        }
        return false;
    }

    @Override // java.util.Map
    public List<String> get(Object obj) {
        return this.headers.get(obj);
    }

    public List<MediaType> getAccept() {
        String first = getFirst("Accept");
        List<MediaType> parseMediaTypes = first != null ? MediaType.parseMediaTypes(first) : Collections.emptyList();
        if (parseMediaTypes.size() != 1) {
            return parseMediaTypes;
        }
        List<String> list = get("Accept");
        return list.size() > 1 ? MediaType.parseMediaTypes(StringUtils.collectionToCommaDelimitedString(list)) : parseMediaTypes;
    }

    public List<Charset> getAcceptCharset() {
        ArrayList arrayList = new ArrayList();
        String first = getFirst("Accept-Charset");
        if (first != null) {
            String[] split = first.split(",\\s*");
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String str = split[i];
                int indexOf = str.indexOf(59);
                if (indexOf != -1) {
                    str = str.substring(0, indexOf);
                }
                if (!str.equals("*")) {
                    arrayList.add(Charset.forName(str));
                }
            }
        }
        return arrayList;
    }

    public List<ContentCodingType> getAcceptEncoding() {
        String first = getFirst("Accept-Encoding");
        return first != null ? ContentCodingType.parseCodingTypes(first) : Collections.emptyList();
    }

    public String getAcceptLanguage() {
        return getFirst("Accept-Language");
    }

    public Set<HttpMethod> getAllow() {
        String first = getFirst("Allow");
        if (StringUtils.isEmpty(first)) {
            return EnumSet.noneOf(HttpMethod.class);
        }
        ArrayList arrayList = new ArrayList(5);
        for (String str : first.split(",\\s*")) {
            arrayList.add(HttpMethod.valueOf(str));
        }
        return EnumSet.copyOf((Collection) arrayList);
    }

    public String getAuthorization() {
        return getFirst("Authorization");
    }

    public String getCacheControl() {
        return getFirst("Cache-Control");
    }

    public List<String> getConnection() {
        return getFirstValueAsList("Connection");
    }

    public List<ContentCodingType> getContentEncoding() {
        String first = getFirst("Content-Encoding");
        return first != null ? ContentCodingType.parseCodingTypes(first) : Collections.emptyList();
    }

    public long getContentLength() {
        String first = getFirst("Content-Length");
        if (first != null) {
            return Long.parseLong(first);
        }
        return -1L;
    }

    public MediaType getContentType() {
        String first = getFirst("Content-Type");
        if (StringUtils.hasLength(first)) {
            return MediaType.parseMediaType(first);
        }
        return null;
    }

    public long getDate() {
        return getFirstDate("Date");
    }

    public String getETag() {
        return getFirst("ETag");
    }

    public long getExpires() {
        try {
            return getFirstDate("Expires");
        } catch (IllegalArgumentException unused) {
            return -1L;
        }
    }

    @Override // org.springframework.util.MultiValueMap
    public String getFirst(String str) {
        List<String> list = this.headers.get(str);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    public long getFirstDate(String str) {
        String first = getFirst(str);
        if (first == null) {
            return -1L;
        }
        for (String str2 : DATE_FORMATS) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.US);
            simpleDateFormat.setTimeZone(GMT);
            try {
                return simpleDateFormat.parse(first).getTime();
            } catch (ParseException unused) {
            }
        }
        throw new IllegalArgumentException("Cannot parse date value \"" + first + "\" for \"" + str + "\" header");
    }

    protected List<String> getFirstValueAsList(String str) {
        ArrayList arrayList = new ArrayList();
        String first = getFirst(str);
        if (first != null) {
            for (String str2 : first.split(",\\s*")) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    public long getIfModifiedSince() {
        return getFirstDate("If-Modified-Since");
    }

    public List<String> getIfNoneMatch() {
        return getFirstValueAsList("If-None-Match");
    }

    @Deprecated
    public long getIfNotModifiedSince() {
        return getIfModifiedSince();
    }

    public long getLastModified() {
        return getFirstDate("Last-Modified");
    }

    public URI getLocation() {
        String first = getFirst("Location");
        if (first != null) {
            return URI.create(first);
        }
        return null;
    }

    public String getOrigin() {
        return getFirst(ORIGIN);
    }

    public String getPragma() {
        return getFirst("Pragma");
    }

    public String getUpgrade() {
        return getFirst("Upgrade");
    }

    public String getUserAgent() {
        return getFirst("User-Agent");
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.headers.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.headers.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.headers.keySet();
    }

    @Override // java.util.Map
    public List<String> put(String str, List<String> list) {
        return this.headers.put(str, list);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends List<String>> map) {
        this.headers.putAll(map);
    }

    @Override // java.util.Map
    public List<String> remove(Object obj) {
        return this.headers.remove(obj);
    }

    @Override // org.springframework.util.MultiValueMap
    public void set(String str, String str2) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(str2);
        this.headers.put(str, linkedList);
    }

    public void setAccept(List<MediaType> list) {
        set("Accept", MediaType.toString(list));
    }

    public void setAcceptCharset(List<Charset> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<Charset> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().name().toLowerCase(Locale.ENGLISH));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        set("Accept-Charset", sb.toString());
    }

    public void setAcceptEncoding(List<ContentCodingType> list) {
        set("Accept-Encoding", ContentCodingType.toString(list));
    }

    public void setAcceptEncoding(ContentCodingType contentCodingType) {
        setAcceptEncoding(Collections.singletonList(contentCodingType));
    }

    public void setAcceptLanguage(String str) {
        set("Accept-Language", str);
    }

    @Override // org.springframework.util.MultiValueMap
    public void setAll(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            set(entry.getKey(), entry.getValue());
        }
    }

    public void setAllow(Set<HttpMethod> set) {
        set("Allow", StringUtils.collectionToCommaDelimitedString(set));
    }

    public void setAuthorization(HttpAuthentication httpAuthentication) {
        set("Authorization", httpAuthentication.getHeaderValue());
    }

    public void setCacheControl(String str) {
        set("Cache-Control", str);
    }

    public void setConnection(String str) {
        set("Connection", str);
    }

    public void setConnection(List<String> list) {
        set("Connection", toCommaDelimitedString(list));
    }

    public void setContentDispositionFormData(String str, String str2) {
        Assert.notNull(str, "'name' must not be null");
        StringBuilder sb = new StringBuilder("form-data; name=\"");
        sb.append(str);
        sb.append('\"');
        if (str2 != null) {
            sb.append("; filename=\"");
            sb.append(str2);
            sb.append('\"');
        }
        set("Content-Disposition", sb.toString());
    }

    public void setContentEncoding(List<ContentCodingType> list) {
        set("Content-Encoding", ContentCodingType.toString(list));
    }

    public void setContentEncoding(ContentCodingType contentCodingType) {
        setContentEncoding(Collections.singletonList(contentCodingType));
    }

    public void setContentLength(long j) {
        set("Content-Length", Long.toString(j));
    }

    public void setContentType(MediaType mediaType) {
        Assert.isTrue(!mediaType.isWildcardType(), "'Content-Type' cannot contain wildcard type '*'");
        Assert.isTrue(!mediaType.isWildcardSubtype(), "'Content-Type' cannot contain wildcard subtype '*'");
        set("Content-Type", mediaType.toString());
    }

    public void setDate(long j) {
        setDate("Date", j);
    }

    public void setDate(String str, long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMATS[0], Locale.US);
        simpleDateFormat.setTimeZone(GMT);
        set(str, simpleDateFormat.format(new Date(j)));
    }

    public void setETag(String str) {
        if (str != null) {
            Assert.isTrue(str.startsWith("\"") || str.startsWith("W/"), "Invalid eTag, does not start with W/ or \"");
            Assert.isTrue(str.endsWith("\""), "Invalid eTag, does not end with \"");
        }
        set("ETag", str);
    }

    public void setExpires(long j) {
        setDate("Expires", j);
    }

    public void setIfModifiedSince(long j) {
        setDate("If-Modified-Since", j);
    }

    public void setIfNoneMatch(String str) {
        set("If-None-Match", str);
    }

    public void setIfNoneMatch(List<String> list) {
        set("If-None-Match", toCommaDelimitedString(list));
    }

    public void setLastModified(long j) {
        setDate("Last-Modified", j);
    }

    public void setLocation(URI uri) {
        set("Location", uri.toASCIIString());
    }

    public void setOrigin(String str) {
        set(ORIGIN, str);
    }

    public void setPragma(String str) {
        set("Pragma", str);
    }

    public void setUpgrade(String str) {
        set("Upgrade", str);
    }

    public void setUserAgent(String str) {
        set("User-Agent", str);
    }

    @Override // java.util.Map
    public int size() {
        return this.headers.size();
    }

    protected String toCommaDelimitedString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override // org.springframework.util.MultiValueMap
    public Map<String, String> toSingleValueMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.headers.size());
        for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue().get(0));
        }
        return linkedHashMap;
    }

    public String toString() {
        return this.headers.toString();
    }

    @Override // java.util.Map
    public Collection<List<String>> values() {
        return this.headers.values();
    }
}
