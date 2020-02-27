public class Card implements Comparable<Card> {
    int value;
    int colour;

    public Card(String card) {
        this.value = getValue(card.charAt(0));
        this.colour = getColour(card.charAt(1));
    }

    public int getValue(char val) {
        switch (val) {
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
            default:
                return val - '0';
        }
    }

    public int getColour(char col) {
        switch (col) {
            case 'C':
                return 0;
            case 'D':
                return 1;
            case 'H':
                return 2;
            case 'S':
                return 3;
            default:
                return -1;
        }
    }

    public int getValue() {
        return value;
    }

    public int getColour() {
        return colour;
    }

    @Override
    public int compareTo(Card other) {
        return getValue() < (other.getValue()) ? 1 : -1;
    }
}
