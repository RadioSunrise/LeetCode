// site: https://leetcode-cn.com/problems/binary-tree-cameras/
// reference: https://leetcode-cn.com/problems/binary-tree-cameras/solution/chao-ji-hao-li-jie-de-da-an-by-levyjeng/
//            https://leetcode-cn.com/problems/binary-tree-cameras/solution/shou-hua-tu-jie-cong-di-gui-you-hua-dao-dong-tai-g/

// 在二叉树上安装监视器，类似于打家劫舍三
// 后序dfs的思想，需要根据孩子节点的返回状态来判断当前节点的状态，相当于二叉树上的dp，从底到顶返回状态值（代替填dp表）
// 每个节点都有三个状态，具体看代码中的注释


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int res = 0;
    public int minCameraCover(TreeNode root) {
        if(root == null){
            return res;
        }
        // dfs
        // 如果最后root是没有被监视的，则加上一个摄像头
        int root_stats = dfs(root);
        if(root_stats == 0){
            res++;
        }
        return res;

    }
    /**
    * dfs函数，每个节点有三种状态：0.未被监视， 1.被监视但没有在此节点安装摄像头， 2.此位置安装摄像头
    * 后续遍历dfs，即根据左右孩子的状态来决定本节点的状态，因为最底层的状态是确定的
    * 类似于树的dp，递归是从顶向下考虑的，而实际执行（得到具体结果）是从底向上的
    * @param node 当前节点
    * return status
    */
    public int dfs(TreeNode node){
        // null 节点则返回1表示被监视，且不安装摄像头
        if(node == null){
            return 1;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        // 左右孩子有一个或以上没被监视，当前节点装摄像头
        if(left == 0 || right == 0){
            res++;
            return 2;
        }
        // 左右孩子都被监视且没有安装摄像头，当前节点没被监视
        else if(left == 1 && right == 1){
            return 0;
        }
        // 有一个孩子装了摄像头，本节点被监视且不需要安装摄像头
        else {
            return 1;
        }
    }
}
