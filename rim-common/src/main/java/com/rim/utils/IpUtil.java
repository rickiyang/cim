package com.rim.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.util.Enumeration;

/**
 * @author rickiyang
 * @date 2020-07-16
 * @Desc
 */
@Slf4j
public class IpUtil {

    public static String getLocalIpStr() {
        Enumeration<NetworkInterface> netInterfaces = null;
        String ipAddrStr = "127.0.0.1";
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                if ("eth0".equals(ni.getName())) {
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        InetAddress ip = ips.nextElement();
                        if (!(ip instanceof Inet6Address)) {
                            ipAddrStr = ip.getHostAddress();
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            return ipAddrStr;
        }
        return ipAddrStr;
    }

    public static InetAddress getLocalIp() throws UnknownHostException, SocketException {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            return InetAddress.getLocalHost();
        }
        Enumeration<NetworkInterface> netInterfaces = null;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            if ("eth0".equals(ni.getName())) {
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    InetAddress ip = ips.nextElement();
                    if (!(ip instanceof Inet6Address)) {
                        return ip;
                    }
                }
                break;
            }
        }
        return null;
    }

    public static String getMacAddress() throws UnknownHostException, SocketException {
        InetAddress ip = getLocalIp();
        if (ip == null) {
            return StringUtils.EMPTY;
        }
        log.info("Current IP address : " + ip.getHostAddress());
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        byte[] mac = network.getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        log.info("Current MAC address : " + sb);
        return sb.toString();
    }

}