package cu.uci.android.apklis.presentation.ui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cu.uci.manolo.android.kiosko.R;

/* loaded from: classes.dex */
public class TextExpandableAnimation extends LinearLayout implements View.OnClickListener {
    public static int TOTAL_LINES;
    private final int WHAT;
    private final int WHAT_ANIMATION_END;
    private final int WHAT_EXPAND_ONLY;
    private Drawable drawableExpand;
    private Drawable drawableShrink;
    private int expandLines;
    private View gradientTransparent;

    @SuppressLint({"HandlerLeak"})
    private Handler handler;
    private boolean isExpandNeeded;
    private boolean isInitTextView;
    private boolean isShrink;
    private ImageView ivExpandOrShrink;
    public OnStateChangeListener onStateChangeListener;
    private RelativeLayout rlToggleLayout;
    private int sleepTime;
    private CharSequence textContent;
    private int textContentColor;
    private float textContentSize;
    private String textExpand;
    private int textLines;
    private String textShrink;
    private TextView textView;
    private int textViewStateColor;
    private Thread thread;
    private TextView tvState;

    /* loaded from: classes.dex */
    public interface OnStateChangeListener {
        void onStateChange(boolean z);
    }

    public TextExpandableAnimation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isShrink = false;
        this.isExpandNeeded = false;
        this.isInitTextView = true;
        this.sleepTime = 10;
        this.WHAT = 2;
        this.WHAT_ANIMATION_END = 3;
        this.WHAT_EXPAND_ONLY = 4;
        this.handler = new Handler() { // from class: cu.uci.android.apklis.presentation.ui.util.TextExpandableAnimation.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (2 == message.what) {
                    TextExpandableAnimation.this.textView.setMaxLines(message.arg1);
                    TextExpandableAnimation.this.textView.invalidate();
                } else if (3 == message.what) {
                    TextExpandableAnimation.this.setExpandState(message.arg1);
                } else if (4 == message.what) {
                    TextExpandableAnimation.this.changeExpandState(message.arg1);
                }
                super.handleMessage(message);
            }
        };
        initValue(context, attributeSet);
        initView(context);
        initClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeExpandState(int i) {
        this.rlToggleLayout.setVisibility(0);
        if (i < this.textLines) {
            this.gradientTransparent.setVisibility(0);
            this.ivExpandOrShrink.setImageDrawable(this.drawableExpand);
            this.tvState.setText(this.textExpand);
        } else {
            this.ivExpandOrShrink.setImageDrawable(this.drawableShrink);
            this.gradientTransparent.setVisibility(8);
            this.tvState.setText(this.textShrink);
        }
    }

    private void clickImageToggle() {
        if (this.isShrink) {
            doAnimation(this.expandLines, this.textLines, 4);
        } else {
            doAnimation(this.textLines, this.expandLines, 4);
        }
        this.isShrink = !this.isShrink;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAnimation(final int i, final int i2, final int i3) {
        this.thread = new Thread(new Runnable() { // from class: cu.uci.android.apklis.presentation.ui.util.TextExpandableAnimation.4
            @Override // java.lang.Runnable
            public void run() {
                if (i < i2) {
                    int i4 = i;
                    while (true) {
                        int i5 = i4 + 1;
                        if (i4 >= i2) {
                            break;
                        }
                        Message obtainMessage = TextExpandableAnimation.this.handler.obtainMessage(2, i5, 0);
                        try {
                            Thread.sleep(TextExpandableAnimation.this.sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        TextExpandableAnimation.this.handler.sendMessage(obtainMessage);
                        i4 = i5;
                    }
                } else if (i > i2) {
                    int i6 = i;
                    while (true) {
                        int i7 = i6 - 1;
                        if (i6 <= i2) {
                            break;
                        }
                        Message obtainMessage2 = TextExpandableAnimation.this.handler.obtainMessage(2, i7, 0);
                        try {
                            Thread.sleep(TextExpandableAnimation.this.sleepTime);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        TextExpandableAnimation.this.handler.sendMessage(obtainMessage2);
                        i6 = i7;
                    }
                }
                TextExpandableAnimation.this.handler.sendMessage(TextExpandableAnimation.this.handler.obtainMessage(i3, i2, 0));
            }
        });
        this.thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doNotExpand() {
        this.textView.setMaxLines(this.expandLines);
        this.rlToggleLayout.setVisibility(8);
        this.gradientTransparent.setVisibility(8);
    }

    private void initClick() {
        this.rlToggleLayout.setOnClickListener(this);
    }

    private void initValue(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextViewExpandableAnimation);
        this.expandLines = obtainStyledAttributes.getInteger(1, 5);
        this.drawableShrink = obtainStyledAttributes.getDrawable(2);
        this.drawableExpand = obtainStyledAttributes.getDrawable(0);
        this.textViewStateColor = obtainStyledAttributes.getColor(7, ContextCompat.getColor(context, cu.uci.android.apklis.R.color.colorPrimary));
        this.textShrink = obtainStyledAttributes.getString(6);
        this.textExpand = obtainStyledAttributes.getString(5);
        if (this.drawableShrink == null) {
            this.drawableShrink = ContextCompat.getDrawable(context, cu.uci.android.apklis.R.mipmap.ic_minus);
        }
        if (this.drawableExpand == null) {
            this.drawableExpand = ContextCompat.getDrawable(context, cu.uci.android.apklis.R.mipmap.ic_plus);
        }
        if (TextUtils.isEmpty(this.textShrink)) {
            this.textShrink = "";
        }
        if (TextUtils.isEmpty(this.textExpand)) {
            this.textExpand = "";
        }
        this.textContentColor = obtainStyledAttributes.getColor(3, ContextCompat.getColor(context, cu.uci.android.apklis.R.color.colorAccent));
        this.textContentSize = obtainStyledAttributes.getDimension(4, 14.0f);
        obtainStyledAttributes.recycle();
    }

    private void initView(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(cu.uci.android.apklis.R.layout.layout_textview_expand_animation, this);
        this.rlToggleLayout = (RelativeLayout) findViewById(cu.uci.android.apklis.R.id.rl_expand_text_view_animation_toggle_layout);
        this.gradientTransparent = findViewById(cu.uci.android.apklis.R.id.gradient_transparent);
        this.textView = (TextView) findViewById(cu.uci.android.apklis.R.id.tv_expand_text_view_animation);
        this.textView.setTextColor(this.textContentColor);
        this.textView.getPaint().setTextSize(this.textContentSize);
        this.ivExpandOrShrink = (ImageView) findViewById(cu.uci.android.apklis.R.id.iv_expand_text_view_animation_toggle);
        this.tvState = (TextView) findViewById(cu.uci.android.apklis.R.id.tv_expand_text_view_animation_hint);
        this.tvState.setTextColor(this.textViewStateColor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExpandState(int i) {
        if (i >= this.textLines) {
            this.isShrink = false;
            this.rlToggleLayout.setVisibility(8);
            this.gradientTransparent.setVisibility(8);
            this.ivExpandOrShrink.setImageDrawable(this.drawableShrink);
            this.tvState.setText(this.textShrink);
            return;
        }
        TOTAL_LINES = this.textLines;
        this.isShrink = true;
        this.rlToggleLayout.setVisibility(0);
        this.gradientTransparent.setVisibility(0);
        this.ivExpandOrShrink.setImageDrawable(this.drawableExpand);
        this.tvState.setText(this.textExpand);
    }

    public Drawable getDrawableExpand() {
        return this.drawableExpand;
    }

    public Drawable getDrawableShrink() {
        return this.drawableShrink;
    }

    public int getExpandLines() {
        return this.expandLines;
    }

    public int getSleepTime() {
        return this.sleepTime;
    }

    public CharSequence getTextContent() {
        return this.textContent;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == cu.uci.android.apklis.R.id.rl_expand_text_view_animation_toggle_layout || view.getId() == cu.uci.android.apklis.R.id.tv_expand_text_view_animation) {
            clickImageToggle();
            if (this.onStateChangeListener != null) {
                this.onStateChangeListener.onStateChange(this.isShrink);
            }
        }
    }

    public void resetState(boolean z) {
        this.isShrink = z;
        if (this.textLines <= this.expandLines) {
            doNotExpand();
            return;
        }
        if (z) {
            this.rlToggleLayout.setVisibility(0);
            this.gradientTransparent.setVisibility(0);
            this.ivExpandOrShrink.setImageDrawable(this.drawableExpand);
            this.textView.setMaxLines(this.expandLines);
            this.tvState.setText(this.textExpand);
            return;
        }
        this.rlToggleLayout.setVisibility(0);
        this.gradientTransparent.setVisibility(8);
        this.ivExpandOrShrink.setImageDrawable(this.drawableShrink);
        this.textView.setMaxLines(this.textLines);
        this.tvState.setText(this.textShrink);
    }

    public void setDrawableExpand(Drawable drawable) {
        this.drawableExpand = drawable;
    }

    public void setDrawableShrink(Drawable drawable) {
        this.drawableShrink = drawable;
    }

    public void setExpandLines(int i) {
        doAnimation(this.isShrink ? this.expandLines : this.textLines, this.textLines < i ? this.textLines : i, 3);
        this.expandLines = i;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    public void setSleepTime(int i) {
        this.sleepTime = i;
    }

    public void setText(CharSequence charSequence) {
        this.textContent = charSequence;
        this.textView.setText(charSequence.toString());
        this.textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: cu.uci.android.apklis.presentation.ui.util.TextExpandableAnimation.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (!TextExpandableAnimation.this.isInitTextView) {
                    return true;
                }
                TextExpandableAnimation.this.textLines = TextExpandableAnimation.this.textView.getLineCount();
                TextExpandableAnimation.this.isExpandNeeded = TextExpandableAnimation.this.textLines > TextExpandableAnimation.this.expandLines;
                TextExpandableAnimation.this.isInitTextView = false;
                if (TextExpandableAnimation.this.isExpandNeeded) {
                    TextExpandableAnimation.this.isShrink = true;
                    TextExpandableAnimation.this.doAnimation(TextExpandableAnimation.this.expandLines, TextExpandableAnimation.this.expandLines, 3);
                } else {
                    TextExpandableAnimation.this.isShrink = false;
                    TextExpandableAnimation.this.doNotExpand();
                }
                return true;
            }
        });
        if (this.isInitTextView) {
            return;
        }
        this.textLines = this.textView.getLineCount();
    }

    public void setTextSetMovementMethod(CharSequence charSequence) {
        this.textContent = Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(charSequence.toString(), 0) : Html.fromHtml(charSequence.toString());
        this.textView.setText(this.textContent);
        this.textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: cu.uci.android.apklis.presentation.ui.util.TextExpandableAnimation.2
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (!TextExpandableAnimation.this.isInitTextView) {
                    return true;
                }
                TextExpandableAnimation.this.textLines = TextExpandableAnimation.this.textView.getLineCount();
                TextExpandableAnimation.this.isExpandNeeded = TextExpandableAnimation.this.textLines > TextExpandableAnimation.this.expandLines;
                TextExpandableAnimation.this.isInitTextView = false;
                if (TextExpandableAnimation.this.isExpandNeeded) {
                    TextExpandableAnimation.this.isShrink = true;
                    TextExpandableAnimation.this.doAnimation(TextExpandableAnimation.this.expandLines, TextExpandableAnimation.this.expandLines, 3);
                } else {
                    TextExpandableAnimation.this.isShrink = false;
                    TextExpandableAnimation.this.doNotExpand();
                }
                return true;
            }
        });
        if (this.isInitTextView) {
            return;
        }
        this.textLines = this.textView.getLineCount();
    }
}
