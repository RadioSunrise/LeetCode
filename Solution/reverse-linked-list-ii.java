// site: https://leetcode-cn.com/problems/reverse-linked-list-ii/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 翻转链表2，指定了需要翻转的位置
// 和每k组反转一次一样，记录prev,start,end,next
// 详细看注释

// 不断开直接做的，更符合一遍扫描的要求
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 使用dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = dummy;
        ListNode prevM = null;
        // 先找start，同时要记录start的前驱，反转完要接回去的
        // 先走m步，记录prevM为start的后一个值
        for(int i = 0; i < m && start != null; i++){
            prevM = start;
            start = start.next;
        }
        // 从start开始，反转需要的长度
        ListNode curr = start;
        ListNode next = null;
        ListNode prev = null;
        for(int i = m; i<= n; i++){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // 让反转段的末尾即prevM.next的后继指向next
        prevM.next.next = next;
        // prev指向的是反转段的新head
        prevM.next = prev;
        return dummy.next;
    }
}

// 断开版本的
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 使用dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = dummy;
        ListNode prev = null;
        // 先找start，同时要记录start的前驱，反转完要接回去的
        // 先走m步，记录prev为start的后一个值
        for(int i = 0; i < m && start != null; i++){
            prev = start;
            start = start.next;
        }
        // 从start出发，找到end
        ListNode end = start;
        for(int i = 0; i < n - m && end != null; i++){
            end = end.next;
        }
        // 把end从原链表断开，记录end.next
        ListNode next = end.next;
        end.next = null;
        // 反转[start, end]，接到prev的后面
        prev.next = reverseList(start);
        // 反转段的新末尾是start，接回去
        start.next = next;
        return dummy.next;
    }
    /**
    * 翻转一段条表，该链表的末尾为null
    * @param node 待翻转的链表头
    */
    public ListNode reverseList(ListNode node){
        ListNode prev = null;
        ListNode curr = node;
        ListNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
