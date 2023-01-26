package parsing;

import types.ClassFile;
import types.constantPool.ConstantPool;
import util.ParsingUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassFileParser {
    private final BufferedInputStream inputStream;

    public ClassFileParser(String path) {
        FileInputStream inputStream;
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
        classFile.setConstant_pool(this.parseConstantPool(classFile.getConstant_pool_count()));
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



    private Long parseMagic() {
        return ParsingUtil.bytesToLong(ParsingUtil.readNBytes(this.inputStream, 4));
    }

    private Integer parseMinorVersion() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Integer parseMajorVersion() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private Integer parseConstantPoolCount() {
        return ParsingUtil.bytesToInt(ParsingUtil.readNBytes(this.inputStream, 2));
    }

    private ConstantPool parseConstantPool(Integer constantPoolCount) {
        return new ConstantPoolParser(this.inputStream, constantPoolCount).parseConstantPool();
    }
}
