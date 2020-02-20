public class Main {

    public static void main(String[] args) {
        int N = 1000;
        int lastDigits = 10;
        long res = 405071317;
        long tenDig = (long) Math.pow(10, lastDigits);
        
        for (int i = 11; i <= N; i++) {
            int j = 1;
            long prod = i;
            while (++j <= i) {
                prod *= i;
                prod %= tenDig;
            }
            res += prod;
            res %= tenDig;
        }
        System.out.println(res);
    }
}