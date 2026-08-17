package org.springframework.core.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ByteArrayResource extends AbstractResource {
    private final byte[] byteArray;
    private final String description;

    public ByteArrayResource(byte[] bArr) {
        this(bArr, "resource loaded from byte array");
    }

    public ByteArrayResource(byte[] bArr, String str) {
        if (bArr == null) {
            throw new IllegalArgumentException("Byte array must not be null");
        }
        this.byteArray = bArr;
        this.description = str == null ? "" : str;
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public long contentLength() {
        return this.byteArray.length;
    }

    @Override // org.springframework.core.io.AbstractResource
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof ByteArrayResource) && Arrays.equals(((ByteArrayResource) obj).byteArray, this.byteArray));
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean exists() {
        return true;
    }

    public final byte[] getByteArray() {
        return this.byteArray;
    }

    @Override // org.springframework.core.io.Resource
    public String getDescription() {
        return this.description;
    }

    @Override // org.springframework.core.io.InputStreamSource
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.byteArray);
    }

    @Override // org.springframework.core.io.AbstractResource
    public int hashCode() {
        return byte[].class.hashCode() * 29 * this.byteArray.length;
    }
}
