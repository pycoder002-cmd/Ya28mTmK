package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: Access modifiers changed from: package-private */
@Immutable
/* loaded from: classes.dex */
public class CacheKeyGenerator {
    private static final URI BASE_URI = URI.create("http://example.com/");

    private int canonicalizePort(int i, String str) {
        if (i == -1 && "http".equalsIgnoreCase(str)) {
            return 80;
        }
        if (i == -1 && "https".equalsIgnoreCase(str)) {
            return 443;
        }
        return i;
    }

    private boolean isRelativeRequest(HttpRequest httpRequest) {
        String uri = httpRequest.getRequestLine().getUri();
        return "*".equals(uri) || uri.startsWith("/");
    }

    public String canonicalizeUri(String str) {
        try {
            URL url = new URL(URIUtils.resolve(BASE_URI, str).toASCIIString());
            String protocol = url.getProtocol();
            String host = url.getHost();
            int canonicalizePort = canonicalizePort(url.getPort(), protocol);
            String path = url.getPath();
            String query = url.getQuery();
            if (query != null) {
                path = path + "?" + query;
            }
            return new URL(protocol, host, canonicalizePort, path).toString();
        } catch (IllegalArgumentException unused) {
            return str;
        } catch (MalformedURLException unused2) {
            return str;
        }
    }

    protected String getFullHeaderValue(Header[] headerArr) {
        if (headerArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        int length = headerArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            Header header = headerArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append(header.getValue().trim());
            i++;
            z = false;
        }
        return sb.toString();
    }

    public String getURI(HttpHost httpHost, HttpRequest httpRequest) {
        return isRelativeRequest(httpRequest) ? canonicalizeUri(String.format("%s%s", httpHost.toString(), httpRequest.getRequestLine().getUri())) : canonicalizeUri(httpRequest.getRequestLine().getUri());
    }

    public String getVariantKey(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) {
        ArrayList<String> arrayList = new ArrayList();
        for (Header header : httpCacheEntry.getHeaders("Vary")) {
            for (HeaderElement headerElement : header.getElements()) {
                arrayList.add(headerElement.getName());
            }
        }
        Collections.sort(arrayList);
        try {
            StringBuilder sb = new StringBuilder("{");
            boolean z = true;
            for (String str : arrayList) {
                if (!z) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(str, Consts.UTF_8.name()));
                sb.append("=");
                sb.append(URLEncoder.encode(getFullHeaderValue(httpRequest.getHeaders(str)), Consts.UTF_8.name()));
                z = false;
            }
            sb.append("}");
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("couldn't encode to UTF-8", e);
        }
    }

    public String getVariantURI(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) {
        if (!httpCacheEntry.hasVariants()) {
            return getURI(httpHost, httpRequest);
        }
        return getVariantKey(httpRequest, httpCacheEntry) + getURI(httpHost, httpRequest);
    }
}
