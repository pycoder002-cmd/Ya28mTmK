package com.stephentuso.welcome;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.stephentuso.welcome.WelcomePage;

/* loaded from: classes.dex */
public class WelcomeFullScreenParallaxFragment extends Fragment implements WelcomePage.OnChangeListener {
    public static final String KEY_END_FACTOR = "end_factor";
    public static final String KEY_LAYOUT_ID = "drawable_id";
    public static final String KEY_PARALLAX_RECURSIVE = "parallax_recursive";
    public static final String KEY_START_FACTOR = "start_factor";
    private FrameLayout frameLayout = null;
    private float startFactor = 0.2f;
    private float endFactor = 1.0f;
    private float parallaxInterval = 0.0f;
    private boolean parallaxRecursive = false;

    public static WelcomeFullScreenParallaxFragment newInstance(@LayoutRes int i, float f, float f2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("drawable_id", i);
        bundle.putFloat("start_factor", f);
        bundle.putFloat("end_factor", f2);
        bundle.putBoolean("parallax_recursive", z);
        WelcomeFullScreenParallaxFragment welcomeFullScreenParallaxFragment = new WelcomeFullScreenParallaxFragment();
        welcomeFullScreenParallaxFragment.setArguments(bundle);
        return welcomeFullScreenParallaxFragment;
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.wel_fragment_parallax_full_screen, viewGroup, false);
        Bundle arguments = getArguments();
        this.frameLayout = (FrameLayout) inflate.findViewById(R.id.wel_parallax_frame);
        if (arguments == null) {
            return inflate;
        }
        this.startFactor = arguments.getFloat("start_factor", this.startFactor);
        this.endFactor = arguments.getFloat("end_factor", this.endFactor);
        this.parallaxRecursive = arguments.getBoolean("parallax_recursive", this.parallaxRecursive);
        layoutInflater.inflate(arguments.getInt("drawable_id"), (ViewGroup) this.frameLayout, true);
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.parallaxInterval = (this.endFactor - this.startFactor) / (WelcomeUtils.calculateParallaxLayers(this.frameLayout.getChildAt(0), this.parallaxRecursive) - 1);
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageScrollStateChanged(int i, int i2) {
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageScrolled(int i, float f, int i2) {
        if (Build.VERSION.SDK_INT < 11 || this.frameLayout == null) {
            return;
        }
        WelcomeUtils.applyParallaxEffect(this.frameLayout.getChildAt(0), this.parallaxRecursive, i2, this.startFactor, this.parallaxInterval);
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageSelected(int i, int i2) {
    }
}
