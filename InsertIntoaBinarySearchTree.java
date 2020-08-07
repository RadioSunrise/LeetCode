// site: https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/

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

// 在BST里面插入一个结点
// 可以递归也可以迭代
// 迭代版的
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode node = root;
        while(node != null){
            // node比较大，放左边
            if(node.val > val){
                // 左子树为null，可以插入
                if(node.left == null){
                    node.left = new TreeNode(val);
                    return root;
                }
                // 不为空就向左移动
                node = node.left;
            }
            // 放右边
            else if(node.val < val){
                if(node.right == null){
                    node.right = new TreeNode(val);
                    return root;
                }
                node = node.right;
            }
        }
        return root;
    }
}

// 递归版的
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val > val){
            root.left = insertIntoBST(root.left, val);
        }
        else if(root.val < val){
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
