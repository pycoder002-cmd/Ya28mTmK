package org.springframework.web.client;

import org.springframework.core.NestedRuntimeException;

/* loaded from: classes2.dex */
public class RestClientException extends NestedRuntimeException {
    private static final long serialVersionUID = -4084444984163796577L;

    public RestClientException(String str) {
        super(str);
    }

    public RestClientException(String str, Throwable th) {
        super(str, th);
    }
}
