/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 递归 + 数组方法，原始但是慢，可以不传整个array，改传下标的方式
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 递归结束条件 -- 1个节点
        if(preorder.length == 0)
        {
            return null;
        }
        TreeNode node = new TreeNode(preorder[0]);
        if(preorder.length == 1 && inorder.length == 1)
        {
            return node;
        }
        else
        {
            // 找到中序中的根节点位置
            int in_order_root = 0;
            while(inorder[in_order_root] != preorder[0])
            {
                in_order_root++;
            }
            // 左子树的新序列
            int[] left_preorder = new int[in_order_root];
            int[] left_inorder = new int[in_order_root];
            for(int i = 1; i <= in_order_root; i++)
            {
                left_preorder[i - 1] = preorder[i];
                left_inorder[i - 1] = inorder[i - 1];
            }
            // 右子树的新序列
            int[] right_preorder = new int[inorder.length - in_order_root - 1];
            int[] right_inorder = new int[inorder.length - in_order_root - 1];
            for(int i = in_order_root + 1, j = 0; i < preorder.length; i++,j++)
            {
                right_preorder[j] = preorder[i];
                right_inorder[j] = inorder[i];
            }
            node.left = buildTree(left_preorder, left_inorder);
            node.right = buildTree(right_preorder, right_inorder);
            return node;
        }
    }
}
