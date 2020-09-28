// site: https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/

// 117题， 116是限定完美二叉树，117中不保证树是完美二叉树，即右子树不一定存在，或者同层的右边节点不一定有孩子
// 把一层的节点看成链表的同时，还要额外记录下一行的起点，即下一行中第一个非空的节点
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        // 把每一层都看成用next指针连起来的链表 

        // 每一行的起点head
        Node head = root;
        while(head != null){
            // 这一层遍历用的node
            Node node = head;
            // 前驱
            Node prev = null;
            // 下一层的起点
            Node nextStart = null;
            // 相当于链表的遍历 / 串联
            while(node != null){
                // 处理左孩子
                if(node.left != null){
                    if(prev != null){
                        // 设置prev的指向（把前驱和当前点相连）
                        prev.next = node.left;
                    }
                    // 遇到第一个非空节点记录下来，作为下一层的起点
                    if(nextStart == null){
                        nextStart = node.left;
                    }
                    prev = node.left;
                }
                // 处理右孩子
                if(node.right != null){
                    if(prev != null){
                        prev.next = node.right;
                    }
                    if(nextStart == null){
                        nextStart = node.right;
                    }
                    prev = node.right;
                }
                // 同一层移动
                node = node.next;
            }
            // 移动到下一层
            head = nextStart;
        }
        return root;
    }
}
