package org.springframework.web.client;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.InterceptingHttpAccessor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

/* loaded from: classes2.dex */
public class RestTemplate extends InterceptingHttpAccessor implements RestOperations {
    private static final String TAG = "RestTemplate";
    private ResponseErrorHandler errorHandler;
    private final ResponseExtractor<HttpHeaders> headersExtractor;
    private final List<HttpMessageConverter<?>> messageConverters;

    /* loaded from: classes2.dex */
    private class AcceptHeaderRequestCallback implements RequestCallback {
        private final Type responseType;

        private AcceptHeaderRequestCallback(Type type) {
            this.responseType = type;
        }

        private List<MediaType> getSupportedMediaTypes(HttpMessageConverter<?> httpMessageConverter) {
            List<MediaType> supportedMediaTypes = httpMessageConverter.getSupportedMediaTypes();
            ArrayList arrayList = new ArrayList(supportedMediaTypes.size());
            for (MediaType mediaType : supportedMediaTypes) {
                if (mediaType.getCharSet() != null) {
                    mediaType = new MediaType(mediaType.getType(), mediaType.getSubtype());
                }
                arrayList.add(mediaType);
            }
            return arrayList;
        }

        @Override // org.springframework.web.client.RequestCallback
        public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
            if (this.responseType != null) {
                Class<?> cls = this.responseType instanceof Class ? (Class) this.responseType : null;
                ArrayList arrayList = new ArrayList();
                for (HttpMessageConverter<?> httpMessageConverter : RestTemplate.this.getMessageConverters()) {
                    if (cls != null) {
                        if (httpMessageConverter.canRead(cls, null)) {
                            arrayList.addAll(getSupportedMediaTypes(httpMessageConverter));
                        }
                    } else if ((httpMessageConverter instanceof GenericHttpMessageConverter) && ((GenericHttpMessageConverter) httpMessageConverter).canRead(this.responseType, null, null)) {
                        arrayList.addAll(getSupportedMediaTypes(httpMessageConverter));
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                MediaType.sortBySpecificity(arrayList);
                if (Log.isLoggable(RestTemplate.TAG, 3)) {
                    Log.d(RestTemplate.TAG, "Setting request Accept header to " + arrayList);
                }
                clientHttpRequest.getHeaders().setAccept(arrayList);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class DefaultMessageConverters {
        private static final boolean gsonPresent;
        private static final boolean jackson2Present;
        private static final boolean javaxXmlTransformPresent = ClassUtils.isPresent("javax.xml.transform.Source", RestTemplate.class.getClassLoader());
        private static final boolean simpleXmlPresent = ClassUtils.isPresent("org.simpleframework.xml.Serializer", RestTemplate.class.getClassLoader());

        static {
            jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", RestTemplate.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", RestTemplate.class.getClassLoader());
            gsonPresent = ClassUtils.isPresent("com.google.gson.Gson", RestTemplate.class.getClassLoader());
        }

        private DefaultMessageConverters() {
        }

        public static void init(List<HttpMessageConverter<?>> list) {
            list.add(new ByteArrayHttpMessageConverter());
            list.add(new StringHttpMessageConverter());
            list.add(new ResourceHttpMessageConverter());
            if (javaxXmlTransformPresent) {
                list.add(new SourceHttpMessageConverter());
                list.add(new AllEncompassingFormHttpMessageConverter());
            } else {
                list.add(new FormHttpMessageConverter());
            }
            if (simpleXmlPresent) {
                list.add(new SimpleXmlHttpMessageConverter());
            }
            if (jackson2Present) {
                list.add(new MappingJackson2HttpMessageConverter());
            } else if (gsonPresent) {
                list.add(new GsonHttpMessageConverter());
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class HeadersExtractor implements ResponseExtractor<HttpHeaders> {
        private HeadersExtractor() {
        }

        @Override // org.springframework.web.client.ResponseExtractor
        public HttpHeaders extractData(ClientHttpResponse clientHttpResponse) throws IOException {
            return clientHttpResponse.getHeaders();
        }
    }

    /* loaded from: classes2.dex */
    private class HttpEntityRequestCallback extends AcceptHeaderRequestCallback {
        private final HttpEntity<?> requestEntity;

        private HttpEntityRequestCallback(RestTemplate restTemplate, Object obj) {
            this(obj, (Type) null);
        }

        private HttpEntityRequestCallback(Object obj, Type type) {
            super(type);
            if (obj instanceof HttpEntity) {
                this.requestEntity = (HttpEntity) obj;
            } else if (obj != null) {
                this.requestEntity = new HttpEntity<>(obj);
            } else {
                this.requestEntity = HttpEntity.EMPTY;
            }
        }

        @Override // org.springframework.web.client.RestTemplate.AcceptHeaderRequestCallback, org.springframework.web.client.RequestCallback
        public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
            super.doWithRequest(clientHttpRequest);
            if (!this.requestEntity.hasBody()) {
                HttpHeaders headers = clientHttpRequest.getHeaders();
                HttpHeaders headers2 = this.requestEntity.getHeaders();
                if (!headers2.isEmpty()) {
                    headers.putAll(headers2);
                }
                if (headers.getContentLength() == -1) {
                    headers.setContentLength(0L);
                    return;
                }
                return;
            }
            Object body = this.requestEntity.getBody();
            Class<?> cls = body.getClass();
            HttpHeaders headers3 = this.requestEntity.getHeaders();
            MediaType contentType = headers3.getContentType();
            for (HttpMessageConverter<?> httpMessageConverter : RestTemplate.this.getMessageConverters()) {
                if (httpMessageConverter.canWrite(cls, contentType)) {
                    if (!headers3.isEmpty()) {
                        clientHttpRequest.getHeaders().putAll(headers3);
                    }
                    if (Log.isLoggable(RestTemplate.TAG, 3)) {
                        if (contentType != null) {
                            Log.d(RestTemplate.TAG, "Writing [" + body + "] as \"" + contentType + "\" using [" + httpMessageConverter + "]");
                        } else {
                            Log.d(RestTemplate.TAG, "Writing [" + body + "] using [" + httpMessageConverter + "]");
                        }
                    }
                    httpMessageConverter.write(body, contentType, clientHttpRequest);
                    return;
                }
            }
            String str = "Could not write request: no suitable HttpMessageConverter found for request type [" + cls.getName() + "]";
            if (contentType != null) {
                str = str + " and content type [" + contentType + "]";
            }
            throw new RestClientException(str);
        }
    }

    /* loaded from: classes2.dex */
    private class ResponseEntityResponseExtractor<T> implements ResponseExtractor<ResponseEntity<T>> {
        private final HttpMessageConverterExtractor<T> delegate;

        public ResponseEntityResponseExtractor(Type type) {
            if (type == null || Void.class.equals(type)) {
                this.delegate = null;
            } else {
                this.delegate = new HttpMessageConverterExtractor<>(type, RestTemplate.this.getMessageConverters());
            }
        }

        @Override // org.springframework.web.client.ResponseExtractor
        public ResponseEntity<T> extractData(ClientHttpResponse clientHttpResponse) throws IOException {
            return this.delegate != null ? new ResponseEntity<>(this.delegate.extractData(clientHttpResponse), clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode()) : new ResponseEntity<>((MultiValueMap<String, String>) clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
        }
    }

    public RestTemplate() {
        this.messageConverters = new ArrayList();
        this.errorHandler = new DefaultResponseErrorHandler();
        this.headersExtractor = new HeadersExtractor();
        DefaultMessageConverters.init(this.messageConverters);
    }

    public RestTemplate(List<HttpMessageConverter<?>> list) {
        this.messageConverters = new ArrayList();
        this.errorHandler = new DefaultResponseErrorHandler();
        this.headersExtractor = new HeadersExtractor();
        Assert.notEmpty(list, "'messageConverters' must not be empty");
        this.messageConverters.addAll(list);
    }

    public RestTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        this();
        setRequestFactory(clientHttpRequestFactory);
    }

    @Deprecated
    public RestTemplate(boolean z) {
        this.messageConverters = new ArrayList();
        this.errorHandler = new DefaultResponseErrorHandler();
        this.headersExtractor = new HeadersExtractor();
        if (z) {
            DefaultMessageConverters.init(this.messageConverters);
        }
    }

    @Deprecated
    public RestTemplate(boolean z, ClientHttpRequestFactory clientHttpRequestFactory) {
        this(z);
        setRequestFactory(clientHttpRequestFactory);
    }

    private void handleResponseError(HttpMethod httpMethod, URI uri, ClientHttpResponse clientHttpResponse) throws IOException {
        if (Log.isLoggable(TAG, 5)) {
            try {
                Log.w(TAG, httpMethod.name() + " request for \"" + uri + "\" resulted in " + clientHttpResponse.getStatusCode() + " (" + clientHttpResponse.getStatusText() + "); invoking error handler");
            } catch (IOException unused) {
            }
        }
        getErrorHandler().handleError(clientHttpResponse);
    }

    private void logResponseStatus(HttpMethod httpMethod, URI uri, ClientHttpResponse clientHttpResponse) {
        if (Log.isLoggable(TAG, 3)) {
            try {
                Log.d(TAG, httpMethod.name() + " request for \"" + uri + "\" resulted in " + clientHttpResponse.getStatusCode() + " (" + clientHttpResponse.getStatusText() + ")");
            } catch (IOException unused) {
            }
        }
    }

    @Override // org.springframework.web.client.RestOperations
    public void delete(String str, Map<String, ?> map) throws RestClientException {
        execute(str, HttpMethod.DELETE, (RequestCallback) null, (ResponseExtractor) null, map);
    }

    @Override // org.springframework.web.client.RestOperations
    public void delete(String str, Object... objArr) throws RestClientException {
        execute(str, HttpMethod.DELETE, (RequestCallback) null, (ResponseExtractor) null, objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public void delete(URI uri) throws RestClientException {
        execute(uri, HttpMethod.DELETE, null, null);
    }

    protected <T> T doExecute(URI uri, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        IOException e;
        ClientHttpResponse execute;
        Assert.notNull(uri, "'url' must not be null");
        Assert.notNull(httpMethod, "'method' must not be null");
        ClientHttpResponse clientHttpResponse = null;
        try {
            try {
                ClientHttpRequest createRequest = createRequest(uri, httpMethod);
                if (requestCallback != null) {
                    requestCallback.doWithRequest(createRequest);
                }
                execute = createRequest.execute();
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            if (getErrorHandler().hasError(execute)) {
                handleResponseError(httpMethod, uri, execute);
            } else {
                logResponseStatus(httpMethod, uri, execute);
            }
            if (responseExtractor == null) {
                if (execute != null) {
                    execute.close();
                }
                return null;
            }
            T extractData = responseExtractor.extractData(execute);
            if (execute != null) {
                execute.close();
            }
            return extractData;
        } catch (IOException e3) {
            e = e3;
            throw new ResourceAccessException("I/O error on " + httpMethod.name() + " request for \"" + uri + "\": " + e.getMessage(), e);
        } catch (Throwable th2) {
            th = th2;
            clientHttpResponse = execute;
            if (clientHttpResponse != null) {
                clientHttpResponse.close();
            }
            throw th;
        }
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> exchange(String str, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> cls, Map<String, ?> map) throws RestClientException {
        return (ResponseEntity) execute(str, httpMethod, new HttpEntityRequestCallback(httpEntity, cls), new ResponseEntityResponseExtractor(cls), map);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> exchange(String str, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> cls, Object... objArr) throws RestClientException {
        return (ResponseEntity) execute(str, httpMethod, new HttpEntityRequestCallback(httpEntity, cls), new ResponseEntityResponseExtractor(cls), objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> exchange(String str, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<T> parameterizedTypeReference, Map<String, ?> map) throws RestClientException {
        Type type = parameterizedTypeReference.getType();
        return (ResponseEntity) execute(str, httpMethod, new HttpEntityRequestCallback(httpEntity, type), new ResponseEntityResponseExtractor(type), map);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> exchange(String str, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<T> parameterizedTypeReference, Object... objArr) throws RestClientException {
        Type type = parameterizedTypeReference.getType();
        return (ResponseEntity) execute(str, httpMethod, new HttpEntityRequestCallback(httpEntity, type), new ResponseEntityResponseExtractor(type), objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> exchange(URI uri, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> cls) throws RestClientException {
        return (ResponseEntity) execute(uri, httpMethod, new HttpEntityRequestCallback(httpEntity, cls), new ResponseEntityResponseExtractor(cls));
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> exchange(URI uri, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<T> parameterizedTypeReference) throws RestClientException {
        Type type = parameterizedTypeReference.getType();
        return (ResponseEntity) execute(uri, httpMethod, new HttpEntityRequestCallback(httpEntity, type), new ResponseEntityResponseExtractor(type));
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T execute(String str, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Map<String, ?> map) throws RestClientException {
        return (T) doExecute(new UriTemplate(str).expand(map), httpMethod, requestCallback, responseExtractor);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T execute(String str, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object... objArr) throws RestClientException {
        return (T) doExecute(new UriTemplate(str).expand(objArr), httpMethod, requestCallback, responseExtractor);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T execute(URI uri, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        return (T) doExecute(uri, httpMethod, requestCallback, responseExtractor);
    }

    public ResponseErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> getForEntity(String str, Class<T> cls, Map<String, ?> map) throws RestClientException {
        return (ResponseEntity) execute(str, HttpMethod.GET, new AcceptHeaderRequestCallback(cls), new ResponseEntityResponseExtractor(cls), map);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> getForEntity(String str, Class<T> cls, Object... objArr) throws RestClientException {
        return (ResponseEntity) execute(str, HttpMethod.GET, new AcceptHeaderRequestCallback(cls), new ResponseEntityResponseExtractor(cls), objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> getForEntity(URI uri, Class<T> cls) throws RestClientException {
        return (ResponseEntity) execute(uri, HttpMethod.GET, new AcceptHeaderRequestCallback(cls), new ResponseEntityResponseExtractor(cls));
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T getForObject(String str, Class<T> cls, Map<String, ?> map) throws RestClientException {
        return (T) execute(str, HttpMethod.GET, new AcceptHeaderRequestCallback(cls), new HttpMessageConverterExtractor((Class) cls, getMessageConverters()), map);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T getForObject(String str, Class<T> cls, Object... objArr) throws RestClientException {
        return (T) execute(str, HttpMethod.GET, new AcceptHeaderRequestCallback(cls), new HttpMessageConverterExtractor((Class) cls, getMessageConverters()), objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T getForObject(URI uri, Class<T> cls) throws RestClientException {
        return (T) execute(uri, HttpMethod.GET, new AcceptHeaderRequestCallback(cls), new HttpMessageConverterExtractor((Class) cls, getMessageConverters()));
    }

    public List<HttpMessageConverter<?>> getMessageConverters() {
        return this.messageConverters;
    }

    @Override // org.springframework.web.client.RestOperations
    public HttpHeaders headForHeaders(String str, Map<String, ?> map) throws RestClientException {
        return (HttpHeaders) execute(str, HttpMethod.HEAD, (RequestCallback) null, this.headersExtractor, map);
    }

    @Override // org.springframework.web.client.RestOperations
    public HttpHeaders headForHeaders(String str, Object... objArr) throws RestClientException {
        return (HttpHeaders) execute(str, HttpMethod.HEAD, (RequestCallback) null, this.headersExtractor, objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public HttpHeaders headForHeaders(URI uri) throws RestClientException {
        return (HttpHeaders) execute(uri, HttpMethod.HEAD, null, this.headersExtractor);
    }

    @Override // org.springframework.web.client.RestOperations
    public Set<HttpMethod> optionsForAllow(String str, Map<String, ?> map) throws RestClientException {
        return ((HttpHeaders) execute(str, HttpMethod.OPTIONS, (RequestCallback) null, this.headersExtractor, map)).getAllow();
    }

    @Override // org.springframework.web.client.RestOperations
    public Set<HttpMethod> optionsForAllow(String str, Object... objArr) throws RestClientException {
        return ((HttpHeaders) execute(str, HttpMethod.OPTIONS, (RequestCallback) null, this.headersExtractor, objArr)).getAllow();
    }

    @Override // org.springframework.web.client.RestOperations
    public Set<HttpMethod> optionsForAllow(URI uri) throws RestClientException {
        return ((HttpHeaders) execute(uri, HttpMethod.OPTIONS, null, this.headersExtractor)).getAllow();
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> postForEntity(String str, Object obj, Class<T> cls, Map<String, ?> map) throws RestClientException {
        return (ResponseEntity) execute(str, HttpMethod.POST, new HttpEntityRequestCallback(obj, cls), new ResponseEntityResponseExtractor(cls), map);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> postForEntity(String str, Object obj, Class<T> cls, Object... objArr) throws RestClientException {
        return (ResponseEntity) execute(str, HttpMethod.POST, new HttpEntityRequestCallback(obj, cls), new ResponseEntityResponseExtractor(cls), objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> ResponseEntity<T> postForEntity(URI uri, Object obj, Class<T> cls) throws RestClientException {
        return (ResponseEntity) execute(uri, HttpMethod.POST, new HttpEntityRequestCallback(obj, cls), new ResponseEntityResponseExtractor(cls));
    }

    @Override // org.springframework.web.client.RestOperations
    public URI postForLocation(String str, Object obj, Map<String, ?> map) throws RestClientException {
        return ((HttpHeaders) execute(str, HttpMethod.POST, new HttpEntityRequestCallback(obj), this.headersExtractor, map)).getLocation();
    }

    @Override // org.springframework.web.client.RestOperations
    public URI postForLocation(String str, Object obj, Object... objArr) throws RestClientException {
        return ((HttpHeaders) execute(str, HttpMethod.POST, new HttpEntityRequestCallback(obj), this.headersExtractor, objArr)).getLocation();
    }

    @Override // org.springframework.web.client.RestOperations
    public URI postForLocation(URI uri, Object obj) throws RestClientException {
        return ((HttpHeaders) execute(uri, HttpMethod.POST, new HttpEntityRequestCallback(obj), this.headersExtractor)).getLocation();
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T postForObject(String str, Object obj, Class<T> cls, Map<String, ?> map) throws RestClientException {
        return (T) execute(str, HttpMethod.POST, new HttpEntityRequestCallback(obj, cls), new HttpMessageConverterExtractor((Class) cls, getMessageConverters()), map);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T postForObject(String str, Object obj, Class<T> cls, Object... objArr) throws RestClientException {
        return (T) execute(str, HttpMethod.POST, new HttpEntityRequestCallback(obj, cls), new HttpMessageConverterExtractor((Class) cls, getMessageConverters()), objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public <T> T postForObject(URI uri, Object obj, Class<T> cls) throws RestClientException {
        return (T) execute(uri, HttpMethod.POST, new HttpEntityRequestCallback(obj, cls), new HttpMessageConverterExtractor((Class) cls, getMessageConverters()));
    }

    @Override // org.springframework.web.client.RestOperations
    public void put(String str, Object obj, Map<String, ?> map) throws RestClientException {
        execute(str, HttpMethod.PUT, new HttpEntityRequestCallback(obj), (ResponseExtractor) null, map);
    }

    @Override // org.springframework.web.client.RestOperations
    public void put(String str, Object obj, Object... objArr) throws RestClientException {
        execute(str, HttpMethod.PUT, new HttpEntityRequestCallback(obj), (ResponseExtractor) null, objArr);
    }

    @Override // org.springframework.web.client.RestOperations
    public void put(URI uri, Object obj) throws RestClientException {
        execute(uri, HttpMethod.PUT, new HttpEntityRequestCallback(obj), null);
    }

    public void setErrorHandler(ResponseErrorHandler responseErrorHandler) {
        Assert.notNull(responseErrorHandler, "'errorHandler' must not be null");
        this.errorHandler = responseErrorHandler;
    }

    public void setMessageConverters(List<HttpMessageConverter<?>> list) {
        Assert.notEmpty(list, "'messageConverters' must not be empty");
        if (this.messageConverters != list) {
            this.messageConverters.clear();
            this.messageConverters.addAll(list);
        }
    }
}
