import java.util.Arrays;

public class Hand {
    int handCount = 5;
    private Card[] hand;
    int count;
    private int value; // hand strength

    public Hand() {
        this.hand = new Card[handCount];
    }

    // add cards and check for straight/flush once all cards have been dealt
    public void addCard(Card card) {
        hand[count++] = card;
        if (count > 4) {
            sortByRank();
            boolean hasFlushOrStraight = hasFlush();
            hasFlushOrStraight |= hasStraight();
            if (!hasFlushOrStraight) countCards();
        }
    }

    private void sortByRank() {
        Arrays.sort(this.hand);
    }

    public boolean hasFlush() {
        int first = hand[0].getColour();
        for (int i = 1; i < hand.length; i++) {
            if (hand[i].getColour() != first) return false;
        }
        value = 11;
        return true;
    }

    public boolean hasStraight() {
        int prev = hand[0].getValue();
        for (int i = 1; i < hand.length; i++) {
            int next = hand[i].getValue();
            if (prev - next != 1) return false;
            prev = next;
        }
        if (value != 0) value *= 2; // has Straight Flush
        else {
            value = 10;
        }
        return true;
    }

    public void countCards() {
        if (value == 0)  {
            Integer[] helperArray = new Integer[handCount]; // in this array we will store number of cards of the same kind
            int i = 0; // rearPointer

            while (i < handCount - 1) {
                int j = i + 1; // front pointer

                // when found at least two matching cards, move front pointer
                while (j < handCount && hand[i].getValue() == hand[j].getValue()) j++;

                int marker = j - i; // number of cards of the same rank
                if (marker > 1)
                    value += marker * marker;

                while (i < j) {
                    helperArray[i++] = marker;
                }
            }
            sortByStrength(helperArray);
        }
    }

    // moves strongest cards to the left
    public void sortByStrength(Integer[] groups) {
        for (int i = 1; i < handCount - 1; i++) {
            int j = 1;

            if (groups[i] > groups[i-j]) {
                while (i - j > 0 && groups[i] > groups[i - j - 1]) j++;

                for (int k = groups[i]; k > 0 ; k--) {
                    swap(groups, i, i - j);
                    swap(hand, i, i - j);
                    i++;
                }
            }
        }
    }

    public void swap(Object[] O, int i, int j) {
        Object temp = O[i];
        O[i] = O[j];
        O[j] = temp;
    }

    public int getValue() {
        return value;
    }

    // compares cards one by one
    public boolean compareCards(Hand otherPlayer) {
        for (int i = 0; i < handCount; i++) {
            int res = otherPlayer.hand[i].getValue() - this.hand[i].getValue();
            if (res == 0) continue;
            if (res > 0) return false;
            else break;
        }
        return true;
    }
}
