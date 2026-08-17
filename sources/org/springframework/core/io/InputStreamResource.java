package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class InputStreamResource extends AbstractResource {
    private final String description;
    private final InputStream inputStream;
    private boolean read;

    public InputStreamResource(InputStream inputStream) {
        this(inputStream, "resource loaded through InputStream");
    }

    public InputStreamResource(InputStream inputStream, String str) {
        this.read = false;
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null");
        }
        this.inputStream = inputStream;
        this.description = str == null ? "" : str;
    }

    @Override // org.springframework.core.io.AbstractResource
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof InputStreamResource) && ((InputStreamResource) obj).inputStream.equals(this.inputStream));
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean exists() {
        return true;
    }

    @Override // org.springframework.core.io.Resource
    public String getDescription() {
        return this.description;
    }

    @Override // org.springframework.core.io.InputStreamSource
    public InputStream getInputStream() throws IOException, IllegalStateException {
        if (this.read) {
            throw new IllegalStateException("InputStream has already been read - do not use InputStreamResource if a stream needs to be read multiple times");
        }
        this.read = true;
        return this.inputStream;
    }

    @Override // org.springframework.core.io.AbstractResource
    public int hashCode() {
        return this.inputStream.hashCode();
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean isOpen() {
        return true;
    }
}
