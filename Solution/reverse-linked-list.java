// site: https://leetcode-cn.com/problems/reverse-linked-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 递归版
/*
递归的思想把原链分为两部分: head + [head.next -> ... -> null] (part1 + part2)
当part2已经翻转完成的时候，head的next指针依然是指向head.next，那么把head.next的next指针指向head就完成了翻转
还需要一个p节点来返回翻转完的新头结点, p在第一次找到原链的尾部之后就不再变化了
*/
class Solution {
    public ListNode reverseList(ListNode head)
    {
        if(head == null || head.next == null)
        {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}


// 迭代版
/*
迭代版的做法是从头开始遍历，当前节点current的next指向其前驱，所以需要一个变量来保存前驱，
同时因为current的next已经改变了，所以需要多一个next_node来保存current的后继
最后返回的previous则会变成原链表的尾
*/
class Solution {
    public ListNode reverseList(ListNode head)
    {
        ListNode previous = null;
        ListNode curr = head;
        while(curr != null)
        {
            ListNode curr_next = curr.next; // 暂时保存current.next
            curr.next = previous; // next指针指向前驱
            previous = curr; // pervious前进
            curr = curr_next; // curr = curr.next(用之前保存的next来做)
        }
        return previous;
    }
}
