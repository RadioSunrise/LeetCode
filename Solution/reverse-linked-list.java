// site: https://leetcode-cn.com/problems/reverse-linked-list/

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
