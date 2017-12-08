package com.gp.frame.basic.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zeng
 * @Create at 2017/12/7
 * @Description: .
 */
public class FileUtils {


    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public static boolean checkSDCard() {
        final String status = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(status)) {
            return true;
        }
        return false;
    }

    /**
     * 转换大小，输出带后缀
     *
     * @param size
     * @return
     */
    public static String convertStorageSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        float value = 0;
        String suffix = "";
        if (size >= gb) {

            suffix = "GB";
            value = (float) size / gb;
        } else if (size >= mb) {

            suffix = "MB";
            value = (float) size / mb;

        } else if (size >= kb) {


            suffix = "KB";
            value = (float) size / kb;

        } else {
            suffix = "B";
            value = (float) size;
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder();
        resultBuffer.append(df.format(value));
        resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    /**
     * 读取文件
     *
     * @param sFileName
     * @return
     */
    public static String readFile(String sFileName) {
        if (TextUtils.isEmpty(sFileName)) {
            return null;
        }

        final StringBuffer sb = new StringBuffer();
        final File file = new File(sFileName);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {
                String data = null;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
                br.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString().trim();
    }

    public static boolean writeStr2File(String filepath, String content) {
        if (TextUtils.isEmpty(filepath)) {
            return false;
        }
        File file = new File(filepath);
        String dir = file.getParentFile().getAbsolutePath();
        return writeStr2File(dir, file.getName(), content, false);
    }


    public static boolean writeStr2File(String directoryPath, String fileName, String content, boolean isAppend) {
        if (!TextUtils.isEmpty(content)) {
            if (!TextUtils.isEmpty(directoryPath)) {// 是否需要创建新的目录
                final File threadListFile = new File(directoryPath);
                if (!threadListFile.exists()) {
                    threadListFile.mkdirs();
                }
            }
            boolean result = false;
            final File file = new File(fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                final FileOutputStream fos = new FileOutputStream(file,
                        isAppend);
                byte[] buffer;
                try {
                    buffer = content.getBytes();
                    fos.write(buffer);
                    if (isAppend) {
                        fos.write("||".getBytes());
                    }
                    fos.flush();
                    result = true;
                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    fos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            final File file = new File(filePath);
            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

}
