// site: https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/

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
 
 // 删除链表里的重复元素
 // 用一遍扫描的方式实现
 
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next = null;
        // while 循环遍历链表
        while(curr != null && curr.next != null){
            // 因为链表排过序，所以重复的数字会相邻
            if(curr.val == curr.next.val){
                while(curr.next != null && curr.val == curr.next.val){
                    curr.next = curr.next.next;
                }
                // 跳出 while 之后 curr 没动
                // 还需要删掉curr
                next = curr.next;
                curr = next;
                prev.next = next;
            } else {
                // 没遇到重复就继续前进
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
