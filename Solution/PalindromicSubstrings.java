// site: https://leetcode-cn.com/problems/palindromic-substrings/submissions/

// 计算字符串中回文子串的数目

// dp做法，时间和空间都是O(n^2)
// 类似于中心扩展判断回文串
// dp[i][j] 表示 s[i, j]是否为回文串
// dp[i][j] = dp[i + 1][j - 1] && (s[i] == s[j]) (j - i > 1)，当s[i] == s[j]且s[i + 1, j - 1]是回文串的时候，s[i, j]才是回文串
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        // dp[i][j] 表示 s[i, j]是否为回文串
        
        boolean[][] dp = new boolean[n][n];
        // base case
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
        // 最少n个回文子串(n个字符)
        int count = n;
        // 转移方程
        // dp[i][j] = dp[i + 1][j - 1] && (s[i] == s[j]) (j - i > 1)
        // 因为每个dp[i][j]要用到左下角，所以从左上角开始准列遍历
        // 列
        for(int j = 1; j < n; j++){
            // 行
            for(int i = 0; i < j; i++){
                // 长度为2
                if(i == j - 1){
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                }
                else {
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                }
                // 增加计数
                if(dp[i][j]){
                    count++;
                }
            }
        }
        return count;
    }
}

// 优化版的暴力做法
// 暴力法可以分为两种，一种是枚举起始和结束位置(i, j)，逐个判断s[i, j]是不是回文串，共有O(n^2)个组合，每个组合需要O(n)来判断，总共是O(n^3)
// 另外一种是中心扩展，枚举每一个可能的回文中心，向两边扩展，直到两边的字符不相等，O(n^2)
// 中心扩展需要判断串的长度，如果长度是奇数，则回文中心是一个字符，偶数则为两个字符
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        // 中心扩展做法
        int count = 0;

        // 因为回文中心可以是单个字符或者两个字符，两种都要考虑
        // 枚举回文中心
        for(int i = 0; i < n; i++){
            // 单独字符回文中心
            count += countSub(s, i, i);
            // 两个字符回文中心
            count += countSub(s, i, i + 1);
        }
        return count;
    }
    /**
    * 返回指定回文中心位置的串个数
    * @param start 中心起点
    * @param end 中心终点，如果中心是单个字符，则end = start
    */
    public int countSub(String s, int start, int end){
        int left = start;
        int right = end;
        int res = 0;
        // 向两边扩展
        // 越界判断 和 相等判断
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            res++;
            left--;
            right++;
        }
        return res;
    }
}
