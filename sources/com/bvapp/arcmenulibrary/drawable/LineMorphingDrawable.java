package com.bvapp.arcmenulibrary.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.text.TextUtilsCompat;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.bvapp.arcmenulibrary.R;
import com.bvapp.arcmenulibrary.util.Util;
import java.util.Locale;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: classes.dex */
public class LineMorphingDrawable extends Drawable implements Animatable {
    private int mAnimDuration;
    private float mAnimProgress;
    private boolean mClockwise;
    private int mCurState;
    private RectF mDrawBound;
    private Interpolator mInterpolator;
    private boolean mIsRtl;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private Paint mPaint;
    private Path mPath;
    private int mPrevState;
    private boolean mRunning;
    private long mStartTime;
    private State[] mStates;
    private Paint.Cap mStrokeCap;
    private int mStrokeColor;
    private Paint.Join mStrokeJoin;
    private int mStrokeSize;
    private final Runnable mUpdater;

    /* loaded from: classes.dex */
    public static class Builder {
        private static final String TAG_ITEM = "item";
        private static final String TAG_LINKS = "links";
        private static final String TAG_POINTS = "points";
        private static final String TAG_STATE = "state";
        private static final String TAG_STATE_LIST = "state-list";
        private int mAnimDuration;
        private boolean mClockwise;
        private int mCurState;
        private Interpolator mInterpolator;
        private boolean mIsRtl;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private State[] mStates;
        private Paint.Cap mStrokeCap;
        private int mStrokeColor;
        private Paint.Join mStrokeJoin;
        private int mStrokeSize;

        public Builder() {
        }

        public Builder(Context context, int i) {
            this(context, null, 0, i);
        }

        public Builder(Context context, AttributeSet attributeSet, int i, int i2) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LineMorphingDrawable, i, i2);
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.LineMorphingDrawable_lmd_state, 0);
            if (resourceId != 0) {
                states(readStates(context, resourceId));
            }
            curState(obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_curState, 0));
            padding(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_padding, 0));
            paddingLeft(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingLeft, this.mPaddingLeft));
            paddingTop(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingTop, this.mPaddingTop));
            paddingRight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingRight, this.mPaddingRight));
            paddingBottom(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingBottom, this.mPaddingBottom));
            animDuration(obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_animDuration, context.getResources().getInteger(android.R.integer.config_mediumAnimTime)));
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.LineMorphingDrawable_lmd_interpolator, 0);
            if (resourceId2 != 0) {
                interpolator(AnimationUtils.loadInterpolator(context, resourceId2));
            }
            strokeSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_strokeSize, Util.dpToPx(3)));
            strokeColor(obtainStyledAttributes.getColor(R.styleable.LineMorphingDrawable_lmd_strokeColor, -1));
            int integer = obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_strokeCap, 0);
            if (integer == 0) {
                strokeCap(Paint.Cap.BUTT);
            } else if (integer == 1) {
                strokeCap(Paint.Cap.ROUND);
            } else {
                strokeCap(Paint.Cap.SQUARE);
            }
            int integer2 = obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_strokeJoin, 0);
            if (integer2 == 0) {
                strokeJoin(Paint.Join.MITER);
            } else if (integer2 == 1) {
                strokeJoin(Paint.Join.ROUND);
            } else {
                strokeJoin(Paint.Join.BEVEL);
            }
            clockwise(obtainStyledAttributes.getBoolean(R.styleable.LineMorphingDrawable_lmd_clockwise, true));
            int integer3 = obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_layoutDirection, 0);
            if (integer3 == 3) {
                rtl(TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1);
            } else {
                rtl(integer3 == 1);
            }
            obtainStyledAttributes.recycle();
        }

        /* JADX WARN: Code restructure failed: missing block: B:105:0x017f, code lost:
        
            if (r3 == null) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0046, code lost:
        
            r7 = new java.util.ArrayList();
            r8 = new java.lang.StringBuilder();
            r12 = null;
            r13 = null;
            r10 = r4;
            r4 = false;
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0056, code lost:
        
            if (r4 != false) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0058, code lost:
        
            r14 = 3;
            r14 = 3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x005a, code lost:
        
            switch(r10) {
                case 1: goto L86;
                case 2: goto L57;
                case 3: goto L20;
                case 4: goto L19;
                default: goto L18;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0167, code lost:
        
            r10 = r3.next();
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x016b, code lost:
        
            r6 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x005f, code lost:
        
            r8.append(r3.getText());
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
        
            r10 = r3.getName();
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x006c, code lost:
        
            if (r11 == false) goto L25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
        
            if (r10.equals(r12) == false) goto L25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0074, code lost:
        
            r12 = null;
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x007a, code lost:
        
            switch(r10.hashCode()) {
                case -982754077: goto L40;
                case -273989542: goto L37;
                case 3242771: goto L34;
                case 102977465: goto L31;
                case 109757585: goto L28;
                default: goto L27;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00b0, code lost:
        
            r14 = -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00b1, code lost:
        
            switch(r14) {
                case 0: goto L56;
                case 1: goto L55;
                case 2: goto L51;
                case 3: goto L47;
                case 4: goto L46;
                default: goto L45;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00b6, code lost:
        
            r7.add(r8.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00bf, code lost:
        
            r13.links = new int[r7.size()];
            r5 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00cb, code lost:
        
            if (r5 >= r13.links.length) goto L114;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00cd, code lost:
        
            r13.links[r5] = java.lang.Integer.parseInt((java.lang.String) r7.get(r5));
            r5 = r5 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00de, code lost:
        
            r13.points = new float[r7.size()];
            r5 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00ea, code lost:
        
            if (r5 >= r13.points.length) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00ec, code lost:
        
            r13.points[r5] = java.lang.Float.parseFloat((java.lang.String) r7.get(r5));
            r5 = r5 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00fd, code lost:
        
            r1.add(r13);
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0102, code lost:
        
            r4 = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0084, code lost:
        
            if (r10.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_STATE) == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0086, code lost:
        
            r14 = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x008e, code lost:
        
            if (r10.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_LINKS) == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0097, code lost:
        
            if (r10.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_ITEM) == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0099, code lost:
        
            r14 = 4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00a2, code lost:
        
            if (r10.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_STATE_LIST) == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00a4, code lost:
        
            r14 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00ac, code lost:
        
            if (r10.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_POINTS) == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00ae, code lost:
        
            r14 = 2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0105, code lost:
        
            if (r11 == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0109, code lost:
        
            r5 = r3.getName();
            r10 = r5.hashCode();
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0114, code lost:
        
            if (r10 == (-982754077)) goto L77;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x0119, code lost:
        
            if (r10 == 3242771) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x011e, code lost:
        
            if (r10 == 102977465) goto L71;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x0123, code lost:
        
            if (r10 == 109757585) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x014d, code lost:
        
            r14 = -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x014e, code lost:
        
            switch(r14) {
                case 0: goto L85;
                case 1: goto L84;
                case 2: goto L84;
                case 3: goto L83;
                default: goto L82;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0151, code lost:
        
            r12 = r5;
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0154, code lost:
        
            r8.delete(0, r8.length());
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x015c, code lost:
        
            r7.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x0160, code lost:
        
            r13 = new com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.State();
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x012c, code lost:
        
            if (r5.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_STATE) == false) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x012e, code lost:
        
            r14 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0136, code lost:
        
            if (r5.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_LINKS) == false) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0138, code lost:
        
            r14 = 2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x0140, code lost:
        
            if (r5.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_ITEM) == false) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0149, code lost:
        
            if (r5.equals(com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.TAG_POINTS) == false) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x014b, code lost:
        
            r14 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0166, code lost:
        
            r4 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x016f, code lost:
        
            if (r3 == null) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0188, code lost:
        
            if (r1.isEmpty() == false) goto L104;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x018a, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x0197, code lost:
        
            return (com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.State[]) r1.toArray(new com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.State[r1.size()]);
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0181, code lost:
        
            r3.close();
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r14v0 */
        /* JADX WARN: Type inference failed for: r14v1 */
        /* JADX WARN: Type inference failed for: r14v10 */
        /* JADX WARN: Type inference failed for: r14v11 */
        /* JADX WARN: Type inference failed for: r14v18 */
        /* JADX WARN: Type inference failed for: r14v19 */
        /* JADX WARN: Type inference failed for: r14v2 */
        /* JADX WARN: Type inference failed for: r14v3 */
        /* JADX WARN: Type inference failed for: r14v4 */
        /* JADX WARN: Type inference failed for: r14v5 */
        /* JADX WARN: Type inference failed for: r14v6 */
        /* JADX WARN: Type inference failed for: r14v7 */
        /* JADX WARN: Type inference failed for: r14v8 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.State[] readStates(android.content.Context r19, int r20) {
            /*
                Method dump skipped, instructions count: 468
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.Builder.readStates(android.content.Context, int):com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable$State[]");
        }

        public Builder animDuration(int i) {
            this.mAnimDuration = i;
            return this;
        }

        public LineMorphingDrawable build() {
            if (this.mStrokeCap == null) {
                this.mStrokeCap = Paint.Cap.BUTT;
            }
            if (this.mStrokeJoin == null) {
                this.mStrokeJoin = Paint.Join.MITER;
            }
            if (this.mInterpolator == null) {
                this.mInterpolator = new AccelerateInterpolator();
            }
            return new LineMorphingDrawable(this.mStates, this.mCurState, this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom, this.mAnimDuration, this.mInterpolator, this.mStrokeSize, this.mStrokeColor, this.mStrokeCap, this.mStrokeJoin, this.mClockwise, this.mIsRtl);
        }

        public Builder clockwise(boolean z) {
            this.mClockwise = z;
            return this;
        }

        public Builder curState(int i) {
            this.mCurState = i;
            return this;
        }

        public Builder interpolator(Interpolator interpolator) {
            this.mInterpolator = interpolator;
            return this;
        }

        public Builder padding(int i) {
            this.mPaddingLeft = i;
            this.mPaddingTop = i;
            this.mPaddingRight = i;
            this.mPaddingBottom = i;
            return this;
        }

        public Builder paddingBottom(int i) {
            this.mPaddingBottom = i;
            return this;
        }

        public Builder paddingLeft(int i) {
            this.mPaddingLeft = i;
            return this;
        }

        public Builder paddingRight(int i) {
            this.mPaddingRight = i;
            return this;
        }

        public Builder paddingTop(int i) {
            this.mPaddingTop = i;
            return this;
        }

        public Builder rtl(boolean z) {
            this.mIsRtl = z;
            return this;
        }

        public Builder states(State... stateArr) {
            this.mStates = stateArr;
            return this;
        }

        public Builder strokeCap(Paint.Cap cap) {
            this.mStrokeCap = cap;
            return this;
        }

        public Builder strokeColor(int i) {
            this.mStrokeColor = i;
            return this;
        }

        public Builder strokeJoin(Paint.Join join) {
            this.mStrokeJoin = join;
            return this;
        }

        public Builder strokeSize(int i) {
            this.mStrokeSize = i;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class State {
        int[] links;
        float[] points;

        public State() {
        }

        public State(float[] fArr, int[] iArr) {
            this.points = fArr;
            this.links = iArr;
        }
    }

    private LineMorphingDrawable(State[] stateArr, int i, int i2, int i3, int i4, int i5, int i6, Interpolator interpolator, int i7, int i8, Paint.Cap cap, Paint.Join join, boolean z, boolean z2) {
        this.mRunning = false;
        this.mPaddingLeft = 12;
        this.mPaddingTop = 12;
        this.mPaddingRight = 12;
        this.mPaddingBottom = 12;
        this.mUpdater = new Runnable() { // from class: com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                LineMorphingDrawable.this.update();
            }
        };
        this.mStates = stateArr;
        this.mPaddingLeft = i2;
        this.mPaddingTop = i3;
        this.mPaddingRight = i4;
        this.mPaddingBottom = i5;
        this.mAnimDuration = i6;
        this.mInterpolator = interpolator;
        this.mStrokeSize = i7;
        this.mStrokeColor = i8;
        this.mStrokeCap = cap;
        this.mStrokeJoin = join;
        this.mClockwise = z;
        this.mIsRtl = z2;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeCap(this.mStrokeCap);
        this.mPaint.setStrokeJoin(this.mStrokeJoin);
        this.mPaint.setColor(this.mStrokeColor);
        this.mPaint.setStrokeWidth(this.mStrokeSize);
        this.mDrawBound = new RectF();
        this.mPath = new Path();
        switchLineState(i, false);
    }

    private float getX(float f) {
        return this.mDrawBound.left + (this.mDrawBound.width() * f);
    }

    private float getY(float f) {
        return this.mDrawBound.top + (this.mDrawBound.height() * f);
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
        this.mAnimProgress = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mAnimDuration);
        if (min == 1.0f) {
            setLineState(this.mCurState, 1.0f);
            this.mRunning = false;
        } else {
            setLineState(this.mCurState, this.mInterpolator.getInterpolation(min));
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
    }

    private void updatePath() {
        this.mPath.reset();
        if (this.mStates == null) {
            return;
        }
        if (this.mAnimProgress == 0.0f || (this.mStates[this.mPrevState].links != null && this.mAnimProgress < 0.05f)) {
            updatePathWithState(this.mPath, this.mStates[this.mPrevState]);
        } else if (this.mAnimProgress == 1.0f || (this.mStates[this.mCurState].links != null && this.mAnimProgress > 0.95f)) {
            updatePathWithState(this.mPath, this.mStates[this.mCurState]);
        } else {
            updatePathBetweenStates(this.mPath, this.mStates[this.mPrevState], this.mStates[this.mCurState], this.mInterpolator.getInterpolation(this.mAnimProgress));
        }
        invalidateSelf();
    }

    private void updatePathBetweenStates(Path path, State state, State state2, float f) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        int max = Math.max(state.points.length, state2.points.length) / 4;
        for (int i = 0; i < max; i++) {
            int i2 = i * 4;
            float f9 = 0.5f;
            if (i2 >= state.points.length) {
                f2 = 0.5f;
                f3 = 0.5f;
                f4 = 0.5f;
                f5 = 0.5f;
            } else {
                f2 = state.points[i2];
                f3 = state.points[i2 + 1];
                f4 = state.points[i2 + 2];
                f5 = state.points[i2 + 3];
            }
            if (i2 >= state2.points.length) {
                f8 = 0.5f;
                f6 = 0.5f;
                f7 = 0.5f;
            } else {
                f9 = state2.points[i2];
                f6 = state2.points[i2 + 1];
                f7 = state2.points[i2 + 2];
                f8 = state2.points[i2 + 3];
            }
            this.mPath.moveTo(getX(f2 + ((f9 - f2) * f)), getY(f3 + ((f6 - f3) * f)));
            this.mPath.lineTo(getX(f4 + ((f7 - f4) * f)), getY(f5 + ((f8 - f5) * f)));
        }
    }

    private void updatePathWithState(Path path, State state) {
        boolean z;
        if (state.links == null) {
            int length = state.points.length / 4;
            for (int i = 0; i < length; i++) {
                int i2 = i * 4;
                path.moveTo(getX(state.points[i2]), getY(state.points[i2 + 1]));
                path.lineTo(getX(state.points[i2 + 2]), getY(state.points[i2 + 3]));
            }
            return;
        }
        for (int i3 = 0; i3 < state.links.length; i3 += 2) {
            int i4 = state.links[i3] * 4;
            int i5 = state.links[i3 + 1] * 4;
            float x = getX(state.points[i4]);
            float y = getY(state.points[i4 + 1]);
            float x2 = getX(state.points[i4 + 2]);
            float y2 = getY(state.points[i4 + 3]);
            float x3 = getX(state.points[i5]);
            float y3 = getY(state.points[i5 + 1]);
            float x4 = getX(state.points[i5 + 2]);
            float y4 = getY(state.points[i5 + 3]);
            if (x == x3 && y == y3) {
                path.moveTo(x2, y2);
                path.lineTo(x, y);
                path.lineTo(x4, y4);
            } else if (x == x4 && y == y4) {
                path.moveTo(x2, y2);
                path.lineTo(x, y);
                path.lineTo(x3, y3);
            } else if (x2 == x3 && y2 == y3) {
                path.moveTo(x, y);
                path.lineTo(x2, y2);
                path.lineTo(x4, y4);
            } else {
                path.moveTo(x, y);
                path.lineTo(x2, y2);
                path.lineTo(x3, y3);
            }
        }
        int length2 = state.points.length / 4;
        for (int i6 = 0; i6 < length2; i6++) {
            int i7 = 0;
            while (true) {
                if (i7 >= state.links.length) {
                    z = false;
                    break;
                } else {
                    if (state.links[i7] == i6) {
                        z = true;
                        break;
                    }
                    i7++;
                }
            }
            if (!z) {
                int i8 = i6 * 4;
                path.moveTo(getX(state.points[i8]), getY(state.points[i8 + 1]));
                path.lineTo(getX(state.points[i8 + 2]), getY(state.points[i8 + 3]));
            }
        }
    }

    public void cancel() {
        stop();
        setLineState(this.mCurState, 1.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int save = canvas.save();
        float f = (this.mClockwise ? Opcodes.GETFIELD : -180) * ((this.mPrevState < this.mCurState ? 0.0f : 1.0f) + this.mAnimProgress);
        if (this.mIsRtl) {
            canvas.scale(-1.0f, 1.0f, this.mDrawBound.centerX(), this.mDrawBound.centerY());
        }
        canvas.rotate(f, this.mDrawBound.centerX(), this.mDrawBound.centerY());
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restoreToCount(save);
    }

    public float getAnimProgress() {
        return this.mAnimProgress;
    }

    public int getLineState() {
        return this.mCurState;
    }

    public int getLineStateCount() {
        if (this.mStates == null) {
            return 0;
        }
        return this.mStates.length;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mRunning;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDrawBound.left = rect.left + this.mPaddingLeft;
        this.mDrawBound.top = rect.top + this.mPaddingTop;
        this.mDrawBound.right = rect.right - this.mPaddingRight;
        this.mDrawBound.bottom = rect.bottom - this.mPaddingBottom;
        updatePath();
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        this.mRunning = true;
        super.scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public boolean setLineState(int i, float f) {
        if (this.mCurState != i) {
            this.mPrevState = this.mCurState;
            this.mCurState = i;
            this.mAnimProgress = f;
            updatePath();
            return true;
        }
        if (this.mAnimProgress == f) {
            return false;
        }
        this.mAnimProgress = f;
        updatePath();
        return true;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        resetAnimation();
        scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (isRunning()) {
            this.mRunning = false;
            unscheduleSelf(this.mUpdater);
            invalidateSelf();
        }
    }

    public void switchLineState(int i, boolean z) {
        if (this.mCurState == i) {
            if (z) {
                return;
            }
            this.mAnimProgress = 1.0f;
            updatePath();
            return;
        }
        this.mPrevState = this.mCurState;
        this.mCurState = i;
        if (z) {
            start();
        } else {
            this.mAnimProgress = 1.0f;
            updatePath();
        }
    }
}
