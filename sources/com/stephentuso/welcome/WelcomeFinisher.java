package com.stephentuso.welcome;

import android.support.v4.app.Fragment;

/* loaded from: classes.dex */
public class WelcomeFinisher {
    private Fragment mFragment;

    public WelcomeFinisher(Fragment fragment) {
        this.mFragment = fragment;
    }

    public void finish() {
        if (this.mFragment.getActivity() instanceof WelcomeActivity) {
            ((WelcomeActivity) this.mFragment.getActivity()).completeWelcomeScreen();
        }
    }
}
