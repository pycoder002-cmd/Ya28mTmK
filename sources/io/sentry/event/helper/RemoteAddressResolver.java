package io.sentry.event.helper;

import javax.servlet.http.HttpServletRequest;

/* loaded from: classes2.dex */
public interface RemoteAddressResolver {
    String getRemoteAddress(HttpServletRequest httpServletRequest);
}
