package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

/* loaded from: classes.dex */
public class AwesomeWarningDialog extends AwesomeDialogBuilder<AwesomeWarningDialog> {
    private Button btDialogOk;
    private RelativeLayout dialogBody;

    public AwesomeWarningDialog(Context context) {
        super(context);
        this.btDialogOk = (Button) findView(R.id.btDialogOk);
        this.dialogBody = (RelativeLayout) findView(R.id.dialog_body);
        setColoredCircle(R.color.dialogWarningBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_dialog_warning, R.color.black);
        setButtonBackgroundColor(R.color.dialogWarningBackgroundColor);
        setCancelable(true);
    }

    @Override // com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeDialogBuilder
    protected int getLayout() {
        return R.layout.dialog_warning;
    }

    public AwesomeWarningDialog setButtonBackgroundColor(int i) {
        if (this.btDialogOk != null) {
            this.btDialogOk.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeWarningDialog setButtonText(String str) {
        if (this.btDialogOk != null) {
            this.btDialogOk.setText(str);
            this.btDialogOk.setVisibility(0);
        }
        return this;
    }

    public AwesomeWarningDialog setButtonTextColor(int i) {
        if (this.btDialogOk != null) {
            this.btDialogOk.setTextColor(ContextCompat.getColor(getContext(), i));
        }
        return this;
    }

    public AwesomeWarningDialog setDialogBodyBackgroundColor(int i) {
        if (this.dialogBody != null) {
            this.dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeWarningDialog setWarningButtonClick(@Nullable final Closure closure) {
        this.btDialogOk.setOnClickListener(new View.OnClickListener() { // from class: com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeWarningDialog.this.hide();
            }
        });
        return this;
    }
}
