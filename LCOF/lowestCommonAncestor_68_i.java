// site: 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 最近公共最先的第一题，特点是这里的树是一棵BST
 
 // 迭代版本
 public TreeNode dfsIteration(TreeNode node, TreeNode p, TreeNode q) {
        // 迭代版本
        while(node != null){
            // node在中间
            if((node.val >= p.val && node.val <= q.val) || (node.val >= q.val && node.val <= p.val)){
                return node;
            }
            // 向左子树迭代
            else if((node.val > p.val) && (node.val > q.val)){
                node = node.left;
            }
            // 向右子树迭代
            else if((node.val < p.val) && (node.val < q.val)){
                node = node.right;
            }
        }
        return node;
    }
 
 
 // 递归版本
 // 不用每次都递归求解node.left和node.right再判断，可以根据val的值判断应该怎么递归或者返回
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null){
            return null;
        }
        return dfs(root, p, q);
    }
    public TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null || node == p || node == q){
            return node;
        }
        // 和68-II不同的是，这里的树的BST，所以可以通过val来判断和递归
        // 1.node在p和q之间，则node必定是LCA
        if((node.val >= p.val && node.val <= q.val) || (node.val >= q.val && node.val <= p.val)){
            return node;
        }
        // 2.node比p和q都大，说明LCA在左子树中
        else if((node.val > p.val) && (node.val > q.val)){
            return dfs(node.left, p, q);
        }
        // 3.node比两个点都小，说明LCA在右子树中
        else if((node.val < p.val) && (node.val < q.val)){
            return dfs(node.right, p, q);
        }
        return node;
    }
}
