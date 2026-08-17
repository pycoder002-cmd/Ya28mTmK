package com.startapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class c9 {

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a extends FilterInputStream {
        public a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long skip = ((FilterInputStream) this).in.skip(j - j2);
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    skip = 1;
                }
                j2 += skip;
            }
            return j2;
        }
    }

    public static Bitmap a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Drawable a(Resources resources, String str) {
        return new BitmapDrawable(resources, a(str));
    }

    public static Bitmap b(String str) {
        Bitmap bitmap;
        HttpURLConnection httpURLConnection = null;
        Bitmap bitmap2 = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.connect();
                InputStream inputStream = httpURLConnection2.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap2 = BitmapFactory.decodeStream(new a(inputStream));
                bufferedInputStream.close();
                inputStream.close();
                httpURLConnection2.disconnect();
                return bitmap2;
            } catch (Throwable unused) {
                Bitmap bitmap3 = bitmap2;
                httpURLConnection = httpURLConnection2;
                bitmap = bitmap3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return bitmap;
            }
        } catch (Throwable unused2) {
            bitmap = null;
        }
    }
}
