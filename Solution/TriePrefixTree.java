// site: https://leetcode-cn.com/problems/implement-trie-prefix-tree/
// reference: https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/trie-tree-de-shi-xian-gua-he-chu-xue-zhe-by-huwt/

// 前缀树的实现

class Trie {

    /**
    * 前缀树节点类
    */
    class TrieNode{
        /** 是否结束 */
        boolean end;
        /** next 链接，每个字母对应一个 */
        TrieNode[] next;

        public TrieNode(){
            end = false;
            next = new TrieNode[26];
        }
    }

    /** 前缀树的根节点 */
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        // 从根节点开始
        TrieNode node = this.root;
        // 逐个字母遍历
        for (char c : word.toCharArray()){
            int index = c - 'a';
            // 如果没有这个字母的链接，则创建一个
            if (node.next[index] == null){
                node.next[index] = new TrieNode();
            }
            // node 向下移动
            node = node.next[index];
        }
        // 末尾记得把 end 设置成 true
        node.end = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = this.root;
        // 逐个字母遍历
        for (char c : word.toCharArray()){
            int index = c - 'a';
            // 当前字母的链接不存在，即没有存储这个字符
            if(node.next[index] == null){
                return false;
            }
            node = node.next[index];
        }
        // 遍历完成，看看是否结束
        return node.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        // 和 search 一样，只是不需要判断是否为结尾
        TrieNode node = this.root;
        // 逐个字母遍历
        for (char c : prefix.toCharArray()){
            int index = c - 'a';
            // 当前字母的链接不存在，即没有存储这个字符
            if(node.next[index] == null){
                return false;
            }
            node = node.next[index];
        }
        // 遍历完成
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
