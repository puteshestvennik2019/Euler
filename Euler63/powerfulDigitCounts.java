public class Main {

    // initially, I intended to solve this the right way, ie with integers:
    // for each power, find lower band =>  i^ex >= 10^(ex - 1) => i >= 10^((ex - 1) / ex) or log10(i) >= (n-1) * n
    // until 9^ex < 10^(ex - 1)
    // but, as I was thinking how to avoid using BigInteger, I thought about this simple solution, which, I believe,
    // has good enough precision for this problem

    public static void main(String[] args) {
	    int count = 0;

	    for (int i = 1; i < 10; i++) {
	        double res =  1;
            double multiplier = (double) i / 10;

	        while ((res *= multiplier) >= 0.1) {
                System.out.println(res);
	            count++;
            }
        }
        System.out.println(count);
    }
}
