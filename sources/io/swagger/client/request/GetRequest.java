package io.swagger.client.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class GetRequest extends StringRequest {
    Map<String, String> apiHeaders;
    String contentType;

    public GetRequest(String str, Map<String, String> map, String str2, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(0, str, listener, errorListener);
        this.apiHeaders = map;
        this.contentType = str2;
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<>();
        }
        if (this.apiHeaders != null && !this.apiHeaders.equals(Collections.emptyMap())) {
            headers.putAll(this.apiHeaders);
        }
        if (this.contentType != null) {
            headers.put("Content-Type", this.contentType);
        }
        return headers;
    }
}
