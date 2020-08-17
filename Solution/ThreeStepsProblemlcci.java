// site: https://leetcode-cn.com/problems/three-steps-problem-lcci/

// 三步跳台阶，相当于f(n) = f(n - 1) + f(n - 2) + f(n - 3)

// 需要注意的是每当两个数相加都要可能会溢出，所以每两个数相加都要取模，三个数相加要取模两次
class Solution {
    public int waysToStep(int n) {
        int dp_n_3 = 1;
        int dp_n_2 = 1;
        int dp_n_1 = 2;
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int res = 1;
        int mod = 1000000007;
        for(int i = 3; i <= n; i++){
            // 只要两个数相加就有可能溢出，所以要取模两次（每当有两个数相加都要取模）
            res = (dp_n_1 + (dp_n_2 + dp_n_3) % mod) % mod;
            dp_n_3 = dp_n_2;
            dp_n_2 = dp_n_1;
            dp_n_1 = res;
        }
        return res;
    }
}
