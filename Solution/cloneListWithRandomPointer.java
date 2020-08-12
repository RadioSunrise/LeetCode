/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// 和复制图cloneGraph很像，需要用hashMap来记录原节点和克隆节点的对应关系，方便查找

// 自己的第一种方法：先遍历一遍处理next域并用map记录下原节点和克隆节点的对应关系，再遍历一遍处理random指针域
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        // 需要一个map来保存克隆的对应关系和方便找到clone的节点
        // key: node，value: 克隆的node
        HashMap<Node, Node> cloneMap = new HashMap<>();

        // 先遍历第一遍，处理next指针域和构建map的对应关系
        
        // 处理第一个节点
        // prev和curr用于克隆链表的遍历
        // node用于原链表的遍历
        Node node = head;
        Node curr = new Node(node.val);
        Node prev = curr;
        // 最终结果res也要存下来
        Node res = curr;
        cloneMap.put(node, curr);

        node = node.next;
        while(node != null){
            curr = new Node(node.val);
            cloneMap.put(node, curr);
            prev.next = curr;
            prev = curr;
            node = node.next;
        }

        // 第二次遍历，处理random指针域
        node = head;
        while(node != null){
            // System.out.println("now node is " + node.val);
            Node cloneNode = cloneMap.get(node);
            if(node.random == null){
                cloneNode.random = null;
            }
            else{
                Node nodeRandom = node.random;
                // System.out.println("now node is " + node.val + ", and nodeRandom is " + nodeRandom.val);
                cloneNode.random = cloneMap.get(nodeRandom);
            }
            // 向前移动
            node = node.next;
        }
        return res;
    }
}

// 递归做法
// 相当于把这个链表看成一个图，而next域和random域则是临接点，类似dfs的递归
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        // 需要一个map来保存克隆的对应关系和方便找到clone的节点
        // key: node，value: 克隆的node
        HashMap<Node, Node> cloneMap = new HashMap<>();
        
        // 递归函数版
        return Clone(head, cloneMap);
    }

    /**
    * 递归做法，分别根据next指针和random指针递归克隆
    * 因为要防止重复创建或者指向导致的死循环，先要判断是否已经创建/访问过
    */
    public Node Clone(Node node, HashMap<Node, Node> cloneMap){
        // 递归结束条件1，node为null
        if(node == null){
            return null;
        }
        // 递归结束条件2，已经创建过了，即对应关系已经存在了
        // 返回已经创建过的这个节点
        if(cloneMap.containsKey(node)){
            return cloneMap.get(node);
        }
        // 创建一个对应关系
        Node cloneNode = new Node(node.val);
        // 先加入map，再进行修改指针，否则会死循环
        cloneMap.put(node, cloneNode);
        // 修改两个指针域
        cloneNode.next = Clone(node.next, cloneMap);
        cloneNode.random = Clone(node.random, cloneMap);
        return cloneNode;
    }
}

// 迭代做法
