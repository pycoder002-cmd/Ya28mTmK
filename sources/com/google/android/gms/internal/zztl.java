package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.zztm;
import com.google.android.gms.internal.zztn;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zztl {
    private static zztm Qh;
    private static String Qj;
    private final Context Qr;
    private static final HashMap<String, byte[]> Qi = new HashMap<>();
    private static final zzb.zza Qk = new zzb.zza() { // from class: com.google.android.gms.internal.zztl.1
        @Override // com.google.android.gms.internal.zztl.zzb.zza
        public zztl zza(Context context, String str, int i) throws zza {
            return zztl.zza(context, str, i);
        }

        @Override // com.google.android.gms.internal.zztl.zzb.zza
        public int zzaa(Context context, String str) {
            return zztl.zzaa(context, str);
        }

        @Override // com.google.android.gms.internal.zztl.zzb.zza
        public int zzb(Context context, String str, boolean z) throws zza {
            return zztl.zzb(context, str, z);
        }
    };
    private static final zzb.zza Ql = new zzb.zza() { // from class: com.google.android.gms.internal.zztl.2
        @Override // com.google.android.gms.internal.zztl.zzb.zza
        public zztl zza(Context context, String str, int i) throws zza {
            return zztl.zzb(context, str, i);
        }

        @Override // com.google.android.gms.internal.zztl.zzb.zza
        public int zzaa(Context context, String str) {
            return zztl.zzaa(context, str);
        }

        @Override // com.google.android.gms.internal.zztl.zzb.zza
        public int zzb(Context context, String str, boolean z) throws zza {
            return zztl.zzc(context, str, z);
        }
    };
    public static final zzb Qm = new zzb() { // from class: com.google.android.gms.internal.zztl.3
        @Override // com.google.android.gms.internal.zztl.zzb
        public zzb.C0036zzb zza(Context context, String str, zzb.zza zzaVar) throws zza {
            zzb.C0036zzb c0036zzb = new zzb.C0036zzb();
            c0036zzb.Qu = zzaVar.zzb(context, str, true);
            if (c0036zzb.Qu != 0) {
                c0036zzb.Qv = 1;
                return c0036zzb;
            }
            c0036zzb.Qt = zzaVar.zzaa(context, str);
            if (c0036zzb.Qt != 0) {
                c0036zzb.Qv = -1;
            }
            return c0036zzb;
        }
    };
    public static final zzb Qn = new zzb() { // from class: com.google.android.gms.internal.zztl.4
        @Override // com.google.android.gms.internal.zztl.zzb
        public zzb.C0036zzb zza(Context context, String str, zzb.zza zzaVar) throws zza {
            zzb.C0036zzb c0036zzb = new zzb.C0036zzb();
            c0036zzb.Qt = zzaVar.zzaa(context, str);
            if (c0036zzb.Qt != 0) {
                c0036zzb.Qv = -1;
                return c0036zzb;
            }
            c0036zzb.Qu = zzaVar.zzb(context, str, true);
            if (c0036zzb.Qu != 0) {
                c0036zzb.Qv = 1;
            }
            return c0036zzb;
        }
    };
    public static final zzb Qo = new zzb() { // from class: com.google.android.gms.internal.zztl.5
        @Override // com.google.android.gms.internal.zztl.zzb
        public zzb.C0036zzb zza(Context context, String str, zzb.zza zzaVar) throws zza {
            int i;
            zzb.C0036zzb c0036zzb = new zzb.C0036zzb();
            c0036zzb.Qt = zzaVar.zzaa(context, str);
            c0036zzb.Qu = zzaVar.zzb(context, str, true);
            if (c0036zzb.Qt == 0 && c0036zzb.Qu == 0) {
                i = 0;
            } else {
                if (c0036zzb.Qt < c0036zzb.Qu) {
                    c0036zzb.Qv = 1;
                    return c0036zzb;
                }
                i = -1;
            }
            c0036zzb.Qv = i;
            return c0036zzb;
        }
    };
    public static final zzb Qp = new zzb() { // from class: com.google.android.gms.internal.zztl.6
        /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
        @Override // com.google.android.gms.internal.zztl.zzb
        public zzb.C0036zzb zza(Context context, String str, zzb.zza zzaVar) throws zza {
            int i;
            zzb.C0036zzb c0036zzb = new zzb.C0036zzb();
            c0036zzb.Qt = zzaVar.zzaa(context, str);
            c0036zzb.Qu = zzaVar.zzb(context, str, true);
            if (c0036zzb.Qt == 0 && c0036zzb.Qu == 0) {
                i = 0;
            } else {
                if (c0036zzb.Qu >= c0036zzb.Qt) {
                    c0036zzb.Qv = 1;
                    return c0036zzb;
                }
                i = -1;
            }
            c0036zzb.Qv = i;
            return c0036zzb;
        }
    };
    public static final zzb Qq = new zzb() { // from class: com.google.android.gms.internal.zztl.7
        @Override // com.google.android.gms.internal.zztl.zzb
        public zzb.C0036zzb zza(Context context, String str, zzb.zza zzaVar) throws zza {
            zzb.C0036zzb c0036zzb = new zzb.C0036zzb();
            c0036zzb.Qt = zzaVar.zzaa(context, str);
            c0036zzb.Qu = c0036zzb.Qt != 0 ? zzaVar.zzb(context, str, false) : zzaVar.zzb(context, str, true);
            if (c0036zzb.Qt == 0 && c0036zzb.Qu == 0) {
                c0036zzb.Qv = 0;
                return c0036zzb;
            }
            if (c0036zzb.Qu >= c0036zzb.Qt) {
                c0036zzb.Qv = 1;
                return c0036zzb;
            }
            c0036zzb.Qv = -1;
            return c0036zzb;
        }
    };

    /* loaded from: classes.dex */
    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes.dex */
    public interface zzb {

        /* loaded from: classes.dex */
        public interface zza {
            zztl zza(Context context, String str, int i) throws zza;

            int zzaa(Context context, String str);

            int zzb(Context context, String str, boolean z) throws zza;
        }

        /* renamed from: com.google.android.gms.internal.zztl$zzb$zzb, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0036zzb {
            public int Qt = 0;
            public int Qu = 0;
            public int Qv = 0;
        }

        C0036zzb zza(Context context, String str, zza zzaVar) throws zza;
    }

    private zztl(Context context) {
        this.Qr = (Context) com.google.android.gms.common.internal.zzaa.zzy(context);
    }

    private static Context zza(Context context, String str, byte[] bArr, String str2) {
        if (str2 == null || str2.isEmpty()) {
            Log.e("DynamiteModule", "No valid DynamiteLoader APK path");
            return null;
        }
        try {
            return (Context) com.google.android.gms.dynamic.zze.zzae(zztn.zza.zzff((IBinder) new PathClassLoader(str2, context.getClassLoader()) { // from class: com.google.android.gms.internal.zztl.9
                @Override // java.lang.ClassLoader
                protected Class<?> loadClass(String str3, boolean z) throws ClassNotFoundException {
                    if (!str3.startsWith("java.") && !str3.startsWith("android.")) {
                        try {
                            return findClass(str3);
                        } catch (ClassNotFoundException unused) {
                        }
                    }
                    return super.loadClass(str3, z);
                }
            }.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0])).zza(com.google.android.gms.dynamic.zze.zzac(context), str, bArr));
        } catch (RemoteException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            String valueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(valueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    public static zztl zza(Context context, zzb zzbVar, String str) throws zza {
        return zza(context, zzbVar, str, Qk);
    }

    public static zztl zza(Context context, zzb zzbVar, String str, zzb.zza zzaVar) throws zza {
        zzb.C0036zzb zza2 = zzbVar.zza(context, str, zzaVar);
        int i = zza2.Qt;
        int i2 = zza2.Qu;
        StringBuilder sb = new StringBuilder(68 + String.valueOf(str).length() + String.valueOf(str).length());
        sb.append("Considering local module ");
        sb.append(str);
        sb.append(":");
        sb.append(i);
        sb.append(" and remote module ");
        sb.append(str);
        sb.append(":");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        if (zza2.Qv == 0 || ((zza2.Qv == -1 && zza2.Qt == 0) || (zza2.Qv == 1 && zza2.Qu == 0))) {
            int i3 = zza2.Qt;
            int i4 = zza2.Qu;
            StringBuilder sb2 = new StringBuilder(91);
            sb2.append("No acceptable module found. Local version is ");
            sb2.append(i3);
            sb2.append(" and remote version is ");
            sb2.append(i4);
            sb2.append(".");
            throw new zza(sb2.toString());
        }
        if (zza2.Qv == -1) {
            return zzac(context, str);
        }
        if (zza2.Qv != 1) {
            int i5 = zza2.Qv;
            StringBuilder sb3 = new StringBuilder(47);
            sb3.append("VersionPolicy returned invalid code:");
            sb3.append(i5);
            throw new zza(sb3.toString());
        }
        try {
            return zzaVar.zza(context, str, zza2.Qu);
        } catch (zza e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
            if (zza2.Qt != 0) {
                final int i6 = zza2.Qt;
                if (zzbVar.zza(context, str, new zzb.zza() { // from class: com.google.android.gms.internal.zztl.8
                    @Override // com.google.android.gms.internal.zztl.zzb.zza
                    public zztl zza(Context context2, String str2, int i7) throws zza {
                        throw new zza("local only VersionPolicy should not load from remote");
                    }

                    @Override // com.google.android.gms.internal.zztl.zzb.zza
                    public int zzaa(Context context2, String str2) {
                        return i6;
                    }

                    @Override // com.google.android.gms.internal.zztl.zzb.zza
                    public int zzb(Context context2, String str2, boolean z) {
                        return 0;
                    }
                }).Qv == -1) {
                    return zzac(context, str);
                }
            }
            throw new zza("Remote load failed. No local fallback found.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zztl zza(Context context, String str, int i) throws zza {
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        zztm zzcs = zzcs(context);
        if (zzcs == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            com.google.android.gms.dynamic.zzd zza2 = zzcs.zza(com.google.android.gms.dynamic.zze.zzac(context), str, i);
            if (com.google.android.gms.dynamic.zze.zzae(zza2) == null) {
                throw new zza("Failed to load remote module.");
            }
            return new zztl((Context) com.google.android.gms.dynamic.zze.zzae(zza2));
        } catch (RemoteException e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    public static int zzaa(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            String valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            String valueOf2 = String.valueOf("ModuleDescriptor");
            StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(str).length() + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append(str);
            sb.append(".");
            sb.append(valueOf2);
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf3 = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(51 + String.valueOf(valueOf3).length() + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf3);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(45 + String.valueOf(str).length());
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String valueOf4 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", valueOf4.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf4) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zzab(Context context, String str) {
        return zzb(context, str, false);
    }

    private static zztl zzac(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new zztl(context.getApplicationContext());
    }

    public static int zzb(Context context, String str, boolean z) {
        zztm zzcs = zzcs(context);
        if (zzcs == null) {
            return 0;
        }
        try {
            return zzcs.zza(com.google.android.gms.dynamic.zze.zzac(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zztl zzb(Context context, String str, int i) throws zza {
        byte[] bArr;
        String str2;
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (zztl.class) {
            HashMap<String, byte[]> hashMap = Qi;
            StringBuilder sb2 = new StringBuilder(12 + String.valueOf(str).length());
            sb2.append(str);
            sb2.append(":");
            sb2.append(i);
            bArr = hashMap.get(sb2.toString());
            str2 = Qj;
        }
        if (bArr == null) {
            throw new zza("Module implementation could not be found.");
        }
        Context zza2 = zza(context.getApplicationContext(), str, bArr, str2);
        if (zza2 == null) {
            throw new zza("Failed to get module context");
        }
        return new zztl(zza2);
    }

    public static int zzc(Context context, String str, boolean z) throws zza {
        ContentResolver contentResolver;
        String str2 = z ? "api_force_staging" : "api";
        String valueOf = String.valueOf("content://com.google.android.gms.chimera/");
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str2).length() + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        Uri parse = Uri.parse(sb.toString());
        if (context == null || (contentResolver = context.getContentResolver()) == null) {
            throw new zza("Failed to get dynamite module ContentResolver.");
        }
        Cursor query = contentResolver.query(parse, null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int i = query.getInt(0);
                    if (i > 0) {
                        synchronized (zztl.class) {
                            HashMap<String, byte[]> hashMap = Qi;
                            StringBuilder sb2 = new StringBuilder(12 + String.valueOf(str).length());
                            sb2.append(str);
                            sb2.append(":");
                            sb2.append(i);
                            hashMap.put(sb2.toString(), query.getBlob(1));
                            Qj = query.getString(2);
                        }
                    }
                    return i;
                }
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
        throw new zza("Failed to connect to dynamite module ContentResolver.");
    }

    private static zztm zzcs(Context context) {
        synchronized (zztl.class) {
            if (Qh != null) {
                return Qh;
            }
            if (com.google.android.gms.common.zzc.zzaql().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            }
            try {
                zztm zzfe = zztm.zza.zzfe((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                if (zzfe != null) {
                    Qh = zzfe;
                    return zzfe;
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public Context zzbdt() {
        return this.Qr;
    }

    public IBinder zzjd(String str) throws zza {
        try {
            return (IBinder) this.Qr.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e);
        }
    }
}
