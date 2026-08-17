package org.springframework.web.client;

import java.io.IOException;

/* loaded from: classes2.dex */
public class ResourceAccessException extends RestClientException {
    private static final long serialVersionUID = -8513182514355844870L;

    public ResourceAccessException(String str) {
        super(str);
    }

    public ResourceAccessException(String str, IOException iOException) {
        super(str, iOException);
    }
}
