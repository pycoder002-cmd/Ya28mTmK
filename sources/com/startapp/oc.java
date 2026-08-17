package com.startapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.startapp.pc;
import java.lang.reflect.Constructor;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class oc {
    public static final String a = "oc";

    public abstract void a(pc pcVar);

    public boolean a(Context context, String[] strArr, pc.a aVar, Bundle bundle) {
        if (strArr == null || strArr.length == 0) {
            Log.e(a, "Class name is empty");
            return false;
        }
        boolean z = false;
        for (String str : strArr) {
            Class cls = null;
            try {
                cls = Class.forName(str).asSubclass(pc.class);
            } catch (ClassNotFoundException unused) {
            } catch (Throwable unused2) {
                Log.e(a, "Invalid class: " + str);
            }
            if (cls != null) {
                try {
                    Constructor declaredConstructor = cls.getDeclaredConstructor(Context.class, pc.a.class, Bundle.class);
                    declaredConstructor.setAccessible(true);
                    a((pc) declaredConstructor.newInstance(y8.b(context), aVar, bundle));
                    z = true;
                } catch (Throwable unused3) {
                    Log.e(a, "Could not instantiate " + str);
                }
            }
        }
        return z;
    }
}
