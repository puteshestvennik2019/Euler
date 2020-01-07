import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // there is little coding/algorithm in this one
        // looking for the smallest number divided by all numbers from range 1-n can be done in two ways:
        // - we can add all numbers in the range and then subtract extra/surplus primes, or
        // we can add only prime numbers and then add multiply some of them to make sure every number in the range can be created
        // i.e. for n = 4, we need 3 and 4, because 3 x 4 = 3 x 2 x 2 - thus, the smallest multiple is 12
        // for n = 5 we need 3, 4 and 5, so the smallest multiple is 60
        // n = 10 => 9 x 8 x 7 x 5 = 3 x 3 x 2 x 2 x 2 x 7 x 5 - using given numbers, we can create every factor in range 1 - 10
        // in other words, we need to return highest exponents of prime numbers present in the range

        int n = 20;
        List<Integer> primeNumbers = sieveOfEratosthenes(n);

        int res = 1;
        for (int prime : primeNumbers) {
            int base = prime;
            for (int exponent = 2; Math.pow(base, exponent) <= n; exponent++) {
                prime *= base;
            }
            res *= prime;
        }

        System.out.println(res);
    }

    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n + 1];
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