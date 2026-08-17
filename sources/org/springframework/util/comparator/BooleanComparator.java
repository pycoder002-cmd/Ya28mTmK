package org.springframework.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes2.dex */
public final class BooleanComparator implements Comparator<Boolean>, Serializable {
    private final boolean trueLow;
    public static final BooleanComparator TRUE_LOW = new BooleanComparator(true);
    public static final BooleanComparator TRUE_HIGH = new BooleanComparator(false);

    public BooleanComparator(boolean z) {
        this.trueLow = z;
    }

    @Override // java.util.Comparator
    public int compare(Boolean bool, Boolean bool2) {
        if (bool2.booleanValue() ^ bool.booleanValue()) {
            return bool.booleanValue() ^ this.trueLow ? 1 : -1;
        }
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BooleanComparator) && this.trueLow == ((BooleanComparator) obj).trueLow;
    }

    public int hashCode() {
        return (this.trueLow ? -1 : 1) * getClass().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BooleanComparator: ");
        sb.append(this.trueLow ? "true low" : "true high");
        return sb.toString();
    }
}
