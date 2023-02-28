package types.constantPool.constants;

import enums.ConstantPoolTags;
import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;
import types.constantPool.constants.strings.Utf8Constant;

import java.util.List;

public class NameAndTypeConstant extends ConstantPoolElement implements ClassFileElement {

    private Utf8Constant name;
    private Utf8Constant type;
    private Integer nameIndex;
    private Integer descroptorIndex;

    public NameAndTypeConstant(Integer constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
        this.tag = ConstantPoolTags.CONSTANT_NameAndType;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public Utf8Constant getName() {
        return name;
    }

    public void setName(Utf8Constant name) {
        this.name = name;
    }

    public Utf8Constant getType() {
        return type;
    }

    public void setType(Utf8Constant type) {
        this.type = type;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    public Integer getDescroptorIndex() {
        return descroptorIndex;
    }

    public void setDescroptorIndex(Integer descroptorIndex) {
        this.descroptorIndex = descroptorIndex;
    }

    @Override
    public String toString() {
        return "NameAndTypeConstant{" +
                "name=" + (name != null ? name.toString() : "") +
                ", type=" + (type != null ? type.toString() : "") +
                ", tag=" + tag +
                ", constantPoolIndex=" + constantPoolIndex +
                '}';
    }
}
