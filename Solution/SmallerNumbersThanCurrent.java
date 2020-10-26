// site: https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/

// 统计数组中有多少个数比当前数字小的

// 自己写的第一个版本
// 用桶来实现，count先记录出现次数，之后用累加来统计出比当前数字小的个数，注意sum和count的更新
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // count 统计出现次数
        int[] count = new int[101];
        int max = 0;
        for(int num : nums){
            max = Math.max(num, max);
            count[num]++;
        }
        // 累加将count[i]变成比i小的个数
        int sum = 0;
        for(int i = 0; i <= max; i++){
            int temp = sum;
            sum += count[i];
            count[i] = temp;
        }
        // 查询结果
        int n = nums.length;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            res[i] = count[nums[i]];
        }
        return res;
    }
}

// 改进的版本，sum更新那一块可以改进，但count的统计就变成了小于等于自己的个数，res找结果的时候要用前一个位置的count
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // count 统计出现次数
        int[] count = new int[101];
        int max = 0;
        for(int num : nums){
            max = Math.max(num, max);
            count[num]++;
        }
        // 累加将count[i]变成比i小的个数
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }
        // 查询结果
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            // 注意用的是nums[i] - 1的统计
            res[i] = nums[i] == 0 ? 0 : count[nums[i] - 1];
        }
        return res;
    }
}
