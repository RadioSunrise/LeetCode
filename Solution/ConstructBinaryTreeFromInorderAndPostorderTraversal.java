https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 用中序和后序遍历结果生成一棵树
 
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        return build(inorder, postorder, 0, len - 1, 0, len - 1);
    }
    public TreeNode build(int[] inorder, int[] postorder, int inS, int inE, int postS, int postE){
        if(inS > inE){
            return null;
        }
        int rootVal = postorder[postE];
        TreeNode node = new TreeNode(rootVal);
        int inLeftEnd = inS;
        int postLeftEnd = postS;
        while(inorder[inLeftEnd] != rootVal){
            inLeftEnd++;
            postLeftEnd++;
        }
        node.left = build(inorder, postorder, inS, inLeftEnd - 1, postS, postLeftEnd - 1);
        node.right = build(inorder, postorder, inLeftEnd + 1, inE, postLeftEnd, postE - 1);
        return node;
    }
}
