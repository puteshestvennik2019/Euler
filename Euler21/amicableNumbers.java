import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int n = 10000;
        List<Integer> primes = sieveOfEratosthenes(n);
        int[] divSums = new int[n];
        ArrayList<Integer> amicableNumbers = new ArrayList<>();
        int amicableNumsSum = 0;

        for (int i = 2; i < n; i++) {
            int sum = divisorsSum(primeFactors(i, primes),i) - i;
            divSums[i] = sum;
            if (sum < n && divSums[sum] == i && sum != i) {
                amicableNumbers.add(sum);
                amicableNumbers.add(i);
                amicableNumsSum += sum + i;
            }
        }
        System.out.println(amicableNumbers);
        System.out.println(amicableNumsSum);
    }

    // returns prime factors
    public static ArrayList<Integer> primeFactors(int n, List<Integer> primes) {
        ArrayList<Integer> primeFactors = new ArrayList<>();
        ArrayList<Integer> multiples = new ArrayList<>();
        for (int prime : primes) {
            if (n % prime == 0) {
                primeFactors.add(prime);
            }
            if (prime > n) break;
        }
        return primeFactors;
    }

    // sum of all the factors equals the product of sums of all powers of prime factors
    // for 120 = 2^3 * 3 * 5 => (2^0 + 2^1 + 2^2 + 2^3) * (3^0 + 3^1) * (5^0 + 5^1) = 15*4*6 = 360
    public static int divisorsSum(ArrayList<Integer> arr, int n) {
        if (n == 1) return 1;

        int res = 1;
        for (int f : arr) {
            int sum = 1;
            int fact = 1;
            while (n % Math.pow(f, fact) == 0) {
                sum += Math.pow(f, fact);
                fact++;
            }
            res *= sum;
        }
        return res;
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