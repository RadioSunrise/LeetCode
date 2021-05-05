// site: https://leetcode-cn.com/problems/delete-and-earn/

// 可以转化为打家劫舍问题

class DeleteAndEarn {
    fun deleteAndEarn(nums: IntArray): Int {
        // 增加统计数组 sum, 用于统计 nums 中相同元素的总和
        // 设数值 x 的出现次数为 cx, 则 sum[x] = x * cx
        var maxVal = 0
        for (num in nums) {
            maxVal = Math.max(maxVal, num)
        }
        // 统计 sum
        val sum = IntArray(maxVal + 1)
        for (num in nums) {
            sum[num] += num
        }
        // 剩余的部分和打家劫舍一样，选择了 x，则 (x - 1) 和 (x + 1) 不能选
        // dp_i_2 = dp[i - 2], dp_i_1 = dp[i - 1]
        var dp_i_2 = sum[0]
        var dp_i_1 = if (sum[0] > sum[1]) sum[0] else sum[1]
        // 最大的下标是maxVal
        for (i in (2..maxVal)) {
            val temp = dp_i_1
            dp_i_1 = Math.max(dp_i_2 + sum[i], dp_i_1)
            dp_i_2 = temp
        }
        return dp_i_1
    }
}
