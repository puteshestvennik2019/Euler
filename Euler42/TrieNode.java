import java.util.HashMap;

public class TrieNode {

    HashMap<Character, TrieNode> children;
    boolean endOfWord = false;
    int val;
    int size;

    public TrieNode(int val) {
        children = new HashMap<>();
        this.setSize();
        this.val = val;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    void printChildren() {
        HashMap<Character, TrieNode> map = this.getChildren();
        map.forEach((c, k) -> System.out.println(this.val + " " + c));
    }

    int getVal() {
        return this.val;
    }

    int getSize() {
        return this.size;
    }

    void setSize() {
        this.size++;
    }
}
