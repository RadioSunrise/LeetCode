// site: https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/

class MinimumNumberOfDaysToMakeMBouquets {
    // 总共需要 k * m 朵花，而且使用的 k 朵花需要连续
    // 第 i 朵花在 bloomDay[i] 才开
    // 通过一个辅助函数判断在指定天数 day 能不能制作足够的花
    // 可以通过遍历数组，计算不重复且长度等于 k 的连续子区间的数量
    // 若数量大于等于 m ，则满足条件
    // 当 day 很小的时候，基本都是不满足的；当 day 很大的时候，是可以的
    // 所以可以用二分，即当天数小于答案式，可以排除一边

    fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
        // 长度判断，如果总的花朵数小于需要的花束，返回-1
        // 否则必定能找到结果
        val len = bloomDay.size
        if (len < k * m) {
            return -1
        }

        // 最短天数为1，最大天数为 max(bloomDay)
        var left = 1
        var right = 1
        for (day in bloomDay) {
            right = if (day > right) day else right
        }

        // 二分
        while (left < right) {
            // 当天数为 mid 时，如果满足，则区间为 [left, mid]
            // 不满足，则区间为 [mid + 1, right]
            // 所以用下取整 
            val mid = left + ((right - left) / 2)
            // 满足
            if (check(bloomDay, m, k, mid)) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        // 最后的边界为left
        return left
    }

    /**
    * 判断天数为 [day] 的时候能否制作 [m] 束花
    */
    fun check(bloomDay: IntArray, m: Int, k: Int, day: Int): Boolean {
        // 遍历的方式来

        // 非重叠且区间长度大于k的数量
        var sum = 0
        // 当前区间的长度
        var flowers = 0
        val len = bloomDay.size
        var i = 0
        while (i < len && sum < m) {
            if (bloomDay[i] <= day) {
                flowers++
                if (flowers == k) {
                    sum++
                    flowers = 0
                }
            } else {
                flowers = 0
            }
            i++
        }
        return sum >= m
    }
}
