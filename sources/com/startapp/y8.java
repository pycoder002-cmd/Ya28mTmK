package com.startapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import java.lang.reflect.Method;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class y8 {
    public static volatile Application a;

    public static Context a(Context context) {
        Application application;
        Context context2 = a;
        if (context2 == null) {
            try {
                if (context instanceof Application) {
                    try {
                        a = (Application) context;
                    } catch (Throwable unused) {
                    }
                    context2 = context;
                } else {
                    context2 = context2;
                    if (context instanceof ContextWrapper) {
                        Context baseContext = ((ContextWrapper) context).getBaseContext();
                        context2 = context2;
                        if (baseContext != null) {
                            context2 = a(baseContext);
                        }
                    } else if (context != null) {
                        context2 = context.getApplicationContext();
                    }
                }
            } catch (Throwable unused2) {
            }
            if (context2 == null) {
                Application application2 = a;
                context2 = application2;
                if (application2 == null) {
                    synchronized (y8.class) {
                        Application application3 = a;
                        application = application3;
                        if (application3 == null) {
                            try {
                                Method declaredMethod = Class.forName(Activity.class.getName() + Thread.class.getSimpleName()).getDeclaredMethod(new String(new byte[]{99, 117, 114, 114, 101, 110, 116}) + Application.class.getSimpleName(), new Class[0]);
                                declaredMethod.setAccessible(true);
                                application3 = (Application) declaredMethod.invoke(null, new Object[0]);
                            } catch (Throwable unused3) {
                            }
                            a = application3;
                            application = application3;
                        }
                    }
                    context2 = application;
                }
                if (context2 == null) {
                    return context;
                }
            }
        }
        return context2;
    }

    public static Context b(Context context) {
        Context a2 = a(context);
        return a2 != null ? a2 : context;
    }
}
