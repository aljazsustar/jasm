package types.attributes.util.types;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private final List<Integer> arguments;

    public Arguments() {
        this.arguments = new ArrayList<>();
    }

    public void addArgument(Integer argument) {
        this.arguments.add(argument);
    }

    public List<Integer> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Integer argument : this.arguments) {
            sb.append(" ").append(argument).append(", ");
        }

        sb.append("]");
        return sb.toString();
    }
}
