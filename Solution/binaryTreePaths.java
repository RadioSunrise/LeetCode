// site: https://leetcode-cn.com/problems/binary-tree-paths/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
  // dfs，用path来增加或者删除
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(root, path, res);
        return res;
    }
    /**
    * dfs回溯
    */
    public void dfs(TreeNode node, Deque<Integer> path, List<String> res){
        if(node == null){
            return;
        }

        path.addLast(node.val);

        // 叶子节点
        if(node.left == null && node.right == null){
            res.add(pathToString(path));
            return;
        }
        // 非叶子节点
        if(node.left != null){
            dfs(node.left, path, res);
            // 回溯删除
            path.removeLast();
        }
        if(node.right != null){
            dfs(node.right, path, res);
            // 回溯删除
            path.removeLast();
        }
    }
    public String pathToString(Deque<Integer> path){
        StringBuilder str = new StringBuilder();
        String temp = "";
        for(int i : path){
            str.append(temp);
            str.append(i);
            temp = "->";
        }
        return str.toString();
    }
}
 
 // dfs，传递一个stringBuilder，时间反而慢了
 class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        // Deque<Integer> path = new ArrayDeque<>();
        StringBuilder path = new StringBuilder();
        dfs(root, path, res);
        return res;
    }
    /**
    * dfs回溯
    */
    public void dfs(TreeNode node, StringBuilder path, List<String> res){
        if(node == null){
            return;
        }
        // 叶子节点
        if(node.left == null && node.right == null){
            res.add(path.toString() + node.val);
            return;
        }
        // 左右递归
        int currLen = path.length();
        path.append(node.val);
        path.append("->");
        dfs(node.left, path, res);
        dfs(node.right, path, res);
        // 回溯，setLength回原来的长度
        path.setLength(currLen);
    }
 
 
