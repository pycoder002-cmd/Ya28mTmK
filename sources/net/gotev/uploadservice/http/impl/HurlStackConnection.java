package net.gotev.uploadservice.http.impl;

import android.os.Build;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import net.gotev.uploadservice.Logger;
import net.gotev.uploadservice.NameValue;
import net.gotev.uploadservice.UploadService;
import net.gotev.uploadservice.http.HttpConnection;

/* loaded from: classes2.dex */
public class HurlStackConnection implements HttpConnection {
    private static final String LOG_TAG = "HurlStackConnection";
    private HttpURLConnection mConnection;

    public HurlStackConnection(String str, String str2, boolean z, boolean z2, int i, int i2) throws IOException {
        Logger.debug(getClass().getSimpleName(), "creating new connection");
        URL url = new URL(str2);
        if (url.getProtocol().equals("https")) {
            this.mConnection = (HttpsURLConnection) url.openConnection();
        } else {
            this.mConnection = (HttpURLConnection) url.openConnection();
        }
        this.mConnection.setDoInput(true);
        this.mConnection.setDoOutput(true);
        this.mConnection.setConnectTimeout(i);
        this.mConnection.setReadTimeout(i2);
        this.mConnection.setUseCaches(z2);
        this.mConnection.setInstanceFollowRedirects(z);
        this.mConnection.setRequestMethod(str);
    }

    private byte[] getResponseBodyAsByteArray(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[UploadService.BUFFER_SIZE];
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, bArr.length);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Exception unused) {
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // net.gotev.uploadservice.http.HttpConnection
    public void close() {
        Logger.debug(getClass().getSimpleName(), "closing connection");
        if (this.mConnection != null) {
            try {
                this.mConnection.getInputStream().close();
            } catch (Exception unused) {
            }
            try {
                this.mConnection.getOutputStream().flush();
                this.mConnection.getOutputStream().close();
            } catch (Exception unused2) {
            }
            try {
                this.mConnection.disconnect();
            } catch (Exception e) {
                Logger.error(LOG_TAG, "Error while closing connection", e);
            }
        }
    }

    @Override // net.gotev.uploadservice.http.HttpConnection
    public byte[] getServerResponseBody() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = this.mConnection.getResponseCode() / 100 == 2 ? this.mConnection.getInputStream() : this.mConnection.getErrorStream();
            return getResponseBodyAsByteArray(inputStream);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    Logger.error(LOG_TAG, "Error while closing server response stream", e);
                }
            }
        }
    }

    @Override // net.gotev.uploadservice.http.HttpConnection
    public int getServerResponseCode() throws IOException {
        return this.mConnection.getResponseCode();
    }

    @Override // net.gotev.uploadservice.http.HttpConnection
    public LinkedHashMap<String, String> getServerResponseHeaders() throws IOException {
        Map<String, List<String>> headerFields = this.mConnection.getHeaderFields();
        if (headerFields == null) {
            return null;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(headerFields.size());
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            if (entry.getKey() != null) {
                StringBuilder sb = new StringBuilder();
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                }
                linkedHashMap.put(entry.getKey(), sb.toString());
            }
        }
        return linkedHashMap;
    }

    @Override // net.gotev.uploadservice.http.HttpConnection
    public void setHeaders(List<NameValue> list, boolean z, long j) throws IOException {
        if (!z) {
            this.mConnection.setChunkedStreamingMode(0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mConnection.setFixedLengthStreamingMode(j);
        } else {
            if (j > 2147483647L) {
                throw new RuntimeException("You need Android API version 19 or newer to upload more than 2GB in a single request using fixed size content length. Try switching to chunked mode instead, but make sure your server side supports it!");
            }
            this.mConnection.setFixedLengthStreamingMode((int) j);
        }
        for (NameValue nameValue : list) {
            this.mConnection.setRequestProperty(nameValue.getName(), nameValue.getValue());
        }
    }

    @Override // net.gotev.uploadservice.http.HttpConnection
    public void writeBody(byte[] bArr) throws IOException {
        this.mConnection.getOutputStream().write(bArr, 0, bArr.length);
    }

    @Override // net.gotev.uploadservice.http.HttpConnection
    public void writeBody(byte[] bArr, int i) throws IOException {
        this.mConnection.getOutputStream().write(bArr, 0, i);
    }
}
