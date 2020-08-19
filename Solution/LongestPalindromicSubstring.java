// site: https://leetcode-cn.com/problems/longest-palindromic-substring/

// 最长回文子串

// 中心扩展做法
class Solution {
    int max = 0;
    int start = 0;
    int end = 0;
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1){
            return s;
        }
        // 枚举中心点
        // 单个字符或者连续两个字符
        // 倒数第二个字符就可以了
        for(int i = 0; i < n - 1; i++){
            centerSpread(s, i, i);
            centerSpread(s, i, i + 1);
        }
        return s.substring(start, end + 1);
    }
    /**
    * 指定回文中心，两边扩展并且挑战最大值
    */
    public void centerSpread(String s, int centerP1, int centerP2){
        int left = centerP1;
        int right = centerP2;
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        // 退出while的时候s[i] != s[j]，要回退一个位置，即[left + 1]到[right - 1]
        if(right - left - 1 > max){
            // System.out.print("update max, old max is " + max);
            max = right - left - 1;
            // System.out.println(", new max is" + max);
            start = left + 1;
            end = right - 1;
        }
    }
}

// Manacher 算法
// 待添加

// 动态规划，dp[i][j]用来表示s[i, j]是不是一个回文串
// 第一版的dp做法，用dp表来记录长度
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1){
            return s;
        }
        // dp做法
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            dp[i][i] = 1;
        }
        int max = 0;
        int start = 0;
        int end = 0;
        // dp[i][j]表示以s[i, j]的回文串的长度
        // 左上角开始逐列遍历，每列从上到下
        for(int j = 1; j < n; j++){
            for(int i = 0; i < j; i++){
                // 当s[i] == s[j]才可能是回文串
                if(s.charAt(i) == s.charAt(j)){
                    // 只是两个相邻字符
                    if(i == j - 1){
                        dp[i][j] = 2;
                        if(j - i + 1 > max){
                            max = j - i + 1;
                            start = i;
                            end = j;
                        }
                    }
                    else if(dp[i + 1][j - 1] > 0) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                        if(j - i + 1 > max){
                            max = j - i + 1;
                            start = i;
                            end = j;
                        }
                    }  
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
// 第二版dp做法，boolean的dp表，最后遍历一遍找最大的
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1){
            return s;
        }
        // dp做法
        boolean dp[][] = new boolean[n][n];
        // 遍历方向是逐列判断，每一列中从上到下
        for(int j = 0; j < n; j++){
            for(int i = 0; i <= j; i++){
                if(s.charAt(i) == s.charAt(j)){
                    // 单个字符或者连续两个字符
                    if(i == j || i == j - 1){
                        dp[i][j] = true;
                    }
                    // 其余位置，看左下角
                    else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }
        // 遍历一遍找最大的
        int max = 0;
        int start = 0;
        int end = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dp[i][j] && j - i + 1 > max){
                    max = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
