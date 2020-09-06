// site: https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/submissions/

// 判断两个链表是否相交，通过走相同步数来判断是否相遇（相同步数 = linkA + linkB）
// 注意写的时候，循环结束条件和移动的写法都是关键，不要next完再判断，而是通过判断是否null来进行移动

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while(nodeA != nodeB){
            // 走完了本链表，从另一链表的链表头开始走
            // 否则继续前进
            if(nodeA == null){
                nodeA = headB;
            }
            else {
                nodeA = nodeA.next;
            }
            if(nodeB == null){
                nodeB = headA;
            }
            else {
                nodeB = nodeB.next;
            }
        }
        return nodeA;
    }
}
