package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zzsd extends Fragment implements zzrp {
    private static WeakHashMap<FragmentActivity, WeakReference<zzsd>> Bg = new WeakHashMap<>();
    private Bundle Bi;
    private Map<String, zzro> Bh = new ArrayMap();
    private int zzbtt = 0;

    public static zzsd zza(FragmentActivity fragmentActivity) {
        zzsd zzsdVar;
        WeakReference<zzsd> weakReference = Bg.get(fragmentActivity);
        if (weakReference != null && (zzsdVar = weakReference.get()) != null) {
            return zzsdVar;
        }
        try {
            zzsd zzsdVar2 = (zzsd) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (zzsdVar2 == null || zzsdVar2.isRemoving()) {
                zzsdVar2 = new zzsd();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add(zzsdVar2, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            Bg.put(fragmentActivity, new WeakReference<>(zzsdVar2));
            return zzsdVar2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    private void zzb(final String str, @NonNull final zzro zzroVar) {
        if (this.zzbtt > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.zzsd.1
                @Override // java.lang.Runnable
                public void run() {
                    if (zzsd.this.zzbtt >= 1) {
                        zzroVar.onCreate(zzsd.this.Bi != null ? zzsd.this.Bi.getBundle(str) : null);
                    }
                    if (zzsd.this.zzbtt >= 2) {
                        zzroVar.onStart();
                    }
                    if (zzsd.this.zzbtt >= 3) {
                        zzroVar.onStop();
                    }
                    if (zzsd.this.zzbtt >= 4) {
                        zzroVar.onDestroy();
                    }
                }
            });
        }
    }

    @Override // android.support.v4.app.Fragment
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(i, i2, intent);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbtt = 1;
        this.Bi = bundle;
        for (Map.Entry<String, zzro> entry : this.Bh.entrySet()) {
            entry.getValue().onCreate(bundle != null ? bundle.getBundle(entry.getKey()) : null);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.zzbtt = 4;
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    @Override // android.support.v4.app.Fragment
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

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.zzbtt = 2;
        Iterator<zzro> it = this.Bh.values().iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    @Override // android.support.v4.app.Fragment
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
    /* renamed from: zzaub, reason: merged with bridge method [inline-methods] */
    public FragmentActivity zzaty() {
        return getActivity();
    }
}
