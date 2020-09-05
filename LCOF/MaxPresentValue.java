// https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/submissions/

// 经典的dp问题

// 二维dp
// 第一行第一列单独处理
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // base case
        dp[0][0] = grid[0][0];
        for(int j = 1; j < n; j++){
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}

// 二维dp表做法
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // base case
        dp[0][0] = grid[0][0];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                }
                // 第一行
                else if(i == 0){
                    // 左边选
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
                // 第一列
                else if(j == 0){
                    // 上面选
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
                // 其余位置
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

// 一维dp
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        int[] dp = new int[n];
        // base case
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    dp[j] = grid[i][j];
                }
                // 第一行
                else if(i == 0){
                    // 左边选
                    dp[j] = dp[j - 1] + grid[i][j];
                }
                // 第一列
                else if(j == 0){
                    // 上面选
                    dp[j] = dp[j] + grid[i][j];
                }
                // 其余位置
                else {
                    dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }
}
