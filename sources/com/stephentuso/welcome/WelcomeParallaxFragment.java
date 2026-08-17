package com.stephentuso.welcome;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.stephentuso.welcome.WelcomePage;

/* loaded from: classes.dex */
public class WelcomeParallaxFragment extends Fragment implements WelcomePage.OnChangeListener {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DESCRIPTION_COLOR = "description_color";
    public static final String KEY_DESCRIPTION_TYPEFACE_PATH = "description_typeface";
    public static final String KEY_END_FACTOR = "end_factor";
    public static final String KEY_HEADER_COLOR = "header_color";
    public static final String KEY_HEADER_TYPEFACE_PATH = "header_typeface";
    public static final String KEY_LAYOUT_ID = "drawable_id";
    public static final String KEY_PARALLAX_RECURSIVE = "parallax_recursive";
    public static final String KEY_START_FACTOR = "start_factor";
    public static final String KEY_TITLE = "title";
    private FrameLayout frameLayout = null;
    private TextView titleView = null;
    private TextView descriptionView = null;
    private float startFactor = 0.2f;
    private float endFactor = 1.0f;
    private float parallaxInterval = 0.0f;
    private boolean parallaxRecursive = false;

    public static WelcomeParallaxFragment newInstance(@LayoutRes int i, String str, String str2, float f, float f2, boolean z, String str3, String str4, @ColorInt int i2, @ColorInt int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("drawable_id", i);
        bundle.putString("title", str);
        bundle.putString("description", str2);
        bundle.putFloat("start_factor", f);
        bundle.putFloat("end_factor", f2);
        bundle.putBoolean("parallax_recursive", z);
        bundle.putString("header_typeface", str3);
        bundle.putString("description_typeface", str4);
        bundle.putInt("header_color", i2);
        bundle.putInt("description_color", i3);
        WelcomeParallaxFragment welcomeParallaxFragment = new WelcomeParallaxFragment();
        welcomeParallaxFragment.setArguments(bundle);
        return welcomeParallaxFragment;
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.wel_fragment_parallax, viewGroup, false);
        Bundle arguments = getArguments();
        this.frameLayout = (FrameLayout) inflate.findViewById(R.id.wel_parallax_frame);
        this.titleView = (TextView) inflate.findViewById(R.id.wel_title);
        this.descriptionView = (TextView) inflate.findViewById(R.id.wel_description);
        if (arguments == null) {
            return inflate;
        }
        this.startFactor = arguments.getFloat("start_factor", this.startFactor);
        this.endFactor = arguments.getFloat("end_factor", this.endFactor);
        this.parallaxRecursive = arguments.getBoolean("parallax_recursive", this.parallaxRecursive);
        layoutInflater.inflate(arguments.getInt("drawable_id"), (ViewGroup) this.frameLayout, true);
        if (arguments.getString("title") != null) {
            this.titleView.setText(arguments.getString("title"));
        }
        if (arguments.getString("description") != null) {
            this.descriptionView.setText(arguments.getString("description"));
        }
        int i = arguments.getInt("header_color", -1);
        if (i != -1) {
            this.titleView.setTextColor(i);
        }
        int i2 = arguments.getInt("description_color", -1);
        if (i2 != -1) {
            this.descriptionView.setTextColor(i2);
        }
        WelcomeUtils.setTypeface(this.titleView, arguments.getString("header_typeface"), getActivity());
        WelcomeUtils.setTypeface(this.descriptionView, arguments.getString("description_typeface"), getActivity());
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
