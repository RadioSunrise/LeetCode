// site: https://leetcode-cn.com/problems/UlBDOe/

// 叶子收藏集，动态规划做法

class Solution {
    public int minimumOperations(String leaves) {
        // 将r..ry...yr...r的前后的r作为状态0和2，中间的y作为1
        // dp 动态规划
        // dp[i][j]为指对数组[0:i]进行调整，且将位置i调整成状态j需要的最小次数

        // 转移方程：
        // 1.dp[i][0]，因为要将i变成0，所以第(i-1)只能等于0
        // dp[i][0] = dp[i - 1][0] + isYellow(i);
        // isYellow(k)指的是k是否为yellow，当k本身是黄色，isYellow是1，否则为0

        // 2.dp[i][1]，将i变成1，i要变成yellow，前一个位置可以是0也可以是1
        // dp[i][1] = {dp[i - 1][0], dp[i - 1][1]} + isRed(i)

        // 3.dp[i][2]，将i变成2，即变成最后的红色，前一个位置可以是1也可是2，但不能是0
        // 因为每个部分至少1个
        // dp[i][2] = {dp[i - 1][1], dp[i - 1][2]} + isYellow(i)

        int n = leaves.length();
        int[][] dp = new int[n][3];
        // base case
        // 唯一的base case是dp[0][0] = isYellow(0)，即第0号位置是不是黄色
        dp[0][0] = (leaves.charAt(0) == 'y') ? 1 : 0;
        // 第0个位置不可能是1或者2，第1个位置也不可能是2，定成最大值
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++){
            int isRed = (leaves.charAt(i) == 'r') ? 1 : 0;
            int isYellow = (isRed == 1) ? 0 : 1;
            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;
            if(i >= 2){
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }
        // 最后一个位置必定是状态2，所以返回dp[n - 1][2]
        return dp[n - 1][2];
    }
}
