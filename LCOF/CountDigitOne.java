// site: https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/submissions/

// 待添加其他方法

// 递归做法
// f(n)表示从1到n中，1出现的次数
// 将数字拆分成两部分，1.最高位的数字hight + 2.剩余的部分last，求解之后相加
// 根据最高位是否为1分为两种情况

// 第一种
// 最高位为1，如1234，则high = 1，last = 234，pow = 1000
// 分为两个范围：1-999，1000-1234
// 1-999出现次数是f(999) = f(pow - 1)
// 1000-1234出现次数:(1)千位为1的：last + 1 = 234 + 1 (2)其他位为1的: f(234)
// 最后的结果则是 f(pow - 1) + last + 1 + f(last)

// 第二种
// 最高位不为1，如3234，则high = 3，last = 234，pow = 1000
// 分为四个范围：1-999，1000-1999, 2000-2999, 3000-3234
// 1-999、2000-2999出现次数均是f(999) = f(pow - 1)
// 1000-1999中1的出现次数分为两部分:(1)千位为1的:pow，(2)其他位的次数f(999) = f(pow - 1)
// 3000-3234出现次数:(1)千位为1的：last + 1 = 234 + 1 (2)其他位为1的: f(234)
// 最后的结果则是 high * f(pow - 1) + pow + f(last)

// 实现中可以用一个数组把f(pow - 1)的结果存起来，f(pow - 1)就不用算了，因为递归last的时候会用到多次的，而且也好求
class Solution {
    public int countDigitOne(int n) {
        // pow-1出现的1的次数，0,9,99,999,....
        // 即f(pow - 1)
        int[] powArr = new int[11];
        powArr[0] = 0;
        int temp = 1;
        for(int i = 1; i < 11; i++){
            powArr[i] = 10 * powArr[i - 1] + temp;
            temp *= 10;
        }
        // 递归求解
        return f(n, powArr);
    }
    /**
    * 递归函数
    */
    public int f(int n, int[] powArr){
        // 递归结束条件
        if(n <= 0){
            return 0;
        }
        // 先计算位数
        String val = String.valueOf(n);
        int len = val.length();
        int high = val.charAt(0) - '0';
        // 如4位数，pow = 1000 = 10^3
        int pow = (int)Math.pow(10,len - 1);
        int res = 0;
        // 最高位分类判断
        if(high == 1){
            // 最高位为1
            int last = n - pow;
            res = f(last, powArr) + powArr[len - 1] + last + 1;
        }
        else {
            int last = n - high * pow;
            res = f(last, powArr) + high * powArr[len - 1] + pow;
        }
        return res;
    }
}
