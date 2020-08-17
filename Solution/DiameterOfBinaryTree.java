// site: https://leetcode-cn.com/problems/diameter-of-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 求一棵树的直径，即两个结点之间的最长距离
 // 不能简单得用根节点的左子树高度 + 右子树高度，因为最长路径不一定经过根节点（其中一个子树很大）
 
 // 和求树的高度很像，在求树高的时候比较当前最长半径即可
 class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        length(root);
        return max;
    }
    public int length(TreeNode node){
        if(node == null){
            return 0;
        }
        int leftLen = length(node.left);
        int rightLen = length(node.right);
        // 以当前node为根的树的最长路径是 左子树深度 + 右子树深度
        if(max < leftLen + rightLen){
            max = leftLen + rightLen;
        }
        return Math.max(leftLen, rightLen) + 1;
    }
}
 
 // 第一版复杂的写法
class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        length(root);
        return max;
    }
    public int length(TreeNode node){
        if(node == null){
            return 0;
        }
        // leftPath
        int leftPathLen;
        if(node.left == null){
            leftPathLen = 0;
        }
        else {
            leftPathLen = length(node.left) + 1;
        }
        int rightPathLen;
        if(node.right == null){
            rightPathLen = 0;
        }
        else {
            rightPathLen = length(node.right) + 1;
        }
        // 每个节点都比较一次
        if(leftPathLen + rightPathLen > max){
            max = leftPathLen + rightPathLen;
        }
        // 返回的是这棵树的最大长度
        return Math.max(leftPathLen, rightPathLen);
    }
}
