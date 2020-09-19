// site: https://leetcode-cn.com/problems/sum-of-left-leaves/

// 计算左叶子的总和

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // dfs 做法
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        return dfs(root);
    }
    public int dfs(TreeNode node){
        int res = 0;
        if(node.left != null){
            if(isLeaf(node.left)){
                res += node.left.val;
            }
            else{
                res += dfs(node.left);
            }
        }
        if(node.right != null){
            res += dfs(node.right);
        }
        return res;
    }
    public boolean isLeaf(TreeNode node){
        if(node.left == null && node.right == null){
            return true;
        }
        return false;
    }
}

// bfs
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        TreeNode node;
        while(!queue.isEmpty()){
            node = queue.poll();
            if(node.left != null){
                if(isLeaf(node.left)){
                    res += node.left.val;
                }
                else {
                    queue.add(node.left);
                }
            }
            if(node.right != null && !isLeaf(node.right)){
                queue.add(node.right);
            }
        }
        return res;
    }
    public boolean isLeaf(TreeNode node){
        if(node.left == null && node.right == null){
            return true;
        }
        return false;
    }
}
