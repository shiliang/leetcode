package leetcode.design;

import leetcode.tree.TrieNode;
import leetcode.tree.TrieTree;

import java.util.ArrayList;
import java.util.List;

//周赛第164场
public class SearchRecommend {
    /*
        设计一个搜索推荐系统
        给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，
推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
     */

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        TrieTree trieTree = new TrieTree(root);
        for (String product : products) {
            trieTree.insert(product);
        }
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            String word = searchWord.substring(0, i);
            if (trieTree.search(word) != null) {

            }
        }

        return  res;
    }

    public void dfs(TrieNode root, String cur, List<String> res) {
        if (!root.isEnd() && res.size() < 3) {
            res.add(cur);
        }
    }
}
