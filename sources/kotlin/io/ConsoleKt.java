package kotlin.io;

import io.sentry.marshaller.json.JsonMarshaller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Console.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000eH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0010H\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0011H\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0012H\u0087\b\u001a\t\u0010\u0013\u001a\u00020\u0007H\u0087\b\u001a\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fH\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000eH\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0010H\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0011H\u0087\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0012H\u0087\b\u001a\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0016"}, d2 = {"stdin", "Ljava/io/BufferedReader;", "getStdin", "()Ljava/io/BufferedReader;", "stdin$delegate", "Lkotlin/Lazy;", "print", "", JsonMarshaller.MESSAGE, "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 9})
@JvmName(name = "ConsoleKt")
/* loaded from: classes2.dex */
public final class ConsoleKt {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinPackage(ConsoleKt.class, "kotlin-stdlib"), "stdin", "getStdin()Ljava/io/BufferedReader;"))};
    private static final Lazy stdin$delegate = LazyKt.lazy(new Function0<BufferedReader>() { // from class: kotlin.io.ConsoleKt$stdin$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final BufferedReader invoke() {
            return new BufferedReader(new InputStreamReader(new InputStream() { // from class: kotlin.io.ConsoleKt$stdin$2.1
                @Override // java.io.InputStream
                public int available() {
                    return System.in.available();
                }

                @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    System.in.close();
                }

                @Override // java.io.InputStream
                public void mark(int readlimit) {
                    System.in.mark(readlimit);
                }

                @Override // java.io.InputStream
                public boolean markSupported() {
                    return System.in.markSupported();
                }

                @Override // java.io.InputStream
                public int read() {
                    return System.in.read();
                }

                @Override // java.io.InputStream
                public int read(@NotNull byte[] b) {
                    Intrinsics.checkParameterIsNotNull(b, "b");
                    return System.in.read(b);
                }

                @Override // java.io.InputStream
                public int read(@NotNull byte[] b, int off, int len) {
                    Intrinsics.checkParameterIsNotNull(b, "b");
                    return System.in.read(b, off, len);
                }

                @Override // java.io.InputStream
                public void reset() {
                    System.in.reset();
                }

                @Override // java.io.InputStream
                public long skip(long n) {
                    return System.in.skip(n);
                }
            }));
        }
    });

    private static final BufferedReader getStdin() {
        Lazy lazy = stdin$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (BufferedReader) lazy.getValue();
    }

    @InlineOnly
    private static final void print(byte b) {
        System.out.print(Byte.valueOf(b));
    }

    @InlineOnly
    private static final void print(char c) {
        System.out.print(c);
    }

    @InlineOnly
    private static final void print(double d) {
        System.out.print(d);
    }

    @InlineOnly
    private static final void print(float f) {
        System.out.print(f);
    }

    @InlineOnly
    private static final void print(int i) {
        System.out.print(i);
    }

    @InlineOnly
    private static final void print(long j) {
        System.out.print(j);
    }

    @InlineOnly
    private static final void print(Object obj) {
        System.out.print(obj);
    }

    @InlineOnly
    private static final void print(short s) {
        System.out.print(Short.valueOf(s));
    }

    @InlineOnly
    private static final void print(boolean z) {
        System.out.print(z);
    }

    @InlineOnly
    private static final void print(char[] cArr) {
        System.out.print(cArr);
    }

    @InlineOnly
    private static final void println() {
        System.out.println();
    }

    @InlineOnly
    private static final void println(byte b) {
        System.out.println(Byte.valueOf(b));
    }

    @InlineOnly
    private static final void println(char c) {
        System.out.println(c);
    }

    @InlineOnly
    private static final void println(double d) {
        System.out.println(d);
    }

    @InlineOnly
    private static final void println(float f) {
        System.out.println(f);
    }

    @InlineOnly
    private static final void println(int i) {
        System.out.println(i);
    }

    @InlineOnly
    private static final void println(long j) {
        System.out.println(j);
    }

    @InlineOnly
    private static final void println(Object obj) {
        System.out.println(obj);
    }

    @InlineOnly
    private static final void println(short s) {
        System.out.println(Short.valueOf(s));
    }

    @InlineOnly
    private static final void println(boolean z) {
        System.out.println(z);
    }

    @InlineOnly
    private static final void println(char[] cArr) {
        System.out.println(cArr);
    }

    @Nullable
    public static final String readLine() {
        return getStdin().readLine();
    }
}
