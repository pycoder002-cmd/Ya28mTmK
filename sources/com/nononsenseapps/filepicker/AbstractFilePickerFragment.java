package com.nononsenseapps.filepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.nononsenseapps.filepicker.NewItemFragment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AbstractFilePickerFragment<T> extends Fragment implements LoaderManager.LoaderCallbacks<SortedList<T>>, NewItemFragment.OnNewFolderListener, LogicHandler<T> {
    public static final String KEY_ALLOW_DIR_CREATE = "KEY_ALLOW_DIR_CREATE";
    public static final String KEY_ALLOW_EXISTING_FILE = "KEY_ALLOW_EXISTING_FILE";
    public static final String KEY_ALLOW_MULTIPLE = "KEY_ALLOW_MULTIPLE";
    protected static final String KEY_CURRENT_PATH = "KEY_CURRENT_PATH";
    public static final String KEY_MODE = "KEY_MODE";
    public static final String KEY_SINGLE_CLICK = "KEY_SINGLE_CLICK";
    public static final String KEY_START_PATH = "KEY_START_PATH";
    public static final int MODE_DIR = 1;
    public static final int MODE_FILE = 0;
    public static final int MODE_FILE_AND_DIR = 2;
    public static final int MODE_NEW_FILE = 3;
    protected LinearLayoutManager layoutManager;
    protected TextView mCurrentDirView;
    protected EditText mEditTextFileName;
    protected OnFilePickedListener mListener;
    protected RecyclerView recyclerView;
    protected int mode = 0;
    protected T mCurrentPath = null;
    protected boolean allowCreateDir = false;
    protected boolean allowMultiple = false;
    protected boolean allowExistingFile = true;
    protected boolean singleClick = false;
    protected FileItemAdapter<T> mAdapter = null;
    protected SortedList<T> mFiles = null;
    protected Toast mToast = null;
    protected boolean isLoading = false;
    protected View mNewFileButtonContainer = null;
    protected View mRegularButtonContainer = null;
    protected final HashSet<T> mCheckedItems = new HashSet<>();
    protected final HashSet<AbstractFilePickerFragment<T>.CheckableViewHolder> mCheckedVisibleViewHolders = new HashSet<>();

    /* loaded from: classes.dex */
    public class CheckableViewHolder extends AbstractFilePickerFragment<T>.DirViewHolder {
        public CheckBox checkbox;

        public CheckableViewHolder(View view) {
            super(view);
            boolean z = AbstractFilePickerFragment.this.mode == 3;
            this.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            this.checkbox.setVisibility((z || AbstractFilePickerFragment.this.singleClick) ? 8 : 0);
            this.checkbox.setOnClickListener(new View.OnClickListener() { // from class: com.nononsenseapps.filepicker.AbstractFilePickerFragment.CheckableViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AbstractFilePickerFragment.this.onClickCheckBox(CheckableViewHolder.this);
                }
            });
        }

        @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment.DirViewHolder, android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractFilePickerFragment.this.onClickCheckable(view, this);
        }

        @Override // com.nononsenseapps.filepicker.AbstractFilePickerFragment.DirViewHolder, android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return AbstractFilePickerFragment.this.onLongClickCheckable(view, this);
        }
    }

    /* loaded from: classes.dex */
    public class DirViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public T file;
        public View icon;
        public TextView text;

        public DirViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            this.icon = view.findViewById(R.id.item_icon);
            this.text = (TextView) view.findViewById(android.R.id.text1);
        }

        public void onClick(View view) {
            AbstractFilePickerFragment.this.onClickDir(view, this);
        }

        public boolean onLongClick(View view) {
            return AbstractFilePickerFragment.this.onLongClickDir(view, this);
        }
    }

    /* loaded from: classes.dex */
    public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView text;

        public HeaderViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.text = (TextView) view.findViewById(android.R.id.text1);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractFilePickerFragment.this.onClickHeader(view, this);
        }
    }

    /* loaded from: classes.dex */
    public interface OnFilePickedListener {
        void onCancelled();

        void onFilePicked(@NonNull Uri uri);

        void onFilesPicked(@NonNull List<Uri> list);
    }

    public AbstractFilePickerFragment() {
        setRetainInstance(true);
    }

    public void clearSelections() {
        Iterator<AbstractFilePickerFragment<T>.CheckableViewHolder> it = this.mCheckedVisibleViewHolders.iterator();
        while (it.hasNext()) {
            it.next().checkbox.setChecked(false);
        }
        this.mCheckedVisibleViewHolders.clear();
        this.mCheckedItems.clear();
    }

    protected void configureItemDecoration(@NonNull LayoutInflater layoutInflater, @NonNull RecyclerView recyclerView) {
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new int[]{R.attr.nnf_list_item_divider});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        if (drawable != null) {
            recyclerView.addItemDecoration(new DividerItemDecoration(drawable));
        }
    }

    protected FileItemAdapter<T> getAdapter() {
        return this.mAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FileItemAdapter<T> getDummyAdapter() {
        return new FileItemAdapter<>(this);
    }

    @Nullable
    public T getFirstCheckedItem() {
        Iterator<T> it = this.mCheckedItems.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    public int getItemViewType(int i, @NonNull T t) {
        return isCheckable(t) ? 2 : 1;
    }

    @NonNull
    protected String getNewFileName() {
        return this.mEditTextFileName.getText().toString();
    }

    public void goToDir(@NonNull T t) {
        if (this.isLoading) {
            return;
        }
        this.mCheckedItems.clear();
        this.mCheckedVisibleViewHolders.clear();
        refresh(t);
    }

    public void goUp() {
        goToDir(getParent(this.mCurrentPath));
    }

    protected void handlePermission(@NonNull T t) {
    }

    protected boolean hasPermission(@NonNull T t) {
        return true;
    }

    protected View inflateRootView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.nnf_fragment_filepicker, viewGroup, false);
    }

    public boolean isCheckable(@NonNull T t) {
        if (isDir(t)) {
            if ((this.mode != 1 || !this.allowMultiple) && (this.mode != 2 || !this.allowMultiple)) {
                return false;
            }
        } else if (this.mode != 0 && this.mode != 2 && !this.allowExistingFile) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isItemVisible(T t) {
        return isDir(t) || this.mode == 0 || this.mode == 2 || (this.mode == 3 && this.allowExistingFile);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        String string;
        super.onActivityCreated(bundle);
        if (this.mCurrentPath == null) {
            if (bundle != null) {
                this.mode = bundle.getInt(KEY_MODE, this.mode);
                this.allowCreateDir = bundle.getBoolean(KEY_ALLOW_DIR_CREATE, this.allowCreateDir);
                this.allowMultiple = bundle.getBoolean(KEY_ALLOW_MULTIPLE, this.allowMultiple);
                this.allowExistingFile = bundle.getBoolean(KEY_ALLOW_EXISTING_FILE, this.allowExistingFile);
                this.singleClick = bundle.getBoolean(KEY_SINGLE_CLICK, this.singleClick);
                String string2 = bundle.getString(KEY_CURRENT_PATH);
                if (string2 != null) {
                    this.mCurrentPath = getPath(string2.trim());
                }
            } else if (getArguments() != null) {
                this.mode = getArguments().getInt(KEY_MODE, this.mode);
                this.allowCreateDir = getArguments().getBoolean(KEY_ALLOW_DIR_CREATE, this.allowCreateDir);
                this.allowMultiple = getArguments().getBoolean(KEY_ALLOW_MULTIPLE, this.allowMultiple);
                this.allowExistingFile = getArguments().getBoolean(KEY_ALLOW_EXISTING_FILE, this.allowExistingFile);
                this.singleClick = getArguments().getBoolean(KEY_SINGLE_CLICK, this.singleClick);
                if (getArguments().containsKey(KEY_START_PATH) && (string = getArguments().getString(KEY_START_PATH)) != null) {
                    T path = getPath(string.trim());
                    if (isDir(path)) {
                        this.mCurrentPath = path;
                    } else {
                        this.mCurrentPath = getParent(path);
                        this.mEditTextFileName.setText(getName(path));
                    }
                }
            }
        }
        setModeView();
        if (this.mCurrentPath == null) {
            this.mCurrentPath = getRoot();
        }
        refresh(this.mCurrentPath);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnFilePickedListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement OnFilePickedListener");
        }
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    public void onBindHeaderViewHolder(@NonNull AbstractFilePickerFragment<T>.HeaderViewHolder headerViewHolder) {
        headerViewHolder.text.setText("..");
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    public void onBindViewHolder(@NonNull AbstractFilePickerFragment<T>.DirViewHolder dirViewHolder, int i, @NonNull T t) {
        dirViewHolder.file = t;
        dirViewHolder.icon.setVisibility(isDir(t) ? 0 : 8);
        dirViewHolder.text.setText(getName(t));
        if (isCheckable(t)) {
            if (!this.mCheckedItems.contains(t)) {
                this.mCheckedVisibleViewHolders.remove(dirViewHolder);
                ((CheckableViewHolder) dirViewHolder).checkbox.setChecked(false);
            } else {
                AbstractFilePickerFragment<T>.CheckableViewHolder checkableViewHolder = (CheckableViewHolder) dirViewHolder;
                this.mCheckedVisibleViewHolders.add(checkableViewHolder);
                checkableViewHolder.checkbox.setChecked(true);
            }
        }
    }

    public void onClickCancel(@NonNull View view) {
        if (this.mListener != null) {
            this.mListener.onCancelled();
        }
    }

    public void onClickCheckBox(@NonNull AbstractFilePickerFragment<T>.CheckableViewHolder checkableViewHolder) {
        if (this.mCheckedItems.contains(checkableViewHolder.file)) {
            checkableViewHolder.checkbox.setChecked(false);
            this.mCheckedItems.remove(checkableViewHolder.file);
            this.mCheckedVisibleViewHolders.remove(checkableViewHolder);
        } else {
            if (!this.allowMultiple) {
                clearSelections();
            }
            checkableViewHolder.checkbox.setChecked(true);
            this.mCheckedItems.add(checkableViewHolder.file);
            this.mCheckedVisibleViewHolders.add(checkableViewHolder);
        }
    }

    public void onClickCheckable(@NonNull View view, @NonNull AbstractFilePickerFragment<T>.CheckableViewHolder checkableViewHolder) {
        if (isDir(checkableViewHolder.file)) {
            goToDir(checkableViewHolder.file);
            return;
        }
        onLongClickCheckable(view, checkableViewHolder);
        if (this.singleClick) {
            onClickOk(view);
        }
    }

    public void onClickDir(@NonNull View view, @NonNull AbstractFilePickerFragment<T>.DirViewHolder dirViewHolder) {
        if (isDir(dirViewHolder.file)) {
            goToDir(dirViewHolder.file);
        }
    }

    public void onClickHeader(@NonNull View view, @NonNull AbstractFilePickerFragment<T>.HeaderViewHolder headerViewHolder) {
        goUp();
    }

    public void onClickOk(@NonNull View view) {
        if (this.mListener == null) {
            return;
        }
        if ((this.allowMultiple || this.mode == 0) && (this.mCheckedItems.isEmpty() || getFirstCheckedItem() == null)) {
            if (this.mToast == null) {
                this.mToast = Toast.makeText(getActivity(), R.string.nnf_select_something_first, 0);
            }
            this.mToast.show();
            return;
        }
        if (this.mode == 3) {
            String newFileName = getNewFileName();
            this.mListener.onFilePicked(newFileName.startsWith("/") ? toUri((AbstractFilePickerFragment<T>) getPath(newFileName)) : toUri((AbstractFilePickerFragment<T>) getPath(Utils.appendPath(getFullPath(this.mCurrentPath), newFileName))));
            return;
        }
        if (this.allowMultiple) {
            this.mListener.onFilesPicked(toUri((Iterable) this.mCheckedItems));
            return;
        }
        if (this.mode == 0) {
            this.mListener.onFilePicked(toUri((AbstractFilePickerFragment<T>) getFirstCheckedItem()));
            return;
        }
        if (this.mode == 1) {
            this.mListener.onFilePicked(toUri((AbstractFilePickerFragment<T>) this.mCurrentPath));
        } else if (this.mCheckedItems.isEmpty()) {
            this.mListener.onFilePicked(toUri((AbstractFilePickerFragment<T>) this.mCurrentPath));
        } else {
            this.mListener.onFilePicked(toUri((AbstractFilePickerFragment<T>) getFirstCheckedItem()));
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public Loader<SortedList<T>> onCreateLoader(int i, Bundle bundle) {
        return getLoader();
    }

    @Override // android.support.v4.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.picker_actions, menu);
        menu.findItem(R.id.nnf_action_createdir).setVisible(this.allowCreateDir);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflateRootView = inflateRootView(layoutInflater, viewGroup);
        Toolbar toolbar = (Toolbar) inflateRootView.findViewById(R.id.nnf_picker_toolbar);
        if (toolbar != null) {
            setupToolbar(toolbar);
        }
        this.recyclerView = (RecyclerView) inflateRootView.findViewById(android.R.id.list);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(this.layoutManager);
        configureItemDecoration(layoutInflater, this.recyclerView);
        this.mAdapter = new FileItemAdapter<>(this);
        this.recyclerView.setAdapter(this.mAdapter);
        inflateRootView.findViewById(R.id.nnf_button_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.nononsenseapps.filepicker.AbstractFilePickerFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AbstractFilePickerFragment.this.onClickCancel(view);
            }
        });
        inflateRootView.findViewById(R.id.nnf_button_ok).setOnClickListener(new View.OnClickListener() { // from class: com.nononsenseapps.filepicker.AbstractFilePickerFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AbstractFilePickerFragment.this.onClickOk(view);
            }
        });
        inflateRootView.findViewById(R.id.nnf_button_ok_newfile).setOnClickListener(new View.OnClickListener() { // from class: com.nononsenseapps.filepicker.AbstractFilePickerFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AbstractFilePickerFragment.this.onClickOk(view);
            }
        });
        this.mNewFileButtonContainer = inflateRootView.findViewById(R.id.nnf_newfile_button_container);
        this.mRegularButtonContainer = inflateRootView.findViewById(R.id.nnf_button_container);
        this.mEditTextFileName = (EditText) inflateRootView.findViewById(R.id.nnf_text_filename);
        this.mEditTextFileName.addTextChangedListener(new TextWatcher() { // from class: com.nononsenseapps.filepicker.AbstractFilePickerFragment.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                AbstractFilePickerFragment.this.clearSelections();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mCurrentDirView = (TextView) inflateRootView.findViewById(R.id.nnf_current_dir);
        if (this.mCurrentPath != null && this.mCurrentDirView != null) {
            this.mCurrentDirView.setText(getFullPath(this.mCurrentPath));
        }
        return inflateRootView;
    }

    @Override // com.nononsenseapps.filepicker.LogicHandler
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return i != 0 ? i != 2 ? new DirViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.nnf_filepicker_listitem_dir, viewGroup, false)) : new CheckableViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.nnf_filepicker_listitem_checkable, viewGroup, false)) : new HeaderViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.nnf_filepicker_listitem_dir, viewGroup, false));
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<SortedList<T>> loader, SortedList<T> sortedList) {
        this.isLoading = false;
        this.mCheckedItems.clear();
        this.mCheckedVisibleViewHolders.clear();
        this.mFiles = sortedList;
        this.mAdapter.setList(sortedList);
        if (this.mCurrentDirView != null) {
            this.mCurrentDirView.setText(getFullPath(this.mCurrentPath));
        }
        getLoaderManager().destroyLoader(0);
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<SortedList<T>> loader) {
        this.isLoading = false;
    }

    public boolean onLongClickCheckable(@NonNull View view, @NonNull AbstractFilePickerFragment<T>.CheckableViewHolder checkableViewHolder) {
        if (3 == this.mode) {
            this.mEditTextFileName.setText(getName(checkableViewHolder.file));
        }
        onClickCheckBox(checkableViewHolder);
        return true;
    }

    public boolean onLongClickDir(@NonNull View view, @NonNull AbstractFilePickerFragment<T>.DirViewHolder dirViewHolder) {
        return false;
    }

    @Override // android.support.v4.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (R.id.nnf_action_createdir != menuItem.getItemId()) {
            return false;
        }
        FragmentActivity activity = getActivity();
        if (!(activity instanceof AppCompatActivity)) {
            return true;
        }
        NewFolderFragment.showDialog(((AppCompatActivity) activity).getSupportFragmentManager(), this);
        return true;
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(KEY_CURRENT_PATH, this.mCurrentPath.toString());
        bundle.putBoolean(KEY_ALLOW_MULTIPLE, this.allowMultiple);
        bundle.putBoolean(KEY_ALLOW_EXISTING_FILE, this.allowExistingFile);
        bundle.putBoolean(KEY_ALLOW_DIR_CREATE, this.allowCreateDir);
        bundle.putBoolean(KEY_SINGLE_CLICK, this.singleClick);
        bundle.putInt(KEY_MODE, this.mode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refresh(@NonNull T t) {
        if (!hasPermission(t)) {
            handlePermission(t);
            return;
        }
        this.mCurrentPath = t;
        this.isLoading = true;
        getLoaderManager().restartLoader(0, null, this);
    }

    public void setArgs(@Nullable String str, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        if (i == 3 && z) {
            throw new IllegalArgumentException("MODE_NEW_FILE does not support 'allowMultiple'");
        }
        if (z4 && z) {
            throw new IllegalArgumentException("'singleClick' can not be used with 'allowMultiple'");
        }
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        if (str != null) {
            arguments.putString(KEY_START_PATH, str);
        }
        arguments.putBoolean(KEY_ALLOW_DIR_CREATE, z2);
        arguments.putBoolean(KEY_ALLOW_MULTIPLE, z);
        arguments.putBoolean(KEY_ALLOW_EXISTING_FILE, z3);
        arguments.putBoolean(KEY_SINGLE_CLICK, z4);
        arguments.putInt(KEY_MODE, i);
        setArguments(arguments);
    }

    protected void setModeView() {
        boolean z = this.mode == 3;
        this.mNewFileButtonContainer.setVisibility(z ? 0 : 8);
        this.mRegularButtonContainer.setVisibility(z ? 8 : 0);
        if (z || !this.singleClick) {
            return;
        }
        getActivity().findViewById(R.id.nnf_button_ok).setVisibility(8);
    }

    protected void setupToolbar(@NonNull Toolbar toolbar) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @NonNull
    protected List<Uri> toUri(@NonNull Iterable<T> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(toUri((AbstractFilePickerFragment<T>) it.next()));
        }
        return arrayList;
    }
}
