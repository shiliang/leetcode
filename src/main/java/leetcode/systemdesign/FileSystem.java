package leetcode.systemdesign;

/*
    你需要设计一个能提供下面两个函数的文件系统：

    create(path, value): 创建一个新的路径，并尽可能将值 value 与路径 path 关联，然后返回 True。如果路径已经存在或者路径的父路径不存在，则返回 False。
    get(path): 返回与路径关联的值。如果路径不存在，则返回 -1。

 */

import leetcode.tree.TrieTree;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    public TrieNode root;
    public FileSystem() {
        root = new TrieNode();
    }

    class TrieNode {
        private TrieNode[] links = new TrieNode[26];
        public String word = null;
        public int value = 0;
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

    public TrieNode search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter); //一直往下找
            } else {
                return null;
            }
        }
        return node;

    }

    public void insert(String word, int value) {
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
        node.value = value;
    }

    public boolean create(String path, int value) {
        String[] pathList = path.split("/");
        for (int i = 1; i < pathList.length; i++) {
            TrieNode node = search(pathList[i]);  //查看父路径是不是存在
            if (node == null) {
                return false;
            }
            insert(pathList[i], value);

        }

        return true;
    }

    public int get(String path) {
        String[] pathList = path.split("/");
        TrieNode node = null;
        for (String s : pathList) {
            node = search(s);
            if (node == null) {
                return -1;
            }
        }
        return node.value;
    }
}
