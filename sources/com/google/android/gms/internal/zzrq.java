package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@TargetApi(11)
/* loaded from: classes.dex */
public final class zzrq extends Fragment implements zzrp {
    private static WeakHashMap<Activity, WeakReference<zzrq>> Bg = new WeakHashMap<>();
    private Bundle Bi;
    private Map<String, zzro> Bh = new ArrayMap();
    private int zzbtt = 0;

    private void zzb(final String str, @NonNull final zzro zzroVar) {
        if (this.zzbtt > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.zzrq.1
                @Override // java.lang.Runnable
                public void run() {
                    if (zzrq.this.zzbtt >= 1) {
                        zzroVar.onCreate(zzrq.this.Bi != null ? zzrq.this.Bi.getBundle(str) : null);
                    }
                    if (zzrq.this.zzbtt >= 2) {
                        zzroVar.onStart();
                    }
                    if (zzrq.this.zzbtt >= 3) {
                        zzroVar.onStop();
                    }
                    if (zzrq.this.zzbtt >= 4) {
                        zzroVar.onDestroy();
                    }
                }
            });
        }
    }

    public static zzrq zzt(Activity activity) {
        zzrq zzrqVar;
        WeakReference<zzrq> weakReference = Bg.get(activity);
        if (weakReference != null && (zzrqVar = weakReference.get()) != null) {
            return zzrqVar;
        }
        try {
            zzrq zzrqVar2 = (zzrq) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
            if (zzrqVar2 == null || zzrqVar2.isRemoving()) {
                zzrqVar2 = new zzrq();
                activity.getFragmentManager().beginTransaction().add(zzrqVar2, "LifecycleFragmentImpl").commitAllowingStateLoss();
            }
            Bg.put(activity, new WeakReference<>(zzrqVar2));
            return zzrqVar2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e);
        }
    }

    @Override // android.app.Fragment
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbtt = 1;
        this.Bi = bundle;
        for (Map.Entry<String, zzro> entry : this.Bh.entrySet()) {
            entry.getValue().onCreate(bundle != null ? bundle.getBundle(entry.getKey()) : null);
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.zzbtt = 4;
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry<String, zzro> entry : this.Bh.entrySet()) {
            Bundle bundle2 = new Bundle();
            entry.getValue().onSaveInstanceState(bundle2);
            bundle.putBundle(entry.getKey(), bundle2);
        }
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.zzbtt = 2;
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.zzbtt = 3;
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }

    @Override // com.google.android.gms.internal.zzrp
    public <T extends zzro> T zza(String str, Class<T> cls) {
        return cls.cast(this.Bh.get(str));
    }

    @Override // com.google.android.gms.internal.zzrp
    public void zza(String str, @NonNull zzro zzroVar) {
        if (!this.Bh.containsKey(str)) {
            this.Bh.put(str, zzroVar);
            zzb(str, zzroVar);
        } else {
            StringBuilder sb = new StringBuilder(59 + String.valueOf(str).length());
            sb.append("LifecycleCallback with tag ");
            sb.append(str);
            sb.append(" already added to this fragment.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.zzrp
    public Activity zzaty() {
        return getActivity();
    }
}
