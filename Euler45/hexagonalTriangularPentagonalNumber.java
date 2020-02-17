// Every hexagonal number is a triangular number, so we only need to check the former and pentagonal numbers
// Thus m(2m - 1) == n(3n-1)/2  =>  3*(4*m - 1)^2 - (6*n - 1)^2 = 2
// x = 6 * n - 1, y = 4 * m - 1   =>  3y^2 - x^2 = 2  =>  x = sqrt(3y^2 - 2), y = Math.sqrt((2 + x * x) / 3)
// we are looking for number greater than 143th hexagonal term, so iteration starts at x = 6 * 144 - 1
public class Main {

    public static void main(String[] args) {

        int m = 143;
        while (true) {
            long y = 4 * ++m - 1;
            double x = Math.sqrt(3 * y * y - 2);
            if (x == (int) x) {
                double n = (x + 1) / 6;
                if (n == (int) n) break;
            }
        }
        System.out.println("Next number that is hexagonal, pentagonal and triangular has hexagonal term: " + m);
        System.out.println(m * 2 * m - m);
    }
}