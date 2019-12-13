package leetcode.tree;

public class TrieTree {
    private TrieNode root;

    public TrieTree(TrieNode root) {
        this.root = root;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (!node.containsKey(curLetter)) {
                node.put(curLetter, new TrieNode());
            }
            node = node.get(curLetter);
        }
        node.setEnd();
        node.word = word;
    }

    //从根遍历看看这个单词是否存在
    public TrieNode search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;

    }

}
