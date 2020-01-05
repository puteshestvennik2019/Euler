import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // System.out.println(isPrime(97));
        System.out.println(findGreatestPrimeFactor(600851475143l));
    }

    public static long findGreatestPrimeFactor(long number) {
        if (isPrime(number))
            return number;
        long gpf = 1;
        for (long i = 2; i * i <= number; i++) {
            if (number % i != 0)
                continue;
            else if (isPrime((number / i))) {
                gpf = number / i;
                break;
            } else if (isPrime(i))
                gpf = i;
        }
        return gpf;
    }

    // if any prime whose square does not exceed n divides it without a remainder, then n is not prime.
    public static boolean isPrime(long number) {
        List<Integer> primes = sieveOfEratosthenes((int) Math.floor(Math.sqrt(number)));
        // System.out.println(primes);
        for (int prime : primes) {
            if (number % prime == 0)
                return false;
        }
        return true;
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
