package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public final class MusicGroupBuilder extends IndexableBuilder<MusicGroupBuilder> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MusicGroupBuilder() {
        super("MusicGroup");
    }

    public MusicGroupBuilder setAlbum(@NonNull MusicAlbumBuilder... musicAlbumBuilderArr) {
        return put("album", musicAlbumBuilderArr);
    }

    public MusicGroupBuilder setGenre(@NonNull String str) {
        return put("genre", str);
    }

    public MusicGroupBuilder setTrack(@NonNull MusicRecordingBuilder... musicRecordingBuilderArr) {
        return put("track", musicRecordingBuilderArr);
    }
}
