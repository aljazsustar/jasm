package types.attributes.util.types;

import util.types.Pair;

import java.util.ArrayList;
import java.util.List;

public class Code {

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
}
