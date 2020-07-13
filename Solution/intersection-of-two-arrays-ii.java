// site:

// 用哈希表来存放其中一个数组的元素及出现次数，遍历第二个数组的同时，如果map中找到（且次数>0），则放入结果，然后将次数减1
// 因为次数也要保证和原数组中一致，所以要有val--的操作

// 自己的混乱写法，可以改成调用自己，把两个数组反过来就可以了
// map的查找也可以用getOrDefault来简化代码
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        if (n1 > n2){
            for (int i : nums2){
                if (!map.containsKey(i)){
                    map.put(i, 1);
                }
                else {
                    map.put(i, map.get(i) + 1);
                }
            }
            int[] result = new int[nums2.length];
            int index = 0;
            for(int i : nums1){
                if(map.containsKey(i)){
                    int val = map.get(i);
                    if(val > 0){
                        result[index] = i;
                        index++;
                        val--;
                        map.put(i, val);
                    }
                }
            }
            return Arrays.copyOfRange(result, 0, index);
        }
        else {
            for (int i : nums1){
                if (!map.containsKey(i)){
                    map.put(i, 1);
                }
                else {
                    map.put(i, map.get(i) + 1);
                }
            }
            int[] result = new int[nums1.length];
            int index = 0;
            for(int i : nums2){
                if(map.containsKey(i)){
                    int val = map.get(i);
                    if(val > 0){ // 只有当count>0才能放入结果
                        result[index] = i;
                        index++;
                        val--;
                        map.put(i, val);
                    }
                }
            }
            return Arrays.copyOfRange(result, 0, index);
        }
    }
}

// 改进版的
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 默认nums1比较小
        if (n1 > n2){
            return intersect(nums2, nums1);
        }
        for (int i : nums1){
            if (!map.containsKey(i)){
                map.put(i, 1);
            }
            else {
                map.put(i, map.get(i) + 1);
            }
        }
        int[] result = new int[nums1.length];
        int index = 0;
        for(int i : nums2){
            // 用getOrDefault来减少if判断
            // 如果没有找到，就返回0
            int count = map.getOrDefault(i, 0);
            if(count > 0){
                result[index] = i;
                    index++;
                    count--;
                    map.put(i, count);
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }
}

// 排序的做法
// 当两个数组都是有序的时候，用两个指针i和
// 如果nums1[i] == nums2[j] -> 存入结果
// nums1[i]小，i++
// nums2[j]小，j++
// 直到其中一个指针越界
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // 排序的做法
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        int[] result = new int[Math.min(n1, n2)];
        while (i < n1 && j < n2){
            if (nums1[i] == nums2[j]){
                result[k] = nums1[i];
                k++;
                i++;
                j++;
            }
            else if (nums1[i] < nums2[j]){
                i++;
            }
            else {
                j++;
            }
        }
        return Arrays.copyOfRange(result, 0, k);
    }
}
