// site: https://leetcode-cn.com/problems/convert-bst-to-greater-tree/submissions/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 要把BST变成GreaterTree，每个节点的val加上比他大的元素的值，就是要加上右边全部的值
 // 因为BST是有序的，所以采取右-根-左的遍历顺序，累加val值并更新这个节点的新值
 // 解法1：维护一个rightSum变量，保存到当前的右累加和
class Solution {
    int rightSum = 0;
    public TreeNode convertBST(TreeNode root) {
        greater(root);
        return root;
    }
    public void greater(TreeNode node){
        if (node == null) {
            return;
        }
        greater(node.right);
        
        /*
        // 暂存当前节点的旧值
        int valTemp = node.val; 
        node.val += rightSum;
        // 更新rightSum
        rightSum += valTemp;
        */
        
        // 另一种更好的写法
        rightSum += node.val;
        node.val = rightSum;
        
        greater(node.left);
    }
}

// 解法2. 用栈来做，类似反向中序的递归顺序，先一路走到最右边（同时压栈），在更新自己和左节点
class Solution {
    int rightSum = 0;
    Stack<TreeNode> stack = new Stack<>();
    public TreeNode convertBST(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){
            // 一路向右走
            while (node != null){
                stack.push(node);
                node = node.right;
            }

            node = stack.pop();

            // 加完了，更新本节点
            rightSum += node.val;
            node.val = rightSum;

            // 转向左边
            node = node.left;
        }
        return root;
    }
}

// 待添加空间O(1)的做法
