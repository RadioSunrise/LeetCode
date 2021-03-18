// site: https://leetcode-cn.com/problems/reverse-linked-list-ii/

// 从 left 到 right 反转链表

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

// 实现2: 一遍扫描，left 
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int index = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = head;
        ListNode prev = dummy;
        for(int i = 0; i < left - 1; i++){
            prev = prev.next;
        }
        // prev 指向的是 node(left - 1)，目标反转区间的前一个位置
        // 一遍扫描的做法是：每次把待反转段的一个节点插入到 prev 的下一个
        // 每次插入改变反转段的头
        // curr 一直指向待反转段的头 node(left)
        ListNode curr = prev.next;
        ListNode next = null;
        for(int i = 0; i < right - left; i++){
            // next 是需要新插入的头节点
            next = curr.next;
            // curr 的 next 指向新头节点原来的next
            curr.next = next.next;
            // next 插入到 prev 和 curr中间
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }
}


// 实现1: 先断开，left 到 right 整段断开，反转完再接回去
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int index = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = head;
        ListNode prev = dummy;
        while(index < left){
            index++;
            prev = node;
            node = node.next;
        }
        // node 指向 left 节点, prev 是 left的前一个, 要找到 right 再断开
        while(index < right){
            index++;
            node = node.next;
        }
        // node 指向 right 节点, 先保存 right 的下一个节点再断开
        ListNode next = node.next;
        node.next = null;
        // 反转链表
        ListNode newTail = prev.next;
        prev.next = ReverseList(prev.next);
        newTail.next = next;
        return dummy.next;
    }
    /**
    * 反转整条链表
    * return 新的头结点
    */
    public ListNode ReverseList(ListNode head){
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        // 反转完之后 prev 是新的头结点
        return prev;
    }
}

