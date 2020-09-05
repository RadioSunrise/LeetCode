// site: https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/

// 动态规划dp来求，dp[i]表示第i个数字结尾的串有多少种方式
// 两种选择: (1)自己构成一个字符 (2)和前一个数字构成一个字符
// 细节是要判断当前数字是否能和前一个数字组成一个合法的0-25数字

// 优化逻辑之后的版本
// 为了可读性还是把dp表表示出来
class Solution {
    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        // 用两个变量来代替dp表
        // dp表的长度是dp[n + 1]
        // dp[i]表示以第i个数字结尾的串有多少种方式
        // 例如: 12258，第1个字符是'1'
        // dp[0]表示没有字符
        int n = numStr.length();
        if(n <= 1){
            return n;
        }
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            // 前1个字符和当前字符组合成一个字符串
            // 两个字符分别是(i - 2) 和 (i - 1)
            String temp = numStr.substring(i - 2, i);
            // 合法判断
            if(temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0){
                dp[i] += dp[i - 2];
            }
            dp[i] += dp[i - 1];
        }
        return dp[n];
    }
}

// 第一版，完整dp表，其实用两个变量压缩空间也是可以的，而且判断合法的方法也可以直接用String的compareTo方法来代替函数
class Solution {
    public int translateNum(int num) {
        // dp[i]表示第i个字符结尾的串有多少在方式
        // 转移方程
        // 两种选择，(1)自己构成一个字符，方法数为 dp[i - 1]
        //          (2)和前一个数字构成一个字符，但是要满足条件才可以 dp[i - 2]
        // dp[i] = dp[i - 1] + dp[i - 2] * indecator(Condiction)
        String numStr = String.valueOf(num);
        char[] numChar = numStr.toCharArray();
        int n = numChar.length;
        if(n <= 1){
            return n;
        }
        // dp 数组
        int[] dp = new int[n];
        // base case
        dp[0] = 1;
        dp[1] = 1;
        if(checkVaild(numChar[0], numChar[1])){
            dp[1]++;
        }
        // dp loop
        for(int i = 2; i <= n - 1; i++){
            // 单独一个字符
            dp[i] += dp[i - 1];
            // 和前面一个数组合成一个字符
            if(checkVaild(numChar[i - 1], numChar[i])){
                dp[i] += dp[i - 2];
            }
        }
        return dp[n - 1];
    }
    /**
    * 检查字符 a 和 b 构成的字符串 ab是不是合法的 0 - 25 的数字
    */
    public boolean checkVaild(char a, char b){
        if(a <= '0' || a > '2'){
            return false;
        }
        if(a == '2' && b >= '6'){
            return false;
        }
        return true;
    }
}

// 压缩版的
