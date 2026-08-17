package org.springframework.core.io;

import android.content.res.AssetManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class AssetResource extends AbstractResource {
    private final AssetManager assetManager;
    private final String fileName;

    public AssetResource(AssetManager assetManager, String str) {
        Assert.notNull(assetManager, "assetManager must not be null");
        Assert.notNull(str, "fileName must not be null");
        this.assetManager = assetManager;
        this.fileName = str;
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public long contentLength() throws IOException {
        return this.assetManager.openFd(this.fileName).getLength();
    }

    @Override // org.springframework.core.io.AbstractResource
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof AssetResource) && this.fileName.equals(((AssetResource) obj).fileName));
    }

    @Override // org.springframework.core.io.AbstractResource, org.springframework.core.io.Resource
    public boolean exists() {
        try {
            return this.assetManager.open(this.fileName) != null;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // org.springframework.core.io.Resource
    public String getDescription() {
        return "asset [" + this.fileName + "]";
    }

    @Override // org.springframework.core.io.InputStreamSource
    public InputStream getInputStream() throws IOException {
        InputStream open = this.assetManager.open(this.fileName);
        if (open != null) {
            return open;
        }
        throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
    }

    @Override // org.springframework.core.io.AbstractResource
    public int hashCode() {
        return this.fileName.hashCode();
    }
}
