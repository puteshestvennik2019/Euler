public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> primes = sieveOfEratosthenes(50000);
        int[] res = findCoefficients(primes, 1000);
        System.out.println(res[0] * res[1]);
    }

    // for n = 0, n^2 + a*n + b = prime <=> b = prime
    // 2 is the only even prime, so for b > 2, n^2 + a*n must be even => a must be odd every time n is odd
    // for n = 1, 1 + a + b > 1 => a > -b
    public static int[] findCoefficients(ArrayList<Integer> primes, int limit) {
        int maxPrimes = 0;
        int[] max = new int[2];
        for (int b : primes) {
            int incr = 1;
            if (b > limit) break;
            if (b == 2) incr = 0;
            for (int sign = 0; sign < 2; sign ++){
                b *= -1;
                int lim = (b < 0) ? 0 : limit;
                for (int a = -b; a < lim; a += 1 + incr) {
                    int n = 0;
                    int counter = 0;
                    while (true) {
                        int res = n * n + a * n + b;
                        int check = primes.get(primes.size() - 1);
                        if (res > check) primes = sieveOfEratosthenes(check * 2);
                        if (isPrimeBinarySearch(res, primes)) {
                            counter++;
                            n++;
                        }
                        else {
                            if (counter > maxPrimes) {
                                maxPrimes = counter;
                                max[0] = a;
                                max[1] = b;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return max;
    }

    public static boolean isPrimeBinarySearch(int number, ArrayList<Integer> primes) {
        int size = primes.size() - 1;
        int beg = 0;
        while (beg <= size) {
            int mid = (size + beg) / 2;
            int check = primes.get(mid);
            if (check == number) return true;
            else if (check > number) size = mid - 1;
            else beg = mid + 1;
        }
        return false;
    }

    public static ArrayList<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
