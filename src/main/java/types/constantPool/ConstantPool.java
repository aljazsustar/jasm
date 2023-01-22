package types.constantPool;

import interfaces.ClassFileElement;
import interfaces.ConstantPoolElement;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool implements ClassFileElement {
    List<ConstantPoolElement> elements;

    public ConstantPool(Integer constantPoolSize) {
        this.elements = new ArrayList<>(constantPoolSize);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public void addToConstantPool(ConstantPoolElement element) {
        elements.add(element.getConstantPoolIndex(), element);
    }

    public Integer getSize() {
        return this.elements.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  ConstantPool {\n   elements = \n    {");

        elements.forEach(el -> sb.append(el.toString()));

        sb.append("  }");
        sb.append("\n  }");
        return sb.toString();
    }
}
