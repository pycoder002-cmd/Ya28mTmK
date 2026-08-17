package com.startapp;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class yd implements be {
    public final ae a;

    public yd(ae aeVar) {
        this.a = aeVar;
    }

    @Override // com.startapp.be
    public String a(String str) {
        GZIPOutputStream gZIPOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream2.write(str.getBytes());
                xd.a(gZIPOutputStream2);
                String a = this.a.a(ud.b(byteArrayOutputStream.toByteArray()));
                xd.a(gZIPOutputStream2);
                return a;
            } catch (Exception unused) {
                gZIPOutputStream = gZIPOutputStream2;
                xd.a(gZIPOutputStream);
                return "";
            } catch (Throwable th) {
                th = th;
                gZIPOutputStream = gZIPOutputStream2;
                xd.a(gZIPOutputStream);
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
