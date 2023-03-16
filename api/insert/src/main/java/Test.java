import com.example.insert.annotations.Block;
import com.example.insert.annotations.Jasm;


public class Test {
    public static void main(String[] args) {

    }

    @Jasm({
            @Block(start = 10, end = 12)
    })
    public static void f() {
    }
}
