public class DigitFactorialChain {
    private final long[] factorials;
    private final int limit = 1000000;
    HashMap<Long, Integer> lengthsOfComputedChains;

    public DigitFactorialChain() {
        lengthsOfComputedChains = new HashMap<>();
        this.factorials = new long[10];
        factorials[0] = 1;

        for (int i = 1; i < 10; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
    }

    public void solution() {
        int res = 0;
        for (long i = 1; i <= limit; i++) {
            int chainLength = buildChain(i);
            lengthsOfComputedChains.put(i, chainLength);
            if (chainLength == 60) res++;
        }
        System.out.println(res);
    }

    private int buildChain(long num) {
        TreeMap<Long, Integer> chain = new TreeMap<>();
        long n = num;
        int itemsInChain = 0;

        while (!chain.containsKey(n)) {
            if (n < num) {
                break;
            }
            chain.put(n, ++itemsInChain);
            int[] digits = geDigits(n);
            n = 0;
            for (int d: digits) {
                n += factorials[d];
            }
        }
        return itemsInChain + lengthsOfComputedChains.getOrDefault(n, 0);
    }

    private int[] geDigits(long n) {
        int[] res = new int[20];
        int count = 0;
        while (n > 0) {
            res[count++] = (int) (n % 10);
            n /= 10;
        }
        return Arrays.copyOfRange(res, 0, count);
    }
}


// solution below computes and prints complete chains in the given range

//public class DigitFactorialChain {
//    private final long[] factorials;
//    private final int limit = 100;
//    HashMap<Long, long[]> computedChains;
//
//    public DigitFactorialChain() {
//        computedChains = new HashMap<>();
//        this.factorials = new long[10];
//        factorials[0] = 1;
//
//        for (int i = 1; i < 10; i++) {
//            factorials[i] = factorials[i - 1] * i;
//        }
//    }
//
//    public void solution() {
//        int res = 0;
//        for (long i = 1; i <= limit; i++) {
//            long[] arr = buildChain(i);
//            computedChains.put(i, arr);
//            if (arr.length == 60) res++;
//            System.out.println(i + ": " + Arrays.toString(arr));
//        }
//        System.out.println(res);
//    }
//
//    private long[] buildChain(long num) {
//        TreeMap<Long, Integer> chain = new TreeMap<>();
//        long n = num;
//        boolean copying = false;
//        // arrays and pointer for copying of already computed chain
//        long[] chainToCopy = new long[0];
//        int pointer = 1;    // points to index 1, because index 0 will have been already copied, as it starts the chain
//        int itemsInChain = 0;
////        System.out.println("chain for " + n);
//
//        while (!chain.containsKey(n)) {
//            chain.put(n, itemsInChain++);
//            if (copying || n < num) {
//                // this number has been already computed and we can fetch it
//                if (!copying) {
//                    copying = true; // flag that there is no need to compute these numbers again, simply fetch and compare
//                    chainToCopy = computedChains.get(n);
//                }
//                // reached end of chain we have been copying
//                if (pointer >= chainToCopy.length) {
//                    break;
//                }
//                n = chainToCopy[pointer++];
//                continue;
//            }
//            int[] digits = geDigits(n);
//            n = 0;
//            for (int d: digits) {
//                n += factorials[d];
//            }
////            System.out.println(n);
//        }
//        long[] chainArr = new long[chain.size()];
//        for (long key: chain.keySet()) {
//            chainArr[chain.get(key)] = key;
//        }
//        return chainArr;
//    }
//
//    private int[] geDigits(long n) {
//        int[] res = new int[20];
//        int count = 0;
//        while (n > 0) {
//            res[count++] = (int) (n % 10);
//            n /= 10;
//        }
//        return Arrays.copyOfRange(res, 0, count);
//    }
//}
