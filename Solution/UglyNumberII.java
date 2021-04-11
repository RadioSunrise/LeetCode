// site: https://leetcode-cn.com/problems/ugly-number-ii/solution/

// 找到第 n 个丑数

class Solution {
    public int nthUglyNumber(int n) {
        // 特判
        if(n <= 5){
            return n;
        }
        // 丑数都是由小的丑数乘以 2 / 3 / 5 得到的
        // 动态规划实现
        // dp[i] 表示第 i + 1 个丑数
        int[] dp = new int[n];
        dp[0] = 1;
        // 转移方程
        // 设定三个指针 pos2, pos3, pos5
        // dp[posJ] 表示下一个与 J 相乘的丑数，三个新的丑数进行比较，选最小的
        int pos2 = 0;
        int pos3 = 0;
        int pos5 = 0;
        for(int i = 1; i < n; i++){
            int num2 = dp[pos2] * 2;
            int num3 = dp[pos3] * 3;
            int num5 = dp[pos3] * 5;
            // 选最小的
            dp[i] = Math.min(Math.min(num2, num3), num5);
            // 通过比较 dp[i] 和三个数的大小，确认选了哪一个
            // 可以多个相等的
            // pos 移动表示这个 pos 位置的丑数已经乘过了，下一次将考虑后面的丑数
            if(dp[i] == num2){
                pos2++;
            }
            if(dp[i] == num3){
                pos3++;
            }
            if(dp[i] == num5){
                pos5++;
            }
        }
        return dp[n - 1];
    }
}
