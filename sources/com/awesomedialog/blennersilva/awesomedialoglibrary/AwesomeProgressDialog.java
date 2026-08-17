package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public class AwesomeProgressDialog extends AwesomeDialogBuilder<AwesomeProgressDialog> {
    private RelativeLayout dialogBody;
    private ProgressBar progressBar;

    public AwesomeProgressDialog(Context context) {
        super(context);
        this.progressBar = (ProgressBar) findView(R.id.dialog_progress_bar);
        this.dialogBody = (RelativeLayout) findView(R.id.dialog_body);
        setColoredCircle(R.color.dialogProgressBackgroundColor);
        setProgressBarColor(R.color.white);
    }

    @Override // com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeDialogBuilder
    protected int getLayout() {
        return R.layout.dialog_progress;
    }

    public AwesomeProgressDialog setDialogBodyBackgroundColor(int i) {
        if (this.dialogBody != null) {
            this.dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeProgressDialog setProgressBarColor(int i) {
        this.progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
        return this;
    }
}
