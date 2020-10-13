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
 
 // 两两交换链表中的相邻节点
 // 用node表示遍历到的节点，交换node后面的两个节点
 // 关键是dummy head的应用，要交换的点在while循环里面定，循环判断可以用一个node判读，避免出错，还能避免奇数个点的情况
 
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode node = dummy;
        while(node.next != null && node.next.next != null){
            ListNode node1 = node.next;
            ListNode node2 = node.next.next;
            node1.next = node2.next;
            node2.next = node1;
            node.next = node2;
            node = node1;
        }
        return dummy.next;
    }
}
