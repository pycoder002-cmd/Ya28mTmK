package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.springframework.core.NestedIOException;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

/* loaded from: classes2.dex */
public abstract class AbstractResource implements Resource {
    @Override // org.springframework.core.io.Resource
    public long contentLength() throws IOException {
        InputStream inputStream = getInputStream();
        Assert.state(inputStream != null, "resource input stream must not be null");
        long j = 0;
        try {
            byte[] bArr = new byte[255];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    j += read;
                } else {
                    try {
                        break;
                    } catch (IOException unused) {
                    }
                }
            }
            return j;
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        }
    }

    @Override // org.springframework.core.io.Resource
    public Resource createRelative(String str) throws IOException {
        throw new FileNotFoundException("Cannot create a relative resource for " + getDescription());
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Resource) && ((Resource) obj).getDescription().equals(getDescription()));
    }

    @Override // org.springframework.core.io.Resource
    public boolean exists() {
        try {
            try {
                return getFile().exists();
            } catch (IOException unused) {
                getInputStream().close();
                return true;
            }
        } catch (Throwable unused2) {
            return false;
        }
    }

    @Override // org.springframework.core.io.Resource
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
    }

    protected File getFileForLastModifiedCheck() throws IOException {
        return getFile();
    }

    @Override // org.springframework.core.io.Resource
    public String getFilename() {
        return null;
    }

    @Override // org.springframework.core.io.Resource
    public URI getURI() throws IOException {
        URL url = getURL();
        try {
            return ResourceUtils.toURI(url);
        } catch (URISyntaxException e) {
            throw new NestedIOException("Invalid URI [" + url + "]", e);
        }
    }

    @Override // org.springframework.core.io.Resource
    public URL getURL() throws IOException {
        throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
    }

    public int hashCode() {
        return getDescription().hashCode();
    }

    @Override // org.springframework.core.io.Resource
    public boolean isOpen() {
        return false;
    }

    @Override // org.springframework.core.io.Resource
    public boolean isReadable() {
        return true;
    }

    @Override // org.springframework.core.io.Resource
    public long lastModified() throws IOException {
        long lastModified = getFileForLastModifiedCheck().lastModified();
        if (lastModified != 0) {
            return lastModified;
        }
        throw new FileNotFoundException(getDescription() + " cannot be resolved in the file system for resolving its last-modified timestamp");
    }

    public String toString() {
        return getDescription();
    }
}
