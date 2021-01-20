// site: https://leetcode-cn.com/problems/maximum-product-of-three-numbers/

// 求一个数组中三数相乘的最大值

// 排序法，O(NlogN)
public class MaximumProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
        // 排序，如果是全负或者是全正，则是最大的三个数相乘
        // 如果有正有负，则可能是 三个最大的正数 或者是 两个最小的负数和最大的正数
        Arrays.sort(nums);
        int n = nums.length;
        // 三个最大的正数和三个负数都是排序后最大的
        int type1 = nums[n - 3] * nums[n - 2] * nums[n - 1];
        // 两个最小的负数和最大正数则分别在头和尾
        int type2 = nums[0] * nums[1] * nums[n - 1];
        return Math.max(type1, type2);
    }
}

// 直接把上面说的5个数找出来也可以，O(N)
// 记住这个分级判断的方法，比较巧妙，递推的感觉
class Solution {
    public int maximumProduct(int[] nums) {
        // 如果是全负或者是全正，则是最大的三个数相乘
        // 如果有正有负，则可能是 三个最大的正数 或者是 两个最小的负数和最大的正数

        // 直接把上述的5个数找出来
        int n = nums.length;
        // 最小的两个, min1 < min2
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        // 最大的三个, max1 > max2 > max3
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        // 遍历
        for(int num : nums){
            // 用if分级判断
            if(num < min1){
                min2 = min1;
                min1 = num;
            } else if(num < min2){
                min2 = num;
            }

            if(num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if(num > max2){
                max3 = max2;
                max2 = num;
            } else if(num > max3){
                max3 = num;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
