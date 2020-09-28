// site: https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/

// 填充每个节点的下一个右侧节点指针
// 层次遍历的变形问题，要求常数空间

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

// 自己写的第一版层序遍历，用了队列，空间O(N)
class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            Node temp = null;
            for(int i = 0; i < size; i++){
                Node node = queue.poll();
                if(i == 0){
                    temp = node;
                }
                else if(i == size - 1){
                    temp.next = node;
                    node.next = null;
                }
                else{
                    temp.next = node;
                    temp = node;
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}
// 层次遍历的改进版，不需要维护一个temp节点，直接连到队头元素（下一个层次遍历到的点，就是右边的点）即可
class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            Node temp = null;
            for(int i = 0; i < size; i++){
                Node node = queue.poll();
                // 连到队头元素就可以了，队头元素就是下一个右侧点
                if(i < size - 1){
                    node.next = queue.peek();
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}

// 空间O(1)的方法，使用以建立好的next指针
// 对于一个节点node，将左右孩子通过next指针相连的操作是：node.left.next = node.right
// 而对于同一层的相邻节点node1和node2，node2 = node1.next，即同层的相邻节点可以通过next指针找到（本层的next指针在上一层建立）
// 把下一层的next处理完之后，转到下一层的最左节点
class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        Node mostLeftNode = root;
        // 结束条件是最后一层，即最左的节点的左孩子为空
        while(mostLeftNode.left != null){
            // 从每一层的最左边节点开始，构建下一层的next指针
            Node node = mostLeftNode;
            while(node != null){
                // 处理本节点左孩子的next指针
                System.out.println(node.val);
                node.left.next = node.right;
                // 处理本节点右孩子的next指针
                if(node.next != null){
                    node.right.next = node.next.left;
                }
                // 再同层移动
                node = node.next;
            }
            // node = null说明下一层的next建立完成
            // 转向下一层的最左节点
            mostLeftNode = mostLeftNode.left;
        }
        return root;
    }
}
