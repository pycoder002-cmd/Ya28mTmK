package org.springframework.util;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public abstract class StreamUtils {
    public static final int BUFFER_SIZE = 4096;

    /* loaded from: classes2.dex */
    private static class NonClosingInputStream extends FilterInputStream {
        public NonClosingInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }

    /* loaded from: classes2.dex */
    private static class NonClosingOutputStream extends FilterOutputStream {
        public NonClosingOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.out.write(bArr, i, i2);
        }
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Assert.notNull(inputStream, "No InputStream specified");
        Assert.notNull(outputStream, "No OutputStream specified");
        byte[] bArr = new byte[4096];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return i;
            }
            outputStream.write(bArr, 0, read);
            i += read;
        }
    }

    public static void copy(String str, Charset charset, OutputStream outputStream) throws IOException {
        Assert.notNull(str, "No input String specified");
        Assert.notNull(charset, "No charset specified");
        Assert.notNull(outputStream, "No OutputStream specified");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
    }

    public static void copy(byte[] bArr, OutputStream outputStream) throws IOException {
        Assert.notNull(bArr, "No input byte array specified");
        Assert.notNull(outputStream, "No OutputStream specified");
        outputStream.write(bArr);
    }

    public static byte[] copyToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static String copyToString(InputStream inputStream, Charset charset) throws IOException {
        Assert.notNull(inputStream, "No InputStream specified");
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        char[] cArr = new char[4096];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    public static InputStream nonClosing(InputStream inputStream) {
        Assert.notNull(inputStream, "No InputStream specified");
        return new NonClosingInputStream(inputStream);
    }

    public static OutputStream nonClosing(OutputStream outputStream) {
        Assert.notNull(outputStream, "No OutputStream specified");
        return new NonClosingOutputStream(outputStream);
    }
}
