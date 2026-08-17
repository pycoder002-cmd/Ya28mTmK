package io.sentry.marshaller;

import io.sentry.event.Event;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface Marshaller {

    /* loaded from: classes2.dex */
    public static final class UncloseableOutputStream extends OutputStream {
        private final OutputStream originalStream;

        public UncloseableOutputStream(OutputStream outputStream) {
            this.originalStream = outputStream;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            this.originalStream.flush();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.originalStream.write(i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.originalStream.write(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.originalStream.write(bArr, i, i2);
        }
    }

    String getContentEncoding();

    String getContentType();

    void marshall(Event event, OutputStream outputStream) throws IOException;
}
