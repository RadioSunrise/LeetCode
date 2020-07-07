// site: https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 // 用栈帮忙，但是这里可以用linkedList来做也行，快一点点
 // 用removeLast方法
class Solution {
    public int[] reversePrint(ListNode head) {
        if(head == null){
            int[] res = new int[0];
            return res;
        }
        LinkedList<Integer> list = new LinkedList<>();
        while(head != null){
            list.addLast(head.val);
            head = head.next;
        }
        int size = list.size();
        int[] res = new int[size];
        for(int i = 0; i < size; i++){
            res[i] = list.removeLast();
        }
        return res;
    }
}
