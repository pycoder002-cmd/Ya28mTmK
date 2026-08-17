package cu.uci.android.apklis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

/* loaded from: classes.dex */
public class ScreenshotViewActivity extends AppCompatActivity {
    public static final String SCREENSHOTS_LIST_PARAM = "SCREENSHOT_LIST_PARAM";
    public static final String SCREENSHOT_ACTUAL_PARAM = "SCREENSHOT_ACTUAL_PARAM";
    CarouselView carouselView;
    private int position;
    private String[] screenshots;
    int[] sampleImages = new int[0];
    ImageListener imageListener = new ImageListener() { // from class: cu.uci.android.apklis.ScreenshotViewActivity.1
        @Override // com.synnapps.carouselview.ImageListener
        public void setImageForPosition(int i, ImageView imageView) {
            imageView.setImageResource(ScreenshotViewActivity.this.sampleImages[i]);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_screenshot_view);
        if (getIntent().getExtras() != null) {
            this.screenshots = getIntent().getExtras().getStringArray("SCREENSHOT_LIST_PARAM");
            this.position = getIntent().getExtras().getInt("SCREENSHOT_ACTUAL_PARAM", -1);
        }
        this.carouselView = (CarouselView) findViewById(R.id.carouselView);
        this.carouselView.setPageCount(this.sampleImages.length);
        this.carouselView.setImageListener(this.imageListener);
    }
}
