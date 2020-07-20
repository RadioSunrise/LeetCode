// site: https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/

// 有序数组的两数之和问题，因为数组已经有序了，所以和Two sum是不同的
// 利用双指针法可以O(n)解决，是因为通过双指针的移动可以排除一些情况
// 双指针求解这道题的本质思想是缩减搜索空间
// 完整的搜索空间是O(n^2)矩阵的倒三角部分，而nums[i] + nums[j] 和 target的大小关系可以排除一些搜索空间
// 1.当nums[i]+nums[j] < target的时候，说明不够大，那么需要增大现在的数组，所以(i,0), (i,1), ... ,(i,j-1)都是不可能的
// 也就是说i和所以小于j位置的组合都不可能，所以排除这一系列的i，对应的操作就是是i++
// 2.当nums[i]+nums[j] > target的时候，说明太大了，所以(i+1,j), .... ,(n,j)都是不可能的
// 所以这个j和其他i都是不可能满足情况的，因此用j--排除这一系列j

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int left = 0;
        int right = numbers.length - 1;
        // int right = findRightPos(numbers, target);
        while(left < right) {
            if(numbers[left] + numbers[right] ==  target){
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            }
            else if (numbers[left] + numbers[right] < target)
            {
                left++;
            }
            else if (numbers[left] + numbers[right] > target)
            {
                right--;
            }
        }
        return result;
    }
    public int findRightPos(int[] numbers, int target) {
        int len = numbers.length;
        int left = 0;
        int right = len;
        // 左闭右开，找到第一个比target小的值
        // 数据都大于0才行的
        while(left < right){
            int mid = left + (right - left) / 2;
            if(numbers[mid] > target) {
                right = mid;
            }
            else if(numbers[mid] <= target) {
                left = mid + 1;
            }
        }
        // 最后left == right
        return left - 1;
    }
}
