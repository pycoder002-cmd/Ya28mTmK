package com.stephentuso.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes.dex */
public class WelcomeHelper {
    public static final int DEFAULT_WELCOME_SCREEN_REQUEST = 1;
    private static final String KEY_WELCOME_SCREEN_STARTED = "com.stephentuso.welcome.welcome_screen_started";
    private Activity mActivity;
    private Class<? extends WelcomeActivity> mActivityClass;
    private boolean welcomeScreenStarted = false;

    public WelcomeHelper(Activity activity, Class<? extends WelcomeActivity> cls) {
        this.mActivity = activity;
        this.mActivityClass = cls;
    }

    private boolean getWelcomeScreenStarted(Bundle bundle) {
        if (!this.welcomeScreenStarted) {
            boolean z = false;
            if (bundle != null && bundle.getBoolean(KEY_WELCOME_SCREEN_STARTED, false)) {
                z = true;
            }
            this.welcomeScreenStarted = z;
        }
        return this.welcomeScreenStarted;
    }

    private boolean shouldShow(Bundle bundle) {
        return (getWelcomeScreenStarted(bundle) || WelcomeSharedPreferencesHelper.welcomeScreenCompleted(this.mActivity, WelcomeUtils.getKey(this.mActivityClass))) ? false : true;
    }

    private void startActivity(int i) {
        this.mActivity.startActivityForResult(new Intent(this.mActivity, this.mActivityClass), i);
    }

    public void forceShow() {
        forceShow(1);
    }

    public void forceShow(int i) {
        startActivity(i);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(KEY_WELCOME_SCREEN_STARTED, this.welcomeScreenStarted);
    }

    public boolean show(Bundle bundle) {
        return show(bundle, 1);
    }

    public boolean show(Bundle bundle, int i) {
        boolean shouldShow = shouldShow(bundle);
        if (shouldShow) {
            this.welcomeScreenStarted = true;
            startActivity(i);
        }
        return shouldShow;
    }
}
