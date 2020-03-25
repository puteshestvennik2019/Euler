public class Main {

    public static void main(String[] args) {

		final long mod = 10000000000l;
        double res = modPow(2,7830457,mod);
        System.out.println((long) (res*28433+1) % mod);
    }

    private static double modPow(double base, int exp, double mod) {
        double res = 1l;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            exp >>= 1;
            base = (base * base) % mod;
        }
        return res;
    }
}