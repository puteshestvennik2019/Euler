public class Main {

    public static void main(String[] args) {
        int N = 1000;
        int lastDigits = 10;
        long res = 405071317;
        long tenDig = (long) Math.pow(10, lastDigits);
        
        for (int i = 11; i <= N; i++) {
            int j = 1;
            long prod = i;
            while (++j <= i) {
                prod *= i;
                prod %= tenDig;
            }
            res += prod;
            res %= tenDig;
        }

//        res = 405071317;

//        for (int i = 11; i <= N; i++) {
//            res += BME(i,i,tenDig);
//           res %= tenDig;
//        }

        System.out.println(res);
    }

    // Binary Modular Exponentiation
    public static int BME(int base, int exp, int mod) {
        long c = 1;
        long b = base;
        while (exp > 0) {
            if (exp % 2 == 1)
                c = (c * b) % mod;
            b = (b * b) % mod;
            exp >>= 1;
        }
        return (int) c;
    }
}