// site: https://leetcode-cn.com/problems/lru-cache

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// 要求：实现一个 LRU 缓存

// 用 HashMap + 链表来实现

class LRUCache {

    /** 
    * LRU策略，用HashMap和双链表来存放数据
    * 最近使用过（访问或者添加）的元素放到链表头，链表尾则是最久未使用的元素
    */

    // 用来存键值对的HashMap
    // key 是 Integer，value 则是 DoubleNode
    private HashMap<Integer, DoubleNode> map;

    // 容量
    private int capacity;

    // 当前size
    private int size;

    // dummy head 和dummy tail
    private DoubleNode head;
    private DoubleNode tail;
    

    // 双链表类
    class DoubleNode {
        int key;
        int value;
        DoubleNode before;
        DoubleNode after;
        // 构造函数
        public DoubleNode() {};
        public DoubleNode(int key, int value){
            this.key = key;
            this.value = value;
            before = null;
            after = null;
        }
    }

    public LRUCache(int capacity) {
        // 初始化 capacity 和 map
        this.capacity = capacity;
        size = 0;
        map = new HashMap<>(capacity);
        
        // 头结点 head 和尾节点 tail
        head = new DoubleNode();
        tail = new DoubleNode();
        // head 和 tail 相连
        head.after = tail;
        tail.before = head;
    }
    
    public int get(int key) {
        // 先去map里面找
        DoubleNode node = map.get(key);
        // 没找到，返回-1
        if(node == null){
            return -1;
        }
        // 找到了，返回node的值
        else {
            // 放到链表头
            moveToHead(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        // 再map中找这个点，看看是否找到
        DoubleNode node = map.get(key);
        // 没找到
        if(node == null){
            // 创建新的结点
            DoubleNode newNode = new DoubleNode(key, value);
            // 放进map
            map.put(key, newNode);
            // 新加入的，所以放在队头
            addToHead(newNode);
            // 增加size
            size ++;
            // 如果超过了容量
            if(size > capacity){
                // 删掉链表最后一个元素 tail
                DoubleNode tailNode = removeTail();
                // map也要删掉，所以删除函数需要返回一个node，根据tailNode的key值来删除
                map.remove(tailNode.key);
                // 删掉了所以size减少
                size--;
            }
        }
        // 已经有这个key了，修改value即可
        else {
            node.value = value;
            // 刚修改过，移动到链表头
            moveToHead(node);
        }
    }

    /**
    * 插入到头结点
    */
    private void addToHead(DoubleNode node){
        // head是dummy head，所以不会直接让head = node的
        node.before = head;
        node.after = head.after;
        head.after.before = node;
        head.after = node;
    }

    /**
    * 移动到头结点
    */
    private void moveToHead(DoubleNode node){
        // 先把这个结点删掉，再插入到头部就可以了
        removeNode(node);
        addToHead(node);
    }

    /**
    * 删除一个结点
    */
    private void removeNode(DoubleNode node){
        // DoubleNode before = node.before;
        // DoubleNode after = node.after;
        node.before.after = node.after;
        node.after.before = node.before;
    }

    /**
    * 删除尾结点，并返回这个尾结点
    */
    private DoubleNode removeTail(){
        // 获取尾结点
        DoubleNode node = tail.before;
        removeNode(node);
        return node;
    }
}


// 继承 LinkedHashMap 来实现
// 重写 removeremoveEldestEntry 函数
class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        // 调用LinkedHashMap的构造函数
        // 构造函数：public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
        // 将accessOrder设置为true，即按照访问 access 顺序来安排双链表中的顺序
        super(capacity, 0.75F, true);
        // call super要放在第一
        this.capacity = capacity;
    }
    
    public int get(int key) {
        // 调用父类的就可以了
        return super.getOrDefault(key, -1);
    }   
    
    public void put(int key, int value) {
        // 调用父类的就可以了
        super.put(key, value);
    }

    // 重写removeEldestEntry函数，按照size和capacity的大小来返回
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

