// this approach is considerably faster than subtracting and then checking for 'squarity'
// for 5777, there are only about 50 iterations
public class Main {

    public static void main(String[] args) {
	    int i = 5;
	    boolean[] sieve = sieveOfEratosthenes(100000);

	    while (true) {
            boolean broken = false;
            if (!sieve[i]) {
	            for (int j = 1; j*j < i/2; j++) {
	                if (sieve[i - j*j*2]) {
	                    broken = true;
	                    break;
                    }
                }
	            if (!broken) {
                    System.out.println(i);
                    break;
                }
            }
            i += 2;
        }
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        return prime;
    }
}