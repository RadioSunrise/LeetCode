// site: https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/

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
 
 // 删除重复元素，留下一次不用全删
 
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null && curr.next != null){
            // 因为排过序了，所以相同数值的节点会在一起
            while(curr.next != null && curr.val == curr.next.val){
                // 删除相邻节点
                curr.next = curr.next.next;
            }
            // prev = curr;
            curr = curr.next;
        }
        return dummy.next;
    }
}
