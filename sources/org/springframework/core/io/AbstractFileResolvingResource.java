package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.util.ResourceUtils;

/* loaded from: classes2.dex */
public abstract class AbstractFileResolvingResource extends AbstractResource {
    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public long contentLength() throws IOException {
        URL url = getURL();
        if (ResourceUtils.isFileURL(url)) {
            return getFile().length();
        }
        customizeConnection(url.openConnection());
        return r0.getContentLength();
    }

    protected void customizeConnection(HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.setRequestMethod("HEAD");
    }

    protected void customizeConnection(URLConnection uRLConnection) throws IOException {
        ResourceUtils.useCachesIfNecessary(uRLConnection);
        if (uRLConnection instanceof HttpURLConnection) {
            customizeConnection((HttpURLConnection) uRLConnection);
        }
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean exists() {
        try {
            URL url = getURL();
            if (ResourceUtils.isFileURL(url)) {
                return getFile().exists();
            }
            URLConnection openConnection = url.openConnection();
            customizeConnection(openConnection);
            HttpURLConnection httpURLConnection = openConnection instanceof HttpURLConnection ? (HttpURLConnection) openConnection : null;
            if (httpURLConnection != null) {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    return true;
                }
                if (responseCode == 404) {
                    return false;
                }
            }
            if (openConnection.getContentLength() >= 0) {
                return true;
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                return false;
            }
            getInputStream().close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public File getFile() throws IOException {
        return ResourceUtils.getFile(getURL(), getDescription());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public File getFile(URI uri) throws IOException {
        return ResourceUtils.getFile(uri, getDescription());
    }

    @Override // org.springframework.core.io.AbstractResource
    protected File getFileForLastModifiedCheck() throws IOException {
        URL url = getURL();
        return ResourceUtils.isJarURL(url) ? ResourceUtils.getFile(ResourceUtils.extractJarFileURL(url), "Jar URL") : getFile();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean isReadable() {
        try {
            if (!ResourceUtils.isFileURL(getURL())) {
                return true;
            }
            File file = getFile();
            if (file.canRead()) {
                return !file.isDirectory();
            }
            return false;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public long lastModified() throws IOException {
        URL url = getURL();
        if (ResourceUtils.isFileURL(url) || ResourceUtils.isJarURL(url)) {
            return super.lastModified();
        }
        URLConnection openConnection = url.openConnection();
        customizeConnection(openConnection);
        return openConnection.getLastModified();
    }
}
