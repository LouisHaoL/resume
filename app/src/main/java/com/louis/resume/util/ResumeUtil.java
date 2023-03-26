package com.louis.resume.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author liuh
 * @since 2023/3/26 14:36
 */
public class ResumeUtil {
    public static boolean wake(String host, String mac, int port) {
        try {
            byte[] macBytes = getMacBytes(mac);//转成字节类型
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length); //放入16个MAC地址
            }
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            new Thread(() -> {
                try(DatagramSocket socket = new DatagramSocket()) {
                    socket.send(packet);
                } catch (IOException e) {
                    System.out.println("网络发送异常" + e.getMessage());
                }
            }).start();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static byte[] getMacBytes(String mac) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = mac.split("([:\\-])");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }
}
