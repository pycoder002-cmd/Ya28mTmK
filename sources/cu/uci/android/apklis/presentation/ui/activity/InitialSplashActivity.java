package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.stephentuso.welcome.FragmentWelcomePage;
import com.stephentuso.welcome.FullscreenParallaxPage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.WelcomeHelper;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.ui.fragment.DoneFragment;
import mehdi.sakout.fancybuttons.FancyButton;

/* loaded from: classes.dex */
public class InitialSplashActivity extends WelcomeActivity {
    private FancyButton btn_fancy;
    Boolean first;
    private Intent intent;
    SharedPreferences sharedPreferences;
    WelcomeHelper welcomeScreen;

    public static String welcomeKey() {
        return "WelcomeScreen";
    }

    @Override // com.stephentuso.welcome.WelcomeActivity
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this).defaultTitleTypefacePath("Roboto-Thin.ttf").defaultDescriptionTypefacePath(getAssets() + "/Roboto-Thin.ttf").defaultTitleTypefacePath(getAssets() + "/Roboto-Thin.ttf").page(new FullscreenParallaxPage(R.layout.content_parallax_first).background(R.color.colorWhite)).page(new FullscreenParallaxPage(R.layout.content_parallax_second).background(R.color.colorWhite)).page(new FullscreenParallaxPage(R.layout.content_parallax_three).background(R.color.colorWhite)).page(new FragmentWelcomePage() { // from class: cu.uci.android.apklis.presentation.ui.activity.InitialSplashActivity.1
            @Override // com.stephentuso.welcome.WelcomePage
            protected Fragment fragment() {
                return new DoneFragment();
            }
        }.background(R.color.colorWhite)).swipeToDismiss(false).useCustomDoneButton(true).defaultTitleTypefacePath(getAssets() + "/Roboto-Thin.ttf").exitAnimation(android.R.anim.fade_out).build();
    }

    public void nextActivity() {
        startActivity(this.intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.stephentuso.welcome.WelcomeActivity
    public void onButtonBarSecondPressed() {
        super.onButtonBarSecondPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.stephentuso.welcome.WelcomeActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT == 26) {
            setRequestedOrientation(-1);
        } else {
            setRequestedOrientation(1);
        }
        this.intent = new Intent(this, (Class<?>) SplashActivity.class);
        this.sharedPreferences = getSharedPreferences("Preference_INITIAL", 0);
        this.first = Boolean.valueOf(this.sharedPreferences.getBoolean("first", false));
        if (this.first.booleanValue()) {
            startActivity(this.intent);
            finish();
        }
        SharedPreferences.Editor edit = getSharedPreferences("Preference_INITIAL", 0).edit();
        edit.putBoolean("first", true);
        edit.apply();
        edit.commit();
        Log.e("onCreate: ", this.first.toString());
    }
}
