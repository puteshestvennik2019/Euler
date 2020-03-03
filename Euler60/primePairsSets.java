import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

// brute force
// recursively looks for 5 primes, then compares their sum to the actual min
// breaks out of the loop when the (sum of elements in set + remaining items * largest number in set) > min

    public static void main(String[] args) {
        int limit = 30000;

        List<Integer> primes = sieveOfEratosthenes(limit);
        PriorityQueue<Integer> set = new PriorityQueue<>((a,b) -> b - a);
        int[] min = {Integer.MAX_VALUE};

        getSet(set, primes, 0, 1, min, 0);
        System.out.println("Lowest sum of 5 'concatinative' primes: " + min[0]);
    }

    public static void getSet(PriorityQueue<Integer> set, List<Integer> primes, int count, int lastIndex, int[] min, int sum) {
        if (count == 5) {
            min[0] = Math.min(min[0], sum);
            return;
        }
        int l = primes.size();

        for (int i = lastIndex; i < l; i++) {
            if (arePrime(set, primes.get(i))) {
                set.add(primes.get(i));
                getSet(set, primes, count + 1, i + 1, min, sum += primes.get(i));
                if (!set.isEmpty()) {
                    sum -= set.remove();
                }
            }
            if (sum + primes.get(i) * (5 - count) > min[0]) break;
        }
    }

    public static boolean arePrime(PriorityQueue<Integer> set, int prime) {
        for (int p : set) {
            if (!isPseudoPrime(reorder(p, prime)) || !isPseudoPrime(reorder(prime, p))) return false;
        }
        return true;
    }


    private static int reorder(int p1, int p2) {
        int d = getDigits(p1);

        return (int) Math.pow(10, d) * p2 + p1;
    }

    private static int getDigits(int n) {
        if (n < 10) return 1;
        if (n < 100) return 2;
        if (n < 1000) return 3;
        if (n < 10000) return 4;
        if (n < 100000) return 5;
        if (n < 1000000) return 6;
        if (n < 10000000) return 7;
        if (n < 100000000) return 8;
        if (n < 1000000000) return 9;
        else return 10;
    }

    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (prime[i]) primes.add(i);
        }
        return primes;
    }

    private static boolean isPseudoPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        if (n < 9) return true;
        if (n % 3 == 0) return false;
        if (n % 5 == 0) return false;

        int[] ar = new int[] {2, 3};
        for (int i = 0; i < ar.length; i++) {
            if (Witness(ar[i], n)) return false;
        }
        return true;
    }


    private static boolean Witness(int a, int n) {
        int t = 0;
        int u = n - 1;
        while ((u & 1) == 0) {
            t++;
            u >>= 1;
        }

        long xi1 = ModularExp(a, u, n);
        long xi2;

        for (int i = 0; i < t; i++) {
            xi2 = xi1 * xi1 % n;
            if ((xi2 == 1) && (xi1 != 1) && (xi1 != (n - 1))) return true;
            xi1 = xi2;
        }
        if (xi1 != 1) return true;
        return false;
    }


    private static long ModularExp(int a, int b, int n) {
        long d = 1;
        int k = 0;
        while ((b >> k) > 0) k++;

        for (int i = k - 1; i >= 0; i--) {
            d = d * d % n;
            if (((b >> i) & 1) > 0) d = d * a % n;
        }

        return d;
    }
}