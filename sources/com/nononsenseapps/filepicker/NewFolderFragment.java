package com.nononsenseapps.filepicker;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import com.nononsenseapps.filepicker.NewItemFragment;

/* loaded from: classes.dex */
public class NewFolderFragment extends NewItemFragment {
    private static final String TAG = "new_folder_fragment";

    public static void showDialog(@NonNull FragmentManager fragmentManager, @Nullable NewItemFragment.OnNewFolderListener onNewFolderListener) {
        NewFolderFragment newFolderFragment = new NewFolderFragment();
        newFolderFragment.setListener(onNewFolderListener);
        newFolderFragment.show(fragmentManager, TAG);
    }

    @Override // com.nononsenseapps.filepicker.NewItemFragment
    protected boolean validateName(@Nullable String str) {
        return Utils.isValidFileName(str);
    }
}
