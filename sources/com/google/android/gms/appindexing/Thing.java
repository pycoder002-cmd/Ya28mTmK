package com.google.android.gms.appindexing;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzaa;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Thing {
    final Bundle he;

    /* loaded from: classes.dex */
    public static class Builder {
        final Bundle hf = new Bundle();

        public Thing build() {
            return new Thing(this.hf);
        }

        public Builder put(String str, Thing thing) {
            zzaa.zzy(str);
            if (thing != null) {
                this.hf.putParcelable(str, thing.he);
            }
            return this;
        }

        public Builder put(String str, String str2) {
            zzaa.zzy(str);
            if (str2 != null) {
                this.hf.putString(str, str2);
            }
            return this;
        }

        public Builder put(String str, boolean z) {
            zzaa.zzy(str);
            this.hf.putBoolean(str, z);
            return this;
        }

        public Builder put(String str, Thing[] thingArr) {
            zzaa.zzy(str);
            if (thingArr != null) {
                ArrayList arrayList = new ArrayList();
                for (Thing thing : thingArr) {
                    if (thing != null) {
                        arrayList.add(thing.he);
                    }
                }
                this.hf.putParcelableArray(str, (Parcelable[]) arrayList.toArray(new Bundle[arrayList.size()]));
            }
            return this;
        }

        public Builder put(String str, String[] strArr) {
            zzaa.zzy(str);
            if (strArr != null) {
                this.hf.putStringArray(str, strArr);
            }
            return this;
        }

        public Builder setDescription(String str) {
            put("description", str);
            return this;
        }

        public Builder setId(String str) {
            if (str != null) {
                put(ConnectionModel.ID, str);
            }
            return this;
        }

        public Builder setName(String str) {
            zzaa.zzy(str);
            put("name", str);
            return this;
        }

        public Builder setType(String str) {
            put("type", str);
            return this;
        }

        public Builder setUrl(Uri uri) {
            zzaa.zzy(uri);
            put(FileDownloadModel.URL, uri.toString());
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Thing(Bundle bundle) {
        this.he = bundle;
    }

    public Bundle zzahu() {
        return this.he;
    }
}
