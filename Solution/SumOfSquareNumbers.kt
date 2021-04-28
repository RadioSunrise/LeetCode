package Leetcode

import kotlin.math.sqrt

/**
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 * 给定整数 c，判断是否存在整数 a 和 b，c = a^2 + b^2
 */
class SumOfSquareNumbers {
    /**
     * 用 sqrt 函数帮忙
     */
    fun judgeSquareSumBySqrt(c: Int): Boolean {

        var a = 0L
        // 遍历到 sqrt(c) 就可以了
        while (a * a <= c) {
            // 计算一个double的 b
            val b: Double = sqrt((c - a * a).toDouble())
            if (b - b.toInt() == 0.0) {
                return true
            }
            a++
        }
        return false
    }

    /**
     * 双指针
     */
    fun judgeSquareSum(c: Int): Boolean {
        // 初始化 a = 0, b = sqrt(c)
        // 如果 1.a^2 + b^2 == c，满足
        //      2.a^2 + b^2 < c，a++
        //      3.a^2 + b^2 > c，b--
        // 直到 a 和 b 相遇

        // 可以等价成有序二维矩阵的搜索问题，从右上角开始找一个值
        // 当一个位置的值不等于target，此时可以排除比他小或者比它大的，矩阵上则为下移和左移
        // 即 a++ 或者 b--
        var a = 0L
        var b = sqrt(c.toDouble()).toLong()
        val cLong = c.toLong()
        while (a <= b) {
            val sum = a * a + b * b
            when {
                sum == cLong -> return true
                sum < cLong -> a++
                else -> b--
            }
        }
        return false
    }
}
