// simple solution using bitmask
public class Main {

    public static void main(String[] args) {
        int i = 0;

        while (true) {
            i++;
            boolean broken = false;
            int BM = getBitMask(i);
            for (int m = 2; m < 7; m++) {
                int multiple = i * m;
                if (BM != getBitMask(multiple)) {
                    broken = true;
                    break;
                }
            }
            if (!broken) break;
        }
        System.out.println(i);
    }

    public static int getBitMask(int num) {
        int bm = 0;

        while (num > 0) {
            int n = num % 10 - 1;
            num /= 10;
            if (n > 0)
                bm += 1 << n;
        }
        return bm;
    }
}
