public class Link {
    private int number;
    private int ending;     // two last digits
    private int type;       // triangle, square, pentagonal, hexagonal, heptagonal, or octagonal

    public Link(int number, int type) {
        this.number = number;
        this.ending = truncate(number);
        this.type = type;
    }

    public static int truncate(int number) {
        return number % 100;
    }

    public int getEnding() {
        return ending;
    }

    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Link{" +
                "number=" + number +
                ", type=" + type +
                '}';
    }
}