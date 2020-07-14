// site: https://leetcode-cn.com/problems/linked-list-cycle/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 
// 空间O(1)的双指针法
// fast要判断两个，fast == null 或者 fast.next == null
// 后者在判断结尾的同时可以防止fast.next.next出现NullPointer
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        // 双指针法
        // 快指针从head.next开始，每次走两步
        ListNode fast = head.next;
        ListNode slow = head;
        // 如果fast追上了slow(快1圈)就退出
        while(slow != fast){
            // fast走到尾或者fast.next是尾
            // 说明可以走到null，没有环
            if(fast == null || fast.next == null){
                return false;
            }
            // fast走两步
            fast = fast.next.next;
            // slow走一步
            slow = slow.next;
        }
        // while退出了，说明追上了，有环
        return true;
    }
}

// 空间O(n)的HashSet记录方法
public class Solution {
    public boolean hasCycle(ListNode head) {
        // HashSet记录法
        HashSet<ListNode> set = new HashSet<>();
        while(head != null){
            if(set.contains(head)){
                return true;
            }
            else{
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }
}
