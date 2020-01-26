import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        int exponent = 999;
        BigInteger ten = new BigInteger("10");
        BigInteger n = ten.pow(exponent);

        int i = 1;
        int term = 2;
        BigInteger[] fib = new BigInteger[3];
        fib[0] = fib[1] = BigInteger.valueOf(1);
        while (n.compareTo(fib[i]) > 0) {
            i = (i + 1) % 3;
            term++;
            fib[i] = fib[(i + 1) % 3].add(fib[(i + 2) % 3]);
        }
        System.out.println(term);
        // System.out.println(fib[i]);
    }
}
