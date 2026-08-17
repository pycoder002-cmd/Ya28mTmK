package github.nisrulz.stackedhorizontalprogressbar;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* loaded from: classes.dex */
public class StackedHorizontalProgressBar extends ProgressBar {
    int max_value;
    private Paint paint;
    int primary_progress;

    public StackedHorizontalProgressBar(Context context) {
        super(context);
        init();
    }

    public StackedHorizontalProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.paint = new Paint();
        this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.primary_progress = 0;
        this.max_value = 100;
    }

    @Override // android.widget.ProgressBar
    public synchronized void setMax(int i) {
        this.max_value = i;
        super.setMax(i);
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (i > this.max_value) {
            i = this.max_value;
        }
        this.primary_progress = i;
        super.setProgress(i);
    }

    @Override // android.widget.ProgressBar
    public synchronized void setSecondaryProgress(int i) {
        if (this.primary_progress + i > this.max_value) {
            i = this.max_value - this.primary_progress;
        }
        super.setSecondaryProgress(this.primary_progress + i);
    }
}
