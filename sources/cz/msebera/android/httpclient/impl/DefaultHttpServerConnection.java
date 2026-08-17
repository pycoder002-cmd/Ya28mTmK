package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.net.Socket;

@NotThreadSafe
@Deprecated
/* loaded from: classes.dex */
public class DefaultHttpServerConnection extends SocketHttpServerConnection {
    @Override // cz.msebera.android.httpclient.impl.SocketHttpServerConnection
    public void bind(Socket socket, HttpParams httpParams) throws IOException {
        Args.notNull(socket, "Socket");
        Args.notNull(httpParams, "HTTP parameters");
        assertNotOpen();
        socket.setTcpNoDelay(httpParams.getBooleanParameter("http.tcp.nodelay", true));
        socket.setSoTimeout(httpParams.getIntParameter("http.socket.timeout", 0));
        socket.setKeepAlive(httpParams.getBooleanParameter("http.socket.keepalive", false));
        int intParameter = httpParams.getIntParameter("http.socket.linger", -1);
        if (intParameter >= 0) {
            socket.setSoLinger(intParameter > 0, intParameter);
        }
        if (intParameter >= 0) {
            socket.setSoLinger(intParameter > 0, intParameter);
        }
        super.bind(socket, httpParams);
    }
}
