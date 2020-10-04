// site: https://leetcode-cn.com/problems/add-two-numbers/

// 将两个用链表表示的整数相加，关键细节是进位位carry的考虑

// while循环里的条件设置也要注意，这样写比较简洁，不容易出错，如果只是node1或者node2结尾，剩下还有考虑进位位在另一条链上走的问题
// 所以当一条链走完，相当于一直加0，这个点不移动就可以了
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode node1 = l1;
        ListNode node2 = l2;
        int num1 = 0;
        int num2 = 0;
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        // 直到两条链和进位位carry为0为止
        while(node1 != null || node2 !=null || carry != 0){
            num1 = (node1 == null) ? 0 : node1.val;
            num2 = (node2 == null) ? 0 : node2.val;
            int sum = num1 + num2 + carry;
            node.next = new ListNode((sum % 10));
            carry = sum / 10;
            node = node.next;
            if(node1 != null){
                node1 = node1.next;
            }
            if(node2 != null){
                node2 = node2.next;
            }
        }
        return dummy.next;
    }
}
