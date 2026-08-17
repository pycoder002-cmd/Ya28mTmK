package com.nononsenseapps.filepicker;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/* loaded from: classes.dex */
public abstract class NewItemFragment extends DialogFragment {
    private OnNewFolderListener listener = null;

    /* loaded from: classes.dex */
    public interface OnNewFolderListener {
        void onNewFolder(@NonNull String str);
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // android.support.v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.nnf_dialog_folder_name).setTitle(R.string.nnf_new_folder).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
        AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.nononsenseapps.filepicker.NewItemFragment.1
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                final AlertDialog alertDialog = (AlertDialog) dialogInterface;
                final EditText editText = (EditText) alertDialog.findViewById(R.id.edit_text);
                if (editText == null) {
                    throw new NullPointerException("Could not find an edit text in the dialog");
                }
                alertDialog.getButton(-2).setOnClickListener(new View.OnClickListener() { // from class: com.nononsenseapps.filepicker.NewItemFragment.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                final Button button = alertDialog.getButton(-1);
                button.setEnabled(false);
                button.setOnClickListener(new View.OnClickListener() { // from class: com.nononsenseapps.filepicker.NewItemFragment.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        String obj = editText.getText().toString();
                        if (NewItemFragment.this.validateName(obj)) {
                            if (NewItemFragment.this.listener != null) {
                                NewItemFragment.this.listener.onNewFolder(obj);
                            }
                            alertDialog.dismiss();
                        }
                    }
                });
                editText.addTextChangedListener(new TextWatcher() { // from class: com.nononsenseapps.filepicker.NewItemFragment.1.3
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        button.setEnabled(NewItemFragment.this.validateName(editable.toString()));
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }
                });
            }
        });
        return create;
    }

    public void setListener(@Nullable OnNewFolderListener onNewFolderListener) {
        this.listener = onNewFolderListener;
    }

    protected abstract boolean validateName(String str);
}
