// site: https://leetcode-cn.com/problems/unique-binary-search-trees-ii/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 
 // 不同的BST二，这次要求把树建立出来
 // 关键：递归函数的设计，设计成返回一个List<TreeNode>比较好，如果设置返回类型为TreeNode，在枚举的过程中会产生多个root，返回不了这么多
 // 因此返回类型为List。枚举root的过程中，根据左右子树的tree集合两边各选一棵，然后组成一棵新树
 
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList<TreeNode>();
        }
        /*
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = i + 1;
        }
        */
        return buildTree(1, n);
    }
    /**
    * 递归建树
    */
    public List<TreeNode> buildTree(int left, int right){
        List<TreeNode> trees = new ArrayList<>();
        // 递归结束条件
        if(right < left){
            trees.add(null);
            return trees;
        }
        if(right == left){
            trees.add(new TreeNode(left));
            return trees;
        }
        // 枚举root，先得到左右区间的所有子树之后再从两边选取
        for(int i = left; i <= right; i++){
            List<TreeNode> leftTrees = buildTree(left, i - 1);
            List<TreeNode> rightTrees = buildTree(i + 1, right);
            for(TreeNode leftNode : leftTrees) {
                for(TreeNode rightNode : rightTrees) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    trees.add(node);
                }
            }
        }
        return trees;
    }
}
