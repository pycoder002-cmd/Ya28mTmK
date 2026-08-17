package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public class UrlResource extends AbstractFileResolvingResource {
    private final URL cleanedUrl;
    private final URI uri;
    private final URL url;

    public UrlResource(String str) throws MalformedURLException {
        Assert.notNull(str, "Path must not be null");
        this.uri = null;
        this.url = new URL(str);
        this.cleanedUrl = getCleanedUrl(this.url, str);
    }

    public UrlResource(String str, String str2) throws MalformedURLException {
        this(str, str2, null);
    }

    public UrlResource(String str, String str2, String str3) throws MalformedURLException {
        try {
            this.uri = new URI(str, str2, str3);
            this.url = this.uri.toURL();
            this.cleanedUrl = getCleanedUrl(this.url, this.uri.toString());
        } catch (URISyntaxException e) {
            MalformedURLException malformedURLException = new MalformedURLException(e.getMessage());
            malformedURLException.initCause(e);
            throw malformedURLException;
        }
    }

    public UrlResource(URI uri) throws MalformedURLException {
        Assert.notNull(uri, "URI must not be null");
        this.uri = uri;
        this.url = uri.toURL();
        this.cleanedUrl = getCleanedUrl(this.url, uri.toString());
    }

    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
        this.cleanedUrl = getCleanedUrl(this.url, url.toString());
        this.uri = null;
    }

    private URL getCleanedUrl(URL url, String str) {
        try {
            return new URL(StringUtils.cleanPath(str));
        } catch (MalformedURLException unused) {
            return url;
        }
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public Resource createRelative(String str) throws MalformedURLException {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        return new UrlResource(new URL(this.url, str));
    }

    @Override // org.springframework.core.io.AbstractResource
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof UrlResource) && this.cleanedUrl.equals(((UrlResource) obj).cleanedUrl));
    }

    @Override // org.springframework.core.io.Resource
    public String getDescription() {
        return "URL [" + this.url + "]";
    }

    @Override // org.springframework.core.io.AbstractFileResolvingResource, org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public File getFile() throws IOException {
        return this.uri != null ? super.getFile(this.uri) : super.getFile();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public String getFilename() {
        return new File(this.url.getFile()).getName();
    }

    @Override // org.springframework.core.io.InputStreamSource
    public InputStream getInputStream() throws IOException {
        URLConnection openConnection = this.url.openConnection();
        ResourceUtils.useCachesIfNecessary(openConnection);
        try {
            return openConnection.getInputStream();
        } catch (IOException e) {
            if (openConnection instanceof HttpURLConnection) {
                ((HttpURLConnection) openConnection).disconnect();
            }
            throw e;
        }
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public URI getURI() throws IOException {
        return this.uri != null ? this.uri : super.getURI();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public URL getURL() throws IOException {
        return this.url;
    }

    @Override // org.springframework.core.io.AbstractResource
    public int hashCode() {
        return this.cleanedUrl.hashCode();
    }
}
