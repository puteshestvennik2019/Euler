public class Main {

    public static void main(String[] args) {
        System.out.println(spiralDiagonals(1001));
    }

    public static int spiralDiagonals(int a) {
        int res = 1;
        int diags = res;
        for (int i = 3; i <= a; i += 2) {
            for (int j = 0; j < 4; j++) {
                diags += i - 1;
                res += diags;
            }
        }
        return res;
    }
}