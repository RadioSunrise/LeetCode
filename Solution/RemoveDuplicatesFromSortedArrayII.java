// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/

// 删除有序数组中的重复元素
// 双指针实现，一般化为：最多出现 k 次

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        // 快慢指针实现
        // 一般化为：最多出现 k 次
        // slow 指针用于和 slow - k 比较，指示存放的位置和返回长度
        // fast 指针用于遍历数组，即 nums[fast] 为待检查元素
        
        int k = 2;
        // 长度特判
        int n = nums.length;
        if(n <= k){
            return n;
        }
        // 快慢指针
        // 由于长度已经特判了，所以指针从 k 开始即可
        int slow = k;
        int fast = k;
        while(fast < n){
            // 比较当前写入位置的前 k 个位置的数值
            // 是否与待检查位置 fast 相同
            // 不相等即可以保留
            if(nums[slow - k] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            // 如果上述 if 不满足，则必有
            // nums[slow - k], nums[slow - k + 1], ..., nums[slow - 1] = nums[fast]

            // fast 前进 
            fast++;
        }
        // 最后 slow 即为返回的长度，因为 slow 放置完会 +1
        return slow;
    }
}
