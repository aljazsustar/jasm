package types.interfaces;

import interfaces.ClassFileElement;
import types.constantPool.constants.ClassConstant;

import java.util.ArrayList;
import java.util.List;

public class Interfaces implements ClassFileElement {

    List<ClassConstant> interfaces;

    public Interfaces(Integer interfacesCount) {
        this.interfaces = new ArrayList<>(interfacesCount);
    }

    public void addToInterfaces(ClassConstant element) {
        this.interfaces.add(element);
    }

    public ClassConstant getInterface(Integer interfacesIndex) {
        return this.interfaces.get(interfacesIndex);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Interfaces = ");

        for (ClassConstant classConstant : this.interfaces) {
            sb.append("{\n");
            sb.append(classConstant.toString());
            sb.append("}\n");
        }
        return sb.toString();
    }
}
