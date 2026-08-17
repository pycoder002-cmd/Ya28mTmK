package com.example.permissionrequest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

/* loaded from: classes.dex */
public class PermissionRequest {
    private static Activity activity;
    private static String cancelBtnTitle;
    private static Context context;
    public static PermissionRequestListener listener;
    private static String permanentPermissionDenyMessage;
    public static String permission;
    private static String permissionDenyMessage;
    private static String permissionDenyTitle;
    public static String permissionName;
    private static PermissionRequest permissionRequestInstance;
    private static String retryBtnTitle;
    private static String settingBtnTitle;
    private static String yesBtnTitle;

    private PermissionRequest(String str, String str2, Context context2) {
        listener = null;
        activity = null;
        permission = str;
        permissionName = str2;
        context = context2;
        permissionRequestInstance = this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void finishActivity() {
        if (activity != null) {
            activity.finish();
        }
        activity = null;
    }

    public static void handlePermissionRequest(String[] strArr, int[] iArr) {
        final String str = strArr[0];
        if (iArr.length <= 0) {
            finishActivity();
            return;
        }
        if (iArr[0] != -1) {
            listener.onPermissionAcept(str);
            finishActivity();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
            new AlertDialog.Builder(activity).setCancelable(false).setTitle(permissionDenyTitle).setMessage(permissionDenyMessage).setNegativeButton(retryBtnTitle, new DialogInterface.OnClickListener() { // from class: com.example.permissionrequest.PermissionRequest.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PermissionRequest.finishActivity();
                    PermissionRequest.permission = str;
                    PermissionRequest.permissionRequestInstance.check();
                }
            }).setPositiveButton(yesBtnTitle, new DialogInterface.OnClickListener() { // from class: com.example.permissionrequest.PermissionRequest.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PermissionRequest.listener.onPermissionDeny(str);
                    PermissionRequest.finishActivity();
                }
            }).show();
        } else {
            new AlertDialog.Builder(activity).setCancelable(false).setTitle(permissionDenyTitle).setMessage(permanentPermissionDenyMessage).setPositiveButton(settingBtnTitle, new DialogInterface.OnClickListener() { // from class: com.example.permissionrequest.PermissionRequest.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PermissionRequest.finishActivity();
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", PermissionRequest.context.getPackageName(), null));
                    PermissionRequest.context.startActivity(intent);
                }
            }).setNegativeButton(cancelBtnTitle, new DialogInterface.OnClickListener() { // from class: com.example.permissionrequest.PermissionRequest.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    PermissionRequest.listener.onPermissionDenyPermanent(str);
                    PermissionRequest.finishActivity();
                }
            }).show();
        }
    }

    public static void onActivityReady(Activity activity2) {
        permissionDenyTitle = activity2.getString(R.string.permission_deny_title);
        permissionDenyMessage = String.format(activity2.getString(R.string.permission_deny_message).toString(), permissionName);
        permanentPermissionDenyMessage = String.format(activity2.getString(R.string.permanent_permission_deny_message).toString(), permissionName);
        yesBtnTitle = activity2.getString(R.string.yes_btn_title);
        retryBtnTitle = activity2.getString(R.string.retry_btn_title);
        settingBtnTitle = activity2.getString(R.string.settings_btn_title);
        cancelBtnTitle = activity2.getString(R.string.cancel_btn_title);
        activity = activity2;
    }

    public static PermissionRequest requestPermission(String str, String str2, Context context2) {
        return new PermissionRequest(str, str2, context2);
    }

    public void check() {
        permissionDenyTitle = null;
        permissionDenyMessage = null;
        permanentPermissionDenyMessage = null;
        yesBtnTitle = null;
        retryBtnTitle = null;
        settingBtnTitle = null;
        cancelBtnTitle = null;
        context.startActivity(new Intent(context, (Class<?>) PermissionRequestActivity.class));
    }

    public PermissionRequest withListener(PermissionRequestListener permissionRequestListener) {
        listener = permissionRequestListener;
        return this;
    }
}
