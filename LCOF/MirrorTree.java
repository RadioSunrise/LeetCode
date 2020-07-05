// site: https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
// 二叉树的镜像

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 第一种递归法
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        root = swap_left_right(root);
        return root;
    }
    public TreeNode swap_left_right(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode left_tree = root.left; //要有暂存，不能直接root.left = swap_left_right(root.right); root.right = swap_left_right(root.left); 第一步反转完左右就改变了
        TreeNode right_tree = root.right;
        root.left = swap_left_right(right_tree);
        root.right = swap_left_right(left_tree);
        return root;
    }
}

// 第二种用栈来保存(队列也行)
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            // 先存左右节点，顺序没关系
            if(node.left != null){
                stack.add(node.left);
            }
            if(node.right != null){
                stack.add(node.right);
            }
            // 再交换
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }
}
