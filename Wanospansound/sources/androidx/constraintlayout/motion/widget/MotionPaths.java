package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    LinkedHashMap<String, ConstraintAttribute> attributes;
    float height;
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo;
    int mDrawPath;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPathRotate;
    float mProgress;
    float mRelativeAngle;
    MotionController mRelativeToController;
    double[] mTempDelta;
    double[] mTempValue;
    float position;
    float time;
    float width;
    float x;
    float y;

    private static final float xRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return (((x - cx) * cos) - ((y - cy) * sin)) + cx;
    }

    private static final float yRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return ((x - cx) * sin) + ((y - cy) * cos) + cy;
    }

    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    void initCartesian(KeyPosition c2, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float f = c2.mFramePosition / 100.0f;
        this.time = f;
        this.mDrawPath = c2.mDrawPath;
        float f2 = Float.isNaN(c2.mPercentWidth) ? f : c2.mPercentWidth;
        float f3 = Float.isNaN(c2.mPercentHeight) ? f : c2.mPercentHeight;
        float f4 = endTimePoint.width;
        float f5 = startTimePoint.width;
        float f6 = endTimePoint.height;
        float f7 = startTimePoint.height;
        this.position = this.time;
        float f8 = startTimePoint.x;
        float f9 = startTimePoint.y;
        float f10 = (endTimePoint.x + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (endTimePoint.y + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = ((f4 - f5) * f2) / 2.0f;
        this.x = (int) ((f8 + (f10 * f)) - f12);
        float f13 = ((f6 - f7) * f3) / 2.0f;
        this.y = (int) ((f9 + (f11 * f)) - f13);
        this.width = (int) (f5 + r9);
        this.height = (int) (f7 + r12);
        float f14 = Float.isNaN(c2.mPercentX) ? f : c2.mPercentX;
        float f15 = Float.isNaN(c2.mAltPercentY) ? 0.0f : c2.mAltPercentY;
        if (!Float.isNaN(c2.mPercentY)) {
            f = c2.mPercentY;
        }
        float f16 = Float.isNaN(c2.mAltPercentX) ? 0.0f : c2.mAltPercentX;
        this.mMode = 0;
        this.x = (int) (((startTimePoint.x + (f14 * f10)) + (f16 * f11)) - f12);
        this.y = (int) (((startTimePoint.y + (f10 * f15)) + (f11 * f)) - f13);
        this.mKeyFrameEasing = Easing.getInterpolator(c2.mTransitionEasing);
        this.mPathMotionArc = c2.mPathMotionArc;
    }

    public MotionPaths(int parentWidth, int parentHeight, KeyPosition c2, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        if (startTimePoint.mAnimateRelativeTo != Key.UNSET) {
            initPolar(parentWidth, parentHeight, c2, startTimePoint, endTimePoint);
            return;
        }
        int i = c2.mPositionType;
        if (i == 1) {
            initPath(c2, startTimePoint, endTimePoint);
        } else if (i == 2) {
            initScreen(parentWidth, parentHeight, c2, startTimePoint, endTimePoint);
        } else {
            initCartesian(c2, startTimePoint, endTimePoint);
        }
    }

    void initPolar(int parentWidth, int parentHeight, KeyPosition c2, MotionPaths s, MotionPaths e) {
        float fMin;
        float f;
        float f2 = c2.mFramePosition / 100.0f;
        this.time = f2;
        this.mDrawPath = c2.mDrawPath;
        this.mMode = c2.mPositionType;
        float f3 = Float.isNaN(c2.mPercentWidth) ? f2 : c2.mPercentWidth;
        float f4 = Float.isNaN(c2.mPercentHeight) ? f2 : c2.mPercentHeight;
        float f5 = e.width;
        float f6 = s.width;
        float f7 = e.height;
        float f8 = s.height;
        this.position = this.time;
        this.width = (int) (f6 + ((f5 - f6) * f3));
        this.height = (int) (f8 + ((f7 - f8) * f4));
        int i = c2.mPositionType;
        if (i == 1) {
            float f9 = Float.isNaN(c2.mPercentX) ? f2 : c2.mPercentX;
            float f10 = e.x;
            float f11 = s.x;
            this.x = (f9 * (f10 - f11)) + f11;
            if (!Float.isNaN(c2.mPercentY)) {
                f2 = c2.mPercentY;
            }
            float f12 = e.y;
            float f13 = s.y;
            this.y = (f2 * (f12 - f13)) + f13;
        } else if (i == 2) {
            if (Float.isNaN(c2.mPercentX)) {
                float f14 = e.x;
                float f15 = s.x;
                fMin = ((f14 - f15) * f2) + f15;
            } else {
                fMin = Math.min(f4, f3) * c2.mPercentX;
            }
            this.x = fMin;
            if (Float.isNaN(c2.mPercentY)) {
                float f16 = e.y;
                float f17 = s.y;
                f = (f2 * (f16 - f17)) + f17;
            } else {
                f = c2.mPercentY;
            }
            this.y = f;
        } else {
            float f18 = Float.isNaN(c2.mPercentX) ? f2 : c2.mPercentX;
            float f19 = e.x;
            float f20 = s.x;
            this.x = (f18 * (f19 - f20)) + f20;
            if (!Float.isNaN(c2.mPercentY)) {
                f2 = c2.mPercentY;
            }
            float f21 = e.y;
            float f22 = s.y;
            this.y = (f2 * (f21 - f22)) + f22;
        }
        this.mAnimateRelativeTo = s.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c2.mTransitionEasing);
        this.mPathMotionArc = c2.mPathMotionArc;
    }

    public void setupRelative(MotionController mc, MotionPaths relative) {
        double d2 = ((this.x + (this.width / 2.0f)) - relative.x) - (relative.width / 2.0f);
        double d3 = ((this.y + (this.height / 2.0f)) - relative.y) - (relative.height / 2.0f);
        this.mRelativeToController = mc;
        this.x = (float) Math.hypot(d3, d2);
        if (Float.isNaN(this.mRelativeAngle)) {
            this.y = (float) (Math.atan2(d3, d2) + 1.5707963267948966d);
        } else {
            this.y = (float) Math.toRadians(this.mRelativeAngle);
        }
    }

    void initScreen(int parentWidth, int parentHeight, KeyPosition c2, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float f = c2.mFramePosition / 100.0f;
        this.time = f;
        this.mDrawPath = c2.mDrawPath;
        float f2 = Float.isNaN(c2.mPercentWidth) ? f : c2.mPercentWidth;
        float f3 = Float.isNaN(c2.mPercentHeight) ? f : c2.mPercentHeight;
        float f4 = endTimePoint.width;
        float f5 = startTimePoint.width;
        float f6 = endTimePoint.height;
        float f7 = startTimePoint.height;
        this.position = this.time;
        float f8 = startTimePoint.x;
        float f9 = startTimePoint.y;
        float f10 = endTimePoint.x + (f4 / 2.0f);
        float f11 = endTimePoint.y + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.x = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.y = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.width = (int) (f5 + f12);
        this.height = (int) (f7 + f13);
        this.mMode = 2;
        if (!Float.isNaN(c2.mPercentX)) {
            this.x = (int) (c2.mPercentX * ((int) (parentWidth - this.width)));
        }
        if (!Float.isNaN(c2.mPercentY)) {
            this.y = (int) (c2.mPercentY * ((int) (parentHeight - this.height)));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c2.mTransitionEasing);
        this.mPathMotionArc = c2.mPathMotionArc;
    }

    void initPath(KeyPosition c2, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float f = c2.mFramePosition / 100.0f;
        this.time = f;
        this.mDrawPath = c2.mDrawPath;
        float f2 = Float.isNaN(c2.mPercentWidth) ? f : c2.mPercentWidth;
        float f3 = Float.isNaN(c2.mPercentHeight) ? f : c2.mPercentHeight;
        float f4 = endTimePoint.width - startTimePoint.width;
        float f5 = endTimePoint.height - startTimePoint.height;
        this.position = this.time;
        if (!Float.isNaN(c2.mPercentX)) {
            f = c2.mPercentX;
        }
        float f6 = startTimePoint.x;
        float f7 = startTimePoint.width;
        float f8 = startTimePoint.y;
        float f9 = startTimePoint.height;
        float f10 = (endTimePoint.x + (endTimePoint.width / 2.0f)) - ((f7 / 2.0f) + f6);
        float f11 = (endTimePoint.y + (endTimePoint.height / 2.0f)) - ((f9 / 2.0f) + f8);
        float f12 = f10 * f;
        float f13 = (f4 * f2) / 2.0f;
        this.x = (int) ((f6 + f12) - f13);
        float f14 = f * f11;
        float f15 = (f5 * f3) / 2.0f;
        this.y = (int) ((f8 + f14) - f15);
        this.width = (int) (f7 + r7);
        this.height = (int) (f9 + r8);
        float f16 = Float.isNaN(c2.mPercentY) ? 0.0f : c2.mPercentY;
        this.mMode = 1;
        float f17 = (int) ((startTimePoint.x + f12) - f13);
        this.x = f17;
        float f18 = (int) ((startTimePoint.y + f14) - f15);
        this.x = f17 + ((-f11) * f16);
        this.y = f18 + (f10 * f16);
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(c2.mTransitionEasing);
        this.mPathMotionArc = c2.mPathMotionArc;
    }

    private boolean diff(float a2, float b2) {
        return (Float.isNaN(a2) || Float.isNaN(b2)) ? Float.isNaN(a2) != Float.isNaN(b2) : Math.abs(a2 - b2) > 1.0E-6f;
    }

    void different(MotionPaths points, boolean[] mask, String[] custom, boolean arcMode) {
        boolean zDiff = diff(this.x, points.x);
        boolean zDiff2 = diff(this.y, points.y);
        mask[0] = mask[0] | diff(this.position, points.position);
        boolean z = zDiff | zDiff2 | arcMode;
        mask[1] = mask[1] | z;
        mask[2] = z | mask[2];
        mask[3] = mask[3] | diff(this.width, points.width);
        mask[4] = diff(this.height, points.height) | mask[4];
    }

    void getCenter(double p, int[] toUse, double[] data, float[] point, int offset) {
        float fSin = this.x;
        float fCos = this.y;
        float f = this.width;
        float f2 = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float f3 = (float) data[i];
            int i2 = toUse[i];
            if (i2 == 1) {
                fSin = f3;
            } else if (i2 == 2) {
                fCos = f3;
            } else if (i2 == 3) {
                f = f3;
            } else if (i2 == 4) {
                f2 = f3;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr = new float[2];
            motionController.getCenter(p, fArr, new float[2]);
            float f4 = fArr[0];
            float f5 = fArr[1];
            double d2 = f4;
            double d3 = fSin;
            double d4 = fCos;
            fSin = (float) ((d2 + (Math.sin(d4) * d3)) - ((double) (f / 2.0f)));
            fCos = (float) ((((double) f5) - (d3 * Math.cos(d4))) - ((double) (f2 / 2.0f)));
        }
        point[offset] = fSin + (f / 2.0f) + 0.0f;
        point[offset + 1] = fCos + (f2 / 2.0f) + 0.0f;
    }

    void getCenter(double p, int[] toUse, double[] data, float[] point, double[] vdata, float[] velocity) {
        float f;
        float f2 = this.x;
        float f3 = this.y;
        float f4 = this.width;
        float f5 = this.height;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float f10 = (float) data[i];
            float f11 = (float) vdata[i];
            int i2 = toUse[i];
            if (i2 == 1) {
                f2 = f10;
                f6 = f11;
            } else if (i2 == 2) {
                f3 = f10;
                f8 = f11;
            } else if (i2 == 3) {
                f4 = f10;
                f7 = f11;
            } else if (i2 == 4) {
                f5 = f10;
                f9 = f11;
            }
        }
        float f12 = 2.0f;
        float f13 = (f7 / 2.0f) + f6;
        float fCos = (f9 / 2.0f) + f8;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motionController.getCenter(p, fArr, fArr2);
            float f14 = fArr[0];
            float f15 = fArr[1];
            float f16 = fArr2[0];
            float f17 = fArr2[1];
            double d2 = f2;
            double d3 = f3;
            f = f4;
            float fSin = (float) ((((double) f14) + (Math.sin(d3) * d2)) - ((double) (f4 / 2.0f)));
            float fCos2 = (float) ((((double) f15) - (d2 * Math.cos(d3))) - ((double) (f5 / 2.0f)));
            double d4 = f6;
            double d5 = f8;
            float fSin2 = (float) (((double) f16) + (Math.sin(d3) * d4) + (Math.cos(d3) * d5));
            fCos = (float) ((((double) f17) - (d4 * Math.cos(d3))) + (Math.sin(d3) * d5));
            f13 = fSin2;
            f2 = fSin;
            f3 = fCos2;
            f12 = 2.0f;
        } else {
            f = f4;
        }
        point[0] = f2 + (f / f12) + 0.0f;
        point[1] = f3 + (f5 / f12) + 0.0f;
        velocity[0] = f13;
        velocity[1] = fCos;
    }

    void getCenterVelocity(double p, int[] toUse, double[] data, float[] point, int offset) {
        float fSin = this.x;
        float fCos = this.y;
        float f = this.width;
        float f2 = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float f3 = (float) data[i];
            int i2 = toUse[i];
            if (i2 == 1) {
                fSin = f3;
            } else if (i2 == 2) {
                fCos = f3;
            } else if (i2 == 3) {
                f = f3;
            } else if (i2 == 4) {
                f2 = f3;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr = new float[2];
            motionController.getCenter(p, fArr, new float[2]);
            float f4 = fArr[0];
            float f5 = fArr[1];
            double d2 = f4;
            double d3 = fSin;
            double d4 = fCos;
            fSin = (float) ((d2 + (Math.sin(d4) * d3)) - ((double) (f / 2.0f)));
            fCos = (float) ((((double) f5) - (d3 * Math.cos(d4))) - ((double) (f2 / 2.0f)));
        }
        point[offset] = fSin + (f / 2.0f) + 0.0f;
        point[offset + 1] = fCos + (f2 / 2.0f) + 0.0f;
    }

    void getBounds(int[] toUse, double[] data, float[] point, int offset) {
        float f = this.width;
        float f2 = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float f3 = (float) data[i];
            int i2 = toUse[i];
            if (i2 == 3) {
                f = f3;
            } else if (i2 == 4) {
                f2 = f3;
            }
        }
        point[offset] = f;
        point[offset + 1] = f2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void setView(float position, View view, int[] toUse, double[] data, double[] slope, double[] cycle, boolean mForceMeasure) {
        float f;
        float f2;
        boolean z;
        boolean z2;
        float f3;
        float f4 = this.x;
        float f5 = this.y;
        float f6 = this.width;
        float f7 = this.height;
        if (toUse.length != 0 && this.mTempValue.length <= toUse[toUse.length - 1]) {
            int i = toUse[toUse.length - 1] + 1;
            this.mTempValue = new double[i];
            this.mTempDelta = new double[i];
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i2 = 0; i2 < toUse.length; i2++) {
            double[] dArr = this.mTempValue;
            int i3 = toUse[i2];
            dArr[i3] = data[i2];
            this.mTempDelta[i3] = slope[i2];
        }
        float f8 = Float.NaN;
        int i4 = 0;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        while (true) {
            double[] dArr2 = this.mTempValue;
            if (i4 >= dArr2.length) {
                break;
            }
            if (Double.isNaN(dArr2[i4]) && (cycle == null || cycle[i4] == 0.0d)) {
                f3 = f8;
            } else {
                double d2 = cycle != null ? cycle[i4] : 0.0d;
                if (!Double.isNaN(this.mTempValue[i4])) {
                    d2 = this.mTempValue[i4] + d2;
                }
                f3 = f8;
                float f13 = (float) d2;
                float f14 = (float) this.mTempDelta[i4];
                if (i4 == 1) {
                    f8 = f3;
                    f4 = f13;
                    f9 = f14;
                } else if (i4 == 2) {
                    f8 = f3;
                    f5 = f13;
                    f10 = f14;
                } else if (i4 == 3) {
                    f8 = f3;
                    f6 = f13;
                    f11 = f14;
                } else if (i4 == 4) {
                    f8 = f3;
                    f7 = f13;
                    f12 = f14;
                } else if (i4 == 5) {
                    f8 = f13;
                }
                i4++;
            }
            f8 = f3;
            i4++;
        }
        float f15 = f8;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motionController.getCenter(position, fArr, fArr2);
            float f16 = fArr[0];
            float f17 = fArr[1];
            float f18 = fArr2[0];
            float f19 = fArr2[1];
            double d3 = f4;
            double d4 = f5;
            float fSin = (float) ((((double) f16) + (Math.sin(d4) * d3)) - ((double) (f6 / 2.0f)));
            float fCos = (float) ((((double) f17) - (Math.cos(d4) * d3)) - ((double) (f7 / 2.0f)));
            double d5 = f9;
            f = f6;
            f2 = f7;
            double d6 = f10;
            float fSin2 = (float) (((double) f18) + (Math.sin(d4) * d5) + (Math.cos(d4) * d3 * d6));
            float fCos2 = (float) ((((double) f19) - (d5 * Math.cos(d4))) + (d3 * Math.sin(d4) * d6));
            if (slope.length >= 2) {
                z = false;
                slope[0] = fSin2;
                z2 = true;
                slope[1] = fCos2;
            } else {
                z = false;
                z2 = true;
            }
            if (!Float.isNaN(f15)) {
                view.setRotation((float) (((double) f15) + Math.toDegrees(Math.atan2(fCos2, fSin2))));
            }
            f4 = fSin;
            f5 = fCos;
        } else {
            f = f6;
            f2 = f7;
            z = false;
            z2 = true;
            if (!Float.isNaN(f15)) {
                view.setRotation((float) (((double) 0.0f) + ((double) f15) + Math.toDegrees(Math.atan2(f10 + (f12 / 2.0f), f9 + (f11 / 2.0f)))));
            }
        }
        if (view instanceof FloatLayout) {
            ((FloatLayout) view).layout(f4, f5, f4 + f, f5 + f2);
            return;
        }
        float f20 = f4 + 0.5f;
        int i5 = (int) f20;
        float f21 = f5 + 0.5f;
        int i6 = (int) f21;
        int i7 = (int) (f20 + f);
        int i8 = (int) (f21 + f2);
        int i9 = i7 - i5;
        int i10 = i8 - i6;
        if (((i9 == view.getMeasuredWidth() && i10 == view.getMeasuredHeight()) ? z : z2) || mForceMeasure) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i9, 1073741824), View.MeasureSpec.makeMeasureSpec(i10, 1073741824));
        }
        view.layout(i5, i6, i7, i8);
    }

    void getRect(int[] toUse, double[] data, float[] path, int offset) {
        float f = this.x;
        float fCos = this.y;
        float f2 = this.width;
        float f3 = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float f4 = (float) data[i];
            int i2 = toUse[i];
            if (i2 == 1) {
                f = f4;
            } else if (i2 == 2) {
                fCos = f4;
            } else if (i2 == 3) {
                f2 = f4;
            } else if (i2 == 4) {
                f3 = f4;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float centerX = motionController.getCenterX();
            float centerY = this.mRelativeToController.getCenterY();
            double d2 = f;
            double d3 = fCos;
            float fSin = (float) ((((double) centerX) + (Math.sin(d3) * d2)) - ((double) (f2 / 2.0f)));
            fCos = (float) ((((double) centerY) - (d2 * Math.cos(d3))) - ((double) (f3 / 2.0f)));
            f = fSin;
        }
        float f5 = f2 + f;
        float f6 = f3 + fCos;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i3 = offset + 1;
        path[offset] = f + 0.0f;
        int i4 = i3 + 1;
        path[i3] = fCos + 0.0f;
        int i5 = i4 + 1;
        path[i4] = f5 + 0.0f;
        int i6 = i5 + 1;
        path[i5] = fCos + 0.0f;
        int i7 = i6 + 1;
        path[i6] = f5 + 0.0f;
        int i8 = i7 + 1;
        path[i7] = f6 + 0.0f;
        path[i8] = f + 0.0f;
        path[i8 + 1] = f6 + 0.0f;
    }

    void setDpDt(float locationX, float locationY, float[] mAnchorDpDt, int[] toUse, double[] deltaData, double[] data) {
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float f5 = (float) deltaData[i];
            double d2 = data[i];
            int i2 = toUse[i];
            if (i2 == 1) {
                f = f5;
            } else if (i2 == 2) {
                f3 = f5;
            } else if (i2 == 3) {
                f2 = f5;
            } else if (i2 == 4) {
                f4 = f5;
            }
        }
        float f6 = f - ((0.0f * f2) / 2.0f);
        float f7 = f3 - ((0.0f * f4) / 2.0f);
        mAnchorDpDt[0] = (f6 * (1.0f - locationX)) + (((f2 * 1.0f) + f6) * locationX) + 0.0f;
        mAnchorDpDt[1] = (f7 * (1.0f - locationY)) + (((f4 * 1.0f) + f7) * locationY) + 0.0f;
    }

    void fillStandard(double[] data, int[] toUse) {
        float[] fArr = {this.position, this.x, this.y, this.width, this.height, this.mPathRotate};
        int i = 0;
        for (int i2 : toUse) {
            if (i2 < 6) {
                data[i] = fArr[r4];
                i++;
            }
        }
    }

    boolean hasCustomData(String name) {
        return this.attributes.containsKey(name);
    }

    int getCustomDataCount(String name) {
        ConstraintAttribute constraintAttribute = this.attributes.get(name);
        if (constraintAttribute == null) {
            return 0;
        }
        return constraintAttribute.numberOfInterpolatedValues();
    }

    int getCustomData(String name, double[] value, int offset) {
        ConstraintAttribute constraintAttribute = this.attributes.get(name);
        int i = 0;
        if (constraintAttribute == null) {
            return 0;
        }
        if (constraintAttribute.numberOfInterpolatedValues() == 1) {
            value[offset] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int iNumberOfInterpolatedValues = constraintAttribute.numberOfInterpolatedValues();
        constraintAttribute.getValuesToInterpolate(new float[iNumberOfInterpolatedValues]);
        while (i < iNumberOfInterpolatedValues) {
            value[offset] = r2[i];
            i++;
            offset++;
        }
        return iNumberOfInterpolatedValues;
    }

    void setBounds(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionPaths o) {
        return Float.compare(this.position, o.position);
    }

    public void applyParameters(ConstraintSet.Constraint c2) {
        this.mKeyFrameEasing = Easing.getInterpolator(c2.motion.mTransitionEasing);
        this.mPathMotionArc = c2.motion.mPathMotionArc;
        this.mAnimateRelativeTo = c2.motion.mAnimateRelativeTo;
        this.mPathRotate = c2.motion.mPathRotate;
        this.mDrawPath = c2.motion.mDrawPath;
        this.mAnimateCircleAngleTo = c2.motion.mAnimateCircleAngleTo;
        this.mProgress = c2.propertySet.mProgress;
        this.mRelativeAngle = c2.layout.circleAngle;
        for (String str : c2.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = c2.mCustomConstraints.get(str);
            if (constraintAttribute != null && constraintAttribute.isContinuous()) {
                this.attributes.put(str, constraintAttribute);
            }
        }
    }

    public void configureRelativeTo(MotionController toOrbit) {
        toOrbit.getPos(this.mProgress);
    }
}
