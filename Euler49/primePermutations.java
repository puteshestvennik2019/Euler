public class Main {

    public static void main(String[] args) {
	    List<Integer> primes = sieveOfEratosthenes(10000);
        primePermutationsHM(primes);
    }

    // one approach is to find all permutations of a number and then look for arithmetic progression within groups
    public static void primePermutationsHM(List<Integer> primes) {
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (int p : primes) {
            int[] nums = new int[4];
            int num = p;
            int i = 0;

            while (num > 0) {
                nums[i++] = num % 10;
                num /= 10;
            }

            // save the number (p) in a key hashed from the lowest permutation (s) of the number 
            Arrays.sort(nums);
            String s = Arrays.toString(nums);

            List<Integer> ll = map.getOrDefault(s, new LinkedList<>());
            ll.add(p);
            map.put(s, ll);
        }

        // loop through map entries; if at least 3 numbers are found, check for common difference
        for (Map.Entry mEntry : map.entrySet()) {
            List<Integer> numbers = (List<Integer>) mEntry.getValue();

            if (numbers.size() >= 3) {
                int[] res = findTripletWithCommonDifference(numbers);

                if (res.length == 3) {
                    System.out.println(Arrays.toString(res));
                }
            }
        }
    }

    public static int[] findTripletWithCommonDifference(List<Integer> list) {
        // consider A[j] as middle element of Arithmetic Progression
        for (int j = 1; j < list.size() - 1; j++)
        {
            // start with left and right index of j
            int i = j - 1, k = j + 1;

            // Find all i and k such that (i, j, k) forms a triplet of AP
            while (i >= 0 && k < list.size())
            {
                // if (A[i], A[j], A[k]) forms a triplet
                if (list.get(i) + list.get(k) == 2 * list.get(j))
                {
                    int[] res = {list.get(i), list.get(j), list.get(k)};
                    return res;
                }
                // else if (A[i] + A[k]) is less than 2*A[j] then
                // try next k. Else, try previous i.
                else if (list.get(i) + list.get(k) < 2 * list.get(j)) {
                    k++;
                } else {
                    i--;
                }
            }
        }
        return new int[0];
    }

    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();

        for (int i = 1000; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}