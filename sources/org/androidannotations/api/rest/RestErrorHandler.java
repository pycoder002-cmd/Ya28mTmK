package org.androidannotations.api.rest;

import org.springframework.core.NestedRuntimeException;

/* loaded from: classes2.dex */
public interface RestErrorHandler {
    void onRestClientExceptionThrown(NestedRuntimeException nestedRuntimeException);
}
