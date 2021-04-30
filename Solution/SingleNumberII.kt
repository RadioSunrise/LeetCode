// site: https://leetcode-cn.com/problems/single-number-ii/

class SingleNumberII {
    fun singleNumber(nums: IntArray): Int {
        // 位运算
        // 对于答案以外的数字，这些数字出现了3次，那么在二进制位上
        // 1 出现的次数是3的倍数
        // 考虑出现1次的答案，二进制上的某个位置为1，则该位置出现1的次数是3n+1
        // 因此对32位的每一位 i 进行遍历，计算位置 i 上 1 出现的次数再模3，和并成一个int型整数

        var res = 0
        for (i in (0 until 32)) {
            var count = 0
            for (num in nums) {
                count += (num.ushr(i).and(1))
            }
            if (count % 3 == 1) {
                // 用或操作，因为res初始化为0
                res = res.or(1.shl(i))
            }
        }
        return res
    }
}
