package org.springframework.web.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HierarchicalUriComponents;

/* loaded from: classes2.dex */
public class UriComponentsBuilder {
    private static final String HOST_IPV4_PATTERN = "[^\\[/?#:]*";
    private static final String HOST_IPV6_PATTERN = "\\[[\\p{XDigit}\\:\\.]*[%\\p{Alnum}]*\\]";
    private static final String HOST_PATTERN = "(\\[[\\p{XDigit}\\:\\.]*[%\\p{Alnum}]*\\]|[^\\[/?#:]*)";
    private static final String HTTP_PATTERN = "(?i)(http|https):";
    private static final String LAST_PATTERN = "(.*)";
    private static final String PATH_PATTERN = "([^?#]*)";
    private static final String PORT_PATTERN = "(\\d*(?:\\{[^/]+?\\})?)";
    private static final String QUERY_PATTERN = "([^#]*)";
    private static final String SCHEME_PATTERN = "([^:/?#]+):";
    private static final String USERINFO_PATTERN = "([^@\\[/?#]*)";
    private String fragment;
    private String host;
    private String port;
    private String scheme;
    private String ssp;
    private String userInfo;
    private static final Pattern QUERY_PARAM_PATTERN = Pattern.compile("([^&=]+)(=?)([^&]+)?");
    private static final Pattern URI_PATTERN = Pattern.compile("^(([^:/?#]+):)?(//(([^@\\[/?#]*)@)?(\\[[\\p{XDigit}\\:\\.]*[%\\p{Alnum}]*\\]|[^\\[/?#:]*)(:(\\d*(?:\\{[^/]+?\\})?))?)?([^?#]*)(\\?([^#]*))?(#(.*))?");
    private static final Pattern HTTP_URL_PATTERN = Pattern.compile("^(?i)(http|https):(//(([^@\\[/?#]*)@)?(\\[[\\p{XDigit}\\:\\.]*[%\\p{Alnum}]*\\]|[^\\[/?#:]*)(:(\\d*(?:\\{[^/]+?\\})?))?)?([^?#]*)(\\?(.*))?");
    private CompositePathComponentBuilder pathBuilder = new CompositePathComponentBuilder();
    private final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CompositePathComponentBuilder implements PathComponentBuilder {
        private final LinkedList<PathComponentBuilder> componentBuilders = new LinkedList<>();

        public CompositePathComponentBuilder() {
        }

        public CompositePathComponentBuilder(String str) {
            addPath(str);
        }

        private <T> T getLastBuilder(Class<T> cls) {
            if (this.componentBuilders.isEmpty()) {
                return null;
            }
            T t = (T) this.componentBuilders.getLast();
            if (cls.isInstance(t)) {
                return t;
            }
            return null;
        }

        public void addPath(String str) {
            if (StringUtils.hasText(str)) {
                PathSegmentComponentBuilder pathSegmentComponentBuilder = (PathSegmentComponentBuilder) getLastBuilder(PathSegmentComponentBuilder.class);
                FullPathComponentBuilder fullPathComponentBuilder = (FullPathComponentBuilder) getLastBuilder(FullPathComponentBuilder.class);
                if (pathSegmentComponentBuilder != null && !str.startsWith("/")) {
                    str = "/" + str;
                }
                if (fullPathComponentBuilder == null) {
                    fullPathComponentBuilder = new FullPathComponentBuilder();
                    this.componentBuilders.add(fullPathComponentBuilder);
                }
                fullPathComponentBuilder.append(str);
            }
        }

        public void addPathSegments(String... strArr) {
            if (ObjectUtils.isEmpty(strArr)) {
                return;
            }
            PathSegmentComponentBuilder pathSegmentComponentBuilder = (PathSegmentComponentBuilder) getLastBuilder(PathSegmentComponentBuilder.class);
            FullPathComponentBuilder fullPathComponentBuilder = (FullPathComponentBuilder) getLastBuilder(FullPathComponentBuilder.class);
            if (pathSegmentComponentBuilder == null) {
                pathSegmentComponentBuilder = new PathSegmentComponentBuilder();
                this.componentBuilders.add(pathSegmentComponentBuilder);
                if (fullPathComponentBuilder != null) {
                    fullPathComponentBuilder.removeTrailingSlash();
                }
            }
            pathSegmentComponentBuilder.append(strArr);
        }

        @Override // org.springframework.web.util.UriComponentsBuilder.PathComponentBuilder
        public HierarchicalUriComponents.PathComponent build() {
            ArrayList arrayList = new ArrayList(this.componentBuilders.size());
            Iterator<PathComponentBuilder> it = this.componentBuilders.iterator();
            while (it.hasNext()) {
                HierarchicalUriComponents.PathComponent build = it.next().build();
                if (build != null) {
                    arrayList.add(build);
                }
            }
            return arrayList.isEmpty() ? HierarchicalUriComponents.NULL_PATH_COMPONENT : arrayList.size() == 1 ? (HierarchicalUriComponents.PathComponent) arrayList.get(0) : new HierarchicalUriComponents.PathComponentComposite(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FullPathComponentBuilder implements PathComponentBuilder {
        private final StringBuilder path;

        private FullPathComponentBuilder() {
            this.path = new StringBuilder();
        }

        public void append(String str) {
            this.path.append(str);
        }

        @Override // org.springframework.web.util.UriComponentsBuilder.PathComponentBuilder
        public HierarchicalUriComponents.PathComponent build() {
            if (this.path.length() == 0) {
                return null;
            }
            String sb = this.path.toString();
            while (true) {
                int indexOf = sb.indexOf("//");
                if (indexOf == -1) {
                    return new HierarchicalUriComponents.FullPathComponent(sb);
                }
                sb = sb.substring(0, indexOf) + sb.substring(indexOf + 1);
            }
        }

        public void removeTrailingSlash() {
            int length = this.path.length() - 1;
            if (this.path.charAt(length) == '/') {
                this.path.deleteCharAt(length);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface PathComponentBuilder {
        HierarchicalUriComponents.PathComponent build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PathSegmentComponentBuilder implements PathComponentBuilder {
        private final List<String> pathSegments;

        private PathSegmentComponentBuilder() {
            this.pathSegments = new LinkedList();
        }

        public void append(String... strArr) {
            for (String str : strArr) {
                if (StringUtils.hasText(str)) {
                    this.pathSegments.add(str);
                }
            }
        }

        @Override // org.springframework.web.util.UriComponentsBuilder.PathComponentBuilder
        public HierarchicalUriComponents.PathComponent build() {
            if (this.pathSegments.isEmpty()) {
                return null;
            }
            return new HierarchicalUriComponents.PathSegmentComponent(this.pathSegments);
        }
    }

    protected UriComponentsBuilder() {
    }

    public static UriComponentsBuilder fromHttpUrl(String str) {
        Assert.notNull(str, "'httpUrl' must not be null");
        Matcher matcher = HTTP_URL_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[" + str + "] is not a valid HTTP URL");
        }
        UriComponentsBuilder uriComponentsBuilder = new UriComponentsBuilder();
        String group = matcher.group(1);
        uriComponentsBuilder.scheme(group != null ? group.toLowerCase() : null);
        uriComponentsBuilder.userInfo(matcher.group(4));
        String group2 = matcher.group(5);
        if (StringUtils.hasLength(group) && !StringUtils.hasLength(group2)) {
            throw new IllegalArgumentException("[" + str + "] is not a valid HTTP URL");
        }
        uriComponentsBuilder.host(group2);
        String group3 = matcher.group(7);
        if (StringUtils.hasLength(group3)) {
            uriComponentsBuilder.port(group3);
        }
        uriComponentsBuilder.path(matcher.group(8));
        uriComponentsBuilder.query(matcher.group(10));
        return uriComponentsBuilder;
    }

    public static UriComponentsBuilder fromPath(String str) {
        UriComponentsBuilder uriComponentsBuilder = new UriComponentsBuilder();
        uriComponentsBuilder.path(str);
        return uriComponentsBuilder;
    }

    public static UriComponentsBuilder fromUri(URI uri) {
        UriComponentsBuilder uriComponentsBuilder = new UriComponentsBuilder();
        uriComponentsBuilder.uri(uri);
        return uriComponentsBuilder;
    }

    public static UriComponentsBuilder fromUriString(String str) {
        Assert.hasLength(str, "'uri' must not be empty");
        Matcher matcher = URI_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[" + str + "] is not a valid URI");
        }
        UriComponentsBuilder uriComponentsBuilder = new UriComponentsBuilder();
        String group = matcher.group(2);
        String group2 = matcher.group(5);
        String group3 = matcher.group(6);
        String group4 = matcher.group(8);
        String group5 = matcher.group(9);
        String group6 = matcher.group(11);
        String group7 = matcher.group(13);
        boolean z = StringUtils.hasLength(group) && !str.substring(group.length()).startsWith(":/");
        uriComponentsBuilder.scheme(group);
        if (z) {
            String substring = str.substring(group.length()).substring(1);
            if (StringUtils.hasLength(group7)) {
                substring = substring.substring(0, substring.length() - (group7.length() + 1));
            }
            uriComponentsBuilder.schemeSpecificPart(substring);
        } else {
            uriComponentsBuilder.userInfo(group2);
            uriComponentsBuilder.host(group3);
            if (StringUtils.hasLength(group4)) {
                uriComponentsBuilder.port(group4);
            }
            uriComponentsBuilder.path(group5);
            uriComponentsBuilder.query(group6);
        }
        if (StringUtils.hasText(group7)) {
            uriComponentsBuilder.fragment(group7);
        }
        return uriComponentsBuilder;
    }

    public static UriComponentsBuilder newInstance() {
        return new UriComponentsBuilder();
    }

    private void resetHierarchicalComponents() {
        this.userInfo = null;
        this.host = null;
        this.port = null;
        this.pathBuilder = new CompositePathComponentBuilder();
        this.queryParams.clear();
    }

    private void resetSchemeSpecificPart() {
        this.ssp = null;
    }

    public UriComponents build() {
        return build(false);
    }

    public UriComponents build(boolean z) {
        return this.ssp != null ? new OpaqueUriComponents(this.scheme, this.ssp, this.fragment) : new HierarchicalUriComponents(this.scheme, this.userInfo, this.host, this.port, this.pathBuilder.build(), this.queryParams, this.fragment, z, true);
    }

    public UriComponents buildAndExpand(Map<String, ?> map) {
        return build(false).expand(map);
    }

    public UriComponents buildAndExpand(Object... objArr) {
        return build(false).expand(objArr);
    }

    public UriComponentsBuilder fragment(String str) {
        if (str != null) {
            Assert.hasLength(str, "'fragment' must not be empty");
            this.fragment = str;
        } else {
            this.fragment = null;
        }
        return this;
    }

    public UriComponentsBuilder host(String str) {
        this.host = str;
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder path(String str) {
        this.pathBuilder.addPath(str);
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder pathSegment(String... strArr) throws IllegalArgumentException {
        Assert.notNull(strArr, "'segments' must not be null");
        this.pathBuilder.addPathSegments(strArr);
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder port(int i) {
        Assert.isTrue(i >= -1, "'port' must not be < -1");
        this.port = String.valueOf(i);
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder port(String str) {
        this.port = str;
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder query(String str) {
        if (str != null) {
            Matcher matcher = QUERY_PARAM_PATTERN.matcher(str);
            while (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                Object[] objArr = new Object[1];
                if (group3 == null) {
                    group3 = StringUtils.hasLength(group2) ? "" : null;
                }
                objArr[0] = group3;
                queryParam(group, objArr);
            }
        } else {
            this.queryParams.clear();
        }
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder queryParam(String str, Object... objArr) {
        Assert.notNull(str, "'name' must not be null");
        if (ObjectUtils.isEmpty(objArr)) {
            this.queryParams.add(str, null);
        } else {
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                this.queryParams.add(str, obj != null ? obj.toString() : null);
            }
        }
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder queryParams(MultiValueMap<String, String> multiValueMap) {
        Assert.notNull(multiValueMap, "'params' must not be null");
        this.queryParams.putAll(multiValueMap);
        return this;
    }

    public UriComponentsBuilder replacePath(String str) {
        this.pathBuilder = new CompositePathComponentBuilder(str);
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder replaceQuery(String str) {
        this.queryParams.clear();
        query(str);
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder replaceQueryParam(String str, Object... objArr) {
        Assert.notNull(str, "'name' must not be null");
        this.queryParams.remove(str);
        if (!ObjectUtils.isEmpty(objArr)) {
            queryParam(str, objArr);
        }
        resetSchemeSpecificPart();
        return this;
    }

    public UriComponentsBuilder scheme(String str) {
        this.scheme = str;
        return this;
    }

    public UriComponentsBuilder schemeSpecificPart(String str) {
        this.ssp = str;
        resetHierarchicalComponents();
        return this;
    }

    public String toUriString() {
        return build(false).encode().toUriString();
    }

    public UriComponentsBuilder uri(URI uri) {
        Assert.notNull(uri, "'uri' must not be null");
        this.scheme = uri.getScheme();
        if (uri.isOpaque()) {
            this.ssp = uri.getRawSchemeSpecificPart();
            resetHierarchicalComponents();
        } else {
            if (uri.getRawUserInfo() != null) {
                this.userInfo = uri.getRawUserInfo();
            }
            if (uri.getHost() != null) {
                this.host = uri.getHost();
            }
            if (uri.getPort() != -1) {
                this.port = String.valueOf(uri.getPort());
            }
            if (StringUtils.hasLength(uri.getRawPath())) {
                this.pathBuilder = new CompositePathComponentBuilder(uri.getRawPath());
            }
            if (StringUtils.hasLength(uri.getRawQuery())) {
                this.queryParams.clear();
                query(uri.getRawQuery());
            }
            resetSchemeSpecificPart();
        }
        if (uri.getRawFragment() != null) {
            this.fragment = uri.getRawFragment();
        }
        return this;
    }

    public UriComponentsBuilder uriComponents(UriComponents uriComponents) {
        Assert.notNull(uriComponents, "'uriComponents' must not be null");
        this.scheme = uriComponents.getScheme();
        if (uriComponents instanceof OpaqueUriComponents) {
            this.ssp = uriComponents.getSchemeSpecificPart();
            resetHierarchicalComponents();
        } else {
            if (uriComponents.getUserInfo() != null) {
                this.userInfo = uriComponents.getUserInfo();
            }
            if (uriComponents.getHost() != null) {
                this.host = uriComponents.getHost();
            }
            if (uriComponents.getPort() != -1) {
                this.port = String.valueOf(uriComponents.getPort());
            }
            if (StringUtils.hasLength(uriComponents.getPath())) {
                List<String> pathSegments = uriComponents.getPathSegments();
                if (pathSegments.isEmpty()) {
                    this.pathBuilder.addPath(uriComponents.getPath());
                } else {
                    this.pathBuilder.addPathSegments((String[]) pathSegments.toArray(new String[pathSegments.size()]));
                }
            }
            if (!uriComponents.getQueryParams().isEmpty()) {
                this.queryParams.clear();
                this.queryParams.putAll(uriComponents.getQueryParams());
            }
            resetSchemeSpecificPart();
        }
        if (uriComponents.getFragment() != null) {
            this.fragment = uriComponents.getFragment();
        }
        return this;
    }

    public UriComponentsBuilder userInfo(String str) {
        this.userInfo = str;
        resetSchemeSpecificPart();
        return this;
    }
}
