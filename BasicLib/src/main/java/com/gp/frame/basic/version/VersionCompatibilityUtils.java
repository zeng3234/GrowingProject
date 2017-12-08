package com.gp.frame.basic.version;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @author zeng
 * @Create at 2017/12/7
 * @Description: 用于部分版本兼容代码
 */
public class VersionCompatibilityUtils {

    /**
     * 设置背景图片
     *
     * @param pView
     * @param pDrawable
     */
    public static void setBackground(View pView, Drawable pDrawable) {
        if (AndroidVersionUtils.aboveJELLY_BEAN()) {
            pView.setBackground(pDrawable);
        } else {
            pView.setBackgroundDrawable(pDrawable);
        }
    }
}
