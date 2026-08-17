package com.nononsenseapps.filepicker;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.nononsenseapps.filepicker.AbstractFilePickerFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AbstractFilePickerActivity<T> extends AppCompatActivity implements AbstractFilePickerFragment.OnFilePickedListener {
    public static final String EXTRA_ALLOW_CREATE_DIR = "nononsense.intent.ALLOW_CREATE_DIR";
    public static final String EXTRA_ALLOW_EXISTING_FILE = "android.intent.extra.ALLOW_EXISTING_FILE";
    public static final String EXTRA_ALLOW_MULTIPLE = "android.intent.extra.ALLOW_MULTIPLE";
    public static final String EXTRA_MODE = "nononsense.intent.MODE";
    public static final String EXTRA_PATHS = "nononsense.intent.PATHS";
    public static final String EXTRA_SINGLE_CLICK = "nononsense.intent.SINGLE_CLICK";
    public static final String EXTRA_START_PATH = "nononsense.intent.START_PATH";
    public static final int MODE_DIR = 1;
    public static final int MODE_FILE = 0;
    public static final int MODE_FILE_AND_DIR = 2;
    public static final int MODE_NEW_FILE = 3;
    protected static final String TAG = "filepicker_fragment";
    protected String startPath = null;
    protected int mode = 0;
    protected boolean allowCreateDir = false;
    protected boolean allowMultiple = false;
    private boolean allowExistingFile = true;
    protected boolean singleClick = false;

    protected abstract AbstractFilePickerFragment<T> getFragment(@Nullable String str, int i, boolean z, boolean z2, boolean z3, boolean z4);

    @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment.OnFilePickedListener
    public void onCancelled() {
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.nnf_activity_filepicker);
        Intent intent = getIntent();
        if (intent != null) {
            this.startPath = intent.getStringExtra(EXTRA_START_PATH);
            this.mode = intent.getIntExtra(EXTRA_MODE, this.mode);
            this.allowCreateDir = intent.getBooleanExtra(EXTRA_ALLOW_CREATE_DIR, this.allowCreateDir);
            this.allowMultiple = intent.getBooleanExtra(EXTRA_ALLOW_MULTIPLE, this.allowMultiple);
            this.allowExistingFile = intent.getBooleanExtra(EXTRA_ALLOW_EXISTING_FILE, this.allowExistingFile);
            this.singleClick = intent.getBooleanExtra(EXTRA_SINGLE_CLICK, this.singleClick);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        AbstractFilePickerFragment<T> abstractFilePickerFragment = (AbstractFilePickerFragment) supportFragmentManager.findFragmentByTag(TAG);
        if (abstractFilePickerFragment == null) {
            abstractFilePickerFragment = getFragment(this.startPath, this.mode, this.allowMultiple, this.allowCreateDir, this.allowExistingFile, this.singleClick);
        }
        if (abstractFilePickerFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment, abstractFilePickerFragment, TAG).commit();
        }
        setResult(0);
    }

    @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment.OnFilePickedListener
    public void onFilePicked(@NonNull Uri uri) {
        Intent intent = new Intent();
        intent.setData(uri);
        setResult(-1, intent);
        finish();
    }

    @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment.OnFilePickedListener
    @TargetApi(16)
    public void onFilesPicked(@NonNull List<Uri> list) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ALLOW_MULTIPLE, true);
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<Uri> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toString());
        }
        intent.putStringArrayListExtra(EXTRA_PATHS, arrayList);
        if (Build.VERSION.SDK_INT >= 16) {
            ClipData clipData = null;
            for (Uri uri : list) {
                if (clipData == null) {
                    clipData = new ClipData("Paths", new String[0], new ClipData.Item(uri));
                } else {
                    clipData.addItem(new ClipData.Item(uri));
                }
            }
            intent.setClipData(clipData);
        }
        setResult(-1, intent);
        finish();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }
}
