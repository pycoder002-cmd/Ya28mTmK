package cu.uci.android.apklis.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.startapp.sdk.adsbase.StartAppAd;
import cu.uci.android.apklis.R;
import cu.uci.manolo.android.kiosko.BuildConfig;

/* loaded from: classes.dex */
public class InfoActivity extends AppCompatActivity {
    public static final String ACTION_PARAM = "ACTION_PARAM";
    private int action;
    ImageView iv_about;
    TextView text;
    TextView title;
    Toolbar toolbar;
    private TextView tv_version;
    public static final Integer ACTION_TERMS_CONDITIONS_VALUE = 0;
    public static final Integer ACTION_PRIVACY_POLICY_VALUE = 1;
    public static final Integer ACTION_ABOUT_VALUE = 2;

    private void init() {
        if (this.action == ACTION_TERMS_CONDITIONS_VALUE.intValue()) {
            this.title.setText(getString(R.string.title_term_condition));
            this.text.setText(getString(R.string.text_term_condition));
        } else if (this.action == ACTION_PRIVACY_POLICY_VALUE.intValue()) {
            this.title.setText(getString(R.string.title_privacy_policy));
            this.text.setText(getString(R.string.text_privacy_policy));
        } else if (this.action != ACTION_ABOUT_VALUE.intValue()) {
            finish();
        } else {
            this.tv_version = (TextView) findViewById(R.id.tv_version);
            this.tv_version.setText(BuildConfig.VERSION_NAME);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_info);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_info);
        setSupportActionBar(this.toolbar);
        this.toolbar.setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        }
        this.action = extras.getInt("ACTION_PARAM", ACTION_TERMS_CONDITIONS_VALUE.intValue());
        this.title = (TextView) findViewById(R.id.title);
        this.text = (TextView) findViewById(R.id.text);
        init();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
        return true;
    }
}
