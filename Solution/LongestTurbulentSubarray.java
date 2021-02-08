// site: https://leetcode-cn.com/problems/longest-turbulent-subarray/

// 湍流数组，dp或者是双指针都可以

// dp实现
public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] arr) {
        // 动态规划
        // 因为有 arr[i]可能处于上升或者下降，所以分两类讨论，二维dp数组
        // dp[i][0] 表示以arr[i]结尾，其arr[i] > arr[i-1]的最大长度
        // dp[i][1] 表示以arr[i]结尾，其arr[i] < arr[i-1]的最大长度

        // 转移方程
        // dp[i][0] 表示arr[i] > arr[i-1]的，如是湍流数组，则arr[i-1] < arr[i-2]
        // 所以dp[i][0] = dp[i-1][1] + 1
        // 反之，当arr[i] < arr[i-1]，dp[i][1] = dp[i-1][0] + 1

        // base case dp[0][1] = dp[0][0] = 1
        
        int n = arr.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int res = 1;
        for(int i = 1; i < n; i++){
            // 上升或者下降至少都是1，即自己
            dp[i][0] = 1;
            dp[i][1] = 1;
            // 状态转移
            // 只会更新1个
            if(arr[i] > arr[i - 1]){
                dp[i][0] = dp[i - 1][1] + 1;
            } else if(arr[i] < arr[i - 1]){
                dp[i][1] = dp[i - 1][0] + 1;
            }
            // 挑战最大值
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }
}

// 双指针实现2
class Solution2 {
    public int maxTurbulenceSize(int[] arr) {
        // 双指针实现2
        // 对于同一个left，最长湍流的长度是固定的
        // 左边界固定的时候，如果长度L的区间不是湍流，则再长的区间也不是会湍流
        // * 左边界向右移动时，最长的湍流子数组的右边界是单调不减的
        // 按照变化是加或者减来判断是否要增加长度
        int n = arr.length;
        // 不能等于2，有可能两个数相同，但是结果为1
        if(n < 2){
            return n;
        }
        int left = 0;
        // 初始化为1，向前看一个位置
        int right = 1;
        // true 表示 arr[i] > arr[i-1]，即上升
        // 上一次的变化
        boolean pre = false;
        int res = 1;
        while(right < n){
            boolean current = arr[right - 1] < arr[right];
            // 如果是第一步或者是变化相同，则left移动到right-1的位置
            if(right == 1 || current == pre){
                // 下一次判断变化是right和right-1的比较，
                // 所以下一次重新计算的时候，左边界是右边界的前一个位置
                left = right - 1;
            }
            // 两个元素相同，那么肯定要从right开始计算
            if(arr[right-1] == arr[right]){
                left = right;
            }
            right ++;
            // right上面加1了，所以长度是right - left
            res = Math.max(res, right - left);
            // 更新pre
            pre = current;
        }
        return res;
    }
}

// 双指针实现1
class Solution1 {
    public int maxTurbulenceSize(int[] arr) {
        // 双指针实现1

        // 如果区间[left, right]是湍流数组，当:
        // 1. arr[r+1] > arr[r]且arr[r] < arr[r-1]
        // 2. arr[r+1] < arr[r]其arr[r] > arr[r-1]
        // [left, right+1]也是湍流，否则对于任意的left < right，[left,right]都不是
        // 则需要使left = right，即left跳到right的位置，因为之前的位置都不行

        // 特殊情况是left = right 即区间为1的时候，如果arr[right] = arr[right+1]
        // 则需要一起移动，否则right移动即可 

        int n = arr.length;
        int left = 0;
        int right = 0;
        int res = 1;
        while(right < n - 1){
            if(left == right){
                if(arr[right] == arr[right + 1]){
                    // left也有一起移动
                    left++;
                }
                right++;
            }
            else {
                // 满足条件right
                if(right + 1 < n && arr[right] > arr[right + 1] && arr[right] > arr[right - 1]){
                    right++;
                } else if(right + 1 < n && arr[right] < arr[right + 1] && arr[right] < arr[right - 1]){
                    right++;
                } else {
                    left = right;
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
