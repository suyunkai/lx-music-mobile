package com.ecarx.eas.framework.sdk.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.internal.view.SupportMenu;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class SafeParcelReader {
    public static int getFieldId(int i) {
        return i & 65535;
    }

    private SafeParcelReader() {
    }

    public static int readHeader(Parcel parcel) {
        return parcel.readInt();
    }

    private static int readSize(Parcel parcel, int i) {
        return (i & SupportMenu.CATEGORY_MASK) != -65536 ? (i >> 16) & 65535 : parcel.readInt();
    }

    private static void readExpectedSize(Parcel parcel, int i, int i2) {
        int size = readSize(parcel, i);
        if (size != i2) {
            throw new a("Expected size " + i2 + " got " + size + " (0x" + Integer.toHexString(size) + ")");
        }
    }

    public static int readObjectHeader(Parcel parcel) {
        int header = readHeader(parcel);
        int size = readSize(parcel, header);
        int iDataPosition = parcel.dataPosition();
        if (getFieldId(header) != 20293) {
            throw new a("Expected object header. Got 0x" + Integer.toHexString(header));
        }
        int i = size + iDataPosition;
        if (i < iDataPosition || i > parcel.dataSize()) {
            throw new a("Size read is invalid start=" + iDataPosition + " end=" + i);
        }
        return i;
    }

    public static int readInt(Parcel parcel, int i) {
        readExpectedSize(parcel, i, 4);
        return parcel.readInt();
    }

    public static byte readByte(Parcel parcel, int i) {
        readExpectedSize(parcel, i, 4);
        return (byte) parcel.readInt();
    }

    public static short readShort(Parcel parcel, int i) {
        readExpectedSize(parcel, i, 4);
        return (short) parcel.readInt();
    }

    public static boolean readBool(Parcel parcel, int i) {
        readExpectedSize(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static long readLong(Parcel parcel, int i) {
        readExpectedSize(parcel, i, 8);
        return parcel.readLong();
    }

    public static float readFloat(Parcel parcel, int i) {
        readExpectedSize(parcel, i, 4);
        return parcel.readFloat();
    }

    public static double readDouble(Parcel parcel, int i) {
        readExpectedSize(parcel, i, 8);
        return parcel.readDouble();
    }

    public static String readString(Parcel parcel, int i) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        String string = parcel.readString();
        parcel.setDataPosition(iDataPosition + size);
        return string;
    }

    public static IBinder readBinder(Parcel parcel, int i) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        IBinder strongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(iDataPosition + size);
        return strongBinder;
    }

    public static <T extends Parcelable> T readParcelable(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        T tCreateFromParcel = creator.createFromParcel(parcel);
        parcel.setDataPosition(iDataPosition + size);
        return tCreateFromParcel;
    }

    public static ArrayList readList(Parcel parcel, int i, ClassLoader classLoader) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        ArrayList arrayList = parcel.readArrayList(classLoader);
        parcel.setDataPosition(iDataPosition + size);
        return arrayList;
    }

    public static HashMap readMap(Parcel parcel, int i, ClassLoader classLoader) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        HashMap hashMap = parcel.readHashMap(classLoader);
        parcel.setDataPosition(iDataPosition + size);
        return hashMap;
    }

    public static <T extends Parcelable> ArrayList<T> readParcelableList(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        ArrayList<T> arrayListCreateTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(iDataPosition + size);
        return arrayListCreateTypedArrayList;
    }

    public static ArrayList<String> readStringList(Parcel parcel, int i) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(iDataPosition + size);
        return arrayListCreateStringArrayList;
    }

    public static <T extends Parcelable> T[] readParcelableArray(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        T[] tArr = (T[]) ((Parcelable[]) parcel.createTypedArray(creator));
        parcel.setDataPosition(iDataPosition + size);
        return tArr;
    }

    public static String[] readStringArray(Parcel parcel, int i) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        String[] strArrCreateStringArray = parcel.createStringArray();
        parcel.setDataPosition(iDataPosition + size);
        return strArrCreateStringArray;
    }

    public static byte[] readByteArray(Parcel parcel, int i) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        byte[] bArrCreateByteArray = parcel.createByteArray();
        parcel.setDataPosition(iDataPosition + size);
        return bArrCreateByteArray;
    }

    public static int[] readIntArray(Parcel parcel, int i) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        int[] iArrCreateIntArray = parcel.createIntArray();
        parcel.setDataPosition(iDataPosition + size);
        return iArrCreateIntArray;
    }

    public static Bundle readBundle(Parcel parcel, int i, ClassLoader classLoader) {
        int size = readSize(parcel, i);
        if (size == 0) {
            return null;
        }
        int iDataPosition = parcel.dataPosition();
        Bundle bundle = parcel.readBundle(classLoader);
        parcel.setDataPosition(iDataPosition + size);
        return bundle;
    }

    public static void skip(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + readSize(parcel, i));
    }

    public static class a extends RuntimeException {
        public a(String str) {
            super(str);
        }
    }
}
