package org.springframework.util;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/* loaded from: classes2.dex */
public abstract class ResourceUtils {
    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    public static final String FILE_URL_PREFIX = "file:";
    public static final String JAR_URL_SEPARATOR = "!/";
    public static final String URL_PROTOCOL_CODE_SOURCE = "code-source";
    public static final String URL_PROTOCOL_FILE = "file";
    public static final String URL_PROTOCOL_JAR = "jar";
    public static final String URL_PROTOCOL_VFS = "vfs";
    public static final String URL_PROTOCOL_VFSFILE = "vfsfile";
    public static final String URL_PROTOCOL_VFSZIP = "vfszip";
    public static final String URL_PROTOCOL_WSJAR = "wsjar";
    public static final String URL_PROTOCOL_ZIP = "zip";

    public static URL extractJarFileURL(URL url) throws MalformedURLException {
        String file = url.getFile();
        int indexOf = file.indexOf(JAR_URL_SEPARATOR);
        if (indexOf == -1) {
            return url;
        }
        String substring = file.substring(0, indexOf);
        try {
            return new URL(substring);
        } catch (MalformedURLException unused) {
            if (!substring.startsWith("/")) {
                substring = "/" + substring;
            }
            return new URL(FILE_URL_PREFIX + substring);
        }
    }

    public static File getFile(String str) throws FileNotFoundException {
        Assert.notNull(str, "Resource location must not be null");
        if (!str.startsWith(CLASSPATH_URL_PREFIX)) {
            try {
                return getFile(new URL(str));
            } catch (MalformedURLException unused) {
                return new File(str);
            }
        }
        String substring = str.substring(CLASSPATH_URL_PREFIX.length());
        String str2 = "class path resource [" + substring + "]";
        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        URL resource = defaultClassLoader != null ? defaultClassLoader.getResource(substring) : ClassLoader.getSystemResource(substring);
        if (resource != null) {
            return getFile(resource, str2);
        }
        throw new FileNotFoundException(str2 + " cannot be resolved to absolute file path because it does not reside in the file system");
    }

    public static File getFile(URI uri) throws FileNotFoundException {
        return getFile(uri, "URI");
    }

    public static File getFile(URI uri, String str) throws FileNotFoundException {
        Assert.notNull(uri, "Resource URI must not be null");
        if (URL_PROTOCOL_FILE.equals(uri.getScheme())) {
            return new File(uri.getSchemeSpecificPart());
        }
        throw new FileNotFoundException(str + " cannot be resolved to absolute file path because it does not reside in the file system: " + uri);
    }

    public static File getFile(URL url) throws FileNotFoundException {
        return getFile(url, "URL");
    }

    public static File getFile(URL url, String str) throws FileNotFoundException {
        Assert.notNull(url, "Resource URL must not be null");
        if (URL_PROTOCOL_FILE.equals(url.getProtocol())) {
            try {
                return new File(toURI(url).getSchemeSpecificPart());
            } catch (URISyntaxException unused) {
                return new File(url.getFile());
            }
        }
        throw new FileNotFoundException(str + " cannot be resolved to absolute file path because it does not reside in the file system: " + url);
    }

    public static URL getURL(String str) throws FileNotFoundException {
        Assert.notNull(str, "Resource location must not be null");
        if (!str.startsWith(CLASSPATH_URL_PREFIX)) {
            try {
                try {
                    return new URL(str);
                } catch (MalformedURLException unused) {
                    throw new FileNotFoundException("Resource location [" + str + "] is neither a URL not a well-formed file path");
                }
            } catch (MalformedURLException unused2) {
                return new File(str).toURI().toURL();
            }
        }
        String substring = str.substring(CLASSPATH_URL_PREFIX.length());
        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        URL resource = defaultClassLoader != null ? defaultClassLoader.getResource(substring) : ClassLoader.getSystemResource(substring);
        if (resource != null) {
            return resource;
        }
        throw new FileNotFoundException(("class path resource [" + substring + "]") + " cannot be resolved to URL because it does not exist");
    }

    public static boolean isFileURL(URL url) {
        String protocol = url.getProtocol();
        return URL_PROTOCOL_FILE.equals(protocol) || URL_PROTOCOL_VFSFILE.equals(protocol) || URL_PROTOCOL_VFS.equals(protocol);
    }

    public static boolean isJarURL(URL url) {
        String protocol = url.getProtocol();
        return URL_PROTOCOL_JAR.equals(protocol) || URL_PROTOCOL_ZIP.equals(protocol) || URL_PROTOCOL_VFSZIP.equals(protocol) || URL_PROTOCOL_WSJAR.equals(protocol) || (URL_PROTOCOL_CODE_SOURCE.equals(protocol) && url.getPath().contains(JAR_URL_SEPARATOR));
    }

    public static boolean isUrl(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith(CLASSPATH_URL_PREFIX)) {
            return true;
        }
        try {
            new URL(str);
            return true;
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    public static URI toURI(String str) throws URISyntaxException {
        return new URI(StringUtils.replace(str, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
    }

    public static URI toURI(URL url) throws URISyntaxException {
        return toURI(url.toString());
    }

    public static void useCachesIfNecessary(URLConnection uRLConnection) {
        uRLConnection.setUseCaches(uRLConnection.getClass().getSimpleName().startsWith("JNLP"));
    }
}
