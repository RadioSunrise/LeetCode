// site: https://leetcode-cn.com/problems/rotate-list/submissions/

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
 
 // 旋转链表
 // 两种方法，快慢指针 和 闭合成环
 
 // 快慢指针
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        // 快慢指针找到倒数 (k + 1) 个点
        // 先统计出链表的长度，找到有效的 k 值（旋转多圈是没用的）
        if(head == null || head.next == null || k == 0){
            return head;
        }
        int len = 0;
        ListNode node = head;
        while(node != null){
            ++len;
            node = node.next;
        }
        k = k % len;
        if(k == 0){
            return head;
        }
        // 快慢指针
        ListNode fast = head;
        ListNode slow = head;
        // fast 先走 k 步
        for(int i = 0; i < k; i++){
            fast = fast.next;
        }
        // 快慢指针一同前进，fast到最后一个节点的时候
        // slow指向倒数第 k + 1 个节点，即新的尾部，slow.next 是新的头部
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        // 设定新头和断开
        ListNode newHead = slow.next;
        slow.next = null;
        // 新链表
        fast.next = head;
        return newHead;
    }
}

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // 把链表闭合成一个环，在新尾节点处断开
        // 先统计出链表的长度，找到有效的 k 值（旋转多圈是没用的）
        if(head == null || head.next == null || k == 0){
            return head;
        }
        // 初始化为1
        int len = 1;
        ListNode node = head;
        // 最后要接上去的，所以统计的时候 while 循环的条件是 node 走到最后一个点，不是 null
        while(node.next != null){
            ++len;
            node = node.next;
        }
        k = k % len;
        if(k == 0){
            return head;
        }
        // 闭合成环
        node.next = head;
        // 找到新的尾（新的头就是新尾的 next ）
        // 从 head 出发，走 (len - k - 1) 步
        ListNode newTail = head;
        for(int i = 0; i < (len - k - 1); ++i){
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}
