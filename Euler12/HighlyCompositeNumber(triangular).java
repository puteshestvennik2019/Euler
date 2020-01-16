// Steps:
// 1 Find first number divided by at least x factors
// 2 Check if the number is triangular number
// 3 If not, check subsequent terms for divisibility and return first number divisible by x

// Looking for smallest number with x factors is akin to looking for smallest number divided by all numbers in a range (refer to Euler 5)
// and as equivalent to searching for Highly Composite Numbers
// (in fact, we could use a list of HCNs here to speed things up, but that's slightly on the cheating side, at least from coding perspective ;-) )
// Factors of composite numbers are combinations of prime numbers (and their exponents).
// Because all powers can include zero, so x = (a + 1)*(b + 1)*(c + 1)...(p + 1), where a, b, c are exponents of primes 
// While looking for HCN, it is important to note a >= b >= c >=... >= z, in other words: exponents are nonincreasing
// and, since a, b, c... are exponents of consecutive primes, no prime number can be skipped (2*3*5 has as many primes as 2*3*7, but is smaller)
// HCN generator will start from 2*3*5*7*11*13...*n, where all exponents (exp = 1) and number of primes = logx
// For x = 500, our base case is 2*3*5*7*11*13*17*19*23, a number with 512 divisors
// Then we will decrement exponents, starting from the end (highest primes), while incrementing powers of the lower-valued primes
// We have to keep track of both the product and its factors

//THIS IS A BRUTE FORCE SOLUTION

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> primeNumbers = sieveOfEratosthenes(2000);
    }

    // find the lowest triangular number with a minimum of divisors minDivs
    public static int HDTN(List<Integer> primeNumbers, int minDivs) {
        int triNum = 3;
        int term = 2;
        while (calcDivisors(primeNumbers, triNum) < minDivs) {
            term++;
            triNum = getTriNumber(term);
        }
        return triNum;
    }

    public static int getTriNumber(int term) {
        return term * (term + 1) / 2;
    }

    // because this is an approximation anyways, we will use a simplified formula Tn = n^2 / 2
    public static int getTriTerm(int num) {
        return (int) Math.sqrt(2 * num); // returns Math.floor()
    }

    public static ArrayList<Integer> primeDivisorsGenerator(List<Integer> primeNumbers, int num) {
        ArrayList<Integer> primeDivisors = new ArrayList<>();

        //List<Integer> primes = sieveOfEratosthenes((int) Math.sqrt(num));
        List<Integer> primes = primeNumbers;
        for (int prime : primes) {
            if (num % prime == 0) {
                primeDivisors.add(prime);
                while (num % prime == 0) {
                    num /= prime;
                }
            }
            if (num < 2) break;
            if (isPrime(num)) {
                primeDivisors.add(num);
                break;
            }
        }
        return primeDivisors;
    }

    public static int calcDivisors(List<Integer> primeNumbers, int number) {
        if (isPrime(number)) return 2;

        int divisors = 1;
        List<Integer> primeDivs = primeDivisorsGenerator(primeNumbers, number);
        for (int prime : primeDivs) {
            int exp = 1;
            while (number % prime == 0) {
                exp++;
                number /= prime;
            }
            divisors *= exp;
        }
        return divisors;
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

    public static boolean isPrime(long number) {
        List<Integer> primes = sieveOfEratosthenes((int) Math.floor(Math.sqrt(number)));
        // System.out.println(primes);
        for (int prime : primes) {
            if (number % prime == 0)
                return false;
        }
        return true;
    }
}