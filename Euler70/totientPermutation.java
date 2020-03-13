import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    // the below solution was made for reusability
    // problem could be solved based on assumption that min n/phi(n) is found when n has only one divisor, its sqrt
    // or two divisors very close to the square root
    // because the problem calls for a very specific solution, ie permutation, it is a guesswork if such solution can be found
    // if we limit n / phi(n) to values below 1.01 (i.e. excluding n with more than 2 coprimes

    // once I have found solution, I started experimenting with this code to optimized, by adding array of booleans for example
    // that's why it is far from clear, but gives some insight to what could be done better, I hope

    public static int limit = 10000000;
    public static boolean[] primesArr = sieveBools(limit);

    public static void main(String[] args) {

        List<Integer> primes = sieveOfEratosthenes((int) Math.sqrt(limit));

        // return first number not divided by 2 or 3 so that we can use steps +2, +4, +2, +4... to iterate
        int n = getNumber(limit);
        double minRatio = Double.MAX_VALUE;
        int res = 0;

        if ((n + 2) % 3 != 0 && n + 2 < limit) {
            int phi = getPhi(n + 2, primes, minRatio);
            if (isPermutation(n, phi)) {
                res = n;
                minRatio = (double) n / phi;
            }
        }

        // main loop
        boolean doubleJump = false;
        for (int i = n; i > 1; i -= 2) {
            int root = (int) Math.sqrt(i);
            if (( (double) i / (root * root)) > minRatio) {
                break;
            }

            int phi = getPhi(i, primes, minRatio);
            
            // invoke next line only if we didn't break out of getPhi
            if (phi > 0 && isPermutation(i, phi)) {
                double ratio = (double) i / phi;
                if (ratio < minRatio) {
                    minRatio  = ratio;
                    res = i;
                }
            }
            if (doubleJump) {
                i -= 2;
            }
            doubleJump = !doubleJump;
        }

        System.out.println(res);
    }

    public static boolean isPermutation(int n1, int n2) {
        int[] countN1 = countDigits(n1);
        int[] countN2 = countDigits(n2);

        int firstN = composeNumber(countN1);
        int secondN = composeNumber(countN2);

        return firstN == secondN;
    }

    public static int composeNumber(int[] arr) {
        int res = 0;
        for (int i = 9; i >= 0; i--) {
            while (arr[i] > 0) {
                arr[i]--;
                res = res * 10 + i;
            }
        }
        return res;
    }

    public static int[] countDigits(int n) {
        int[] digits = new int[10];
        while (n > 0) {
            digits[n % 10]++;
            n /= 10;
        }
        return digits;
    }

    public static int getPhi(int n, List<Integer> primes, double min) {
        int phi = n;
        double ratio = 1;
        boolean primeNumber = true;
        int number = n;

        if (primesArr[n]) return -1;

        for (int i = 0; i < primes.size(); i++) {
            // later on change to break out when prime > sqrt(n) to compare performance
            int p = primes.get(i);
            if (n % p == 0) {
                primeNumber = false;
                // if counted ratio is higher than the current min, break out
                ratio *= (double) p / (p - 1);
                // if ratio > min break
                if (ratio > min) {
                    return -1;
                }
                // else count phi(n)
                phi = (phi / p) * (p - 1);
                // if phi has fewer digits, cannot be a permutation of n
                if (phi < Math.pow(10, (int) Math.log10(number)))
                    return -1;
                while (n % p == 0) n /= p;
                // if (isPrime(n, primes)) {
                if (primesArr[n]) {
                    phi = (phi / n) * (n - 1);
                    break;
                }
            }
        }
        // if (primeNumber) return -1; // for prime numbers, phi(n) will never be a permutation of n
        return phi;
    }

    public static boolean isPrime(int n, List<Integer> primes) {
        for (int p: primes) {
            if (n % p == 0) return false;
            if (p * p > n) break;
        }
        return true;
    }

    // in case limit - 4 is returned, it may be necessary to check limit - 2 before starting the main loop
    public static int getNumber(int limit) {
        if ((limit & 1) == 1) {
            if (limit % 3 == 0) return limit - 2;
            else if ((limit + 2) % 3 == 0) return limit;
            else return limit - 4;
        } else return getNumber(limit - 1);
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

    public static boolean[] sieveBools(int n) {
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