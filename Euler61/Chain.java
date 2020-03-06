public class Chain {
    private Link[] chain;
    private int bitmask;
    private int ending;
    private int length;
    private int head;

    public Chain(Link num1, Link num2) {
        chain = new Link[6];
        this.chain[0] = num1;
        this.chain[1] = num2;
        this.length = 2;
        this.bitmask = (1 << num1.getType() | 1 << num2.getType());
        this.ending = num2.getEnding();
        this.head = num1.getNumber() / 100;
    }

    public boolean concatenate(Chain chain) {
        if ((chain.getBitmask() & this.bitmask) != 0) return false; // Links/number are not of unique polygonal type
        else {
            add(chain);
            this.bitmask |= chain.getBitmask(); // update bitmask
            this.ending = chain.getEnding();
        }
        return true;
    }

    public void add(Chain chain) {
        for (int i = 0; i < chain.getLength(); i++) {
            Link num = chain.getChain()[i];
            this.chain[length] = num;
            this.length++;
        }
    }

    public void removeLinks(Chain ch) {
        int i = ch.getLength();
        this.bitmask -= ch.getBitmask();

        while (i-- > 0) {
            chain[--length] = null;
        }
    }

    public Link[] getChain() {
        return chain;
    }

    public int getBitmask() {
        return bitmask;
    }

    public int getEnding() {
        return ending;
    }

    public int getLength() {
        return length;
    }

    public int getHead() {
        return head;
    }
}