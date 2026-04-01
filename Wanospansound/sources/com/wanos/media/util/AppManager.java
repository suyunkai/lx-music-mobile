package com.wanos.media.util;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import java.util.Stack;

/* JADX INFO: loaded from: classes3.dex */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static Stack<Fragment> fragmentStack;
    private static volatile AppManager instance;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public static Stack<Fragment> getFragmentStack() {
        return fragmentStack;
    }

    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    public boolean isActivity() {
        if (activityStack != null) {
            return !r0.isEmpty();
        }
        return false;
    }

    public Activity currentActivity() {
        Stack<Activity> stack = activityStack;
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return activityStack.lastElement();
    }

    public Activity getActivityWithIndex(int index) {
        Stack<Activity> stack = activityStack;
        if (stack != null && !stack.isEmpty()) {
            try {
                Stack<Activity> stack2 = activityStack;
                return stack2.elementAt(stack2.size() - (index + 1));
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
        }
        return null;
    }

    public void finishActivity() {
        finishActivity(activityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.finish();
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (activityStack.get(i) != null) {
                finishActivity(activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    public Activity getActivity(Class<?> cls) {
        Stack<Activity> stack = activityStack;
        if (stack == null) {
            return null;
        }
        for (Activity activity : stack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public void addFragment(Fragment fragment) {
        if (fragmentStack == null) {
            fragmentStack = new Stack<>();
        }
        fragmentStack.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentStack.remove(fragment);
        }
    }

    public boolean isFragment() {
        if (fragmentStack != null) {
            return !r0.isEmpty();
        }
        return false;
    }

    public Fragment currentFragment() {
        Stack<Fragment> stack = fragmentStack;
        if (stack != null) {
            return stack.lastElement();
        }
        return null;
    }

    public void appExit() {
        try {
            finishAllActivity();
        } catch (Exception unused) {
            if (activityStack != null) {
                activityStack.clear();
            }
        }
    }
}
