package Leetcode

import java.util.*
import kotlin.collections.ArrayList

/**
 * https://leetcode-cn.com/problems/largest-divisible-subset/
 */
class LargestDivisibleSubset {
    /**
     * 整除子集中任意元素都具有整除关系，考虑一个整除子集 S1
     * 1.如果 a 是 S1 最小元素 b 的约数，则 a 可以加进去
     * 2.如果 d 是 S1 最大元素 c 的倍数，则 d 可以加进去
     * 把集合的大小看做状态，上述情况可以构成 dp 的状态转移方程
     */
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        // dp[i] 表示以 nums[i] 为最大整数的整除子集的大小，nums[i] 必须被选择
        // 先将数组排序，方便得到子集的最大或者最小元素
        nums.sort()

        // 对于每个位置i, 判断nums[i]能不能接在nums[j]后面的条件是nums[i] % nums[j] == 0
        // 因为每个i可能会接在多个j后面，选取一个最大的
        // 此外，由于需要返回具体的集合，因此要通过 g[] 数组记录当前的i接在哪个j的后面
        val n = nums.size
        // dp 初始化为1，最起码1个
        val dp = IntArray(n)
        val g = IntArray(n)
        // max 记录全局最大的长度，idx记录最大长度集合中最大元素的位置
        var max = 0
        var idx = -1
        for (i in 0 until n) {
            // len 记录i能接上的j的最长长度，len = 1表示至少一个，prev记录转移长度
            var len = 1
            var prev = i
            for (j in 0 until i) {
                if (nums[i] % nums[j] == 0) {
                    // 能够接在更长的序列后面，则更新 最大长度 和 转移位置
                    if (dp[j] + 1 > len) {
                        len = dp[j] + 1
                        prev = j
                    }
                }
            }
            // 记录 i 的两个信息
            dp[i] = len
            g[i] = prev
            if (dp[i] > max) {
                max = dp[i]
                idx = i
            }
        }
        // 根据g、max、idx构造结果集合
        val res = ArrayList<Int>()
        while(res.size < max) {
            res.add(nums[idx])
            // 寻找上一个位置，类似递归
            idx = g[idx]
        }
        return res
    }
}