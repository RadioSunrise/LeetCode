// site: https://leetcode-cn.com/problems/powx-n/

// 实现pow函数，用幂等法减少计算，每次将幂降一半
// 迭代版本的
// 和递归不同，写递归的时候自顶向下地分，所以根据N的奇偶性容易判断需不需要多乘一个x
// 而迭代的做法中，是自底向上的，不知道需不需要乘，所以通过1个数的二进制拆分来做
// 推导见下方注释，细节：n转成long型放置溢出
class Solution {
    public double myPow(double x, int n) {
        // 用幂等法
        long N = n;
        if(n == 0 || x == 1.0){
            return 1.0;
        }
        if(x == 0.0){
            return 0;
        }
        // 当n小于0，转为求(1/x)^N
        if(N < 0){
            N = -N;
            x = 1 / x;
        }

        // 用二进制拆分的方式将n拆分成
        // n = (2^0 * b1 + 2^1 * b2 + ... + 2^(m-1) * bm)
        // n的二进制表示为 bm bm-1 ... b2 b1，则有
        // x^n = x ^ (2^0 * b1 + 2^1 * b2 + ... + 2^(m-1) * bm)
        //     = (x ^ (2^0 * b1)) * (x ^ (2^1 * b2)) * ... * (x ^ (2^m-1 * bm))
        // 迭代过程中计算 x 的 2次幂次方：x, x^2, x^4, ... 只要x更新成x^2就可以
        // 每个位置bm可以通过&1或者%2的方式取到最后1位，再用n移位或者n/2的方式移动
        double res = 1.0;
        while(N > 0){
            // 获取最后1位，是1就让res乘上对应的x的2的幂次方
            if((N & 1) == 1){
                res *=  x;
            }
            // 更新x，变成x^2
            x = x * x;
            // 逻辑右移去掉最后一位
            N = N >>> 1;
        }
        return res;
    }
}

// 递归版本的
class Solution {
    public double myPow(double x, int n) {
        // 用幂等法
        long N = n;
        if(n == 0 || x == 1.0){
            return 1.0;
        }
        if(N < 0){
            return 1 / myMul(x, -N);
        }
        else{
            return myMul(x, N);
        }
    }
    // N用long型
    public double myMul(double x, long N){
        // 递归结束条件
        if(N == 0){
            // double型，返回1.0
            return 1.0;
        }
        double y = myMul(x, N / 2);
        // 判断N的奇偶，因为每次把N/2下取整，所以当N为奇数时，结果再乘一个x
        if(N % 2 == 0){
            return y * y;
        }
        else{
            return y * y * x;
        }
    }
}
