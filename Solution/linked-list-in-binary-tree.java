// site: https://leetcode-cn.com/problems/linked-list-in-binary-tree/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    public boolean isSubPath(ListNode head, TreeNode root) {
         if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return check(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right); // 注意调用的函数，前面是check函数，后面两个则是isSubPath函数，左右子树也要重头检测
    }
    public boolean check(ListNode head, TreeNode root){
        if(head == null){ // 链表走完了，true
            return true;
        }
        if(root == null){ // 链表没走完，树走完了，false
             return false;
        }
        if(head.val != root.val){
            return false;
        }
        return check(head.next, root.left) || check(head.next, root.right);
    }
}
