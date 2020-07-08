// site: https://leetcode-cn.com/problems/majority-element/
// 找出出现次数大于n/2的元素

// hashmap法
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            if(map.containsKey(i)){
                int time = map.get(i) + 1;
                if(time > nums.length / 2){
                    return i;
                }
                map.put(i, time);
            }
            else{
                map.put(i, 1);
                if(1 > nums.length / 2){
                    return i;
                }
            }
        }
        return 0;
    }
}

// 排序法
如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为floor(n/2)的元素（下标从 0 开始）一定是众数。
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}

// 
