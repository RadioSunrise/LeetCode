// site: https://leetcode-cn.com/problems/is-subsequence/

// 双指针法
class Solution {
    public boolean isSubsequence(String s, String t) {
        // 双指针
        int pointerT = 0;
        int pointerS = 0;
        int lenT = t.length();
        int lenS = s.length();
        // 特判
        if(lenS > lenT){
            return false;
        }
        // 结束条件是其中一个指针走到末尾
        while(pointerS < lenS && pointerT < lenT){
            // 如果字符相等，两个指针都前进
            if (s.charAt(pointerS) == t.charAt(pointerT)){
                pointerT++;
                pointerS++;
            }
            // 不相等，T指针前进
            else {
                pointerT++;
            }
        }
        // 如果是pointerS已经走完
        if(pointerS == lenS){
            return true;
        }
        // pointerT走完但是pointerS没走完
        // 如果是pointerS已经走完
        if(pointerS == lenS){
            return true;
        }
        return false;
    }
}

// 动态规划
// 这道题上比双指针慢，也复杂
class Solution {
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen == 0){
            return true;
        }
        if(sLen > tLen){
            return false;
        }
        // 动态规划
        // dp[i][j]表示s的前i个字符 是不是 t的前j个字符的子序列
        // 转移方程：如果s[i] == t[j]，则dp[i][j] = dp[i-1][j-1]，各退一个位置
        //           如果s[i] != t[j]，则dp[i][j] = dp[i][j-1]，t退一个位置
        boolean[][] dp = new boolean[sLen + 1][tLen + 1];
        // base case，第0行表示s为空，所以全部为true;
        Arrays.fill(dp[0], true);
        for(int i = 1; i <= sLen; i++){
            for(int j = 1; j <= tLen; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[sLen][tLen];
    }
}

// String类的indexOf方法，注意要用index来指定找的位置
class Solution {
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for(char c : s.toCharArray()){
            // index + 1 记录从哪里开始找
            index = t.indexOf(c, index + 1);
            // 没找到就返回false
            if(index == -1){
                return false;
            }
        }
        return true;
    }
}
