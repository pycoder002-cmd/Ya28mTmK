package io.swagger.client.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

/* loaded from: classes2.dex */
public class PostRequest extends Request<String> {
    Map<String, String> apiHeaders;
    String contentType;
    HttpEntity entity;
    private final Response.Listener<String> mListener;

    public PostRequest(String str, Map<String, String> map, String str2, HttpEntity httpEntity, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(1, str, errorListener);
        this.mListener = listener;
        this.entity = httpEntity;
        this.contentType = str2;
        this.apiHeaders = map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void deliverResponse(String str) {
        this.mListener.onResponse(str);
    }

    @Override // com.android.volley.Request
    public byte[] getBody() throws AuthFailureError {
        if (this.entity == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.entity.writeTo(byteArrayOutputStream);
        } catch (IOException unused) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream", new Object[0]);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        if (this.entity == null) {
            return null;
        }
        return this.entity.getContentType().getValue();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        String str;
        try {
            str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException unused) {
            str = new String(networkResponse.data);
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
