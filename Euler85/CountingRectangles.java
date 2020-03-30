// Key to solving this is observation that res = bigSum(x = 1, y = 1 -> n, m) x * y
// Sum of products can be written as product of sums: 1+2+3...+x = x*(x+1)/2
public class Main {

    public static void main(String[] args) {
        int target = 2000000;
        int nMax = (int) Math.sqrt(target * 2);
        int nMin = (int) Math.sqrt(Math.sqrt(target * 4));
        int n = nMin;
        int m = nMin;
        int min = target;
        int res = 0;

        while (n > 0 && m < nMax) {
            int numberOfSquares = (n * (n + 1) * m * (m + 1)) >> 2;
            int curMin = Math.abs(numberOfSquares - target);
            if (curMin < min) {
                min = curMin;
                res = n * m;
            }
            if (numberOfSquares < target) m++;
            else n--;
        }
        System.out.println(res);
    }
}