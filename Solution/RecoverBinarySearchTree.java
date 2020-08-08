// site: https://leetcode-cn.com/problems/recover-binary-search-tree/

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
 
 // 恢复一棵BST，有两个点被交换了
 
 // Morris 中序遍历
class Solution {
    public void recoverTree(TreeNode root) {
        // 非常数空间复杂度的做法
        TreeNode node1 = null; 
        TreeNode node2 = null;
        TreeNode prev = null;
        TreeNode predecessor = null;
        if(root == null){
            return;
        }
        TreeNode node = root;
        // Morris 中序遍历
        while(node != null){
            // 1.如果左子树不为空, predecessor 为左子树的最右边的节点
            if(node.left != null){
                // predecessor 先向左走一步，然后一直向右走
                predecessor = node.left;
                // 除了判断右边非空之外，还要判断右边是不是node
                // 因为设置完线索之后predecessor的right会等于node会出现环的
                while(predecessor.right != null && predecessor.right != node){
                    predecessor = predecessor.right;
                }
                // 判断predecessor的右子树情况
                // 2.1 为空，右指针指向node，继续遍历左子树
                if(predecessor.right == null){
                    predecessor.right = node;
                    node = node.left;
                }
                // 2.2 不为空说明右指针指向node，且node的左子树已经遍历完了，需要断开连接然后访问右边
                else {
                    // 和正常的中序遍历一样，判断逆序对
                    if(prev != null && prev.val > node.val){
                        node2 = node;
                        if(node1 == null){
                            node1 = prev;
                        }
                    }
                    prev = node;
                    // 断开连接，访问右边
                    predecessor.right = null;
                    node = node.right;
                }
            }
            // 如果左子树为空，访问右子树
            else {
                // 和正常的中序遍历一样，判断逆序对
                if(prev != null && prev.val > node.val){
                    node2 = node;
                    if(node1 == null){
                        node1 = prev;
                    }
                }
                prev = node;
                node = node.right;
            }
        }
        // 交换node1和node2的值
        
        if(node1 != null && node2 != null){
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
        
    }
}
 
 
 // 第一版基于中序遍历的做法，进行一次中序遍历，用一个prev记录前一个结点，发现逆序对就能找到要交换的两个点node1和node2
 // 逆序对的出现次数可能是1也可能是2，无论出现1次还是2次，node1都是第一个逆序对（n1, n2）中的n1，而node2则是最后一次出现的逆序对的中的n2
 // 所以n2在出现逆序对的时候都更新，就ok了
 // 一个细节，在更新node1和node2的时候，可以先更新node2，再更新node1，第二次找到逆序对的时候node1就不等于null了，直接break
 // 这样子就不用增加一个count来记录了
class Solution {
    public void recoverTree(TreeNode root) {
        // 非常数空间复杂度的做法
        TreeNode node1 = null; 
        TreeNode node2 = null;
        TreeNode prev = null;
        if(root == null){
            return;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // 第二次遇到，判断是否有错
            if(prev != null && prev.val > node.val){
                // node2每次都更新，最多两次，到两次就退出
                node2 = node;
                // System.out.println("Found node1 and node2 is " + node2.val);
                // 找到node1，只在node1为null的时候赋值一次
                if(node1 == null){
                    node1 = prev;
                    // System.out.println("Found node1 and node1 is " + node1.val);
                }
                // 发现逆序对而node1不为空，说明是第二个逆序对，全部找到了可以提前退出遍历
                else {
                    break;
                }
            }
            prev = node;
            // 转向右边
            node = node.right;
        }
        // 交换node1和node2的值
        if(node1 != null && node2 != null){
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
    }
}
