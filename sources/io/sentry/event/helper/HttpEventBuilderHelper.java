package io.sentry.event.helper;

import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.HttpInterface;
import io.sentry.event.interfaces.UserInterface;
import io.sentry.servlet.SentryServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/* loaded from: classes2.dex */
public class HttpEventBuilderHelper implements EventBuilderHelper {
    private final RemoteAddressResolver remoteAddressResolver;

    public HttpEventBuilderHelper() {
        this.remoteAddressResolver = new BasicRemoteAddressResolver();
    }

    public HttpEventBuilderHelper(RemoteAddressResolver remoteAddressResolver) {
        this.remoteAddressResolver = remoteAddressResolver;
    }

    private void addHttpInterface(EventBuilder eventBuilder, HttpServletRequest httpServletRequest) {
        eventBuilder.withSentryInterface(new HttpInterface(httpServletRequest, this.remoteAddressResolver), false);
    }

    private void addUserInterface(EventBuilder eventBuilder, HttpServletRequest httpServletRequest) {
        eventBuilder.withSentryInterface(new UserInterface(null, httpServletRequest.getUserPrincipal() != null ? httpServletRequest.getUserPrincipal().getName() : null, this.remoteAddressResolver.getRemoteAddress(httpServletRequest), null), false);
    }

    public RemoteAddressResolver getRemoteAddressResolver() {
        return this.remoteAddressResolver;
    }

    @Override // io.sentry.event.helper.EventBuilderHelper
    public void helpBuildingEvent(EventBuilder eventBuilder) {
        HttpServletRequest servletRequest = SentryServletRequestListener.getServletRequest();
        if (servletRequest == null) {
            return;
        }
        addHttpInterface(eventBuilder, servletRequest);
        addUserInterface(eventBuilder, servletRequest);
    }
}
