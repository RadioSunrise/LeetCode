// site: https://leetcode-cn.com/problems/house-robber-ii/

// 打家劫舍2，循环的房屋，两头都做一次

class Solution {
    fun rob(nums: IntArray): Int {
        // 因为围成一圈，所以第一家和最后一家不能同时偷
        // 可以将问题转化成两种直线型的情况
        // 如果 1.不偷第一家，则偷的范围是 [1, n - 1]
        //      2.不偷最后一家，范围是 [0, n - 2]

        // 根据偷取的范围调用直线型，比较两种情况的较大值
        val len = nums.size
        if(len == 1){
            return nums[0]
        } else if (len == 2) {
            return Math.max(nums[0], nums[1])
        }
        val max0 = robIntLine(nums, 0, len - 2)
        val max1 = robIntLine(nums, 1, len - 1)
        return if (max0 > max1) max0 else max1
    }

    fun robIntLine(nums: IntArray, start: Int, end: Int): Int {
        // 偷取区间为 [start, end]
        // 直线型偷取，使用滚动数组减少空间复杂度
        var dpI2 = nums[start]
        var dpI1 = Math.max(nums[start], nums[start + 1])
        var i = start + 2
        while(i <= end){
            val temp = dpI1
            dpI1 = Math.max(dpI2 + nums[i], dpI1)
            dpI2 = temp
            i++
        }
        return dpI1
    }
}
