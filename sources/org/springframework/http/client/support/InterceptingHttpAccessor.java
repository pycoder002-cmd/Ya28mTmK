package org.springframework.http.client.support;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;

/* loaded from: classes2.dex */
public abstract class InterceptingHttpAccessor extends HttpAccessor {
    private List<ClientHttpRequestInterceptor> interceptors = new ArrayList();

    public List<ClientHttpRequestInterceptor> getInterceptors() {
        return this.interceptors;
    }

    @Override // org.springframework.http.client.support.HttpAccessor
    public ClientHttpRequestFactory getRequestFactory() {
        ClientHttpRequestFactory requestFactory = super.getRequestFactory();
        return !CollectionUtils.isEmpty(getInterceptors()) ? new InterceptingClientHttpRequestFactory(requestFactory, getInterceptors()) : requestFactory;
    }

    public void setInterceptors(List<ClientHttpRequestInterceptor> list) {
        this.interceptors = list;
    }
}
