//site: https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 判断一棵树是否包含另一棵数
 
 // 优化逻辑后的递归版
 // 反而更快了
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 前序遍历，每个点都判断：以当前点为根的树是否包含B
        if(A == null || B == null){
            return false;
        }
        // 自身作为根，递归左子树和右子树
        if(contain(A, B)){
            return true;
        }
        if(isSubStructure(A.left, B)){
            return true;
        }
        return isSubStructure(A.right, B);
    }
    /**
    * 判断一棵树Tree1是否包含另一棵数Tree2
    */
    public boolean contain(TreeNode tree1, TreeNode tree2){
        if(tree2 == null){
            return true;
        }
        if(tree1 == null || tree1.val != tree2.val){
            return false;
        }
        if(!contain(tree1.left, tree2.left)){
            return false;
        }
        return contain(tree1.right, tree2.right);
    }
}
 
 // 自己写的第一版前序 + 判断，用的是迭代版前序
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null){
            if(B == null){
                return true;
            }
            return false;
        }
        if(B == null){
            if(A == null){
                return true;
            }
            return false;
        }
        // 先序遍历A，发现和B的val相同就转入同一棵树的判断
        Stack<TreeNode> stack = new Stack<>();
        stack.push(A);
        TreeNode node;
        while(!stack.isEmpty()){
            node = stack.pop();
            // 相等就进入判断
            if(node.val == B.val){
                if(helper(node, B)){
                    return true;
                }
            }
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return false;
    }
    public boolean helper(TreeNode tree1, TreeNode tree2){
        // 递归函数
        // 结束条件
        if(tree2 == null){
            return true;
        }
        if(tree1 == null){
            return false;
        }
        if(tree1.val != tree2.val){
            return false;
        }
        if(!helper(tree1.left, tree2.left)){
            return false;
        }
        return helper(tree1.right, tree2.right);
    }
}

