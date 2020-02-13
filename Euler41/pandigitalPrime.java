public class Main {

    public static void main(String[] args) {
        // since every pandigital number with 2,3,5,6,8,9 digits will not be prime (due to the divisibility of the sum by 3),
        // the largest pandigital prime number might be 7654321
        // two approaches: one looks for a num from the max of the range and then checks if prime & pandigital
        // second approach generates all primes and (for each) checks if pandigital
        // first approach yields result in 5ms, second one needs 70ms, due to the size of the sieve generated
        // except for numbers 2 and 3, every prime number p = num - 1 || p = num + 1, where num % 6 == 0

        int limit = 7654321;
//        List<Integer> primes = sieveOfEratosthenes((int) Math.floor(Math.sqrt(limit)));
//        approachOne(primes, limit);
        List<Integer> primesAll = sieveOfEratosthenes(limit);
        System.out.println(approachTwo(primesAll));

    }

    public static int approachTwo(List<Integer> primes) {
        for (int i = primes.size() - 1; i >= 0; i--) {
            if (checkIfPandigital(primes.get(i))) return primes.get(i);
        }
        return -1;
    }

    public static int approachOne(List<Integer> primes, int limit) {

        int num = limit - 1; // num is the largest number in range, where number % 6 = 0;
        while (true) {
            if (isPrime(num + 1, primes) && checkIfPandigital(num + 1)) {
                num = num + 1;
                break;
            }
            if (isPrime(num - 1, primes) && checkIfPandigital(num - 1)) {
                num = num - 1;
                break;
            }
            num -= 6;
        }
        return num;
    }


    public static boolean checkIfPandigital(int num) {
        // count frequency of digits, break when 0 or duplicate
        int n = 8; // hardcoded - for this challenge we can't have digits 8 or 9
        int[] count = new int[n];
        int digits = 0;
        while (num > 0) {
            int ind = num % 10;
            if (ind >= n) return false;
            digits++;
            count[ind]++;
            num /= 10;
            if (count[ind] > 1 || ind == 0) {
                return false;
            }
        }
        // additional check if numbers with less than 7 digits is passed
        for (int i = digits + 1; i < n; i++) if (count[i] > 0) return false;
        return true;
    }

    private static boolean isPrime(long number, List<Integer> primes) {
        for (int prime : primes) {
            if (number % prime == 0)
                return false;
        }
        return true;
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