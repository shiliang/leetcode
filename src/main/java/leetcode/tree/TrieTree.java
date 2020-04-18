package leetcode.tree;


/*
    trie树的本质，就是利用字符串之间的公共前缀，将重复的前缀合并在一起
 */
public class TrieTree {
    private TrieNode root;

    public TrieTree(TrieNode root) {
        this.root = root;
    }

    public void insert(String word) {
        TrieNode node = root;
        //在26个孩子代表26个字母中，找有没有已经创建好的
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (!node.containsKey(curLetter)) {
                node.put(curLetter, new TrieNode()); //如果没有则创建相应的节点
            }
            node = node.get(curLetter);
        }
        node.setEnd(); //到当前节点已经结束
        node.word = word;
    }

    //从根遍历看看这个单词是否存在
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return false;
            }
        }
        return true;

    }

}
