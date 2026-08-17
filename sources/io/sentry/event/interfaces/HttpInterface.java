package io.sentry.event.interfaces;

import io.sentry.event.helper.BasicRemoteAddressResolver;
import io.sentry.event.helper.RemoteAddressResolver;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/* loaded from: classes2.dex */
public class HttpInterface implements SentryInterface {
    public static final String HTTP_INTERFACE = "sentry.interfaces.Http";
    private final boolean asyncStarted;
    private final String authType;
    private final String body;
    private final Map<String, String> cookies;
    private final Map<String, Collection<String>> headers;
    private final String localAddr;
    private final String localName;
    private final int localPort;
    private final String method;
    private final Map<String, Collection<String>> parameters;
    private final String protocol;
    private final String queryString;
    private final String remoteAddr;
    private final String remoteUser;
    private final String requestUrl;
    private final boolean secure;
    private final String serverName;
    private final int serverPort;

    public HttpInterface(HttpServletRequest httpServletRequest) {
        this(httpServletRequest, new BasicRemoteAddressResolver());
    }

    public HttpInterface(HttpServletRequest httpServletRequest, RemoteAddressResolver remoteAddressResolver) {
        this(httpServletRequest, remoteAddressResolver, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HttpInterface(HttpServletRequest httpServletRequest, RemoteAddressResolver remoteAddressResolver, String str) {
        this.requestUrl = httpServletRequest.getRequestURL().toString();
        this.method = httpServletRequest.getMethod();
        this.parameters = new HashMap();
        for (Map.Entry entry : httpServletRequest.getParameterMap().entrySet()) {
            this.parameters.put(entry.getKey(), Arrays.asList((Object[]) entry.getValue()));
        }
        this.queryString = httpServletRequest.getQueryString();
        if (httpServletRequest.getCookies() != null) {
            this.cookies = new HashMap();
            for (Cookie cookie : httpServletRequest.getCookies()) {
                this.cookies.put(cookie.getName(), cookie.getValue());
            }
        } else {
            this.cookies = Collections.emptyMap();
        }
        this.remoteAddr = remoteAddressResolver.getRemoteAddress(httpServletRequest);
        this.serverName = httpServletRequest.getServerName();
        this.serverPort = httpServletRequest.getServerPort();
        this.localAddr = httpServletRequest.getLocalAddr();
        this.localName = httpServletRequest.getLocalName();
        this.localPort = httpServletRequest.getLocalPort();
        this.protocol = httpServletRequest.getProtocol();
        this.secure = httpServletRequest.isSecure();
        this.asyncStarted = httpServletRequest.isAsyncStarted();
        this.authType = httpServletRequest.getAuthType();
        this.remoteUser = httpServletRequest.getRemoteUser();
        this.headers = new HashMap();
        Iterator it = Collections.list(httpServletRequest.getHeaderNames()).iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            this.headers.put(str2, Collections.list(httpServletRequest.getHeaders(str2)));
        }
        this.body = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpInterface httpInterface = (HttpInterface) obj;
        if (this.asyncStarted != httpInterface.asyncStarted || this.localPort != httpInterface.localPort || this.secure != httpInterface.secure || this.serverPort != httpInterface.serverPort) {
            return false;
        }
        if (this.authType == null ? httpInterface.authType != null : !this.authType.equals(httpInterface.authType)) {
            return false;
        }
        if (!this.cookies.equals(httpInterface.cookies) || !this.headers.equals(httpInterface.headers)) {
            return false;
        }
        if (this.localAddr == null ? httpInterface.localAddr != null : !this.localAddr.equals(httpInterface.localAddr)) {
            return false;
        }
        if (this.localName == null ? httpInterface.localName != null : !this.localName.equals(httpInterface.localName)) {
            return false;
        }
        if (this.method == null ? httpInterface.method != null : !this.method.equals(httpInterface.method)) {
            return false;
        }
        if (!this.parameters.equals(httpInterface.parameters)) {
            return false;
        }
        if (this.protocol == null ? httpInterface.protocol != null : !this.protocol.equals(httpInterface.protocol)) {
            return false;
        }
        if (this.queryString == null ? httpInterface.queryString != null : !this.queryString.equals(httpInterface.queryString)) {
            return false;
        }
        if (this.remoteAddr == null ? httpInterface.remoteAddr != null : !this.remoteAddr.equals(httpInterface.remoteAddr)) {
            return false;
        }
        if (this.remoteUser == null ? httpInterface.remoteUser != null : !this.remoteUser.equals(httpInterface.remoteUser)) {
            return false;
        }
        if (!this.requestUrl.equals(httpInterface.requestUrl)) {
            return false;
        }
        if (this.serverName == null ? httpInterface.serverName == null : this.serverName.equals(httpInterface.serverName)) {
            return this.body == null ? httpInterface.body == null : this.body.equals(httpInterface.body);
        }
        return false;
    }

    public String getAuthType() {
        return this.authType;
    }

    public String getBody() {
        return this.body;
    }

    public Map<String, String> getCookies() {
        return this.cookies;
    }

    public Map<String, Collection<String>> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override // io.sentry.event.interfaces.SentryInterface
    public String getInterfaceName() {
        return HTTP_INTERFACE;
    }

    public String getLocalAddr() {
        return this.localAddr;
    }

    public String getLocalName() {
        return this.localName;
    }

    public int getLocalPort() {
        return this.localPort;
    }

    public String getMethod() {
        return this.method;
    }

    public Map<String, Collection<String>> getParameters() {
        return Collections.unmodifiableMap(this.parameters);
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getRemoteAddr() {
        return this.remoteAddr;
    }

    public String getRemoteUser() {
        return this.remoteUser;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public String getServerName() {
        return this.serverName;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public int hashCode() {
        return (31 * ((this.requestUrl.hashCode() * 31) + (this.method != null ? this.method.hashCode() : 0))) + this.parameters.hashCode();
    }

    public boolean isAsyncStarted() {
        return this.asyncStarted;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public String toString() {
        return "HttpInterface{requestUrl='" + this.requestUrl + "', method='" + this.method + "', queryString='" + this.queryString + "', parameters=" + this.parameters + '}';
    }
}
