package org.springframework.util.comparator;

import java.io.Serializable;
import java.util.Comparator;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class InvertibleComparator<T> implements Comparator<T>, Serializable {
    private boolean ascending = true;
    private final Comparator<T> comparator;

    public InvertibleComparator(Comparator<T> comparator) {
        Assert.notNull(comparator, "Comparator must not be null");
        this.comparator = comparator;
    }

    public InvertibleComparator(Comparator<T> comparator, boolean z) {
        Assert.notNull(comparator, "Comparator must not be null");
        this.comparator = comparator;
        setAscending(z);
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        int compare = this.comparator.compare(t, t2);
        if (compare == 0) {
            return 0;
        }
        if (this.ascending) {
            return compare;
        }
        if (Integer.MIN_VALUE == compare) {
            return Integer.MAX_VALUE;
        }
        return compare * (-1);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InvertibleComparator)) {
            return false;
        }
        InvertibleComparator invertibleComparator = (InvertibleComparator) obj;
        return this.comparator.equals(invertibleComparator.comparator) && this.ascending == invertibleComparator.ascending;
    }

    public int hashCode() {
        return this.comparator.hashCode();
    }

    public void invertOrder() {
        this.ascending = !this.ascending;
    }

    public boolean isAscending() {
        return this.ascending;
    }

    public void setAscending(boolean z) {
        this.ascending = z;
    }

    public String toString() {
        return "InvertibleComparator: [" + this.comparator + "]; ascending=" + this.ascending;
    }
}
