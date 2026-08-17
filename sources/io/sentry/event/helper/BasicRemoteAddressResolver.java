package io.sentry.event.helper;

import javax.servlet.http.HttpServletRequest;

/* loaded from: classes2.dex */
public class BasicRemoteAddressResolver implements RemoteAddressResolver {
    @Override // io.sentry.event.helper.RemoteAddressResolver
    public String getRemoteAddress(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRemoteAddr();
    }
}
