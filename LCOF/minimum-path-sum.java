// site: https://leetcode-cn.com/problems/minimum-path-sum/

// 从左上到右下，原地修改grid矩阵作为dp表
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        // grid原地修改
        // 左上角开始
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 左上角
                if(i == 0 && j == 0){
                    continue;
                }
                // 第一行，只能考虑从左边来
                else if(i == 0){
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                }
                // 第一列，只能考虑从上边来
                else if(j == 0){
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                }
                // 其余情况
                else{
                    grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }
}

// 右下到左上dp，一维写法
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        int[] dp = new int[n];
        // 右下角开始
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                // 右下角
                if(i == m - 1 && j == n - 1){
                    dp[j] = grid[i][j];
                }
                // 最后一行，只能考虑右边
                else if(i == m - 1){
                    dp[j] = grid[i][j] + dp[j+1];
                }
                // 最后一列，只能考虑下边
                else if(j == n - 1){
                    dp[j] = grid[i][j] + dp[j];
                }
                // 其余情况
                else{
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j+1]);
                }
            }
        }
        return dp[0];
    }
}

// 右下到左上dp，二维写法
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // 右下角开始
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                // 右下角
                if(i == m - 1 && j == n - 1){
                    dp[i][j] = grid[i][j];
                }
                // 最后一行，只能考虑右边
                else if(i == m - 1){
                    dp[i][j] = grid[i][j] + dp[i][j+1];
                }
                // 最后一列，只能考虑下边
                else if(j == n - 1){
                    dp[i][j] = grid[i][j] + dp[i+1][j];
                }
                // 其余情况
                else{
                    dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }
}
