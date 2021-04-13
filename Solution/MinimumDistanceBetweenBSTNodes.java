// site: https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/submissions/

// BST 中序遍历的应用

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 要找最小差值，肯定是在有序序列中相邻的值之间计算
    // 因为是 BST, 中序遍历是有序序列，比较差值计算min即可

    private int minVal = Integer.MAX_VALUE;
    // 因为值都是正整数，所以初始化两个变量 prev 和 curr 为 -1
    int prev = -1;
    int curr = -1;

    public int minDiffInBST(TreeNode root) {
        if(root == null){
            return 0;
        }
        inOrder(root);
        return minVal;
    }

    /**
    * 中序遍历
    */
    private void inOrder(TreeNode root){
        if(root == null){
            return;
        }
        // 转向左子树
        inOrder(root.left);
        // 处理当前节点
        // prev == -1 说明是第一次处理节点，不用计算
        curr = root.val;
        if(prev != -1){
            minVal = Math.min(minVal, Math.abs(prev - curr));
        }
        // prev赋值，
        prev = curr;
        // 转向右子树
        inOrder(root.right);
    }
}
