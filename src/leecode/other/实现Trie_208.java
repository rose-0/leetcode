package leecode.other;

//字典树 前缀树的实现
//https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/trie-tree-de-shi-xian-gua-he-chu-xue-zhe-by-huwt/
//字典树的应用： 一次建树，多次查询。它是字典树，不要老是构建，主要用途是查
public class 实现Trie_208 {
    class Trie {
        class TrieNode {

            private boolean isEnd;
            TrieNode[] next;

            public TrieNode() {
                isEnd = false;
                next = new TrieNode[26];
            }
        }

        private TrieNode root;

        public Trie(){
            root=new TrieNode();
        }

        public void insert(String word){
            TrieNode node = root;
            for(char c:word.toCharArray()){
                if(node.next[c-'a']==null){
                    node.next[c-'a']=new TrieNode();
                }
                node=node.next[c-'a'];
            }
            node.isEnd=true;
        }

        public boolean search(String word){
            TrieNode node=root;
            for(char c:word.toCharArray()){
                node=node.next[c-'a'];
                if(node==null){
                    return false;
                }
            }
            return node.isEnd;
        }

        //和 search 就差一个return
        public boolean startsWith(String prefix) {
            TrieNode node=root;
            for(char c: prefix.toCharArray()){
                node=node.next[c-'a'];
                if(node==null){
                    return false;
                }
            }
            return true;
        }
    }
}
