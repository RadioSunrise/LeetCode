// site https://leetcode-cn.com/problems/path-sum-iii/submissions/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 双重dfs的做法
 // 两个dfs，一个是遍历树本身的mainDFS，另外一个是求路径总和的subDFS
 // 注意两个dfs的调用结构，dfsRoot相当于是遍历树本身
 class Solution {
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        mainInterval(root, sum);
        return count;
    }
    public void mainInterval(TreeNode node, int sum){
        if(node == null){
            return;
        }
        // 处理本节点
        subInterval(node, sum);
        // 遍历到左右节点
        mainInterval(node.left, sum);
        mainInterval(node.right, sum);
    }
    /**
    * sub遍历，和路径总和II一样的递归
    */
    public void subInterval(TreeNode node, int remainSum){
        if(node == null){
            return;
        }
        if(remainSum == node.val){
            count++;
        }
        subInterval(node.left, remainSum - node.val);
        subInterval(node.right, remainSum - node.val);
    }
}

// 更快的做法
// 用一个数组保存从根节点到当前点的路径和
// path_array[p]的意思是以p为末尾的路径中，path_array[0] .. path_array[p-1]存放了路径上前p-1个点的val值
// p实际上相当于深度，当递归函数对左右子树进行调用(p=p+1)传进去的，真正表示的结点会不同(分别是左右孩子)
// 但对左右孩子来说，path_array[0] .. path_array[p-1]是一样的，而后序的path_array[p+1] .. path[n]是不参与计算的
// 因为我们是利用path_array进行反推，从p位置一直往根节点走，遇到路径和=target就+1
// 每次调用递归函数都会把p位置的值覆盖掉，所以左右子树的p相同也没问题，因为前面的路径是一样的
class Solution {
    public int pathSum(TreeNode root, int sum) {
        int[] path_array = new int[1000]; // 最多1000个结点
        // 根节点的深度p为0
        return path_count(root, sum, path_array, 0);
    }
    public int path_count(TreeNode node, int sum, int[] array, int p)
    {
        // 递归终止条件
        if(node == null){
            return 0;
        }
        // 把array数组里本节点位置的值覆盖/修改
        array[p] = node.val;
        // 从当前结点按照从根节点来的路径统计符合条件的路径数量
        int currSum = 0;
        int count = 0;
        for(int i = p; i >= 0; i--){
            currSum += array[i];
            if(currSum == sum){
                count++;
            }
        }
        // 递归左右子树, p + 1(深度+1)
        int countLeft = path_count(node.left, sum, array, p + 1);
        int countRight = path_count(node.right, sum, array, p + 1);
        return count + countLeft + countRight;
    }
}
