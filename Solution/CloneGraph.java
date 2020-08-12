// site: https://leetcode-cn.com/problems/clone-graph/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// 克隆一个图
// 用遍历（bfs、dfs）来克隆
// 关键的细节：首先要有一个在遍历过程中能够把node和nodeClone联系起来的结构，能通过原node找到未克隆完的node
// 还能标记是否加入neighbor，防止死循环
// 所以用HashMap，在一个node未完成克隆前，能够找到这个node的引用，把原node的neighbor加到新node里面
// 因为都是java传的都是引用，所以neighborClone都是在遍历的过程中被完善的，不是第一次加入就克隆完的

// bfs的做法
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        // 需要一个保存已经复制过的节点
        HashMap<Node, Node> clonedMap = new HashMap<>();
        // bfs 遍历复制
        // 先复制 Node1，放进队列和map里面
        Node newOne = new Node(node.val, new ArrayList<>());
        clonedMap.put(node, newOne);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        // bfs
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            // 将temp的邻居都入队，并且克隆，加到newNode里面
            for(Node neighbor : temp.neighbors){
                // 之前没访问过就创建一个node然后加入hashMap
                if(!clonedMap.containsKey(neighbor)){
                    clonedMap.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
                // 把neighbor对应的neighborClone放到克隆节点的neighbors列表中
                Node neighborClone = clonedMap.get(neighbor);
                clonedMap.get(temp).neighbors.add(neighborClone);
            }
        }
        return newOne;
    }
}

// dfs
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        // 需要一个保存已经复制过的节点
        HashMap<Node, Node> clonedMap = new HashMap<>();
        // dfs 遍历复制
        return dfsClone(node, clonedMap);
    }
    /** 
    * 递归dfs克隆
    */
    public Node dfsClone(Node node, HashMap<Node, Node>clonedMap){
        // 递归dfs结束条件
        // 如果node已经克隆过了(不代表克隆完了)
        if(clonedMap.containsKey(node)){
            // System.out.println("node " + node.val + " has cloned.");
            return clonedMap.get(node);
        }
        // 克隆，加入map中
        Node cloneNode = new Node(node.val, new ArrayList());
        clonedMap.put(node, cloneNode);
        // 加入邻居
        for(Node neighbor : node.neighbors){
            // 递归
            // System.out.println("node " + node.val + " 's neighbors adding " + neighbor.val);
            cloneNode.neighbors.add(dfsClone(neighbor, clonedMap));
            // System.out.println("node " + node.val + " 's neighbors has added " + neighbor.val);
        }
        return cloneNode;
    }
}
