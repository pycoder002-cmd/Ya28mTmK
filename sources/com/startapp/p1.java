package com.startapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import com.startapp.networkTest.enums.AppCategoryTypes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p1 {

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements DialogInterface.OnClickListener {
        public final /* synthetic */ Activity a;

        public a(Activity activity) {
            this.a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            p1.c(this.a);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements DialogInterface.OnClickListener {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ Activity b;

        public b(boolean z, Activity activity) {
            this.a = z;
            this.b = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (this.a) {
                this.b.finish();
            }
        }
    }

    public static AppCategoryTypes a(int i) {
        AppCategoryTypes appCategoryTypes = AppCategoryTypes.Unknown;
        switch (i) {
            case -1:
                return AppCategoryTypes.Undefined;
            case 0:
                return AppCategoryTypes.Game;
            case 1:
                return AppCategoryTypes.Audio;
            case 2:
                return AppCategoryTypes.Video;
            case 3:
                return AppCategoryTypes.Image;
            case 4:
                return AppCategoryTypes.Social;
            case 5:
                return AppCategoryTypes.News;
            case 6:
                return AppCategoryTypes.Maps;
            case 7:
                return AppCategoryTypes.Productivity;
            default:
                return appCategoryTypes;
        }
    }

    public static boolean a(Activity activity, int i, int i2, int i3, int i4, boolean z) {
        if (!a(activity)) {
            return false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(i);
        builder.setCancelable(false);
        builder.setMessage(i2);
        builder.setPositiveButton(i3, new a(activity));
        builder.setNegativeButton(i4, new b(z, activity));
        builder.create().show();
        return true;
    }

    public static boolean a(Context context) {
        return Build.VERSION.SDK_INT >= 21 && new Intent("android.settings.USAGE_ACCESS_SETTINGS").resolveActivity(context.getPackageManager()) != null;
    }

    public static boolean b(Context context) {
        return Build.VERSION.SDK_INT < 21 || ((AppOpsManager) context.getApplicationContext().getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), context.getApplicationContext().getPackageName()) == 0;
    }

    public static boolean c(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
                intent.setFlags(411074560);
                context.startActivity(intent);
                return true;
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        return false;
    }
}
