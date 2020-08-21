// site: https://leetcode-cn.com/problems/path-sum-ii/

// 路径总和II

// 找一条从根节点到叶节点的和为指定值的路径

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 第一版dfs递归回溯
class Solution {
    public List<List<Integer>> path-sum-ii(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(root, sum, res, path);
        return res;
    }
    public void dfs(TreeNode node, int sum, List<List<Integer>> res, Deque<Integer> path){
        // 递归结束条件
        if(node == null){
            return;
        }
        // node为叶子结点
        if(node.left == null && node.right == null){
            // 结点值刚好等于sum
            if(node.val == sum){
                path.add(node.val);
                // System.out.println("path add tree " + node.val + ", now size is " + path.size());
                res.add(new ArrayList(path));
                // path中删掉自己
                path.removeLast();
                // System.out.println("path remove tree " + node.val + ", now size is " + path.size());
                return;
            }
            // 否则不添加，退出
            else{
                return;
            }
        }
        // 非叶子结点，回溯递归
        else {
            int temp = sum - node.val;
            path.add(node.val);
            // System.out.println("path add " + node.val + ", now size is " + path.size());
            // 递归
            dfs(node.left, temp, res, path);
            dfs(node.right, temp, res, path);
            // 回溯，path中删除
            // int lastVal = path.getLast();
            path.removeLast();
            // System.out.println("path remove " + lastVal + ", now size is " + path.size());
        }
    }
}
