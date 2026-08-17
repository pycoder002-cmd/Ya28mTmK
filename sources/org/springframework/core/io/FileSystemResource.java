package org.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public class FileSystemResource extends AbstractResource implements WritableResource {
    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        Assert.notNull(file, "File must not be null");
        this.file = file;
        this.path = StringUtils.cleanPath(file.getPath());
    }

    public FileSystemResource(String str) {
        Assert.notNull(str, "Path must not be null");
        this.file = new File(str);
        this.path = StringUtils.cleanPath(str);
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public long contentLength() throws IOException {
        return this.file.length();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public Resource createRelative(String str) {
        return new FileSystemResource(StringUtils.applyRelativePath(this.path, str));
    }

    @Override // org.springframework.core.io.AbstractResource
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof FileSystemResource) && this.path.equals(((FileSystemResource) obj).path));
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean exists() {
        return this.file.exists();
    }

    @Override // org.springframework.core.io.Resource
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public File getFile() {
        return this.file;
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public String getFilename() {
        return this.file.getName();
    }

    @Override // org.springframework.core.io.InputStreamSource
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override // org.springframework.core.io.WritableResource
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.file);
    }

    public final String getPath() {
        return this.path;
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public URI getURI() throws IOException {
        return this.file.toURI();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public URL getURL() throws IOException {
        return this.file.toURI().toURL();
    }

    @Override // org.springframework.core.io.AbstractResource
    public int hashCode() {
        return this.path.hashCode();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean isReadable() {
        return this.file.canRead() && !this.file.isDirectory();
    }

    @Override // org.springframework.core.io.WritableResource
    public boolean isWritable() {
        return this.file.canWrite() && !this.file.isDirectory();
    }
}
