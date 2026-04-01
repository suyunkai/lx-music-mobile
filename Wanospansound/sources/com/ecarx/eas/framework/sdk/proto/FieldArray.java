package com.ecarx.eas.framework.sdk.proto;

/* JADX INFO: loaded from: classes2.dex */
public final class FieldArray implements Cloneable {
    private static final a DELETED = new a();
    private a[] mData;
    private int[] mFieldNumbers;
    private boolean mGarbage;
    private int mSize;

    private int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                return i3;
            }
        }
        return i;
    }

    FieldArray() {
        this(10);
    }

    FieldArray(int i) {
        this.mGarbage = false;
        int iIdealIntArraySize = idealIntArraySize(i);
        this.mFieldNumbers = new int[iIdealIntArraySize];
        this.mData = new a[iIdealIntArraySize];
        this.mSize = 0;
    }

    final a get(int i) {
        a aVar;
        int iBinarySearch = binarySearch(i);
        if (iBinarySearch < 0 || (aVar = this.mData[iBinarySearch]) == DELETED) {
            return null;
        }
        return aVar;
    }

    final void remove(int i) {
        int iBinarySearch = binarySearch(i);
        if (iBinarySearch >= 0) {
            a[] aVarArr = this.mData;
            a aVar = aVarArr[iBinarySearch];
            a aVar2 = DELETED;
            if (aVar != aVar2) {
                aVarArr[iBinarySearch] = aVar2;
                this.mGarbage = true;
            }
        }
    }

    private void gc() {
        int i = this.mSize;
        int[] iArr = this.mFieldNumbers;
        a[] aVarArr = this.mData;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            a aVar = aVarArr[i3];
            if (aVar != DELETED) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    aVarArr[i2] = aVar;
                    aVarArr[i3] = null;
                }
                i2++;
            }
        }
        this.mGarbage = false;
        this.mSize = i2;
    }

    final void put(int i, a aVar) {
        int iBinarySearch = binarySearch(i);
        if (iBinarySearch >= 0) {
            this.mData[iBinarySearch] = aVar;
            return;
        }
        int i2 = ~iBinarySearch;
        int i3 = this.mSize;
        if (i2 < i3) {
            a[] aVarArr = this.mData;
            if (aVarArr[i2] == DELETED) {
                this.mFieldNumbers[i2] = i;
                aVarArr[i2] = aVar;
                return;
            }
        }
        if (this.mGarbage && i3 >= this.mFieldNumbers.length) {
            gc();
            i2 = ~binarySearch(i);
        }
        int i4 = this.mSize;
        if (i4 >= this.mFieldNumbers.length) {
            int iIdealIntArraySize = idealIntArraySize(i4 + 1);
            int[] iArr = new int[iIdealIntArraySize];
            a[] aVarArr2 = new a[iIdealIntArraySize];
            int[] iArr2 = this.mFieldNumbers;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            a[] aVarArr3 = this.mData;
            System.arraycopy(aVarArr3, 0, aVarArr2, 0, aVarArr3.length);
            this.mFieldNumbers = iArr;
            this.mData = aVarArr2;
        }
        int i5 = this.mSize;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.mFieldNumbers;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            a[] aVarArr4 = this.mData;
            System.arraycopy(aVarArr4, i2, aVarArr4, i6, this.mSize - i2);
        }
        this.mFieldNumbers[i2] = i;
        this.mData[i2] = aVar;
        this.mSize++;
    }

    final int size() {
        if (this.mGarbage) {
            gc();
        }
        return this.mSize;
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    final a dataAt(int i) {
        if (this.mGarbage) {
            gc();
        }
        return this.mData[i];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldArray)) {
            return false;
        }
        FieldArray fieldArray = (FieldArray) obj;
        return size() == fieldArray.size() && arrayEquals(this.mFieldNumbers, fieldArray.mFieldNumbers, this.mSize) && arrayEquals(this.mData, fieldArray.mData, this.mSize);
    }

    public final int hashCode() {
        if (this.mGarbage) {
            gc();
        }
        int iHashCode = 17;
        for (int i = 0; i < this.mSize; i++) {
            iHashCode = (((iHashCode * 31) + this.mFieldNumbers[i]) * 31) + this.mData[i].hashCode();
        }
        return iHashCode;
    }

    private int idealIntArraySize(int i) {
        return idealByteArraySize(i << 2) / 4;
    }

    private int binarySearch(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.mFieldNumbers[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else {
                if (i5 <= i) {
                    return i4;
                }
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }

    private boolean arrayEquals(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean arrayEquals(a[] aVarArr, a[] aVarArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!aVarArr[i2].equals(aVarArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final FieldArray m230clone() {
        int size = size();
        FieldArray fieldArray = new FieldArray(size);
        System.arraycopy(this.mFieldNumbers, 0, fieldArray.mFieldNumbers, 0, size);
        for (int i = 0; i < size; i++) {
            a aVar = this.mData[i];
            if (aVar != null) {
                fieldArray.mData[i] = aVar.clone();
            }
        }
        fieldArray.mSize = size;
        return fieldArray;
    }
}
