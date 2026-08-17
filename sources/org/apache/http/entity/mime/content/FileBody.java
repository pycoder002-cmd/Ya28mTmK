package org.apache.http.entity.mime.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.ContentType;
import org.apache.http.util.Args;

/* loaded from: classes2.dex */
public class FileBody extends AbstractContentBody {
    private final File file;
    private final String filename;

    public FileBody(File file) {
        this(file, ContentType.DEFAULT_BINARY, file != null ? file.getName() : null);
    }

    @Deprecated
    public FileBody(File file, String str) {
        this(file, ContentType.create(str), (String) null);
    }

    @Deprecated
    public FileBody(File file, String str, String str2) {
        this(file, null, str, str2);
    }

    @Deprecated
    public FileBody(File file, String str, String str2, String str3) {
        this(file, ContentType.create(str2, str3), str);
    }

    public FileBody(File file, ContentType contentType) {
        this(file, contentType, file != null ? file.getName() : null);
    }

    public FileBody(File file, ContentType contentType, String str) {
        super(contentType);
        Args.notNull(file, "File");
        this.file = file;
        this.filename = str == null ? file.getName() : str;
    }

    @Override // org.apache.http.entity.mime.content.ContentDescriptor
    public long getContentLength() {
        return this.file.length();
    }

    public File getFile() {
        return this.file;
    }

    @Override // org.apache.http.entity.mime.content.ContentBody
    public String getFilename() {
        return this.filename;
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override // org.apache.http.entity.mime.content.ContentDescriptor
    public String getTransferEncoding() {
        return "binary";
    }

    @Override // org.apache.http.entity.mime.content.ContentBody
    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        FileInputStream fileInputStream = new FileInputStream(this.file);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            fileInputStream.close();
        }
    }
}
