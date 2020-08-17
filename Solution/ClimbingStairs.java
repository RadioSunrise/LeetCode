// site: https://leetcode-cn.com/problems/climbing-stairs/

// 爬楼梯，和求斐波那契数列是一样的

// 动态规划法，滚动数组
// class Solution {
    public int climbStairs(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int dp_n_2 = 1;
        int dp_n_1 = 1;
        int res = 1;
        for(int i = 2; i <= n; i++){
            res = dp_n_1 + dp_n_2;
            dp_n_2 = dp_n_1;
            dp_n_1 = res;
        }
        return res;
    }
}

// 数学法
// 直接用斐波那契数列的递推式求解
class Solution {
    public int climbStairs(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        double sqrt5 = Math.sqrt(5);
        double temp = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int)(temp / sqrt5);
    }
}
