// site: https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        for(int i = 0; i < k; i++)
        {
            if(fast == null)
            {
                return null;
            }
            fast = fast.next;
        }
        while(fast != null)
        {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
// 双游标法，fast先走k步（因为k从1开始），注意判断条件是，最后fast会走到null，slow则是结果
