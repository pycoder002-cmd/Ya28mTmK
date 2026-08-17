package io.sentry.event.helper;

import io.sentry.util.Util;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

/* loaded from: classes2.dex */
public class ForwardedAddressResolver implements RemoteAddressResolver {
    private BasicRemoteAddressResolver basicRemoteAddressResolver = new BasicRemoteAddressResolver();

    private static String firstAddress(String str) {
        return ((String) Arrays.asList(str.split(",")).get(0)).trim();
    }

    @Override // io.sentry.event.helper.RemoteAddressResolver
    public String getRemoteAddress(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-FORWARDED-FOR");
        return !Util.isNullOrEmpty(header) ? firstAddress(header) : this.basicRemoteAddressResolver.getRemoteAddress(httpServletRequest);
    }
}
