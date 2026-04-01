package androidx.constraintlayout.core.motion.utils;

/* JADX INFO: loaded from: classes.dex */
public class SpringStopEngine implements StopEngine {
    private static final double UNSET = Double.MAX_VALUE;
    private float mLastTime;
    private double mLastVelocity;
    private float mMass;
    private float mPos;
    private double mStiffness;
    private float mStopThreshold;
    private double mTargetPos;
    private float mV;
    double mDamping = 0.5d;
    private boolean mInitialized = false;
    private int mBoundaryMode = 0;

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String str, float f) {
        return null;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return 0.0f;
    }

    void log(String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        System.out.println((".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName() + "() ") + str);
    }

    public void springConfig(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        this.mTargetPos = f2;
        this.mDamping = f6;
        this.mInitialized = false;
        this.mPos = f;
        this.mLastVelocity = f3;
        this.mStiffness = f5;
        this.mMass = f4;
        this.mStopThreshold = f7;
        this.mBoundaryMode = i;
        this.mLastTime = 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        return this.mV;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        compute(f - this.mLastTime);
        this.mLastTime = f;
        return this.mPos;
    }

    public float getAcceleration() {
        double d2 = this.mStiffness;
        return ((float) (((-d2) * (((double) this.mPos) - this.mTargetPos)) - (this.mDamping * ((double) this.mV)))) / this.mMass;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        double d2 = ((double) this.mPos) - this.mTargetPos;
        double d3 = this.mStiffness;
        double d4 = this.mV;
        return Math.sqrt((((d4 * d4) * ((double) this.mMass)) + ((d3 * d2) * d2)) / d3) <= ((double) this.mStopThreshold);
    }

    private void compute(double d2) {
        double d3 = this.mStiffness;
        double d4 = this.mDamping;
        int iSqrt = (int) ((9.0d / ((Math.sqrt(d3 / ((double) this.mMass)) * d2) * 4.0d)) + 1.0d);
        double d5 = d2 / ((double) iSqrt);
        int i = 0;
        while (i < iSqrt) {
            float f = this.mPos;
            double d6 = this.mTargetPos;
            float f2 = this.mV;
            double d7 = d3;
            double d8 = ((-d3) * (((double) f) - d6)) - (((double) f2) * d4);
            float f3 = this.mMass;
            double d9 = d4;
            double d10 = ((double) f2) + (((d8 / ((double) f3)) * d5) / 2.0d);
            double d11 = ((((-((((double) f) + ((d5 * d10) / 2.0d)) - d6)) * d7) - (d10 * d9)) / ((double) f3)) * d5;
            float f4 = (float) (((double) f2) + d11);
            this.mV = f4;
            float f5 = (float) (((double) f) + ((((double) f2) + (d11 / 2.0d)) * d5));
            this.mPos = f5;
            int i2 = this.mBoundaryMode;
            if (i2 > 0) {
                if (f5 < 0.0f && (i2 & 1) == 1) {
                    this.mPos = -f5;
                    this.mV = -f4;
                }
                float f6 = this.mPos;
                if (f6 > 1.0f && (i2 & 2) == 2) {
                    this.mPos = 2.0f - f6;
                    this.mV = -this.mV;
                }
            }
            i++;
            d3 = d7;
            d4 = d9;
        }
    }
}
