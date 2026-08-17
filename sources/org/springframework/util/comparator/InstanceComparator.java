package org.springframework.util.comparator;

import java.util.Comparator;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class InstanceComparator<T> implements Comparator<T> {
    private final Class<?>[] instanceOrder;

    public InstanceComparator(Class<?>... clsArr) {
        Assert.notNull(clsArr, "'instanceOrder' must not be null");
        this.instanceOrder = clsArr;
    }

    private int getOrder(T t) {
        if (t != null) {
            for (int i = 0; i < this.instanceOrder.length; i++) {
                if (this.instanceOrder[i].isInstance(t)) {
                    return i;
                }
            }
        }
        return this.instanceOrder.length;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        int order = getOrder(t);
        int order2 = getOrder(t2);
        if (order < order2) {
            return -1;
        }
        return order == order2 ? 0 : 1;
    }
}
