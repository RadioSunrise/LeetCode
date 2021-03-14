// https://leetcode-cn.com/problems/design-hashmap/submissions/

// 设计哈希映射 （HashMap）

public class DesignHashMap {

    /**
    * 哈希函数: Base(素数) 求模
    * 冲突解决: 链地址法
    * 实际存放数据的是一个 Node 类数据
    */
    private static int BASE = 367;
    private Node[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new Node[BASE];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        // 计算 hash 值
        int index = hash(key);
        // 判断是否已经存在该 key
        Node head = data[index];
        Node temp = head;
        // 遍历链表
        if(head != null){
            Node prev = null;
            while(temp != null){
                // 遇到 key 则修改value
                // 修改完直接返回
                if(temp.key == key){
                    temp.value = value;
                    return;
                }
                prev = temp;
                temp = temp.next;
            }
            // temp = prev 回到链尾
            temp = prev;
        }
        Node node = new Node(key, value);
        // 插入链表
        // 尾插法
        if(temp != null){
            temp.next = node;
        } else {
            // temp == null 说明这条链是空的，放第一个
            data[index] = node;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        Node head = data[index];
        while(head != null){
            // 找到 key 了返回它的 value
            if(head.key == key){
                return head.value;
            }
            head = head.next;
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        // 计算哈希，遍历链表删掉
        int index = hash(key);
        Node head = data[index];
        Node temp = head;
        if(head != null){
            Node prev = null;
            while(temp != null){
                // 找到了删掉
                if(temp.key == key){
                    if(prev != null){
                        prev.next = temp.next;
                    } else {
                        // prev == null，说明第一次就遇到要删的
                        data[index] = temp.next;
                    }
                    return;
                }
                prev = temp;
                temp = temp.next;
            }
        }
    }

    /** 
    * 计算 hash 值
    */
    private int hash(int key){
        return (key % BASE);
    }

    /**
    * 内部类 Node
    */
    class Node {
        private int key;
        private int value;
        private Node next;
        private Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
