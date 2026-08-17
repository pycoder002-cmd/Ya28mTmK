package com.afollestad.materialdialogs;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDAdapter;
import com.afollestad.materialdialogs.internal.MDButton;
import com.afollestad.materialdialogs.internal.MDRootLayout;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.util.DialogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import me.zhanghai.android.materialprogressbar.HorizontalProgressDrawable;
import me.zhanghai.android.materialprogressbar.IndeterminateHorizontalProgressDrawable;
import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DialogInit {
    DialogInit() {
    }

    private static void fixCanvasScalingWhenHardwareAccelerated(ProgressBar progressBar) {
        if (Build.VERSION.SDK_INT >= 18 || !progressBar.isHardwareAccelerated() || progressBar.getLayerType() == 1) {
            return;
        }
        progressBar.setLayerType(1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @LayoutRes
    public static int getInflateLayout(MaterialDialog.Builder builder) {
        return builder.customView != null ? R.layout.md_dialog_custom : (builder.items == null && builder.adapter == null) ? builder.progress > -2 ? R.layout.md_dialog_progress : builder.indeterminateProgress ? builder.indeterminateIsHorizontalProgress ? R.layout.md_dialog_progress_indeterminate_horizontal : R.layout.md_dialog_progress_indeterminate : builder.inputCallback != null ? builder.checkBoxPrompt != null ? R.layout.md_dialog_input_check : R.layout.md_dialog_input : builder.checkBoxPrompt != null ? R.layout.md_dialog_basic_check : R.layout.md_dialog_basic : builder.checkBoxPrompt != null ? R.layout.md_dialog_list_check : R.layout.md_dialog_list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @StyleRes
    public static int getTheme(@NonNull MaterialDialog.Builder builder) {
        boolean resolveBoolean = DialogUtils.resolveBoolean(builder.context, R.attr.md_dark_theme, builder.theme == Theme.DARK);
        builder.theme = resolveBoolean ? Theme.DARK : Theme.LIGHT;
        return resolveBoolean ? R.style.MD_Dark : R.style.MD_Light;
    }

    @UiThread
    public static void init(MaterialDialog materialDialog) {
        boolean resolveBoolean;
        MaterialDialog.Builder builder = materialDialog.builder;
        materialDialog.setCancelable(builder.cancelable);
        materialDialog.setCanceledOnTouchOutside(builder.canceledOnTouchOutside);
        if (builder.backgroundColor == 0) {
            builder.backgroundColor = DialogUtils.resolveColor(builder.context, R.attr.md_background_color, DialogUtils.resolveColor(materialDialog.getContext(), R.attr.colorBackgroundFloating));
        }
        if (builder.backgroundColor != 0) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(builder.context.getResources().getDimension(R.dimen.md_bg_corner_radius));
            gradientDrawable.setColor(builder.backgroundColor);
            materialDialog.getWindow().setBackgroundDrawable(gradientDrawable);
        }
        if (!builder.positiveColorSet) {
            builder.positiveColor = DialogUtils.resolveActionTextColorStateList(builder.context, R.attr.md_positive_color, builder.positiveColor);
        }
        if (!builder.neutralColorSet) {
            builder.neutralColor = DialogUtils.resolveActionTextColorStateList(builder.context, R.attr.md_neutral_color, builder.neutralColor);
        }
        if (!builder.negativeColorSet) {
            builder.negativeColor = DialogUtils.resolveActionTextColorStateList(builder.context, R.attr.md_negative_color, builder.negativeColor);
        }
        if (!builder.widgetColorSet) {
            builder.widgetColor = DialogUtils.resolveColor(builder.context, R.attr.md_widget_color, builder.widgetColor);
        }
        if (!builder.titleColorSet) {
            builder.titleColor = DialogUtils.resolveColor(builder.context, R.attr.md_title_color, DialogUtils.resolveColor(materialDialog.getContext(), android.R.attr.textColorPrimary));
        }
        if (!builder.contentColorSet) {
            builder.contentColor = DialogUtils.resolveColor(builder.context, R.attr.md_content_color, DialogUtils.resolveColor(materialDialog.getContext(), android.R.attr.textColorSecondary));
        }
        if (!builder.itemColorSet) {
            builder.itemColor = DialogUtils.resolveColor(builder.context, R.attr.md_item_color, builder.contentColor);
        }
        materialDialog.title = (TextView) materialDialog.view.findViewById(R.id.md_title);
        materialDialog.icon = (ImageView) materialDialog.view.findViewById(R.id.md_icon);
        materialDialog.titleFrame = materialDialog.view.findViewById(R.id.md_titleFrame);
        materialDialog.content = (TextView) materialDialog.view.findViewById(R.id.md_content);
        materialDialog.recyclerView = (RecyclerView) materialDialog.view.findViewById(R.id.md_contentRecyclerView);
        materialDialog.checkBoxPrompt = (CheckBox) materialDialog.view.findViewById(R.id.md_promptCheckbox);
        materialDialog.positiveButton = (MDButton) materialDialog.view.findViewById(R.id.md_buttonDefaultPositive);
        materialDialog.neutralButton = (MDButton) materialDialog.view.findViewById(R.id.md_buttonDefaultNeutral);
        materialDialog.negativeButton = (MDButton) materialDialog.view.findViewById(R.id.md_buttonDefaultNegative);
        if (builder.inputCallback != null && builder.positiveText == null) {
            builder.positiveText = builder.context.getText(android.R.string.ok);
        }
        materialDialog.positiveButton.setVisibility(builder.positiveText != null ? 0 : 8);
        materialDialog.neutralButton.setVisibility(builder.neutralText != null ? 0 : 8);
        materialDialog.negativeButton.setVisibility(builder.negativeText != null ? 0 : 8);
        materialDialog.positiveButton.setFocusable(true);
        materialDialog.neutralButton.setFocusable(true);
        materialDialog.negativeButton.setFocusable(true);
        if (builder.positiveFocus) {
            materialDialog.positiveButton.requestFocus();
        }
        if (builder.neutralFocus) {
            materialDialog.neutralButton.requestFocus();
        }
        if (builder.negativeFocus) {
            materialDialog.negativeButton.requestFocus();
        }
        if (builder.icon != null) {
            materialDialog.icon.setVisibility(0);
            materialDialog.icon.setImageDrawable(builder.icon);
        } else {
            Drawable resolveDrawable = DialogUtils.resolveDrawable(builder.context, R.attr.md_icon);
            if (resolveDrawable != null) {
                materialDialog.icon.setVisibility(0);
                materialDialog.icon.setImageDrawable(resolveDrawable);
            } else {
                materialDialog.icon.setVisibility(8);
            }
        }
        int i = builder.maxIconSize;
        if (i == -1) {
            i = DialogUtils.resolveDimension(builder.context, R.attr.md_icon_max_size);
        }
        if (builder.limitIconToDefaultSize || DialogUtils.resolveBoolean(builder.context, R.attr.md_icon_limit_icon_to_default_size)) {
            i = builder.context.getResources().getDimensionPixelSize(R.dimen.md_icon_max_size);
        }
        if (i > -1) {
            materialDialog.icon.setAdjustViewBounds(true);
            materialDialog.icon.setMaxHeight(i);
            materialDialog.icon.setMaxWidth(i);
            materialDialog.icon.requestLayout();
        }
        if (!builder.dividerColorSet) {
            builder.dividerColor = DialogUtils.resolveColor(builder.context, R.attr.md_divider_color, DialogUtils.resolveColor(materialDialog.getContext(), R.attr.md_divider));
        }
        materialDialog.view.setDividerColor(builder.dividerColor);
        if (materialDialog.title != null) {
            materialDialog.setTypeface(materialDialog.title, builder.mediumFont);
            materialDialog.title.setTextColor(builder.titleColor);
            materialDialog.title.setGravity(builder.titleGravity.getGravityInt());
            if (Build.VERSION.SDK_INT >= 17) {
                materialDialog.title.setTextAlignment(builder.titleGravity.getTextAlignment());
            }
            if (builder.title == null) {
                materialDialog.titleFrame.setVisibility(8);
            } else {
                materialDialog.title.setText(builder.title);
                materialDialog.titleFrame.setVisibility(0);
            }
        }
        if (materialDialog.content != null) {
            materialDialog.content.setMovementMethod(new LinkMovementMethod());
            materialDialog.setTypeface(materialDialog.content, builder.regularFont);
            materialDialog.content.setLineSpacing(0.0f, builder.contentLineSpacingMultiplier);
            if (builder.linkColor == null) {
                materialDialog.content.setLinkTextColor(DialogUtils.resolveColor(materialDialog.getContext(), android.R.attr.textColorPrimary));
            } else {
                materialDialog.content.setLinkTextColor(builder.linkColor);
            }
            materialDialog.content.setTextColor(builder.contentColor);
            materialDialog.content.setGravity(builder.contentGravity.getGravityInt());
            if (Build.VERSION.SDK_INT >= 17) {
                materialDialog.content.setTextAlignment(builder.contentGravity.getTextAlignment());
            }
            if (builder.content != null) {
                materialDialog.content.setText(builder.content);
                materialDialog.content.setVisibility(0);
            } else {
                materialDialog.content.setVisibility(8);
            }
        }
        if (materialDialog.checkBoxPrompt != null) {
            materialDialog.checkBoxPrompt.setText(builder.checkBoxPrompt);
            materialDialog.checkBoxPrompt.setChecked(builder.checkBoxPromptInitiallyChecked);
            materialDialog.checkBoxPrompt.setOnCheckedChangeListener(builder.checkBoxPromptListener);
            materialDialog.setTypeface(materialDialog.checkBoxPrompt, builder.regularFont);
            materialDialog.checkBoxPrompt.setTextColor(builder.contentColor);
            MDTintHelper.setTint(materialDialog.checkBoxPrompt, builder.widgetColor);
        }
        materialDialog.view.setButtonGravity(builder.buttonsGravity);
        materialDialog.view.setButtonStackedGravity(builder.btnStackedGravity);
        materialDialog.view.setStackingBehavior(builder.stackingBehavior);
        if (Build.VERSION.SDK_INT >= 14) {
            resolveBoolean = DialogUtils.resolveBoolean(builder.context, android.R.attr.textAllCaps, true);
            if (resolveBoolean) {
                resolveBoolean = DialogUtils.resolveBoolean(builder.context, R.attr.textAllCaps, true);
            }
        } else {
            resolveBoolean = DialogUtils.resolveBoolean(builder.context, R.attr.textAllCaps, true);
        }
        MDButton mDButton = materialDialog.positiveButton;
        materialDialog.setTypeface(mDButton, builder.mediumFont);
        mDButton.setAllCapsCompat(resolveBoolean);
        mDButton.setText(builder.positiveText);
        mDButton.setTextColor(builder.positiveColor);
        materialDialog.positiveButton.setStackedSelector(materialDialog.getButtonSelector(DialogAction.POSITIVE, true));
        materialDialog.positiveButton.setDefaultSelector(materialDialog.getButtonSelector(DialogAction.POSITIVE, false));
        materialDialog.positiveButton.setTag(DialogAction.POSITIVE);
        materialDialog.positiveButton.setOnClickListener(materialDialog);
        materialDialog.positiveButton.setVisibility(0);
        MDButton mDButton2 = materialDialog.negativeButton;
        materialDialog.setTypeface(mDButton2, builder.mediumFont);
        mDButton2.setAllCapsCompat(resolveBoolean);
        mDButton2.setText(builder.negativeText);
        mDButton2.setTextColor(builder.negativeColor);
        materialDialog.negativeButton.setStackedSelector(materialDialog.getButtonSelector(DialogAction.NEGATIVE, true));
        materialDialog.negativeButton.setDefaultSelector(materialDialog.getButtonSelector(DialogAction.NEGATIVE, false));
        materialDialog.negativeButton.setTag(DialogAction.NEGATIVE);
        materialDialog.negativeButton.setOnClickListener(materialDialog);
        materialDialog.negativeButton.setVisibility(0);
        MDButton mDButton3 = materialDialog.neutralButton;
        materialDialog.setTypeface(mDButton3, builder.mediumFont);
        mDButton3.setAllCapsCompat(resolveBoolean);
        mDButton3.setText(builder.neutralText);
        mDButton3.setTextColor(builder.neutralColor);
        materialDialog.neutralButton.setStackedSelector(materialDialog.getButtonSelector(DialogAction.NEUTRAL, true));
        materialDialog.neutralButton.setDefaultSelector(materialDialog.getButtonSelector(DialogAction.NEUTRAL, false));
        materialDialog.neutralButton.setTag(DialogAction.NEUTRAL);
        materialDialog.neutralButton.setOnClickListener(materialDialog);
        materialDialog.neutralButton.setVisibility(0);
        if (builder.listCallbackMultiChoice != null) {
            materialDialog.selectedIndicesList = new ArrayList();
        }
        if (materialDialog.recyclerView != null) {
            if (builder.adapter == null) {
                if (builder.listCallbackSingleChoice != null) {
                    materialDialog.listType = MaterialDialog.ListType.SINGLE;
                } else if (builder.listCallbackMultiChoice != null) {
                    materialDialog.listType = MaterialDialog.ListType.MULTI;
                    if (builder.selectedIndices != null) {
                        materialDialog.selectedIndicesList = new ArrayList(Arrays.asList(builder.selectedIndices));
                        builder.selectedIndices = null;
                    }
                } else {
                    materialDialog.listType = MaterialDialog.ListType.REGULAR;
                }
                builder.adapter = new DefaultRvAdapter(materialDialog, MaterialDialog.ListType.getLayoutForType(materialDialog.listType));
            } else if (builder.adapter instanceof MDAdapter) {
                ((MDAdapter) builder.adapter).setDialog(materialDialog);
            }
        }
        setupProgressDialog(materialDialog);
        setupInputDialog(materialDialog);
        if (builder.customView != null) {
            ((MDRootLayout) materialDialog.view.findViewById(R.id.md_root)).noTitleNoPadding();
            FrameLayout frameLayout = (FrameLayout) materialDialog.view.findViewById(R.id.md_customViewFrame);
            materialDialog.customViewFrame = frameLayout;
            View view = builder.customView;
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            if (builder.wrapCustomViewInScroll) {
                Resources resources = materialDialog.getContext().getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.md_dialog_frame_margin);
                ScrollView scrollView = new ScrollView(materialDialog.getContext());
                int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.md_content_padding_top);
                int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.md_content_padding_bottom);
                scrollView.setClipToPadding(false);
                if (view instanceof EditText) {
                    scrollView.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize3);
                } else {
                    scrollView.setPadding(0, dimensionPixelSize2, 0, dimensionPixelSize3);
                    view.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
                }
                scrollView.addView(view, new FrameLayout.LayoutParams(-1, -2));
                view = scrollView;
            }
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -2));
        }
        if (builder.showListener != null) {
            materialDialog.setOnShowListener(builder.showListener);
        }
        if (builder.cancelListener != null) {
            materialDialog.setOnCancelListener(builder.cancelListener);
        }
        if (builder.dismissListener != null) {
            materialDialog.setOnDismissListener(builder.dismissListener);
        }
        if (builder.keyListener != null) {
            materialDialog.setOnKeyListener(builder.keyListener);
        }
        materialDialog.setOnShowListenerInternal();
        materialDialog.invalidateList();
        materialDialog.setViewInternal(materialDialog.view);
        materialDialog.checkIfListInitScroll();
        Display defaultDisplay = materialDialog.getWindow().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i2 = point.x;
        int i3 = point.y;
        int dimensionPixelSize4 = builder.context.getResources().getDimensionPixelSize(R.dimen.md_dialog_vertical_margin);
        int dimensionPixelSize5 = builder.context.getResources().getDimensionPixelSize(R.dimen.md_dialog_horizontal_margin);
        materialDialog.view.setMaxHeight(i3 - (dimensionPixelSize4 * 2));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(materialDialog.getWindow().getAttributes());
        layoutParams.width = Math.min(builder.context.getResources().getDimensionPixelSize(R.dimen.md_dialog_max_width), i2 - (dimensionPixelSize5 * 2));
        materialDialog.getWindow().setAttributes(layoutParams);
    }

    private static void setupInputDialog(MaterialDialog materialDialog) {
        MaterialDialog.Builder builder = materialDialog.builder;
        materialDialog.input = (EditText) materialDialog.view.findViewById(android.R.id.input);
        if (materialDialog.input == null) {
            return;
        }
        materialDialog.setTypeface(materialDialog.input, builder.regularFont);
        if (builder.inputPrefill != null) {
            materialDialog.input.setText(builder.inputPrefill);
        }
        materialDialog.setInternalInputCallback();
        materialDialog.input.setHint(builder.inputHint);
        materialDialog.input.setSingleLine();
        materialDialog.input.setTextColor(builder.contentColor);
        materialDialog.input.setHintTextColor(DialogUtils.adjustAlpha(builder.contentColor, 0.3f));
        MDTintHelper.setTint(materialDialog.input, materialDialog.builder.widgetColor);
        if (builder.inputType != -1) {
            materialDialog.input.setInputType(builder.inputType);
            if (builder.inputType != 144 && (builder.inputType & 128) == 128) {
                materialDialog.input.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
        materialDialog.inputMinMax = (TextView) materialDialog.view.findViewById(R.id.md_minMax);
        if (builder.inputMinLength > 0 || builder.inputMaxLength > -1) {
            materialDialog.invalidateInputMinMaxIndicator(materialDialog.input.getText().toString().length(), !builder.inputAllowEmpty);
        } else {
            materialDialog.inputMinMax.setVisibility(8);
            materialDialog.inputMinMax = null;
        }
    }

    private static void setupProgressDialog(MaterialDialog materialDialog) {
        MaterialDialog.Builder builder = materialDialog.builder;
        if (builder.indeterminateProgress || builder.progress > -2) {
            materialDialog.progressBar = (ProgressBar) materialDialog.view.findViewById(android.R.id.progress);
            if (materialDialog.progressBar == null) {
                return;
            }
            if (Build.VERSION.SDK_INT < 14) {
                MDTintHelper.setTint(materialDialog.progressBar, builder.widgetColor);
            } else if (!builder.indeterminateProgress) {
                HorizontalProgressDrawable horizontalProgressDrawable = new HorizontalProgressDrawable(builder.getContext());
                horizontalProgressDrawable.setTint(builder.widgetColor);
                materialDialog.progressBar.setProgressDrawable(horizontalProgressDrawable);
                materialDialog.progressBar.setIndeterminateDrawable(horizontalProgressDrawable);
            } else if (builder.indeterminateIsHorizontalProgress) {
                IndeterminateHorizontalProgressDrawable indeterminateHorizontalProgressDrawable = new IndeterminateHorizontalProgressDrawable(builder.getContext());
                indeterminateHorizontalProgressDrawable.setTint(builder.widgetColor);
                materialDialog.progressBar.setProgressDrawable(indeterminateHorizontalProgressDrawable);
                materialDialog.progressBar.setIndeterminateDrawable(indeterminateHorizontalProgressDrawable);
            } else {
                IndeterminateProgressDrawable indeterminateProgressDrawable = new IndeterminateProgressDrawable(builder.getContext());
                indeterminateProgressDrawable.setTint(builder.widgetColor);
                materialDialog.progressBar.setProgressDrawable(indeterminateProgressDrawable);
                materialDialog.progressBar.setIndeterminateDrawable(indeterminateProgressDrawable);
            }
            if (!builder.indeterminateProgress || builder.indeterminateIsHorizontalProgress) {
                materialDialog.progressBar.setIndeterminate(builder.indeterminateProgress && builder.indeterminateIsHorizontalProgress);
                materialDialog.progressBar.setProgress(0);
                materialDialog.progressBar.setMax(builder.progressMax);
                materialDialog.progressLabel = (TextView) materialDialog.view.findViewById(R.id.md_label);
                if (materialDialog.progressLabel != null) {
                    materialDialog.progressLabel.setTextColor(builder.contentColor);
                    materialDialog.setTypeface(materialDialog.progressLabel, builder.mediumFont);
                    materialDialog.progressLabel.setText(builder.progressPercentFormat.format(0L));
                }
                materialDialog.progressMinMax = (TextView) materialDialog.view.findViewById(R.id.md_minMax);
                if (materialDialog.progressMinMax != null) {
                    materialDialog.progressMinMax.setTextColor(builder.contentColor);
                    materialDialog.setTypeface(materialDialog.progressMinMax, builder.regularFont);
                    if (builder.showMinMax) {
                        materialDialog.progressMinMax.setVisibility(0);
                        materialDialog.progressMinMax.setText(String.format(builder.progressNumberFormat, 0, Integer.valueOf(builder.progressMax)));
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) materialDialog.progressBar.getLayoutParams();
                        marginLayoutParams.leftMargin = 0;
                        marginLayoutParams.rightMargin = 0;
                    } else {
                        materialDialog.progressMinMax.setVisibility(8);
                    }
                } else {
                    builder.showMinMax = false;
                }
            }
        }
        if (materialDialog.progressBar != null) {
            fixCanvasScalingWhenHardwareAccelerated(materialDialog.progressBar);
        }
    }
}
