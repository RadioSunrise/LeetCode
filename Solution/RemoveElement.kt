// site: https://leetcode-cn.com/problems/remove-element/submissions/

class RemoveElement {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        // 双指针扫描
        // for in 循环也可以的
        var index = 0
        for (num in nums) {
            if (num != `val`) {
                nums[index++] = num
            }
        }
        return index
    }
}
