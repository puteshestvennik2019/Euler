/* Lattice paths from (0, 0) to (n, k) on Cartesian plane, which can only be formed using two directions (one of the pairs: LD, LU, RD, RU),
    are counted using binominal coefficient (n + k) choose n
    The result is k-th element in (n+k)th row of Pascal's triangle
 */
public class Main {

    public static void main(String[] args) {
	    int[] point = {20, 20}; // let's call it a tuple :-D
        int n = Math.min(point[0], point[1]);
        int k = Math.max(point[0], point[1]);
        long res = 1;

        for (int i = n + k; i > 1; i--) {
            int j = (i % 2 == 0) ? 2 : i;
            if (i > k) {
                res *= j;
            } else if (i % 2 == 0) {
                j = i / 2;
                res /= j;
                i--;
            }
        }
        System.out.println(res);
    }
}