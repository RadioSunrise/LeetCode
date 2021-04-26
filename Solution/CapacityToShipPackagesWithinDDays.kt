// site: https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/

// 二分查找

class CapacityToShipPackagesWithinDDays {
    /**
    * 二分法 + 贪心 实现
    * 
    * 对于运载能力的下限 Xans, 若比 Xans 小, 则一定不能够运完
    * 若大于 Xans, 则必然能够运完, 可以用二分法找出 Xans
    * 
    * 判断运力为 x 时，能否 D 天搬完，使用贪心策略，从小到大遍历
    * 当一天的重量和超过了运力 x，则将最后一件物品放到下一天，遍历完则可以得到天数
    */
    fun shipWithinDays(weights: IntArray, D: Int): Int {
        // 运力的下限是最重的物品，上限是重量和
        var left = 0
        var right = 0
        for (weight in weights) {
            if (weight > left) {
                left = weight
            }
            right += weight
        }
        // 区间是[left, right]，二分查找
        while (left < right) {
            val mid = left + (right - left) / 2
            // 运力为 mid 不满足
            if (!checkTrans(mid, weights, D)) {
                // 下一次搜索区间为 [mid + 1, right]
                left = mid + 1
            } else {
                // 运力为 mid 满足，下一次搜索区间是 [left, mid]
                right = mid
            }
        }
        // 结束 while 时，left = right，为最终答案（边界）
        return left
    }

    fun checkTrans(power: Int, weights: IntArray, d: Int): Boolean {
        var needDay = 1
        var sum = 0
        for (weight in weights) {
            // 总重量超过了运力，则清空sum，天数+1
            if (sum + weight > power) {
                needDay++
                sum = 0
            }
            sum += weight
        }
        return (needDay <= d)
    }
}
