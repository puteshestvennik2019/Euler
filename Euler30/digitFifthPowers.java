public class Main {

    public static void main(String[] args) {
        int power = 5;
        int[] powers = generatePowers(power);
        System.out.println(findSumDigitPowers(powers));
    }

    public static int findSumDigitPowers(int[] powers) {
        int minSum = 10;
        int sum = 0;
        for (int i = minSum; i < findMaxDigits(powers[9]) * powers[9]; i++) {
            int sumOfPowers = 0;
            int num = i;
            while (num > 0) {
                sumOfPowers += powers[num % 10];
                num /= 10;
            }
            if (i == sumOfPowers) sum += sumOfPowers;
        }
        return sum;
    }

    public static int findMaxDigits(int powerOfNine) {
        int dig = 2;
        int num = 99;
        while (powerOfNine * dig > num) {
            num *= 10;
            num += 9;
            dig++;
        }
        return dig;
    }

    public static int[] generatePowers(int n) {
        int[] powers = new int[10];
        for (int i = 0; i < 10; i++) {
            powers[i] = (int) Math.pow(i, n);
        }
        return powers;
    }
}