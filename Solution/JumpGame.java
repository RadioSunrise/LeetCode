// site: https://leetcode-cn.com/problems/jump-game/

// 维护一个最远可达位置，逐个位置扫描，如果这个位置可达，则计算从本位置i可达的最远位置(i + nums[i])
// 并挑战最远距离值，如果最远位置大于等于n-1即最后一个位置可达，则true

class Solution {
    public boolean canJump(int[] nums) {
        // 当前可达的最远位置
        int rightMaxDis = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            // 当前位置i是否可达
            if(i <= rightMaxDis){
                // 挑战最远距离
                rightMaxDis = Math.max(rightMaxDis, nums[i] + i);
                // 最后一个位置可以到达
                if(rightMaxDis >= (n - 1)){
                    return true;
                }
            }
        }
        return false;
    }
}
