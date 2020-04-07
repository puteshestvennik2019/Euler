public class Main {
    public static void main(String[] args) {
        int n = 50;
        int res = 2 * n * n;    // when right angle is on one of the axis (points (0,y) and (x,0))
        res += n * n;           // when right angle is at (0,0), each point (x,0) completes triangle with points (0,y)

        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <=n; y++) {
                // for point P = (x,y), slope of function parallel to (0,0)->(x,y) is -x/y
                // and function crosses point (x,y) and (x+y, y-x)
                // in counting integer points on this linear function, we must consider ranges above and below
                // however, using symmetry provided by y = x, we can simply multiply results by 2 (for either points above or below f(x)
                // depending on the slope, range can equal [y, 0] or [x, n]
                // x1 = x0 + dy/d * t, where dy = y, d = gcd(x,y), t = 1,2,3,4...n
                // double slope = -x / y;
                // int b = y + x * x / y;
                int d = gcd(x,y);
                res += Math.min(y * d / x, (n - x) * d / y) * 2;
            }
        }
        System.out.println(res);
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}