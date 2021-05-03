// site: https://leetcode-cn.com/problems/reverse-integer/submissions/

// reference: https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/

class Solution {
    fun reverse(x: Int): Int {
        // 每次取末尾，移位累加
        // 需要注意溢出问题
        var res: Int = 0
        var X = x
        while (X != 0) {
            // 末尾
            val temp = X % 10

            // 判断溢出 是否大于最大整型
            // 最大的整数是 2147483648
            // 1.如果当前的 res > 214748364，则末尾加上之后一定溢出
            // 2.如果当前的 res = 214748364，则末尾需要小于等于 7
            if (res > 214748364 || (res == 214748364 && temp > 7)) {
                return 0
            }

            // 判断溢出，小于最小整型
            // 最小的整数是 -2147483648
            // 1.如果当前的res < -214748364，加上末尾一定更小溢出
            // 2.如果当前的res == -214748364，则末尾需要大于 -8
            if (res < -214748364 || (res == 214748364 && temp < -8)) {
                return 0
            }

            res = res * 10 + temp
            X = (X / 10)
        }
        return res
    }
}
