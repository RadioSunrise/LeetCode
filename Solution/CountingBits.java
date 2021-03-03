// site: https://leetcode-cn.com/problems/counting-bits/

// 给定一个整数num，计算 0 <= i <= num 中二进制的0的个数

// 动态规划法
// 1. 最高有效位
public class CountingBits {
    public int[] countBits(int num) {
        // 动态规划
        // 最高有效位
        // 二次幂的整数只有1个1，对于正整数x，如果找到最大的整数y，满足 y <= x
        // 且y为二次幂，则 z = x - y 的二进制中的1比x的1少1个
        // 即 bit[x] = bit[z] + 1
        // 当 x & (x - 1) = 0 时，表示x只有1个1，即为二次幂
        // 遍历0到num，若i为二次幂则最高有效位highBit = i
        // 其余数 bit[i] = bit[i - highBit] + 1
        // 之前的数都计算好了
        int[] res = new int[num + 1];
        int highBit = 0;
        for(int i = 1; i <= num; i++){
            if((i & (i - 1)) == 0){
                highBit = i;
            }
            res[i] = res[i - highBit] + 1;
        }
        return res;
    }
}

// 2.最低有效位
class Solution {
    public int[] countBits(int num) {
        // 动态规划
        // 最低有效位
        // x右移一位会去掉二进制的最后一位，得到 x/2 下取整
        // 如果 x 为奇数，则 bit[x] = bit[x/2] + 1
        // x 为 偶数，则 bit[x] = bit[x/2]
        int[] res = new int[num + 1];
        for(int i = 1; i <= num; i++){
            res[i] = res[(i >> 1)] + (i & 1);
        }
        return res;
    }
}

// 3.最低设置位
class Solution {
    public int[] countBits(int num) {
        // 动态规划
        // 最低设置位
        // 最低设置位是 x 的二进制表示中的最低的 1 所在位
        // 如 x = 10 = 1010(2)，则最低设置位为2，对应10(2)
        // y = x & (x-1) 后，会将最后一个1变成0，则y为x将最低设置位变成0之后的数
        // y < x 且 bit[x] = bit[y] + 1
        int[] res = new int[num + 1];
        for(int i = 1; i <= num; i++){
            res[i] = res[i & (i-1)] + 1;
        }
        return res;
    }
}


// 逐个计数法
// O(k * n)
class Solution {
    public int[] countBits(int num) {
        // 逐个计算法
        // 比较慢
        // x & (x - 1) 可以把 x 的最后一个 1 变成 0
        // 统计次数即可
        int[] res = new int[num + 1];
        for(int i = 0; i <= num; i++){
            int x = i;
            int k = 0;
            while(x != 0){
                x &= (x - 1);
                k++;
            }
            res[i] = k;
        }
        return res;
    }
}
