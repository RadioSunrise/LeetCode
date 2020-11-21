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

// 迭代法
// 迭代法通过分段合并的思想，一开始每小段长为1，相邻的两段合并，之后段长翻倍，知道段长大于length
// 关键的细节是要先计算整个链的长度，另外是cut函数的写法，在主函数迭代部分，合并和接到结果链上要用两个指针来操作，防止断链
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        // 统计链表长度
        int length = 0;
        ListNode node = head;
        while(node != null){
            length++;
            node = node.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 按照段长逐步扩大
        for(int part = 1; part < length; part *= 2){
            // 两个节点curr和tail
            // curr 指向需要排序的段的起点
            // tail 指向已排好序的段的末尾，用于连接下一段已经排序好的链表
            ListNode curr = dummy.next;
            ListNode tail = dummy;
            // 每次两段来合并，按照段长找到第一段
            while(curr != null){
                // left从curr开始
                ListNode left = curr;
                // 以left为起点剪一段，这一段原来的下一个点就是第二段的起点
                ListNode right = cut(left, part);
                // 更新curr，并且剪第二段
                curr = cut(right, part);
                // 把这两段合并起来
                tail.next = merge(left, right);
                // 更新tail，通过顺序走到结尾的方法
                while(tail.next != null){
                    tail = tail.next;
                }
            }
        }
        // 直到part>length
        return dummy.next;
    }
    /**
    * 从node开始，把长度为n的一段剪断，返回剪断的下一个点
    */
    public ListNode cut(ListNode node, int n){
        ListNode temp = node;
        // 找到n的位置，注意--n
        while((--n) > 0 && temp != null){
            temp  = temp.next;
        }
        // temp到末尾了，说这一段剩下的长度不够n，返回null
        if(temp == null){
            return null;
        }
        ListNode next = temp.next;
        temp.next = null;
        return next;
    }
    /**
    * 合并两段链表
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

// 2020-11-21 更新另一种递归的写法，和数组排列类似，指定开始和结束的位置
class Solution {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    /**
    * 递归实现归并排序，指定head和tail（和数组排序一样，指定边界）
    */
    public ListNode sortList(ListNode head, ListNode tail){
        // 空链则返回
        if(head == null){
            return head;
        }

        /*
        if(tail == null){
            System.out.println("call sort, head is " + head.val + ", tail is null");
        }
        else {
            System.out.println("call sort, head is " + head.val + ", tail is " + tail.val);
        }
        */ 
        
        // 或者只有一个节点，
        if(head.next == tail){
            // 断开head，因为递归的传递是[head,mid), [mid,tail)
            // 所以断开之后tail在后面不会丢，是null就直接置null也没问题
            head.next = null;
            return head;
        }
        // 快慢指针找到中间点mid
        ListNode slow = head;
        ListNode fast = head;
        while(fast != tail){
            slow = slow.next;
            fast = fast.next;
            // fast走一步不为tail，再走一步
            if(fast != tail){
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        // 分开排序再合并
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode list = merge(list1, list2);
        return list;
    }
    /**
    * 合并两个有序链表，参数是两个头节点
    */
    public ListNode merge(ListNode head1, ListNode head2){
        // System.out.println("call merge, head1 is " + head1.val + ", head2 is " + head2.val);
        ListNode dummy = new ListNode(0);
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode temp = dummy;
        while(temp1 != null && temp2 != null){
            if(temp1.val <= temp2.val){
                temp.next = temp1;
                temp1 = temp1.next;
            }
            else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        temp.next = (temp1 == null)? temp2 : temp1;
        return dummy.next;
    }
}
