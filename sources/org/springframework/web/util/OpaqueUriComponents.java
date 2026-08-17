package org.springframework.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponents;

/* loaded from: classes2.dex */
final class OpaqueUriComponents extends UriComponents {
    private static final MultiValueMap<String, String> QUERY_PARAMS_NONE = new LinkedMultiValueMap(0);
    private final String ssp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpaqueUriComponents(String str, String str2, String str3) {
        super(str, str3);
        this.ssp = str2;
    }

    @Override // org.springframework.web.util.UriComponents
    public UriComponents encode(String str) throws UnsupportedEncodingException {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpaqueUriComponents)) {
            return false;
        }
        OpaqueUriComponents opaqueUriComponents = (OpaqueUriComponents) obj;
        return ObjectUtils.nullSafeEquals(getScheme(), opaqueUriComponents.getScheme()) && ObjectUtils.nullSafeEquals(this.ssp, opaqueUriComponents.ssp) && ObjectUtils.nullSafeEquals(getFragment(), opaqueUriComponents.getFragment());
    }

    @Override // org.springframework.web.util.UriComponents
    protected UriComponents expandInternal(UriComponents.UriTemplateVariables uriTemplateVariables) {
        return new OpaqueUriComponents(expandUriComponent(getScheme(), uriTemplateVariables), expandUriComponent(getSchemeSpecificPart(), uriTemplateVariables), expandUriComponent(getFragment(), uriTemplateVariables));
    }

    @Override // org.springframework.web.util.UriComponents
    public String getHost() {
        return null;
    }

    @Override // org.springframework.web.util.UriComponents
    public String getPath() {
        return null;
    }

    @Override // org.springframework.web.util.UriComponents
    public List<String> getPathSegments() {
        return Collections.emptyList();
    }

    @Override // org.springframework.web.util.UriComponents
    public int getPort() {
        return -1;
    }

    @Override // org.springframework.web.util.UriComponents
    public String getQuery() {
        return null;
    }

    @Override // org.springframework.web.util.UriComponents
    public MultiValueMap<String, String> getQueryParams() {
        return QUERY_PARAMS_NONE;
    }

    @Override // org.springframework.web.util.UriComponents
    public String getSchemeSpecificPart() {
        return this.ssp;
    }

    @Override // org.springframework.web.util.UriComponents
    public String getUserInfo() {
        return null;
    }

    public int hashCode() {
        return (31 * ((ObjectUtils.nullSafeHashCode(getScheme()) * 31) + ObjectUtils.nullSafeHashCode(this.ssp))) + ObjectUtils.nullSafeHashCode(getFragment());
    }

    @Override // org.springframework.web.util.UriComponents
    public UriComponents normalize() {
        return this;
    }

    @Override // org.springframework.web.util.UriComponents
    public URI toUri() {
        try {
            return new URI(getScheme(), this.ssp, getFragment());
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
        if (this.ssp != null) {
            sb.append(this.ssp);
        }
        if (getFragment() != null) {
            sb.append('#');
            sb.append(getFragment());
        }
        return sb.toString();
    }
}
