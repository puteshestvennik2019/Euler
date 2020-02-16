import java.util.HashMap;

public class Trie {
    private TrieNode root = new TrieNode(0);
    private int triangleCounter = 0;


    public void insert(String word) {
        TrieNode current = root;
        int prevVal = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int val = ch - 'A' + prevVal + 1;
            current = current.getChildren().computeIfAbsent(ch, c -> new TrieNode(val));
            current.setSize();
            prevVal = val;
        }
        current.setEndOfWord(true);
        if (isTriangle(prevVal)) triangleCounter++;
    }

    public TrieNode getRoot() {
        return root;
    }

    public int getTriangleCounter() {
        return triangleCounter;
    }

    public void traverse(char c) {
        TrieNode node = root;
        HashMap<Character, TrieNode> children = showChildren(node.getChildren().get(c));
        System.out.println(children);
    }

    public boolean isTriangle(int val) {
        int n = 0;
        int i = 0;
        while (n < val){
            n += ++i;
        }
        if (n == val) return true;
        else return false;

    }

    public int getNodeVal(String word) {
        TrieNode current = root;
        TrieNode node = current;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            node = current.getChildren().get(ch);
            current = node;
        }
        return current.getVal();
    }

    public HashMap<Character, TrieNode> showChildren(TrieNode node) {
        return node.getChildren();
    }

    public HashMap<Character, TrieNode> showChildren(char c) {
        TrieNode node = root;
        return showChildren(node.getChildren().get(c));
    }

    public void showChildren(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            showChildren(node);
            TrieNode current = node.getChildren().get(c);
            node = current;
        }
    }

    public int getSize(TrieNode node) {
        return (node == null) ? 0 : node.getSize();
    }

    public void getSize(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            TrieNode current = node.getChildren().get(c);
            node = current;
            System.out.println(getSize(node) + " " + node);
        }
    }

    public boolean find(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }
}


