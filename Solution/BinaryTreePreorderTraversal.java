// site: https://leetcode-cn.com/problems/binary-tree-preorder-traversal/

// 奇前序遍历 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 
 // 旧版非递归实现
 class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                res.add(node.val);
                stack.add(node);
                node = node.left;
            }
            node = stack.pop();
            // 转向右子树
            node = node.right;
        }
        return res;
    }
}

// 新版非递归实现
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node;
        while(!stack.isEmpty()){
            node = stack.pop();
            if(node != null){
                // 前序遍历->中左右，倒序入栈
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
                // 处理自己
                stack.push(node);
                stack.push(null);
            }
            // 遇到标志位null
            else {
                node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }
}
