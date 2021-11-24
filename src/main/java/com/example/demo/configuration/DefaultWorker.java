package com.example.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;

/**
 * @author Tan Ke
 * @date 2021/11/19
 */
public class DefaultWorker implements Worker {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Random random = new Random();

    public DefaultWorker() {
    }

    @Override
    public long getWorkerId() {
        int max = 100000;
        int min = 10000;
        int s = this.random.nextInt(max) % (max - min + 1) + min;
        String ip = this.getHostIp();
        long workerId = 0L;
        if (ip != null) {
            String str = ip.replaceAll("\\.", "") + s;
            workerId = Long.parseLong(str);
            workerId %= 1024L;
        }

        return workerId;
    }

    private String getHostIp() {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();

            while(allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface)allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();

                while(addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress)addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception var5) {
            this.logger.error("获取本机ip异常", var5);
        }

        return "127.0.0.1";
    }
}
