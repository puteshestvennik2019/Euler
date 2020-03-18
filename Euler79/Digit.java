// variable names might be misleading, as there might be only one neighbour for each digit...
// left and right neighbours here are simply candidates - these are numbers coming after (right) or before (left) dig
// It would make more sense to call these char arrays 'prev' and 'next', while, at the same time, adding two variables:
// leftNeighbour and rightNeighbour set to correct values, once counters for appropriate arrays drops to 1

public class Digit {
    private char dig;
    private char[] leftNeighbours;
    private char[] rightNeighbours;
    private int cntL;
    private int cntR;

    public Digit(String keylog) {
        this.dig = keylog.charAt(1);
        this.leftNeighbours = new char[20];
        setLeftNeighbours(keylog.charAt(0));
        this.rightNeighbours = new char[20];
        setRightNeighbours(keylog.charAt(2));
    }

    public char[] getLeftNeighbours() {
        return Arrays.copyOfRange(leftNeighbours, 0, cntL);
    }

    public void setLeftNeighbours(char leftNeighbour) {
        // if (cntL >= leftNeighbours.length) resize(leftNeighbours);
        this.leftNeighbours[cntL++] = leftNeighbour;
    }

    public char[] getRightNeighbours() {
        return Arrays.copyOfRange(rightNeighbours, 0, cntR);
    }

    public void setRightNeighbours(char rightNeighbour) {
        // if (cntR >= rightNeighbours.length) resize(rightNeighbours);
        this.rightNeighbours[cntR++] = rightNeighbour;
    }

    public char getDig() {
        return dig;
    }

    public int getCntL() {
        return cntL;
    }

    public int getCntR() {
        return cntR;
    }

    // I guess, this method could simply have two calls to separate private methods removeLeft and removeRight...
    public char removeNeighbour(boolean left, char neighbour) {
        char[] arr;
        int cnt;
        if (left) {
            cnt = cntL;
            arr = leftNeighbours;
        }
        else {
            cnt = cntR;
            arr = rightNeighbours;
        }
        int index = findIndex(neighbour, arr, cnt);
        // if key not found
        if (index < 0) return '-';

        char temp = arr[index];
        arr[index] = arr[--cnt];

        // update counter
        if (left) {
            cntL--;
        } else {
            cntR--;
        }
        return temp;
    }

    public boolean contains(char c) {
        return findIndex(c, this.leftNeighbours, cntL) >= 0 || findIndex(c, this.rightNeighbours, cntR) >= 0;
    }

    private int findIndex(char c, char[] arr, int cnt) {
        for (int i = 0; i < cnt; i++) {
            if (c == arr[i]) return i;
        }
        return -1;
    }

    // returns true if this char is present in neighbours
    public boolean isNeighbour(boolean left, char c) {
        if (left) return isLeftNeighbour(c);
        else return isRightNeighbour(c);
    }

    private boolean isLeftNeighbour(char c) {
        if (findIndex(c, leftNeighbours, cntL) < 0) return false;
        return true;
    }

    private boolean isRightNeighbour(char c) {
        if (findIndex(c, rightNeighbours, cntR) < 0) return false;
        return true;
    }

    public boolean isComplete() {
        if (cntL < 2 && cntR < 2) return true;
        return false;
    }
}