// site: https://leetcode-cn.com/problems/insertion-sort-list/submissions/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 // 对单链表实现插入排序
 // 将链分为已排好序和未排序的部分，用sorted来记录已排的末尾，初值为head
 // curr指向当前的要排序的点，排序前先比较sorted的大小，如果比sorted大则不需要排，否则要从头寻找要插入的位置
 // 由于sorted大小的判断，在找插入点的时候也不用担心会“出界”
 
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 已排好的最后一个点
        ListNode sorted = head;
        ListNode curr = head.next;
        while(curr != null){
            // 比已排好序的大，不用移动
            if(sorted.val <= curr.val){
                sorted = sorted.next;
            }
            else{
                // 从头开始找合适的位置
                ListNode prev = dummy;
                while(prev.next.val < curr.val){
                    prev = prev.next;
                }
                // 因为前面的if已经判断了大于的情况，所以prev不会“出界”
                // 保存要排序的下一个点
                sorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            // curr是排好序的下一个，即下一个未排序的点
            curr = sorted.next;
        }
        return dummy.next;
    }
}
