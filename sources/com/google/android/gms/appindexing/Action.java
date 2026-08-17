package com.google.android.gms.appindexing;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.internal.zzaa;
import com.liulishuo.filedownloader.model.FileDownloadModel;

/* loaded from: classes.dex */
public final class Action extends Thing {
    public static final String STATUS_TYPE_ACTIVE = "http://schema.org/ActiveActionStatus";
    public static final String STATUS_TYPE_COMPLETED = "http://schema.org/CompletedActionStatus";
    public static final String STATUS_TYPE_FAILED = "http://schema.org/FailedActionStatus";
    public static final String TYPE_ACTIVATE = "http://schema.org/ActivateAction";
    public static final String TYPE_ADD = "http://schema.org/AddAction";
    public static final String TYPE_BOOKMARK = "http://schema.org/BookmarkAction";
    public static final String TYPE_COMMUNICATE = "http://schema.org/CommunicateAction";
    public static final String TYPE_FILM = "http://schema.org/FilmAction";
    public static final String TYPE_LIKE = "http://schema.org/LikeAction";
    public static final String TYPE_LISTEN = "http://schema.org/ListenAction";
    public static final String TYPE_PHOTOGRAPH = "http://schema.org/PhotographAction";
    public static final String TYPE_RESERVE = "http://schema.org/ReserveAction";
    public static final String TYPE_SEARCH = "http://schema.org/SearchAction";
    public static final String TYPE_VIEW = "http://schema.org/ViewAction";
    public static final String TYPE_WANT = "http://schema.org/WantAction";
    public static final String TYPE_WATCH = "http://schema.org/WatchAction";

    /* loaded from: classes.dex */
    public static final class Builder extends Thing.Builder {
        public Builder(String str) {
            zzaa.zzy(str);
            super.put("type", str);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        public Action build() {
            zzaa.zzb(this.hf.get("object"), "setObject is required before calling build().");
            zzaa.zzb(this.hf.get("type"), "setType is required before calling build().");
            Bundle bundle = (Bundle) this.hf.getParcelable("object");
            zzaa.zzb(bundle.get("name"), "Must call setObject() with a valid name. Example: setObject(new Thing.Builder().setName(name).setUrl(url))");
            zzaa.zzb(bundle.get(FileDownloadModel.URL), "Must call setObject() with a valid app URI. Example: setObject(new Thing.Builder().setName(name).setUrl(url))");
            return new Action(this.hf);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        public Builder put(String str, Thing thing) {
            return (Builder) super.put(str, thing);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        public Builder put(String str, String str2) {
            return (Builder) super.put(str, str2);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        public Builder put(String str, boolean z) {
            return (Builder) super.put(str, z);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        public Builder put(String str, Thing[] thingArr) {
            return (Builder) super.put(str, thingArr);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        public Builder put(String str, String[] strArr) {
            return (Builder) super.put(str, strArr);
        }

        public Builder setActionStatus(String str) {
            zzaa.zzy(str);
            return (Builder) super.put("actionStatus", str);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        /* renamed from: setName, reason: merged with bridge method [inline-methods] */
        public Builder m34setName(String str) {
            return (Builder) super.put("name", str);
        }

        public Builder setObject(Thing thing) {
            zzaa.zzy(thing);
            return (Builder) super.put("object", thing);
        }

        @Override // com.google.android.gms.appindexing.Thing.Builder
        public Builder setUrl(Uri uri) {
            if (uri != null) {
                super.put(FileDownloadModel.URL, uri.toString());
            }
            return this;
        }
    }

    private Action(Bundle bundle) {
        super(bundle);
    }

    public static Action newAction(String str, String str2, Uri uri) {
        return newAction(str, str2, null, uri);
    }

    public static Action newAction(String str, String str2, Uri uri, Uri uri2) {
        return (Action) new Builder(str).setObject(new Thing.Builder().m34setName(str2).setId(uri == null ? null : uri.toString()).setUrl(uri2).build()).build();
    }
}
