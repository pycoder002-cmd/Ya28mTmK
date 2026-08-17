package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* loaded from: classes.dex */
public final class zzapd {
    public zzaoy zza(Reader reader) throws zzaoz, zzaph {
        try {
            zzaqp zzaqpVar = new zzaqp(reader);
            zzaoy zzh = zzh(zzaqpVar);
            if (zzh.aY() || zzaqpVar.bq() == zzaqq.END_DOCUMENT) {
                return zzh;
            }
            throw new zzaph("Did not consume the entire document.");
        } catch (zzaqs e) {
            throw new zzaph(e);
        } catch (IOException e2) {
            throw new zzaoz(e2);
        } catch (NumberFormatException e3) {
            throw new zzaph(e3);
        }
    }

    public zzaoy zzh(zzaqp zzaqpVar) throws zzaoz, zzaph {
        boolean isLenient = zzaqpVar.isLenient();
        zzaqpVar.setLenient(true);
        try {
            try {
                return zzapz.zzh(zzaqpVar);
            } catch (OutOfMemoryError e) {
                String valueOf = String.valueOf(zzaqpVar);
                StringBuilder sb = new StringBuilder(36 + String.valueOf(valueOf).length());
                sb.append("Failed parsing JSON source: ");
                sb.append(valueOf);
                sb.append(" to Json");
                throw new zzapc(sb.toString(), e);
            } catch (StackOverflowError e2) {
                String valueOf2 = String.valueOf(zzaqpVar);
                StringBuilder sb2 = new StringBuilder(36 + String.valueOf(valueOf2).length());
                sb2.append("Failed parsing JSON source: ");
                sb2.append(valueOf2);
                sb2.append(" to Json");
                throw new zzapc(sb2.toString(), e2);
            }
        } finally {
            zzaqpVar.setLenient(isLenient);
        }
    }

    public zzaoy zzuq(String str) throws zzaph {
        return zza(new StringReader(str));
    }
}
