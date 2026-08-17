package com.nononsenseapps.filepicker;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.support.annotation.Nullable;
import java.io.File;

@SuppressLint({"Registered"})
/* loaded from: classes.dex */
public class FilePickerActivity extends AbstractFilePickerActivity<File> {
    @Override // com.nononsenseapps.filepicker.AbstractFilePickerActivity
    protected AbstractFilePickerFragment<File> getFragment(@Nullable String str, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        FilePickerFragment filePickerFragment = new FilePickerFragment();
        if (str == null) {
            str = Environment.getExternalStorageDirectory().getPath();
        }
        filePickerFragment.setArgs(str, i, z, z2, z3, z4);
        return filePickerFragment;
    }
}
