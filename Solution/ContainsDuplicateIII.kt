package Leetcode

import kotlin.math.abs

class ContainsDuplicateIII {

    fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
        // 用桶排序实现
        // 因为差距最大为 t, 即一个桶内元素最大相差 t, 则桶的大小为 t + 1
        // 如 t = 3，桶内可以放 1 2 3 4，共 4 = 3 + 1 个元素

        val bucketSize = t.toLong() + 1

        // 将每个元素 x 表示成 x = (t + 1) * a + b (0 <= b <= t) 的形式
        // 用 Map 实现，桶的个数最多 k 个，遍历 nums 的时候只看 [i - k, i] 的区间
        // 遍历过程中如果一个桶内存在两个元素，则必定满足条件
        // 桶内没有元素则考虑相邻的桶是否满足差值小于 t 的条件，相邻的桶内元素也可能满足条件
        // Map 的 put 操作有覆盖性，所以桶内最多只有一个元素 （超过一个就返回 true 结束了）

        val n = nums.size
        val map: MutableMap<Long, Long> = HashMap()
        for (i in 0 until n) {
            // 计算 id
            val id = getID(nums[i], bucketSize)
            // 考察桶内是否已经存在元素
            if (map.containsKey(id)){
                return true
            }
            // 考察相邻的桶
            if (map.containsKey(id - 1) && (Math.abs(nums[i] - map[id - 1]!!) < bucketSize)) {
                return true
            }
            if (map.containsKey(id + 1) && (Math.abs(nums[i] - map[id + 1]!!) < bucketSize)) {
                return true
            }
            map[id] = nums[i].toLong()
            // 遍历完当前位置后，清除 map 中 (i - k) 的桶，保持桶最多 k 个
            if (i >= k) {
                // 获取 (i - k) 的 ID 后再删掉
                map.remove(getID(nums[i - k], bucketSize))
            }
        }
        // 遍历完都没有就返回 false
        return false
    }

    /**
     * 计算 [value] 所属的桶的编号，桶的大小是 [bucketSize]
     */
    private fun getID(value: Int, bucketSize: Long): Long {
        return if (value >= 0) {
            value / bucketSize
        } else {
            (value + 1) / bucketSize - 1
        }
    }

}

/*
fun main() {
    val nums = intArrayOf(1,5,9,1,5,9)
    val k = 2
    val t = 3
    val res = ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, k, t)
    println(res)
}*/
