package types.fields;

import interfaces.ClassFileElement;

import java.util.List;

public class Fields implements ClassFileElement {

    List<FieldInfo> fields;

    @Override
    public List<Byte> toHex() {
        return null;
    }
}
