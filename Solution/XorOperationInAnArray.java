// site: https://leetcode-cn.com/problems/xor-operation-in-an-array/

// 循环异或一个数组

class Solution {
    public int xorOperation(int n, int start) {
        int result = start;
        for(int i = 1; i < n; i++)
        {
            result = result ^ (start + 2*i);
        }
        return result;
    }
}
