package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    Arc[] mArcs;
    private boolean mExtrapolate = true;
    private final double[] mTime;

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d2, double[] dArr) {
        if (!this.mExtrapolate) {
            if (d2 < this.mArcs[0].mTime1) {
                d2 = this.mArcs[0].mTime1;
            }
            Arc[] arcArr = this.mArcs;
            if (d2 > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d2 = arcArr2[arcArr2.length - 1].mTime2;
            }
        } else {
            if (d2 < this.mArcs[0].mTime1) {
                double d3 = this.mArcs[0].mTime1;
                double d4 = d2 - this.mArcs[0].mTime1;
                if (this.mArcs[0].linear) {
                    dArr[0] = this.mArcs[0].getLinearX(d3) + (this.mArcs[0].getLinearDX(d3) * d4);
                    dArr[1] = this.mArcs[0].getLinearY(d3) + (d4 * this.mArcs[0].getLinearDY(d3));
                    return;
                } else {
                    this.mArcs[0].setPoint(d3);
                    dArr[0] = this.mArcs[0].getX() + (this.mArcs[0].getDX() * d4);
                    dArr[1] = this.mArcs[0].getY() + (d4 * this.mArcs[0].getDY());
                    return;
                }
            }
            Arc[] arcArr3 = this.mArcs;
            if (d2 > arcArr3[arcArr3.length - 1].mTime2) {
                Arc[] arcArr4 = this.mArcs;
                double d5 = arcArr4[arcArr4.length - 1].mTime2;
                double d6 = d2 - d5;
                Arc[] arcArr5 = this.mArcs;
                int length = arcArr5.length - 1;
                if (arcArr5[length].linear) {
                    dArr[0] = this.mArcs[length].getLinearX(d5) + (this.mArcs[length].getLinearDX(d5) * d6);
                    dArr[1] = this.mArcs[length].getLinearY(d5) + (d6 * this.mArcs[length].getLinearDY(d5));
                    return;
                } else {
                    this.mArcs[length].setPoint(d2);
                    dArr[0] = this.mArcs[length].getX() + (this.mArcs[length].getDX() * d6);
                    dArr[1] = this.mArcs[length].getY() + (d6 * this.mArcs[length].getDY());
                    return;
                }
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr6 = this.mArcs;
            if (i >= arcArr6.length) {
                return;
            }
            if (d2 <= arcArr6[i].mTime2) {
                if (this.mArcs[i].linear) {
                    dArr[0] = this.mArcs[i].getLinearX(d2);
                    dArr[1] = this.mArcs[i].getLinearY(d2);
                    return;
                } else {
                    this.mArcs[i].setPoint(d2);
                    dArr[0] = this.mArcs[i].getX();
                    dArr[1] = this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d2, float[] fArr) {
        if (this.mExtrapolate) {
            if (d2 < this.mArcs[0].mTime1) {
                double d3 = this.mArcs[0].mTime1;
                double d4 = d2 - this.mArcs[0].mTime1;
                if (this.mArcs[0].linear) {
                    fArr[0] = (float) (this.mArcs[0].getLinearX(d3) + (this.mArcs[0].getLinearDX(d3) * d4));
                    fArr[1] = (float) (this.mArcs[0].getLinearY(d3) + (d4 * this.mArcs[0].getLinearDY(d3)));
                    return;
                } else {
                    this.mArcs[0].setPoint(d3);
                    fArr[0] = (float) (this.mArcs[0].getX() + (this.mArcs[0].getDX() * d4));
                    fArr[1] = (float) (this.mArcs[0].getY() + (d4 * this.mArcs[0].getDY()));
                    return;
                }
            }
            Arc[] arcArr = this.mArcs;
            if (d2 > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                double d5 = arcArr2[arcArr2.length - 1].mTime2;
                double d6 = d2 - d5;
                Arc[] arcArr3 = this.mArcs;
                int length = arcArr3.length - 1;
                if (arcArr3[length].linear) {
                    fArr[0] = (float) (this.mArcs[length].getLinearX(d5) + (this.mArcs[length].getLinearDX(d5) * d6));
                    fArr[1] = (float) (this.mArcs[length].getLinearY(d5) + (d6 * this.mArcs[length].getLinearDY(d5)));
                    return;
                } else {
                    this.mArcs[length].setPoint(d2);
                    fArr[0] = (float) this.mArcs[length].getX();
                    fArr[1] = (float) this.mArcs[length].getY();
                    return;
                }
            }
        } else if (d2 < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr4 = this.mArcs;
            if (d2 > arcArr4[arcArr4.length - 1].mTime2) {
                Arc[] arcArr5 = this.mArcs;
                d2 = arcArr5[arcArr5.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr6 = this.mArcs;
            if (i >= arcArr6.length) {
                return;
            }
            if (d2 <= arcArr6[i].mTime2) {
                if (this.mArcs[i].linear) {
                    fArr[0] = (float) this.mArcs[i].getLinearX(d2);
                    fArr[1] = (float) this.mArcs[i].getLinearY(d2);
                    return;
                } else {
                    this.mArcs[i].setPoint(d2);
                    fArr[0] = (float) this.mArcs[i].getX();
                    fArr[1] = (float) this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d2, double[] dArr) {
        if (d2 < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (d2 > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d2 = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d2 <= arcArr3[i].mTime2) {
                if (this.mArcs[i].linear) {
                    dArr[0] = this.mArcs[i].getLinearDX(d2);
                    dArr[1] = this.mArcs[i].getLinearDY(d2);
                    return;
                } else {
                    this.mArcs[i].setPoint(d2);
                    dArr[0] = this.mArcs[i].getDX();
                    dArr[1] = this.mArcs[i].getDY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d2, int i) {
        double d3;
        double linearY;
        double linearDY;
        double y;
        double dy;
        int i2 = 0;
        if (this.mExtrapolate) {
            if (d2 < this.mArcs[0].mTime1) {
                double d4 = this.mArcs[0].mTime1;
                d3 = d2 - this.mArcs[0].mTime1;
                if (!this.mArcs[0].linear) {
                    this.mArcs[0].setPoint(d4);
                    if (i == 0) {
                        y = this.mArcs[0].getX();
                        dy = this.mArcs[0].getDX();
                    } else {
                        y = this.mArcs[0].getY();
                        dy = this.mArcs[0].getDY();
                    }
                    return y + (d3 * dy);
                }
                if (i == 0) {
                    linearY = this.mArcs[0].getLinearX(d4);
                    linearDY = this.mArcs[0].getLinearDX(d4);
                } else {
                    linearY = this.mArcs[0].getLinearY(d4);
                    linearDY = this.mArcs[0].getLinearDY(d4);
                }
            } else {
                if (d2 > this.mArcs[r0.length - 1].mTime2) {
                    double d5 = this.mArcs[r0.length - 1].mTime2;
                    d3 = d2 - d5;
                    Arc[] arcArr = this.mArcs;
                    int length = arcArr.length - 1;
                    if (i == 0) {
                        linearY = arcArr[length].getLinearX(d5);
                        linearDY = this.mArcs[length].getLinearDX(d5);
                    } else {
                        linearY = arcArr[length].getLinearY(d5);
                        linearDY = this.mArcs[length].getLinearDY(d5);
                    }
                }
            }
            return linearY + (d3 * linearDY);
        }
        if (d2 < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
        } else {
            if (d2 > this.mArcs[r0.length - 1].mTime2) {
                d2 = this.mArcs[r7.length - 1].mTime2;
            }
        }
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i2 >= arcArr2.length) {
                return Double.NaN;
            }
            if (d2 <= arcArr2[i2].mTime2) {
                if (this.mArcs[i2].linear) {
                    if (i == 0) {
                        return this.mArcs[i2].getLinearX(d2);
                    }
                    return this.mArcs[i2].getLinearY(d2);
                }
                this.mArcs[i2].setPoint(d2);
                if (i == 0) {
                    return this.mArcs[i2].getX();
                }
                return this.mArcs[i2].getY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d2, int i) {
        int i2 = 0;
        if (d2 < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
        }
        if (d2 > this.mArcs[r0.length - 1].mTime2) {
            d2 = this.mArcs[r5.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i2 >= arcArr.length) {
                return Double.NaN;
            }
            if (d2 <= arcArr[i2].mTime2) {
                if (this.mArcs[i2].linear) {
                    if (i == 0) {
                        return this.mArcs[i2].getLinearDX(d2);
                    }
                    return this.mArcs[i2].getLinearDY(d2);
                }
                this.mArcs[i2].setPoint(d2);
                if (i == 0) {
                    return this.mArcs[i2].getDX();
                }
                return this.mArcs[i2].getDY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArcCurveFit(int[] r25, double[] r26, double[][] r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r26
            r24.<init>()
            r2 = 1
            r0.mExtrapolate = r2
            r0.mTime = r1
            int r3 = r1.length
            int r3 = r3 - r2
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r3 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit.Arc[r3]
            r0.mArcs = r3
            r3 = 0
            r5 = r2
            r6 = r5
            r4 = r3
        L16:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r7 = r0.mArcs
            int r8 = r7.length
            if (r4 >= r8) goto L4f
            r8 = r25[r4]
            r9 = 3
            if (r8 == 0) goto L2f
            if (r8 == r2) goto L2c
            r10 = 2
            if (r8 == r10) goto L2a
            if (r8 == r9) goto L28
            goto L30
        L28:
            if (r5 != r2) goto L2c
        L2a:
            r5 = r10
            goto L2d
        L2c:
            r5 = r2
        L2d:
            r6 = r5
            goto L30
        L2f:
            r6 = r9
        L30:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc r22 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc
            r10 = r1[r4]
            int r23 = r4 + 1
            r12 = r1[r23]
            r8 = r27[r4]
            r14 = r8[r3]
            r16 = r8[r2]
            r8 = r27[r23]
            r18 = r8[r3]
            r20 = r8[r2]
            r8 = r22
            r9 = r6
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r22
            r4 = r23
            goto L16
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.ArcCurveFit.<init>(int[], double[], double[][]):void");
    }

    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];
        boolean linear;
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int i, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.linear = false;
            this.mVertical = i == 1;
            this.mTime1 = d2;
            this.mTime2 = d3;
            this.mOneOverDeltaTime = 1.0d / (d3 - d2);
            if (3 == i) {
                this.linear = true;
            }
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (this.linear || Math.abs(d8) < EPSILON || Math.abs(d9) < EPSILON) {
                this.linear = true;
                this.mX1 = d4;
                this.mX2 = d6;
                this.mY1 = d5;
                this.mY2 = d7;
                double dHypot = Math.hypot(d9, d8);
                this.mArcDistance = dHypot;
                this.mArcVelocity = dHypot * this.mOneOverDeltaTime;
                double d10 = this.mTime2;
                double d11 = this.mTime1;
                this.mEllipseCenterX = d8 / (d10 - d11);
                this.mEllipseCenterY = d9 / (d10 - d11);
                return;
            }
            this.mLut = new double[101];
            boolean z = this.mVertical;
            this.mEllipseA = d8 * ((double) (z ? -1 : 1));
            this.mEllipseB = d9 * ((double) (z ? 1 : -1));
            this.mEllipseCenterX = z ? d6 : d4;
            this.mEllipseCenterY = z ? d5 : d7;
            buildTable(d4, d5, d6, d7);
            this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
        }

        void setPoint(double d2) {
            double dLookup = lookup((this.mVertical ? this.mTime2 - d2 : d2 - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(dLookup);
            this.mTmpCosAngle = Math.cos(dLookup);
        }

        double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        double getDX() {
            double d2 = this.mEllipseA * this.mTmpCosAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d2, (-this.mEllipseB) * this.mTmpSinAngle);
            if (this.mVertical) {
                d2 = -d2;
            }
            return d2 * dHypot;
        }

        double getDY() {
            double d2 = this.mEllipseA * this.mTmpCosAngle;
            double d3 = (-this.mEllipseB) * this.mTmpSinAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d2, d3);
            return this.mVertical ? (-d3) * dHypot : d3 * dHypot;
        }

        public double getLinearX(double d2) {
            double d3 = (d2 - this.mTime1) * this.mOneOverDeltaTime;
            double d4 = this.mX1;
            return d4 + (d3 * (this.mX2 - d4));
        }

        public double getLinearY(double d2) {
            double d3 = (d2 - this.mTime1) * this.mOneOverDeltaTime;
            double d4 = this.mY1;
            return d4 + (d3 * (this.mY2 - d4));
        }

        public double getLinearDX(double d2) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d2) {
            return this.mEllipseCenterY;
        }

        double lookup(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = d2 * ((double) (dArr.length - 1));
            int i = (int) length;
            double d3 = length - ((double) i);
            double d4 = dArr[i];
            return d4 + (d3 * (dArr[i + 1] - d4));
        }

        private void buildTable(double d2, double d3, double d4, double d5) {
            double dHypot;
            double d6 = d4 - d2;
            double d7 = d3 - d5;
            int i = 0;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            while (true) {
                if (i >= ourPercent.length) {
                    break;
                }
                double d11 = d8;
                double radians = Math.toRadians((((double) i) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d6;
                double dCos = Math.cos(radians) * d7;
                if (i > 0) {
                    dHypot = Math.hypot(dSin - d9, dCos - d10) + d11;
                    ourPercent[i] = dHypot;
                } else {
                    dHypot = d11;
                }
                i++;
                d10 = dCos;
                d8 = dHypot;
                d9 = dSin;
            }
            double d12 = d8;
            this.mArcDistance = d12;
            int i2 = 0;
            while (true) {
                double[] dArr = ourPercent;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] / d12;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.mLut.length) {
                    return;
                }
                double length = ((double) i3) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(ourPercent, length);
                if (iBinarySearch >= 0) {
                    this.mLut[i3] = ((double) iBinarySearch) / ((double) (ourPercent.length - 1));
                } else if (iBinarySearch == -1) {
                    this.mLut[i3] = 0.0d;
                } else {
                    int i4 = -iBinarySearch;
                    int i5 = i4 - 2;
                    double[] dArr2 = ourPercent;
                    double d13 = dArr2[i5];
                    this.mLut[i3] = (((double) i5) + ((length - d13) / (dArr2[i4 - 1] - d13))) / ((double) (dArr2.length - 1));
                }
                i3++;
            }
        }
    }
}
