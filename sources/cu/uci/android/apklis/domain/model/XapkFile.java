package cu.uci.android.apklis.domain.model;

import java.io.InputStream;

/* loaded from: classes.dex */
public class XapkFile {
    private InputStream file;
    private String location;
    private String storage;

    public XapkFile(String str, InputStream inputStream, String str2) {
        this.storage = str;
        this.file = inputStream;
        this.location = str2;
    }

    public InputStream getFile() {
        return this.file;
    }

    public String getLocation() {
        return this.location;
    }

    public String getStorage() {
        return this.storage;
    }
}
