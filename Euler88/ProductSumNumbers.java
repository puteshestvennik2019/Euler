public class Main {

    public static void main(String[] args) {

        int target = 12000;
        int[] minProdSum = new int[target + 1];
        Arrays.fill(minProdSum, Integer.MAX_VALUE);
        int res = 0;

        factorize(0,0,1, minProdSum, target);
        
        // sort array and sum up non-duplicate numbers
        Arrays.sort(minProdSum, 2, target + 1);
        int prev = minProdSum[1];
        for (int i = 2; i <= target; i++) {
            if (minProdSum[i] != prev) res += minProdSum[i];
            prev = minProdSum[i];
        }
        System.out.println(res);
    }

    // loop over all possible factorizations and update array with min value computed for a given k
    private static void factorize(int factors, int sum, int prod, int[] array, int target) {
        int k = prod - sum + factors;
        if (k <= target)
            array[k] = Math.min(array[k], prod);
        for (int i = 2; i <= (target << 1) / prod; i++) {
            if (prod > (target << 1)) break;
            factorize(factors + 1,sum + i, prod * i, array, target);
        }
    }
}