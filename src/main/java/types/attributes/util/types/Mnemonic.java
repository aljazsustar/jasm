package types.attributes.util.types;

import interfaces.ClassFileElement;

import java.util.List;

public class Mnemonic implements ClassFileElement {
    private Integer opcode;
    private String mnemonic;

    public Mnemonic(Integer opcode, String mnemonic) {
        this.opcode = opcode;
        this.mnemonic = mnemonic;
    }

    public Integer getOpcode() {
        return opcode;
    }

    public void setOpcode(Integer opcode) {
        this.opcode = opcode;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        return "Mnemonic{" +
                "opcode=" + opcode +
                ", mnemonic='" + mnemonic + '\'' +
                '}';
    }
}
