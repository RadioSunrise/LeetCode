// site: https://leetcode-cn.com/problems/partition-list/

// 按照数值x分割链表，使小于x的节点出现在大于等于x的节点的前面

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 // 使用dummy的思想，判断的对象是node.next，判断大小关系之后再进行移动
 // 这一版实现是没有单独的smallList的，所以有一部分是node不移动
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode biggerList = new ListNode(0);
        ListNode node = dummy;
        ListNode bigger = biggerList;
        while(node.next != null){
            // node.next需要移动
            if(node.next.val >= x){
                bigger.next = node.next;
                bigger = bigger.next;
                // 原链中断开
                node.next = node.next.next;
            }
            // 不需要移动，遍历下一个点
            else {
                node = node.next;
            }
        }
        // 设置结尾，两段链接上
        bigger.next = null;
        node.next = biggerList.next;
        if(dummy.next != null){
            return dummy.next;
        }
        else {
            return biggerList.next;
        }
    }
}

// 区分小于和大于等于两部分的实现
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null){
            return null;
        }
        ListNode smallList = new ListNode(0);
        ListNode small = smallList;
        ListNode biggerList = new ListNode(0);
        ListNode bigger = biggerList;
        ListNode node = head;
        while(node != null){
            if(node.val < x){
                small.next = node;
                small = small.next;
            }
            else {
                bigger.next = node;
                bigger = bigger.next;
            }
            // 不需要设置断开操作
            node = node.next;
        }
        bigger.next = null;
        small.next = biggerList.next;
        // 不需要判断smallList.next是否为空，如果链的数值均大于等于x
        // 则small是不会移动的，一直等于smallList
        return smallList.next;
    }
}
