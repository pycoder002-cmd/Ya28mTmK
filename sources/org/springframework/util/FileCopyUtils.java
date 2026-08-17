package org.springframework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/* loaded from: classes2.dex */
public abstract class FileCopyUtils {
    public static final int BUFFER_SIZE = 4096;

    public static int copy(File file, File file2) throws IOException {
        Assert.notNull(file, "No input File specified");
        Assert.notNull(file2, "No output File specified");
        return copy(new BufferedInputStream(new FileInputStream(file)), new BufferedOutputStream(new FileOutputStream(file2)));
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Assert.notNull(inputStream, "No InputStream specified");
        Assert.notNull(outputStream, "No OutputStream specified");
        try {
            int copy = StreamUtils.copy(inputStream, outputStream);
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            try {
                outputStream.close();
            } catch (IOException unused2) {
            }
            return copy;
        } finally {
        }
    }

    public static int copy(Reader reader, Writer writer) throws IOException {
        Assert.notNull(reader, "No Reader specified");
        Assert.notNull(writer, "No Writer specified");
        try {
            char[] cArr = new char[4096];
            int i = 0;
            while (true) {
                int read = reader.read(cArr);
                if (read == -1) {
                    break;
                }
                writer.write(cArr, 0, read);
                i += read;
            }
            writer.flush();
            try {
                reader.close();
            } catch (IOException unused) {
            }
            try {
                writer.close();
            } catch (IOException unused2) {
            }
            return i;
        } finally {
        }
    }

    public static void copy(String str, Writer writer) throws IOException {
        Assert.notNull(str, "No input String specified");
        Assert.notNull(writer, "No Writer specified");
        try {
            writer.write(str);
        } finally {
            try {
                writer.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void copy(byte[] bArr, File file) throws IOException {
        Assert.notNull(bArr, "No input byte array specified");
        Assert.notNull(file, "No output File specified");
        copy(new ByteArrayInputStream(bArr), new BufferedOutputStream(new FileOutputStream(file)));
    }

    public static void copy(byte[] bArr, OutputStream outputStream) throws IOException {
        Assert.notNull(bArr, "No input byte array specified");
        Assert.notNull(outputStream, "No OutputStream specified");
        try {
            outputStream.write(bArr);
        } finally {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] copyToByteArray(File file) throws IOException {
        Assert.notNull(file, "No input File specified");
        return copyToByteArray(new BufferedInputStream(new FileInputStream(file)));
    }

    public static byte[] copyToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static String copyToString(Reader reader) throws IOException {
        StringWriter stringWriter = new StringWriter();
        copy(reader, stringWriter);
        return stringWriter.toString();
    }
}
