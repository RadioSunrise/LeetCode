// site: https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof

// 把n右移的方法
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int n_temp = n;
        int count = 0;
        while(n_temp != 0){ // 用!= 0 做判断，不能用n>0，n可能为负
            count += n_temp & 1;
            n_temp = n_temp >>> 1; // >>>无符号右移 >>算术右移
        }
        return count;
    }
}

// 把掩码1左移的方法
public int hammingWeight(int n) {
    int bits = 0;
    int mask = 1;
    for (int i = 0; i < 32; i++) {
        if ((n & mask) != 0) {
            bits++;
        }
        mask <<= 1;
    }
    return bits;
}


// n & n-1 会把n的最后一个1变成0
public int hammingWeight(int n) {
    int sum = 0;
    while (n != 0) {
        sum++;
        n &= (n - 1);
    }
    return sum;
}
