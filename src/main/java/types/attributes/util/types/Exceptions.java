package types.attributes.util.types;

import interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class Exceptions implements ClassFileElement {

    private List<Exception> exceptions;

    public Exceptions() {
        this.exceptions = new ArrayList<>();
    }

    public Exceptions(Integer exceptionTableLength) {
        this.exceptions = new ArrayList<>(exceptionTableLength);
    }


    public void addException(Exception exception) {
        this.exceptions.add(exception);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Exception exception : this.exceptions) {
            sb.append(" ").append(exception.toString()).append(", ");
        }

        sb.append("]");
        return sb.toString();
    }
}
