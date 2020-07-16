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
 // 两个dfs，一个是当做root开始dfs，另一个是当做中间结点dfs
 // 注意两个dfs的调用结构
 // dfsRoot只调用dfsRoot（sum不减）, dfsMid只调用dfsMid（sum要减）
class Solution {
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        dfsRoot(root, sum);
        return count;
    }
    

    /**
    * 以当前结点为dfs起点，从sum开始统计
    */
    public void dfsRoot(TreeNode root, int sum){
        if(root == null){
            return;
        }
        
        /*
        // 这个点val等于路径和，直接+1返回
        if(root.val == sum){
            count++;
            return;
        }
        */
        // 本结点dfsMid
        dfsMid(root, sum);

        // 对左右子树都进行dfsRoor
        dfsRoot(root.left, sum);
        dfsRoot(root.right, sum);

    }

    /**
    * 以当前结点为路径中间点, 从sumRemaind继续做
    */
    public void dfsMid(TreeNode node, int sumRemaind){
        if(node == null){
            return;
        }
        sumRemaind -= node.val;
        // 加上这个点就满足了，+1返回
        if(sumRemaind == 0){
            count++;
            // System.out.println("mid find, node is " + node.val);
            // return;
        }
        // 对左右子树继续remaind
        dfsMid(node.left, sumRemaind);
        dfsMid(node.right, sumRemaind);
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
