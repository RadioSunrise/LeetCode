// site: https://leetcode-cn.com/problems/remove-duplicate-node-lcci/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //一次遍历，时间O(N)，空间O(N)
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null || head.next == null)
        {
            return head;
        }
        int Max_num = 20001;
        boolean[] cache = new boolean[Max_num];
        ListNode temp = head; 
        cache[temp.val] = true;
        // 因为是单链表，所以判断temp.next是否满足条件再删除，如果删除temp的位置会找不到前驱
        while(temp.next!= null)
        {
            // ListNode cur = temp.next;
            if(cache[temp.next.val])
            {
                temp.next = temp.next.next; //不满足条件，temp不动，temp.next向前移动
            }
            else
            {
                cache[temp.next.val] = true;
                temp = temp.next;  //满足条件，temp移动到temp.next
            }
        }
        //temp.next = null;
        return head;
    }
}

// 关键在于: 1.具体判断和删除的位置temp.next而不是temp
//           2.temp不是每一次while循环都要temp=temp.next，遇到要删除的节点时，temp是不动的，只有temp.next会动。不删除的时候temp才会移动到temp.next

/*
// 双重循环，时间O(N^2)，空间O(1)
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null || head.next == null)
        {
            return head;
        }
        ListNode cur = head;
        while(cur != null)
        {
            ListNode cur_check = cur;
            while(cur_check.next != null)
            {
                if(cur_check.next.val == cur.val)
                {
                    cur_check.next = cur_check.next.next;
                }
                else
                {
                    cur_check = cur_check.next;
                }
            }
            cur = cur.next;
        }
        return head;
    }
}
*/
