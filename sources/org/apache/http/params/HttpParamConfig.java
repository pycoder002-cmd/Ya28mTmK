package org.apache.http.params;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.SocketConfig;

@Deprecated
/* loaded from: classes2.dex */
public final class HttpParamConfig {
    private HttpParamConfig() {
    }

    public static ConnectionConfig getConnectionConfig(HttpParams httpParams) {
        MessageConstraints messageConstraints = getMessageConstraints(httpParams);
        String str = (String) httpParams.getParameter("http.protocol.element-charset");
        return ConnectionConfig.custom().setCharset(str != null ? Charset.forName(str) : null).setMalformedInputAction((CodingErrorAction) httpParams.getParameter("http.malformed.input.action")).setMalformedInputAction((CodingErrorAction) httpParams.getParameter("http.unmappable.input.action")).setMessageConstraints(messageConstraints).build();
    }

    public static MessageConstraints getMessageConstraints(HttpParams httpParams) {
        return MessageConstraints.custom().setMaxHeaderCount(httpParams.getIntParameter("http.connection.max-header-count", -1)).setMaxLineLength(httpParams.getIntParameter("http.connection.max-line-length", -1)).build();
    }

    public static SocketConfig getSocketConfig(HttpParams httpParams) {
        return SocketConfig.custom().setSoTimeout(httpParams.getIntParameter("http.socket.timeout", 0)).setSoReuseAddress(httpParams.getBooleanParameter("http.socket.reuseaddr", false)).setSoKeepAlive(httpParams.getBooleanParameter("http.socket.keepalive", false)).setSoLinger(httpParams.getIntParameter("http.socket.linger", -1)).setTcpNoDelay(httpParams.getBooleanParameter("http.tcp.nodelay", true)).build();
    }
}
