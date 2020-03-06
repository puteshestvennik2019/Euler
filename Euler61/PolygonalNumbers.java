import java.util.ArrayList;

public class PolygonalNumbers {
    private int mapSize = 100;
    private ArrayList<Link>[] map;
    private ArrayList<Chain>[] chains;
    private int target = (1 << 7) - 2;  // bitmask with all bits set (except for the last zero)
    private int bottom = 1000;          // lower bound

    public PolygonalNumbers() {
        map = new ArrayList[mapSize];       // map grouping polygonal numbers by first two digits
        chains = new ArrayList[mapSize];

        // initialize lists for map array
        for (int i = 10; i < mapSize; i++) {
            this.map[i] = new ArrayList<>();
        }
    }

    // sort of 6 types of numbers by their first two digits
    public void populateMap(int limit) {
        for (int type = 1; type <= 6; type++) {
            int term = getTermFor1000(type);
            int num = getP(type, term);
            int incrementor = type * term + 1;

            while (num < limit) {
                if (num % 100 > 9) {     // penultimate digit cannot be zero)
                    this.map[num / 100].add(new Link(num, type));
                }
                num += incrementor;
                incrementor += type;
            }
        }
    }

    // pair numbers up
    public void makeChains() {
        for (int i = 10; i < mapSize; i++) {
            ArrayList<Link> list = map[i];
            if (list.size() < 1) continue;

            // initialize list of chains
            chains[i] = new ArrayList<>();

            for (Link num: list) {
                for (Link num2: map[num.getEnding()]) {
                    // avoid pairs of same type
                    if (num.getType() != num2.getType()) {
                        chains[i].add(new Chain(num, num2));
                    }
                }
            }
        }
    }

    // iterate over chains of two
    public Chain findCyclicalChain() {

        for (int i = 10; i < mapSize; i++) {
            for (Chain ch: chains[i]) {
                if (ch != null) {
                    Chain res = findMatch(ch);
                    if (res != null) return res;
                }
            }
        }
        return null;
    }

    // join chains of two
    public Chain findMatch(Chain chain) {
        if (chain.getBitmask() == target) {
            if (chain.getEnding() == chain.getHead()) {
                return chain;
            }
            else return null;
        }
        else {
            for (Chain ch: chains[chain.getEnding()]) {
                if (chain.concatenate(ch)) {
                    if (findMatch(chain) != null) return chain;
                    chain.removeLinks(ch);
                }
            }
        }
        return null;
    }

    // calculates given 'type' number for n-th term
    public int getP(int type, int n) {
        switch (type) {
            case 1: {
                return n * (n + 1) / 2;
            }
            case 2: {
                return n * n;
            }
            case 3: {
                return n * (3 * n - 1) / 2;
            }
            case 4: {
                return n * (2 * n - 1);
            }
            case 5: {
                return n * (5 * n - 3) / 2;
            }
            case 6: {
                return n * (3 * n - 2);
            }
            default:
                return 0;
        }
    }

    // returns first 4-dig term
    public int getTermFor1000(int type) {
        return binarySearch(bottom, type);
    }

    public int binarySearch(int res, int type) {
        int end = res;
        int st = 0;

        while (st < end) {
            int m = (st + end) >> 1;
            if (getP(type, m) > res) end = m;
            else st = m + 1;
        }
        return st;
    }
}