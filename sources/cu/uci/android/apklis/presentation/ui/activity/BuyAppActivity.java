package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import cu.uci.android.apklis.R;

/* loaded from: classes.dex */
public class BuyAppActivity extends AppCompatActivity {
    void handleSendText(Intent intent, TextView textView) {
        String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
        if (stringExtra != null) {
            textView.setText(stringExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_buy_app);
        TextView textView = (TextView) findViewById(R.id.tvAct2);
        Intent intent = getIntent();
        intent.getAction();
        String type = intent.getType();
        if (type == null || !"text/plain".equals(type)) {
            return;
        }
        handleSendText(intent, textView);
    }
}
