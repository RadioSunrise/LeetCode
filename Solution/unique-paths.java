// site: https://leetcode-cn.com/problems/unique-paths/

// dp[i][j]表示走到(i,j)位置有多少种路径
// 找不同路径，转移方程：dp[i][j] = dp[i + 1][j] + dp[i][j - 1]
// 二维dp
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        // 第一行
        for(int j = 0; j < m; j++){
            dp[0][j] = 1;
        }
        // 第一列
        for(int i = 0; i < n; i++){
            dp[i][0] = 1;
        }
        // 左上到右下遍历
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }
}

// 一维dp
// 从上面的转义方程来看，只需要上一行和本行的数据，所以可以用O(2n)空间代替O(n^2)
class Solution {
    public int uniquePaths(int m, int n) {
        // 保存上一行数据
        int[] preRow = new int[n];
        // 保存当前行数据
        int[] currRow = new int[n];
        // base case
        Arrays.fill(preRow, 1);
        Arrays.fill(currRow, 1);
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                currRow[j] = currRow[j-1] + preRow[j];
            }
            // 用curr覆盖pre
            // 用clone复制
            preRow = currRow.clone();
        }
        return currRow[n-1];
    }
}

// 空间再优化
// cur[j] += cur[j-1], 即cur[j] = cur[j] + cur[j-1] 等价于-->> cur[j] = pre[j] + cur[j-1]
class Solution {
    public int uniquePaths(int m, int n) {
        // 再优化，保存当前行数据
        int[] currRow = new int[n];
        // base case
        Arrays.fill(currRow, 1);
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                // currRow[j]的原值就是preRow[j]，所以也可以节省空间
                currRow[j] += currRow[j-1];
            }
        }
        return currRow[n-1];
    }
}
