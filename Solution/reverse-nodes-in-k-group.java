// site: https://leetcode-cn.com/problems/reverse-nodes-in-k-group

// 每K 个一组翻转链表
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 // 思路：将链表分为三段
 // 已翻转 + 待翻转 + 未翻转
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        // 创建dummy头结点，方便操作
        dummy.next = head;
        ListNode prev = dummy;
        ListNode end = dummy;

        while (end.next != null){
            //找到end
            for(int i = 0; i < k && end != null; i++){
                end = end.next;
            }
            // end到达末尾，结束
            if(end == null){
                break;
            }
            // 记录end的下一个值，即未翻转链表的头结点
            ListNode next = end.next;
            // 记录需要翻转的链表段的头结点
            ListNode start = prev.next;
            // 从元链表断开准备翻转的链表段(待翻转)
            end.next = null;
            prev.next = reverseList(start);
            // 翻转之后,start变成已翻转链表段的尾部
            // 接上之前保存的next
            start.next = next;
            // 更新prev和end，更新至start，即以翻转的尾部
            prev = start;
            end = prev;
        }
        return dummy.next;
    }
    /**
     * 翻转一段链表
     * @param head 头结点
     * @return 翻转后的新头结点
     */
    public ListNode reverseList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
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
