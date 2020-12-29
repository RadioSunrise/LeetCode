// site: https://leetcode-cn.com/problems/patching-array/

// 贪心算法，不太懂

class Solution {
    public int minPatches(int[] nums, int n) {
        // 贪心思想
        // 当[1 : x - 1]都被覆盖，且x在数组内，则覆盖范围扩展至[1 : 2x - 1]
        // 贪心的做法是每次找到最小的没被覆盖的x，且[1 : x - 1]都被覆盖

        // 结果ans，初始化需要增加0个
        int ans = 0;
        // x看做是覆盖的上界，因为要乘2，所以用long
        long x = 1;
        // 遍历数组用的
        int index = 0;
        int length = nums.length;
        // 退出条件x > n，因为[1:x-1]被覆盖
        while(x <= n){
            // nums[index] <= x，则覆盖的范围x可以增加nums[index]
            if(index < length && nums[index] <= x){
                x += nums[index];
                index++;
            }
            // nums[index] > x 或者越界，则x翻倍继续遍历
            else {
                x *= 2;
                ans++;
            }
        }
        return ans;
    }
}
