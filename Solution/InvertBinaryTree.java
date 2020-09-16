// site: https://leetcode-cn.com/problems/invert-binary-tree/solution/

// 翻转一棵二叉树

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 第一版递归
 // 先翻转再交换
 // 先交换再翻转也可以的
class Solution {
    public TreeNode invertTree(TreeNode root) {
        return Reverse(root);
    }
    public TreeNode Reverse(TreeNode node){
        if(node == null){
            return null;
        }
        TreeNode temp = node.left;
        node.left = Reverse(node.right);
        node.right = Reverse(temp);
        return node;
    }
}

// 第二版迭代
// 用BFS做
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // bfs逐层交较左右孩子节点
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }
}
