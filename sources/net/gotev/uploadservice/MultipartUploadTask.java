package net.gotev.uploadservice;

import android.content.Intent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import net.gotev.uploadservice.http.HttpConnection;

/* loaded from: classes2.dex */
public class MultipartUploadTask extends HttpUploadTask {
    private static final String BOUNDARY_SIGNATURE = "-------AndroidUploadService";
    private static final String NEW_LINE = "\r\n";
    protected static final String PARAM_UTF8_CHARSET = "multipartUtf8Charset";
    protected static final String PROPERTY_CONTENT_TYPE = "httpContentType";
    protected static final String PROPERTY_PARAM_NAME = "httpParamName";
    protected static final String PROPERTY_REMOTE_FILE_NAME = "httpRemoteFileName";
    private static final String TWO_HYPHENS = "--";
    private static final Charset US_ASCII = Charset.forName("US-ASCII");
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private String boundary;
    private byte[] boundaryBytes;
    private boolean isUtf8Charset;
    private byte[] trailerBytes;

    private String getBoundary() {
        return BOUNDARY_SIGNATURE + System.currentTimeMillis();
    }

    private byte[] getBoundaryBytes() throws UnsupportedEncodingException {
        return ("\r\n--" + this.boundary + NEW_LINE).getBytes(US_ASCII);
    }

    private long getFilesLength() throws UnsupportedEncodingException {
        Iterator<UploadFile> it = this.params.getFiles().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += getTotalMultipartBytes(it.next(), this.isUtf8Charset);
        }
        return j;
    }

    private byte[] getMultipartHeader(UploadFile uploadFile, boolean z) throws UnsupportedEncodingException {
        return ("Content-Disposition: form-data; name=\"" + uploadFile.getProperty(PROPERTY_PARAM_NAME) + "\"; filename=\"" + uploadFile.getProperty(PROPERTY_REMOTE_FILE_NAME) + "\"" + NEW_LINE + "Content-Type: " + uploadFile.getProperty(PROPERTY_CONTENT_TYPE) + NEW_LINE + NEW_LINE).getBytes(z ? UTF8 : US_ASCII);
    }

    private long getRequestParametersLength() throws UnsupportedEncodingException {
        long j = 0;
        if (!this.httpParams.getRequestParameters().isEmpty()) {
            while (this.httpParams.getRequestParameters().iterator().hasNext()) {
                j += this.boundaryBytes.length + r0.next().getMultipartBytes(this.isUtf8Charset).length;
            }
        }
        return j;
    }

    private long getTotalMultipartBytes(UploadFile uploadFile, boolean z) throws UnsupportedEncodingException {
        return this.boundaryBytes.length + getMultipartHeader(uploadFile, z).length + uploadFile.length();
    }

    private byte[] getTrailerBytes() throws UnsupportedEncodingException {
        return ("\r\n--" + this.boundary + TWO_HYPHENS + NEW_LINE).getBytes(US_ASCII);
    }

    private void writeFiles(HttpConnection httpConnection) throws IOException {
        for (UploadFile uploadFile : this.params.getFiles()) {
            if (!this.shouldContinue) {
                return;
            }
            httpConnection.writeBody(this.boundaryBytes);
            httpConnection.writeBody(getMultipartHeader(uploadFile, this.isUtf8Charset));
            this.uploadedBytes += this.boundaryBytes.length + r2.length;
            broadcastProgress(this.uploadedBytes, this.totalBytes);
            writeStream(uploadFile.getStream());
        }
    }

    private void writeRequestParameters(HttpConnection httpConnection) throws IOException {
        if (this.httpParams.getRequestParameters().isEmpty()) {
            return;
        }
        for (NameValue nameValue : this.httpParams.getRequestParameters()) {
            httpConnection.writeBody(this.boundaryBytes);
            httpConnection.writeBody(nameValue.getMultipartBytes(this.isUtf8Charset));
            this.uploadedBytes += this.boundaryBytes.length + r1.length;
            broadcastProgress(this.uploadedBytes, this.totalBytes);
        }
    }

    @Override // net.gotev.uploadservice.HttpUploadTask
    protected long getBodyLength() throws UnsupportedEncodingException {
        return getRequestParametersLength() + getFilesLength() + this.trailerBytes.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.gotev.uploadservice.HttpUploadTask, net.gotev.uploadservice.UploadTask
    public void init(UploadService uploadService, Intent intent) throws IOException {
        super.init(uploadService, intent);
        this.boundary = getBoundary();
        this.boundaryBytes = getBoundaryBytes();
        this.trailerBytes = getTrailerBytes();
        this.isUtf8Charset = intent.getBooleanExtra(PARAM_UTF8_CHARSET, false);
        if (this.params.getFiles().size() <= 1) {
            this.httpParams.addRequestHeader("Connection", "close");
        } else {
            this.httpParams.addRequestHeader("Connection", "Keep-Alive");
        }
        this.httpParams.addRequestHeader("Content-Type", "multipart/form-data; boundary=" + this.boundary);
    }

    @Override // net.gotev.uploadservice.UploadTask
    protected void onSuccessfulUpload() {
        Iterator<UploadFile> it = this.params.getFiles().iterator();
        while (it.hasNext()) {
            addSuccessfullyUploadedFile(it.next().getAbsolutePath());
        }
    }

    @Override // net.gotev.uploadservice.HttpUploadTask
    protected void writeBody(HttpConnection httpConnection) throws IOException {
        writeRequestParameters(httpConnection);
        writeFiles(httpConnection);
        httpConnection.writeBody(this.trailerBytes);
    }
}
