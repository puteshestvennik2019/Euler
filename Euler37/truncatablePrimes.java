public class Main {

    public static void main(String[] args) {
        int n = 1000000;
        int count = 11; // problem 37 specifies there are only 11 truncatable primes
	    boolean[] primes = sieveOfEratosthenes(n);
	    int sum = 0;
	    for (int i = 10; i < n; i++) {
	        if (primes[i]) {
	            int a = i;
	            if (truncate(i, primes)) {
	                count--;
	                sum += i;
                    System.out.println(i);
	                if (count == 0) break;
                }
            }
        }
        System.out.println(sum);
    }

    public static boolean truncate(int n, boolean[] primes) {
        int num = n;
        int digits = 0;
        while (num > 0) {
            if (primes[num]) num /= 10;
            else return false;
            digits++;
        }

        num = n;
        digits--;
        while (digits > 0) {
            if (primes[num % (int) Math.pow(10, digits)]) num %= (int) Math.pow(10, digits);
            else return false;
            digits--;
        }
        return true;
    }


    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }
        return primes;
    }
}