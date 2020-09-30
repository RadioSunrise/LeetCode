// site: https://leetcode-cn.com/problems/sort-list/

// 链表排序

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
 
 // 递归法，递归空间要o(n)不满足常数空间复杂度
class Solution {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }
    /**
    * 递归实现的归并排序（不符合常数级的空间要求）
    */
    public ListNode mergeSort(ListNode head){
        // 递归结束
        if(head == null || head.next == null){
            return head;
        }
        // 快慢指针找中间点
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow指向的是分界点，第一段是head到slow，第二段是slow.next到null
        ListNode nextStart = slow.next;
        // slow断开
        slow.next = null;
        // 递归
        ListNode first = mergeSort(head);
        ListNode second = mergeSort(nextStart);
        ListNode res = merge(first, second);
        return res;
    }
    /**
    * 将两段排序好的链表合起来
    */
    public ListNode merge(ListNode node1, ListNode node2){
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while(node1 != null && node2 != null){
            if(node1.val < node2.val){
                node.next = node1;
                node1 = node1.next;
            }
            else {
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }
        node.next = (node1 == null)? node2 : node1;
        return dummy.next;
    }
}

// 待添加迭代法
