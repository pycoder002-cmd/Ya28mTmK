package com.example.permissionrequest;

/* loaded from: classes.dex */
public interface PermissionRequestListener {
    void onPermissionAcept(String str);

    void onPermissionDeny(String str);

    void onPermissionDenyPermanent(String str);
}
