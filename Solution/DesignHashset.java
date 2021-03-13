// site: https://leetcode-cn.com/problems/design-hashset/

// reference: https://leetcode-cn.com/problems/design-hashset/solution/yi-ti-san-jie-jian-dan-shu-zu-lian-biao-nj3dg/

// 实现 HashSet
// 链地址法

public class DesignHashset {

    /**
    * 哈希函数: Base(素数) 求模
    * 冲突解决: 链地址法
    * 实际存放数据的是一个 Node 类数据
    */
    private static int BASE = 367;
    private Node[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new Node[BASE];
    }
    
    public void add(int key) {
        // 计算 hash 值
        int index = hash(key);
        // 判断是否已经存在该 key
        Node head = data[index];
        Node temp = head;
        // 遍历链表
        if(head != null){
            Node prev = null;
            while(temp != null){
                // 遇到 key 则直接退出
                if(temp.key == key){
                    return;
                }
                prev = temp;
                temp = temp.next;
            }
            // temp = prev 回到链尾
            temp = prev;
        }
        Node node = new Node(key);
        // 插入链表
        // 尾插法
        if(temp != null){
            temp.next = node;
        } else {
            // temp == null 说明这条链是空的，放第一个
            data[index] = node;
        }
    }
    
    public void remove(int key) {
        // 计算哈希，遍历链表删掉
        int index = hash(key);
        Node head = data[index];
        Node temp = head;
        if(head != null){
            Node prev = null;
            while(temp != null){
                if(temp.key == key){
                    if(prev != null){
                        prev.next = temp.next;
                    } else {
                        // prev == null，说第一次就遇到要删的
                        data[index] = temp.next;
                    }
                    return;
                }
                prev = temp;
                temp = temp.next;
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = hash(key);
        Node head = data[index];
        while(head != null){
            if(head.key == key){
                return true;
            }
            head = head.next;
        }
        return false;
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
        private Node next;
        private Node(int key){
            this.key = key;
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
