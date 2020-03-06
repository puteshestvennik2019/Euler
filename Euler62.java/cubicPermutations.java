import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Long, long[]> map = new HashMap<>();
        long i = 1;

        while (true) {
            long num = i * i * i;
            long key = countSort(num);
            long[] keyVal = map.getOrDefault(key, new long[2]);
            if (keyVal[0] < 1)
                keyVal[0] = num;
            keyVal[1]++;
            map.put(key, keyVal);
            if (keyVal[1] > 4) {
                System.out.println(keyVal[0]);
                break;
            }
            i++;
        }
    }

    public static long countSort(long num) {
        int[] cnt = new int[10];

        while (num > 0) {
            cnt[(int) (num % 10)]++;
            num /= 10;
        }
        long key = 0;
        for (int i = 9; i >= 0; i--) {
            while (cnt[i]-- > 0)
                key = key * 10 + i;
        }
        return key;
    }
}