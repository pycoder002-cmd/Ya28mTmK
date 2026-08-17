package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        int i3;
        ConstraintWidget[] constraintWidgetArr;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            constraintWidgetArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = i4;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            constraintWidgetArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, constraintWidgetArr[i5]);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
    
        if (r12.mHorizontalChainStyle == 2) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x002b, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x004b, code lost:
    
        if (r12.mVerticalChainStyle == 2) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0100, code lost:
    
        if (r15.mListAnchors[r34].mTarget.mOwner == r8) goto L67;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r31, android.support.constraint.solver.LinearSystem r32, int r33, int r34, android.support.constraint.solver.widgets.ConstraintWidget r35) {
        /*
            Method dump skipped, instructions count: 904
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ConstraintWidget):void");
    }
}
