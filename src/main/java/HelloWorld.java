public class HelloWorld implements Comparable {

    public static final int NUMBER = 100;

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    private static int add100(int n) {
        return n + NUMBER;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}