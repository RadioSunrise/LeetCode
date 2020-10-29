// site: https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/

// 将根节点到叶子的路径看成一个数，求和

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 自己实现的第一版，用了dfs+回溯，用StringBuilder来统计path，比较慢
class Solution {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        StringBuilder path = new StringBuilder();
        dfs(root, path);
        return sum;
    }
    public void dfs(TreeNode node, StringBuilder path){
        if(node == null){
            return;
        }
        // leaf
        if(node.left == null && node.right == null){
            path.append(node.val);
            // System.out.println("leaf, value is " + Integer.valueOf(path.toString()));
            sum += Integer.valueOf(path.toString());
            path.setLength(path.length() - 1);
            return;
        }
        else{
            path.append(node.val);
            // 左孩子
            dfs(node.left, path);
            // 右孩子
            dfs(node.right, path);
            path.setLength(path.length() - 1);
        }
    }
}

// 用数字往下传就行，每次都乘以10再加上本层节点
// 简单整洁版的dfs
class Solution {
    public int sumNumbers(TreeNode root) {
        StringBuilder path = new StringBuilder();
        return dfs(root, 0);
    }
    public int dfs(TreeNode node, int currentSum){
        if(node == null){
            return 0;
        }
        int sum = currentSum * 10 + node.val;
        if(node.left == null && node.right == null){
            return sum;
        }
        else {
            return dfs(node.left, sum) + dfs(node.right, sum);
        }
    }
}

// 当然bfs也是可以的
