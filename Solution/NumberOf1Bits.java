// site: https://leetcode-cn.com/problems/number-of-1-bits/

// 输入一个n，计算n的二进制中1的个数

// 第一种：右移 + 与1 实现
// 注意右移要逻辑右移，因为 Java 用补码存负数，高位是1，算术右移会补1，不会等于0
public class NumberOf1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // 右移判断最后一位
        int N = n;
        int count = 0;
        while(N != 0){
            if((N & 1) == 1){
                count++;
            }
            N = N >>> 1;
        }
        return count;
    }
}

// 第二种：消除1
// n & (n-1)会消除最后一个1，直到消除完即可
class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // 右移判断最后一位
        int N = n;
        int count = 0;
        while(N != 0){
            N = N & (N - 1);
            count++;
        }
        return count;
    }
}

// 第三种：和mask作与运算，跟第一种的本质是一样的，但是N不动，mask来动
// 因为是32位，所以32次即可
class Solution2 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // 右移判断最后一位
        int N = n;
        int count = 0;
        for(int i = 0; i < 32; i++){
            // mask 左移，即 1 左移 i 位
            int k = 1 << i;
            // 不是等于1， 而是非0，因为位置不同
            if((n & k) != 0){
                count++;
            }
        }
        return count;
    }
}
