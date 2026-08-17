package com.bvapp.arcmenulibrary;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bvapp.arcmenulibrary.anim.AnimationObject;
import com.bvapp.arcmenulibrary.anim.ViewAnim;
import com.bvapp.arcmenulibrary.interfaces.ScrollDirectionListener;
import com.bvapp.arcmenulibrary.util.Util;
import com.bvapp.arcmenulibrary.widget.ArcLayout;
import com.bvapp.arcmenulibrary.widget.FloatingActionButton;
import com.bvapp.arcmenulibrary.widget.MoveUpwardBehavior;
import com.bvapp.arcmenulibrary.widget.ObservableScrollView;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

@CoordinatorLayout.DefaultBehavior(MoveUpwardBehavior.class)
/* loaded from: classes.dex */
public class ArcMenu extends RelativeLayout {
    public static final int ANIM_BOTTOM_TO_DOWN = 3850;
    public static final int ANIM_BOTTOM_TO_LEFT = 3851;
    public static final int ANIM_BOTTOM_TO_RIGHT = 3852;
    public static final int ANIM_BOTTOM_TO_UP = 3853;
    private static final int ANIM_DURATION = 300;
    public static final int ANIM_INTERPOLATOR_ACCELERATE = 3863;
    public static final int ANIM_INTERPOLATOR_ACCELERATE_DECLERATE = 3864;
    public static final int ANIM_INTERPOLATOR_ANTICIPATE = 3865;
    public static final int ANIM_INTERPOLATOR_BOUNCE = 3866;
    public static final int ANIM_INTERPOLATOR_DECLERATE = 3862;
    public static final int ANIM_MIDDLE_TO_DOWN = 3854;
    public static final int ANIM_MIDDLE_TO_LEFT = 3856;
    public static final int ANIM_MIDDLE_TO_RIGHT = 3855;
    public static final int ANIM_MIDDLE_TO_UP = 3857;
    public static final int ANIM_TOP_TO_DOWN = 3861;
    public static final int ANIM_TOP_TO_LEFT = 3859;
    public static final int ANIM_TOP_TO_RIGHT = 3860;
    public static final int ANIM_TOP_TO_UP = 3858;
    public static final int BOTTOM_LEFT = 3844;
    public static final int BOTTOM_MIDDLE = 3846;
    public static final int BOTTOM_RIGHT = 3845;
    public static final int CENTER = 3849;
    public static final int LEFT_MIDDLE = 3848;
    public static final int RIGHT_MIDDLE = 3847;
    public static final int TOOLTIP_DOWN = 3873;
    public static final int TOOLTIP_LEFT = 3875;
    public static final int TOOLTIP_RIGHT = 3874;
    public static final int TOOLTIP_UP = 3872;
    public static final int TOP_LEFT = 3841;
    public static final int TOP_MIDDLE = 3843;
    public static final int TOP_RIGHT = 3842;
    public static final int TYPE_LARGE = 0;
    public static final int TYPE_MINI = 2;
    public static final int TYPE_NORMAL = 1;
    private boolean childAnim;
    private View.OnClickListener clickListener;
    private FloatingActionButton fabMenu;
    private Drawable iconClose;
    private Drawable iconOpen;
    private boolean isDoubleIconSet;
    private boolean isMenuClicked;
    private boolean isMenuIn;
    private boolean isMenuOut;
    private boolean isOneIconSet;
    private boolean isShadow;
    private FrameLayout layMenu;
    private int mAnimDurationIn;
    private int mAnimDurationOut;
    private int mAnimType;
    private ArcLayout mArcLayout;
    private int mBackgroundColor;
    private int mChildSize;
    private Context mContext;
    private int mCornerRadius;
    private ArcMenuDuration mDuration;
    private int mFromDegree;
    private ImageView mIcon;
    private int mMarginBottom;
    private int mMarginLeft;
    private int mMarginRight;
    private int mMarginTop;
    private int mMenuGravity;
    private int mMenuMargin;
    private int mMenuSize;
    private int mPadding;
    private int mPrimaryChildCount;
    private int mScrollThreshold;
    private int mShadowBorder;
    private int mShadowElevation;
    private ColorStateList mTextColor;
    private int mTextSize;
    private int mToDegree;
    private int mToltalChildCount;
    private Typeface mTypeface;
    private boolean menuAnim;
    private Animation menuTranslateIn;
    private Animation menuTranslateOut;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bvapp.arcmenulibrary.ArcMenu$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ View.OnClickListener val$listener;

        AnonymousClass2(View.OnClickListener onClickListener) {
            this.val$listener = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ArcMenu.this.mArcLayout.isExpandDone()) {
                ArcMenu.this.bindItemAnimation(view, true, 250L).setAnimationListener(new Animation.AnimationListener() { // from class: com.bvapp.arcmenulibrary.ArcMenu.2.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        ArcMenu.this.postDelayed(new Runnable() { // from class: com.bvapp.arcmenulibrary.ArcMenu.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ArcMenu.this.itemDidDisappear();
                            }
                        }, 0L);
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }
                });
                int childCount = ArcMenu.this.mArcLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = ArcMenu.this.mArcLayout.getChildAt(i);
                    if (view != childAt) {
                        ArcMenu.this.bindItemAnimation(childAt, false, 200L);
                    }
                }
                ArcMenu.this.retFirstStatus();
                ArcMenu.this.mArcLayout.invalidate();
                ArcMenu.this.mArcLayout.setExpandDone(false);
                if (this.val$listener != null) {
                    this.val$listener.onClick(view);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AbsListViewScrollDetectorImpl extends AbsListViewScrollDetector {
        private AbsListView.OnScrollListener mOnScrollListener;
        private ScrollDirectionListener mScrollDirectionListener;

        private AbsListViewScrollDetectorImpl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScrollDirectionListener(ScrollDirectionListener scrollDirectionListener) {
            this.mScrollDirectionListener = scrollDirectionListener;
        }

        @Override // com.bvapp.arcmenulibrary.AbsListViewScrollDetector, android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.mOnScrollListener != null) {
                this.mOnScrollListener.onScroll(absListView, i, i2, i3);
            }
            super.onScroll(absListView, i, i2, i3);
        }

        @Override // com.bvapp.arcmenulibrary.AbsListViewScrollDetector
        public void onScrollDown() {
            ArcMenu.this.show();
            if (this.mScrollDirectionListener != null) {
                this.mScrollDirectionListener.onScrollDown();
            }
        }

        @Override // com.bvapp.arcmenulibrary.AbsListViewScrollDetector, android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (this.mOnScrollListener != null) {
                this.mOnScrollListener.onScrollStateChanged(absListView, i);
            }
            super.onScrollStateChanged(absListView, i);
        }

        @Override // com.bvapp.arcmenulibrary.AbsListViewScrollDetector
        public void onScrollUp() {
            ArcMenu.this.hide();
            if (this.mScrollDirectionListener != null) {
                this.mScrollDirectionListener.onScrollUp();
            }
        }

        public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
            this.mOnScrollListener = onScrollListener;
        }
    }

    /* loaded from: classes.dex */
    public enum ArcMenuDuration {
        LENGTH_SHORT(300),
        LENGTH_LONG(500);

        private int duration;

        ArcMenuDuration(int i) {
            this.duration = i;
        }

        public int getDuration() {
            return this.duration;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RecyclerViewScrollDetectorImpl extends RecyclerViewScrollDetector {
        private RecyclerView.OnScrollListener mOnScrollListener;
        private ScrollDirectionListener mScrollDirectionListener;

        private RecyclerViewScrollDetectorImpl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScrollDirectionListener(ScrollDirectionListener scrollDirectionListener) {
            this.mScrollDirectionListener = scrollDirectionListener;
        }

        @Override // com.bvapp.arcmenulibrary.RecyclerViewScrollDetector
        public void onScrollDown() {
            ArcMenu.this.show();
            if (this.mScrollDirectionListener != null) {
                this.mScrollDirectionListener.onScrollDown();
            }
        }

        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (this.mOnScrollListener != null) {
                this.mOnScrollListener.onScrollStateChanged(recyclerView, i);
            }
            super.onScrollStateChanged(recyclerView, i);
        }

        @Override // com.bvapp.arcmenulibrary.RecyclerViewScrollDetector
        public void onScrollUp() {
            ArcMenu.this.hide();
            if (this.mScrollDirectionListener != null) {
                this.mScrollDirectionListener.onScrollUp();
            }
        }

        @Override // com.bvapp.arcmenulibrary.RecyclerViewScrollDetector, android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (this.mOnScrollListener != null) {
                this.mOnScrollListener.onScrolled(recyclerView, i, i2);
            }
            super.onScrolled(recyclerView, i, i2);
        }

        public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
            this.mOnScrollListener = onScrollListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ScrollViewScrollDetectorImpl extends ScrollViewScrollDetector {
        private ObservableScrollView.OnScrollChangedListener mOnScrollChangedListener;
        private ScrollDirectionListener mScrollDirectionListener;

        private ScrollViewScrollDetectorImpl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScrollDirectionListener(ScrollDirectionListener scrollDirectionListener) {
            this.mScrollDirectionListener = scrollDirectionListener;
        }

        @Override // com.bvapp.arcmenulibrary.ScrollViewScrollDetector, com.bvapp.arcmenulibrary.widget.ObservableScrollView.OnScrollChangedListener
        public void onScrollChanged(ScrollView scrollView, int i, int i2, int i3, int i4) {
            if (this.mOnScrollChangedListener != null) {
                this.mOnScrollChangedListener.onScrollChanged(scrollView, i, i2, i3, i4);
            }
            super.onScrollChanged(scrollView, i, i2, i3, i4);
        }

        @Override // com.bvapp.arcmenulibrary.ScrollViewScrollDetector
        public void onScrollDown() {
            ArcMenu.this.show();
            if (this.mScrollDirectionListener != null) {
                this.mScrollDirectionListener.onScrollDown();
            }
        }

        @Override // com.bvapp.arcmenulibrary.ScrollViewScrollDetector
        public void onScrollUp() {
            ArcMenu.this.hide();
            if (this.mScrollDirectionListener != null) {
                this.mScrollDirectionListener.onScrollUp();
            }
        }

        public void setOnScrollChangedListener(ObservableScrollView.OnScrollChangedListener onScrollChangedListener) {
            this.mOnScrollChangedListener = onScrollChangedListener;
        }
    }

    public ArcMenu(Context context) {
        super(context);
        this.mDuration = ArcMenuDuration.LENGTH_SHORT;
        this.mAnimDurationOut = 300;
        this.mAnimDurationIn = 300;
        this.isMenuIn = true;
        this.mBackgroundColor = 0;
        this.mTextSize = 0;
        this.mTypeface = Typeface.DEFAULT;
        init(context);
    }

    public ArcMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDuration = ArcMenuDuration.LENGTH_SHORT;
        this.mAnimDurationOut = 300;
        this.mAnimDurationIn = 300;
        this.isMenuIn = true;
        this.mBackgroundColor = 0;
        this.mTextSize = 0;
        this.mTypeface = Typeface.DEFAULT;
        init(context);
        applyAttrs(context, attributeSet);
    }

    private void addChildToArcLayout(View view, String str, int i, View.OnClickListener onClickListener, boolean z) {
        if (this.mArcLayout != null) {
            int i2 = i * 2;
            this.mArcLayout.addView(view, i2);
            this.mArcLayout.addView(getContentView(str, z), i2 + 1);
            view.setOnClickListener(getItemClickListener(onClickListener));
        }
    }

    private void applyAttrs(Context context, AttributeSet attributeSet) {
        int dimensionPixelSize;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ArcMenu);
            switch (obtainStyledAttributes.getInt(R.styleable.ArcMenu_menuType, 1)) {
                case 0:
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.fab_size_large);
                    break;
                case 1:
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.fab_size_normal);
                    break;
                case 2:
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.fab_size_mini);
                    break;
                default:
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.fab_size_normal);
                    break;
            }
            this.fabMenu.setBackgroundColor(obtainStyledAttributes.getColor(R.styleable.ArcMenu_menuNormalColor, -16776961));
            this.isShadow = obtainStyledAttributes.getBoolean(R.styleable.ArcMenu_menuShadowElevation, false);
            this.fabMenu.setShadow(this.isShadow);
            this.mMenuSize = dimensionPixelSize;
            this.fabMenu.setSize(dimensionPixelSize);
            this.mArcLayout.setMenuSize(this.isShadow ? this.mMenuSize : this.mMenuSize / 2);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ArcMenu_menuImage);
            if (drawable != null) {
                this.mIcon.setVisibility(8);
                this.fabMenu.setIcon(drawable);
            } else if (this.isShadow) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mIcon.getLayoutParams();
                layoutParams.bottomMargin = (int) (this.fabMenu.getShadowSize() / 2.0f);
                this.mIcon.setLayoutParams(layoutParams);
            }
            this.mMenuMargin = (int) dpToPx(4.0f);
            this.mMenuGravity = obtainStyledAttributes.getInt(R.styleable.ArcMenu_menuGravity, 3849);
            setMenuGravity(this.mMenuGravity);
            this.mChildSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ArcMenu_menuChildSize, (int) getResources().getDimension(R.dimen.menu_child_size));
            this.mArcLayout.setChildSize(this.mChildSize);
            this.mArcLayout.setTextViewSize(this.mChildSize * 2, this.mChildSize / 2);
            this.mMarginBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ArcMenu_menuMarginBottom, 0);
            this.mMarginTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ArcMenu_menuMarginTop, 0);
            this.mMarginRight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ArcMenu_menuMarginRight, 0);
            this.mMarginLeft = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ArcMenu_menuMarginLeft, 0);
            this.mArcLayout.setMargin(this.mMarginLeft, this.mMarginTop, this.mMarginRight, this.mMarginBottom);
            this.mArcLayout.setMinRadius(obtainStyledAttributes.getInt(R.styleable.ArcMenu_menuChildRadius, (int) getResources().getDimension(R.dimen.menu_child_radius)));
            this.childAnim = obtainStyledAttributes.getBoolean(R.styleable.ArcMenu_menuChildAnim, false);
            this.mArcLayout.setItemRotation(this.childAnim);
            this.menuAnim = obtainStyledAttributes.getBoolean(R.styleable.ArcMenu_menuClickAnim, false);
            this.mArcLayout.setDefaultShift((int) dpToPx(10.0f));
            this.mScrollThreshold = getResources().getDimensionPixelOffset(R.dimen.menu_scroll_threshold);
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation bindItemAnimation(View view, boolean z, long j) {
        Animation createItemDisappearAnimation = createItemDisappearAnimation(j, z);
        view.setAnimation(createItemDisappearAnimation);
        return createItemDisappearAnimation;
    }

    private static Animation createItemDisappearAnimation(long j, boolean z) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new ScaleAnimation(1.0f, z ? 1.4f : 0.0f, 1.0f, z ? 1.4f : 0.0f, 1, 0.5f, 1, 0.5f));
        animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        animationSet.setDuration(j);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setFillAfter(true);
        return animationSet;
    }

    public static float dpToPx(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }

    private TextView getContentView(String str, boolean z) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.mBackgroundColor);
        gradientDrawable.setCornerRadius(this.mCornerRadius);
        TextView textView = new TextView(this.mContext);
        textView.setText(str);
        textView.setPadding(this.mPadding, this.mPadding / 2, this.mPadding, this.mPadding / 2);
        textView.setTypeface(this.mTypeface);
        if (this.mTextSize > 0) {
            textView.setTextSize(this.mTextSize);
        } else {
            textView.setTextSize(10.0f);
        }
        if (this.mTextColor != null) {
            textView.setTextColor(this.mTextColor);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 0.0f);
        layoutParams.gravity = 5;
        textView.setLayoutParams(layoutParams);
        new FrameLayout(this.mContext).setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        if (z) {
            int[] textSize = getTextSize(str);
            Log.i("Child measure", "Size Height: " + textSize[0] + "   Size Width: " + textSize[1]);
            setArcLayoutTextSize(textSize[0], textSize[1]);
        }
        return textView;
    }

    private View.OnClickListener getItemClickListener(View.OnClickListener onClickListener) {
        return new AnonymousClass2(onClickListener);
    }

    private int[] getTextSize(String str) {
        int[] iArr = new int[2];
        iArr[0] = Util.getWidth(this.mContext, str, this.mTextSize == 0 ? 10 : this.mTextSize, Util.getDeviceWidth(), this.mTypeface, this.mPadding);
        iArr[1] = Util.getHeight(this.mContext, str, this.mTextSize != 0 ? this.mTextSize : 10, Util.getDeviceWidth(), this.mTypeface, this.mPadding);
        return iArr;
    }

    private void init(Context context) {
        this.mContext = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.arc_menu, this);
        this.mArcLayout = (ArcLayout) findViewById(R.id.arcmenu_item_layout);
        this.layMenu = (FrameLayout) findViewById(R.id.layArcMenu);
        this.mIcon = (ImageView) findViewById(R.id.imgPlusIcon);
        this.fabMenu = (FloatingActionButton) findViewById(R.id.fabArcMenu);
        this.fabMenu.setOnClickListener(new View.OnClickListener() { // from class: com.bvapp.arcmenulibrary.ArcMenu.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ArcMenu.this.menuAnim && ArcMenu.this.mArcLayout.isAnimDone()) {
                    if (ArcMenu.this.clickListener != null) {
                        ArcMenu.this.clickListener.onClick(ArcMenu.this.fabMenu);
                    }
                    ViewAnim.shrinkExpandAnimation(ArcMenu.this.fabMenu);
                    if (ArcMenu.this.isMenuClicked) {
                        ArcMenu.this.isMenuClicked = false;
                        if (!ArcMenu.this.isDoubleIconSet && !ArcMenu.this.isOneIconSet) {
                            ViewAnim.rotateAnimation(ArcMenu.this.mIcon, false);
                        } else if (ArcMenu.this.isDoubleIconSet && !ArcMenu.this.isOneIconSet) {
                            ArcMenu.this.fabMenu.setIcon(ArcMenu.this.iconClose, true);
                        }
                    } else {
                        ArcMenu.this.isMenuClicked = true;
                        if (!ArcMenu.this.isDoubleIconSet && !ArcMenu.this.isOneIconSet) {
                            ViewAnim.rotateAnimation(ArcMenu.this.mIcon, true);
                        } else if (ArcMenu.this.isDoubleIconSet && !ArcMenu.this.isOneIconSet) {
                            ArcMenu.this.fabMenu.setIcon(ArcMenu.this.iconOpen, true);
                        }
                    }
                }
                if (ArcMenu.this.mArcLayout.isAnimDone()) {
                    ArcMenu.this.mArcLayout.switchState(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void itemDidDisappear() {
        int childCount = this.mArcLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.mArcLayout.getChildAt(i).clearAnimation();
        }
        this.mArcLayout.switchState(false);
    }

    public static float pxToDp(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retFirstStatus() {
        if (this.isMenuClicked) {
            this.isMenuClicked = false;
            if (!this.isDoubleIconSet && !this.isOneIconSet) {
                ViewAnim.rotateAnimation(this.mIcon, false);
            } else {
                if (!this.isDoubleIconSet || this.isOneIconSet) {
                    return;
                }
                this.fabMenu.setIcon(this.iconClose, true);
            }
        }
    }

    private void setArcLayoutParam(int i, int i2, int i3, int i4) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mArcLayout.getLayoutParams();
        this.mAnimType = i;
        layoutParams.gravity = i2;
        this.mArcLayout.setLayoutParams(layoutParams);
        this.mArcLayout.setArc(i3, i4);
    }

    private void setArcLayoutTextSize(int i, int i2) {
        this.mArcLayout.setTextSize(i, i2);
    }

    private void setDefaultAnim() {
        int i = this.mAnimType;
        if (i == 3850) {
            this.menuTranslateOut = AnimationObject.translateAnimationRelativeToParent(0.0f, 0.0f, 0.0f, 0.2f, new DecelerateInterpolator(), this.mAnimDurationOut, true);
            this.menuTranslateIn = AnimationObject.translateAnimationRelativeToParent(0.0f, 0.0f, 2.0f, 0.0f, new DecelerateInterpolator(), this.mAnimDurationIn, true);
        } else if (i == 3854) {
            this.menuTranslateOut = AnimationObject.translateAnimationRelativeToParent(0.0f, 0.0f, 0.0f, 0.7f, new DecelerateInterpolator(), this.mAnimDurationOut, true);
            this.menuTranslateIn = AnimationObject.translateAnimationRelativeToParent(0.0f, 0.0f, 7.0f, 0.0f, new DecelerateInterpolator(), this.mAnimDurationIn, true);
        } else {
            if (i != 3858) {
                return;
            }
            this.menuTranslateOut = AnimationObject.translateAnimationRelativeToParent(0.0f, 0.0f, 0.0f, -0.2f, new DecelerateInterpolator(), this.mAnimDurationOut, true);
            this.menuTranslateIn = AnimationObject.translateAnimationRelativeToParent(0.0f, 0.0f, -0.2f, 0.0f, new DecelerateInterpolator(), this.mAnimDurationIn, true);
        }
    }

    private void setMenuParam(int i, int i2, int i3, int i4, int i5) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.layMenu.getLayoutParams();
        layoutParams.setMargins(i, i2, i3, i4);
        layoutParams.gravity = i5;
        this.layMenu.setLayoutParams(layoutParams);
    }

    public void addChildAt(View view, String str, int i, View.OnClickListener onClickListener) {
        addChildToArcLayout(view, str, i, onClickListener, true);
    }

    public void addItem(View view, String str, View.OnClickListener onClickListener) {
        this.mArcLayout.addView(view);
        this.mArcLayout.addView(getContentView(str, true));
        view.setOnClickListener(getItemClickListener(onClickListener));
    }

    public void attachToListView(@NonNull AbsListView absListView) {
        attachToListView(absListView, null, null);
    }

    public void attachToListView(@NonNull AbsListView absListView, ScrollDirectionListener scrollDirectionListener) {
        attachToListView(absListView, scrollDirectionListener, null);
    }

    public void attachToListView(@NonNull AbsListView absListView, ScrollDirectionListener scrollDirectionListener, AbsListView.OnScrollListener onScrollListener) {
        AbsListViewScrollDetectorImpl absListViewScrollDetectorImpl = new AbsListViewScrollDetectorImpl();
        absListViewScrollDetectorImpl.setScrollDirectionListener(scrollDirectionListener);
        absListViewScrollDetectorImpl.setOnScrollListener(onScrollListener);
        absListViewScrollDetectorImpl.setListView(absListView);
        absListViewScrollDetectorImpl.setScrollThreshold(this.mScrollThreshold);
        absListView.setOnScrollListener(absListViewScrollDetectorImpl);
    }

    public void attachToRecyclerView(@NonNull RecyclerView recyclerView) {
        attachToRecyclerView(recyclerView, null, null);
    }

    public void attachToRecyclerView(@NonNull RecyclerView recyclerView, ScrollDirectionListener scrollDirectionListener) {
        attachToRecyclerView(recyclerView, scrollDirectionListener, null);
    }

    public void attachToRecyclerView(@NonNull RecyclerView recyclerView, ScrollDirectionListener scrollDirectionListener, RecyclerView.OnScrollListener onScrollListener) {
        RecyclerViewScrollDetectorImpl recyclerViewScrollDetectorImpl = new RecyclerViewScrollDetectorImpl();
        recyclerViewScrollDetectorImpl.setScrollDirectionListener(scrollDirectionListener);
        recyclerViewScrollDetectorImpl.setOnScrollListener(onScrollListener);
        recyclerViewScrollDetectorImpl.setScrollThreshold(this.mScrollThreshold);
        recyclerView.addOnScrollListener(recyclerViewScrollDetectorImpl);
    }

    public void attachToScrollView(@NonNull ObservableScrollView observableScrollView) {
        attachToScrollView(observableScrollView, null, null);
    }

    public void attachToScrollView(@NonNull ObservableScrollView observableScrollView, ScrollDirectionListener scrollDirectionListener) {
        attachToScrollView(observableScrollView, scrollDirectionListener, null);
    }

    public void attachToScrollView(@NonNull ObservableScrollView observableScrollView, ScrollDirectionListener scrollDirectionListener, ObservableScrollView.OnScrollChangedListener onScrollChangedListener) {
        ScrollViewScrollDetectorImpl scrollViewScrollDetectorImpl = new ScrollViewScrollDetectorImpl();
        scrollViewScrollDetectorImpl.setScrollDirectionListener(scrollDirectionListener);
        scrollViewScrollDetectorImpl.setOnScrollChangedListener(onScrollChangedListener);
        scrollViewScrollDetectorImpl.setScrollThreshold(this.mScrollThreshold);
        observableScrollView.setOnScrollChangedListener(scrollViewScrollDetectorImpl);
    }

    public int getColor(@ColorRes int i) {
        return getResources().getColor(i);
    }

    public boolean hasChid() {
        return this.mArcLayout.getChildCount() > 0;
    }

    public void hide() {
        if (!this.isMenuIn || this.isMenuOut) {
            return;
        }
        this.isMenuOut = true;
        this.isMenuIn = false;
        menuOut();
    }

    public boolean isClose() {
        return !this.mArcLayout.isExpanded();
    }

    public boolean isOpen() {
        return this.mArcLayout.isExpanded();
    }

    public void menuIn() {
        this.mArcLayout.setExpandMenu(false);
        startAnimation(this.menuTranslateIn);
    }

    public void menuOut() {
        if (this.mArcLayout.isExpanded()) {
            this.mArcLayout.switchState(true);
            retFirstStatus();
        }
        startAnimation(this.menuTranslateOut);
    }

    @Override // android.view.View
    public boolean performClick() {
        this.mArcLayout.switchState(!this.mArcLayout.isExpanded());
        return this.mArcLayout.isExpanded();
    }

    public void removeAllChild() {
        if (this.mArcLayout == null || this.mArcLayout.getChildCount() <= 0) {
            return;
        }
        this.mArcLayout.removeAllViews();
    }

    public void removeChildAt(int i) {
        if (this.mArcLayout == null || this.mArcLayout.getChildCount() <= 0) {
            return;
        }
        int childCount = this.mArcLayout.getChildCount();
        if (childCount % 2 == 0) {
            int i2 = i * 2;
            if (i2 + 1 <= childCount) {
                this.mArcLayout.removeViewAt(i2);
                this.mArcLayout.removeViewAt(i2);
            }
        }
    }

    public void replaceChildAt(View view, String str, int i, View.OnClickListener onClickListener) {
        if (this.mArcLayout != null) {
            removeChildAt(i);
            addChildToArcLayout(view, str, i, onClickListener, false);
            int[] textSize = getTextSize(str);
            this.mArcLayout.changeTextSize(i, textSize[0], textSize[1]);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void setAnim(int i, int i2, int i3, int i4, int i5, int i6) {
        Interpolator decelerateInterpolator;
        Interpolator decelerateInterpolator2;
        float f;
        float f2;
        float f3;
        float f4;
        this.mAnimDurationOut = i;
        this.mAnimDurationIn = i2;
        switch (i6) {
            case ANIM_INTERPOLATOR_DECLERATE /* 3862 */:
                decelerateInterpolator = new DecelerateInterpolator();
                break;
            case ANIM_INTERPOLATOR_ACCELERATE /* 3863 */:
                decelerateInterpolator = new AccelerateInterpolator();
                break;
            case ANIM_INTERPOLATOR_ACCELERATE_DECLERATE /* 3864 */:
                decelerateInterpolator = new AccelerateDecelerateInterpolator();
                break;
            case ANIM_INTERPOLATOR_ANTICIPATE /* 3865 */:
                decelerateInterpolator = new AnticipateInterpolator();
                break;
            case ANIM_INTERPOLATOR_BOUNCE /* 3866 */:
                decelerateInterpolator = new BounceInterpolator();
                break;
            default:
                decelerateInterpolator = new AccelerateInterpolator();
                break;
        }
        Interpolator interpolator = decelerateInterpolator;
        switch (i5) {
            case ANIM_INTERPOLATOR_DECLERATE /* 3862 */:
                decelerateInterpolator2 = new DecelerateInterpolator();
                break;
            case ANIM_INTERPOLATOR_ACCELERATE /* 3863 */:
                decelerateInterpolator2 = new AccelerateInterpolator();
                break;
            case ANIM_INTERPOLATOR_ACCELERATE_DECLERATE /* 3864 */:
                decelerateInterpolator2 = new AccelerateDecelerateInterpolator();
                break;
            case ANIM_INTERPOLATOR_ANTICIPATE /* 3865 */:
                decelerateInterpolator2 = new AnticipateInterpolator();
                break;
            case ANIM_INTERPOLATOR_BOUNCE /* 3866 */:
                decelerateInterpolator2 = new BounceInterpolator();
                break;
            default:
                decelerateInterpolator2 = new AccelerateInterpolator();
                break;
        }
        Interpolator interpolator2 = decelerateInterpolator2;
        float f5 = 0.7f;
        switch (i3) {
            case ANIM_BOTTOM_TO_DOWN /* 3850 */:
            default:
                f2 = 0.2f;
                f = 0.0f;
                break;
            case ANIM_BOTTOM_TO_LEFT /* 3851 */:
            case ANIM_TOP_TO_LEFT /* 3859 */:
                f = -0.2f;
                f2 = 0.0f;
                break;
            case ANIM_BOTTOM_TO_RIGHT /* 3852 */:
                f = 0.2f;
                f2 = 0.0f;
                break;
            case ANIM_BOTTOM_TO_UP /* 3853 */:
                f2 = -1.0f;
                f = 0.0f;
                break;
            case ANIM_MIDDLE_TO_DOWN /* 3854 */:
                f2 = 0.7f;
                f = 0.0f;
                break;
            case ANIM_MIDDLE_TO_RIGHT /* 3855 */:
                f = 0.7f;
                f2 = 0.0f;
                break;
            case ANIM_MIDDLE_TO_LEFT /* 3856 */:
                f = -0.7f;
                f2 = 0.0f;
                break;
            case ANIM_MIDDLE_TO_UP /* 3857 */:
                f2 = -0.7f;
                f = 0.0f;
                break;
            case ANIM_TOP_TO_UP /* 3858 */:
                f2 = -0.2f;
                f = 0.0f;
                break;
            case ANIM_TOP_TO_RIGHT /* 3860 */:
                f = 0.2f;
                f2 = 0.2f;
                break;
            case ANIM_TOP_TO_DOWN /* 3861 */:
                f2 = 1.0f;
                f = 0.0f;
                break;
        }
        this.menuTranslateOut = AnimationObject.translateAnimationRelativeToParent(0.0f, f, 0.0f, f2, interpolator2, this.mAnimDurationOut, true);
        switch (i4) {
            case ANIM_BOTTOM_TO_DOWN /* 3850 */:
            default:
                f5 = 0.2f;
                f3 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_BOTTOM_TO_LEFT /* 3851 */:
            case ANIM_TOP_TO_LEFT /* 3859 */:
                f3 = -0.2f;
                f5 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_BOTTOM_TO_RIGHT /* 3852 */:
                f3 = 0.2f;
                f5 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_BOTTOM_TO_UP /* 3853 */:
                f5 = -1.0f;
                f3 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_MIDDLE_TO_DOWN /* 3854 */:
                f3 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_MIDDLE_TO_RIGHT /* 3855 */:
                f3 = 0.7f;
                f5 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_MIDDLE_TO_LEFT /* 3856 */:
                f3 = -0.7f;
                f5 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_MIDDLE_TO_UP /* 3857 */:
                f5 = -0.7f;
                f3 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_TOP_TO_UP /* 3858 */:
                f5 = -0.2f;
                f3 = 0.0f;
                f4 = 0.0f;
                break;
            case ANIM_TOP_TO_RIGHT /* 3860 */:
                f3 = 0.2f;
                f4 = 0.2f;
                f5 = 0.0f;
                break;
            case ANIM_TOP_TO_DOWN /* 3861 */:
                f5 = 1.0f;
                f3 = 0.0f;
                f4 = 0.0f;
                break;
        }
        this.menuTranslateIn = AnimationObject.translateAnimationRelativeToParent(f3, 0.0f, f5, f4, interpolator, this.mAnimDurationIn, true);
    }

    public void setArc(float f, float f2) {
        this.mArcLayout.setArc(f, f2);
    }

    public void setChildSize(int i) {
        this.mArcLayout.setChildSize(i);
    }

    public void setColorNormal(int i) {
        this.fabMenu.setBackgroundColor(i);
    }

    public void setColorNormalResId(@ColorRes int i) {
        setColorNormal(getColor(i));
    }

    public void setDefaultIcon() {
        this.isDoubleIconSet = false;
        this.isOneIconSet = true;
        this.mIcon.setVisibility(0);
    }

    public void setDuration(int i) {
        this.mArcLayout.setDuration(i);
    }

    public void setDuration(ArcMenuDuration arcMenuDuration) {
        this.mDuration = arcMenuDuration;
        this.mArcLayout.setDuration(this.mDuration.getDuration());
    }

    public void setIcon(@DrawableRes int i) {
        try {
            setIcon(ContextCompat.getDrawable(getContext(), i));
        } catch (Exception e) {
            e.printStackTrace();
            this.isOneIconSet = false;
        }
    }

    public void setIcon(@DrawableRes int i, @DrawableRes int i2) {
        try {
            setIcon(ContextCompat.getDrawable(getContext(), i), ContextCompat.getDrawable(getContext(), i2));
        } catch (Exception e) {
            e.printStackTrace();
            this.isDoubleIconSet = false;
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            this.iconClose = drawable;
            this.fabMenu.setIcon(this.iconClose);
            this.mIcon.setVisibility(8);
            this.isDoubleIconSet = false;
            this.isOneIconSet = true;
        }
    }

    public void setIcon(Drawable drawable, Drawable drawable2) {
        if (drawable == null || drawable2 == null) {
            return;
        }
        this.iconClose = drawable;
        this.iconOpen = drawable2;
        this.fabMenu.setIcon(this.iconClose);
        this.mIcon.setVisibility(8);
        this.isDoubleIconSet = true;
        this.isOneIconSet = false;
    }

    public void setIconSize(int i) {
        this.fabMenu.setIconSize(i);
    }

    public void setMenuGravity(int i) {
        this.mArcLayout.setMenuGravity(i);
        switch (i) {
            case 3841:
                this.mFromDegree = -5;
                this.mToDegree = 95;
                setArcLayoutParam(ANIM_TOP_TO_UP, 51, -5, 95);
                setMenuParam(this.mMenuMargin, this.mMenuMargin, 0, 0, 51);
                break;
            case 3842:
                this.mFromDegree = 85;
                this.mToDegree = Opcodes.INVOKEINTERFACE;
                setArcLayoutParam(ANIM_TOP_TO_UP, 53, 85, Opcodes.INVOKEINTERFACE);
                setMenuParam(0, this.mMenuMargin, this.mMenuMargin, 0, 53);
                break;
            case 3843:
                this.mFromDegree = -5;
                this.mToDegree = Opcodes.INVOKEINTERFACE;
                setArcLayoutParam(ANIM_TOP_TO_UP, 49, -5, Opcodes.INVOKEINTERFACE);
                setMenuParam(0, this.mMenuMargin, 0, 0, 49);
                break;
            case 3844:
                this.mFromDegree = 265;
                this.mToDegree = 365;
                setArcLayoutParam(ANIM_BOTTOM_TO_DOWN, 83, 265, 365);
                setMenuParam(this.mMenuMargin, 0, 0, this.mMenuMargin, 83);
                break;
            case 3845:
                this.mFromDegree = 275;
                this.mToDegree = Opcodes.DRETURN;
                setArcLayoutParam(ANIM_BOTTOM_TO_DOWN, 85, 275, Opcodes.DRETURN);
                setMenuParam(0, 0, this.mMenuMargin, this.mMenuMargin, 85);
                break;
            case 3846:
                this.mFromDegree = Opcodes.DRETURN;
                this.mToDegree = 365;
                setArcLayoutParam(ANIM_BOTTOM_TO_DOWN, 81, Opcodes.DRETURN, 365);
                setMenuParam(0, 0, 0, this.mMenuMargin, 81);
                break;
            case 3847:
                this.mFromDegree = 275;
                this.mToDegree = 85;
                setArcLayoutParam(ANIM_MIDDLE_TO_DOWN, 21, 275, 85);
                setMenuParam(0, 0, this.mMenuMargin, 0, 21);
                break;
            case 3848:
                this.mFromDegree = -95;
                this.mToDegree = 95;
                setArcLayoutParam(ANIM_MIDDLE_TO_DOWN, 19, -95, 95);
                setMenuParam(this.mMenuMargin, 0, 0, 0, 19);
                break;
            case 3849:
                this.mFromDegree = 0;
                this.mToDegree = 360;
                setArcLayoutParam(ANIM_MIDDLE_TO_DOWN, 17, 0, 360);
                setMenuParam(0, 0, 0, 0, 17);
                break;
            default:
                this.mFromDegree = 0;
                this.mToDegree = 360;
                setArcLayoutParam(ANIM_MIDDLE_TO_DOWN, 17, 0, 360);
                this.mArcLayout.setMenuGravity(3849);
                setMenuParam(0, 0, 0, 0, 17);
                break;
        }
        setDefaultAnim();
    }

    public void setMinRadius(int i) {
        this.mArcLayout.setMinRadius((int) dpToPx(i));
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    public void setRadius(int i) {
        this.mArcLayout.setRadius((int) dpToPx(i));
    }

    public void setToolTipBackColor(int i) {
        this.mBackgroundColor = i;
    }

    public void setToolTipCorner(float f) {
        this.mCornerRadius = (int) dpToPx(f);
    }

    public void setToolTipPadding(float f) {
        this.mPadding = (int) dpToPx(f);
    }

    public void setToolTipSide(int i) {
        this.mArcLayout.setToolTipSide(i);
    }

    public void setToolTipTextColor(int i) {
        this.mTextColor = ColorStateList.valueOf(i);
    }

    public void setToolTipTextSize(int i) {
        this.mTextSize = i;
    }

    public void show() {
        if (this.isMenuIn || !this.isMenuOut) {
            return;
        }
        this.isMenuOut = false;
        this.isMenuIn = true;
        menuIn();
    }

    public void showTooltip(boolean z) {
        this.mArcLayout.showTooltip(z);
    }
}
