// site: https://leetcode-cn.com/problems/validate-binary-search-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 判断一棵树是不是BST
// 中序遍历判断是否有序，第二版，用一个prev记录前驱
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        // 中序遍历看看是否有序
        // 用一个Double就可以了
        double prev = - Double.MAX_VALUE;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        // 非递归中序遍历
        while(node != null || !stack.isEmpty()){
            // 一路向左走
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            // 开始遍历
            node = stack.pop();
            // 发现不是严格升序排列
            if(prev >= node.val){
                return false;
            }
            prev = node.val;
            // 转向右边
            node = node.right;
        }
        return true;
    }
}

// 第一版 自己写的，中序遍历是否出现非升序的
// 用了两个变量curr和prev，就比较慢了
// 用一个prev就可以了
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        // 中序遍历看看是否有序
        int prev = 0;
        int curr = 0;
        boolean first = true;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        // 非递归中序遍历
        while(node != null || !stack.isEmpty()){
            // 一路向左走
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            // 开始遍历
            node = stack.pop();
            curr = node.val;
            // 发现不是严格升序排列
            // 第一次先不比较
            if(!first && prev >= curr){
                return false;
            }
            first = false;
            prev = curr;
            // 转向右边
            node = node.right;
        }
        return true;
    }
}

// 递归做法
// 递归的时候只跟左右孩子比较时不行的，因为BST要求node要比node.left的所有结点都大，比node.right所有结点都小
// 所以在递归的时候可以增加参数，把上界和下界传进去改成dfs(node, lower, upper)
// 递归左子树的时候，upper = node.val，右子树则是lower = node.val
// main调用则是dfs(node, -inf, +inf)，无穷可以用 TreeNode null代替，函数里面判断空就可以了
class Solution {
    public boolean isValidBST(TreeNode root) {
        // 递归
        return dfsChcek(root, null, null);
    }
    public boolean dfsChcek(TreeNode node, TreeNode lower, TreeNode upper){
        // lower 或者 upper 等于 null 分别表示-inf 和 +inf
        // 终止条件
        if(node == null){
            return true;
        }
        int val = node.val;
        // 先判断根节点node的值在不在范围内
        if(lower != null && val <= lower.val){
            return false;
        }
        if(upper != null && val >= upper.val){
            return false;
        }
        // 递归左和右
        // 递归左边上界upper就是node
        if(!dfsChcek(node.left, lower, node)){
            return false;
        }
        // 递归右边下界lower就是node
        return dfsChcek(node.right, node, upper);
    }
}
