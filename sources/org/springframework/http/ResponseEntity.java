package org.springframework.http;

import kotlin.text.Typography;
import org.springframework.util.MultiValueMap;

/* loaded from: classes2.dex */
public class ResponseEntity<T> extends HttpEntity<T> {
    private final HttpStatus statusCode;

    public ResponseEntity(T t, HttpStatus httpStatus) {
        super(t);
        this.statusCode = httpStatus;
    }

    public ResponseEntity(T t, MultiValueMap<String, String> multiValueMap, HttpStatus httpStatus) {
        super(t, multiValueMap);
        this.statusCode = httpStatus;
    }

    public ResponseEntity(HttpStatus httpStatus) {
        this.statusCode = httpStatus;
    }

    public ResponseEntity(MultiValueMap<String, String> multiValueMap, HttpStatus httpStatus) {
        super(multiValueMap);
        this.statusCode = httpStatus;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    @Override // org.springframework.http.HttpEntity
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(this.statusCode.toString());
        sb.append(' ');
        sb.append(this.statusCode.getReasonPhrase());
        sb.append(',');
        T body = getBody();
        HttpHeaders headers = getHeaders();
        if (body != null) {
            sb.append(body);
            if (headers != null) {
                sb.append(',');
            }
        }
        if (headers != null) {
            sb.append(headers);
        }
        sb.append(Typography.greater);
        return sb.toString();
    }
}
