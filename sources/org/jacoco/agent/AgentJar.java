package org.jacoco.agent;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* loaded from: classes2.dex */
public final class AgentJar {
    private static final String RESOURCE = "/jacocoagent.jar";
    private static final String ERRORMSG = String.format("The resource %s has not been found. Please see /org.jacoco.agent/README.TXT for details.", RESOURCE);

    private AgentJar() {
    }

    public static void extractTo(File file) throws IOException {
        FileOutputStream fileOutputStream;
        InputStream resourceAsStream = getResourceAsStream();
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = resourceAsStream.read(bArr);
                    if (read == -1) {
                        safeClose(resourceAsStream);
                        safeClose(fileOutputStream);
                        return;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } catch (Throwable th) {
                th = th;
                safeClose(resourceAsStream);
                safeClose(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    public static File extractToTempLocation() throws IOException {
        File createTempFile = File.createTempFile("jacocoagent", ".jar");
        createTempFile.deleteOnExit();
        extractTo(createTempFile);
        return createTempFile;
    }

    public static URL getResource() {
        URL resource = AgentJar.class.getResource(RESOURCE);
        if (resource == null) {
            throw new AssertionError(ERRORMSG);
        }
        return resource;
    }

    public static InputStream getResourceAsStream() {
        InputStream resourceAsStream = AgentJar.class.getResourceAsStream(RESOURCE);
        if (resourceAsStream == null) {
            throw new AssertionError(ERRORMSG);
        }
        return resourceAsStream;
    }

    private static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
