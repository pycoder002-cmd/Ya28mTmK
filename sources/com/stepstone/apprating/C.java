package com.stepstone.apprating;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: C.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, d2 = {"Lcom/stepstone/apprating/C;", "", "Animation", "ExtraKeys", "InitialValues", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public interface C {

    /* compiled from: C.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/stepstone/apprating/C$Animation;", "", "Companion", "app-rating_release"}, k = 1, mv = {1, 1, 9})
    /* loaded from: classes.dex */
    public interface Animation {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* compiled from: C.kt */
        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lcom/stepstone/apprating/C$Animation$Companion;", "", "()V", "CHECK_STAR_DURATION", "", "getCHECK_STAR_DURATION", "()J", "INVISIBLE", "", "getINVISIBLE", "()F", "VISIBLE", "getVISIBLE", "app-rating_release"}, k = 1, mv = {1, 1, 9})
        /* loaded from: classes.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final long CHECK_STAR_DURATION = 200;
            private static final float INVISIBLE = 0.0f;
            private static final float VISIBLE = 1.0f;

            private Companion() {
            }

            public final long getCHECK_STAR_DURATION() {
                return CHECK_STAR_DURATION;
            }

            public final float getINVISIBLE() {
                return INVISIBLE;
            }

            public final float getVISIBLE() {
                return VISIBLE;
            }
        }
    }

    /* compiled from: C.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/stepstone/apprating/C$ExtraKeys;", "", "Companion", "app-rating_release"}, k = 1, mv = {1, 1, 9})
    /* loaded from: classes.dex */
    public interface ExtraKeys {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* compiled from: C.kt */
        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/stepstone/apprating/C$ExtraKeys$Companion;", "", "()V", "CURRENT_RATE_NUMBER", "", "getCURRENT_RATE_NUMBER", "()Ljava/lang/String;", "DATA", "getDATA", "app-rating_release"}, k = 1, mv = {1, 1, 9})
        /* loaded from: classes.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            @NotNull
            private static final String CURRENT_RATE_NUMBER = "currentRateNumber";

            @NotNull
            private static final String DATA = "data";

            private Companion() {
            }

            @NotNull
            public final String getCURRENT_RATE_NUMBER() {
                return CURRENT_RATE_NUMBER;
            }

            @NotNull
            public final String getDATA() {
                return DATA;
            }
        }
    }

    /* compiled from: C.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/stepstone/apprating/C$InitialValues;", "", "Companion", "app-rating_release"}, k = 1, mv = {1, 1, 9})
    /* loaded from: classes.dex */
    public interface InitialValues {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* compiled from: C.kt */
        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/stepstone/apprating/C$InitialValues$Companion;", "", "()V", "DEFAULT_RATING", "", "getDEFAULT_RATING", "()I", "MAX_RATING", "getMAX_RATING", "app-rating_release"}, k = 1, mv = {1, 1, 9})
        /* loaded from: classes.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final int DEFAULT_RATING = 4;
            private static final int MAX_RATING = 6;

            private Companion() {
            }

            public final int getDEFAULT_RATING() {
                return DEFAULT_RATING;
            }

            public final int getMAX_RATING() {
                return MAX_RATING;
            }
        }
    }
}
