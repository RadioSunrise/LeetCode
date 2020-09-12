// https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // bfs层次遍历做法
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            double sum = 0;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(sum / size);
        }
        return res;
    }
}

// dfs做法
// 也可以用dfs来做，要记录层数和用数组把同一层节点数与同层的和加起来
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> count = new ArrayList<>();
        List<Double> sum = new ArrayList<>();
        dfs(root, 0, count, sum);
        for(int i = 0; i < sum.size(); i++){
            sum.set(i, sum.get(i) / count.get(i));
        }
        return sum;
    }
    public void dfs(TreeNode node, int level, List<Integer> count, List<Double> sum){
        if(node == null){
            return;
        }
        if(level < count.size()){
            count.set(level, count.get(level) + 1);
            sum.set(level, sum.get(level) + node.val);
        }
        else{
            count.add(1);
            sum.add(1.0 * node.val);
        }
        dfs(node.left, level + 1, count, sum);
        dfs(node.right, level + 1, count, sum);
    }
}
