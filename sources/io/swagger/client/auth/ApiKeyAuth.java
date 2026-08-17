package io.swagger.client.auth;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.actions.SearchIntents;
import io.swagger.client.Pair;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class ApiKeyAuth implements Authentication {
    private String apiKey;
    private String apiKeyPrefix;
    private final String location;
    private final String paramName;

    public ApiKeyAuth(String str, String str2) {
        this.location = str;
        this.paramName = str2;
    }

    @Override // io.swagger.client.auth.Authentication
    public void applyToParams(List<Pair> list, Map<String, String> map) {
        String str;
        if (this.apiKey == null) {
            return;
        }
        if (this.apiKeyPrefix != null) {
            str = this.apiKeyPrefix + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.apiKey;
        } else {
            str = this.apiKey;
        }
        if (SearchIntents.EXTRA_QUERY.equals(this.location)) {
            list.add(new Pair(this.paramName, str));
        } else if ("header".equals(this.location)) {
            map.put(this.paramName, str);
        }
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getApiKeyPrefix() {
        return this.apiKeyPrefix;
    }

    public String getLocation() {
        return this.location;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setApiKey(String str) {
        this.apiKey = str;
    }

    public void setApiKeyPrefix(String str) {
        this.apiKeyPrefix = str;
    }
}
