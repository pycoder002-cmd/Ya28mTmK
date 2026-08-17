package com.miguelcatalan.materialsearchview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miguelcatalan.materialsearchview.utils.AnimationUtil;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class MaterialSearchView extends FrameLayout implements Filter.FilterListener {
    public static final int REQUEST_VOICE = 9999;
    private boolean allowVoiceSearch;
    private boolean ellipsize;
    private ListAdapter mAdapter;
    private int mAnimationDuration;
    private ImageButton mBackBtn;
    private boolean mClearingFocus;
    private Context mContext;
    private ImageButton mEmptyBtn;
    private boolean mIsSearchOpen;
    private MenuItem mMenuItem;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener;
    private OnQueryTextListener mOnQueryChangeListener;
    private SavedState mSavedState;
    private View mSearchLayout;
    private EditText mSearchSrcTextView;
    private RelativeLayout mSearchTopBar;
    private SearchViewListener mSearchViewListener;
    private ListView mSuggestionsListView;
    private View mTintView;
    private CharSequence mUserQuery;
    private ImageButton mVoiceBtn;
    private boolean submit;
    private Drawable suggestionIcon;

    /* loaded from: classes.dex */
    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean isSearchOpen;
        String query;

        private SavedState(Parcel parcel) {
            super(parcel);
            this.query = parcel.readString();
            this.isSearchOpen = parcel.readInt() == 1;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.query);
            parcel.writeInt(this.isSearchOpen ? 1 : 0);
        }
    }

    /* loaded from: classes.dex */
    public interface SearchViewListener {
        void onSearchViewClosed();

        void onSearchViewShown();
    }

    public MaterialSearchView(Context context) {
        this(context, null);
    }

    public MaterialSearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mIsSearchOpen = false;
        this.submit = false;
        this.ellipsize = false;
        this.mOnClickListener = new View.OnClickListener() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == MaterialSearchView.this.mBackBtn) {
                    MaterialSearchView.this.closeSearch();
                    return;
                }
                if (view == MaterialSearchView.this.mVoiceBtn) {
                    MaterialSearchView.this.onVoiceClicked();
                    return;
                }
                if (view == MaterialSearchView.this.mEmptyBtn) {
                    MaterialSearchView.this.mSearchSrcTextView.setText((CharSequence) null);
                } else if (view == MaterialSearchView.this.mSearchSrcTextView) {
                    MaterialSearchView.this.showSuggestions();
                } else if (view == MaterialSearchView.this.mTintView) {
                    MaterialSearchView.this.closeSearch();
                }
            }
        };
        this.mContext = context;
        initiateView();
        initStyle(attributeSet, i);
    }

    private void initSearchView() {
        this.mSearchSrcTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                MaterialSearchView.this.onSubmitQuery();
                return true;
            }
        });
        this.mSearchSrcTextView.addTextChangedListener(new TextWatcher() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                MaterialSearchView.this.mUserQuery = charSequence;
                MaterialSearchView.this.startFilter(charSequence);
                MaterialSearchView.this.onTextChanged(charSequence);
            }
        });
        this.mSearchSrcTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    MaterialSearchView.this.showKeyboard(MaterialSearchView.this.mSearchSrcTextView);
                    MaterialSearchView.this.showSuggestions();
                }
            }
        });
    }

    private void initStyle(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.MaterialSearchView, i, 0);
        if (obtainStyledAttributes != null) {
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_searchBackground)) {
                setBackground(obtainStyledAttributes.getDrawable(R.styleable.MaterialSearchView_searchBackground));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_android_textColor)) {
                setTextColor(obtainStyledAttributes.getColor(R.styleable.MaterialSearchView_android_textColor, 0));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_android_textColorHint)) {
                setHintTextColor(obtainStyledAttributes.getColor(R.styleable.MaterialSearchView_android_textColorHint, 0));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_android_hint)) {
                setHint(obtainStyledAttributes.getString(R.styleable.MaterialSearchView_android_hint));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_searchVoiceIcon)) {
                setVoiceIcon(obtainStyledAttributes.getDrawable(R.styleable.MaterialSearchView_searchVoiceIcon));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_searchCloseIcon)) {
                setCloseIcon(obtainStyledAttributes.getDrawable(R.styleable.MaterialSearchView_searchCloseIcon));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_searchBackIcon)) {
                setBackIcon(obtainStyledAttributes.getDrawable(R.styleable.MaterialSearchView_searchBackIcon));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_searchSuggestionBackground)) {
                setSuggestionBackground(obtainStyledAttributes.getDrawable(R.styleable.MaterialSearchView_searchSuggestionBackground));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.MaterialSearchView_searchSuggestionIcon)) {
                setSuggestionIcon(obtainStyledAttributes.getDrawable(R.styleable.MaterialSearchView_searchSuggestionIcon));
            }
            obtainStyledAttributes.recycle();
        }
    }

    private void initiateView() {
        LayoutInflater.from(this.mContext).inflate(R.layout.search_view, (ViewGroup) this, true);
        this.mSearchLayout = findViewById(R.id.search_layout);
        this.mSearchTopBar = (RelativeLayout) this.mSearchLayout.findViewById(R.id.search_top_bar);
        this.mSuggestionsListView = (ListView) this.mSearchLayout.findViewById(R.id.suggestion_list);
        this.mSearchSrcTextView = (EditText) this.mSearchLayout.findViewById(R.id.searchTextView);
        this.mBackBtn = (ImageButton) this.mSearchLayout.findViewById(R.id.action_up_btn);
        this.mVoiceBtn = (ImageButton) this.mSearchLayout.findViewById(R.id.action_voice_btn);
        this.mEmptyBtn = (ImageButton) this.mSearchLayout.findViewById(R.id.action_empty_btn);
        this.mTintView = this.mSearchLayout.findViewById(R.id.transparent_view);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mBackBtn.setOnClickListener(this.mOnClickListener);
        this.mVoiceBtn.setOnClickListener(this.mOnClickListener);
        this.mEmptyBtn.setOnClickListener(this.mOnClickListener);
        this.mTintView.setOnClickListener(this.mOnClickListener);
        this.allowVoiceSearch = false;
        showVoice(true);
        initSearchView();
        this.mSuggestionsListView.setVisibility(8);
        setAnimationDuration(AnimationUtil.ANIMATION_DURATION_MEDIUM);
    }

    private boolean isVoiceAvailable() {
        return isInEditMode() || getContext().getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSubmitQuery() {
        Editable text = this.mSearchSrcTextView.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(text.toString())) {
            closeSearch();
            this.mSearchSrcTextView.setText((CharSequence) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTextChanged(CharSequence charSequence) {
        this.mUserQuery = this.mSearchSrcTextView.getText();
        if (!TextUtils.isEmpty(r0)) {
            this.mEmptyBtn.setVisibility(0);
            showVoice(false);
        } else {
            this.mEmptyBtn.setVisibility(8);
            showVoice(true);
        }
        if (this.mOnQueryChangeListener != null && !TextUtils.equals(charSequence, this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVoiceClicked() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        intent.putExtra("android.speech.extra.MAX_RESULTS", 1);
        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).startActivityForResult(intent, REQUEST_VOICE);
        }
    }

    private void setVisibleWithAnimation() {
        AnimationUtil.AnimationListener animationListener = new AnimationUtil.AnimationListener() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.7
            @Override // com.miguelcatalan.materialsearchview.utils.AnimationUtil.AnimationListener
            public boolean onAnimationCancel(View view) {
                return false;
            }

            @Override // com.miguelcatalan.materialsearchview.utils.AnimationUtil.AnimationListener
            public boolean onAnimationEnd(View view) {
                if (MaterialSearchView.this.mSearchViewListener == null) {
                    return false;
                }
                MaterialSearchView.this.mSearchViewListener.onSearchViewShown();
                return false;
            }

            @Override // com.miguelcatalan.materialsearchview.utils.AnimationUtil.AnimationListener
            public boolean onAnimationStart(View view) {
                return false;
            }
        };
        if (Build.VERSION.SDK_INT < 21) {
            AnimationUtil.fadeInView(this.mSearchLayout, this.mAnimationDuration, animationListener);
        } else {
            this.mSearchLayout.setVisibility(0);
            AnimationUtil.reveal(this.mSearchTopBar, animationListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFilter(CharSequence charSequence) {
        if (this.mAdapter == null || !(this.mAdapter instanceof Filterable)) {
            return;
        }
        ((Filterable) this.mAdapter).getFilter().filter(charSequence, this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.mClearingFocus = true;
        hideKeyboard(this);
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mClearingFocus = false;
    }

    public void closeSearch() {
        if (isSearchOpen()) {
            this.mSearchSrcTextView.setText((CharSequence) null);
            dismissSuggestions();
            clearFocus();
            this.mSearchLayout.setVisibility(8);
            if (this.mSearchViewListener != null) {
                this.mSearchViewListener.onSearchViewClosed();
            }
            this.mIsSearchOpen = false;
        }
    }

    public void dismissSuggestions() {
        if (this.mSuggestionsListView.getVisibility() == 0) {
            this.mSuggestionsListView.setVisibility(8);
        }
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean isSearchOpen() {
        return this.mIsSearchOpen;
    }

    @Override // android.widget.Filter.FilterListener
    public void onFilterComplete(int i) {
        if (i > 0) {
            showSuggestions();
        } else {
            dismissSuggestions();
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        this.mSavedState = (SavedState) parcelable;
        if (this.mSavedState.isSearchOpen) {
            showSearch(false);
            setQuery(this.mSavedState.query, false);
        }
        super.onRestoreInstanceState(this.mSavedState.getSuperState());
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        this.mSavedState = new SavedState(super.onSaveInstanceState());
        this.mSavedState.query = this.mUserQuery != null ? this.mUserQuery.toString() : null;
        this.mSavedState.isSearchOpen = this.mIsSearchOpen;
        return this.mSavedState;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i, Rect rect) {
        if (!this.mClearingFocus && isFocusable()) {
            return this.mSearchSrcTextView.requestFocus(i, rect);
        }
        return false;
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        this.mSuggestionsListView.setAdapter(listAdapter);
        startFilter(this.mSearchSrcTextView.getText());
    }

    public void setAnimationDuration(int i) {
        this.mAnimationDuration = i;
    }

    public void setBackIcon(Drawable drawable) {
        this.mBackBtn.setImageDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mSearchTopBar.setBackground(drawable);
        } else {
            this.mSearchTopBar.setBackgroundDrawable(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mSearchTopBar.setBackgroundColor(i);
    }

    public void setCloseIcon(Drawable drawable) {
        this.mEmptyBtn.setImageDrawable(drawable);
    }

    public void setCursorDrawable(int i) {
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            declaredField.set(this.mSearchSrcTextView, Integer.valueOf(i));
        } catch (Exception e) {
            Log.e("MaterialSearchView", e.toString());
        }
    }

    public void setEllipsize(boolean z) {
        this.ellipsize = z;
    }

    public void setHint(CharSequence charSequence) {
        this.mSearchSrcTextView.setHint(charSequence);
    }

    public void setHintTextColor(int i) {
        this.mSearchSrcTextView.setHintTextColor(i);
    }

    public void setMenuItem(MenuItem menuItem) {
        this.mMenuItem = menuItem;
        this.mMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.6
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem2) {
                MaterialSearchView.this.showSearch();
                return true;
            }
        });
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mSuggestionsListView.setOnItemClickListener(onItemClickListener);
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnSearchViewListener(SearchViewListener searchViewListener) {
        this.mSearchViewListener = searchViewListener;
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            this.mSearchSrcTextView.setSelection(this.mSearchSrcTextView.length());
            this.mUserQuery = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        onSubmitQuery();
    }

    public void setSubmitOnClick(boolean z) {
        this.submit = z;
    }

    public void setSuggestionBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mSuggestionsListView.setBackground(drawable);
        } else {
            this.mSuggestionsListView.setBackgroundDrawable(drawable);
        }
    }

    public void setSuggestionIcon(Drawable drawable) {
        this.suggestionIcon = drawable;
    }

    public void setSuggestions(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            this.mTintView.setVisibility(8);
            return;
        }
        this.mTintView.setVisibility(0);
        final SearchAdapter searchAdapter = new SearchAdapter(this.mContext, strArr, this.suggestionIcon, this.ellipsize);
        setAdapter(searchAdapter);
        setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.miguelcatalan.materialsearchview.MaterialSearchView.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MaterialSearchView.this.setQuery((String) searchAdapter.getItem(i), MaterialSearchView.this.submit);
            }
        });
    }

    public void setTextColor(int i) {
        this.mSearchSrcTextView.setTextColor(i);
    }

    public void setVoiceIcon(Drawable drawable) {
        this.mVoiceBtn.setImageDrawable(drawable);
    }

    public void setVoiceSearch(boolean z) {
        this.allowVoiceSearch = z;
    }

    public void showKeyboard(View view) {
        if (Build.VERSION.SDK_INT <= 10 && view.hasFocus()) {
            view.clearFocus();
        }
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public void showSearch() {
        showSearch(true);
    }

    public void showSearch(boolean z) {
        if (isSearchOpen()) {
            return;
        }
        this.mSearchSrcTextView.setText((CharSequence) null);
        this.mSearchSrcTextView.requestFocus();
        if (z) {
            setVisibleWithAnimation();
        } else {
            this.mSearchLayout.setVisibility(0);
            if (this.mSearchViewListener != null) {
                this.mSearchViewListener.onSearchViewShown();
            }
        }
        this.mIsSearchOpen = true;
    }

    public void showSuggestions() {
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0 || this.mSuggestionsListView.getVisibility() != 8) {
            return;
        }
        this.mSuggestionsListView.setVisibility(0);
    }

    public void showVoice(boolean z) {
        if (z && isVoiceAvailable() && this.allowVoiceSearch) {
            this.mVoiceBtn.setVisibility(0);
        } else {
            this.mVoiceBtn.setVisibility(8);
        }
    }
}
