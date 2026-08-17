package com.example.permissionrequest;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/* loaded from: classes.dex */
public class PermissionRequestActivity extends AppCompatActivity {
    public static final int PERMISSION_REQUEST_CODE = 11;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(16);
        setTitle("");
        PermissionRequest.onActivityReady(this);
        try {
            if (ActivityCompat.checkSelfPermission(this, PermissionRequest.permission) != 0) {
                ActivityCompat.requestPermissions(this, new String[]{PermissionRequest.permission}, 11);
                return;
            }
            if (PermissionRequest.listener != null) {
                PermissionRequest.listener.onPermissionAcept(PermissionRequest.permission);
            }
            finish();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 11) {
            return;
        }
        PermissionRequest.handlePermissionRequest(strArr, iArr);
    }
}
