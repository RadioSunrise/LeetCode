// site: https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/

// 找二叉搜索树中的第k大节点，因为找第k大，所以用左右反过来的中序遍历比较方便
// BST的右左中遍历就是从大到小的序列，根据类变量k来判断可以提前结束

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
    int tempk;
    int res;
    public int kthLargest(TreeNode root, int k) {
        tempk = k;
        InOrder(root);
        return(res);
    }
    public void InOrder(TreeNode root){
        if(root == null){
            return;
        }
        InOrder(root.right); //先右
        tempk--;
        if(tempk == 0){
            res = root.val;
            return;
        }
        InOrder(root.left); //后左
    }
}
