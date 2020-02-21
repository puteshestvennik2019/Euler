public class Main {

    public static void main(String[] args) {
        int number = 1000000;
        Deque<Integer> primes = sieveOfEratosthenes(number);
        System.out.println("Largest sequence of consecutive primes making up a prime number below "
                + number +" : "+ consecutivePrimeSum(primes));
    }

    // returns number of terms
    public static int consecutivePrimeSum(Deque<Integer> primes) {
        int n = 1000000;
        int res = 0;
        int cnt = 0;
        int nextPossibleAddition = 0;
        int maxPrime = 0;
        List<Integer> arr = new ArrayList<>();
//        Deque<Integer> primesRemovedFromBack = new LinkedList<>();

        for (int p : primes) {
            res += p;

            if (res > n) {
                nextPossibleAddition = p;
                res -= p;
                break;
            }
            arr.add(p);
            cnt++;
        }

        int max = 0;
        int nextPrime = primes.removeLast();

        // exits when subtracting largest terms will no longer produce higher max (cnt)
        while (max < cnt) {
            boolean firstLoop = true;
            int i = 0;

            // get next lower prime
            while (nextPrime > res) {
//                primesRemovedFromBack.push(nextPrime);
                nextPrime = primes.removeLast();
            }

            int probe = res;

            // subtract lowest elements in set
            while (probe > nextPrime) {
                probe -= arr.get(i++);
            }

            // if the resulting sum is prime
            if (probe == nextPrime) {
                if (max < cnt - i) {
                    max = cnt - i;
                    maxPrime = probe;
                }
            }

            // this check needs to be performed in case adding next prime in sequence, while subtracting from the start, would produce new max
            // this needs only one validation, because in the next loop, we remove largest element in sequence, thus "disconnecting" nextPossibleAddition
            if (firstLoop && (probe + nextPossibleAddition) < n) {
                firstLoop = false;
                int newResult = probe + nextPossibleAddition;

                while (newResult < n && i > 0) {
                    if (isPrime(newResult, primes)) {
                        if (max < cnt - i + 1) {
                            max = cnt - i + 1; // add 1 for nextPossibleAddition
                            maxPrime = newResult;
                        }
                    }
                    newResult += arr.get(--i);
                }
            }
            res -= arr.remove(arr.size() - 1);
            cnt--;
        }
        System.out.println("Prime sum below " + n + " with most consecutive primes: " + maxPrime);
        return max;
    }

    public static Deque<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        Deque<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public static boolean isPrime(long number, Deque<Integer> primes) {
        for (int prime : primes) {
            if (number % prime == 0)
                return false;
        }
        return true;
    }
}

/*
once max sum is found and it is not prime,
look for the next prime number smaller than the sum
if this prime can be achieved subtracting smallest primes, count number of operations needed
save new count / compare to existing max, then subtract largest prime in original sequence
repeat lines [2-4] until starting count is less than current max

 */
