package com.downloader.request;

import com.downloader.Priority;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class DownloadRequestBuilder implements RequestBuilder {
    int connectTimeout;
    String dirPath;
    String fileName;
    HashMap<String, List<String>> headerMap;
    Priority priority = Priority.MEDIUM;
    int readTimeout;
    Object tag;
    String url;
    String userAgent;

    public DownloadRequestBuilder(String str, String str2, String str3) {
        this.url = str;
        this.dirPath = str2;
        this.fileName = str3;
    }

    public DownloadRequest build() {
        return new DownloadRequest(this);
    }

    @Override // com.downloader.request.RequestBuilder
    public DownloadRequestBuilder setConnectTimeout(int i) {
        this.connectTimeout = i;
        return this;
    }

    @Override // com.downloader.request.RequestBuilder
    public DownloadRequestBuilder setHeader(String str, String str2) {
        if (this.headerMap == null) {
            this.headerMap = new HashMap<>();
        }
        List<String> list = this.headerMap.get(str);
        if (list == null) {
            list = new ArrayList<>();
            this.headerMap.put(str, list);
        }
        if (!list.contains(str2)) {
            list.add(str2);
        }
        return this;
    }

    @Override // com.downloader.request.RequestBuilder
    public DownloadRequestBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override // com.downloader.request.RequestBuilder
    public DownloadRequestBuilder setReadTimeout(int i) {
        this.readTimeout = i;
        return this;
    }

    @Override // com.downloader.request.RequestBuilder
    public DownloadRequestBuilder setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    @Override // com.downloader.request.RequestBuilder
    public DownloadRequestBuilder setUserAgent(String str) {
        this.userAgent = str;
        return this;
    }
}
