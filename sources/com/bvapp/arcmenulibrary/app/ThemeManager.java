package com.bvapp.arcmenulibrary.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.SparseArray;
import com.bvapp.arcmenulibrary.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ThemeManager {
    private static final String KEY_THEME = "theme";
    private static final String PREF = "theme.pref";
    public static final int THEME_UNDEFINED = Integer.MIN_VALUE;
    private static volatile ThemeManager mInstance;
    private Context mContext;
    private int mCurrentTheme;
    private EventDispatcher mDispatcher;
    private SparseArray<int[]> mStyles = new SparseArray<>();
    private int mThemeCount;

    /* loaded from: classes.dex */
    public interface EventDispatcher {
        void dispatchThemeChanged(int i);

        void registerListener(OnThemeChangedListener onThemeChangedListener);

        void unregisterListener(OnThemeChangedListener onThemeChangedListener);
    }

    /* loaded from: classes.dex */
    public static class OnThemeChangedEvent {
        public final int theme;

        public OnThemeChangedEvent(int i) {
            this.theme = i;
        }
    }

    /* loaded from: classes.dex */
    public interface OnThemeChangedListener {
        void onThemeChanged(@Nullable OnThemeChangedEvent onThemeChangedEvent);
    }

    /* loaded from: classes.dex */
    public static class SimpleDispatcher implements EventDispatcher {
        ArrayList<WeakReference<OnThemeChangedListener>> mListeners = new ArrayList<>();

        @Override // com.bvapp.arcmenulibrary.app.ThemeManager.EventDispatcher
        public void dispatchThemeChanged(int i) {
            OnThemeChangedEvent onThemeChangedEvent = new OnThemeChangedEvent(i);
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                WeakReference<OnThemeChangedListener> weakReference = this.mListeners.get(size);
                if (weakReference.get() == null) {
                    this.mListeners.remove(size);
                } else {
                    weakReference.get().onThemeChanged(onThemeChangedEvent);
                }
            }
        }

        @Override // com.bvapp.arcmenulibrary.app.ThemeManager.EventDispatcher
        public void registerListener(OnThemeChangedListener onThemeChangedListener) {
            boolean z = false;
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                WeakReference<OnThemeChangedListener> weakReference = this.mListeners.get(size);
                if (weakReference.get() == null) {
                    this.mListeners.remove(size);
                } else if (weakReference.get() == onThemeChangedListener) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            this.mListeners.add(new WeakReference<>(onThemeChangedListener));
        }

        @Override // com.bvapp.arcmenulibrary.app.ThemeManager.EventDispatcher
        public void unregisterListener(OnThemeChangedListener onThemeChangedListener) {
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                WeakReference<OnThemeChangedListener> weakReference = this.mListeners.get(size);
                if (weakReference.get() == null || weakReference.get() == onThemeChangedListener) {
                    this.mListeners.remove(size);
                }
            }
        }
    }

    private void dispatchThemeChanged(int i) {
        if (this.mDispatcher != null) {
            this.mDispatcher.dispatchThemeChanged(i);
        }
    }

    public static ThemeManager getInstance() {
        if (mInstance == null) {
            synchronized (ThemeManager.class) {
                if (mInstance == null) {
                    mInstance = new ThemeManager();
                }
            }
        }
        return mInstance;
    }

    private SharedPreferences getSharedPreferences(Context context) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(PREF, 0);
    }

    public static int getStyleId(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ThemableView, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.ThemableView_v_styleId, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private int[] getStyleList(int i) {
        if (this.mStyles == null) {
            return null;
        }
        int[] iArr = this.mStyles.get(i);
        if (iArr != null) {
            return iArr;
        }
        int[] loadStyleList = loadStyleList(this.mContext, i);
        this.mStyles.put(i, loadStyleList);
        return loadStyleList;
    }

    public static void init(Context context, int i, int i2, @Nullable EventDispatcher eventDispatcher) {
        getInstance().setup(context, i, i2, eventDispatcher);
    }

    private int[] loadStyleList(Context context, int i) {
        if (context == null) {
            return null;
        }
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(i);
        int[] iArr = new int[obtainTypedArray.length()];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = obtainTypedArray.getResourceId(i2, 0);
        }
        obtainTypedArray.recycle();
        return iArr;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getCurrentStyle(int i) {
        return getStyle(i, this.mCurrentTheme);
    }

    @UiThread
    public int getCurrentTheme() {
        return this.mCurrentTheme;
    }

    public int getStyle(int i, int i2) {
        int[] styleList = getStyleList(i);
        if (styleList == null) {
            return 0;
        }
        return styleList[i2];
    }

    public int getThemeCount() {
        return this.mThemeCount;
    }

    public void registerOnThemeChangedListener(@NonNull OnThemeChangedListener onThemeChangedListener) {
        if (this.mDispatcher != null) {
            this.mDispatcher.registerListener(onThemeChangedListener);
        }
    }

    public boolean setCurrentTheme(int i) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread() || this.mCurrentTheme == i) {
            return false;
        }
        this.mCurrentTheme = i;
        SharedPreferences sharedPreferences = getSharedPreferences(this.mContext);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(KEY_THEME, this.mCurrentTheme).apply();
        }
        dispatchThemeChanged(this.mCurrentTheme);
        return true;
    }

    protected void setup(Context context, int i, int i2, @Nullable EventDispatcher eventDispatcher) {
        this.mContext = context;
        if (eventDispatcher == null) {
            eventDispatcher = new SimpleDispatcher();
        }
        this.mDispatcher = eventDispatcher;
        this.mThemeCount = i;
        SharedPreferences sharedPreferences = getSharedPreferences(this.mContext);
        if (sharedPreferences != null) {
            this.mCurrentTheme = sharedPreferences.getInt(KEY_THEME, i2);
        } else {
            this.mCurrentTheme = i2;
        }
        if (this.mCurrentTheme >= this.mThemeCount) {
            setCurrentTheme(i2);
        }
    }

    public void unregisterOnThemeChangedListener(@NonNull OnThemeChangedListener onThemeChangedListener) {
        if (this.mDispatcher != null) {
            this.mDispatcher.unregisterListener(onThemeChangedListener);
        }
    }
}
