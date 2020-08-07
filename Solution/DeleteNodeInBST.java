// site: https://leetcode-cn.com/problems/delete-node-in-a-bst/

// BST里面删除一个结点，关键在怎么把子树接回原树上
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        TreeNode node = root;
        // 找到了
        if(node.val == key){
            // 先判断要删除的点的左右子树是否为空
            // 都不为空
            if(node.left != null && node.right != null){
                // 用右子树的最小结点当新的根节点
                // 暂时把node存起来
                TreeNode temp = node;
                node = finMin(temp.right);
                // 原来的右子树删掉最小的点，作为新的右子树
                node.right = deleteMin(temp.right);
                // 原来的左子树就是新的左子树
                node.left = temp.left;
            }
            // 至少有一棵子树为空
            else {
                return (node.left == null) ? node.right : node.left;
            }
        }
        // 向右递归
        else if(node.val < key){
            node.right = deleteNode(node.right, key);
        }
        // 向左递归
        else {
            // (node.val > key)
            node.left = deleteNode(node.left, key);
        }      
        return node;
    }
    /**
    * 找一颗BST最小的节点
    */
    public TreeNode finMin(TreeNode node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    /**
    * 删除一BST最小的点，有待改进，递归版可读性差
    */
    public TreeNode deleteMin(TreeNode node){
        if(node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }
}
