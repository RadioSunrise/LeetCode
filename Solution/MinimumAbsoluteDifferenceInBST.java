// site: https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 第一版用递归中序遍历实现的，其实curr也是可以省下的
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int min = Integer.MAX_VALUE;
    int prev = -1;
    int curr = -1;
    public int getMinimumDifference(TreeNode root) {
        TreeNode node = root;
        if(node == null){
            return 0;
        }
        inOrder(root);
        return min;
    }
    public void inOrder(TreeNode node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        // 处理自己
        curr = node.val;
        if(prev != -1){
            // curr 比 prev大，就不需要abs了
            min = Math.min(min, curr - prev);
        }
        prev = curr;
        inOrder(node.right);
    }
}
