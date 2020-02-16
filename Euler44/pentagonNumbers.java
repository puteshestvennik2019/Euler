import java.util.*;

public class Main {

    public static void main(String[] args) {
        long st = System.nanoTime();
        int sum = 0;
        int dif = 0;
        int num = 0;
        int lastDif = 1;
        Stack<Integer> pentagonalNumbers = new Stack<>();
        boolean found = false;

        while (!found) {
            num += lastDif;
            ListIterator<Integer> itr = pentagonalNumbers.listIterator(pentagonalNumbers.size());
            lastDif += 3;
            while (itr.hasPrevious()) {
                int p = itr.previous();
                if (p < lastDif) break;
                dif = num - p;
                sum = num + p;
                found = isPentagonal(sum) && isPentagonal(dif);
                if (found) break;
            }
            pentagonalNumbers.push(num);
        }
        long end = System.nanoTime();
        System.out.println((end - st)/1000000 + "ms");
        System.out.println(dif);
    }

    public static boolean isPentagonal(int n) {
        double res = (Math.sqrt(24 * n + 1) + 1)  / 6;
        return res == (int) res;
    }
}