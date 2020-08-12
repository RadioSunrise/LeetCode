// site: https://leetcode-cn.com/problems/symmetric-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 判断一棵二叉树是否镜像对称
 
 // 递归版本
 // 递归的思路，一棵树需要镜像对称，需要对比树的“两边”，而根节点只有一个，所以对比根节点的左子树和右子树是否对称即可
 // 因此设计一个递归函数，判断两个树是否对称
 // 终止条件：1.null的判断，均为null返回true，只有一个为null返回false
 //           2.根节点值不相等，为false
 // 根据镜像对称的性质，有：左子树的左子树 和 右子树的右子树 对称，且 左子树的右子树 和 右子树的左子树 对称
 // 根据这个关系递归判断
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymTwoTree(root.left, root.right);
        
    }
    /**
    * 递归判断两棵树是不是镜像对称的
    * 需要tree1.left和tree2.right对称，且tree1.right和tree2.left对称才会返回true
    * @param tree1左子树
    * @param tree1右子树
    */
    public boolean isSymTwoTree(TreeNode tree1, TreeNode tree2){
        // 递归结束条件1，空判断
        // 一棵为空另一棵不为空则false，均为空则ture
        if(tree1 == null){
            return (tree2 == null);
        }
        if(tree2 == null){
            return (tree1 == null);
        }
        // 结束条件2，根节点的val不同
        if(tree1.val != tree2.val){
            return false;
        }
        // 递归
        if(!isSymTwoTree(tree1.left, tree2.right)){
            return false;
        }
        return isSymTwoTree(tree1.right, tree2.left);
    }
}

// 迭代做法
// 可以用队列Queue做，而不是是用stack模拟上面的迭代
// 按照上面提到的对称的性质，将 left.left和right.right， left.right和right.left 入队，每次出队两个，比较两个的val是否相同
// 不同就返回false，一个细节：开始的时候root入队两次，或者把root.left和root.right入队
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        // 先将root.left和root.right入队
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while(!queue.isEmpty()){
            TreeNode t1 = queue.removeFirst();
            TreeNode t2 = queue.removeFirst();
            if(t1 == null && t2 == null){
                continue;
            }
            if((t1 == null || t2 == null) || (t1.val != t2.val)){
                return false;
            }
            // 按照规则顺序入队
            queue.add(t1.left);
            queue.add(t2.right);

            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
}
