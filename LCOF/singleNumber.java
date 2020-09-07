// 只有一个数出现了1次，其他数都出现了m次，找出这个数

// 通过统计二进制位上的1来计算

class Solution {
    public int singleNumber(int[] nums) {
        // 统计32位整型上，每个位置出现1的个数，最后 mod 3
        int bit = 32;
        // int 型32位
        int[] count = new int[bit];
        // 统计每个位置1的个数
        // 通过不断右移再和1与
        for(int num : nums){
            for(int i = 0; i < bit; i++){
                count[i] += (num & 1);
                // 逻辑右移，前面补0
                num >>>= 1;
            }
        }
        // 根据count数组重建目标数
        int res = 0, m = 3;
        for(int i = 0; i < bit; i++){
            // 左移
            res <<= 1;
            // 或
            res |= (count[bit - 1 - i] % m);
        }
        return res;
    }
}

// 单独生成32个mask，好像比较慢
class Solution {
    public int singleNumber(int[] nums) {
        // 统计32位整型上，每个位置出现1的个数，最后 mod 3 (mod m)
        int bit = 32;
        // int 型32位
        int[] count = new int[bit];
        // 生成32个mask
        int[] mask = new int[bit];
        mask[0] = 1;
        for(int i = 1; i < bit; i++){
            mask[i] = mask[i - 1] << 1;
        }
        // 统计每个位置1的个数
        for(int num : nums){
            for(int i = 0; i < bit; i++){
                if((num & mask[i]) != 0){
                    count[i]++;
                }
            }
        }
        // 根据count数组重建目标数
        int res = 0, m = 3;
        for(int i = 0; i < bit; i++){
            // 左移
            res <<= 1;
            // 或
            res |= (count[bit - 1 - i] % m);
        }
        return res;
    }
}
