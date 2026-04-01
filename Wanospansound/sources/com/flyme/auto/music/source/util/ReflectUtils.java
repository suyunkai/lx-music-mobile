package com.flyme.auto.music.source.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectUtils {
    private static IReflect sReflect = new CacheReflect();

    public interface IReflect {
        IReflectClass from(Class<?> cls) throws ClassNotFoundException;

        IReflectClass from(ClassLoader classLoader, String str) throws ClassNotFoundException;

        IReflectClass from(Object obj) throws ClassNotFoundException;

        IReflectClass from(String str) throws ClassNotFoundException;
    }

    public interface IReflectClass {
        Class<?> clazz();

        IReflectConstructor constructor(Class... clsArr) throws NoSuchMethodException;

        IReflectField field(String str) throws NoSuchFieldException;

        IReflectMethod method(String str, Class... clsArr) throws NoSuchMethodException;
    }

    public interface IReflectConstructor {
        Constructor constructor();

        Object newInstance(Object... objArr) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException;
    }

    public interface IReflectField {
        Field field();

        Object get(Object obj) throws IllegalAccessException, IllegalArgumentException;

        boolean getBoolean(Object obj) throws IllegalAccessException, IllegalArgumentException;

        byte getByte(Object obj) throws IllegalAccessException, IllegalArgumentException;

        char getChar(Object obj) throws IllegalAccessException, IllegalArgumentException;

        double getDouble(Object obj) throws IllegalAccessException, IllegalArgumentException;

        float getFloat(Object obj) throws IllegalAccessException, IllegalArgumentException;

        int getInt(Object obj) throws IllegalAccessException, IllegalArgumentException;

        long getLong(Object obj) throws IllegalAccessException, IllegalArgumentException;

        short getShort(Object obj) throws IllegalAccessException, IllegalArgumentException;

        void set(Object obj, Object obj2) throws IllegalAccessException, IllegalArgumentException;

        void setBoolean(Object obj, boolean z) throws IllegalAccessException, IllegalArgumentException;

        void setByte(Object obj, byte b2) throws IllegalAccessException, IllegalArgumentException;

        void setChar(Object obj, char c2) throws IllegalAccessException, IllegalArgumentException;

        void setDouble(Object obj, double d2) throws IllegalAccessException, IllegalArgumentException;

        void setFloat(Object obj, float f) throws IllegalAccessException, IllegalArgumentException;

        void setInt(Object obj, int i) throws IllegalAccessException, IllegalArgumentException;

        void setLong(Object obj, long j) throws IllegalAccessException, IllegalArgumentException;

        void setShort(Object obj, short s) throws IllegalAccessException, IllegalArgumentException;
    }

    public interface IReflectMethod {
        Object invoke(Object obj, Object... objArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

        Method method();
    }

    public static IReflectClass from(Object obj) throws ClassNotFoundException {
        return sReflect.from(obj);
    }

    public static IReflectClass from(Class<?> cls) throws ClassNotFoundException {
        return sReflect.from(cls);
    }

    public static IReflectClass from(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return sReflect.from(classLoader, str);
    }

    public static IReflectClass from(String str) throws ClassNotFoundException {
        return sReflect.from(str);
    }

    private static class CacheReflect implements IReflect {
        private Map<ClassLoader, Map<String, IReflectClass>> mCacheClass;

        private CacheReflect() {
            this.mCacheClass = new HashMap();
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflect
        public IReflectClass from(Object obj) throws ClassNotFoundException {
            return from(obj.getClass());
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflect
        public IReflectClass from(Class<?> cls) throws ClassNotFoundException {
            return from(cls.getClassLoader(), cls.getName());
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflect
        public IReflectClass from(ClassLoader classLoader, String str) throws ClassNotFoundException {
            Map<String, IReflectClass> map = this.mCacheClass.get(classLoader);
            if (map == null) {
                map = new HashMap<>();
                this.mCacheClass.put(classLoader, map);
            }
            IReflectClass iReflectClass = map.get(str);
            if (iReflectClass != null) {
                return iReflectClass;
            }
            DefaultReflectClass defaultReflectClass = new DefaultReflectClass(classLoader.loadClass(str));
            map.put(str, defaultReflectClass);
            return defaultReflectClass;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflect
        public IReflectClass from(String str) throws ClassNotFoundException {
            return from(getClass().getClassLoader(), str);
        }
    }

    private static class DefaultReflectClass implements IReflectClass {
        private Class<?> mClass;
        private Map<String, IReflectConstructor> mConstructors = new HashMap();
        private Map<String, IReflectMethod> mMethods = new HashMap();
        private Map<String, IReflectField> mFields = new HashMap();

        DefaultReflectClass(Class<?> cls) {
            this.mClass = cls;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectClass
        public Class<?> clazz() {
            return this.mClass;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectClass
        public IReflectConstructor constructor(Class... clsArr) throws NoSuchMethodException {
            StringBuilder sb = new StringBuilder();
            if (clsArr != null && clsArr.length > 0) {
                for (Class cls : clsArr) {
                    sb.append(cls.getName());
                }
            }
            String string = sb.toString();
            IReflectConstructor iReflectConstructor = this.mConstructors.get(string);
            if (iReflectConstructor != null) {
                return iReflectConstructor;
            }
            DefaultReflectConstructor defaultReflectConstructor = new DefaultReflectConstructor(this.mClass.getConstructor(clsArr));
            this.mConstructors.put(string, defaultReflectConstructor);
            return defaultReflectConstructor;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectClass
        public IReflectMethod method(String str, Class... clsArr) throws NoSuchMethodException {
            Method declaredMethod;
            StringBuilder sb = new StringBuilder(str);
            if (clsArr != null && clsArr.length > 0) {
                for (Class cls : clsArr) {
                    sb.append(cls.getName());
                }
            }
            String string = sb.toString();
            IReflectMethod iReflectMethod = this.mMethods.get(string);
            if (iReflectMethod != null) {
                return iReflectMethod;
            }
            Class<?> superclass = this.mClass;
            while (true) {
                if (superclass == null) {
                    declaredMethod = null;
                    break;
                }
                try {
                    declaredMethod = superclass.getDeclaredMethod(str, clsArr);
                    break;
                } catch (Exception unused) {
                    superclass = superclass.getSuperclass();
                }
            }
            if (declaredMethod == null) {
                throw new NoSuchMethodException(str + " " + Arrays.toString(clsArr));
            }
            DefaultReflectMethod defaultReflectMethod = new DefaultReflectMethod(declaredMethod);
            this.mMethods.put(string, defaultReflectMethod);
            return defaultReflectMethod;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectClass
        public IReflectField field(String str) throws NoSuchFieldException {
            Field declaredField;
            IReflectField iReflectField = this.mFields.get(str);
            if (iReflectField != null) {
                return iReflectField;
            }
            Class<?> superclass = this.mClass;
            while (true) {
                if (superclass == null) {
                    declaredField = null;
                    break;
                }
                try {
                    declaredField = superclass.getDeclaredField(str);
                    break;
                } catch (Exception unused) {
                    superclass = superclass.getSuperclass();
                }
            }
            if (declaredField == null) {
                throw new NoSuchFieldException(str);
            }
            DefaultReflectField defaultReflectField = new DefaultReflectField(declaredField);
            this.mFields.put(str, defaultReflectField);
            return defaultReflectField;
        }
    }

    private static class DefaultReflectConstructor implements IReflectConstructor {
        private Constructor<?> mConstructor;

        DefaultReflectConstructor(Constructor<?> constructor) {
            this.mConstructor = constructor;
            constructor.setAccessible(true);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectConstructor
        public Constructor constructor() {
            return this.mConstructor;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectConstructor
        public Object newInstance(Object... objArr) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
            return this.mConstructor.newInstance(objArr);
        }
    }

    private static class DefaultReflectMethod implements IReflectMethod {
        private Method mMethod;

        DefaultReflectMethod(Method method) {
            this.mMethod = method;
            method.setAccessible(true);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectMethod
        public Method method() {
            return this.mMethod;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectMethod
        public Object invoke(Object obj, Object... objArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            return this.mMethod.invoke(obj, objArr);
        }
    }

    private static class DefaultReflectField implements IReflectField {
        private Field mField;

        DefaultReflectField(Field field) {
            this.mField = field;
            field.setAccessible(true);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public Field field() {
            return this.mField;
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public Object get(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.get(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public boolean getBoolean(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getBoolean(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public byte getByte(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getByte(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public char getChar(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getChar(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public short getShort(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getShort(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public int getInt(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getInt(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public long getLong(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getLong(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public float getFloat(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getFloat(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public double getDouble(Object obj) throws IllegalAccessException, IllegalArgumentException {
            return this.mField.getDouble(obj);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void set(Object obj, Object obj2) throws IllegalAccessException, IllegalArgumentException {
            this.mField.set(obj, obj2);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setBoolean(Object obj, boolean z) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setBoolean(obj, z);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setByte(Object obj, byte b2) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setByte(obj, b2);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setChar(Object obj, char c2) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setChar(obj, c2);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setShort(Object obj, short s) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setShort(obj, s);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setInt(Object obj, int i) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setInt(obj, i);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setLong(Object obj, long j) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setLong(obj, j);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setFloat(Object obj, float f) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setFloat(obj, f);
        }

        @Override // com.flyme.auto.music.source.util.ReflectUtils.IReflectField
        public void setDouble(Object obj, double d2) throws IllegalAccessException, IllegalArgumentException {
            this.mField.setDouble(obj, d2);
        }
    }
}
