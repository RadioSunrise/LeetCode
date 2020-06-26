// site: https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/

class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}
// substring的两种两种用法  1：s.substring(n)，单参数返回从n开始到末尾的substring（包括n）；
//                          2：s.substring(a, b), 单参数返回从a开始到b的substring（包括a不包括b）；
