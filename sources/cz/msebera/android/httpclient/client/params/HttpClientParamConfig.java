package cz.msebera.android.httpclient.client.params;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.auth.params.AuthPNames;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;
import java.util.Collection;

@Deprecated
/* loaded from: classes.dex */
public final class HttpClientParamConfig {
    private HttpClientParamConfig() {
    }

    public static RequestConfig getRequestConfig(HttpParams httpParams) {
        return RequestConfig.custom().setSocketTimeout(httpParams.getIntParameter("http.socket.timeout", 0)).setStaleConnectionCheckEnabled(httpParams.getBooleanParameter("http.connection.stalecheck", true)).setConnectTimeout(httpParams.getIntParameter("http.connection.timeout", 0)).setExpectContinueEnabled(httpParams.getBooleanParameter("http.protocol.expect-continue", false)).setProxy((HttpHost) httpParams.getParameter(ConnRoutePNames.DEFAULT_PROXY)).setLocalAddress((InetAddress) httpParams.getParameter(ConnRoutePNames.LOCAL_ADDRESS)).setProxyPreferredAuthSchemes((Collection) httpParams.getParameter(AuthPNames.PROXY_AUTH_PREF)).setTargetPreferredAuthSchemes((Collection) httpParams.getParameter(AuthPNames.TARGET_AUTH_PREF)).setAuthenticationEnabled(httpParams.getBooleanParameter(ClientPNames.HANDLE_AUTHENTICATION, true)).setCircularRedirectsAllowed(httpParams.getBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false)).setConnectionRequestTimeout((int) httpParams.getLongParameter("http.conn-manager.timeout", 0L)).setCookieSpec((String) httpParams.getParameter(ClientPNames.COOKIE_POLICY)).setMaxRedirects(httpParams.getIntParameter(ClientPNames.MAX_REDIRECTS, 50)).setRedirectsEnabled(httpParams.getBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true)).setRelativeRedirectsAllowed(!httpParams.getBooleanParameter(ClientPNames.REJECT_RELATIVE_REDIRECT, false)).build();
    }
}
