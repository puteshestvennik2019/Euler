public class Main {

	// this problem calls for a solution using combinatorics or hashmapping duplicate numbers (12, 21, 120, 102, 201, 210
	// and so on are one and the same number for the purpose of this problem)
	// I have used a boolean array the size of 7 single digit squares which is also an overkill since it stores values very sparsely
    public static void main(String[] args) {
	    int target = 10000000;
	    int arrLimit = 7*9*9 + 1;
	    boolean[] helper = new boolean[arrLimit];
	    int cnt = 0;
	    int[] squares = new int[]{0, 1, 4, 9, 16, 25, 36, 49, 64, 81};

	    for (int i = 1; i < target; i++) {
	        int n = i;
	        while (true) {
	            if (n == 1) {
	                break;
                }
	            if (n == 89) {
	                cnt++;
	                if (i < arrLimit) {
						helper[i] = true;
					}
	                break;
                }
	            if (n < i) {
	                if (helper[n]) {
	                    cnt++;
	                    if (i < arrLimit)
	                    	helper[i] = true;
                    }
	                break;
                }
	            int num = 0;
	            while (n > 0) {
	                num += squares[n % 10];
	                n /= 10;
                }
	            n = num;
            }
        }
        System.out.println(cnt);
    }
}