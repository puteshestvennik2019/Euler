public class Main {

    public static void main(String[] args) {
        // number of items for the sieve is arbitrary for optimal solution
        List<Integer> primes = sieveOfEratosthenes(10000);

        int n = distinctPrimeFactors(primes);
        System.out.println(n);

    }

    // this solution only iterates over even numbers only, until two numbers meeting criteria are found
    // only then it checks the remaining numbers to complete the series
    public static void fourDistinctPrimeFactors(List<Integer> primes) {
        // number in series of 4 with distinct factors cannot be less than 2*2*3*5*7
        int bottom = 420;
        int i = bottom;
        while (true) {
            if (hasNPrimeFactors(primes, i)) {
                if (hasNPrimeFactors(primes, i + 2)) {
                    if (hasNPrimeFactors(primes, i + 1)) {
                        if (hasNPrimeFactors(primes, i - 1)) {
                            i = i - 1;
                            break;
                        } else if (hasNPrimeFactors(primes, i + 3)) {
                            i = i + 3;
                            break;
                        }
                    }
                }
                else i += 2;
            }
            i += 2;
        }
        System.out.println(i);
    }

    // in this solution, we loop over every single number until n consecutive are found
    // while numbersFound < n, we jump over numbersFound 
    public static int distinctPrimeFactors(List<Integer> primes) {
        // n consecutive numbers
        int n = 4;

        // number in series of 4 with distinct factors cannot be less than 2*2*3*5*7
        int bottom = 0;
        int i = bottom - 1;
        int numbersFound = 0;

        while (numbersFound < n) {
            ++i;
            if (hasNPrimeFactors(primes, i)) numbersFound++;
            else {
                i += (numbersFound > 1) ? numbersFound - 1 : 0;
                numbersFound = 0;
            }
        }
        return i - n + 1;
    }

    public static boolean hasNPrimeFactors(List<Integer> primes, int num) {
        int i = 0;
        int N = 4;
        for (int p : primes) {
            long f = p * p;
            if (f > num) {
                i++;
                break;
            }
            if (num % p == 0) {
                i++;
                if (i > N) return false;
                while (num % p == 0) num /= p;
                if (num == 1) break;
            }
        }
        return i == N;
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