public class Main {

    public static void main(String[] args) {
        //System.out.println(findNumberOfCuboids(100));
        System.out.println(findMaxSideLength(1000000));
    }

    private static int findMaxSideLength(int target) {
        // this will be M, but for convenience and readability, a makes more sense to use with Pythagorean triples
        int a = 2;
        int res = 0;

        while (res < target) {
            a++;
            int bLimit = 2 * a;
            int aa = a * a;
            for (int b = 3; b <= bLimit; b++) {
                // check if c is integer
                int bb = b * b;
                double sqrt = Math.sqrt(aa + bb);
                if (sqrt == (int) sqrt) {
                    if (b > a)
                        res += a - (b + 1)/ 2 + 1;
                    else
                        res += b / 2;
                }
            }
        }
        return a;
    }

    // return number of solutions per given M
    private static int findNumberOfCuboids(int mMax) {
        int cMax = (int) Math.sqrt(mMax * mMax + (2 * mMax) * (2 * mMax));
        int res = 0;

        for (int c = 5; c <= cMax; c++) {
            int cc = c * c;
            int aLimit = Math.min((int) Math.sqrt(cc / 2), mMax);
            for (int a = 1; a <= aLimit; a++) {
                int aa = a * a;
                int bb = cc - aa;
                int b = (int) Math.sqrt(bb);

                // impossible state
                if (b > 2*mMax) continue;

                // check if triple found
                if (b * b == bb) {

                    // if b > 2a, (a + x)^2 + (b - x)^2 = 2x^2 + 2ax a^2 + b^2 - 2bx < a^2 + b^2 => x + a < b,
                    // because x >= b / 2 (if x < b / 2, we need to 'rotate cube'/look at other edges)
                    if (b <= 2 * a)
                        res += a - (b + 1)/ 2 + 1;  // c is shortest path when a >= d >= 1/2 * b, where b = d + h
                    if (b <= mMax)
                        res += a / 2;

                    //System.out.println(a + " "+ b + " "+c + " res " + res);
                }
            }
        }
        return res;
    }
}