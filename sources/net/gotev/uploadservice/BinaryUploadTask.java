package net.gotev.uploadservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import net.gotev.uploadservice.http.HttpConnection;

/* loaded from: classes2.dex */
public class BinaryUploadTask extends HttpUploadTask {
    @Override // net.gotev.uploadservice.HttpUploadTask
    protected long getBodyLength() throws UnsupportedEncodingException {
        return this.params.getFiles().get(0).length();
    }

    @Override // net.gotev.uploadservice.UploadTask
    protected void onSuccessfulUpload() {
        addSuccessfullyUploadedFile(this.params.getFiles().get(0).getAbsolutePath());
    }

    @Override // net.gotev.uploadservice.HttpUploadTask
    protected void writeBody(HttpConnection httpConnection) throws IOException {
        writeStream(this.params.getFiles().get(0).getStream());
    }
}
