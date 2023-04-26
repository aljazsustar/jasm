package com.jasm.parser.util;

import java.io.BufferedInputStream;
import java.io.IOException;

public class ParsingUtil {

    public static Long bytesToLong(byte[] bytes) {
        Long res = 0L;
        for (byte b : bytes) {
            res = (res << 8) | (b & 0xFF);
        }
        return res;
    }

    public static Integer bytesToInt(byte[] bytes) {
        Integer res = 0;
        for (byte b : bytes) {
            res = (res << 8) | (b & 0xFF);
        }
        return res;

    }

    public static Long bytesToUnsignedLong(byte[] bytes) {
        Long res = 0L;
        for (byte b : bytes) {
            res += Byte.toUnsignedLong(b);
        }
        return res;
    }

    public static Integer bytesToUnsignedInt(byte[] bytes) {
        Integer res = 0;
        for (byte b : bytes) {
            res += Byte.toUnsignedInt(b);
        }
        return res;
    }

    public static void printBytes(byte[] list) {
        for (byte b : list) {
            System.out.printf("%02X ", b);
        }
    }

    public static byte[] readNBytes(BufferedInputStream inputStream, Integer n) {
        byte[] bytes = new byte[n];

        try {
            inputStream.read(bytes, 0, n);
        } catch (IOException e) {
            throw new RuntimeException("Napaka pri branju razredne datoteke.", e);
        }

        return bytes;
    }
}
