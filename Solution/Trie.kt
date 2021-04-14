package Leetcode

/**
 * 前缀树 Trie
 */
class Trie {
    /**
     * 前缀树节点 TrieNode
     */
    class TrieNode{
        /** 是否结束 */
        var end = false
        /** 链接数组 next */
        val next = arrayOfNulls<TrieNode>(26)
    }

    /** Initialize your data structure here. */
    private val root: TrieNode = TrieNode()

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var node: TrieNode = this.root
        for (c in word){
            val index = c - 'a'
            if(node.next[index] == null){
                node.next[index] = TrieNode()
            }
            // node 向下移动
            node = node.next[index]!!
        }
        // 结尾
        node.end = true
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        var node = this.root
        for (c in word){
            val index = c - 'a'
            if(node.next[index] == null){
                return false
            } else {
                // node 向下移动
                node = node.next[index]!!
            }
        }
        // 结尾
        return node.end
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        var node = this.root
        for (c in prefix){
            val index = c - 'a'
            if(node.next[index] == null){
                return false
            } else {
                // node 向下移动
                node = node.next[index]!!
            }
        }
        // 结尾
        return true
    }
}
