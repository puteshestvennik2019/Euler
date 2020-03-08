import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        char[] convergent = nthConvergentOfE(100).toCharArray();
        int countNominatorDigits = 0;

        for (char c : convergent) {
            if (c == ' ') break;
            countNominatorDigits += c - '0';
        }
        System.out.println(countNominatorDigits);
    }
    
    // since in this particular problem we are only interested in the nominator, for better performance we could skip calculating denominators altogether
    public static String nthConvergentOfE(int term) {
        // nominators and denominators of first and '0th' convergents
        BigInteger n0 = new BigInteger("1");
        BigInteger d0 = new BigInteger("0");
        BigInteger n1 = new BigInteger("2");
        BigInteger d1 = new BigInteger("1");

        BigInteger multiplicator = new BigInteger("2");  // following the pattern in e's continued fraction, in every 3 elements,
                                // there are two 1's and a third number increasing by 2

        for (int i = 2; i <= term; i++) {
            BigInteger n = n1;
            BigInteger d = d1;
            if (i % 3 == 0) {
                n1 = n1.multiply(multiplicator);
                d1 = d1.multiply(multiplicator);
                multiplicator = multiplicator.add(new BigInteger("2"));
            }
            n1 = n1.add(n0);
            d1 = d1.add(d0);

            n0 = n;
            d0 = d;
        }
        return n1.toString() + " / " + d1.toString();
    }
}