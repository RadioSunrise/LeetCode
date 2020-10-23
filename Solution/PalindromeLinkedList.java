// site: https://leetcode-cn.com/problems/palindrome-linked-list/

// 判断一条链表是否回文
// 因为链表不能从后往前访问，所以要考虑反转后半段
// 找中点将链表分开两边，注意奇偶的fast指针的判断和slow的移动，之后反转后半段，然后比较回文

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        // 先找到中点
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果是奇数个点，fast不为null
        if(fast != null){
            // 奇数个点，则slow此时指向正中间的节点
            // 移动到后半段指向后半段的第一个点，如12345中令slow指向4的位置
            slow = slow.next;
        }
        // 反转
        slow = reverse(slow);
        // fast回到head的位置
        fast = head;
        // 比较是否相同
        while(slow != null){
            if(fast.val != slow.val){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    public ListNode reverse(ListNode node){
        ListNode prev = null;
        ListNode curr = node;
        ListNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
