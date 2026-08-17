package cu.uci.android.apklis.presentation.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.ui.adapter.ViewPagerAdapter;
import cu.uci.android.apklis.presentation.ui.fragment.ScreenshotFragment;

/* loaded from: classes.dex */
public class ScreenshotDialog extends AppCompatActivity {
    public static final String SCREENSHOTS_LIST_PARAM = "SCREENSHOT_LIST_PARAM";
    public static final String SCREENSHOT_ACTUAL_PARAM = "SCREENSHOT_ACTUAL_PARAM";
    private int position;
    private String[] screenshots;
    ViewPager viewPager;

    private void init() {
        if (this.screenshots == null || this.screenshots.length <= 0) {
            return;
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < this.screenshots.length; i++) {
            viewPagerAdapter.addFragment(ScreenshotFragment.createInstance(this.screenshots[i]), "");
        }
        this.viewPager.setAdapter(viewPagerAdapter);
        if (this.position == -1 || this.position < 0 || this.position >= this.screenshots.length) {
            return;
        }
        this.viewPager.setCurrentItem(this.position);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_screenshots);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        if (getIntent().getExtras() == null) {
            finish();
            return;
        }
        this.screenshots = getIntent().getExtras().getStringArray("SCREENSHOT_LIST_PARAM");
        this.position = getIntent().getExtras().getInt("SCREENSHOT_ACTUAL_PARAM", -1);
        init();
    }
}
