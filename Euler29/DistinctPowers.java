public class Main {

    public static void main(String[] args) {
        int n = 100;
        printPowers(n,n);
    }

    public static void printPowers(int a, int exp) {
        Set powers = new HashSet();
        for (int i = 2; i <= a; i++) {
            for (int e = 2; e <= exp; e++) {
                double res = Math.pow(i,e);
                //if (powers.contains(res)) System.out.println(i + "^" + e);
                powers.add(res);
            }
        }
        System.out.println(powers.size());
    }
}