https://leetcode-cn.com/problems/sqrtx/

// 牛顿迭代法
class Solution {
    public int mySqrt(int x) {
        if(x == 0){
            return 0;
        }
        // 牛顿迭代法
        long n = x;
        while(n * n > x){
            n = (n + x / n) / 2;
        }
        return (int)(n);
    }
}

// 求解平方根，用二分查找可以降到O(log N)
// 注意边界值的选取，而且这题中mid需要上取整

class Solution {
    public int mySqrt(int x) {
        if(x == 0){
            return 0;
        }
        // 二分查找
        // 下界为 1，上界为 n^2 / 2 即 x / 2
        long left = 1;
        long right = x / 2;
        // 结束条件left = right
        while(left < right){
            // 用上取整，因为最后返回的是一个舍去小数部分的平方根
            // 所以当mid * mid < x 的时候 left 的操作应该是 left = mid
            // 而不是 left = mid + 1, 这样会将正确结果排除到搜索区间外
            // long mid = left + ((right - left + 1)/2);
            
            // 或者这样写也可以
            long mid = (left + right + 1) >>> 1;
            
            // mid 太大了，mid肯定不是解
            if(mid * mid > x){
                // 下一次搜索区间[left, mid - 1]
                right = mid - 1;
            }
            // mid不够大或者平方根下取整
            else {
                // 下一次的搜区间[mid, right]
                // 因为收缩操作是left = mid，所以mid要上取整
                left = mid;
            }
        }
        return (int)left;
    }
}
