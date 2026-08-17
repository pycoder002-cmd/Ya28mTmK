package com.downloader;

import com.downloader.httpclient.DefaultHttpClient;
import com.downloader.httpclient.HttpClient;

/* loaded from: classes.dex */
public class PRDownloaderConfig {
    private int connectTimeout;
    private boolean databaseEnabled;
    private HttpClient httpClient;
    private int readTimeout;
    private String userAgent;

    /* loaded from: classes.dex */
    public static class Builder {
        int readTimeout = 20000;
        int connectTimeout = 20000;
        String userAgent = Constants.DEFAULT_USER_AGENT;
        HttpClient httpClient = new DefaultHttpClient();
        boolean databaseEnabled = false;

        public PRDownloaderConfig build() {
            return new PRDownloaderConfig(this);
        }

        public Builder setConnectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        public Builder setDatabaseEnabled(boolean z) {
            this.databaseEnabled = z;
            return this;
        }

        public Builder setHttpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder setReadTimeout(int i) {
            this.readTimeout = i;
            return this;
        }

        public Builder setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }
    }

    private PRDownloaderConfig(Builder builder) {
        this.readTimeout = builder.readTimeout;
        this.connectTimeout = builder.connectTimeout;
        this.userAgent = builder.userAgent;
        this.httpClient = builder.httpClient;
        this.databaseEnabled = builder.databaseEnabled;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public boolean isDatabaseEnabled() {
        return this.databaseEnabled;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public void setDatabaseEnabled(boolean z) {
        this.databaseEnabled = z;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }
}
