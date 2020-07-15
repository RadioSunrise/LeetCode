//site: https://leetcode-cn.com/problems/unique-binary-search-trees/

// 动态规划
// 相当于给定了BST的中序遍历，那么可以按照不同的位置进行树根的选择
// 当根选好了，以这个点为根的树的个数等于 左边 * 右边
// dp[i] 表示 i 长度有多少种不同的树
// dp[i] 与内容无关，只和长度 i 有关
// dp[i] = dp[0] * dp[i-1] + dp[1]*dp[i-2] + ... + dp[i]*dp[0]
// base case: dp[0] = 0, dp[1] = 1;
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = 0;
            for(int j = 0; j <= i - 1; j++){
                dp[i]+= dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
