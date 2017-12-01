package com.gp.frame.basic.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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
}
