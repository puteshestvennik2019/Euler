public class Main {
    // I needed 120 lines of code and two BF approaches to realize how simple this problem is :-D
    // Code further down is not very readable, because it was a living project
    // I left it there anyway for future reference etc.
    // solution in 30ms, down from 600ms

    public static void main(String[] args) {

        long st = System.nanoTime();

        int limit = 1000000;
        long res = 0;
        int[] dp = new int[limit + 1];
        for (int i = 0; i <= limit; i++) dp[i] = i;

        for (int i = 2; i <= limit; i++) {
            if (dp[i] == i) {
                for (int j = i; j <= limit; j += i) {
                    dp[j] = (dp[j] / i) * (i - 1);
                }
            }
            res += dp[i];
        }
        System.out.println(System.nanoTime() - st);
        System.out.println(res);
    }



//    public static void main(String[] args) {
//	    int limit = 1000000;
//        long res = 0;
//        boolean[] sieve = sieveBools(limit);
//        List<Integer> primes = getPrimes((int) Math.sqrt(limit), sieve);
//        boolean[] done = new boolean[limit + 1];
//
//        long st = System.nanoTime();
//
//        for (int i = 2; i <= limit; i++) {
//            if (done[i]) continue; // fractions for this number have been included
//
//            long phi = getPhi(i, primes);
//            res += phi;
//
//            // mark as done and compute powers of i
//            done[i] = true;
//            res += getBulkPhi(phi,(long) i, done, limit);
//        }
//        System.out.println(res);
//    }
//
//    public static long getBulkPhi(long phi, long i, boolean[] done, int limit) {
//        long bulk = 0;
//        long num = i * i;
//
//        while (num > 0 && num <= limit) {
//            done[(int) num] = true;
//            phi *= i;
//            bulk += phi;
//            num *= i;
//        }
//        return bulk;
//    }
//
//    public static long getPhi(int n, List<Integer> primes) {
//        long phi = n;
//        int num = n;
//
//        for (int p: primes) {
//            if (p * p > num) break;
//
//            if (n % p == 0) {
//                while (n % p == 0) n /= p;
//                phi = phi * (p - 1) / p;
//            }
//            if (n == 1) break;
//        }
//        // remainder prime larger than sqrt(n)
//        if (n > 1) {
//            phi *= (n - 1);
//            phi /= n;
//        }
//        return phi;
//
//    }
//
//    public static boolean[] sieveBools(int n) {
//        boolean[] prime = new boolean[n + 1];
//        Arrays.fill(prime, true);
//        for (int p = 2; p * p <= n; p++) {
//            if (prime[p]) {
//                for (int i = p * 2; i <= n; i += p) {
//                    prime[i] = false;
//                }
//            }
//        }
//        return prime;
//    }
//
//    public static List<Integer> getPrimes(int limit, boolean[] sieve) {
//        List<Integer> primes = new ArrayList<>();
//        primes.add(2);
//        for (int i = 3; i <= limit; i += 2) {
//            if (sieve[i]) primes.add(i);
//        }
//        return primes;
//    }

    // below is the BF solution for generating reduced proper fractions

//    public static void main(String[] args) {
//
//        List<int[]> fractions = new ArrayList<>();
//        fractions.add(new int[] {1,2});
//
//        for (int i = 3; i <= 1000; i++) {
//            List<int[]> newFractions = new ArrayList<>();
//            newFractions.add(new int[] {1,i});
//
//            int[] prevFraction = fractions.get(0);
//
//            for (int j = 1; j < fractions.size(); j++) {
//                newFractions.add(prevFraction);
//
//                int[] nextFraction = fractions.get(j);
//                int[] sumFractions = new int[] {nextFraction[0] + prevFraction[0], nextFraction[1] + prevFraction[1]};
//
//                if (sumFractions[0] <= i && sumFractions[1] <= i) newFractions.add(sumFractions);
//
//                prevFraction = nextFraction;
//            }
//            fractions = newFractions;
//            fractions.add(prevFraction);
//            fractions.add(new int[] {i - 1, i});
//
////            System.out.println("i = " + i + " : " + fractions.size());
//
//        }
////        for (int[] f: fractions) {
////            System.out.println(f[0] + "/" + f[1]);
////        }
//        System.out.println(fractions.size());
//    }
}