// site: https://leetcode-cn.com/problems/reverse-bits/

// 颠倒二进制位，考察位运算

public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // n 向右移位，每次考虑最右边的 1 位
        // 这一位在答案 res 中需要左移相应的位数
        // 还需要加上之前的结果
        int res = 0;
        // 共 32 位
        for(int i = 0; i < 32; i++){
            // res 左移，加上当前 n 的最后一位
            res = res << 1;
            res += (n & 1);
            // n 右移
            n = n >>> 1;
        }
        return res;
    }
}

class Solution2 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // n 向右移位，每次考虑最右边的 1 位
        // 这一位在答案 res 中需要左移相应的位数
        // 还需要加上之前的结果
        int res = 0;
        // 共 32 位
        for(int i = 0; i < 32; i++){
            // res 不动，用或实现也可以
            // n 的末位移动到合适的位置
            res = res | ((n & 1) << (31 - i));
            n = n >>> 1;
        }
        return res;
    }
}

// 分治法
// reference: https://leetcode-cn.com/problems/reverse-bits/solution/dian-dao-er-jin-zhi-wei-by-leetcode-solu-yhxz/
