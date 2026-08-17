package cu.uci.android.apklis.presentation.ui.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.domain.model.Release;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.Review;
import cu.uci.android.apklis.presentation.presenter.ReviewsPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.ReviewsPresenterImpl;
import cu.uci.android.apklis.presentation.ui.adapter.ReviewAppAdapter;
import cu.uci.android.apklis.presentation.ui.util.MyBarChartRender;
import cu.uci.android.apklis.threading.MainThreadImpl;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes.dex */
public class ReviewAppDetailsFragment extends Fragment implements ReviewsPresenter.View {
    RelativeLayout actualuserReviewContainer;
    private ReviewAppAdapter adapter;
    private AppDetails app;
    LinearLayout btnDelete;
    LinearLayout btnOpine;
    LinearLayout btnSend;
    RelativeLayout emptyuserReviewContainer;
    HorizontalBarChart mChart;
    NestedScrollView nestedScroll;
    private ReviewsPresenter presenter;
    SimpleRatingBar ratingBar;
    TextView ratingValue;
    RecyclerView recycler;
    TextView reviewsCount;
    CircularImageView userAvatar;
    TextView userComment;
    TextView userDate;
    TextView userName;
    SimpleRatingBar userRating;
    private Review userReview;
    LinearLayout userReviewContainer;
    private Integer userReviewId = -1;
    private Release release = null;

    public static ReviewAppDetailsFragment createInstance(AppDetails appDetails) {
        ReviewAppDetailsFragment reviewAppDetailsFragment = new ReviewAppDetailsFragment();
        reviewAppDetailsFragment.setAppDetails(appDetails);
        return reviewAppDetailsFragment;
    }

    private void init(View view) {
        this.nestedScroll = (NestedScrollView) view.findViewById(R.id.nested_scroll);
        this.recycler = (RecyclerView) view.findViewById(R.id.recycler);
        this.mChart = (HorizontalBarChart) view.findViewById(R.id.chart);
        this.ratingBar = (SimpleRatingBar) view.findViewById(R.id.rating);
        this.ratingValue = (TextView) view.findViewById(R.id.rating_value);
        this.reviewsCount = (TextView) view.findViewById(R.id.reviews_count);
        this.userReviewContainer = (LinearLayout) view.findViewById(R.id.user_review_container);
        this.emptyuserReviewContainer = (RelativeLayout) view.findViewById(R.id.empty_user_review_container);
        this.actualuserReviewContainer = (RelativeLayout) view.findViewById(R.id.actual_user_review_container);
        this.userAvatar = (CircularImageView) view.findViewById(R.id.user_avatar);
        this.userName = (TextView) view.findViewById(R.id.user_name);
        this.userComment = (TextView) view.findViewById(R.id.user_comment);
        this.userDate = (TextView) view.findViewById(R.id.user_review_date);
        this.userRating = (SimpleRatingBar) view.findViewById(R.id.user_rating);
        if (MainApp.userAccount == null) {
            Picasso.get().load(R.drawable.ic_user_account).into(this.userAvatar);
        } else {
            Picasso.get().load(MainApp.userAccount.getAvatar()).into(this.userAvatar);
        }
        this.btnOpine = (LinearLayout) view.findViewById(R.id.btn_opine);
        this.btnDelete = (LinearLayout) view.findViewById(R.id.btn_delete);
        this.btnSend = (LinearLayout) view.findViewById(R.id.btn_edit);
        this.userRating = (SimpleRatingBar) view.findViewById(R.id.user_rating);
        this.btnOpine.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.ReviewAppDetailsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ReviewAppDetailsFragment.this.showDialog();
            }
        });
        this.btnSend.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.ReviewAppDetailsFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ReviewAppDetailsFragment.this.showDialog();
            }
        });
        this.btnDelete.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.ReviewAppDetailsFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ReviewAppDetailsFragment.this.userReview != null) {
                    ReviewAppDetailsFragment.this.presenter.deleteReview(ReviewAppDetailsFragment.this.userReview.getId());
                }
            }
        });
        initRatingChar();
        this.userReviewContainer.setVisibility(8);
        try {
            Float valueOf = Float.valueOf(Float.parseFloat(this.app.getRating()));
            String format = String.format(Locale.US, "%.1f", valueOf);
            this.ratingBar.setRating(valueOf.floatValue());
            this.ratingValue.setText(format);
            this.reviewsCount.setText(this.app.getReviewsCount() + "");
        } catch (NullPointerException e) {
            MainApp.log(getClass().getName(), e);
            e.printStackTrace();
        }
        this.adapter = new ReviewAppAdapter(getContext(), new ArrayList());
        this.recycler.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recycler.setAdapter(this.adapter);
        this.recycler.setHasFixedSize(true);
        this.recycler.setNestedScrollingEnabled(false);
        this.presenter = new ReviewsPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        loadReviews();
    }

    private void initRatingChar() {
        this.mChart.setRenderer(new MyBarChartRender(this.mChart, this.mChart.getAnimator(), this.mChart.getViewPortHandler()));
        this.mChart.invalidate();
        this.mChart.setDrawBarShadow(false);
        this.mChart.setDrawValueAboveBar(true);
        this.mChart.getDescription().setEnabled(false);
        this.mChart.setPinchZoom(false);
        this.mChart.setPadding(0, 0, 0, 0);
        this.mChart.setBackgroundColor(Color.parseColor("#ffffff"));
        this.mChart.setDrawGridBackground(false);
        this.mChart.setClickable(false);
        this.mChart.setHovered(false);
        this.mChart.setHorizontalFadingEdgeEnabled(false);
        this.mChart.setHorizontalScrollBarEnabled(false);
        this.mChart.setClipValuesToContent(false);
        this.mChart.getLegend().setEnabled(false);
        XAxis xAxis = this.mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1.0f);
        xAxis.setAxisMinimum(0.6f);
        xAxis.setAxisMaximum(5.4f);
        xAxis.setDrawLabels(true);
        YAxis axisLeft = this.mChart.getAxisLeft();
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setAxisMinimum(0.0f);
        axisLeft.setDrawLabels(false);
        YAxis axisRight = this.mChart.getAxisRight();
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setAxisMinimum(0.0f);
        axisRight.setDrawLabels(false);
        try {
            setData();
        } catch (NullPointerException e) {
            MainApp.log(getClass().getName(), e);
            e.printStackTrace();
        }
        this.mChart.setFitBars(true);
        this.mChart.animateY(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS);
    }

    private void loadReviews() {
        int i = -1;
        try {
            if (MainApp.isUserAuthenticate()) {
                i = MainApp.userAccount.getId();
                this.presenter.getReviewsByUsername(i, this.app.getId());
            }
            this.presenter.getReviewsByAppInRange(0, 100, this.app.getId(), i);
        } catch (NullPointerException e) {
            MainApp.log(getClass().getName(), e);
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setData() {
        ArrayList arrayList = new ArrayList();
        Integer[] reviewsStart = this.app.getReviewsStart();
        float f = 0.0f;
        int i = 0;
        while (i < 5) {
            Integer num = reviewsStart[i];
            if (num.intValue() > f) {
                f = num.intValue();
            }
            i++;
            arrayList.add(new BarEntry(i, num.intValue()));
        }
        int[] iArr = new int[5];
        for (int i2 = 0; i2 < 5; i2++) {
            if (reviewsStart[i2].intValue() < f || reviewsStart[i2].intValue() == 0) {
                iArr[i2] = R.color.colorBarChart;
            } else {
                iArr[i2] = R.color.colorBarChartMax;
            }
        }
        if (this.mChart.getData() != null && ((BarData) this.mChart.getData()).getDataSetCount() > 0) {
            BarDataSet barDataSet = (BarDataSet) ((BarData) this.mChart.getData()).getDataSetByIndex(0);
            barDataSet.setValues(arrayList);
            barDataSet.setValueFormatter(new LargeValueFormatter());
            ((BarData) this.mChart.getData()).notifyDataChanged();
            this.mChart.notifyDataSetChanged();
            return;
        }
        BarDataSet barDataSet2 = new BarDataSet(arrayList, "");
        barDataSet2.setDrawIcons(false);
        barDataSet2.setColors(iArr, getContext());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(barDataSet2);
        BarData barData = new BarData(arrayList2);
        barData.setValueTextSize(10.0f);
        barData.setBarWidth(0.8f);
        barData.setDrawValues(true);
        barData.setValueFormatter(new LargeValueFormatter());
        this.mChart.setData(barData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog() {
        Integer num = 0;
        String str = "";
        if (this.userReview != null) {
            num = this.userReview.getRating();
            str = this.userReview.getComment();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_review, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.comment);
        final SimpleRatingBar simpleRatingBar = (SimpleRatingBar) inflate.findViewById(R.id.rating);
        editText.setVisibility(8);
        if (!TextUtils.isEmpty(str)) {
            editText.setText(str);
        }
        simpleRatingBar.setRating(num.intValue());
        simpleRatingBar.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.ReviewAppDetailsFragment.4
            @Override // com.iarcuschin.simpleratingbar.SimpleRatingBar.OnRatingBarChangeListener
            public void onRatingChanged(SimpleRatingBar simpleRatingBar2, float f, boolean z) {
                editText.setVisibility(0);
            }
        });
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.cancel_title, new DialogInterface.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.ReviewAppDetailsFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(R.string.send_title, new DialogInterface.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.ReviewAppDetailsFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                int rating = (int) simpleRatingBar.getRating();
                String obj = editText.getText().toString();
                if (ReviewAppDetailsFragment.this.userReview == null) {
                    ReviewAppDetailsFragment.this.presenter.sendReview(ReviewAppDetailsFragment.this.app.getId(), Integer.valueOf(rating), obj);
                } else {
                    ReviewAppDetailsFragment.this.presenter.editReview(ReviewAppDetailsFragment.this.app.getId(), Integer.valueOf(rating), obj);
                }
            }
        });
        AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorAppDetailsBackground)));
        create.show();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
    }

    void onClickDelete() {
        if (this.userReview != null) {
            this.presenter.deleteReview(this.userReview.getId());
        }
    }

    void onClickEdit() {
        showDialog();
    }

    void onClickOpine() {
        showDialog();
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_review_app_details, viewGroup, false);
        init(inflate);
        return inflate;
    }

    public void onPositiveButtonClicked(int i, String str) {
        if (this.userReview == null) {
            this.presenter.sendReview(this.app.getId(), Integer.valueOf(i), str);
        } else {
            this.presenter.editReview(this.app.getId(), Integer.valueOf(i), str);
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter.View
    public void onReviewDeleted(boolean z) {
        if (z) {
            onUserReviewLoaded(null);
        } else {
            showError(null);
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter.View
    public void onReviewSent(Review review) {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter.View
    public void onReviewsLoaded(ArrayList<Review> arrayList) {
        if (this.adapter.getItemCount() + arrayList.size() > 0) {
            this.recycler.setVisibility(0);
            this.adapter.addReviews(arrayList);
        } else {
            this.recycler.setVisibility(4);
        }
        this.nestedScroll.scrollTo(0, 0);
        this.nestedScroll.requestLayout();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter.View
    public void onUserReviewLoaded(Review review) {
        this.userReview = review;
        if (review == null) {
            this.userReviewContainer.setVisibility(0);
            this.actualuserReviewContainer.setVisibility(8);
            this.emptyuserReviewContainer.setVisibility(0);
            return;
        }
        this.userReviewContainer.setVisibility(0);
        this.emptyuserReviewContainer.setVisibility(8);
        this.actualuserReviewContainer.setVisibility(0);
        try {
            Picasso.get().load(Uri.parse(review.getAvatar())).placeholder(R.drawable.ic_user_account).error(R.drawable.ic_user_account).resize(0, 200).networkPolicy(NetworkPolicy.NO_CACHE, new NetworkPolicy[0]).into(this.userAvatar);
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            this.userAvatar.setImageResource(R.drawable.ic_user_account);
        }
        this.userName.setText(review.getFullName());
        this.userComment.setText(review.getComment());
        this.userRating.setRating(review.getRating().intValue());
        this.userDate.setText(review.getPublishedAtFormated());
    }

    public void setAppDetails(AppDetails appDetails) {
        this.app = appDetails;
        this.release = appDetails.getLastRelease();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        if (str == null && isAdded()) {
            MainApp.showToast(getString(R.string.error_server_conection), false);
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
    }
}
