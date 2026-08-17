package cu.uci.android.apklis.presentation.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.R;

/* loaded from: classes.dex */
public class ScreenshotFragment extends Fragment {
    ImageView image;
    private String imgUrl;

    public static ScreenshotFragment createInstance(String str) {
        ScreenshotFragment screenshotFragment = new ScreenshotFragment();
        screenshotFragment.setImageUrl(str);
        return screenshotFragment;
    }

    private void init(View view) {
        this.image = (ImageView) view.findViewById(R.id.screenshot);
        Picasso.get().load(Uri.parse(this.imgUrl)).fit().into(this.image);
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.item_screenshot_dialog, viewGroup, false);
        init(inflate);
        return inflate;
    }

    public void setImageUrl(String str) {
        this.imgUrl = str;
    }
}
