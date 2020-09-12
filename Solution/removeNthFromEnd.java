// https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/

// 删除链表中的倒数第n个节点
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 
// 自己写的第一版没有用dummy head，用了快慢指针和prev指针
// 要单独考虑删除的是头结点的情况
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = null;
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        // fast == null, slow = target, prev = target.slow
        // slow == head 说明要删除的是第一个节点，就是删除head
        if(slow == head){
            return head.next;
        }
        else {
            prev.next = slow.next;
        }
        return head;
    }
}

// 使用dummy head，就不用记录prev了，快慢指针都从dummy出发
// 所以快指针先走n+1步
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for(int i = 0; i < n + 1; i++){
            fast = fast.next;
        }
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        // while 终止，fast = null，而slow指向被删除的点的前一个点，即prev
        // 修改这个指针
        // 所以就算要删除第一个节点也不会报错，应slow是从dummy出发的，不会有空节点的问题
        slow.next = slow.next.next;
        return dummy.next;
    }
}
