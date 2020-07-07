// site: https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof

// 用(a ^ b)异或表示加 ，用(a & b) << 1与完再进位表示进位
// 直到进位为0不需要再做为止
class Solution {
    public int add(int a, int b) {
        // 加法 + 进位
        int n = 1;
        int carry = -1;
        while(carry != 0){
            n = a ^ b;
            carry = (a & b) << 1;
            a = n;
            b = carry;
        }
        return n;
    }
}
