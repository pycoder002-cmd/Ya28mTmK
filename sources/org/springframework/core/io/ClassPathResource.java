package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public class ClassPathResource extends AbstractFileResolvingResource {
    private ClassLoader classLoader;
    private Class<?> clazz;
    private final String path;

    public ClassPathResource(String str) {
        this(str, (ClassLoader) null);
    }

    public ClassPathResource(String str, Class<?> cls) {
        Assert.notNull(str, "Path must not be null");
        this.path = StringUtils.cleanPath(str);
        this.clazz = cls;
    }

    public ClassPathResource(String str, ClassLoader classLoader) {
        Assert.notNull(str, "Path must not be null");
        String cleanPath = StringUtils.cleanPath(str);
        this.path = cleanPath.startsWith("/") ? cleanPath.substring(1) : cleanPath;
        this.classLoader = classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }

    protected ClassPathResource(String str, ClassLoader classLoader, Class<?> cls) {
        this.path = StringUtils.cleanPath(str);
        this.classLoader = classLoader;
        this.clazz = cls;
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public Resource createRelative(String str) {
        return new ClassPathResource(StringUtils.applyRelativePath(this.path, str), this.classLoader, this.clazz);
    }

    @Override // org.springframework.core.io.AbstractResource
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassPathResource)) {
            return false;
        }
        ClassPathResource classPathResource = (ClassPathResource) obj;
        return this.path.equals(classPathResource.path) && ObjectUtils.nullSafeEquals(this.classLoader, classPathResource.classLoader) && ObjectUtils.nullSafeEquals(this.clazz, classPathResource.clazz);
    }

    @Override // org.springframework.core.io.AbstractFileResolvingResource, org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean exists() {
        return resolveURL() != null;
    }

    public final ClassLoader getClassLoader() {
        return this.clazz != null ? this.clazz.getClassLoader() : this.classLoader;
    }

    @Override // org.springframework.core.io.Resource
    public String getDescription() {
        StringBuilder sb = new StringBuilder("class path resource [");
        String str = this.path;
        if (this.clazz != null && !str.startsWith("/")) {
            sb.append(ClassUtils.classPackageAsResourcePath(this.clazz));
            sb.append('/');
        }
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public String getFilename() {
        return StringUtils.getFilename(this.path);
    }

    @Override // org.springframework.core.io.InputStreamSource
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = this.clazz != null ? this.clazz.getResourceAsStream(this.path) : this.classLoader != null ? this.classLoader.getResourceAsStream(this.path) : ClassLoader.getSystemResourceAsStream(this.path);
        if (resourceAsStream != null) {
            return resourceAsStream;
        }
        throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
    }

    public final String getPath() {
        return this.path;
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public URL getURL() throws IOException {
        URL resolveURL = resolveURL();
        if (resolveURL != null) {
            return resolveURL;
        }
        throw new FileNotFoundException(getDescription() + " cannot be resolved to URL because it does not exist");
    }

    @Override // org.springframework.core.io.AbstractResource
    public int hashCode() {
        return this.path.hashCode();
    }

    protected URL resolveURL() {
        return this.clazz != null ? this.clazz.getResource(this.path) : this.classLoader != null ? this.classLoader.getResource(this.path) : ClassLoader.getSystemResource(this.path);
    }
}
