package org.springframework.util;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public abstract class Base64Utils {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final Base64Delegate delegate = new AndroidBase64Delegate();

    /* loaded from: classes2.dex */
    private static class AndroidBase64Delegate implements Base64Delegate {
        private AndroidBase64Delegate() {
        }

        @Override // org.springframework.util.Base64Utils.Base64Delegate
        public byte[] decode(byte[] bArr) {
            return (bArr == null || bArr.length == 0) ? bArr : Base64.decode(bArr, 2);
        }

        @Override // org.springframework.util.Base64Utils.Base64Delegate
        public byte[] encode(byte[] bArr) {
            return (bArr == null || bArr.length == 0) ? bArr : Base64.encode(bArr, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface Base64Delegate {
        byte[] decode(byte[] bArr);

        byte[] encode(byte[] bArr);
    }

    @Deprecated
    public static byte[] decode(String str) {
        return decodeFromString(str);
    }

    public static byte[] decode(byte[] bArr) {
        return delegate.decode(bArr);
    }

    public static byte[] decodeFromString(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        try {
            return delegate.decode(str.getBytes(DEFAULT_CHARSET.displayName()));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static byte[] encode(byte[] bArr) {
        return delegate.encode(bArr);
    }

    public static String encodeToString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return "";
        }
        try {
            return new String(delegate.encode(bArr), DEFAULT_CHARSET.displayName());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
