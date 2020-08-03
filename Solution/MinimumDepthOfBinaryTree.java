// site: https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 二叉树的最小深度，递归写法
 // 和最大深度不同，如果一个结点的有一个子节点为null，那么这一边的递归可肯定返回0，选择min的时候就会出错，选了空的一边
 // 也就是把当前节点当成叶子节点了，但实际上本节点不是叶子节点
 // 所以要多加一步判断，判断是否存在空的孩子节点
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        // 判断左右孩子是否空，有一个为空则返回非空的深度
        // 还可以剪枝，如果有一边为空，那一边返回0就可以了，不用再递归多一次
        int leftMinDepth = (root.left == null ? 0 : minDepth(root.left));
        int rightMinDepth = (root.right == null ? 0 : minDepth(root.right));
        if(root.left == null || root.right == null){
            // return ((root.left == null) ? rightMinDepth : leftMinDepth) + 1;
            return rightMinDepth + leftMinDepth + 1;
        }
        return Math.min(leftMinDepth,rightMinDepth) + 1;
    }
}
