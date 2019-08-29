package leetcode.tree;

import java.util.List;

public class TrieTree {
    class TrieNode {
        private TrieNode[] links = new TrieNode[26];
        public String word = null;
        private boolean isEnd;

        public TrieNode() {
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    public TrieNode root;

    public TrieTree() {
        root = new TrieNode();
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

}
