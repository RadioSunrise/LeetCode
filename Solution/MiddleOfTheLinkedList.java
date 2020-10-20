// site: https://leetcode-cn.com/problems/middle-of-the-linked-list/

// 快慢指针找链表的中点

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 // 从dummy开始，要判断fast最后是否到null来返回slow或者slow的下一个值
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast == null){
            return slow;
        }
        else {
            return slow.next;
        }
    }
}

// 从head开始
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
