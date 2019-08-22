package leetcode.tree;

public class TrieTree {
    class TrieNode {
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;
        private int size;
        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
            size++;
        }

        public int getLinks() {
            return 0;
        }
    }
}
