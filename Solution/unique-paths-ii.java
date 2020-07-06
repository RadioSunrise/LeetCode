//site: https://leetcode-cn.com/problems/unique-paths-ii/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null){
            return 1;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(n == 0){
            return 1;
        }
        // System.out.println("m is " + m + ", and n is " + n);
        int[][] dp = new int[m][n];
        // base case
        if(obstacleGrid[0][0] == 1){ // 障碍物在开始的位置
            dp[0][0] = 0;
        }
        else{
            dp[0][0] = 1;
        }
        for(int i = 1; i < m; i++)
        {
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }
            else{
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int j = 1; j < n; j++){
            if(obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }
            else{
                dp[0][j] = dp[0][j-1];
            }
        }
        // convert
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }
                else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}

// 关键：dp数组的意义是走到[i][j]位置的走法，因为能向右和向下，所以当这个点不是障碍物的时候，dp[i][j] = dp[i-1][j](从上面来) + dp[i][j-1](从左边来)
