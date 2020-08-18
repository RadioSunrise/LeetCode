// site: https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
 
 // 有序链表转为AVL
 // 递归建树，用快慢指针找到中间结点，然后左右递归建树
 // 关键：左闭右开区间，初始调用递归函数的时候right = null
 // 所以结束条件是当left == null的时候，就返回null
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return build(head, null);
    }
    // right是取不到的，左闭右开
    TreeNode build(ListNode left, ListNode right){
        // 结束条件
        if(left == right){
            return null;
        }
        // 快慢指针
        ListNode slow = left;
        ListNode fast = left;
        while(fast != right && fast.next != right)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast指针到末尾了, slow指针则为中间中位数
        TreeNode node = new TreeNode(slow.val);
        node.left = build(left, slow);
        node.right = build(slow.next, right);
        return node;
    }
}

