// site: https://leetcode-cn.com/problems/lru-cache

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// 要求：实现一个 LRU 缓存

// 用 HashMap + 链表来实现




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

