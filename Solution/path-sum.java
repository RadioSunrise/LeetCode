// site: https://leetcode-cn.com/problems/path-sum/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 递归做法，只要不是叶节点就向左右子树递归，sum -> sum - root.val
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        return check(root, sum);
    }
    public boolean check(TreeNode root, int sum){
        if(root == null){
            return false;
        }
        /*
        if(sum < root.val){ //可以是负权值，所以不需要判断够不够减
            System.out.println("now root val small ");
            return false;
        }
        */
        if(sum == root.val && root.left == null && root.right == null){
            return true;
        }
        boolean result;
        result = check(root.left, sum - root.val) || check(root.right, sum - root.val);
        return result;
    }
}

//用队列，两个队列，一个存放node，一个放当前路径的val和，当走到叶子节点且sum = sum(val)的时候就返回true
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        // 两个队列
        Queue<TreeNode> q_node = new LinkedList<>();
        Queue<Integer> q_val = new LinkedList<>();
        q_node.add(root);
        q_val.add(root.val);
        while(!q_node.isEmpty()){
            TreeNode node = q_node.poll();
            int temp = q_val.poll(); // 这个节点的当前路径和
            if(node.left == null && node.right == null){
                if(temp == sum){
                    return true;
                }
                continue;
            }
            if(node.left != null){
                q_node.add(node.left);
                q_val.add(temp + node.left.val); //当前和+孩子的val，进入队列
            }
            if(node.right != null){
                q_node.add(node.right);
                q_val.add(temp + node.right.val);
            }
        }
        return false;
    }
}
