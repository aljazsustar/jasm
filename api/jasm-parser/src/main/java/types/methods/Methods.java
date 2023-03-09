package types.methods;

import interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Methods implements ClassFileElement {

    private List<MethodInfo> methods;

    public Methods(Integer methodsCount) {
        this.methods = new ArrayList<>(methodsCount);
    }

    public Methods() {
        this.methods = new ArrayList<>();
    }

    public void addMethod(MethodInfo methodInfo) {
        this.methods.add(methodInfo);
    }

    public void addMethod(Integer index, MethodInfo methodInfo) {
        this.methods.add(index, methodInfo);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("methods = [\n");

        for (MethodInfo method : this.methods) {
            sb.append("\t{\n\t\t").append(method.toString()).append("\n\t}\n");
        }

        sb.append("]");
        return sb.toString();

    }
}
