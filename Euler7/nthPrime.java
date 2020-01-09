import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
    	nthPrime(110000, 10001);
    }

    // n is the upper bound of range for the prime generator, while n-th is the element we are looking for
    public static void nthPrime(int n, int nth) {
    	boolean[] prime = new boolean[n + 1];
    	Arrays.fill(prime, true);
    	for (int p = 2; p * p <= n; p++) {
        	if (prime[p]) {
            	for (int i = p * 2; i <= n; i += p) {
                	prime[i] = false;
            	}
        	}
    	} 

    	int count = 0;
    	int i = 2;
    	boolean brokenLoop = false;
    	while (i <= n) {
       		if (prime[i]) {
            	count++;
            	if (count == nth) {
            		System.out.println(nth + "-th prime number is: " + i);
            		brokenLoop = true;
            		break;
            	}
        	}
        	i++;
    	}
    	if (!brokenLoop)
    		System.out.println("Only " + count + " prime numbers found in the range 0-" + n);
    }
}
