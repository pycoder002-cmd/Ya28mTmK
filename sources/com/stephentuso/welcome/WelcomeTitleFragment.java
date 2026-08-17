package com.stephentuso.welcome;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.stephentuso.welcome.WelcomePage;

/* loaded from: classes.dex */
public class WelcomeTitleFragment extends Fragment implements WelcomePage.OnChangeListener {
    private static final String ARG_DRAWABLE_ID = "drawable_id";
    private static final String ARG_SHOW_ANIM = "show_anim";
    private static final String ARG_TITLE = "title";
    private static final String ARG_TITLE_COLOR = "title_color";
    private static final String ARG_TYPEFACE_PATH = "typeface_path";
    private int drawableId;
    private String title = "";
    private boolean showParallaxAnim = true;
    private TextView titleView = null;
    private ImageView imageView = null;

    public static WelcomeTitleFragment newInstance(@DrawableRes int i, String str, boolean z, @Nullable String str2, @ColorInt int i2) {
        WelcomeTitleFragment welcomeTitleFragment = new WelcomeTitleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("drawable_id", i);
        bundle.putString("title", str);
        bundle.putBoolean("show_anim", z);
        bundle.putString(ARG_TYPEFACE_PATH, str2);
        bundle.putInt(ARG_TITLE_COLOR, i2);
        welcomeTitleFragment.setArguments(bundle);
        return welcomeTitleFragment;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.wel_fragment_title, viewGroup, false);
        this.imageView = (ImageView) inflate.findViewById(R.id.wel_image);
        this.titleView = (TextView) inflate.findViewById(R.id.wel_title);
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.drawableId = arguments.getInt("drawable_id");
        this.title = arguments.getString("title");
        this.imageView.setImageResource(this.drawableId);
        this.titleView.setText(this.title);
        int i = arguments.getInt(ARG_TITLE_COLOR, -1);
        if (i != -1) {
            this.titleView.setTextColor(i);
        }
        this.showParallaxAnim = arguments.getBoolean("show_anim", this.showParallaxAnim);
        WelcomeUtils.setTypeface(this.titleView, arguments.getString(ARG_TYPEFACE_PATH), getActivity());
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageScrollStateChanged(int i, int i2) {
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageScrolled(int i, float f, int i2) {
        if (!this.showParallaxAnim || Build.VERSION.SDK_INT < 11 || this.imageView == null) {
            return;
        }
        this.imageView.setTranslationX((-i2) * 0.8f);
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageSelected(int i, int i2) {
    }
}
