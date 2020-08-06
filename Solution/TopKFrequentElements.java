// site: https://leetcode-cn.com/problems/top-k-frequent-elements/

package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 给定一个数组，返回出现频率最大的前个k元素

// 第一种方法：用最小堆来做
// 维护一个大小为k的最小堆，堆里面存放出现频率最大的k个元素
// 求前k个最大，用可以最小堆，堆的空间O(k)，hash表的空间则是O(n)
/**
 * leetcode 347 出现频率最高的前k个元素
 */
public class TopKFrequentElements {
    /**
     * 给定的数组，返回出现频率最高的k个元素
     * @param nums
     * @param k
     * @return k大小的数组，顺序随意
     */
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        // 统计频率的HashMap
        // key元素，value出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        // 最小堆minHeap
        int[] minHeap = new int[k];
        for(int num : nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }
            else {
                map.put(num, 1);
            }
        }
        // 如果数组里面的不同元素个数小于等于k
        if(map.size() <= k){
            int[] res = new int[map.size()];
            int i = 0;
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                res[i++] = entry.getKey();
            }
            return res;
        }
        // 逐个插入堆
        // count统计堆里面的元素
        int count = 0;
        int appear;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            // 堆还没有满，插入到最后一个位置
            if(count + 1 <= k){
                minHeap[count] = entry.getKey();
                appear = entry.getValue();
                count++;
                // 从count逐步向上比较，如果parent出现的次数大，就让parent下沉
                int i;
                for(i = count - 1; map.get(minHeap[(i - 1) / 2]) > appear && i > 0; i = (i - 1) / 2){
                    minHeap[i] = minHeap[(i - 1) / 2];
                }
                minHeap[i] = entry.getKey();
            }
            // 堆已经满了
            else {
                // 比较，如果当前元素的出现次数小于堆顶元素的出现次数
                if(entry.getValue() > map.get(minHeap[0])){
                    // 删除堆顶（堆顶最小），新元素放入堆顶，然后调整
                    minHeap[0] = entry.getKey();
                    adjust(minHeap, 0, map);
                }
            }
        }
        return minHeap;
    }

    /**
     * 最小堆调整
     * @param minHeap
     * @param index
     */
    public void adjust(int[] minHeap, int index, HashMap<Integer, Integer> map){
        int size = minHeap.length;
        int parent, child;
        int temp = minHeap[index];
        int appear = map.get(temp);
        for(parent = index; 2 * parent + 1 < size; parent = child){
            child = 2 * parent + 1;
            if(child + 1 < size && map.get(minHeap[child]) > map.get(minHeap[child + 1])){
                child++;
            }
            // 如果temp小于等于minHeap[child]，不同调整了
            if(appear <= map.get(minHeap[child])){
                break;
            }
            else {
                minHeap[parent] = minHeap[child];
            }
        }
        minHeap[parent] = temp;
    }
    public static void main(String[] args){
        TopKFrequentElements solution  = new TopKFrequentElements();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] res = solution.topKFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }
}


// 用最大堆也是可以的，把各个不同的元素放进堆里，然后调整，然后删除前K个就可以了
