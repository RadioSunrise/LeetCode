package com.example.networktest

/**
 * https://leetcode-cn.com/problems/integer-to-roman/
 * 贪心算法实现
 */
class IntegerToRoman {
    fun intToRoman(num: Int): String {
        // 包含 900、400 等
        val values = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        val symbols = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        // 贪心实现
        val res = StringBuilder()
        // 下标遍历用 Arrays.indices
        var numTemp = num
        for (i in values.indices) {
            val value = values[i]
            val symbol = symbols[i]
            while (numTemp >= value) {
                res.append(symbol)
                numTemp -= value
            }
            if (numTemp == 0) {
                break
            }
        }
        return res.toString()
    }
}