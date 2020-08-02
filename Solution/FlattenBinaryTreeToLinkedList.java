// site: https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/

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
 
// 将一棵二叉树原地展开成链，从根节点开始只有右子树

// 第一种做法：类似递归的思想
// 展开成链的步骤分两步，第一步：将左子树插到右子树的位置，第二步：将右子树插到新右子树（原左子树）的最右边的结点
// 之后继续向右迭代，考察下一个节点
// 时间O(n)，空间O(1)
class Solution {
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode node = root;
        // 循环展开
        while(node != null){
            // 1. 将左子树移到右子树上
            // 左子树为空，考虑下一个结点，node移动
            if(node.left == null){
                node = node.right;
            }
            else {
                // 左子树非空，将左子树移到右子树上
                TreeNode rightTree = node.right;
                node.right = node.left;
                // 将node的左子树置空
                node.left = null;
                // 2. 将原来的右子树接到新右子树的最右边的节点上
                // 从node.right向右走，走到最后一个非空结点就是最右边的节点
                TreeNode temp = node;
                while(temp.right != null){
                    temp = temp.right;
                }
                // 将原本的右子树接上去
                temp.right = rightTree;
                // 继续考虑下一个节点，向右移动
                node = node.right;
            }
        }
    }
}

// 第二种做法
// 前序遍历
// 利用前序遍历，先前序遍历按顺序将TreeNode放到一个list里面
// 再根据list的前后顺序来修改指针
class Solution {
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode node = root;
        // 利用非递归写法的前序遍历
        // 前序遍历和展开分开写
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        // 当node非空或者stack非空
        while(node != null || !stack.isEmpty()){
            // 当前结点node作为根，一直向左走，非空就压入栈
            while(node != null){
                stack.push(node);
                list.add(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        // 前序遍历完成，修改节点的指针
        int n = list.size();
        for(int i = 0; i < n - 1; i++){
            TreeNode curr = list.get(i);
            TreeNode next = list.get(i + 1);
            curr.left = null;
            curr.right = next;
        }
    }
}


// 第三种方法：第二种方法的改进，将前序遍历和展开同时进行
// 除了上面的非递归先序遍历之外，还有另一种先序遍历的写法:
/*
public static void PreOrderTraversalIterate2(TreeNode node)
        {
            if(node == null){
                return;
            }
            Stack<TreeNode> s = new Stack<>();
            s.push(node);
            while(!s.isEmpty()){
                node = s.pop();
                System.out.print(node.getVal() + " ");
                // 栈是先进后出，所以先压入右子树
                if(node.right != null){
                    s.push(node.right);
                }
                // 再压入左子树
                if(node.left != null){
                    s.push(node.left);
                }
            }
            System.out.println();
        }
        */
        
// 利用上面的写法，可以同时先序遍历和展开
class Solution {
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        // 利用非递归写法的前序遍历
        // 前序遍历和展开同时进行
        // 只前序遍历会丢失右孩子信息，要等第二次遇到node才会操作右子树
        // 可以记录右子树信息，遍历和展开一起做

        Stack<TreeNode> stack = new Stack<>();
        // 之前访问过的prev和当前访问的curr，用于展开操作
        TreeNode prev = null;
        TreeNode curr = null;
        stack.push(root);
        while(!stack.isEmpty()){
            curr = stack.pop();
            // 当prev非null，设置指针
            // prev为null则表示还没处理完第一个节点（root）
            if(prev != null){
                // 左子树置空
                prev.left = null;
                // 当前节点接到上一个访问节点的右子树
                // 相当于在前序遍历的顺序中，将当前节点置为上一个节点的右指针
                prev.right = curr;
            }
            // 栈是先进后出，所以先压右子树
            if(curr.right != null){
                stack.push(curr.right);
            }
            if(curr.left != null){
                stack.push(curr.left);
            }
            prev = curr;
        }
    }
}
