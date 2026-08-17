package com.downloader.httpclient;

import com.downloader.request.DownloadRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class DefaultHttpClient implements HttpClient {
    private URLConnection connection;

    private void addHeaders(DownloadRequest downloadRequest) {
        HashMap<String, List<String>> headers = downloadRequest.getHeaders();
        if (headers != null) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (value != null) {
                    Iterator<String> it = value.iterator();
                    while (it.hasNext()) {
                        this.connection.addRequestProperty(key, it.next());
                    }
                }
            }
        }
    }

    @Override // com.downloader.httpclient.HttpClient
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public HttpClient m7clone() {
        return new DefaultHttpClient();
    }

    @Override // com.downloader.httpclient.HttpClient
    public void close() {
    }

    @Override // com.downloader.httpclient.HttpClient
    public void connect(DownloadRequest downloadRequest) throws IOException {
        this.connection = new URL(downloadRequest.getUrl()).openConnection();
        this.connection.setReadTimeout(downloadRequest.getReadTimeout());
        this.connection.setConnectTimeout(downloadRequest.getConnectTimeout());
        this.connection.addRequestProperty("Range", String.format(Locale.ENGLISH, "bytes=%d-", Long.valueOf(downloadRequest.getDownloadedBytes())));
        this.connection.addRequestProperty("User-Agent", downloadRequest.getUserAgent());
        addHeaders(downloadRequest);
        this.connection.connect();
    }

    @Override // com.downloader.httpclient.HttpClient
    public long getContentLength() {
        try {
            return Long.parseLong(this.connection.getHeaderField("Content-Length"));
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    @Override // com.downloader.httpclient.HttpClient
    public InputStream getInputStream() throws IOException {
        return this.connection.getInputStream();
    }

    @Override // com.downloader.httpclient.HttpClient
    public int getResponseCode() throws IOException {
        if (this.connection instanceof HttpURLConnection) {
            return ((HttpURLConnection) this.connection).getResponseCode();
        }
        return 0;
    }

    @Override // com.downloader.httpclient.HttpClient
    public String getResponseHeader(String str) {
        return this.connection.getHeaderField(str);
    }
}
