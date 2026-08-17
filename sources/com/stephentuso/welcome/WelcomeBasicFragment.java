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
public class WelcomeBasicFragment extends Fragment implements WelcomePage.OnChangeListener {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DESCRIPTION_COLOR = "description_color";
    public static final String KEY_DESCRIPTION_TYPEFACE_PATH = "description_typeface";
    public static final String KEY_DRAWABLE_ID = "drawable_id";
    public static final String KEY_HEADER_COLOR = "header_color";
    public static final String KEY_HEADER_TYPEFACE_PATH = "header_typeface";
    public static final String KEY_SHOW_ANIM = "show_anim";
    public static final String KEY_TITLE = "title";
    private ImageView imageView = null;
    private TextView titleView = null;
    private TextView descriptionView = null;
    private boolean showParallaxAnim = true;

    public static WelcomeBasicFragment newInstance(@DrawableRes int i, String str, String str2, boolean z, String str3, String str4, @ColorInt int i2, @ColorInt int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("drawable_id", i);
        bundle.putString("title", str);
        bundle.putString("description", str2);
        bundle.putBoolean(KEY_SHOW_ANIM, z);
        bundle.putString("header_typeface", str3);
        bundle.putString("description_typeface", str4);
        bundle.putInt("header_color", i2);
        bundle.putInt("description_color", i3);
        WelcomeBasicFragment welcomeBasicFragment = new WelcomeBasicFragment();
        welcomeBasicFragment.setArguments(bundle);
        return welcomeBasicFragment;
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.wel_fragment_basic, viewGroup, false);
        Bundle arguments = getArguments();
        this.imageView = (ImageView) inflate.findViewById(R.id.wel_image);
        this.titleView = (TextView) inflate.findViewById(R.id.wel_title);
        this.descriptionView = (TextView) inflate.findViewById(R.id.wel_description);
        if (arguments == null) {
            return inflate;
        }
        this.showParallaxAnim = arguments.getBoolean(KEY_SHOW_ANIM, this.showParallaxAnim);
        this.imageView.setImageResource(arguments.getInt("drawable_id"));
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
