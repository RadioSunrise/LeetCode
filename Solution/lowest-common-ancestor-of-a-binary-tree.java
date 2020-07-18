// site: https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 最近公共祖先
 
 // dfs后序遍历，因为当一个node做判断的时候，需要知道左右子树的情况，所以使后序遍历的做法
 // 递归的思想就是：递归左右子树，因为是递归，使用函数后可认为左右子树已经算出结果，用 left 和 right 表示
 // 用递归的做法，递归函数的意思可以看做是给p和q找祖先，对于每个node，有几种情况
 // 递归求解左和右，结果记为left和right
 // 1.如果p和q分属node的两边，说明node是p和q的最近公共祖先
 // 2.如果left ==  null且 right != null， 说明p和q都在node的右边，返回right
 // 3.如果right ==  null且 left != null， 说明p和q都在node的左边，返回left
 // 4.如果node就是p或者q，那么返回p或q
 // 5.如果node == null，自然是返回null了
 
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }
    // 后序遍历dfs
    public TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        // 特殊条件
        // node 为空，p，q则返回node
        if (node == null || node == p || node == q) {
            return node;
        }
        // 向左和右递归
        TreeNode left = dfs(node.left, p, q);
        TreeNode right = dfs(node.right, p, q);
        // 1.两边都为null，p和q不在左右子树
        if(left == null && right ==null) {
            return null;
        }
        // 2.左为null，右不为null，说明都在右子树
        else if(left == null && right != null){
            return right;
        }
        // 3.左不为null，右为null，说明都在左子树
        else if(left != null && right == null){
            return left;
        }
        // 4.均不为空，说明p和q分属左右两棵子树
        else {
            return node;
        }
    }
}
 
 
 // 储存父结点的思想
 // 先dfs遍历，用HashMap保存每个节点的父结点(parent)，通过循环可以从底向上地找到p和q的全部祖先
 // 先遍历p的祖先，把访问过的祖先结点用HashSet保存，再遍历q的祖先，如果HashSet已经出现过了，则可以判断这个点就是最近公共祖先
class Solution {
    // 记录父结点的map
    private HashMap<Integer, TreeNode> parentMap = new HashMap<>();
    // 记录访问过的set
    private HashSet<Integer> visitedSet = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        // 根据map，将p的父结点一直推到root
        while(p != null) {
            // 找祖先的过程中记录已访问的结点，包括自己
            visitedSet.add(p.val);
            // p = p.parent, get不到则返回null
            p = parentMap.get(p.val);
        }
        // 遍历q的祖先，通过visitedSet判断是否出现过，出现过则为最近公共祖先
        while(q != null) {
            // System.out.println("q.val is " + q.val);
            // 集合里面出现过表示当前q就是最近公共祖先
            if (visitedSet.contains(q.val)){
                return q;
            }
            q = parentMap.get(q.val);
        }
        return root; // 最差的也是root
    }
    /**
    * dfs遍历，用map保存每个结点的父结点
    */
    public void dfs(TreeNode node) {
        // node左右孩子的父结点是自己
        // 保存val值和父结点node
        if (node.left != null) {
            parentMap.put(node.left.val, node);
            dfs(node.left);
        }
        if (node.right != null) {
            parentMap.put(node.right.val, node);
            dfs(node.right);
        }
    }
}
