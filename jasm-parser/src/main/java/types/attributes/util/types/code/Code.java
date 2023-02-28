package types.attributes.util.types.code;

import interfaces.ClassFileElement;
import util.types.Pair;

import java.util.ArrayList;
import java.util.List;

public class Code implements ClassFileElement {

    private final List<Pair<Mnemonic, Arguments>> code;

    public Code() {
        this.code = new ArrayList<>();
    }

    public List<Pair<Mnemonic, Arguments>> getCode() {
        return code;
    }

    public void addCommand(Pair<Mnemonic, Arguments> command) {
        this.code.add(command);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Pair<Mnemonic, Arguments> command : this.code) {
            sb.append(" ").append(command).append(", ");
        }

        sb.append("]");
        return sb.toString();
    }
}
