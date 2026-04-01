package com.ecarx.eas.framework.sdk.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class SafeParcelUtil {
    private static final String TAG = "SafeParcel";

    private SafeParcelUtil() {
    }

    public static <T extends SafeParcelable> T createObject(Class<T> cls, Parcel parcel) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            boolean zIsAccessible = declaredConstructor.isAccessible();
            declaredConstructor.setAccessible(true);
            T tNewInstance = declaredConstructor.newInstance(new Object[0]);
            readObject(tNewInstance, parcel);
            declaredConstructor.setAccessible(zIsAccessible);
            return tNewInstance;
        } catch (NoSuchElementException unused) {
            throw new RuntimeException("createObject() requires a default constructor.");
        } catch (Exception e) {
            throw new RuntimeException("Can't construct object", e);
        }
    }

    public static void writeObject(SafeParcelable safeParcelable, Parcel parcel, int i) {
        safeParcelable.getClass();
        int iWriteObjectHeader = SafeParcelWriter.writeObjectHeader(parcel);
        for (Class<?> superclass = safeParcelable.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            for (Field field : superclass.getDeclaredFields()) {
                if (isSafeParceledField(field)) {
                    try {
                        writeField(safeParcelable, parcel, field, i);
                    } catch (Exception e) {
                        Log.w(TAG, "Error writing field: " + e);
                    }
                }
            }
        }
        SafeParcelWriter.finishObjectHeader(parcel, iWriteObjectHeader);
    }

    public static void readObject(SafeParcelable safeParcelable, Parcel parcel) {
        safeParcelable.getClass();
        SparseArray sparseArray = new SparseArray();
        for (Class<?> superclass = safeParcelable.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            for (Field field : superclass.getDeclaredFields()) {
                if (isSafeParceledField(field)) {
                    int fieldId = getFieldId(field);
                    if (sparseArray.get(fieldId) != null) {
                        throw new RuntimeException(String.format("Field number %d is used twice in %s for fields %s and %s", Integer.valueOf(fieldId), superclass.getName(), field.getName(), ((Field) sparseArray.get(fieldId)).getName()));
                    }
                    sparseArray.put(fieldId, field);
                }
            }
        }
        int objectHeader = SafeParcelReader.readObjectHeader(parcel);
        while (parcel.dataPosition() < objectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId2 = SafeParcelReader.getFieldId(header);
            Field field2 = (Field) sparseArray.get(fieldId2);
            if (field2 == null) {
                Log.d(TAG, String.format("Unknown field id %d in %s, skipping.", Integer.valueOf(fieldId2), safeParcelable.getClass().getName()));
                SafeParcelReader.skip(parcel, header);
            } else {
                try {
                    readField(safeParcelable, parcel, field2, header);
                } catch (Exception e) {
                    Log.w(TAG, String.format("Error reading field: %d in %s, skipping.", Integer.valueOf(fieldId2), safeParcelable.getClass().getName()), e);
                    SafeParcelReader.skip(parcel, header);
                }
            }
        }
        if (parcel.dataPosition() > objectHeader) {
            throw new RuntimeException("Overread allowed size end=" + objectHeader);
        }
    }

    private static Parcelable.Creator<Parcelable> getCreator(Field field) {
        Class<?> type = field.getType();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        if (type != null && Parcelable.class.isAssignableFrom(type)) {
            return getCreator(type);
        }
        throw new RuntimeException(type + " is not an Parcelable");
    }

    private static Parcelable.Creator<Parcelable> getCreator(Class cls) {
        try {
            Field declaredField = cls.getDeclaredField("CREATOR");
            declaredField.setAccessible(true);
            return (Parcelable.Creator) declaredField.get(null);
        } catch (IllegalAccessException unused) {
            throw new RuntimeException("CREATOR in " + cls + " is not accessible");
        } catch (NoSuchFieldException unused2) {
            throw new RuntimeException(cls + " is an Parcelable without CREATOR");
        }
    }

    private static Class getSubClass(Field field) {
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (field2 == null || field2.subClass() == SafeParcelable.class) {
            return null;
        }
        return field2.subClass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Class getListItemClass(Field field) {
        Class subClass = getSubClass(field);
        if (subClass != null) {
            return subClass;
        }
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        if (parameterizedType.getActualTypeArguments().length <= 0) {
            return null;
        }
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type instanceof Class) {
            return (Class) type;
        }
        return null;
    }

    private static ClassLoader getClassLoader(Class cls) {
        return (cls == null || cls.getClassLoader() == null) ? ClassLoader.getSystemClassLoader() : cls.getClassLoader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean getUseValueParcel(Field field) {
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (field2 != null) {
            return field2.useValueParcel();
        }
        throw new IllegalStateException();
    }

    private static int getFieldId(Field field) {
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (field2 != null) {
            return field2.value();
        }
        throw new IllegalStateException();
    }

    private static boolean getMayNull(Field field) {
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (field2 != null) {
            return field2.mayNull();
        }
        throw new IllegalStateException();
    }

    private static boolean isSafeParceledField(Field field) {
        return field.isAnnotationPresent(SafeParcelable.Field.class);
    }

    private static void writeField(SafeParcelable safeParcelable, Parcel parcel, Field field, int i) throws IllegalAccessException {
        int fieldId = getFieldId(field);
        boolean mayNull = getMayNull(field);
        boolean zIsAccessible = field.isAccessible();
        field.setAccessible(true);
        switch (AnonymousClass1.f45a[a.a(field).ordinal()]) {
            case 1:
                SafeParcelWriter.write(parcel, fieldId, (Parcelable) field.get(safeParcelable), i, mayNull);
                break;
            case 2:
                SafeParcelWriter.write(parcel, fieldId, (IBinder) field.get(safeParcelable), mayNull);
                break;
            case 3:
                SafeParcelWriter.write(parcel, fieldId, ((IInterface) field.get(safeParcelable)).asBinder(), mayNull);
                break;
            case 4:
                SafeParcelWriter.writeStringList(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                break;
            case 5:
                Class listItemClass = getListItemClass(field);
                if (listItemClass == null || !Parcelable.class.isAssignableFrom(listItemClass) || getUseValueParcel(field)) {
                    SafeParcelWriter.write(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                } else {
                    SafeParcelWriter.write(parcel, fieldId, (List) field.get(safeParcelable), i, mayNull);
                }
                break;
            case 6:
                SafeParcelWriter.write(parcel, fieldId, (Map) field.get(safeParcelable), mayNull);
                break;
            case 7:
                SafeParcelWriter.write(parcel, fieldId, (Bundle) field.get(safeParcelable), mayNull);
                break;
            case 8:
                SafeParcelWriter.write(parcel, fieldId, (Parcelable[]) field.get(safeParcelable), i, mayNull);
                break;
            case 9:
                SafeParcelWriter.write(parcel, fieldId, (String[]) field.get(safeParcelable), mayNull);
                break;
            case 10:
                SafeParcelWriter.write(parcel, fieldId, (byte[]) field.get(safeParcelable), mayNull);
                break;
            case 11:
                SafeParcelWriter.write(parcel, fieldId, (int[]) field.get(safeParcelable), mayNull);
                break;
            case 12:
                SafeParcelWriter.write(parcel, fieldId, (Integer) field.get(safeParcelable));
                break;
            case 13:
                SafeParcelWriter.write(parcel, fieldId, (Long) field.get(safeParcelable));
                break;
            case 14:
                SafeParcelWriter.write(parcel, fieldId, (Boolean) field.get(safeParcelable));
                break;
            case 15:
                SafeParcelWriter.write(parcel, fieldId, (Float) field.get(safeParcelable));
                break;
            case 16:
                SafeParcelWriter.write(parcel, fieldId, (Double) field.get(safeParcelable));
                break;
            case 17:
                SafeParcelWriter.write(parcel, fieldId, (String) field.get(safeParcelable), mayNull);
                break;
        }
        field.setAccessible(zIsAccessible);
    }

    /* JADX INFO: renamed from: com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f45a;

        static {
            int[] iArr = new int[a.values().length];
            f45a = iArr;
            try {
                iArr[a.Parcelable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f45a[a.Binder.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f45a[a.Interface.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f45a[a.StringList.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f45a[a.List.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f45a[a.Map.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f45a[a.Bundle.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f45a[a.ParcelableArray.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f45a[a.StringArray.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f45a[a.ByteArray.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f45a[a.IntArray.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f45a[a.Integer.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f45a[a.Long.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f45a[a.Boolean.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f45a[a.Float.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f45a[a.Double.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f45a[a.String.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f45a[a.Byte.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    private static void readField(SafeParcelable safeParcelable, Parcel parcel, Field field, int i) throws IllegalAccessException {
        ArrayList list;
        Bundle bundle;
        boolean zIsAccessible = field.isAccessible();
        boolean z = true;
        field.setAccessible(true);
        long jVersionCode = field.isAnnotationPresent(SafeParcelable.Field.class) ? ((SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class)).versionCode() : -1L;
        switch (AnonymousClass1.f45a[a.a(field).ordinal()]) {
            case 1:
                field.set(safeParcelable, SafeParcelReader.readParcelable(parcel, i, getCreator(field)));
                break;
            case 2:
                field.set(safeParcelable, SafeParcelReader.readBinder(parcel, i));
                break;
            case 3:
                Class<?>[] declaredClasses = field.getType().getDeclaredClasses();
                int length = declaredClasses.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        try {
                            field.set(safeParcelable, declaredClasses[i2].getDeclaredMethod("asInterface", IBinder.class).invoke(null, SafeParcelReader.readBinder(parcel, i)));
                        } catch (Exception unused) {
                            i2++;
                        }
                    } else {
                        z = false;
                    }
                }
                if (!z) {
                    throw new RuntimeException("Field has broken interface: " + field);
                }
                break;
            case 4:
                field.set(safeParcelable, SafeParcelReader.readStringList(parcel, i));
                break;
            case 5:
                Class listItemClass = getListItemClass(field);
                if (listItemClass == null || !Parcelable.class.isAssignableFrom(listItemClass) || getUseValueParcel(field)) {
                    list = SafeParcelReader.readList(parcel, i, getClassLoader(listItemClass));
                } else {
                    list = SafeParcelReader.readParcelableList(parcel, i, getCreator(listItemClass));
                }
                field.set(safeParcelable, list);
                break;
            case 6:
                field.set(safeParcelable, SafeParcelReader.readMap(parcel, i, getClassLoader(getSubClass(field))));
                break;
            case 7:
                Class subClass = getSubClass(field);
                if (subClass == null || !Parcelable.class.isAssignableFrom(subClass) || getUseValueParcel(field)) {
                    bundle = SafeParcelReader.readBundle(parcel, i, getClassLoader(field.getDeclaringClass()));
                } else {
                    bundle = SafeParcelReader.readBundle(parcel, i, getClassLoader(subClass));
                }
                field.set(safeParcelable, bundle);
                break;
            case 8:
                field.set(safeParcelable, SafeParcelReader.readParcelableArray(parcel, i, getCreator(field)));
                break;
            case 9:
                field.set(safeParcelable, SafeParcelReader.readStringArray(parcel, i));
                break;
            case 10:
                field.set(safeParcelable, SafeParcelReader.readByteArray(parcel, i));
                break;
            case 11:
                field.set(safeParcelable, SafeParcelReader.readIntArray(parcel, i));
                break;
            case 12:
                int i3 = SafeParcelReader.readInt(parcel, i);
                if (jVersionCode != -1 && i3 > jVersionCode) {
                    Log.d(TAG, String.format("Version code of %s (%d) is older than object read (%d).", field.getDeclaringClass().getName(), Long.valueOf(jVersionCode), Integer.valueOf(i3)));
                }
                field.set(safeParcelable, Integer.valueOf(i3));
                break;
            case 13:
                long j = SafeParcelReader.readLong(parcel, i);
                if (jVersionCode != -1 && j > jVersionCode) {
                    Log.d(TAG, String.format("Version code of %s (%d) is older than object read (%d).", field.getDeclaringClass().getName(), Long.valueOf(jVersionCode), Long.valueOf(j)));
                }
                field.set(safeParcelable, Long.valueOf(j));
                break;
            case 14:
                field.set(safeParcelable, Boolean.valueOf(SafeParcelReader.readBool(parcel, i)));
                break;
            case 15:
                field.set(safeParcelable, Float.valueOf(SafeParcelReader.readFloat(parcel, i)));
                break;
            case 16:
                field.set(safeParcelable, Double.valueOf(SafeParcelReader.readDouble(parcel, i)));
                break;
            case 17:
                field.set(safeParcelable, SafeParcelReader.readString(parcel, i));
                break;
            case 18:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + a.a(field));
        }
        field.setAccessible(zIsAccessible);
    }

    private enum a {
        Parcelable,
        Binder,
        StringList,
        List,
        Bundle,
        ParcelableArray,
        StringArray,
        ByteArray,
        Interface,
        IntArray,
        Integer,
        Long,
        Boolean,
        Float,
        Double,
        String,
        Map,
        Byte;

        public static a a(Field field) {
            Class<?> type = field.getType();
            Class<?> componentType = type.getComponentType();
            if (type.isArray() && componentType != null && Parcelable.class.isAssignableFrom(componentType)) {
                return ParcelableArray;
            }
            if (type.isArray() && componentType != null && String.class.isAssignableFrom(componentType)) {
                return StringArray;
            }
            if (type.isArray() && componentType != null && Byte.TYPE.isAssignableFrom(componentType)) {
                return ByteArray;
            }
            if (type.isArray() && componentType != null && Integer.TYPE.isAssignableFrom(componentType)) {
                return IntArray;
            }
            if (Bundle.class.isAssignableFrom(type)) {
                return Bundle;
            }
            if (Parcelable.class.isAssignableFrom(type)) {
                return Parcelable;
            }
            if (IBinder.class.isAssignableFrom(type)) {
                return Binder;
            }
            if (IInterface.class.isAssignableFrom(type)) {
                return Interface;
            }
            if (type == List.class || type == ArrayList.class) {
                return (SafeParcelUtil.getListItemClass(field) != String.class || SafeParcelUtil.getUseValueParcel(field)) ? List : StringList;
            }
            if (type == Map.class || type == HashMap.class) {
                return Map;
            }
            if (type == Integer.TYPE || type == Integer.class) {
                return Integer;
            }
            if (type == Boolean.TYPE || type == Boolean.class) {
                return Boolean;
            }
            if (type == Long.TYPE || type == Long.class) {
                return Long;
            }
            if (type == Float.TYPE || type == Float.class) {
                return Float;
            }
            if (type == Double.TYPE || type == Double.class) {
                return Double;
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return Byte;
            }
            if (type == String.class) {
                return String;
            }
            throw new RuntimeException("Type is not yet usable with SafeParcelUtil: " + type);
        }
    }

    public static <T extends Parcelable> byte[] asByteArray(T t) {
        if (t == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        t.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    public static <T extends Parcelable> T fromByteArray(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        T tCreateFromParcel = creator.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tCreateFromParcel;
    }
}
