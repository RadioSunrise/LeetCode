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

// 使用Hash表提升查找inorder_root的速度 + 传下标减少内存消耗
class Solution {
    private HashMap<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) 
    {
        int n = preorder.length;
        map = new HashMap<>();
        for(int i = 0; i < n; i++)
        {
            map.put(inorder[i],i);
        }
        TreeNode result = myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
        return result;
    }
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int pre_head, int pre_tail, int in_head, int in_tail)
    {
        if(pre_head > pre_tail)
        {
            return null;
        }
        int pre_root = pre_head;
        TreeNode node = new TreeNode(preorder[pre_root]);
        
        // 找中序的根节点
        int inorder_root = map.get(preorder[pre_root]);
        
        // 左子树的长度
        int left_legth = inorder_root - in_head;

        // 传下标构造左子树
        node.left = myBuildTree(preorder, inorder, pre_root+1, pre_root + left_legth, in_head, inorder_root - 1);

        // 传下标构造右子树
        node.right = myBuildTree(preorder, inorder, pre_root+left_legth+1, pre_tail, inorder_root+1, in_tail);
        
        return node;
    }
}
