package com.gp.frame.basic.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.webkit.MimeTypeMap;

import com.gp.frame.basic.constant.AndroidVersionCodes;

import java.io.File;
//@formatter:off
/**
 *
 *
 * @author zeng
 * @Create at on 2017/12/2 0:29
 * @Description
 */
//@formatter:on
public class ApkUtils {


    public static boolean isInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean isInstalled = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isInstalled = false;
        }
        return isInstalled;
    }


    public static void installApk(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT < AndroidVersionCodes.M) {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        } else {
            String mimeType = getMIMEType(file);
            intent.setDataAndType(Uri.fromFile(file), mimeType);
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMIMEType(File file) {
        String type = "";
        String name = file.getName();
        String fileName = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileName);
        return type;
    }

    /**
     * 卸载应用程序
     *
     * @param pContext
     * @param packageName
     */
    public static void uninstallApk(Context pContext, String packageName) {
        Uri packageURI = Uri.parse("package:" + packageName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        pContext.startActivity(uninstallIntent);
    }


}
