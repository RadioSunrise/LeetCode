/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 统计一棵完全二叉树的节点个数
 
 // site: https://leetcode-cn.com/problems/count-complete-tree-nodes/submissions/
 // 参考: https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/
 
 // 完全二叉树的特性，左子树高度 >= 右子树高度
 // 根据左右子树的高度分情况递归
 
 // 1.如果左子树 == 右子树高度
 // 说明右子树可能存在空缺，而左子树肯定是满的，所以左子树 + 根节点一共 (2^left - 1) + 1 = 2^left 个点，然后到右子树进行递归
 // 2.左子树 != 右子树高度
 // 说明最后一层不满，而倒数第二层满了，空缺在左子树，因此右子树是满的（矮了一层但是是满的），右子树 + 根节点一共 2^right个点，然后到左子树进行递归
 
 
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        // 计算两边的树高
        int leftHigh = countHigh(root.left);
        int rightHigh = countHigh(root.right);
        // 两种情况
        // 1.如果高度相同，个数 = 2^left (左子树 + 根节点) + 右子树
        if(leftHigh == rightHigh){
            return countNodes(root.right) + (1 << leftHigh);
        }
        // 2.如果高度不同同，个数 = 2^right (右子树 + 根节点) + 左子树
        else {
            return countNodes(root.left) + (1 << rightHigh);
        }
    }

    /**
    * 利用完全二叉树的特性计算树的高度
    * 因为左子树一定不矮于右子树，所以一直往左走即可
    */
    public int countHigh(TreeNode root){
        if(root == null){
            return 0;
        }
        int lev = 0;
        while(root != null){
            lev++;
            root = root.left;
        }
        return lev;
    }
}
