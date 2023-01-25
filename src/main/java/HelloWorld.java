public class HelloWorld implements Comparable {

    public static final int NUMBER = 100;
    public static final float FLOAT = 10.14F;
    public static final long LONG = 321434738194L;
    public static final double DOUBLE = 1230.43921321;

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