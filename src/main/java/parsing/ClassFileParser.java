package parsing;

import types.ClassFile;
import util.ParsingUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassFileParser {
    private final BufferedInputStream inputStream;

    public ClassFileParser(String path) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Razredne datoteke '%s' ni bilo mogoče najti.", path));
        }
        this.inputStream = new BufferedInputStream(inputStream);
    }

    public ClassFile parse() {
        ClassFile classFile = new ClassFile();
        classFile.setMagic(this.parseMagic());
        classFile.setMinor_version(this.parseMinorVersion());
        classFile.setMajor_version(this.parseMajorVersion());
        classFile.setConstant_pool_count(this.parseConstantPoolCount());
        this.closeInputStream();
        return classFile;
    }

    private void closeInputStream() {
        try {
            this.inputStream.close();
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Napaka pri zapiranju vhoda");
        }
    }

    private byte[] parseNBytes(Integer n) {
        byte[] bytes = new byte[n];

        try {
            this.inputStream.read(bytes, 0, n);
        } catch (IOException e) {
            throw new RuntimeException("Napaka pri branju razredne datoteke.", e);
        }

        return bytes;
    }

    private Long parseMagic() {
        return ParsingUtil.bytesToLong(this.parseNBytes(4));
    }

    private Integer parseMinorVersion() {
        return ParsingUtil.bytesToInt(this.parseNBytes(2));
    }

    private Integer parseMajorVersion() {
        return ParsingUtil.bytesToInt(this.parseNBytes(2));
    }

    private Integer parseConstantPoolCount() {
        return ParsingUtil.bytesToInt(this.parseNBytes(2));
    }
}
