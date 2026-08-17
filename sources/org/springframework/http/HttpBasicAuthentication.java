package org.springframework.http;

import org.springframework.util.Base64Utils;

/* loaded from: classes2.dex */
public class HttpBasicAuthentication extends HttpAuthentication {
    private final String password;
    private final String username;

    public HttpBasicAuthentication(String str, String str2) {
        this.username = str;
        this.password = str2;
    }

    @Override // org.springframework.http.HttpAuthentication
    public String getHeaderValue() {
        return String.format("Basic %s", Base64Utils.encodeToString(String.format("%s:%s", this.username, this.password).getBytes()));
    }

    public String toString() {
        try {
            return String.format("Authorization: %s", getHeaderValue());
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
