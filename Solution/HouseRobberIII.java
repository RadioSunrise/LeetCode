// site: https://leetcode-cn.com/problems/house-robber-iii/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
// 打家劫舍第三题
// 所有house组成一棵二叉树
// 一开始的思路：只考虑了当前节点和孩子结点，但没有考虑到上层的选择也会随着下层而改变，同时本节点的选择也需要考虑兄弟节点的选择
// 因此需要考虑三层，1个爷爷，2个父亲和4个孩子

// 一：完全无优化的直接暴力dfs
class Solution {
    public int rob(TreeNode root) {
        // 单独去考虑一个结点和子节点（两层）是不够的，因为上层的选择会受到这两层选择的影响
        // 所以应该考虑三层（爷爷、父亲、孩子）
        return dfsRob(root);
    }
    /**
    * DFS 递归找最大值
    */
    public int dfsRob(TreeNode node){
        // 结束条件
        if(node == null){
            return 0;
        }
        // 考虑三层，爷爷，两个父亲，四个孩子
        // 方案1： 爷爷 + 四个孩子的结果 2：两个父亲的结果
        int res1 = node.val;
        int res2 = 0;
        // 左父亲非空，累加左父亲两个孙子的结果
        if(node.left != null){
            res1 += (dfsRob(node.left.left) + dfsRob(node.left.right));
            res2 += dfsRob(node.left);
        }
        // 右父亲非空
        // 左父亲非空，累加左父亲两个孙子的结果
        if(node.right != null){
            res1 += (dfsRob(node.right.left) + dfsRob(node.right.right));
            res2 += dfsRob(node.right);
        }

        // 返回较大的数
        return Math.max(res1, res2);
    }
}

// 二：优化的dfs
// dfs + 记忆化
// 由于求解过程从顶向下，不断地向下用dfsRob求解，会导致重复计算，所以可以用HashMap来保存访问过的节点的数值
// 快了差不多200倍
class Solution {
    // 增加一个memo记录
    HashMap<TreeNode, Integer> memo = new HashMap<>();
    public int rob(TreeNode root) {
        // 单独去考虑一个结点和子节点（两层）是不够的，因为上层的选择会受到这两层选择的影响
        // 所以应该考虑三层（爷爷、父亲、孩子）
        return dfsRob(root);
    }
    /**
    * DFS 递归找最大值
    */
    public int dfsRob(TreeNode node){
        // 结束条件
        if(node == null){
            return 0;
        }
        // memo里面已经有了
        if(memo.containsKey(node)){
            return memo.get(node);
        }
        // 考虑三层，爷爷，两个父亲，四个孩子
        // 方案1： 爷爷 + 四个孩子的结果 2：两个父亲的结果
        int res1 = node.val;
        int res2 = 0;
        // 左父亲非空，累加左父亲两个孙子的结果
        if(node.left != null){
            res1 += (dfsRob(node.left.left) + dfsRob(node.left.right));
            res2 += dfsRob(node.left);
        }
        // 右父亲非空
        // 左父亲非空，累加左父亲两个孙子的结果
        if(node.right != null){
            res1 += (dfsRob(node.right.left) + dfsRob(node.right.right));
            res2 += dfsRob(node.right);
        }

        // 返回较大的数
        // 记录到memo中
        int res = Math.max(res1, res2);
        memo.put(node, res);
        return res;
    }
}

// 动态规划

// dp表简单版，因为每个点的选择其实只跟左右孩子的选择或不选有关，总共四个值，每个孩子各两个
// 所以可以用一个数据结构，通过返回来向把自身的两种选择结果给上级使用
// 每个点两种状态，所以用一个大小2的数组就可以了，省去Hash表的映射
class Solution {
    public int rob(TreeNode root) {
        // 动态规划的写法
        int[] res = dfsRob(root);
        return Math.max(res[0], res[1]);
    }
    public int[] dfsRob(TreeNode node){
        // 每个节点可以选择偷或者不偷
        // 1.如果选择偷，则结果是node.val + 左孩子node.left不偷的最大结果 + 右孩子node.right不偷的最大结果
        // 2.如果选择不偷，则结果是 左孩子node.left选择偷和右孩子node.right选择不偷的结果
        // 递归结束条件
        if(node == null){
            return (new int[2]);
        }
        int[] res = new int[2];
        // 先进行左右孩子的计算
        int[] leftRes = dfsRob(node.left);
        int[] rightRes = dfsRob(node.right);
        // 两种选择：偷和不偷node
        // 1.node偷，则左右孩子必定不偷
        res[0] = node.val + leftRes[1] + rightRes[1];
        // 2.node不偷，左右孩子选择偷或不偷
        res[1] = Math.max(leftRes[0], leftRes[1]) + Math.max(rightRes[0], rightRes[1]);
        return res;
    }
}


// dp表复杂版，分为两个表，一个存放偷，一个存放不偷
// 每个节点可以选择两个
// 1. 选择偷，则左后孩子必不能偷，返回：当前val + 左孩子不偷的最大值 + 右孩子不偷的最大值
// 2. 选择不偷，则左右孩子各自可以选择偷或不偷，最后相加
class Solution {
    // 两个HashMap，分别记录节点偷和不偷的最大值
    HashMap<TreeNode, Integer> steal = new HashMap<>();
    HashMap<TreeNode, Integer> notSteal = new HashMap<>();

    public int rob(TreeNode root) {
        // 动态规划的写法
        dfsRob(root);
        return Math.max(steal.getOrDefault(root, 0), notSteal.getOrDefault(root, 0));
    }
    public void dfsRob(TreeNode node){
        // 每个节点可以选择偷或者不偷
        // 1.如果选择偷，则结果是node.val + 左孩子node.left不偷的最大结果 + 右孩子node.right不偷的最大结果
        // 2.如果选择不偷，则结果是 左孩子node.left选择偷和右孩子node.right选择不偷的结果
        // 递归结束条件
        if(node == null){
            return;
        }
        // 先进行左右孩子的计算
        dfsRob(node.left);
        dfsRob(node.right);
        int leftMax;
        int rightMax;
        // 两种选择：偷和不偷node
        // 1.node偷，则左右孩子必定不偷
        leftMax = notSteal.getOrDefault(node.left, 0);
        rightMax = notSteal.getOrDefault(node.right, 0);
        int res1 = node.val + leftMax + rightMax;
        steal.put(node, res1);
        // 2.node不偷，左右孩子选择偷或不偷
        leftMax = Math.max(steal.getOrDefault(node.left, 0), notSteal.getOrDefault(node.left, 0));
        rightMax = Math.max(steal.getOrDefault(node.right, 0), notSteal.getOrDefault(node.right, 0));
        int res2 = leftMax + rightMax; 
        notSteal.put(node, res2);
    }
}
