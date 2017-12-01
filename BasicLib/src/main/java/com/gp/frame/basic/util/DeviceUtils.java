package com.gp.frame.basic.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
}
