// site: https://leetcode-cn.com/problems/linked-list-cycle-ii/

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
 
 // 和判断是否有环的第一题不同，这题还需要找到入环结点，需要根据fast和slow的步数关系来得出结果
 // 第二次相遇可以找到入环结点，详细见代码注释
 
public class Solution {
    public ListNode detectCycle(ListNode head) {
        // 双指针法
        if(head == null){
            return null;
        }
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        // 如果存在环，那么当fast指针和slow指针必定会相遇
        while(true){
            // 走fast指针走到末尾了
            if(fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            // 相遇了，存在环
            if(fast == slow){
                break;
            }
        }
        // 退出while，此时slow == fast
        // fast 走的步数 f 是 slow 的步数 s 的两倍，即f = 2 * s
        // 设链表长度为 a + b，head到入环结点（包括入环结点）共a个，环有b个结点（包括入环结点）
        // 而当fast == slow的时候，f比s多n * b，（环长度的整数倍）
        // 即 f = 2 * s, f = s + nb，推出 s = nb
        // 当一个指针走到入环结点的时候，走过的步数是a + nb（走第一段加n圈的环）
        // 此时 slow 已经走过了 nb，所以只要再走 a 就会回到入环结点
        // a是不知道的，但是一个新指针从head结点走a步就会到入环结点，这样slow和新指针就会相遇
        // 所以让fast = head，每次移动一步，第二次相遇就能找到入环结点

        // 令fast指向head
        fast = head;
        // slow和fast一起走，fast也是走一步
        while(slow != fast){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
