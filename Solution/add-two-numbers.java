// site: https://leetcode-cn.com/problems/add-two-numbers
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 增加dummy head
        ListNode head = new ListNode(0); // 不存放内容的头结点
        ListNode temp = head;
        ListNode n1 = l1;
        ListNode n2 = l2;
        int flow = 0;
        int a, b, sum;
        while(n1 != null || n2 != null) { // 用或代替且，内部增加if判断，用且在结束之后要判断哪个没有结束，再剩余的连到尾端
            a = (n1 == null)? 0 : n1.val; // n1 == null， 将x置0，就不需要链表的连接操作了，当成另一数和0相加
            b = (n2 == null)? 0 : n2.val;
            sum = a + b + flow;
            flow = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            if(n1 != null) {n1 = n1.next;}
            if(n2 != null) {n2 = n2.next;}
        }
        // System.out.println(temp.val);
        // 两条链都遍历完了，这个时候temp指的是两个链的最后的一个点的位置，不是指向null
        if(flow == 1)
        {
            temp.next = new ListNode(1);
        }
        return head.next; // head的下一个节点
    }
}
// 使用dummy_head，就是不存放任何数据的头结点，方便找下一个值，代码更简洁减少断链问题
