
// site: https://leetcode-cn.com/problems/two-sum/

// 两数之和，同hashMap保存差值可以将时间复杂度减到O(n)，暴力法要O(n^2)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // key: 和target的差值，value: 下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            if(!map.containsKey(nums[i])){
                map.put(diff, i);
            }
            else {
                return new int[]{map.get(nums[i]), i};
            }
        }
        return new int[]{0,0};
    }
}
