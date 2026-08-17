package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class SpannableStringUtils {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Align {
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Layout.Alignment alignment;
        private Bitmap bitmap;
        private float blurRadius;
        private int bulletColor;
        private int bulletGapWidth;
        private int bulletRadius;
        private ClickableSpan clickSpan;
        private Drawable drawable;
        private int first;
        private String fontFamily;
        private boolean fontSizeIsDp;
        private boolean imageIsBitmap;
        private boolean imageIsDrawable;
        private boolean imageIsResourceId;
        private boolean imageIsUri;
        private boolean isBlur;
        private boolean isBold;
        private boolean isBoldItalic;
        private boolean isBullet;
        private boolean isItalic;
        private boolean isLeadingMargin;
        private boolean isStrikethrough;
        private boolean isSubscript;
        private boolean isSuperscript;
        private boolean isUnderline;
        private int quoteGapWidth;

        @DrawableRes
        private int resourceId;
        private int rest;
        private int stripeWidth;
        private BlurMaskFilter.Blur style;
        private CharSequence text;
        private Typeface typeface;
        private Uri uri;
        private String url;
        private int defaultValue = 301989888;
        private int flag = 33;

        @ColorInt
        private int foregroundColor = this.defaultValue;

        @ColorInt
        private int backgroundColor = this.defaultValue;

        @ColorInt
        private int quoteColor = this.defaultValue;
        private int margin = -1;
        private int fontSize = -1;
        private float proportion = -1.0f;
        private float xProportion = -1.0f;
        int align = 0;
        private SpannableStringBuilder mBuilder = new SpannableStringBuilder();

        private void setSpan() {
            if (this.text == null || this.text.length() == 0) {
                return;
            }
            int length = this.mBuilder.length();
            this.mBuilder.append(this.text);
            int length2 = this.mBuilder.length();
            if (this.backgroundColor != this.defaultValue) {
                this.mBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), length, length2, this.flag);
                this.backgroundColor = this.defaultValue;
            }
            if (this.foregroundColor != this.defaultValue) {
                this.mBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), length, length2, this.flag);
                this.foregroundColor = this.defaultValue;
            }
            if (this.isLeadingMargin) {
                this.mBuilder.setSpan(new LeadingMarginSpan.Standard(this.first, this.rest), length, length2, this.flag);
                this.isLeadingMargin = false;
            }
            if (this.margin != -1) {
                this.mBuilder.setSpan(new MarginSpan(this.margin), length, length2, this.flag);
                this.margin = -1;
            }
            if (this.quoteColor != this.defaultValue) {
                this.mBuilder.setSpan(new CustomQuoteSpan(this.quoteColor, this.stripeWidth, this.quoteGapWidth), length, length2, this.flag);
                this.quoteColor = this.defaultValue;
            }
            if (this.isBullet) {
                this.mBuilder.setSpan(new CustomBulletSpan(this.bulletColor, this.bulletRadius, this.bulletGapWidth), length, length2, this.flag);
                this.isBullet = false;
            }
            if (this.fontSize != -1) {
                this.mBuilder.setSpan(new AbsoluteSizeSpan(this.fontSize, this.fontSizeIsDp), length, length2, this.flag);
                this.fontSize = -1;
                this.fontSizeIsDp = false;
            }
            if (this.proportion != -1.0f) {
                this.mBuilder.setSpan(new RelativeSizeSpan(this.proportion), length, length2, this.flag);
                this.proportion = -1.0f;
            }
            if (this.xProportion != -1.0f) {
                this.mBuilder.setSpan(new ScaleXSpan(this.xProportion), length, length2, this.flag);
                this.xProportion = -1.0f;
            }
            if (this.isStrikethrough) {
                this.mBuilder.setSpan(new StrikethroughSpan(), length, length2, this.flag);
                this.isStrikethrough = false;
            }
            if (this.isUnderline) {
                this.mBuilder.setSpan(new UnderlineSpan(), length, length2, this.flag);
                this.isUnderline = false;
            }
            if (this.isSuperscript) {
                this.mBuilder.setSpan(new SuperscriptSpan(), length, length2, this.flag);
                this.isSuperscript = false;
            }
            if (this.isSubscript) {
                this.mBuilder.setSpan(new SubscriptSpan(), length, length2, this.flag);
                this.isSubscript = false;
            }
            if (this.isBold) {
                this.mBuilder.setSpan(new StyleSpan(1), length, length2, this.flag);
                this.isBold = false;
            }
            if (this.isItalic) {
                this.mBuilder.setSpan(new StyleSpan(2), length, length2, this.flag);
                this.isItalic = false;
            }
            if (this.isBoldItalic) {
                this.mBuilder.setSpan(new StyleSpan(3), length, length2, this.flag);
                this.isBoldItalic = false;
            }
            if (this.fontFamily != null) {
                this.mBuilder.setSpan(new TypefaceSpan(this.fontFamily), length, length2, this.flag);
                this.fontFamily = null;
            }
            if (this.typeface != null) {
                this.mBuilder.setSpan(new CustomTypefaceSpan(this.typeface), length, length2, this.flag);
                this.typeface = null;
            }
            if (this.alignment != null) {
                this.mBuilder.setSpan(new AlignmentSpan.Standard(this.alignment), length, length2, this.flag);
                this.alignment = null;
            }
            if (this.imageIsBitmap || this.imageIsDrawable || this.imageIsUri || this.imageIsResourceId) {
                if (this.imageIsBitmap) {
                    this.mBuilder.setSpan(new CustomImageSpan(Utils.getContext(), this.bitmap, this.align), length, length2, this.flag);
                    this.bitmap = null;
                    this.imageIsBitmap = false;
                } else if (this.imageIsDrawable) {
                    this.mBuilder.setSpan(new CustomImageSpan(this.drawable, this.align), length, length2, this.flag);
                    this.drawable = null;
                    this.imageIsDrawable = false;
                } else if (this.imageIsUri) {
                    this.mBuilder.setSpan(new CustomImageSpan(Utils.getContext(), this.uri, this.align), length, length2, this.flag);
                    this.uri = null;
                    this.imageIsUri = false;
                } else {
                    this.mBuilder.setSpan(new CustomImageSpan(Utils.getContext(), this.resourceId, this.align), length, length2, this.flag);
                    this.resourceId = 0;
                    this.imageIsResourceId = false;
                }
            }
            if (this.clickSpan != null) {
                this.mBuilder.setSpan(this.clickSpan, length, length2, this.flag);
                this.clickSpan = null;
            }
            if (this.url != null) {
                this.mBuilder.setSpan(new URLSpan(this.url), length, length2, this.flag);
                this.url = null;
            }
            if (this.isBlur) {
                this.mBuilder.setSpan(new MaskFilterSpan(new BlurMaskFilter(this.blurRadius, this.style)), length, length2, this.flag);
                this.isBlur = false;
            }
            this.flag = 33;
        }

        public Builder append(@NonNull CharSequence charSequence) {
            setSpan();
            this.text = charSequence;
            return this;
        }

        public Builder appendLine(@NonNull CharSequence charSequence) {
            return append(((Object) charSequence) + SpannableStringUtils.LINE_SEPARATOR);
        }

        public SpannableStringBuilder create() {
            setSpan();
            return this.mBuilder;
        }

        public Builder setAlign(@NonNull Layout.Alignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int i) {
            this.backgroundColor = i;
            return this;
        }

        public Builder setBitmap(@NonNull Bitmap bitmap) {
            return setBitmap(bitmap, this.align);
        }

        public Builder setBitmap(@NonNull Bitmap bitmap, int i) {
            this.bitmap = bitmap;
            this.align = i;
            this.text = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((Object) this.text);
            this.imageIsBitmap = true;
            return this;
        }

        public Builder setBlur(float f, BlurMaskFilter.Blur blur) {
            this.blurRadius = f;
            this.style = blur;
            this.isBlur = true;
            return this;
        }

        public Builder setBold() {
            this.isBold = true;
            return this;
        }

        public Builder setBoldItalic() {
            this.isBoldItalic = true;
            return this;
        }

        public Builder setBullet(@ColorInt int i) {
            this.bulletColor = 0;
            this.bulletRadius = 3;
            this.bulletGapWidth = i;
            this.isBullet = true;
            return this;
        }

        public Builder setBullet(@ColorInt int i, int i2, int i3) {
            this.bulletColor = i;
            this.bulletRadius = i2;
            this.bulletGapWidth = i3;
            this.isBullet = true;
            return this;
        }

        public Builder setClickSpan(@NonNull ClickableSpan clickableSpan) {
            this.clickSpan = clickableSpan;
            return this;
        }

        public Builder setDrawable(@NonNull Drawable drawable) {
            return setDrawable(drawable, this.align);
        }

        public Builder setDrawable(@NonNull Drawable drawable, int i) {
            this.drawable = drawable;
            this.align = i;
            this.text = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((Object) this.text);
            this.imageIsDrawable = true;
            return this;
        }

        public Builder setFlag(int i) {
            this.flag = i;
            return this;
        }

        public Builder setFontFamily(@NonNull String str) {
            this.fontFamily = str;
            return this;
        }

        public Builder setFontProportion(float f) {
            this.proportion = f;
            return this;
        }

        public Builder setFontSize(int i) {
            this.fontSize = i;
            this.fontSizeIsDp = false;
            return this;
        }

        public Builder setFontSize(int i, boolean z) {
            this.fontSize = i;
            this.fontSizeIsDp = z;
            return this;
        }

        public Builder setFontXProportion(float f) {
            this.xProportion = f;
            return this;
        }

        public Builder setForegroundColor(@ColorInt int i) {
            this.foregroundColor = i;
            return this;
        }

        public Builder setItalic() {
            this.isItalic = true;
            return this;
        }

        public Builder setLeadingMargin(int i, int i2) {
            this.first = i;
            this.rest = i2;
            this.isLeadingMargin = true;
            return this;
        }

        public Builder setMargin(int i) {
            this.margin = i;
            this.text = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((Object) this.text);
            return this;
        }

        public Builder setQuoteColor(@ColorInt int i) {
            this.quoteColor = i;
            this.stripeWidth = 2;
            this.quoteGapWidth = 2;
            return this;
        }

        public Builder setQuoteColor(@ColorInt int i, int i2, int i3) {
            this.quoteColor = i;
            this.stripeWidth = i2;
            this.quoteGapWidth = i3;
            return this;
        }

        public Builder setResourceId(@DrawableRes int i) {
            return setResourceId(i, this.align);
        }

        public Builder setResourceId(@DrawableRes int i, int i2) {
            this.resourceId = i;
            this.align = i2;
            this.text = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((Object) this.text);
            this.imageIsResourceId = true;
            return this;
        }

        public Builder setStrikethrough() {
            this.isStrikethrough = true;
            return this;
        }

        public Builder setSubscript() {
            this.isSubscript = true;
            return this;
        }

        public Builder setSuperscript() {
            this.isSuperscript = true;
            return this;
        }

        public Builder setTypeface(@NonNull Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public Builder setUnderline() {
            this.isUnderline = true;
            return this;
        }

        public Builder setUri(@NonNull Uri uri) {
            setUri(uri, 0);
            return this;
        }

        public Builder setUri(@NonNull Uri uri, int i) {
            this.uri = uri;
            this.align = i;
            this.text = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ((Object) this.text);
            this.imageIsUri = true;
            return this;
        }

        public Builder setUrl(@NonNull String str) {
            this.url = str;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CustomBulletSpan implements LeadingMarginSpan {
        private static Path sBulletPath;
        private final int color;
        private final int gapWidth;
        private final int radius;

        private CustomBulletSpan(int i, int i2, int i3) {
            this.color = i;
            this.radius = i2;
            this.gapWidth = i3;
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            if (((Spanned) charSequence).getSpanStart(this) == i6) {
                Paint.Style style = paint.getStyle();
                int color = paint.getColor();
                paint.setColor(this.color);
                paint.setStyle(Paint.Style.FILL);
                if (canvas.isHardwareAccelerated()) {
                    if (sBulletPath == null) {
                        sBulletPath = new Path();
                        sBulletPath.addCircle(0.0f, 0.0f, this.radius, Path.Direction.CW);
                    }
                    canvas.save();
                    canvas.translate(i + (i2 * this.radius), (i3 + i5) / 2.0f);
                    canvas.drawPath(sBulletPath, paint);
                    canvas.restore();
                } else {
                    canvas.drawCircle(i + (i2 * this.radius), (i3 + i5) / 2.0f, this.radius, paint);
                }
                paint.setColor(color);
                paint.setStyle(style);
            }
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(boolean z) {
            return (2 * this.radius) + this.gapWidth;
        }
    }

    /* loaded from: classes.dex */
    static abstract class CustomDynamicDrawableSpan extends ReplacementSpan {
        static final int ALIGN_BASELINE = 1;
        static final int ALIGN_BOTTOM = 0;
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        private WeakReference<Drawable> mDrawableRef;
        final int mVerticalAlignment;

        CustomDynamicDrawableSpan() {
            this.mVerticalAlignment = 0;
        }

        CustomDynamicDrawableSpan(int i) {
            this.mVerticalAlignment = i;
        }

        private Drawable getCachedDrawable() {
            WeakReference<Drawable> weakReference = this.mDrawableRef;
            if ((weakReference != null ? weakReference.get() : null) == null) {
                this.mDrawableRef = new WeakReference<>(getDrawable());
            }
            return getDrawable();
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            Drawable cachedDrawable = getCachedDrawable();
            Rect bounds = cachedDrawable.getBounds();
            canvas.save();
            float f2 = paint.getFontMetrics().descent - paint.getFontMetrics().ascent;
            int i6 = i5 - bounds.bottom;
            if (bounds.height() < f2) {
                if (this.mVerticalAlignment == 1) {
                    i6 -= paint.getFontMetricsInt().descent;
                } else if (this.mVerticalAlignment == 2) {
                    i6 = (int) (i6 - ((f2 - bounds.height()) / 2.0f));
                } else if (this.mVerticalAlignment == 3) {
                    i6 = (int) (i6 - (f2 - bounds.height()));
                }
            } else if (this.mVerticalAlignment == 1) {
                i6 -= paint.getFontMetricsInt().descent;
            }
            canvas.translate(f, i6);
            cachedDrawable.draw(canvas);
            canvas.restore();
        }

        public abstract Drawable getDrawable();

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            Rect bounds = getCachedDrawable().getBounds();
            int i3 = (int) (paint.getFontMetrics().descent - paint.getFontMetrics().ascent);
            if (fontMetricsInt != null && bounds.height() > i3) {
                if (this.mVerticalAlignment == 3) {
                    fontMetricsInt.descent += bounds.height() - i3;
                } else if (this.mVerticalAlignment == 2) {
                    fontMetricsInt.ascent -= (bounds.height() - i3) / 2;
                    fontMetricsInt.descent += (bounds.height() - i3) / 2;
                } else if (this.mVerticalAlignment == 1) {
                    fontMetricsInt.ascent -= (bounds.height() - i3) + fontMetricsInt.descent;
                } else {
                    fontMetricsInt.ascent -= bounds.height() - i3;
                }
            }
            return bounds.right;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CustomImageSpan extends CustomDynamicDrawableSpan {
        private Uri mContentUri;
        private Context mContext;
        private Drawable mDrawable;
        private int mResourceId;

        CustomImageSpan(Context context, @DrawableRes int i, int i2) {
            super(i2);
            this.mContext = context;
            this.mResourceId = i;
        }

        CustomImageSpan(Context context, Bitmap bitmap, int i) {
            super(i);
            this.mContext = context;
            this.mDrawable = context != null ? new BitmapDrawable(context.getResources(), bitmap) : new BitmapDrawable(bitmap);
            int intrinsicWidth = this.mDrawable.getIntrinsicWidth();
            int intrinsicHeight = this.mDrawable.getIntrinsicHeight();
            this.mDrawable.setBounds(0, 0, intrinsicWidth <= 0 ? 0 : intrinsicWidth, intrinsicHeight <= 0 ? 0 : intrinsicHeight);
        }

        CustomImageSpan(Context context, Uri uri, int i) {
            super(i);
            this.mContext = context;
            this.mContentUri = uri;
        }

        CustomImageSpan(Drawable drawable, int i) {
            super(i);
            this.mDrawable = drawable;
            this.mDrawable.setBounds(0, 0, this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        @Override // com.blankj.utilcode.util.SpannableStringUtils.CustomDynamicDrawableSpan
        public Drawable getDrawable() {
            Drawable drawable;
            InputStream openInputStream;
            BitmapDrawable bitmapDrawable;
            if (this.mDrawable != null) {
                return this.mDrawable;
            }
            BitmapDrawable bitmapDrawable2 = null;
            if (this.mContentUri != null) {
                try {
                    openInputStream = this.mContext.getContentResolver().openInputStream(this.mContentUri);
                    bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), BitmapFactory.decodeStream(openInputStream));
                } catch (Exception e) {
                    e = e;
                }
                try {
                    bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    return bitmapDrawable;
                } catch (Exception e2) {
                    e = e2;
                    bitmapDrawable2 = bitmapDrawable;
                    Log.e("sms", "Failed to loaded content " + this.mContentUri, e);
                    return bitmapDrawable2;
                }
            }
            try {
                drawable = ContextCompat.getDrawable(this.mContext, this.mResourceId);
                try {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    return drawable;
                } catch (Exception unused) {
                    Log.e("sms", "Unable to find resource: " + this.mResourceId);
                    return drawable;
                }
            } catch (Exception unused2) {
                drawable = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CustomQuoteSpan implements LeadingMarginSpan {
        private final int color;
        private final int gapWidth;
        private final int stripeWidth;

        private CustomQuoteSpan(@ColorInt int i, int i2, int i3) {
            this.color = i;
            this.stripeWidth = i2;
            this.gapWidth = i3;
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.color);
            canvas.drawRect(i, i3, i + (this.stripeWidth * i2), i5, paint);
            paint.setStyle(style);
            paint.setColor(color);
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(boolean z) {
            return this.stripeWidth + this.gapWidth;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"ParcelCreator"})
    /* loaded from: classes.dex */
    public static class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface newType;

        private CustomTypefaceSpan(Typeface typeface) {
            super("");
            this.newType = typeface;
        }

        private static void apply(Paint paint, Typeface typeface) {
            Typeface typeface2 = paint.getTypeface();
            int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (typeface.getStyle() ^ (-1));
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.setTypeface(typeface);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            apply(textPaint, this.newType);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint textPaint) {
            apply(textPaint, this.newType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MarginSpan extends ReplacementSpan {
        private final int margin;

        private MarginSpan(int i) {
            this.margin = i;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            return this.margin;
        }
    }

    private SpannableStringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
}
