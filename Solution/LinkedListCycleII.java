// site: https://leetcode-cn.com/problems/linked-list-cycle-ii/

// 检测单链表中是否有环，有环就返回入环节点

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        // fast 走到结尾了，说明无环
        if(fast == null || fast.next == null){
            return null;
        }
        // 相遇表示有环，fast 回到 head，同时每次前进一步
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
