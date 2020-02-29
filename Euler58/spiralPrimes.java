public class Main {

    public static void main(String[] args) {
        // sieve commented out as it takes way to much time to generate such a large table
        //boolean[] notPrime = sieveOfEratosthenes(1000000000);
        double ratio = 1;
        int val = 1;
        int increment = 0;
        int primeCount = 0;

        while (ratio > 0.10d) {
            increment += 2;

            // 3 loops because bottom right corner produces odd squares
            for (int i = 0; i < 3; i++) {
                val += increment;
                if (isPrime(val)) primeCount++;
                //if (!notPrime[val/2]) primeCount++;
            }
            val += increment;
            ratio = ((double) primeCount) / (increment * 2 + 1); // increment / 2 - i-th square, hence (increment / 2) * 4 + 1 - corner count
        }
        System.out.println("Side length when sought ratio drops below 10% is: " + (increment + 1));
    }

    // except for 2 (which is stored in 0th index), all primes are (!notPrime) at indices i/2 ->
    // these are retrieved with prime = i * 2 + 1
    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] notPrime = new boolean[n / 2];
        for (int p = 3; p * p <= n; p += 2) {
            for (int i = p * p; i <= n; i += 2 * p) {
                notPrime[i/2] = true;
            }
        }
        return notPrime;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        if (n < 9) return true;
        if (n % 3 == 0) return false;

        long f = 5;
        while ((f * f) <= n) {
            if (n % f == 0) return false;
            if (n % (f + 2) == 0) return false;
            f += 6;
        }

        return true;
    }
}