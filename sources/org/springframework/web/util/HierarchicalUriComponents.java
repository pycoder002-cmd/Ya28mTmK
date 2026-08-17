package org.springframework.web.util;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;

/* loaded from: classes2.dex */
final class HierarchicalUriComponents extends UriComponents {
    static final PathComponent NULL_PATH_COMPONENT = new PathComponent() { // from class: org.springframework.web.util.HierarchicalUriComponents.1
        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent encode(String str) throws UnsupportedEncodingException {
            return this;
        }

        public boolean equals(Object obj) {
            return this == obj;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent expand(UriComponents.UriTemplateVariables uriTemplateVariables) {
            return this;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public String getPath() {
            return null;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public List<String> getPathSegments() {
            return Collections.emptyList();
        }

        public int hashCode() {
            return 42;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public void verify() {
        }
    };
    private static final char PATH_DELIMITER = '/';
    private final boolean encoded;
    private final String host;
    private final PathComponent path;
    private final String port;
    private final MultiValueMap<String, String> queryParams;
    private final String userInfo;

    /* loaded from: classes2.dex */
    static final class FullPathComponent implements PathComponent {
        private final String path;

        public FullPathComponent(String str) {
            this.path = str;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent encode(String str) throws UnsupportedEncodingException {
            return new FullPathComponent(HierarchicalUriComponents.encodeUriComponent(getPath(), str, Type.PATH));
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof FullPathComponent) && getPath().equals(((FullPathComponent) obj).getPath()));
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent expand(UriComponents.UriTemplateVariables uriTemplateVariables) {
            return new FullPathComponent(UriComponents.expandUriComponent(getPath(), uriTemplateVariables));
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public String getPath() {
            return this.path;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public List<String> getPathSegments() {
            return Collections.unmodifiableList(Arrays.asList(StringUtils.tokenizeToStringArray(this.path, new String(new char[]{HierarchicalUriComponents.PATH_DELIMITER}))));
        }

        public int hashCode() {
            return getPath().hashCode();
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public void verify() {
            HierarchicalUriComponents.verifyUriComponent(this.path, Type.PATH);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface PathComponent extends Serializable {
        PathComponent encode(String str) throws UnsupportedEncodingException;

        PathComponent expand(UriComponents.UriTemplateVariables uriTemplateVariables);

        String getPath();

        List<String> getPathSegments();

        void verify();
    }

    /* loaded from: classes2.dex */
    static final class PathComponentComposite implements PathComponent {
        private final List<PathComponent> pathComponents;

        public PathComponentComposite(List<PathComponent> list) {
            this.pathComponents = list;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent encode(String str) throws UnsupportedEncodingException {
            ArrayList arrayList = new ArrayList(this.pathComponents.size());
            Iterator<PathComponent> it = this.pathComponents.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().encode(str));
            }
            return new PathComponentComposite(arrayList);
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent expand(UriComponents.UriTemplateVariables uriTemplateVariables) {
            ArrayList arrayList = new ArrayList(this.pathComponents.size());
            Iterator<PathComponent> it = this.pathComponents.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().expand(uriTemplateVariables));
            }
            return new PathComponentComposite(arrayList);
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public String getPath() {
            StringBuilder sb = new StringBuilder();
            Iterator<PathComponent> it = this.pathComponents.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getPath());
            }
            return sb.toString();
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public List<String> getPathSegments() {
            ArrayList arrayList = new ArrayList();
            Iterator<PathComponent> it = this.pathComponents.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().getPathSegments());
            }
            return arrayList;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public void verify() {
            Iterator<PathComponent> it = this.pathComponents.iterator();
            while (it.hasNext()) {
                it.next().verify();
            }
        }
    }

    /* loaded from: classes2.dex */
    static final class PathSegmentComponent implements PathComponent {
        private final List<String> pathSegments;

        public PathSegmentComponent(List<String> list) {
            this.pathSegments = Collections.unmodifiableList(new ArrayList(list));
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent encode(String str) throws UnsupportedEncodingException {
            List<String> pathSegments = getPathSegments();
            ArrayList arrayList = new ArrayList(pathSegments.size());
            Iterator<String> it = pathSegments.iterator();
            while (it.hasNext()) {
                arrayList.add(HierarchicalUriComponents.encodeUriComponent(it.next(), str, Type.PATH_SEGMENT));
            }
            return new PathSegmentComponent(arrayList);
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof PathSegmentComponent) && getPathSegments().equals(((PathSegmentComponent) obj).getPathSegments()));
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent expand(UriComponents.UriTemplateVariables uriTemplateVariables) {
            List<String> pathSegments = getPathSegments();
            ArrayList arrayList = new ArrayList(pathSegments.size());
            Iterator<String> it = pathSegments.iterator();
            while (it.hasNext()) {
                arrayList.add(UriComponents.expandUriComponent(it.next(), uriTemplateVariables));
            }
            return new PathSegmentComponent(arrayList);
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public String getPath() {
            StringBuilder sb = new StringBuilder();
            sb.append(HierarchicalUriComponents.PATH_DELIMITER);
            Iterator<String> it = this.pathSegments.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append(HierarchicalUriComponents.PATH_DELIMITER);
                }
            }
            return sb.toString();
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public List<String> getPathSegments() {
            return this.pathSegments;
        }

        public int hashCode() {
            return getPathSegments().hashCode();
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public void verify() {
            Iterator<String> it = getPathSegments().iterator();
            while (it.hasNext()) {
                HierarchicalUriComponents.verifyUriComponent(it.next(), Type.PATH_SEGMENT);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum Type {
        SCHEME { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.1
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isAlpha(i) || isDigit(i) || 43 == i || 45 == i || 46 == i;
            }
        },
        AUTHORITY { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.2
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isUnreserved(i) || isSubDelimiter(i) || 58 == i || 64 == i;
            }
        },
        USER_INFO { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.3
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isUnreserved(i) || isSubDelimiter(i) || 58 == i;
            }
        },
        HOST_IPV4 { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.4
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isUnreserved(i) || isSubDelimiter(i);
            }
        },
        HOST_IPV6 { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.5
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isUnreserved(i) || isSubDelimiter(i) || 91 == i || 93 == i || 58 == i;
            }
        },
        PORT { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.6
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isDigit(i);
            }
        },
        PATH { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.7
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isPchar(i) || 47 == i;
            }
        },
        PATH_SEGMENT { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.8
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isPchar(i);
            }
        },
        QUERY { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.9
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isPchar(i) || 47 == i || 63 == i;
            }
        },
        QUERY_PARAM { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.10
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                if (61 == i || 43 == i || 38 == i) {
                    return false;
                }
                return isPchar(i) || 47 == i || 63 == i;
            }
        },
        FRAGMENT { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.11
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(int i) {
                return isPchar(i) || 47 == i || 63 == i;
            }
        };

        public abstract boolean isAllowed(int i);

        protected boolean isAlpha(int i) {
            return (i >= 97 && i <= 122) || (i >= 65 && i <= 90);
        }

        protected boolean isDigit(int i) {
            return i >= 48 && i <= 57;
        }

        protected boolean isGenericDelimiter(int i) {
            return 58 == i || 47 == i || 63 == i || 35 == i || 91 == i || 93 == i || 64 == i;
        }

        protected boolean isPchar(int i) {
            return isUnreserved(i) || isSubDelimiter(i) || 58 == i || 64 == i;
        }

        protected boolean isReserved(char c) {
            return isGenericDelimiter(c) || isReserved(c);
        }

        protected boolean isSubDelimiter(int i) {
            return 33 == i || 36 == i || 38 == i || 39 == i || 40 == i || 41 == i || 42 == i || 43 == i || 44 == i || 59 == i || 61 == i;
        }

        protected boolean isUnreserved(int i) {
            return isAlpha(i) || isDigit(i) || 45 == i || 46 == i || 95 == i || 126 == i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HierarchicalUriComponents(String str, String str2, String str3, String str4, PathComponent pathComponent, MultiValueMap<String, String> multiValueMap, String str5, boolean z, boolean z2) {
        super(str, str5);
        this.userInfo = str2;
        this.host = str3;
        this.port = str4;
        this.path = pathComponent == null ? NULL_PATH_COMPONENT : pathComponent;
        this.queryParams = CollectionUtils.unmodifiableMultiValueMap(multiValueMap == null ? new LinkedMultiValueMap<>(0) : multiValueMap);
        this.encoded = z;
        if (z2) {
            verify();
        }
    }

    private static byte[] encodeBytes(byte[] bArr, Type type) {
        Assert.notNull(bArr, "Source must not be null");
        Assert.notNull(type, "Type must not be null");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            if (b < 0) {
                b = (byte) (b + FileDownloadStatus.INVALID_STATUS);
            }
            if (type.isAllowed(b)) {
                byteArrayOutputStream.write(b);
            } else {
                byteArrayOutputStream.write(37);
                char upperCase = Character.toUpperCase(Character.forDigit((b >> 4) & 15, 16));
                char upperCase2 = Character.toUpperCase(Character.forDigit(b & 15, 16));
                byteArrayOutputStream.write(upperCase);
                byteArrayOutputStream.write(upperCase2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encodeUriComponent(String str, String str2, Type type) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        Assert.hasLength(str2, "Encoding must not be empty");
        return new String(encodeBytes(str.getBytes(str2), type), "US-ASCII");
    }

    private Type getHostType() {
        return (this.host == null || !this.host.startsWith("[")) ? Type.HOST_IPV4 : Type.HOST_IPV6;
    }

    private void verify() {
        if (this.encoded) {
            verifyUriComponent(getScheme(), Type.SCHEME);
            verifyUriComponent(this.userInfo, Type.USER_INFO);
            verifyUriComponent(this.host, getHostType());
            this.path.verify();
            for (Map.Entry<String, String> entry : this.queryParams.entrySet()) {
                verifyUriComponent(entry.getKey(), Type.QUERY_PARAM);
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    verifyUriComponent((String) it.next(), Type.QUERY_PARAM);
                }
            }
            verifyUriComponent(getFragment(), Type.FRAGMENT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void verifyUriComponent(String str, Type type) {
        if (str == null) {
            return;
        }
        int length = str.length();
        int i = 0;
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
                i = i2;
            } else if (!type.isAllowed(charAt)) {
                throw new IllegalArgumentException("Invalid character '" + charAt + "' for " + type.name() + " in \"" + str + "\"");
            }
            i++;
        }
    }

    @Override // org.springframework.web.util.UriComponents
    public HierarchicalUriComponents encode(String str) throws UnsupportedEncodingException {
        Assert.hasLength(str, "Encoding must not be empty");
        if (this.encoded) {
            return this;
        }
        String encodeUriComponent = encodeUriComponent(getScheme(), str, Type.SCHEME);
        String encodeUriComponent2 = encodeUriComponent(this.userInfo, str, Type.USER_INFO);
        String encodeUriComponent3 = encodeUriComponent(this.host, str, getHostType());
        PathComponent encode = this.path.encode(str);
        LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap(this.queryParams.size());
        for (Map.Entry<String, String> entry : this.queryParams.entrySet()) {
            String encodeUriComponent4 = encodeUriComponent(entry.getKey(), str, Type.QUERY_PARAM);
            ArrayList arrayList = new ArrayList(((List) entry.getValue()).size());
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                arrayList.add(encodeUriComponent((String) it.next(), str, Type.QUERY_PARAM));
            }
            linkedMultiValueMap.put((LinkedMultiValueMap) encodeUriComponent4, (String) arrayList);
        }
        return new HierarchicalUriComponents(encodeUriComponent, encodeUriComponent2, encodeUriComponent3, this.port, encode, linkedMultiValueMap, encodeUriComponent(getFragment(), str, Type.FRAGMENT), true, false);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HierarchicalUriComponents)) {
            return false;
        }
        HierarchicalUriComponents hierarchicalUriComponents = (HierarchicalUriComponents) obj;
        return ObjectUtils.nullSafeEquals(getScheme(), hierarchicalUriComponents.getScheme()) && ObjectUtils.nullSafeEquals(getUserInfo(), hierarchicalUriComponents.getUserInfo()) && ObjectUtils.nullSafeEquals(getHost(), hierarchicalUriComponents.getHost()) && getPort() == hierarchicalUriComponents.getPort() && this.path.equals(hierarchicalUriComponents.path) && this.queryParams.equals(hierarchicalUriComponents.queryParams) && ObjectUtils.nullSafeEquals(getFragment(), hierarchicalUriComponents.getFragment());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.web.util.UriComponents
    public HierarchicalUriComponents expandInternal(UriComponents.UriTemplateVariables uriTemplateVariables) {
        Assert.state(!this.encoded, "Cannot expand an already encoded UriComponents object");
        String expandUriComponent = expandUriComponent(getScheme(), uriTemplateVariables);
        String expandUriComponent2 = expandUriComponent(this.userInfo, uriTemplateVariables);
        String expandUriComponent3 = expandUriComponent(this.host, uriTemplateVariables);
        String expandUriComponent4 = expandUriComponent(this.port, uriTemplateVariables);
        PathComponent expand = this.path.expand(uriTemplateVariables);
        LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap(this.queryParams.size());
        for (Map.Entry<String, String> entry : this.queryParams.entrySet()) {
            String expandUriComponent5 = expandUriComponent(entry.getKey(), uriTemplateVariables);
            ArrayList arrayList = new ArrayList(((List) entry.getValue()).size());
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                arrayList.add(expandUriComponent((String) it.next(), uriTemplateVariables));
            }
            linkedMultiValueMap.put((LinkedMultiValueMap) expandUriComponent5, (String) arrayList);
        }
        return new HierarchicalUriComponents(expandUriComponent, expandUriComponent2, expandUriComponent3, expandUriComponent4, expand, linkedMultiValueMap, expandUriComponent(getFragment(), uriTemplateVariables), false, false);
    }

    @Override // org.springframework.web.util.UriComponents
    public String getHost() {
        return this.host;
    }

    @Override // org.springframework.web.util.UriComponents
    public String getPath() {
        return this.path.getPath();
    }

    @Override // org.springframework.web.util.UriComponents
    public List<String> getPathSegments() {
        return this.path.getPathSegments();
    }

    @Override // org.springframework.web.util.UriComponents
    public int getPort() {
        if (this.port == null) {
            return -1;
        }
        if (!this.port.contains("{")) {
            return Integer.parseInt(this.port);
        }
        throw new IllegalStateException("The port contains a URI variable but has not been expanded yet: " + this.port);
    }

    @Override // org.springframework.web.util.UriComponents
    public String getQuery() {
        if (this.queryParams.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.queryParams.entrySet()) {
            String key = entry.getKey();
            List list = (List) entry.getValue();
            if (CollectionUtils.isEmpty(list)) {
                if (sb.length() != 0) {
                    sb.append(Typography.amp);
                }
                sb.append(key);
            } else {
                for (Object obj : list) {
                    if (sb.length() != 0) {
                        sb.append(Typography.amp);
                    }
                    sb.append(key);
                    if (obj != null) {
                        sb.append('=');
                        sb.append(obj.toString());
                    }
                }
            }
        }
        return sb.toString();
    }

    @Override // org.springframework.web.util.UriComponents
    public MultiValueMap<String, String> getQueryParams() {
        return this.queryParams;
    }

    @Override // org.springframework.web.util.UriComponents
    public String getSchemeSpecificPart() {
        return null;
    }

    @Override // org.springframework.web.util.UriComponents
    public String getUserInfo() {
        return this.userInfo;
    }

    public int hashCode() {
        return (31 * ((((((((((ObjectUtils.nullSafeHashCode(getScheme()) * 31) + ObjectUtils.nullSafeHashCode(this.userInfo)) * 31) + ObjectUtils.nullSafeHashCode(this.host)) * 31) + ObjectUtils.nullSafeHashCode(this.port)) * 31) + this.path.hashCode()) * 31) + this.queryParams.hashCode())) + ObjectUtils.nullSafeHashCode(getFragment());
    }

    @Override // org.springframework.web.util.UriComponents
    public UriComponents normalize() {
        return new HierarchicalUriComponents(getScheme(), this.userInfo, this.host, this.port, new FullPathComponent(StringUtils.cleanPath(getPath())), this.queryParams, getFragment(), this.encoded, false);
    }

    @Override // org.springframework.web.util.UriComponents
    public URI toUri() {
        try {
            if (this.encoded) {
                return new URI(toString());
            }
            String path = getPath();
            if (StringUtils.hasLength(path) && path.charAt(0) != '/' && (getScheme() != null || getUserInfo() != null || getHost() != null || getPort() != -1)) {
                path = PATH_DELIMITER + path;
            }
            return new URI(getScheme(), getUserInfo(), getHost(), getPort(), path, getQuery(), getFragment());
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Could not create URI object: " + e.getMessage(), e);
        }
    }

    @Override // org.springframework.web.util.UriComponents
    public String toUriString() {
        StringBuilder sb = new StringBuilder();
        if (getScheme() != null) {
            sb.append(getScheme());
            sb.append(':');
        }
        if (this.userInfo != null || this.host != null) {
            sb.append("//");
            if (this.userInfo != null) {
                sb.append(this.userInfo);
                sb.append('@');
            }
            if (this.host != null) {
                sb.append(this.host);
            }
            if (getPort() != -1) {
                sb.append(':');
                sb.append(this.port);
            }
        }
        String path = getPath();
        if (StringUtils.hasLength(path)) {
            if (sb.length() != 0 && path.charAt(0) != '/') {
                sb.append(PATH_DELIMITER);
            }
            sb.append(path);
        }
        String query = getQuery();
        if (query != null) {
            sb.append('?');
            sb.append(query);
        }
        if (getFragment() != null) {
            sb.append('#');
            sb.append(getFragment());
        }
        return sb.toString();
    }
}
