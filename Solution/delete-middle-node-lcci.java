// site: https://leetcode-cn.com/problems/delete-middle-node-lcci

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        if(node == null)
        {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

// 因为只能访问要删除的节点，所以没有办法获取到该节点的前驱，所以把下一个节点的val和next赋给本节点的val和next，其实是把node.next给删掉了
