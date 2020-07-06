//site: https://leetcode-cn.com/problems/check-subtree-lcci/

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
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        return check(t1, t2);
    }
    public boolean check(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 != null)
        {
            return false;
        }
        else if(t1 != null && t2 == null){
            return false;
        }
        else if(t1 == null && t2 == null){
            return true;
        }
        boolean res;
        if(t1.val == t2.val){ // 根节点值相同，比较左右子树，都true才返回true
            res = check(t1.left, t2.left) && check(t1.right, t2.right);
        }
        else{ //根节点不同，t2和左右子树比较，有一边true就返回true
            res = check(t1.left, t2) || check(t1.right, t2); 
        }
        return res;
    }
}
