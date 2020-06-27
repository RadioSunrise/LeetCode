// site: https://leetcode-cn.com/problems/two-sum/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++)
        {
            int differ = target - nums[i];
            if(! map.containsKey(differ))
            {
                map.put(nums[i], i);
            }
            else
            {
                result[0] = map.get(differ);
                result[1] = i;
                return result;
            }
        }
        return result;
    }
}
// 用map来实现，map的<K,V>设计是关键，key设计为nums[i]，用differ来查找(map.containsKey)，value为下标
