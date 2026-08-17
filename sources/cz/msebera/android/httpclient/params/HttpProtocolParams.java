package cz.msebera.android.httpclient.params;

import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import java.nio.charset.CodingErrorAction;

@Deprecated
/* loaded from: classes.dex */
public final class HttpProtocolParams implements CoreProtocolPNames {
    private HttpProtocolParams() {
    }

    public static String getContentCharset(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        String str = (String) httpParams.getParameter("http.protocol.content-charset");
        return str == null ? HTTP.DEF_CONTENT_CHARSET.name() : str;
    }

    public static String getHttpElementCharset(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        String str = (String) httpParams.getParameter("http.protocol.element-charset");
        return str == null ? HTTP.DEF_PROTOCOL_CHARSET.name() : str;
    }

    public static CodingErrorAction getMalformedInputAction(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object parameter = httpParams.getParameter("http.malformed.input.action");
        return parameter == null ? CodingErrorAction.REPORT : (CodingErrorAction) parameter;
    }

    public static CodingErrorAction getUnmappableInputAction(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object parameter = httpParams.getParameter("http.unmappable.input.action");
        return parameter == null ? CodingErrorAction.REPORT : (CodingErrorAction) parameter;
    }

    public static String getUserAgent(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return (String) httpParams.getParameter("http.useragent");
    }

    public static ProtocolVersion getVersion(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object parameter = httpParams.getParameter("http.protocol.version");
        return parameter == null ? HttpVersion.HTTP_1_1 : (ProtocolVersion) parameter;
    }

    public static void setContentCharset(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.protocol.content-charset", str);
    }

    public static void setHttpElementCharset(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.protocol.element-charset", str);
    }

    public static void setMalformedInputAction(HttpParams httpParams, CodingErrorAction codingErrorAction) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.malformed.input.action", codingErrorAction);
    }

    public static void setUnmappableInputAction(HttpParams httpParams, CodingErrorAction codingErrorAction) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.unmappable.input.action", codingErrorAction);
    }

    public static void setUseExpectContinue(HttpParams httpParams, boolean z) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setBooleanParameter("http.protocol.expect-continue", z);
    }

    public static void setUserAgent(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.useragent", str);
    }

    public static void setVersion(HttpParams httpParams, ProtocolVersion protocolVersion) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.protocol.version", protocolVersion);
    }

    public static boolean useExpectContinue(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.protocol.expect-continue", false);
    }
}
