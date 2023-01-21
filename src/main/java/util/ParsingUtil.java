package util;

import java.util.List;

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
            System.out.println(b);
        }
    }

}
