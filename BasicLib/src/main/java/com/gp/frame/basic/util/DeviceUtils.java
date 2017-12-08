package com.gp.frame.basic.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
//@formatter:off
/**
 *
 * @author zeng
 * @Create at on 2017/12/2 0:29
 * @Description  
 *
 */
//@formatter:on
public class DeviceUtils {


    private static final String DEFAULT_IP = "127.0.0.1";

    /**
     * 获取ip
     */
    public static String getIp(Context mContext) {
        if (mContext == null) {
            Log.e("", "mContext is null");
            return null;
        }
        try {
            ConnectivityManager connectivity = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null) {
                    // 说明网络是通的 则根据不同类型 获取设置信息
                    switch (info.getType()) {
                        case ConnectivityManager.TYPE_WIFI: {
                            WifiManager wm = (WifiManager) mContext
                                    .getSystemService(Context.WIFI_SERVICE);
                            // wifi网络
                            WifiInfo wifi = null;
                            if (wm != null) {
                                wifi = wm.getConnectionInfo();
                            }
                            if (wifi != null) {
                                return intIP2StringIP(wifi.getIpAddress());
                            }
                        }
                        break;
                        case ConnectivityManager.TYPE_MOBILE: {
                            // 移动网络
                            try {
                                // Enumeration<NetworkInterface>
                                // en=NetworkInterface.getNetworkInterfaces();
                                for (Enumeration<NetworkInterface> en = NetworkInterface
                                        .getNetworkInterfaces(); en.hasMoreElements(); ) {
                                    NetworkInterface intf = en.nextElement();
                                    for (Enumeration<InetAddress> enumIpAddr = intf
                                            .getInetAddresses(); enumIpAddr
                                                 .hasMoreElements(); ) {
                                        InetAddress inetAddress = enumIpAddr.nextElement();
                                        if (!inetAddress.isLoopbackAddress()
                                                && inetAddress instanceof Inet4Address) {
                                            return inetAddress.getHostAddress();
                                        }
                                    }
                                }
                            } catch (SocketException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                        default:
                            break;
                    }
                }
                return DEFAULT_IP;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return DEFAULT_IP;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    /**
     * @return 当前设备系统版本
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * @return 设备厂商
     */
    public static String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取设备型号
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = android.os.Build.MODEL;
        if (TextUtils.isEmpty(model)) {
            model = "";
        } else {
            model = model.trim().replaceAll("\\s*", "");
        }
        return model;
    }

    public static String getIMEI(Context pContext) {
        TelephonyManager tm = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getDeviceId() : null;
    }

    public static String getIMSI(Context pContext) {
        TelephonyManager tm = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getSubscriberId() : null;
    }

    public static boolean isSimCardReady(Context pContext) {
        TelephonyManager tm = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    /**
     * 获取手机状态信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @return DeviceId(IMEI) = 99000311726612<br>
     * DeviceSoftwareVersion = 00<br>
     * Line1Number =<br>
     * NetworkCountryIso = cn<br>
     * NetworkOperator = 46003<br>
     * NetworkOperatorName = 中国电信<br>
     * NetworkType = 6<br>
     * honeType = 2<br>
     * SimCountryIso = cn<br>
     * SimOperator = 46003<br>
     * SimOperatorName = 中国电信<br>
     * SimSerialNumber = 89860315045710604022<br>
     * SimState = 5<br>
     * SubscriberId(IMSI) = 460030419724900<br>
     * VoiceMailNumber = *86<br>
     */
    public static String getPhoneStatus(Context pContext) {
        TelephonyManager tm = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuffer sb = new StringBuffer();
        sb.append("DeviceId(IMEI) = " + tm.getDeviceId() + "\n");
        sb.append("DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n");
        sb.append("Line1Number = " + tm.getLine1Number() + "\n");
        sb.append("NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n");
        sb.append("NetworkOperator = " + tm.getNetworkOperator() + "\n");
        sb.append("NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n");
        sb.append("NetworkType = " + tm.getNetworkType() + "\n");
        sb.append("PhoneType = " + tm.getPhoneType() + "\n");
        sb.append("SimCountryIso = " + tm.getSimCountryIso() + "\n");
        sb.append("SimOperator = " + tm.getSimOperator() + "\n");
        sb.append("SimOperatorName = " + tm.getSimOperatorName() + "\n");
        sb.append("SimSerialNumber = " + tm.getSimSerialNumber() + "\n");
        sb.append("SimState = " + tm.getSimState() + "\n");
        sb.append("SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n");
        sb.append("VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n");
        return sb.toString();
    }
}
