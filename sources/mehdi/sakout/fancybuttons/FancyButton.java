package mehdi.sakout.fancybuttons;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.FontRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class FancyButton extends LinearLayout {
    public static final int POSITION_BOTTOM = 4;
    public static final int POSITION_LEFT = 1;
    public static final int POSITION_RIGHT = 2;
    public static final int POSITION_TOP = 3;
    public static final String TAG = "FancyButton";
    private int mBorderColor;
    private int mBorderWidth;
    private Context mContext;
    private int mDefaultBackgroundColor;
    private int mDefaultIconColor;
    private String mDefaultIconFont;
    private int mDefaultTextColor;
    private String mDefaultTextFont;
    private int mDefaultTextGravity;
    private int mDefaultTextSize;
    private int mDisabledBackgroundColor;
    private int mDisabledBorderColor;
    private int mDisabledTextColor;
    private boolean mEnabled;
    private int mFocusBackgroundColor;
    private String mFontIcon;
    private int mFontIconSize;
    private TextView mFontIconView;
    private boolean mGhost;
    private int mIconPaddingBottom;
    private int mIconPaddingLeft;
    private int mIconPaddingRight;
    private int mIconPaddingTop;
    private int mIconPosition;
    private Drawable mIconResource;
    private Typeface mIconTypeFace;
    private ImageView mIconView;
    private int mRadius;
    private int mRadiusBottomLeft;
    private int mRadiusBottomRight;
    private int mRadiusTopLeft;
    private int mRadiusTopRight;
    private String mText;
    private boolean mTextAllCaps;
    private int mTextPosition;
    private Typeface mTextTypeFace;
    private TextView mTextView;
    private boolean mUseRippleEffect;
    private boolean mUseSystemFont;
    private int textStyle;

    @TargetApi(21)
    /* loaded from: classes2.dex */
    private class CustomOutline extends ViewOutlineProvider {
        int height;
        int width;

        CustomOutline(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (FancyButton.this.mRadius == 0) {
                outline.setRect(0, 10, this.width, this.height);
            } else {
                outline.setRoundRect(0, 10, this.width, this.height, FancyButton.this.mRadius);
            }
        }
    }

    public FancyButton(Context context) {
        super(context);
        this.mDefaultBackgroundColor = ViewCompat.MEASURED_STATE_MASK;
        this.mFocusBackgroundColor = 0;
        this.mDisabledBackgroundColor = Color.parseColor("#f6f7f9");
        this.mDisabledTextColor = Color.parseColor("#bec2c9");
        this.mDisabledBorderColor = Color.parseColor("#dddfe2");
        this.mDefaultTextColor = -1;
        this.mDefaultIconColor = -1;
        this.mTextPosition = 1;
        this.mDefaultTextSize = Utils.spToPx(getContext(), 15.0f);
        this.mDefaultTextGravity = 17;
        this.mText = null;
        this.mIconResource = null;
        this.mFontIconSize = Utils.spToPx(getContext(), 15.0f);
        this.mFontIcon = null;
        this.mIconPosition = 1;
        this.mIconPaddingLeft = 10;
        this.mIconPaddingRight = 10;
        this.mIconPaddingTop = 0;
        this.mIconPaddingBottom = 0;
        this.mBorderColor = 0;
        this.mBorderWidth = 0;
        this.mRadius = 0;
        this.mRadiusTopLeft = 0;
        this.mRadiusTopRight = 0;
        this.mRadiusBottomLeft = 0;
        this.mRadiusBottomRight = 0;
        this.mEnabled = true;
        this.mTextAllCaps = false;
        this.mTextTypeFace = null;
        this.mIconTypeFace = null;
        this.mDefaultIconFont = "fontawesome.ttf";
        this.mDefaultTextFont = "robotoregular.ttf";
        this.mGhost = false;
        this.mUseSystemFont = false;
        this.mUseRippleEffect = true;
        this.mContext = context;
        this.mTextTypeFace = Utils.findFont(this.mContext, this.mDefaultTextFont, null);
        this.mIconTypeFace = Utils.findFont(this.mContext, this.mDefaultIconFont, null);
        initializeFancyButton();
    }

    public FancyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDefaultBackgroundColor = ViewCompat.MEASURED_STATE_MASK;
        this.mFocusBackgroundColor = 0;
        this.mDisabledBackgroundColor = Color.parseColor("#f6f7f9");
        this.mDisabledTextColor = Color.parseColor("#bec2c9");
        this.mDisabledBorderColor = Color.parseColor("#dddfe2");
        this.mDefaultTextColor = -1;
        this.mDefaultIconColor = -1;
        this.mTextPosition = 1;
        this.mDefaultTextSize = Utils.spToPx(getContext(), 15.0f);
        this.mDefaultTextGravity = 17;
        this.mText = null;
        this.mIconResource = null;
        this.mFontIconSize = Utils.spToPx(getContext(), 15.0f);
        this.mFontIcon = null;
        this.mIconPosition = 1;
        this.mIconPaddingLeft = 10;
        this.mIconPaddingRight = 10;
        this.mIconPaddingTop = 0;
        this.mIconPaddingBottom = 0;
        this.mBorderColor = 0;
        this.mBorderWidth = 0;
        this.mRadius = 0;
        this.mRadiusTopLeft = 0;
        this.mRadiusTopRight = 0;
        this.mRadiusBottomLeft = 0;
        this.mRadiusBottomRight = 0;
        this.mEnabled = true;
        this.mTextAllCaps = false;
        this.mTextTypeFace = null;
        this.mIconTypeFace = null;
        this.mDefaultIconFont = "fontawesome.ttf";
        this.mDefaultTextFont = "robotoregular.ttf";
        this.mGhost = false;
        this.mUseSystemFont = false;
        this.mUseRippleEffect = true;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FancyButtonsAttrs, 0, 0);
        initAttributesArray(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        initializeFancyButton();
    }

    private void applyRadius(GradientDrawable gradientDrawable) {
        if (this.mRadius > 0) {
            gradientDrawable.setCornerRadius(this.mRadius);
        } else {
            gradientDrawable.setCornerRadii(new float[]{this.mRadiusTopLeft, this.mRadiusTopLeft, this.mRadiusTopRight, this.mRadiusTopRight, this.mRadiusBottomRight, this.mRadiusBottomRight, this.mRadiusBottomLeft, this.mRadiusBottomLeft});
        }
    }

    @TargetApi(21)
    private Drawable getRippleDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        return !this.mEnabled ? drawable3 : new RippleDrawable(ColorStateList.valueOf(this.mFocusBackgroundColor), drawable, drawable2);
    }

    private Typeface getTypeface(TypedArray typedArray) {
        int resourceId;
        int resourceId2;
        if (typedArray.hasValue(R.styleable.FancyButtonsAttrs_android_fontFamily) && (resourceId2 = typedArray.getResourceId(R.styleable.FancyButtonsAttrs_android_fontFamily, 0)) != 0) {
            return ResourcesCompat.getFont(getContext(), resourceId2);
        }
        if (!typedArray.hasValue(R.styleable.FancyButtonsAttrs_fb_textFontRes) || (resourceId = typedArray.getResourceId(R.styleable.FancyButtonsAttrs_fb_textFontRes, 0)) == 0) {
            return null;
        }
        return ResourcesCompat.getFont(getContext(), resourceId);
    }

    private void initAttributesArray(TypedArray typedArray) {
        this.mDefaultBackgroundColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_defaultColor, this.mDefaultBackgroundColor);
        this.mFocusBackgroundColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_focusColor, this.mFocusBackgroundColor);
        this.mDisabledBackgroundColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_disabledColor, this.mDisabledBackgroundColor);
        this.mEnabled = typedArray.getBoolean(R.styleable.FancyButtonsAttrs_android_enabled, true);
        this.mDisabledTextColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_disabledTextColor, this.mDisabledTextColor);
        this.mDisabledBorderColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_disabledBorderColor, this.mDisabledBorderColor);
        this.mDefaultTextColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_textColor, this.mDefaultTextColor);
        this.mDefaultIconColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_iconColor, this.mDefaultTextColor);
        this.mDefaultTextSize = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_textSize, this.mDefaultTextSize);
        this.mDefaultTextSize = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_android_textSize, this.mDefaultTextSize);
        this.mDefaultTextGravity = typedArray.getInt(R.styleable.FancyButtonsAttrs_fb_textGravity, this.mDefaultTextGravity);
        this.mBorderColor = typedArray.getColor(R.styleable.FancyButtonsAttrs_fb_borderColor, this.mBorderColor);
        this.mBorderWidth = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_borderWidth, this.mBorderWidth);
        this.mRadius = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_radius, this.mRadius);
        this.mRadiusTopLeft = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_radiusTopLeft, this.mRadius);
        this.mRadiusTopRight = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_radiusTopRight, this.mRadius);
        this.mRadiusBottomLeft = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_radiusBottomLeft, this.mRadius);
        this.mRadiusBottomRight = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_radiusBottomRight, this.mRadius);
        this.mFontIconSize = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_fontIconSize, this.mFontIconSize);
        this.mIconPaddingLeft = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_iconPaddingLeft, this.mIconPaddingLeft);
        this.mIconPaddingRight = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_iconPaddingRight, this.mIconPaddingRight);
        this.mIconPaddingTop = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_iconPaddingTop, this.mIconPaddingTop);
        this.mIconPaddingBottom = (int) typedArray.getDimension(R.styleable.FancyButtonsAttrs_fb_iconPaddingBottom, this.mIconPaddingBottom);
        this.mTextAllCaps = typedArray.getBoolean(R.styleable.FancyButtonsAttrs_fb_textAllCaps, false);
        this.mTextAllCaps = typedArray.getBoolean(R.styleable.FancyButtonsAttrs_android_textAllCaps, false);
        this.mGhost = typedArray.getBoolean(R.styleable.FancyButtonsAttrs_fb_ghost, this.mGhost);
        this.mUseSystemFont = typedArray.getBoolean(R.styleable.FancyButtonsAttrs_fb_useSystemFont, this.mUseSystemFont);
        String string = typedArray.getString(R.styleable.FancyButtonsAttrs_fb_text);
        if (string == null) {
            string = typedArray.getString(R.styleable.FancyButtonsAttrs_android_text);
        }
        this.mIconPosition = typedArray.getInt(R.styleable.FancyButtonsAttrs_fb_iconPosition, this.mIconPosition);
        this.textStyle = typedArray.getInt(R.styleable.FancyButtonsAttrs_android_textStyle, 0);
        String string2 = typedArray.getString(R.styleable.FancyButtonsAttrs_fb_fontIconResource);
        String string3 = typedArray.getString(R.styleable.FancyButtonsAttrs_fb_iconFont);
        String string4 = typedArray.getString(R.styleable.FancyButtonsAttrs_fb_textFont);
        try {
            this.mIconResource = typedArray.getDrawable(R.styleable.FancyButtonsAttrs_fb_iconResource);
        } catch (Exception unused) {
            this.mIconResource = null;
        }
        if (string2 != null) {
            this.mFontIcon = string2;
        }
        if (string != null) {
            if (this.mTextAllCaps) {
                string = string.toUpperCase();
            }
            this.mText = string;
        }
        if (isInEditMode()) {
            return;
        }
        this.mIconTypeFace = string3 != null ? Utils.findFont(this.mContext, string3, this.mDefaultIconFont) : Utils.findFont(this.mContext, this.mDefaultIconFont, null);
        Typeface typeface = getTypeface(typedArray);
        if (typeface != null) {
            this.mTextTypeFace = typeface;
        } else {
            this.mTextTypeFace = string4 != null ? Utils.findFont(this.mContext, string4, this.mDefaultTextFont) : Utils.findFont(this.mContext, this.mDefaultTextFont, null);
        }
    }

    private void initializeButtonContainer() {
        if (this.mIconPosition == 3 || this.mIconPosition == 4) {
            setOrientation(1);
        } else {
            setOrientation(0);
        }
        if (getLayoutParams() == null) {
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        }
        setGravity(17);
        if (this.mIconResource == null && this.mFontIcon == null && getPaddingLeft() == 0 && getPaddingRight() == 0 && getPaddingTop() == 0 && getPaddingBottom() == 0) {
            setPadding(20, 0, 20, 0);
        }
    }

    private void initializeFancyButton() {
        initializeButtonContainer();
        this.mTextView = setupTextView();
        this.mIconView = setupIconView();
        this.mFontIconView = setupFontIconView();
        removeAllViews();
        setupBackground();
        ArrayList arrayList = new ArrayList();
        if (this.mIconPosition == 1 || this.mIconPosition == 3) {
            if (this.mIconView != null) {
                arrayList.add(this.mIconView);
            }
            if (this.mFontIconView != null) {
                arrayList.add(this.mFontIconView);
            }
            if (this.mTextView != null) {
                arrayList.add(this.mTextView);
            }
        } else {
            if (this.mTextView != null) {
                arrayList.add(this.mTextView);
            }
            if (this.mIconView != null) {
                arrayList.add(this.mIconView);
            }
            if (this.mFontIconView != null) {
                arrayList.add(this.mFontIconView);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            addView((View) it.next());
        }
    }

    @SuppressLint({"NewApi"})
    private void setupBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        applyRadius(gradientDrawable);
        if (this.mGhost) {
            gradientDrawable.setColor(getResources().getColor(android.R.color.transparent));
        } else {
            gradientDrawable.setColor(this.mDefaultBackgroundColor);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        applyRadius(gradientDrawable2);
        gradientDrawable2.setColor(this.mFocusBackgroundColor);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        applyRadius(gradientDrawable3);
        gradientDrawable3.setColor(this.mDisabledBackgroundColor);
        gradientDrawable3.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
        if (this.mBorderColor != 0) {
            gradientDrawable.setStroke(this.mBorderWidth, this.mBorderColor);
        }
        if (!this.mEnabled) {
            gradientDrawable.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
            if (this.mGhost) {
                gradientDrawable3.setColor(getResources().getColor(android.R.color.transparent));
            }
        }
        if (this.mUseRippleEffect && Build.VERSION.SDK_INT >= 21) {
            setBackground(getRippleDrawable(gradientDrawable, gradientDrawable2, gradientDrawable3));
            return;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        applyRadius(gradientDrawable4);
        if (this.mGhost) {
            gradientDrawable4.setColor(getResources().getColor(android.R.color.transparent));
        } else {
            gradientDrawable4.setColor(this.mFocusBackgroundColor);
        }
        if (this.mBorderColor != 0) {
            if (this.mGhost) {
                gradientDrawable4.setStroke(this.mBorderWidth, this.mFocusBackgroundColor);
            } else {
                gradientDrawable4.setStroke(this.mBorderWidth, this.mBorderColor);
            }
        }
        if (!this.mEnabled) {
            if (this.mGhost) {
                gradientDrawable4.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
            } else {
                gradientDrawable4.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
            }
        }
        if (this.mFocusBackgroundColor != 0) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable4);
            stateListDrawable.addState(new int[]{android.R.attr.state_focused}, gradientDrawable4);
            stateListDrawable.addState(new int[]{-16842910}, gradientDrawable3);
        }
        stateListDrawable.addState(new int[0], gradientDrawable);
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(stateListDrawable);
        } else {
            setBackground(stateListDrawable);
        }
    }

    private TextView setupFontIconView() {
        if (this.mFontIcon == null) {
            return null;
        }
        TextView textView = new TextView(this.mContext);
        textView.setTextColor(this.mEnabled ? this.mDefaultIconColor : this.mDisabledTextColor);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = this.mIconPaddingRight;
        layoutParams.leftMargin = this.mIconPaddingLeft;
        layoutParams.topMargin = this.mIconPaddingTop;
        layoutParams.bottomMargin = this.mIconPaddingBottom;
        if (this.mTextView == null) {
            layoutParams.gravity = 17;
            textView.setGravity(16);
        } else if (this.mIconPosition == 3 || this.mIconPosition == 4) {
            layoutParams.gravity = 17;
            textView.setGravity(17);
        } else {
            textView.setGravity(16);
            layoutParams.gravity = 16;
        }
        textView.setLayoutParams(layoutParams);
        if (isInEditMode()) {
            textView.setTextSize(Utils.pxToSp(getContext(), this.mFontIconSize));
            textView.setText("O");
        } else {
            textView.setTextSize(Utils.pxToSp(getContext(), this.mFontIconSize));
            textView.setText(this.mFontIcon);
            textView.setTypeface(this.mIconTypeFace);
        }
        return textView;
    }

    private ImageView setupIconView() {
        if (this.mIconResource == null) {
            return null;
        }
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageDrawable(this.mIconResource);
        imageView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (this.mTextView != null) {
            if (this.mIconPosition == 3 || this.mIconPosition == 4) {
                layoutParams.gravity = 17;
            } else {
                layoutParams.gravity = GravityCompat.START;
            }
            layoutParams.rightMargin = 10;
            layoutParams.leftMargin = 10;
        } else {
            layoutParams.gravity = 16;
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private TextView setupTextView() {
        if (this.mText == null) {
            this.mText = "Fancy Button";
        }
        TextView textView = new TextView(this.mContext);
        textView.setText(this.mText);
        textView.setGravity(this.mDefaultTextGravity);
        textView.setTextColor(this.mEnabled ? this.mDefaultTextColor : this.mDisabledTextColor);
        textView.setTextSize(Utils.pxToSp(getContext(), this.mDefaultTextSize));
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        if (!isInEditMode() && !this.mUseSystemFont) {
            textView.setTypeface(this.mTextTypeFace, this.textStyle);
        }
        return textView;
    }

    public TextView getIconFontObject() {
        return this.mFontIconView;
    }

    public ImageView getIconImageObject() {
        return this.mIconView;
    }

    public CharSequence getText() {
        return this.mTextView != null ? this.mTextView.getText() : "";
    }

    public TextView getTextViewObject() {
        return this.mTextView;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new CustomOutline(i, i2));
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mDefaultBackgroundColor = i;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setBorderWidth(int i) {
        this.mBorderWidth = i;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setCustomIconFont(String str) {
        this.mIconTypeFace = Utils.findFont(this.mContext, str, this.mDefaultIconFont);
        if (this.mFontIconView == null) {
            initializeFancyButton();
        } else {
            this.mFontIconView.setTypeface(this.mIconTypeFace);
        }
    }

    public void setCustomTextFont(@FontRes int i) {
        this.mTextTypeFace = ResourcesCompat.getFont(getContext(), i);
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            this.mTextView.setTypeface(this.mTextTypeFace, this.textStyle);
        }
    }

    public void setCustomTextFont(String str) {
        this.mTextTypeFace = Utils.findFont(this.mContext, str, this.mDefaultTextFont);
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            this.mTextView.setTypeface(this.mTextTypeFace, this.textStyle);
        }
    }

    public void setDisableBackgroundColor(int i) {
        this.mDisabledBackgroundColor = i;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setDisableBorderColor(int i) {
        this.mDisabledBorderColor = i;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setDisableTextColor(int i) {
        this.mDisabledTextColor = i;
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            if (this.mEnabled) {
                return;
            }
            this.mTextView.setTextColor(i);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mEnabled = z;
        initializeFancyButton();
    }

    public void setFocusBackgroundColor(int i) {
        this.mFocusBackgroundColor = i;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setFontIconSize(int i) {
        float f = i;
        this.mFontIconSize = Utils.spToPx(getContext(), f);
        if (this.mFontIconView != null) {
            this.mFontIconView.setTextSize(f);
        }
    }

    public void setGhost(boolean z) {
        this.mGhost = z;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setIconColor(int i) {
        if (this.mFontIconView != null) {
            this.mFontIconView.setTextColor(i);
        }
    }

    public void setIconPadding(int i, int i2, int i3, int i4) {
        this.mIconPaddingLeft = i;
        this.mIconPaddingTop = i2;
        this.mIconPaddingRight = i3;
        this.mIconPaddingBottom = i4;
        if (this.mIconView != null) {
            this.mIconView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
        if (this.mFontIconView != null) {
            this.mFontIconView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
    }

    public void setIconPosition(int i) {
        if (i <= 0 || i >= 5) {
            this.mIconPosition = 1;
        } else {
            this.mIconPosition = i;
        }
        initializeFancyButton();
    }

    public void setIconResource(int i) {
        this.mIconResource = this.mContext.getResources().getDrawable(i);
        if (this.mIconView != null && this.mFontIconView == null) {
            this.mIconView.setImageDrawable(this.mIconResource);
        } else {
            this.mFontIconView = null;
            initializeFancyButton();
        }
    }

    public void setIconResource(Drawable drawable) {
        this.mIconResource = drawable;
        if (this.mIconView != null && this.mFontIconView == null) {
            this.mIconView.setImageDrawable(this.mIconResource);
        } else {
            this.mFontIconView = null;
            initializeFancyButton();
        }
    }

    public void setIconResource(String str) {
        this.mFontIcon = str;
        if (this.mFontIconView != null) {
            this.mFontIconView.setText(str);
        } else {
            this.mIconView = null;
            initializeFancyButton();
        }
    }

    public void setRadius(int i) {
        this.mRadius = i;
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setRadius(int[] iArr) {
        this.mRadiusTopLeft = iArr[0];
        this.mRadiusTopRight = iArr[1];
        this.mRadiusBottomLeft = iArr[2];
        this.mRadiusBottomRight = iArr[3];
        if (this.mIconView == null && this.mFontIconView == null && this.mTextView == null) {
            return;
        }
        setupBackground();
    }

    public void setText(String str) {
        if (this.mTextAllCaps) {
            str = str.toUpperCase();
        }
        this.mText = str;
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            this.mTextView.setText(str);
        }
    }

    public void setTextAllCaps(boolean z) {
        this.mTextAllCaps = z;
        setText(this.mText);
    }

    public void setTextColor(int i) {
        this.mDefaultTextColor = i;
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            this.mTextView.setTextColor(i);
        }
    }

    public void setTextGravity(int i) {
        this.mDefaultTextGravity = i;
        if (this.mTextView != null) {
            setGravity(i);
        }
    }

    public void setTextSize(int i) {
        float f = i;
        this.mDefaultTextSize = Utils.spToPx(getContext(), f);
        if (this.mTextView != null) {
            this.mTextView.setTextSize(f);
        }
    }

    public void setUsingSystemFont(boolean z) {
        this.mUseSystemFont = z;
    }
}
