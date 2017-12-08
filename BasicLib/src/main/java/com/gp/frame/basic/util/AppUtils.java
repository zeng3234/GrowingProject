package com.gp.frame.basic.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

//@formatter:off
/**
 *
 *
 * @author zeng
 * @Create at on 2017/12/1 23:32
 * @Description
 */
//@formatter:on
public class AppUtils {

    public static final String UNKNOWN_VERSION_NAME = "UNKNOWN";
    public static final int UNKNOWN_VERSION_CODE = -1;

    /**
     * 获取应用VersionName
     *
     * @param pContext
     * @return
     */
    public static String getVersionName(Context pContext) {

        if (pContext == null) {
            return UNKNOWN_VERSION_NAME;
        }
        PackageManager packageManager = pContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(pContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        } else {
            return UNKNOWN_VERSION_NAME;
        }
    }

    /**
     * 根据包名获取程序的VersionCode
     *
     * @param pContext
     * @param pPackageName
     * @return
     */
    public static final int getVersionCode(Context pContext, String pPackageName) {
        PackageManager packageManager = pContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(pPackageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return packageInfo.versionCode;
        } else {
            return UNKNOWN_VERSION_CODE;
        }
    }

    /**
     * 是否是系统App
     *
     * @param pInfo
     * @return
     */
    public static boolean isSystemApp(ApplicationInfo pInfo) {
        return ((pInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    /**
     * 查看app安装路径
     *
     * @param pContext
     * @return
     */
    public static String getAppInstallPath(Context pContext) {
        String path = pContext.getApplicationContext().getPackageResourcePath();
        return path;
    }

    public static String getAppInstallPath(Context pContext, String packageName) {
        try {
            PackageManager pm = pContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取已安装的app,icon
     *
     * @param pContext
     * @param pPackageName
     * @return
     */
    public static Drawable getAppIcon(Context pContext, String pPackageName) {
        try {
            PackageManager pm = pContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(pPackageName, 0);
            return pi == null ? null : pi.applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取已安装的app,name
     *
     * @param pContext
     * @param pPackageName
     * @return
     */
    public static String getAppName(Context pContext, String pPackageName) {
        try {
            PackageManager pm = pContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(pPackageName, 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取已安装的app,相关信息,icon,name,....
     *
     * @param pContext
     * @param pPackageName
     * @param pCount       最小数2，否则使用单独方法    需要几个信息index,0:name,1:icon,2: 待定...
     * @return 返回object，强转使用
     */
    public static Object[] getAppInfo(Context pContext, String pPackageName, int pCount) {
        Object[] infos = new Object[pCount];
        try {
            PackageManager pm = pContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(pPackageName, 0);
            infos[0] = pi.applicationInfo.loadIcon(pm);
            infos[1] = pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return infos;
    }

}
