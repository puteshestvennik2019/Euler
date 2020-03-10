public class Main {

    // solutions to Pell equation x^2 - D*y^2 = 1 are found in convergents of sqrt(D), where x/y = sqrt(D) approx.
    // I am not too sure and have no time to dive deep into this one, but as far as i understand,
    // equation written in the form (x + y*sqrt(D)) * (x-y*sqrt(D)) = 1, left part: lim INF, while right part lim 0
    // for example: D = 14, one convergent of sqrt(14) = 15/4 => x = 15, y = 4:
    // 15 + 4 * 15/4 -> INF and 15 - 4 * 15/4 -> 0
    // but most importantly, 15*15 - 14*4*4 = 1
    
    public static void main(String[] args) {
        final int limit = 1000;
        BigInteger maxX = BigInteger.valueOf(0);
        int res = 0;

        for (int D = 2; D <= limit; D++) {
            int root = (int) Math.sqrt(D);  // first coefficient / term, precisely a0
            if (root * root == D) continue;     // D cannot be a perfect square

            // algorithm for periodic continued fraction, where a0, a1, a2... an are n-th coefficients
            // and n-th convergent = an * previous convergent + convergent before previous
            int m = 0;
            int d = 1;
            int a = root;

            BigInteger nom1 = BigInteger.valueOf(1);  // nominator of continued fraction current minus 1
            BigInteger den1 = BigInteger.valueOf(0);  // denominator current minus 1
            BigInteger nom = BigInteger.valueOf(a);
            BigInteger den = BigInteger.valueOf(1);

            // auxiliary variables
            BigInteger one = BigInteger.valueOf(1);
            BigInteger Di = BigInteger.valueOf(D);

            while (true) {
                BigInteger x2 = nom.multiply(nom);
                BigInteger Dy2 = den.multiply(den).multiply(Di);
                if (x2.subtract(Dy2).equals(one)) break;

                // coefficient
                m = d * a - m;
                d = (D - m * m) / d;
                a = (root + m) / d;

                // convergent
                BigInteger nom2 = nom1;
                BigInteger den2 = den1;
                nom1 = nom;
                den1 = den;
                nom = nom.multiply(BigInteger.valueOf(a)).add(nom2);
                den = den.multiply(BigInteger.valueOf(a)).add(den2);

                // save D for max value of x (nom)
                if (maxX.compareTo(nom) < 0) {
                    maxX = nom;
                    res = D;
                }
            }
        }
        System.out.println(res);
    }
}