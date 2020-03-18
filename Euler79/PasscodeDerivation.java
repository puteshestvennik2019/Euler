// for this problem, there might be no need to BF or backtrack for solution, as there might be no ambiguity at the end
// However, the code can be improved so that it proceeds with finding solution once this initial sweep/preparation has been done

// code below is an overkill for this problem, but, since I am also looking into something else that involves interval trees, I thought this could be a nice practice
// and some methods are written for reusability in the other problem

public class PasscodeDerivation {
    private String solution;
    private PriorityQueue<Digit> logs;
    private Digit[] logsArray;

    public PasscodeDerivation(String[] stringLogs) {
        this.logsArray = new Digit[10];
        for (String log: stringLogs) {
            int index = log.charAt(1) - '0';
            if (logsArray[index] == null) {
                // add new entry
                 Digit digit = new Digit(log);
                 logsArray[index] = digit;
            }
            else {
                Digit digit = logsArray[index];
                if (!digit.contains(log.charAt(2))) {
                    digit.setRightNeighbours(log.charAt(2));
                }
                if (!digit.contains(log.charAt(0))) {
                    digit.setLeftNeighbours(log.charAt(0));
                }
            }
        }

        this.logs = new PriorityQueue<>(new comparatorPQ());
        for (Digit entry: logsArray) {
            if (entry == null) continue;
            this.logs.add(entry);
        }
        solve(this.logs);
    }

    private void solve(PriorityQueue<Digit> pq) {
        List<Digit> sorted = new ArrayList<>();
        Stack<Digit> stack = new Stack<>();

        while (!pq.isEmpty()) {
            while (!pq.isEmpty()) {
                Digit entry = pq.poll();
                if (entry.getCntL() == 1) removeDone(entry,true);
                if (entry.getCntR() == 1) removeDone(entry, false);

                boolean shortestArrayLeft = getShortestArray(entry);
                char dig = entry.getDig();

                for (Digit arrayEntry : logsArray) {
                    if (arrayEntry == null) continue;
                    if (arrayEntry.getDig() == dig) continue; // the same entry

                    removeIncorrect(arrayEntry, entry, shortestArrayLeft);
                }
                if (entry.isComplete()) sorted.add(entry);
                else stack.push(entry);
            }

            while (!stack.isEmpty()) {
                pq.add(stack.pop());
            }
        }

        Digit first = sorted.get(0);
        System.out.println(getSides(first));
    }

    private String getSides(Digit first) {
        solution = "" + first.getDig();
        while (first != null) {
            solution = first.getLeftNeighbours()[0] + solution;
            first = logsArray[first.getLeftNeighbours()[0] - '0'];
        }
        first = logsArray[solution.charAt(solution.length() - 1) - '0'];
        while (first != null) {
            solution += first.getRightNeighbours()[0];
            first = logsArray[first.getRightNeighbours()[0] - '0'];
        }
        return solution;
    }

    // if any log has only one neighbour, other logs cannot have the same neighbour on this side
    private void removeDone(Digit log, boolean left) {
        if (left) removeDoneLeft(log);
        else removeDoneRight(log);
    }

    private void removeDoneLeft(Digit log) {
        char remove = log.getLeftNeighbours()[0];
        for (Digit entry: logsArray) {
            if (entry == log || entry == null) continue;
            entry.removeNeighbour(true, remove);
        }
    }

    private void removeDoneRight(Digit log) {
        char remove = log.getRightNeighbours()[0];
        for (Digit entry: logsArray) {  
            if (entry == log || entry == null) continue;
            entry.removeNeighbour(false, remove);
        }
    }

    private void removeIncorrect(Digit removeFrom, Digit removeThis, boolean removeLeft) {
        char dig = removeThis.getDig();
        // if dig is neighbour of removeFrom, it will be present in its array (left or right),
        // therefore neighbours of dig cannot be there
        if (removeLeft && removeFrom.isNeighbour(true, dig)) {
            removeLeftNeighbours(removeThis, removeFrom);
        } else if (!removeLeft && removeFrom.isNeighbour(false, dig)) {
            removeRightNeighbours(removeThis, removeFrom);
        }
    }

    private void removeLeftNeighbours(Digit removeThis, Digit removeFrom) {
        char[] removeDuplicatesFromThis = removeThis.getLeftNeighbours();

        for (char ch: removeDuplicatesFromThis) {
            removeFrom.removeNeighbour(true, ch);
        }
    }

    private void removeRightNeighbours(Digit removeThis, Digit removeFrom) {
        char[] removeDuplicatesFromThis = removeThis.getRightNeighbours();

        for (char ch: removeDuplicatesFromThis) {
            removeFrom.removeNeighbour(false, ch);
        }
    }

    private boolean getShortestArray(Digit object) {
        int arr1 = object.getCntL();
        int arr2 = object.getCntR();

        if (arr1 < 2) return false;
        else if (arr2 < 2) return true;
        return arr1 < arr2;
    }

    // compares entries by length of arrays (leftNeighbours, rightNeighbours) - the idea is to have shortest arrays on the top,
    // so they are dealt with first. Once their neighbours are established, they can be popped off the PQ
    class comparatorPQ implements Comparator<Digit> {

        @Override
        public int compare(Digit o1, Digit o2) {
            int shortestArray1 = getShortestArray(o1);
            int shortestArray2 = getShortestArray(o2);

            if (shortestArray1 < shortestArray2) return -1;
            else return 1;
        }

        private int getShortestArray(Digit object) {
            int arr1 = object.getCntL();
            int arr2 = object.getCntR();

            if (arr1 < 2 || arr2 < 2) return Math.max(arr1, arr2);
            else return Math.min(arr1, arr2);
        }
    }
}