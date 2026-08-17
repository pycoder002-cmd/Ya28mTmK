package org.springframework.util.comparator;

import java.util.Comparator;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class NullSafeComparator<T> implements Comparator<T> {
    private final Comparator<T> nonNullComparator;
    private final boolean nullsLow;
    public static final NullSafeComparator NULLS_LOW = new NullSafeComparator(true);
    public static final NullSafeComparator NULLS_HIGH = new NullSafeComparator(false);

    public NullSafeComparator(Comparator<T> comparator, boolean z) {
        Assert.notNull(comparator, "The non-null comparator is required");
        this.nonNullComparator = comparator;
        this.nullsLow = z;
    }

    private NullSafeComparator(boolean z) {
        this.nonNullComparator = new ComparableComparator();
        this.nullsLow = z;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        if (t == t2) {
            return 0;
        }
        return t == null ? this.nullsLow ? -1 : 1 : t2 == null ? this.nullsLow ? 1 : -1 : this.nonNullComparator.compare(t, t2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NullSafeComparator)) {
            return false;
        }
        NullSafeComparator nullSafeComparator = (NullSafeComparator) obj;
        return this.nonNullComparator.equals(nullSafeComparator.nonNullComparator) && this.nullsLow == nullSafeComparator.nullsLow;
    }

    public int hashCode() {
        return (this.nullsLow ? -1 : 1) * this.nonNullComparator.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NullSafeComparator: non-null comparator [");
        sb.append(this.nonNullComparator);
        sb.append("]; ");
        sb.append(this.nullsLow ? "nulls low" : "nulls high");
        return sb.toString();
    }
}
