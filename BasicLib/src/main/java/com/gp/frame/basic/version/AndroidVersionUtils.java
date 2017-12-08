package com.gp.frame.basic.version;

import android.os.Build;


/**
 * @author zeng
 * @Create at 2017/12/7
 * @Description: .版本比对，里面全部是用的大于等于
 */
public class AndroidVersionUtils {

    public static boolean aboveL() {
        return Build.VERSION.SDK_INT >= AndroidVersionCodes.LOLLIPOP;
    }

    public static boolean aboveJELLY_BEAN() {
        return Build.VERSION.SDK_INT >= AndroidVersionCodes.JELLY_BEAN;
    }
}
