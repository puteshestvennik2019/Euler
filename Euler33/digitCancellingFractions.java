public class Main {

    public static void main(String[] args) {

		boolean checkedPair = false;

		int over = 1;
		int under = 1;

		for (int i = 10; i < 99; i++) {
			if (i % 10 == 0) continue;
			int[] iDigits = {i % 10, i / 10};
			for (int j = i + 1; j < 100; j++) {
				if (j % 10 == 0) continue;
				int[] jDigits = {j % 10, j / 10};
				if (iDigits[0] != jDigits[0] && iDigits[0] != jDigits[1] &&
						iDigits[1] != jDigits[0] && iDigits[1] != jDigits[1]) continue;
				if (iDigits[0] == jDigits[1])
					checkedPair = i * (j % 10) == j * (i / 10);
				else checkedPair = i * (j / 10) == j * (i % 10);

				if (checkedPair) {
					int d = gcd(j, i);
					over *= i / d;
					under *= j / d;
				}
			}
		}
		System.out.println(under / gcd(under, over));
    }

    public static int gcd(int a, int b) {
    	if (b == 0) return a;
    	return gcd(b, a % b);
	}
}