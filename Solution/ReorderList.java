// site: https://leetcode-cn.com/problems/reorder-list/

// 重排链表，结果是等同于将链表分为两段，后一段和前一段顺序交叉合并
// 可以用栈或者arraylist这类空间O(n)的方法来提供随机访问
// 也可以空间O(1)地原地交换，需要找中点分段，逆序后一段之后再合并

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
class Solution {
    public void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow 即为中点，即后半段的前一个节点
        System.out.println("slow is " + slow.val);
        ListNode l1 = head;
        ListNode l2 = slow.next;
        // 断开
        slow.next = null;
        l2 = reverse(l2);
        // 合并
        ListNode l1Temp;
        ListNode l2Temp;
        while(l1 != null && l2 != null){
            l1Temp = l1.next;
            l2Temp = l2.next;

            l1.next = l2;
            l1 = l1Temp;

            l2.next = l1;
            l2 = l2Temp;
        }
    }
    /**
    * 翻转链表
    */
    public ListNode reverse(ListNode node){
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
