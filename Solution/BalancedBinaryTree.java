 // site: https://leetcode-cn.com/problems/balanced-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
// 判断一棵树是不是平衡的（不需要判断是不是搜索树）

// 比较好的方法
// 自底向上
// 把第二种方法中求解高度的函数加以修改
// 因为一棵树的高度最少为0(node == null)的时候，所以当一棵树不平衡的时候，设置高度为-1，所以求解树高的时候
// 判断左右子树的高度，-1则表示不平衡，因此本节点的高度直接返回-1
// 因为每个点都自遍历一次，所以时间复杂度是O(n)
class Solution {
    public boolean isBalanced(TreeNode root) {
        return (height(root) != -1);
    }
    public int height(TreeNode node){
        if(node == null){
            return 0;
        }
        int leftHeight = height(node.left);
        if(leftHeight == -1){
            return -1;
        }
        int rightHeight = height(node.right);
        if(rightHeight == -1){
            return -1;
        }
        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

// 暴力递归判断
// 自顶向下
// 需要用到两个递归函数
// 两个递归，一个递归求解深度一个递归判断是不是平衡
// 会有很多重复的计算，因为每次嗲用isBalanced都会递归求解树，浪费很多时间
// 时间复杂度是O(N*logN)，每个节点isBalanced一遍O(n)，每个节点求高度O(logN)
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean lrHeight = Math.abs(depth(root.left) - depth(root.right)) <= 1;
        if(lrHeight && isBalanced(root.left) && isBalanced(root.right)){
            return true;
        }
        return false;
    }
    public int depth(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = depth(node.left);
        int right = depth(node.right);
        return Math.max(left, right) + 1;
    }
}
// 暴力法稍微改进一下
// 用个HashMap把树高存起来
// 其实也不会快很多
class Solution {
    HashMap<TreeNode, Integer> map = new HashMap<>();
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean lrHeight = Math.abs(depth(root.left) - depth(root.right)) <= 1;
        if(lrHeight && isBalanced(root.left) && isBalanced(root.right)){
            return true;
        }
        return false;
    }
    public int depth(TreeNode node){
        if(node == null){
            return 0;
        }
        if(map.containsKey(node)){
            return map.get(node);
        }
        int left = depth(node.left);
        int right = depth(node.right);
        int height =  Math.max(left, right) + 1;
        map.put(node, height);
        return height;
    }
}
