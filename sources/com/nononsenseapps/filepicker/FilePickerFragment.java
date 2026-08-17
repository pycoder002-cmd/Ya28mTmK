package com.nononsenseapps.filepicker;

import android.net.Uri;
import android.os.FileObserver;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v7.util.SortedList;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.widget.Toast;
import java.io.File;

/* loaded from: classes.dex */
public class FilePickerFragment extends AbstractFilePickerFragment<File> {
    protected static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    protected boolean showHiddenItems = false;
    private File mRequestedPath = null;

    public boolean areHiddenItemsShown() {
        return this.showHiddenItems;
    }

    protected int compareFiles(@NonNull File file, @NonNull File file2) {
        if (file.isDirectory() && !file2.isDirectory()) {
            return -1;
        }
        if (!file2.isDirectory() || file.isDirectory()) {
            return file.getName().compareToIgnoreCase(file2.getName());
        }
        return 1;
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public String getFullPath(@NonNull File file) {
        return file.getPath();
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public Loader<SortedList<File>> getLoader() {
        return new AsyncTaskLoader<SortedList<File>>(getActivity()) { // from class: com.nononsenseapps.filepicker.FilePickerFragment.1
            FileObserver fileObserver;

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.support.v4.content.AsyncTaskLoader
            public SortedList<File> loadInBackground() {
                File[] listFiles = ((File) FilePickerFragment.this.mCurrentPath).listFiles();
                SortedList<File> sortedList = new SortedList<>(File.class, new SortedListAdapterCallback<File>(FilePickerFragment.this.getDummyAdapter()) { // from class: com.nononsenseapps.filepicker.FilePickerFragment.1.1
                    @Override // android.support.v7.util.SortedList.Callback
                    public boolean areContentsTheSame(File file, File file2) {
                        return file.getAbsolutePath().equals(file2.getAbsolutePath()) && file.isFile() == file2.isFile();
                    }

                    @Override // android.support.v7.util.SortedList.Callback
                    public boolean areItemsTheSame(File file, File file2) {
                        return areContentsTheSame(file, file2);
                    }

                    @Override // android.support.v7.util.SortedList.Callback, java.util.Comparator
                    public int compare(File file, File file2) {
                        return FilePickerFragment.this.compareFiles(file, file2);
                    }
                }, listFiles == null ? 0 : listFiles.length);
                sortedList.beginBatchedUpdates();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (FilePickerFragment.this.isItemVisible(file)) {
                            sortedList.add(file);
                        }
                    }
                }
                sortedList.endBatchedUpdates();
                return sortedList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.content.Loader
            public void onReset() {
                super.onReset();
                if (this.fileObserver != null) {
                    this.fileObserver.stopWatching();
                    this.fileObserver = null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [T, java.io.File] */
            @Override // android.support.v4.content.Loader
            public void onStartLoading() {
                super.onStartLoading();
                if (FilePickerFragment.this.mCurrentPath == 0 || !((File) FilePickerFragment.this.mCurrentPath).isDirectory()) {
                    FilePickerFragment.this.mCurrentPath = FilePickerFragment.this.getRoot();
                }
                this.fileObserver = new FileObserver(((File) FilePickerFragment.this.mCurrentPath).getPath(), 960) { // from class: com.nononsenseapps.filepicker.FilePickerFragment.1.2
                    @Override // android.os.FileObserver
                    public void onEvent(int i, String str) {
                        onContentChanged();
                    }
                };
                this.fileObserver.startWatching();
                forceLoad();
            }
        };
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public String getName(@NonNull File file) {
        return file.getName();
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public File getParent(@NonNull File file) {
        return (file.getPath().equals(getRoot().getPath()) || file.getParentFile() == null) ? file : file.getParentFile();
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public File getPath(@NonNull String str) {
        return new File(str);
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public File getRoot() {
        return new File("/");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment
    public void handlePermission(@NonNull File file) {
        this.mRequestedPath = file;
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment
    public boolean hasPermission(@NonNull File file) {
        return ContextCompat.checkSelfPermission(getContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    public boolean isDir(@NonNull File file) {
        return file.isDirectory();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment
    public boolean isItemVisible(File file) {
        if (this.showHiddenItems || !file.isHidden()) {
            return super.isItemVisible((FilePickerFragment) file);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.nononsenseapps.filepicker.NewItemFragment.OnNewFolderListener
    public void onNewFolder(@NonNull String str) {
        File file = new File((File) this.mCurrentPath, str);
        if (file.mkdir()) {
            refresh(file);
        } else {
            Toast.makeText(getActivity(), R.string.nnf_create_folder_error, 0).show();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (strArr.length == 0) {
            if (this.mListener != null) {
                this.mListener.onCancelled();
            }
        } else if (iArr[0] == 0) {
            if (this.mRequestedPath != null) {
                refresh(this.mRequestedPath);
            }
        } else {
            Toast.makeText(getContext(), R.string.nnf_permission_external_write_denied, 0).show();
            if (this.mListener != null) {
                this.mListener.onCancelled();
            }
        }
    }

    public void showHiddenItems(boolean z) {
        this.showHiddenItems = z;
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public Uri toUri(@NonNull File file) {
        return FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", file);
    }
}
