// https://leetcode-cn.com/problems/fibonacci-number/

// 斐波那契数列，用滚动数组降低空间复杂度
class Solution {
    public int fib(int n) {
        int f_n_2 = 0;
        int f_n_1 = 1;
        if(n <= 1){
            return n;
        }
        int res = 0;
        for(int i = 2; i <= n; i++){
            res = f_n_1 + f_n_2;
            f_n_2 = f_n_1;
            f_n_1 = res;
        }
        return res;
    }
}
