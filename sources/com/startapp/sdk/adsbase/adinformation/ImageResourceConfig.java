package com.startapp.sdk.adsbase.adinformation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import com.startapp.a9;
import com.startapp.aa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ImageResourceConfig implements Serializable {
    private static final long serialVersionUID = 1;
    public transient Bitmap a;
    public transient Bitmap b;
    private String name;
    private String imageUrlSecured = "";
    private String imageFallbackUrl = "";
    public transient Bitmap c = null;
    private int width = 1;
    private int height = 1;

    public static ImageResourceConfig a(String str) {
        ImageResourceConfig imageResourceConfig = new ImageResourceConfig();
        imageResourceConfig.name = str;
        return imageResourceConfig;
    }

    public int a() {
        return this.height;
    }

    public Bitmap a(Context context) {
        if (this.c == null) {
            Bitmap bitmap = this.a;
            this.c = bitmap;
            if (bitmap == null) {
                if (this.b == null) {
                    this.b = a9.a(context, this.imageFallbackUrl);
                }
                this.c = this.b;
            }
        }
        return this.c;
    }

    public void a(int i) {
        this.height = i;
    }

    public String b() {
        String str = this.imageUrlSecured;
        return str != null ? str : "";
    }

    public void b(int i) {
        this.width = i;
    }

    public void b(String str) {
        this.imageFallbackUrl = str;
    }

    public String c() {
        return this.name;
    }

    public int d() {
        return this.width;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageResourceConfig.class != obj.getClass()) {
            return false;
        }
        ImageResourceConfig imageResourceConfig = (ImageResourceConfig) obj;
        return this.width == imageResourceConfig.width && this.height == imageResourceConfig.height && aa.a(this.imageUrlSecured, imageResourceConfig.imageUrlSecured) && aa.a(this.imageFallbackUrl, imageResourceConfig.imageFallbackUrl) && aa.a(this.name, imageResourceConfig.name);
    }

    public int hashCode() {
        Object[] objArr = {this.imageUrlSecured, this.imageFallbackUrl, Integer.valueOf(this.width), Integer.valueOf(this.height), this.name};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
