package com.gp.frame.basic.util;

import java.util.Locale;

public class HexStrUtils {

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase(Locale.getDefault());
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 数组转换成十六进制字符串
     *
     * @param pArray
     * @return HexString
     */
    public static final String bytesToHexString(byte[] pArray) {
        if (pArray == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer(pArray.length);
        String sTemp;
        for (int i = 0; i < pArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & pArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase(Locale.getDefault()));
        }
        return sb.toString();
    }

    public static final String splitBytesString(String byteString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteString.length(); i++) {
            sb.append(byteString.charAt(i));
            if (sb.length() % 3 == 0 && sb.charAt(sb.length() - 1) != ' ') {
                sb.insert(sb.length() - 1, ' ');
            }
        }
        return sb.toString();
    }

}