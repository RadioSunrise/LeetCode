/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

// 将一棵BST转成双向链表

// 非递归中序遍历版本，记得最后要设置尾节点和头节点的左右指针
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        // dummy head
        Node dummy = new Node(0);
        dummy.right = root;
        // 利用InOrder
        Node prev = dummy;
        Node node = root;
        Stack<Node> stack = new Stack<>();
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            // 第二次遇到，操作
            node = stack.pop();
            // System.out.println("now node is " + node.val);
            // System.out.println("now prev is " + prev.val);
            prev.right = node;
            node.left = prev;
            prev = node;
            // 转向右边
            node = node.right;
        }
        // 设置第一个节点的left指针
        // prev指向最后一个节点
        // System.out.println("Travel over prev is " + prev.val);
        // System.out.println("Dummy's right is " + dummy.right.val);
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }
}

// 递归中序遍历版做法，注意遍历第一个节点的时候的特判，prev == null表明遍历第一个节点，设置head，之后通过prev构成链

class Solution {
    /**
    * 全局变量结点, head是整个链表的头节点
    */
    Node head = null;
    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        // 中序dfs
        dfs(root);
        // dfs完之后，prev指向尾节点
        head.left = prev;
        prev.right = head;
        // 返回head
        return head;
    }
    /**
    * 递归设置head和prev指向的节点对象
    */ 
    public void dfs(Node node){
        if(node == null){
            return;
        }
        dfs(node.left);
        // 第一次设置，设置头节点head
        if(prev != null){
            prev.right = node;
        }
        else {
            head = node;
        }
        node.left = prev;
        prev = node;
        dfs(node.right);
    }
}
