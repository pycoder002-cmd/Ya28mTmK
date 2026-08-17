package io.swagger.client.auth;

import android.util.Base64;
import io.swagger.client.Pair;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class HttpBasicAuth implements Authentication {
    private String password;
    private String username;

    @Override // io.swagger.client.auth.Authentication
    public void applyToParams(List<Pair> list, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.username == null ? "" : this.username);
        sb.append(":");
        sb.append(this.password == null ? "" : this.password);
        map.put("Authorization", "Basic " + Base64.encodeToString(sb.toString().getBytes(), 0));
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
