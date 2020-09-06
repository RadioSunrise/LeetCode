// site: https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 判断一棵树是否平衡，通过计算树高的方式
 // 如果一棵树不平衡，则返回高度为-1，表示不平衡，即通过一个整型数来表示平衡和真实高度
class Solution {
    public boolean isBalanced(TreeNode root) {
        return (height(root) != -1);
    }
    /**
    * 计算树的高度，如果不平衡则返回-1，表示不平衡
    */
    public int height(TreeNode node){
        if(node == null){
            return 0;
        }
        int leftHeight = height(node.left);
        // 如果左子树或者右子树不平衡，返回-1
        if(leftHeight == -1){
            return -1;
        }
        int rightHeight = height(node.right);
        // 本节点为根的树不平衡也返回-1
        if(rightHeight == -1 || (rightHeight - leftHeight < -1 || rightHeight - leftHeight > 1)){
            return -1;
        }
        return Math.max(rightHeight, leftHeight) + 1;
    }
}
