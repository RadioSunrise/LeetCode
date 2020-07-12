// site: https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 根据前序和中序遍历结果重建一棵树
 
 // 不传数组，传下标就可以了，一直用同两个数组就好
 // 用HashMap把中序遍历里面的元素对应的下标存起来，查询的时候就不用再遍历了
 // 注意左右子树的下标计算
 class Solution {
    HashMap<Integer, Integer> inorder_index_map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if(n == 0){
            return null;
        }
        inorder_index_map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            inorder_index_map.put(inorder[i], i);
        }
        TreeNode root = buildTreeIndex(preorder, inorder, 0, n-1, 0, n-1);
        return root;
    }
    public TreeNode buildTreeIndex(int[] pre, int[] in, int pre_s, int pre_e, int in_s, int in_e){
        if(pre_s > pre_e){ // 退出条件，当右边比左边大的时候退出
            return null;
        }
        int root_val = pre[pre_s];
        TreeNode root = new TreeNode(root_val);
        int inorder_root = inorder_index_map.get(root_val);
        int count = inorder_root - in_s; // count是左子树的长度
        // 左子树 [pre_s + 1 -> pres + count]
        root.left = buildTreeIndex(pre, in, pre_s + 1, pre_s + count, in_s, inorder_root - 1);
        root.right = buildTreeIndex(pre, in, pre_s + count + 1, pre_e, inorder_root + 1, in_e);
        return root;
    }
}
 
 // 辣鸡做法，要构造数组比较麻烦
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int count = 0;
        for(count = 0; inorder[count] != preorder[0]; count++);
        int[] left_pre = new int[count];
        int[] left_in = new int[count];
        int i = 0;
        for(i = 0; i < count; i++){
            left_pre[i] = preorder[i+1];
            left_in[i] = inorder[i];
        }
        int[] right_pre = new int[preorder.length - i - 1];
        int[] right_in = new int[preorder.length - i - 1];
        for(int j = 0; j < right_pre.length; j++){
            right_pre[j] = preorder[i + j + 1];
            right_in[j] = inorder[i + j + 1]; 
        }
        root.left = buildTree(left_pre, left_in);
        root.right = buildTree(right_pre, right_in);
        return root;
    }
}
