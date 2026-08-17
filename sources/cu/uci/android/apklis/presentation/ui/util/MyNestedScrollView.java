package cu.uci.android.apklis.presentation.ui.util;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* loaded from: classes.dex */
public class MyNestedScrollView extends NestedScrollView {
    private float lastX;
    private float lastY;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private int slop;
    private float xDistance;
    private float yDistance;

    public MyNestedScrollView(Context context) {
        super(context);
        init(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.slop = ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    @Override // android.support.v4.widget.NestedScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        motionEvent.getX();
        motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.yDistance = 0.0f;
            this.xDistance = 0.0f;
            this.lastX = motionEvent.getX();
            this.lastY = motionEvent.getY();
            computeScroll();
        } else if (action == 2) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.xDistance += Math.abs(x - this.lastX);
            this.yDistance += Math.abs(y - this.lastY);
            this.lastX = x;
            this.lastY = y;
            if (this.xDistance > this.yDistance) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
