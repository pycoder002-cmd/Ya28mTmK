package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

@KeepName
/* loaded from: classes.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zze();
    private static final zza Cf = new zza(new String[0], null) { // from class: com.google.android.gms.common.data.DataHolder.1
        @Override // com.google.android.gms.common.data.DataHolder.zza
        public zza zza(ContentValues contentValues) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }

        @Override // com.google.android.gms.common.data.DataHolder.zza
        public zza zzb(HashMap<String, Object> hashMap) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }
    };
    private final String[] BY;
    Bundle BZ;
    private final CursorWindow[] Ca;
    private final Bundle Cb;
    int[] Cc;
    int Cd;
    private boolean Ce;
    boolean mClosed;
    final int mVersionCode;
    private final int uo;

    /* loaded from: classes.dex */
    public static class zza {
        private final String[] BY;
        private final ArrayList<HashMap<String, Object>> Cg;
        private final String Ch;
        private final HashMap<Object, Integer> Ci;
        private boolean Cj;
        private String Ck;

        private zza(String[] strArr, String str) {
            this.BY = (String[]) zzaa.zzy(strArr);
            this.Cg = new ArrayList<>();
            this.Ch = str;
            this.Ci = new HashMap<>();
            this.Cj = false;
            this.Ck = null;
        }

        private int zzc(HashMap<String, Object> hashMap) {
            Object obj;
            if (this.Ch == null || (obj = hashMap.get(this.Ch)) == null) {
                return -1;
            }
            Integer num = this.Ci.get(obj);
            if (num != null) {
                return num.intValue();
            }
            this.Ci.put(obj, Integer.valueOf(this.Cg.size()));
            return -1;
        }

        public zza zza(ContentValues contentValues) {
            com.google.android.gms.common.internal.zzc.zzu(contentValues);
            HashMap<String, Object> hashMap = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return zzb(hashMap);
        }

        public zza zzb(HashMap<String, Object> hashMap) {
            com.google.android.gms.common.internal.zzc.zzu(hashMap);
            int zzc = zzc(hashMap);
            if (zzc == -1) {
                this.Cg.add(hashMap);
            } else {
                this.Cg.remove(zzc);
                this.Cg.add(zzc, hashMap);
            }
            this.Cj = false;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DataHolder zzgc(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }
    }

    /* loaded from: classes.dex */
    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.Ce = true;
        this.mVersionCode = i;
        this.BY = strArr;
        this.Ca = cursorWindowArr;
        this.uo = i2;
        this.Cb = bundle;
    }

    private DataHolder(zza zzaVar, int i, Bundle bundle) {
        this(zzaVar.BY, zza(zzaVar, -1), i, bundle);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.Ce = true;
        this.mVersionCode = 1;
        this.BY = (String[]) zzaa.zzy(strArr);
        this.Ca = (CursorWindow[]) zzaa.zzy(cursorWindowArr);
        this.uo = i;
        this.Cb = bundle;
        zzaun();
    }

    public static DataHolder zza(int i, Bundle bundle) {
        return new DataHolder(Cf, i, bundle);
    }

    private static CursorWindow[] zza(zza zzaVar, int i) {
        long j;
        if (zzaVar.BY.length == 0) {
            return new CursorWindow[0];
        }
        List subList = (i < 0 || i >= zzaVar.Cg.size()) ? zzaVar.Cg : zzaVar.Cg.subList(0, i);
        int size = subList.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(zzaVar.BY.length);
        boolean z = false;
        CursorWindow cursorWindow2 = cursorWindow;
        int i2 = 0;
        while (i2 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    StringBuilder sb = new StringBuilder(72);
                    sb.append("Allocating additional cursor window for large data set (row ");
                    sb.append(i2);
                    sb.append(")");
                    Log.d("DataHolder", sb.toString());
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(zzaVar.BY.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) subList.get(i2);
                boolean z2 = true;
                for (int i3 = 0; i3 < zzaVar.BY.length && z2; i3++) {
                    String str = zzaVar.BY[i3];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z2 = cursorWindow2.putNull(i2, i3);
                    } else if (obj instanceof String) {
                        z2 = cursorWindow2.putString((String) obj, i2, i3);
                    } else {
                        if (obj instanceof Long) {
                            j = ((Long) obj).longValue();
                        } else if (obj instanceof Integer) {
                            z2 = cursorWindow2.putLong(((Integer) obj).intValue(), i2, i3);
                        } else if (obj instanceof Boolean) {
                            j = ((Boolean) obj).booleanValue() ? 1L : 0L;
                        } else if (obj instanceof byte[]) {
                            z2 = cursorWindow2.putBlob((byte[]) obj, i2, i3);
                        } else if (obj instanceof Double) {
                            z2 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i2, i3);
                        } else {
                            if (!(obj instanceof Float)) {
                                String valueOf = String.valueOf(obj);
                                StringBuilder sb2 = new StringBuilder(32 + String.valueOf(str).length() + String.valueOf(valueOf).length());
                                sb2.append("Unsupported object for column ");
                                sb2.append(str);
                                sb2.append(": ");
                                sb2.append(valueOf);
                                throw new IllegalArgumentException(sb2.toString());
                            }
                            z2 = cursorWindow2.putDouble(((Float) obj).floatValue(), i2, i3);
                        }
                        z2 = cursorWindow2.putLong(j, i2, i3);
                    }
                }
                if (z2) {
                    z = false;
                } else {
                    if (z) {
                        throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                    }
                    StringBuilder sb3 = new StringBuilder(74);
                    sb3.append("Couldn't populate window data for row ");
                    sb3.append(i2);
                    sb3.append(" - allocating new window.");
                    Log.d("DataHolder", sb3.toString());
                    cursorWindow2.freeLastRow();
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(zzaVar.BY.length);
                    arrayList.add(cursorWindow2);
                    i2--;
                    z = true;
                }
                i2++;
            } catch (RuntimeException e) {
                int size2 = arrayList.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    ((CursorWindow) arrayList.get(i4)).close();
                }
                throw e;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zza zzc(String[] strArr) {
        return new zza(strArr, null);
    }

    public static DataHolder zzgb(int i) {
        return zza(i, (Bundle) null);
    }

    private void zzi(String str, int i) {
        if (this.BZ == null || !this.BZ.containsKey(str)) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "No such column: ".concat(valueOf) : new String("No such column: "));
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.Cd) {
            throw new CursorIndexOutOfBoundsException(i, this.Cd);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.Ca.length; i++) {
                    this.Ca[i].close();
                }
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (this.Ce && this.Ca.length > 0 && !isClosed()) {
                close();
                String valueOf = String.valueOf(toString());
                StringBuilder sb = new StringBuilder(Opcodes.GETSTATIC + String.valueOf(valueOf).length());
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(valueOf);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        return this.Cd;
    }

    public int getStatusCode() {
        return this.uo;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzi(str, i);
        this.Ca[i2].copyStringToBuffer(i, this.BZ.getInt(str), charArrayBuffer);
    }

    public Bundle zzaui() {
        return this.Cb;
    }

    public void zzaun() {
        this.BZ = new Bundle();
        for (int i = 0; i < this.BY.length; i++) {
            this.BZ.putInt(this.BY[i], i);
        }
        this.Cc = new int[this.Ca.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.Ca.length; i3++) {
            this.Cc[i3] = i2;
            i2 += this.Ca[i3].getNumRows() - (i2 - this.Ca[i3].getStartPosition());
        }
        this.Cd = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] zzauo() {
        return this.BY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CursorWindow[] zzaup() {
        return this.Ca;
    }

    public long zzb(String str, int i, int i2) {
        zzi(str, i);
        return this.Ca[i2].getLong(i, this.BZ.getInt(str));
    }

    public int zzc(String str, int i, int i2) {
        zzi(str, i);
        return this.Ca[i2].getInt(i, this.BZ.getInt(str));
    }

    public String zzd(String str, int i, int i2) {
        zzi(str, i);
        return this.Ca[i2].getString(i, this.BZ.getInt(str));
    }

    public boolean zze(String str, int i, int i2) {
        zzi(str, i);
        return Long.valueOf(this.Ca[i2].getLong(i, this.BZ.getInt(str))).longValue() == 1;
    }

    public float zzf(String str, int i, int i2) {
        zzi(str, i);
        return this.Ca[i2].getFloat(i, this.BZ.getInt(str));
    }

    public byte[] zzg(String str, int i, int i2) {
        zzi(str, i);
        return this.Ca[i2].getBlob(i, this.BZ.getInt(str));
    }

    public int zzga(int i) {
        int i2 = 0;
        zzaa.zzbs(i >= 0 && i < this.Cd);
        while (true) {
            if (i2 >= this.Cc.length) {
                break;
            }
            if (i < this.Cc[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.Cc.length ? i2 - 1 : i2;
    }

    public Uri zzh(String str, int i, int i2) {
        String zzd = zzd(str, i, i2);
        if (zzd == null) {
            return null;
        }
        return Uri.parse(zzd);
    }

    public boolean zzho(String str) {
        return this.BZ.containsKey(str);
    }

    public boolean zzi(String str, int i, int i2) {
        zzi(str, i);
        return this.Ca[i2].isNull(i, this.BZ.getInt(str));
    }
}
