package org.springframework.web.client.support;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/* loaded from: classes2.dex */
public class RestGatewaySupport {
    private RestTemplate restTemplate;

    public RestGatewaySupport() {
        this.restTemplate = new RestTemplate();
    }

    public RestGatewaySupport(ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.notNull(clientHttpRequestFactory, "'requestFactory' must not be null");
        this.restTemplate = new RestTemplate(clientHttpRequestFactory);
    }

    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        Assert.notNull(restTemplate, "'restTemplate' must not be null");
        this.restTemplate = restTemplate;
    }
}
