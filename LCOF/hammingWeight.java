// site: https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof

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
