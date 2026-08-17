package cu.uci.android.apklis.presentation.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.model.Release;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.AllAppPresenter;
import cu.uci.android.apklis.presentation.ui.adapter.ScreenshotAppAdapter;
import cu.uci.android.apklis.presentation.ui.util.TextExpandableAnimation;
import cu.uci.android.apklis.storage.repository.model.ApiCategory;

/* loaded from: classes.dex */
public class GeneralAppDetailsFragment extends Fragment {
    private ScreenshotAppAdapter adapter;
    private AppDetails app;
    LinearLayout btnMoreDesciption;
    LinearLayout categoryContainer;
    LinearLayout dateContainer;
    TextExpandableAnimation description;
    View divider2;
    View divider3;
    TextView infoCategory;
    TextView infoDate;
    TextView infoPermission;
    TextView infoSize;
    TextView infoVersion;
    LinearLayout llAppCategory;
    LinearLayout llDownloadContainer;
    LinearLayout llRequiredVersion;
    TextView moreMinusText;
    LinearLayout permissionContainer;
    private AllAppPresenter presenter;
    RecyclerView recycler;
    LinearLayout sizeContainer;
    TextView tvAppCategory;
    TextView tvChangelog;
    TextView tvInfoDownloadAmount;
    TextView tvInfoRequiredVersion;
    LinearLayout versionContainer;
    private Release release = null;
    private Boolean expanded = true;
    private int minHeinght = 64;
    private int realHeight = 0;

    public static GeneralAppDetailsFragment createInstance(AppDetails appDetails) {
        GeneralAppDetailsFragment generalAppDetailsFragment = new GeneralAppDetailsFragment();
        generalAppDetailsFragment.setAppDetails(appDetails);
        return generalAppDetailsFragment;
    }

    private void init(View view) {
        this.recycler = (RecyclerView) view.findViewById(R.id.recycler);
        this.divider3 = view.findViewById(R.id.divider3);
        this.btnMoreDesciption = (LinearLayout) view.findViewById(R.id.btn_more_description);
        this.description = (TextExpandableAnimation) view.findViewById(R.id.description);
        this.moreMinusText = (TextView) view.findViewById(R.id.more_minus_text);
        this.infoVersion = (TextView) view.findViewById(R.id.info_version_text);
        this.infoDate = (TextView) view.findViewById(R.id.info_date_text);
        this.infoSize = (TextView) view.findViewById(R.id.info_size_text);
        this.tvInfoDownloadAmount = (TextView) view.findViewById(R.id.tv_info_download_amount);
        this.tvInfoRequiredVersion = (TextView) view.findViewById(R.id.tv_info_required_version);
        this.tvAppCategory = (TextView) view.findViewById(R.id.tv_app_category);
        this.tvChangelog = (TextView) view.findViewById(R.id.tvChangelog);
        this.infoPermission = (TextView) view.findViewById(R.id.info_permission_text);
        if (this.app != null) {
            if (this.app.getLastRelease().getScreenshots() == null || this.app.getLastRelease().getScreenshots().length <= 0) {
                this.recycler.setVisibility(8);
            } else {
                this.recycler.setVisibility(0);
                this.adapter = new ScreenshotAppAdapter(getContext(), this.app.getLastRelease().getScreenshots());
                this.recycler.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
                this.recycler.setAdapter(this.adapter);
                this.recycler.setHasFixedSize(true);
                this.recycler.setNestedScrollingEnabled(false);
            }
            this.description.setTextSetMovementMethod(this.app.getDescription());
            this.description.resetState(true);
            if (this.release != null) {
                this.infoVersion.setText(this.release.getPackage_versionName());
                StringBuilder sb = new StringBuilder();
                for (ApiCategory apiCategory : this.app.getCategory()) {
                    sb.append(apiCategory.getName());
                    sb.append(" / ");
                }
                StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.length() - 3));
                this.tvInfoDownloadAmount.setText(String.valueOf(this.app.getDownloadCount()));
                this.infoDate.setText(this.release.getPublishedAtFormated());
                this.tvInfoRequiredVersion.setText(getString(R.string.versionMin, this.release.getVersionSdkName()));
                this.tvAppCategory.setText(sb2.toString());
                String usesPermissionString = this.release.getUsesPermissionString();
                this.tvChangelog.setText(Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(this.release.getChangelog(), 0) : Html.fromHtml(this.release.getChangelog()));
                this.tvChangelog.setMovementMethod(LinkMovementMethod.getInstance());
                if (usesPermissionString != null && usesPermissionString.length() > 0) {
                    this.infoPermission.setText(usesPermissionString);
                }
                if (this.release.getSize().length() > 0) {
                    this.infoSize.setText(this.release.getSize());
                }
            }
        }
    }

    public void collapse(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        this.realHeight = measuredHeight;
        Animation animation = new Animation() { // from class: cu.uci.android.apklis.presentation.ui.fragment.GeneralAppDetailsFragment.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                if (f == 1.0f) {
                    return;
                }
                view.getLayoutParams().height = measuredHeight - ((int) ((measuredHeight - GeneralAppDetailsFragment.this.minHeinght) * f));
                view.requestLayout();
            }

            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(500L);
        view.startAnimation(animation);
    }

    public void expand(final View view) {
        final int i = this.realHeight;
        view.getLayoutParams().height = this.minHeinght;
        view.setVisibility(0);
        Animation animation = new Animation() { // from class: cu.uci.android.apklis.presentation.ui.fragment.GeneralAppDetailsFragment.1
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                int i2;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (f == 1.0f) {
                    i2 = -2;
                } else {
                    i2 = ((int) ((i - GeneralAppDetailsFragment.this.minHeinght) * f)) + GeneralAppDetailsFragment.this.minHeinght;
                }
                layoutParams.height = i2;
                view.requestLayout();
            }

            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(500L);
        view.startAnimation(animation);
    }

    public void onCLickMore(View view) {
        if (this.expanded.booleanValue()) {
            collapse(this.description);
            this.expanded = Boolean.valueOf(!this.expanded.booleanValue());
            this.moreMinusText.setText("Mas");
        } else {
            expand(this.description);
            this.expanded = Boolean.valueOf(!this.expanded.booleanValue());
            this.moreMinusText.setText("Menos");
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_general_app_details, viewGroup, false);
        init(inflate);
        return inflate;
    }

    public void setAppDetails(AppDetails appDetails) {
        this.app = appDetails;
        this.release = appDetails.getLastRelease();
    }
}
