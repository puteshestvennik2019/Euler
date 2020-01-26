import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int n = 28123;
        List<Integer> primes = sieveOfEratosthenes(n);
        ArrayList<Integer> abundantNumbers = printAbundantNumbers(n, primes);
        System.out.println(printNonAbundantSums(abundantNumbers, n));
    }

    public static int printNonAbundantSums(ArrayList<Integer> abundantNumbers, int range) {
        boolean[] abundantSums = new boolean[range + 1];
        Arrays.fill(abundantSums, false);
        for (int num : abundantNumbers) {
            for (int n : abundantNumbers) {
                int sum = n + num;
                if (sum <= range) abundantSums[sum] = true;
                else break;
            }
        }
        int count = 0;
        for (int i = 0; i <= range; i++) {
            count += (!abundantSums[i]) ? i : 0;
        }
        return count;
    }

    public static ArrayList<Integer> printAbundantNumbers(int range, List<Integer> primes) {
        ArrayList<Integer> abundantNumbers = new ArrayList<>();
        for (int i = 1; i <= range; i++) {
            if (isAbundant(i, primes)) abundantNumbers.add(i);
        }
        return abundantNumbers;
    }

    public static boolean isAbundant(int n, List<Integer> primes) {
        return n < properDivisorsSum(primes, n);
    }

    public static int properDivisorsSum(List<Integer> primes, int num) {
        if (num == 1) return 0;
        int sum = 1;
        int n = num;

        for (int p : primes) {
            if (p * p > num || n < 2) break;
            if (n % p == 0) {
                n /= p;
                int fact = 2;
                while (n % p == 0) {
                    fact++;
                    n /= p;
                }
                sum *= ((Math.pow(p, fact) - 1) / (p - 1));
            }
        }
        // for prime factors that are greater than sqrt(n)
        if (n > 1) sum *= (1 + n);

        return sum - num;
    }

    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}