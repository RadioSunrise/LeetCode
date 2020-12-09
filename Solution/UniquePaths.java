// site: https://leetcode-cn.com/problems/unique-paths/

// 走格子的组合问题，动态规划或者组合数学法

class Solution {
    public int uniquePaths(int m, int n) {
        // 二维dp，dp[i][j]表示从(0, 0)位置到(i, j)位置有多少种走法
        int[][] dp =  new int[m][n];
        // 转移方程
        // dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        dp[0][0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 第一行，只能从左边
                if(i == 0 && j > 0){
                    dp[0][j] = dp[0][j - 1];
                }
                // 第一列，只能从上面
                else if(j == 0 && i > 0){
                    dp[i][0] = dp[i - 1][0];
                }
                // 其他位置
                else if(i > 0 && j > 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

class Solution {
    public int uniquePaths(int m, int n) {
        // 组合数学法
        // 总共(m + n - 2)步，其中(m - 1)步向下，剩下的(n - 1)向右
        // ans = C(m - 1)(m + n - 2) = [(m + n - 2) * (m + n - 3) * ... * n] / [(m - 1)!]
        
        // 用连续乘法和循环来求这个数
        // 可以交换两个数，让m是较小的一个
        if(m < n){
            int temp = m;
            m = n;
            n = temp;
        }
        long ans = 1;
        for(int x = n, y = 1; y < m; x++, y++){
            // x 从 n 到 (m + n - 2)，y 从 1 到 (m - 1)
            ans = ans * x / y;
        }
        return (int) ans;
    }
}
