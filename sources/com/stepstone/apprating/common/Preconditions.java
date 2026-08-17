package com.stepstone.apprating.common;

import android.support.media.ExifInterface;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Preconditions.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0002J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0002J \u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001J/\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0014\"\u00020\u0001¢\u0006\u0002\u0010\u0015J\"\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0007J\u001b\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u00182\b\u0010\u0019\u001a\u0004\u0018\u0001H\u0018¢\u0006\u0002\u0010\u001aJ#\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u00182\b\u0010\u0019\u001a\u0004\u0018\u0001H\u00182\u0006\u0010\u0011\u001a\u00020\u0001¢\u0006\u0002\u0010\u001bJ7\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u00182\b\u0010\u0019\u001a\u0004\u0018\u0001H\u00182\u0006\u0010\u0012\u001a\u00020\u00042\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0014\"\u00020\u0001¢\u0006\u0002\u0010\u001cJ\"\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0007J\u001e\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001J/\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0014\"\u00020\u0001¢\u0006\u0002\u0010\u0015J+\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0014\"\u00020\u0001H\u0000¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/stepstone/apprating/common/Preconditions;", "", "()V", "badElementIndex", "", "index", "", "size", "desc", "badPositionIndex", "badPositionIndexes", "start", "end", "checkArgument", "", "expression", "", "errorMessage", "errorMessageTemplate", "errorMessageArgs", "", "(ZLjava/lang/String;[Ljava/lang/Object;)V", "checkElementIndex", "checkNotNull", ExifInterface.GPS_DIRECTION_TRUE, "reference", "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", "checkPositionIndex", "checkPositionIndexes", "checkState", "format", "template", "args", "format$app_rating_release", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class Preconditions {
    public static final Preconditions INSTANCE = new Preconditions();

    private Preconditions() {
    }

    private final String badElementIndex(int index, int size, String desc) {
        if (index < 0) {
            return format$app_rating_release("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size >= 0) {
            return format$app_rating_release("%s (%s) must be less than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
        throw new IllegalArgumentException("negative size: " + size);
    }

    private final String badPositionIndex(int index, int size, String desc) {
        if (index < 0) {
            return format$app_rating_release("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size >= 0) {
            return format$app_rating_release("%s (%s) must not be greater than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
        throw new IllegalArgumentException("negative size: " + size);
    }

    private final String badPositionIndexes(int start, int end, int size) {
        return (start < 0 || start > size) ? badPositionIndex(start, size, "start index") : (end < 0 || end > size) ? badPositionIndex(end, size, "end index") : format$app_rating_release("end index (%s) must not be less than start index (%s)", Integer.valueOf(end), Integer.valueOf(start));
    }

    @JvmOverloads
    public static /* bridge */ /* synthetic */ int checkElementIndex$default(Preconditions preconditions, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            str = "index";
        }
        return preconditions.checkElementIndex(i, i2, str);
    }

    @JvmOverloads
    public static /* bridge */ /* synthetic */ int checkPositionIndex$default(Preconditions preconditions, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            str = "index";
        }
        return preconditions.checkPositionIndex(i, i2, str);
    }

    public final void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public final void checkArgument(boolean expression, @NotNull Object errorMessage) {
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        if (!expression) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    public final void checkArgument(boolean expression, @NotNull String errorMessageTemplate, @NotNull Object... errorMessageArgs) {
        Intrinsics.checkParameterIsNotNull(errorMessageTemplate, "errorMessageTemplate");
        Intrinsics.checkParameterIsNotNull(errorMessageArgs, "errorMessageArgs");
        if (!expression) {
            throw new IllegalArgumentException(format$app_rating_release(errorMessageTemplate, Arrays.copyOf(errorMessageArgs, errorMessageArgs.length)));
        }
    }

    @JvmOverloads
    public final int checkElementIndex(int i, int i2) {
        return checkElementIndex$default(this, i, i2, null, 4, null);
    }

    @JvmOverloads
    public final int checkElementIndex(int index, int size, @NotNull String desc) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
        }
        return index;
    }

    public final <T> T checkNotNull(@Nullable T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public final <T> T checkNotNull(@Nullable T reference, @NotNull Object errorMessage) {
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        if (reference == null) {
            throw new NullPointerException(errorMessage.toString());
        }
        return reference;
    }

    public final <T> T checkNotNull(@Nullable T reference, @NotNull String errorMessageTemplate, @NotNull Object... errorMessageArgs) {
        Intrinsics.checkParameterIsNotNull(errorMessageTemplate, "errorMessageTemplate");
        Intrinsics.checkParameterIsNotNull(errorMessageArgs, "errorMessageArgs");
        if (reference == null) {
            throw new NullPointerException(format$app_rating_release(errorMessageTemplate, Arrays.copyOf(errorMessageArgs, errorMessageArgs.length)));
        }
        return reference;
    }

    @JvmOverloads
    public final int checkPositionIndex(int i, int i2) {
        return checkPositionIndex$default(this, i, i2, null, 4, null);
    }

    @JvmOverloads
    public final int checkPositionIndex(int index, int size, @NotNull String desc) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
        }
        return index;
    }

    public final void checkPositionIndexes(int start, int end, int size) {
        if (start < 0 || end < start || end > size) {
            throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
        }
    }

    public final void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public final void checkState(boolean expression, @NotNull Object errorMessage) {
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        if (!expression) {
            throw new IllegalStateException(errorMessage.toString());
        }
    }

    public final void checkState(boolean expression, @NotNull String errorMessageTemplate, @NotNull Object... errorMessageArgs) {
        Intrinsics.checkParameterIsNotNull(errorMessageTemplate, "errorMessageTemplate");
        Intrinsics.checkParameterIsNotNull(errorMessageArgs, "errorMessageArgs");
        if (!expression) {
            throw new IllegalStateException(format$app_rating_release(errorMessageTemplate, Arrays.copyOf(errorMessageArgs, errorMessageArgs.length)));
        }
    }

    @NotNull
    public final String format$app_rating_release(@NotNull String template, @NotNull Object... args) {
        int indexOf$default;
        Intrinsics.checkParameterIsNotNull(template, "template");
        Intrinsics.checkParameterIsNotNull(args, "args");
        int i = 0;
        StringBuilder sb = new StringBuilder(template.length() + (16 * args.length));
        int i2 = 0;
        while (i < args.length && (indexOf$default = StringsKt.indexOf$default((CharSequence) template, "%s", i2, false, 4, (Object) null)) != -1) {
            String substring = template.substring(i2, indexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(substring);
            sb.append(args[i]);
            i++;
            i2 = indexOf$default + 2;
        }
        String substring2 = template.substring(i2);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(substring2);
        if (i < args.length) {
            sb.append(" [");
            sb.append(args[i]);
            for (int i3 = i + 1; i3 < args.length; i3++) {
                sb.append(", ");
                sb.append(args[i3]);
            }
            sb.append("]");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }
}
