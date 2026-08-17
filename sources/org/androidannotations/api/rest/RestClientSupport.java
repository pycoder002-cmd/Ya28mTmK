package org.androidannotations.api.rest;

import org.springframework.web.client.RestTemplate;

/* loaded from: classes2.dex */
public interface RestClientSupport {
    RestTemplate getRestTemplate();

    void setRestTemplate(RestTemplate restTemplate);
}
