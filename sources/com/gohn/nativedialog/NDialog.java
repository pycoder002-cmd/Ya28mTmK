package com.gohn.nativedialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class NDialog {
    public static final int BUTTON_NEGATIVE = 2;
    public static final int BUTTON_NEUTRAL = 4;
    public static final int BUTTON_POSITIVE = 1;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private ButtonType buttonType;
    private CanceledListener canceledListener;
    private Context context;
    private View customView;
    private List<View> customViewChildren;
    private CustomViewClickListener customViewClickListener;
    private Drawable icon;
    private CharSequence message;
    private ButtonClickListener negativeButtonClickListener;
    private CharSequence negativeButtonText;
    private int negativeButtonTextColor;
    private ButtonClickListener neutralButtonClickListener;
    private CharSequence neutralButtonText;
    private int neutralButtonTextColor;
    private ButtonClickListener positiveButtonClickListener;
    private CharSequence positiveButtonText;
    private int positiveButtonTextColor;
    private CharSequence title;
    private boolean isPositiveButtonOnClickDismiss = true;
    private boolean isNegativeButtonOnClickDismiss = true;
    private boolean isNeutralButtonOnClickDismiss = true;
    private boolean cancelable = true;

    public NDialog(Context context, ButtonType buttonType) {
        this.context = context;
        this.builder = new AlertDialog.Builder(context);
        this.buttonType = buttonType;
    }

    private List<View> getAllChildViews(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            arrayList.addAll(getChildViews((ViewGroup) view));
        }
        return arrayList;
    }

    private List<View> getChildViews(ViewGroup viewGroup) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            arrayList.add(childAt);
            if (childAt instanceof ViewGroup) {
                arrayList.addAll(getChildViews((ViewGroup) childAt));
            }
        }
        return arrayList;
    }

    public void dismiss() {
        if (this.alertDialog != null) {
            this.alertDialog.dismiss();
        }
    }

    public AlertDialog getAlertDialog() {
        return this.alertDialog;
    }

    public List<View> getCustomViewChildren() {
        return this.customViewChildren;
    }

    public NDialog isCancelable(boolean z) {
        this.cancelable = z;
        return this;
    }

    public NDialog setCanceledListener(CanceledListener canceledListener) {
        this.canceledListener = canceledListener;
        return this;
    }

    public NDialog setCustomView(int i) {
        if (this.context instanceof Activity) {
            this.customView = ((Activity) this.context).getLayoutInflater().inflate(i, (ViewGroup) null);
            this.customViewChildren = getAllChildViews(this.customView);
        }
        return this;
    }

    public NDialog setCustomView(View view) {
        this.customView = view;
        this.customViewChildren = getAllChildViews(this.customView);
        return this;
    }

    public NDialog setCustomViewClickListener(CustomViewClickListener customViewClickListener) {
        this.customViewClickListener = customViewClickListener;
        return this;
    }

    public NDialog setIcon(int i) {
        this.icon = this.context.getResources().getDrawable(i);
        return this;
    }

    public NDialog setIcon(Drawable drawable) {
        this.icon = drawable;
        return this;
    }

    public NDialog setMessage(int i) {
        setMessage(this.context.getResources().getString(i));
        return this;
    }

    public NDialog setMessage(CharSequence charSequence) {
        this.message = charSequence;
        return this;
    }

    public NDialog setNegativeButtonClickListener(ButtonClickListener buttonClickListener) {
        this.negativeButtonClickListener = buttonClickListener;
        return this;
    }

    public NDialog setNegativeButtonOnClickDismiss(boolean z) {
        this.isNegativeButtonOnClickDismiss = z;
        return this;
    }

    public NDialog setNegativeButtonText(int i) {
        setNegativeButtonText(this.context.getResources().getString(i));
        return this;
    }

    public NDialog setNegativeButtonText(CharSequence charSequence) {
        this.negativeButtonText = charSequence;
        return this;
    }

    public NDialog setNegativeButtonTextColor(int i) {
        this.negativeButtonTextColor = i;
        return this;
    }

    public NDialog setNegativeColorResource(int i) {
        this.negativeButtonTextColor = this.context.getResources().getColor(i);
        return this;
    }

    public NDialog setNeutralButtonClickListener(ButtonClickListener buttonClickListener) {
        this.neutralButtonClickListener = buttonClickListener;
        return this;
    }

    public NDialog setNeutralButtonOnClickDismiss(boolean z) {
        this.isNeutralButtonOnClickDismiss = z;
        return this;
    }

    public NDialog setNeutralButtonText(int i) {
        setNeutralButtonText(this.context.getResources().getString(i));
        return this;
    }

    public NDialog setNeutralButtonText(CharSequence charSequence) {
        this.neutralButtonText = charSequence;
        return this;
    }

    public NDialog setNeutralButtonTextColor(int i) {
        this.neutralButtonTextColor = i;
        return this;
    }

    public NDialog setNeutralColorResource(int i) {
        this.neutralButtonTextColor = this.context.getResources().getColor(i);
        return this;
    }

    public NDialog setPositiveButtonClickListener(ButtonClickListener buttonClickListener) {
        this.positiveButtonClickListener = buttonClickListener;
        return this;
    }

    public NDialog setPositiveButtonOnClickDismiss(boolean z) {
        this.isPositiveButtonOnClickDismiss = z;
        return this;
    }

    public NDialog setPositiveButtonText(int i) {
        setPositiveButtonText(this.context.getResources().getString(i));
        return this;
    }

    public NDialog setPositiveButtonText(CharSequence charSequence) {
        this.positiveButtonText = charSequence;
        return this;
    }

    public NDialog setPositiveButtonTextColor(int i) {
        this.positiveButtonTextColor = i;
        return this;
    }

    public NDialog setPositiveButtonTextColorResource(int i) {
        this.positiveButtonTextColor = this.context.getResources().getColor(i);
        return this;
    }

    public NDialog setTitle(int i) {
        setTitle(this.context.getResources().getString(i));
        return this;
    }

    public NDialog setTitle(CharSequence charSequence) {
        this.title = charSequence;
        return this;
    }

    public NDialog show() {
        if (this.builder == null) {
            return null;
        }
        if (this.customView != null) {
            this.builder.setView(this.customView);
            if (this.customViewClickListener != null && this.customViewChildren != null) {
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.gohn.nativedialog.NDialog.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NDialog.this.customViewClickListener.onClickView(view);
                    }
                };
                Iterator<View> it = getAllChildViews(this.customView).iterator();
                while (it.hasNext()) {
                    it.next().setOnClickListener(onClickListener);
                }
            }
        }
        if (this.icon != null) {
            this.builder.setIcon(this.icon);
        }
        if (this.title != null) {
            this.builder.setTitle(this.title);
        }
        if (this.message != null) {
            this.builder.setMessage(this.message);
        }
        this.builder.setCancelable(this.cancelable);
        if (this.canceledListener != null) {
            this.builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.gohn.nativedialog.NDialog.2
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    NDialog.this.canceledListener.onCanceled();
                }
            });
        }
        if ((this.buttonType.getValue() & 1) == 1) {
            this.builder.setPositiveButton(R.string.easy_native_dialog_btn_ok, (DialogInterface.OnClickListener) null);
        }
        if ((this.buttonType.getValue() & 2) == 2) {
            this.builder.setNegativeButton(R.string.easy_native_dialog_btn_cancel, (DialogInterface.OnClickListener) null);
        }
        if ((this.buttonType.getValue() & 4) == 4) {
            this.builder.setNeutralButton(R.string.easy_native_dialog_btn_option, (DialogInterface.OnClickListener) null);
        }
        this.alertDialog = this.builder.create();
        this.alertDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.gohn.nativedialog.NDialog.3
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                if ((NDialog.this.buttonType.getValue() & 1) == 1) {
                    Button button = NDialog.this.alertDialog.getButton(-1);
                    if (NDialog.this.positiveButtonText != null) {
                        button.setText(NDialog.this.positiveButtonText);
                    }
                    if (NDialog.this.positiveButtonTextColor != 0) {
                        button.setTextColor(NDialog.this.positiveButtonTextColor);
                    }
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.gohn.nativedialog.NDialog.3.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (NDialog.this.isPositiveButtonOnClickDismiss) {
                                NDialog.this.dismiss();
                            }
                            if (NDialog.this.positiveButtonClickListener != null) {
                                NDialog.this.positiveButtonClickListener.onClick(1);
                            }
                        }
                    });
                }
                if ((NDialog.this.buttonType.getValue() & 2) == 2) {
                    Button button2 = NDialog.this.alertDialog.getButton(-2);
                    if (NDialog.this.negativeButtonText != null) {
                        button2.setText(NDialog.this.negativeButtonText);
                    }
                    if (NDialog.this.negativeButtonTextColor != 0) {
                        button2.setTextColor(NDialog.this.negativeButtonTextColor);
                    }
                    button2.setOnClickListener(new View.OnClickListener() { // from class: com.gohn.nativedialog.NDialog.3.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (NDialog.this.isNegativeButtonOnClickDismiss) {
                                NDialog.this.dismiss();
                            }
                            if (NDialog.this.negativeButtonClickListener != null) {
                                NDialog.this.negativeButtonClickListener.onClick(2);
                            }
                        }
                    });
                }
                if ((NDialog.this.buttonType.getValue() & 4) == 4) {
                    Button button3 = NDialog.this.alertDialog.getButton(-3);
                    if (NDialog.this.neutralButtonText != null) {
                        button3.setText(NDialog.this.neutralButtonText);
                    }
                    if (NDialog.this.neutralButtonTextColor != 0) {
                        button3.setTextColor(NDialog.this.neutralButtonTextColor);
                    }
                    button3.setOnClickListener(new View.OnClickListener() { // from class: com.gohn.nativedialog.NDialog.3.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (NDialog.this.isNeutralButtonOnClickDismiss) {
                                NDialog.this.dismiss();
                            }
                            if (NDialog.this.neutralButtonClickListener != null) {
                                NDialog.this.neutralButtonClickListener.onClick(4);
                            }
                        }
                    });
                }
            }
        });
        this.alertDialog.show();
        return this;
    }
}
