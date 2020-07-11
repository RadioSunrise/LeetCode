//site: https://leetcode-cn.com/problems/reverse-linked-list/

// 迭代版
/* 
迭代方法需要三个变量：一个是当前的遍历节点(head)，另一个是当前节点的next节点(node_next)，
第三个是返回的新head(new_node)。
因为当前节点的next指针需要改变，改变后就找不到原来的next了，
所以需要多一个变量保存，new_node也是从最后的的null开始“往后”移动，当head为null，new_node则为原链中的最后一个非null节点
*/
class Solution {
    public ListNode reverseList(ListNode head)
    {
        ListNode new_node = null;
        while(head != null)
        {
            ListNode next_node = head.next;
            head.next = new_node;
            new_node = head;
            head = next_node;
        }
        return new_node;
    }
}

//递归版
/*
递归版的想法是把原来的链表看成第一个节点head + 第二个节点head.next一直到末尾的节点，
第二部分翻转好了，把第二个节点(head.next)的next赋值为head就可以了
new_node则是翻转后新链表的头结点，一直都是同一个值
*/
class Solution {
    public ListNode reverseList(ListNode head)
    {
       if(head == null || head.next == null)
        {
            return head;
        }
        ListNode new_node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return new_node;
    }
}
