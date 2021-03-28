// site: https://leetcode-cn.com/problems/binary-search-tree-iterator/

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

// 二叉搜素树 BST 的遍历迭代器

class BSTIterator {

    // 栈实现的非递归中序遍历

    TreeNode currentNode;
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        currentNode = root;
        stack = new LinkedList<>();
    }
    
    public int next() {
        // 栈实现的非递归中序遍历
        // 正常的中序遍历是有个大的 while 的，这里实现的是迭代器，调用 next 再输出
        // 先一路往左走并入栈
        while(currentNode != null){
            stack.push(currentNode);
            currentNode = currentNode.left;
        }
        // 走到最左了，开始弹出访问
        currentNode = stack.pop();
        int res = currentNode.val;
        // 转向右子树
        currentNode = currentNode.right;
        return res;
    }
    
    public boolean hasNext() {
        // 还有 next 的条件是栈里面没弹出完，或者 currentNode 还没走完
        return (currentNode != null) || (!stack.isEmpty()); 
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
