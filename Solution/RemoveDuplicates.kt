package Leetcode

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * 排序数组中删除重复元素，扩展到最多重复出现 k 次（本题为 1 次）
 */
class RemoveDuplicates {
    fun removeDuplicates(nums: IntArray): Int {
        // 双指针实现，将问题一般化为最多出现 k 次
        // 本题即为最多出现 1 次的结果
        return removeDuplicatesK(nums, 1)
    }

    fun removeDuplicatesK(nums: IntArray, k: Int): Int {
        // 快慢双指针实现
        // 慢指针 slow 指向要存数字的位置
        // 快指针 fast 用于遍历数组
        // 用 nums[fast] 和 nums[slow - k] 比较看是否相同，不同即可以将nums[fast] 存放在 slow的位置
        // 因为 slow 是要存放的位置，要和之前已将存放过[slow - k]的比较

        val n = nums.size
        // 长度判断，如果长度小于 k 则必定满足
        if (n <= k) {
            return n
        }
        // 因为长度判断过了，所以从 k 开始
        var slow = k
        var fast = k
        while (fast < n) {
            // 比较 nums[fast] 和 nums[slow - k]，不相等则可以存放在 slow
            if (nums[slow - k] != nums[fast]) {
                nums[slow] = nums[fast]
                slow++
            }
            // 如果上述 if 不满足，则说明 nums[slow - k], nums[slow - k + 1], ... nums[slow - 1] = nums[fast]
            // 就已经出现 k 次了，不能再放了
            fast++
        }
        // slow 就是放置完的长度，因此存放一个数后 slow 会 +1
        return slow
    }
}