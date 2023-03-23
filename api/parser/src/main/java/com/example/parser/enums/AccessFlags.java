package com.example.parser.enums;

import java.util.HashMap;
import java.util.Set;

public class AccessFlags {
    private final HashMap<String, Integer> accessFlags;
    private final Integer intValue;

    public AccessFlags(Integer rawAccessFlags) {
        this.intValue = rawAccessFlags;
        this.accessFlags = new HashMap<>();
        AccessFlagsDef.getAccessFlags().forEach((key, value) -> {
            if ((value & rawAccessFlags) != 0)
                this.accessFlags.put(key, value);
        });
    }

    public HashMap<String, Integer> getAccessFlags() {
        return accessFlags;
    }

    public Set<String> getAccessFlagsList() {
        return this.accessFlags.keySet();
    }

    public Integer getIntValue() {
        return intValue;
    }

    @Override
    public String toString() {
        return "AccessFlags{" +
                this.accessFlags.keySet().stream().reduce("", (res, el) -> res + el + ",") +
                '}';
    }
}

class AccessFlagsDef {
    public static final int ACC_PUBLIC = 0x0001;
    public static final int ACC_FINAL = 0x0010;
    public static final int ACC_SUPER = 0x0020;
    public static final int ACC_INTERFACE = 0x0200;
    public static final int ACC_ABSTRACT = 0x0400;
    public static final int ACC_SYNTHETIC = 0x1000;
    public static final int ACC_ANNOTATION = 0x2000;
    public static final int ACC_ENUM = 0x4000;
    public static final int ACC_MODULE = 0x8000;

    public static HashMap<String, Integer> getAccessFlags() {
        HashMap<String, Integer> res = new HashMap<>();
        res.put("PUBLIC", ACC_PUBLIC);
        res.put("FINAL", ACC_FINAL);
        res.put("SUPER", ACC_SUPER);
        res.put("INTERFACE", ACC_INTERFACE);
        res.put("ABSTRACT", ACC_ABSTRACT);
        res.put("SYNTHETIC", ACC_SYNTHETIC);
        res.put("ANNOTATION", ACC_ANNOTATION);
        res.put("ENUM", ACC_ENUM);
        res.put("MODULE", ACC_MODULE);
        return res;
    }
}
