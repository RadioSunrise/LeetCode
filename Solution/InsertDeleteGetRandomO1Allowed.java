// site: https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

// O(1) 时间插入、删除和获取随机元素 - 允许重复，使用list和hashmap实现，hashset存放索引作为map的value
class RandomizedCollection {

    // list进行随机获取
    List<Integer> list;
    // 因为预允许重复元素，所以一个key会有多次出现，用一个set来存储list中的索引
    HashMap<Integer, Set<Integer>> map;
    // 随机object
    Random random;
    // 当前元素的数量，用于生成索引
    int count = 0;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // 放到list里面，得到一个索引
        list.add(val);
        int index = list.size() - 1;
        // 检查map有没有这个key，有的话就放入set，没有就创建一个set
        Set set = map.getOrDefault(val, null);
        if(set == null){
            set = new HashSet<>();
        }
        // set里面存放索引
        set.add(count);
        // 数量+1
        count++;
        // 放回map中更新
        map.put(val, set);
        // 返回
        // System.out.println("insert " + val + " successfully");
        return set.size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        // 因为list只有删除最后一个元素才是O(1)时间，所以把要删除的元素交换到末尾，再删除
        // 先获取这个元素出现的索引
        if(map.containsKey(val)){
            // System.out.println("contains, deleting " + val);
            int lastIndex = count - 1;
            int lastVal = list.get(lastIndex);
            // 用迭代器获取set中的索引
            Set currentSet = map.get(val);
            Set lastSet = map.get(lastVal);
            int currentIndex = (int)currentSet.iterator().next();
            // 把原位置设置成lastVal
            list.set(currentIndex, lastVal);
            // 删除set中的索引
            currentSet.remove(currentIndex);
            // 如果已经删完了，map中把这个key对删掉
            if(currentSet.size() == 0){
                // System.out.println("set is clear, remove the set");
                map.remove(val);
            }
            // 加入新索引
            lastSet.remove(lastIndex);
            lastSet.add(currentIndex);
            // 删除最后一个元素
            list.remove(lastIndex);
            // 数量减1
            count--;
            return true;
        }
        else {
            // System.out.println("not contain " + val + ", cannot remove");
            return false;
        }
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get((int)(Math.random() * list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
