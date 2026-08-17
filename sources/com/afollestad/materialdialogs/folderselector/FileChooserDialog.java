package com.afollestad.materialdialogs.folderselector;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
public class FileChooserDialog extends DialogFragment implements MaterialDialog.ListCallback {
    private static final String DEFAULT_TAG = "[MD_FILE_SELECTOR]";
    private FileCallback callback;
    private boolean canGoUp = true;
    private File[] parentContents;
    private File parentFolder;

    /* loaded from: classes.dex */
    public static class Builder implements Serializable {

        @NonNull
        final transient AppCompatActivity context;
        String[] extensions;
        String tag;

        @StringRes
        int cancelButton = R.string.cancel;
        String initialPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String mimeType = null;
        String goUpLabel = "...";

        public <ActivityType extends AppCompatActivity & FileCallback> Builder(@NonNull ActivityType activitytype) {
            this.context = activitytype;
        }

        @NonNull
        public FileChooserDialog build() {
            FileChooserDialog fileChooserDialog = new FileChooserDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("builder", this);
            fileChooserDialog.setArguments(bundle);
            return fileChooserDialog;
        }

        @NonNull
        public Builder cancelButton(@StringRes int i) {
            this.cancelButton = i;
            return this;
        }

        @NonNull
        public Builder extensionsFilter(@Nullable String... strArr) {
            this.extensions = strArr;
            return this;
        }

        @NonNull
        public Builder goUpLabel(String str) {
            this.goUpLabel = str;
            return this;
        }

        @NonNull
        public Builder initialPath(@Nullable String str) {
            if (str == null) {
                str = File.separator;
            }
            this.initialPath = str;
            return this;
        }

        @NonNull
        public Builder mimeType(@Nullable String str) {
            this.mimeType = str;
            return this;
        }

        @NonNull
        public FileChooserDialog show() {
            FileChooserDialog build = build();
            build.show(this.context);
            return build;
        }

        @NonNull
        public Builder tag(@Nullable String str) {
            if (str == null) {
                str = FileChooserDialog.DEFAULT_TAG;
            }
            this.tag = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface FileCallback {
        void onFileChooserDismissed(@NonNull FileChooserDialog fileChooserDialog);

        void onFileSelection(@NonNull FileChooserDialog fileChooserDialog, @NonNull File file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FileSorter implements Comparator<File> {
        private FileSorter() {
        }

        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            if (file.isDirectory() && !file2.isDirectory()) {
                return -1;
            }
            if (file.isDirectory() || !file2.isDirectory()) {
                return file.getName().compareTo(file2.getName());
            }
            return 1;
        }
    }

    private void checkIfCanGoUp() {
        try {
            boolean z = true;
            if (this.parentFolder.getPath().split("/").length <= 1) {
                z = false;
            }
            this.canGoUp = z;
        } catch (IndexOutOfBoundsException unused) {
            this.canGoUp = false;
        }
    }

    @NonNull
    private Builder getBuilder() {
        return (Builder) getArguments().getSerializable("builder");
    }

    boolean fileIsMimeType(File file, String str, MimeTypeMap mimeTypeMap) {
        int lastIndexOf;
        if (str == null || str.equals("*/*")) {
            return true;
        }
        String uri = file.toURI().toString();
        int lastIndexOf2 = uri.lastIndexOf(46);
        if (lastIndexOf2 == -1) {
            return false;
        }
        String substring = uri.substring(lastIndexOf2 + 1);
        if (substring.endsWith("json")) {
            return str.startsWith("application/json");
        }
        String mimeTypeFromExtension = mimeTypeMap.getMimeTypeFromExtension(substring);
        if (mimeTypeFromExtension == null) {
            return false;
        }
        if (mimeTypeFromExtension.equals(str)) {
            return true;
        }
        int lastIndexOf3 = str.lastIndexOf(47);
        if (lastIndexOf3 == -1) {
            return false;
        }
        return str.substring(lastIndexOf3 + 1).equals("*") && (lastIndexOf = mimeTypeFromExtension.lastIndexOf(47)) != -1 && mimeTypeFromExtension.substring(0, lastIndexOf).equals(str.substring(0, lastIndexOf3));
    }

    CharSequence[] getContentsArray() {
        if (this.parentContents == null) {
            return this.canGoUp ? new String[]{getBuilder().goUpLabel} : new String[0];
        }
        String[] strArr = new String[this.parentContents.length + (this.canGoUp ? 1 : 0)];
        if (this.canGoUp) {
            strArr[0] = getBuilder().goUpLabel;
        }
        for (int i = 0; i < this.parentContents.length; i++) {
            strArr[this.canGoUp ? i + 1 : i] = this.parentContents[i].getName();
        }
        return strArr;
    }

    @NonNull
    public String getInitialPath() {
        return getBuilder().initialPath;
    }

    File[] listFiles(@Nullable String str, @Nullable String[] strArr) {
        boolean z;
        File[] listFiles = this.parentFolder.listFiles();
        ArrayList arrayList = new ArrayList();
        if (listFiles == null) {
            return null;
        }
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                arrayList.add(file);
            } else if (strArr != null) {
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    if (file.getName().toLowerCase().endsWith(strArr[i].toLowerCase())) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (z) {
                    arrayList.add(file);
                }
            } else if (str != null && fileIsMimeType(file, str, singleton)) {
                arrayList.add(file);
            }
        }
        Collections.sort(arrayList, new FileSorter());
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.callback = (FileCallback) activity;
    }

    @Override // android.support.v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return new MaterialDialog.Builder(getActivity()).title(com.afollestad.materialdialogs.commons.R.string.md_error_label).content(com.afollestad.materialdialogs.commons.R.string.md_storage_perm_error).positiveText(R.string.ok).build();
        }
        if (getArguments() == null || !getArguments().containsKey("builder")) {
            throw new IllegalStateException("You must create a FileChooserDialog using the Builder.");
        }
        if (!getArguments().containsKey("current_path")) {
            getArguments().putString("current_path", getBuilder().initialPath);
        }
        this.parentFolder = new File(getArguments().getString("current_path"));
        checkIfCanGoUp();
        this.parentContents = listFiles(getBuilder().mimeType, getBuilder().extensions);
        return new MaterialDialog.Builder(getActivity()).title(this.parentFolder.getAbsolutePath()).items(getContentsArray()).itemsCallback(this).onNegative(new MaterialDialog.SingleButtonCallback() { // from class: com.afollestad.materialdialogs.folderselector.FileChooserDialog.1
            @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
            public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                materialDialog.dismiss();
            }
        }).autoDismiss(false).negativeText(getBuilder().cancelButton).build();
    }

    @Override // android.support.v4.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.callback != null) {
            this.callback.onFileChooserDismissed(this);
        }
    }

    @Override // com.afollestad.materialdialogs.MaterialDialog.ListCallback
    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
        if (this.canGoUp && i == 0) {
            this.parentFolder = this.parentFolder.getParentFile();
            if (this.parentFolder.getAbsolutePath().equals("/storage/emulated")) {
                this.parentFolder = this.parentFolder.getParentFile();
            }
            this.canGoUp = this.parentFolder.getParent() != null;
        } else {
            File[] fileArr = this.parentContents;
            if (this.canGoUp) {
                i--;
            }
            this.parentFolder = fileArr[i];
            this.canGoUp = true;
            if (this.parentFolder.getAbsolutePath().equals("/storage/emulated")) {
                this.parentFolder = Environment.getExternalStorageDirectory();
            }
        }
        if (this.parentFolder.isFile()) {
            this.callback.onFileSelection(this, this.parentFolder);
            dismiss();
            return;
        }
        this.parentContents = listFiles(getBuilder().mimeType, getBuilder().extensions);
        MaterialDialog materialDialog2 = (MaterialDialog) getDialog();
        materialDialog2.setTitle(this.parentFolder.getAbsolutePath());
        getArguments().putString("current_path", this.parentFolder.getAbsolutePath());
        materialDialog2.setItems(getContentsArray());
    }

    public void show(FragmentActivity fragmentActivity) {
        String str = getBuilder().tag;
        Fragment findFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag(str);
        if (findFragmentByTag != null) {
            ((DialogFragment) findFragmentByTag).dismiss();
            fragmentActivity.getSupportFragmentManager().beginTransaction().remove(findFragmentByTag).commit();
        }
        show(fragmentActivity.getSupportFragmentManager(), str);
    }
}
