package types.attributes.util.types.lineNumberTable;

import interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTable implements ClassFileElement {

    private List<LineNumberTableElement> lineNumberTable;

    public LineNumberTable() {
        this.lineNumberTable = new ArrayList<>();
    }

    public LineNumberTable(Integer lineNumberTableLength) {
        this.lineNumberTable = new ArrayList<>(lineNumberTableLength);
    }

    public void addLineNumberTableElement(LineNumberTableElement element) {
        this.lineNumberTable.add(element);
    }

    @Override
    public List<Byte> toHex() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (LineNumberTableElement element : this.lineNumberTable) {
            sb.append(" ").append(element).append(", ");
        }

        sb.append("]");
        return sb.toString();
    }
}
