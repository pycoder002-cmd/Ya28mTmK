package com.afollestad.materialdialogs.simplelist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import com.afollestad.materialdialogs.util.DialogUtils;

/* loaded from: classes.dex */
public class MaterialSimpleListItem {
    private final Builder builder;

    /* loaded from: classes.dex */
    public static class Builder {
        int backgroundColor = Color.parseColor("#BCBCBC");
        protected CharSequence content;
        private final Context context;
        protected Drawable icon;
        int iconPadding;
        protected long id;
        Object tag;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder backgroundColor(@ColorInt int i) {
            this.backgroundColor = i;
            return this;
        }

        public Builder backgroundColorAttr(@AttrRes int i) {
            return backgroundColor(DialogUtils.resolveColor(this.context, i));
        }

        public Builder backgroundColorRes(@ColorRes int i) {
            return backgroundColor(DialogUtils.getColor(this.context, i));
        }

        public MaterialSimpleListItem build() {
            return new MaterialSimpleListItem(this);
        }

        public Builder content(@StringRes int i) {
            return content(this.context.getString(i));
        }

        public Builder content(CharSequence charSequence) {
            this.content = charSequence;
            return this;
        }

        public Builder icon(@DrawableRes int i) {
            return icon(ContextCompat.getDrawable(this.context, i));
        }

        public Builder icon(Drawable drawable) {
            this.icon = drawable;
            return this;
        }

        public Builder iconPadding(@IntRange(from = 0, to = 2147483647L) int i) {
            this.iconPadding = i;
            return this;
        }

        public Builder iconPaddingDp(@IntRange(from = 0, to = 2147483647L) int i) {
            this.iconPadding = (int) TypedValue.applyDimension(1, i, this.context.getResources().getDisplayMetrics());
            return this;
        }

        public Builder iconPaddingRes(@DimenRes int i) {
            return iconPadding(this.context.getResources().getDimensionPixelSize(i));
        }

        public Builder id(long j) {
            this.id = j;
            return this;
        }

        public Builder tag(@Nullable Object obj) {
            this.tag = obj;
            return this;
        }
    }

    private MaterialSimpleListItem(Builder builder) {
        this.builder = builder;
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.builder.backgroundColor;
    }

    public CharSequence getContent() {
        return this.builder.content;
    }

    public Drawable getIcon() {
        return this.builder.icon;
    }

    public int getIconPadding() {
        return this.builder.iconPadding;
    }

    public long getId() {
        return this.builder.id;
    }

    @Nullable
    public Object getTag() {
        return this.builder.tag;
    }

    public String toString() {
        return getContent() != null ? getContent().toString() : "(no content)";
    }
}
