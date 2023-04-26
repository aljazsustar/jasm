package com.jasm.parser.util;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class WritingUtil {

    public static List<Byte> writeBytes(Integer value, Integer length) {
        byte[] result = new byte[length];
        for (int i = Integer.BYTES - 1; i >= 0; i--) {
            if (i < length) {
                result[i] = (byte) (value & 0xFF);
                value >>= Byte.SIZE;
            }
        }
        return primitiveToObjectByteList(result);
    }

    public static List<Byte> writeBytes(Long value, Integer length) {
        byte[] result = new byte[length];
        for (int i = Long.BYTES - 1; i >= 0; i--) {
            if (i < length) {
                result[i] = (byte) (value & 0xFF);
                value >>= Byte.SIZE;
            }
        }
        return primitiveToObjectByteList(result);
    }

    public static List<Byte> writeBytes(Float value, Integer length) {
        return primitiveToObjectByteList(ByteBuffer.allocate(length).putFloat(value).array());
    }

    public static List<Byte> writeBytes(Double value, Integer length) {
        byte[] result = new byte[length];
        Long longBits = Double.doubleToLongBits(value);
        for (int i = 0; i < 8; i++) {
            result[i] = (byte) ((longBits >> ((7 - i) * 8)) & 0xff);
        }
        return primitiveToObjectByteList(result);
    }

    public static ArrayList<Byte> primitiveToObjectByteList(byte[] bytes) {
        ArrayList<Byte> res = new ArrayList<>();
        for (byte b : bytes) {
            res.add(b);
        }

        return res;
    }

    public static byte[] objectByteListToPrimitiveArray(List<Byte> list) {
        byte[] res = new byte[list.size()];

        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}
