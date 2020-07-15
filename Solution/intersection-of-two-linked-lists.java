// site: https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
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
 // 判断两个链表是否有交集，只给了两个链表的头结点
 // 因为两个链表的长度，头结点离交点的距离都不知道，所以对两个指针可以用遍历两个链表来消除路径长度不同的问题
 // 指针pa和指针pb，从两个链表头一起出发，当pa走完了a，接着让pa从headB继续走；当pb走完了b，接着让pb从headA继续走
 // 这样子做可以抹除长度差
 // 当交点相遇或者都走到另一个链表末尾的时候，可以确保两个两个pointer所走过的长度是一致的
 // 所以当两个pointer相等的时候，就可以退出循环了
 // 两个pointer都等于null，也会返回null
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        // 当pointer走到null，即本身链表的末尾，就从另一个链表的表头继续走
        // 两个链表都走了一遍(有交集会在另一个链表上走完之前碰到)
        // 这样可以保证两个pointer最终走的路长是一样的
        while(pa != pb){
            pa = (pa == null)? headB : pa.next;
            pb = (pb == null)? headA : pb.next; 
        }
        // 如果没有交集，最后两个pointer同时走到null
        // 也会返回null
        return pa;
    }
}
