/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 最近公共祖先LWA的第二题，这里的树不是BST，所以要根据左右子树返回的结果(left, right)来判断当前结点的返回值
 // 所以使用后序遍历的DFS，特殊返回情况：
 // 1.node == null 即到了叶子结点，没找到返回null
 // 2.node == p 或 node == q，返回当前结点(p/q
 // 3.left 和 right都不等于null，说明p和q分别在两边，则node必定是LWA
 // 4.left == null 且 right != null，说明在右边，返回right
 // 5.left != null 且 right == null，说明在左边，返回left
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null){
            return null;
        }
        return dfs(root, p, q);
    }
    /**
    * 后序遍历DFS，因为需要知道左右子树的结果，所以用后序的DFS
    */

    public TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        // 特殊情况判断
        if (node == null || node == p || node == q) {
            return node;
        }
        TreeNode left = dfs(node.left, p, q);
        TreeNode right = dfs(node.right, p, q);
        // 分属两边，node为最近公共最先
        if(left != null && right != null) {
            return node;
        }
        // p和q都在右边
        else if(left == null && right != null){
            return right;
        }
        // p和q都在左边
        else if(left != null && right == null){
            return left;
        }
        // 两边都不在
        else if(left == null && right != null){
            return null;
        }
        return null;
    }
}
