package org.springframework.http;

import kotlin.text.Typography;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

/* loaded from: classes2.dex */
public class HttpEntity<T> {
    public static final HttpEntity EMPTY = new HttpEntity();
    private final T body;
    private final HttpHeaders headers;

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpEntity() {
        this(null, null);
    }

    public HttpEntity(T t) {
        this(t, null);
    }

    public HttpEntity(T t, MultiValueMap<String, String> multiValueMap) {
        this.body = t;
        HttpHeaders httpHeaders = new HttpHeaders();
        if (multiValueMap != null) {
            httpHeaders.putAll(multiValueMap);
        }
        this.headers = HttpHeaders.readOnlyHttpHeaders(httpHeaders);
    }

    public HttpEntity(MultiValueMap<String, String> multiValueMap) {
        this(null, multiValueMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpEntity)) {
            return false;
        }
        HttpEntity httpEntity = (HttpEntity) obj;
        return ObjectUtils.nullSafeEquals(this.headers, httpEntity.headers) && ObjectUtils.nullSafeEquals(this.body, httpEntity.body);
    }

    public T getBody() {
        return this.body;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public boolean hasBody() {
        return this.body != null;
    }

    public int hashCode() {
        return (ObjectUtils.nullSafeHashCode(this.headers) * 29) + ObjectUtils.nullSafeHashCode(this.body);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        if (this.body != null) {
            sb.append(this.body);
            if (this.headers != null) {
                sb.append(',');
            }
        }
        if (this.headers != null) {
            sb.append(this.headers);
        }
        sb.append(Typography.greater);
        return sb.toString();
    }
}
