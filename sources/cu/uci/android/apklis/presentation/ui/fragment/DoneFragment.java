package cu.uci.android.apklis.presentation.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.stephentuso.welcome.WelcomeFinisher;
import com.stephentuso.welcome.WelcomePage;
import com.stephentuso.welcome.WelcomeUtils;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.ui.activity.SplashActivity;
import mehdi.sakout.fancybuttons.FancyButton;

/* loaded from: classes.dex */
public class DoneFragment extends Fragment implements WelcomePage.OnChangeListener {
    private FancyButton btn_fancy;
    private Intent intent;
    private ViewGroup rootLayout;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.content_parallax_four, viewGroup, false);
        this.btn_fancy = (FancyButton) inflate.findViewById(R.id.btn_fancy);
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.btn_fancy).setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.DoneFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DoneFragment.this.intent = new Intent(DoneFragment.this.getActivity(), (Class<?>) SplashActivity.class);
                DoneFragment.this.startActivity(DoneFragment.this.intent);
                new WelcomeFinisher(DoneFragment.this).finish();
            }
        });
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageScrollStateChanged(int i, int i2) {
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageScrolled(int i, float f, int i2) {
        if (this.rootLayout != null) {
            WelcomeUtils.applyParallaxEffect(this.rootLayout, true, i2, 0.3f, 0.2f);
        }
    }

    @Override // com.stephentuso.welcome.WelcomePage.OnChangeListener
    public void onWelcomeScreenPageSelected(int i, int i2) {
    }
}
