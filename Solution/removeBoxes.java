// site: 

// 消消乐（清除盒子）

// 和一般的dp指定边界不同，因为选取的位置不同会导致后序(l, r)并不是一个连续数组，所以考虑(l, r, k)
// 表示区间[l, r]加上j后面k个和ar相同的数字的最大值

// dp的选择，每组(l, r, k)都有两选择： 1. 把后面的 k + 1个消掉，再加上dp(l, r - 1, 0)，即dp(l, r - 1, 0) + (k + 1)^2
//                                    2. 对于[l, r - 1]中的每个位置i，且boxes[i] == boxes[r] = key，把[i + 1, r - 1]消掉，
//                                       再加上前面的[l, i]和后面k个key组成的k + 1个key，即dp(l, i, k + 1) + dp(i + 1, r - 1, 0)
// 详细见注释

class Solution {
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        int n = boxes.length;
        return calculate(boxes, dp, 0, n - 1, 0);
    }
    // dp[l][r][k]表示区间[l, r]加上j后面k个和ar相同的数字的最大值
    // 令ar = key
    public int calculate(int[] boxes, int[][][] dp, int l, int r, int k){
        // 递归结束条件
        if(l > r){
            return 0;
        }
        // 已经求解过了
        if(dp[l][r][k] != 0){
            return dp[l][r][k];
        }
        // r向左移动，找到和ar相同(key)的最左边的连续数字
        while(r > l && boxes[r] == boxes[r - 1]){
            r--;
            k++;
        }
        // 第一种选择：把后面的k + 1个ar全部消掉 = dp[l][r - 1][0] + (k + 1) ^ 2
        // 因为ar跟后面的k个数(key)相同
        dp[l][r][k] = (k + 1) * (k + 1) + calculate(boxes, dp, l, r - 1, 0);
        // 枚举分割点i
        for(int i = l; i < r; i++){
            // 如果在[l, r-1]中还有key
            if(boxes[i] == boxes[r]){
                // 把[i + 1, r - 1]消掉，加上(l, r, k + 1)的最大值，因为key多了一个所以是k+1
                dp[l][r][k] = Math.max(dp[l][r][k], calculate(boxes, dp, i + 1, r - 1, 0) + calculate(boxes, dp, l, i, k + 1));
            }
        }
        return dp[l][r][k];
    }
}
