// site: https://leetcode-cn.com/problems/integer-break/


// 整数分割，跟剑指offer的14一样的，自己的写法是动态规划

// 枚举一段中的分割点j，剩下的长度i-j可以考虑用拆分dp[i-j]还是直接用i-j
// 其实当i > 5的时候，dp[i - j] > i - j
// 虽然结果是对的，但是没有利用到数学的特性
class Solution {
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        // 构造dp
        for(int i = 2; i <= n; i++){
            // 枚举分割点
            for(int j = 1; j < i; j++){
                // 每一段可以选择继续分解或者不分解
                // 就是dp[i-j]和i-j哪个大
                // 然后比较最大值
                dp[i] = Math.max(dp[i], Math.max(dp[i - j], i - j) * j);
            }
        }
        return dp[n];
    }
}

// 将n分割成尽量多的3和2，最后的结果一定是最大的
// 3是最优拆分数字，原因可以看：https://leetcode-cn.com/problems/integer-break/solution/343-zheng-shu-chai-fen-tan-xin-by-jyd/
// 所以将n拆成x个3，剩余的部分即y = n % 3会是0/1/2，再分类讨论:
// 1.y = 0: ans = pow(3,x)
// 2.y = 1: 将最后的一个3和剩余的1组成4，分成2和2
// 3.y = 2: ans = pow(3,x) * 2，2不用拆，因为2 > f(2)=1
class Solution {
    public int integerBreak(int n) {
        if(n == 2){
            return 1;
        }
        if(n == 3){
            return 2;
        }
        int x = n / 3;
        int y = n % 3;
        if(y == 0){
            return (int)Math.pow(3, x);
        }
        else if(y == 1){
            // 最后的3和剩余的1组合在一起写成2*2
            return (int)Math.pow(3, x - 1) * 4;
        }
        else {
            // y == 2
            return (int)Math.pow(3, x) * 2;
        }
    }
}
