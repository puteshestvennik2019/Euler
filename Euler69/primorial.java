public class Main {

    // we are looking for n with least phi(n), ie fewest coprimes - therefore a number similar to highly composite number.
    // HCN will always have consecutive smallest primes in its factors, however, some factors will have exponents > 1.
    // The number we are looking for, on the other hand will be the largest primorial in given range
    // (product of consecutive smallest primes), because n / phi(n) is independent of n,
    // other than the product is over the distinct prime numbers dividing n
    // I.E. if my logic is correct!
    // examples of n, where n / phi(n) is maxed out:
    // 6 -> 2 * 3
    // 30 -> 2 * 3 * 5
    // 210 -> 2 * 3 * 5 * 7  >  42  >  70
    public static void main(String[] args) {

        int[] primes = {2,3,5,7,11,13,17,19,23,29,31};

        // one liner but required digging
        int limit = 1000000;
        int res = 1;
        int i = 0;

        while (res < limit) {
            res *= primes[i++];
        }
        System.out.println(res / primes[--i]);
    }
}