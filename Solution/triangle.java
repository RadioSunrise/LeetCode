// site: https://leetcode-cn.com/problems/triangle/submissions/

// 求三角形的最短路径和

// 从上到下dp，结果返回的是最后一行的最小值

// 一维版dp
// 从上到下dp
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 一维DP
        int n = triangle.size();
        if(n == 0){
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++){
            // 使用一维的dp，j从i到0遍历
            // 因为每dp[j]需要考虑他左边的值，从右往左遍历选择不会对之后的结果造成影响
            // 如果从左往右遍历，左边的值改变了，下一个右边的值在选择的时候就会出错

            // 一行的最右边
            dp[i] = dp[i - 1]+ triangle.get(i).get(i);
            // 一行的中间
            for(int j = i-1; j > 0; j--){
                dp[j] = Math.min(dp[j-1], dp[j]) + triangle.get(i).get(j);
            }
            // 一行的最左边
            dp[0] = dp[0] + triangle.get(i).get(0);
        }
        int res = dp[0];
        for(int j = 1; j < n; j++){
            res = Math.min(res, dp[j]);
        }
        return res;
    }
}

// 自己写的
// 二维dp版 -- base，从上到下
// 每一行的最左和右都可以单独处理，中间for循环
// 可以减少if判断
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 二维DP
        int n = triangle.size();
        if(n == 0){
            return 0;
        }
        int[][] dp = new int[n][n];
        /*
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        */
        dp[0][0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++){
            // 一行的最左边
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            // 一行的中间
            for(int j = 1; j < i; j++){
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
            }
            // 一行的最右边
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int res = dp[n-1][0];
        for(int j = 1; j <= n-1; j++){
            res = Math.min(res, dp[n-1][j]);
        }
        return res;
    }
}

// 从底向上的dp方法
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 从底向上的dp方法
        // 二维dp不压缩
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        // base case
        Arrays.fill(dp[n], 0);

        for(int i = n - 1; i >=0; i--){
            for(int j = 0; j <= i; j++){
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
// 从底向上的dp方法 + 空间压缩
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 从底向上的dp方法
        // 一维dp
        int n = triangle.size();
        int[]dp = new int[n + 1];
        // base case
        Arrays.fill(dp, 0);
        for(int i = n - 1; i >=0; i--){
            // 和之前自顶向下不同，自底向上是考虑右边来选择左边，所以j是使左边开始的
            for(int j = 0; j <= i; j++){
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}

// DFS + 记忆化
// DFS从(0,0)调用，按照计算顺序是从底向上的
class Solution {
    // 备忘录辅助dfs
    Integer[][] memo;
    public int minimumTotal(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }
    // dfs搜索, d(i, j) = Min(d(i + 1, j), d(i + 1,j + 1)) + c[i][j]
    public int dfs(List<List<Integer>> triangle, int i, int j){
        if(i == triangle.size()){
            // 越界了，只用判断i就可以，因为j <= i
            return 0;
        }
        // memo中有记录
        // Integer型，初始值为null
        if(memo[i][j] != null){
            return memo[i][j];
        }
        else{
            return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
        }
    }
}
