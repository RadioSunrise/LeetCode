// site: https://leetcode-cn.com/problems/single-number-ii/

// single-number第二题，第一题中重复数字的出现次数为2，通过异或可以消掉，而这一题中是重复三次，不能直接异或了

// 位统计的方法，统计每个位1的出现次数，如果某个位1的出现次数模3为1，那么最终结果的二进制中，这个位也为1
// 详细见注释
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        // 逐位考虑的做法
        // 除了出现一次的数之外，其他数都出现了3次
        // 那个对于32位的二进制数，某一个位置上出现1的次数必然是3的倍数
        // 考虑单独出现的数字，如果这个数字的二进制上某一位为1
        // 那么这一位上出现的次数必然是3n + 1，即模3等于1
        // 所以32位每一位进行遍历，计算这个位1出现的个数模3，然后将这32个位合并成一个int
        for(int i = 0; i < 32; i++){
            int count = 0;
            for(int num : nums){
                // 逻辑右移，不要按符号补位
                count += (num >>> i) & 1;
            }
            if(count % 3 == 1){
                // 同或操作把1"放到"i的位置
                res = res | (1 << i);
            }
        }
        return res;
    }
}

// 直接位运算
// 待添加
