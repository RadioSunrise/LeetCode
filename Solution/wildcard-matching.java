// site: https://leetcode-cn.com/problems/wildcard-matching/

// 通配符匹配
// 状态是ture 或 false
// dp define: s的前i个 和 p的前j个 能否匹配，这里的i和j从1开始，dp[0][j]和dp[i][0]分别代表s和p是空串
// 转移方程: 看当前字符（尾部字符） (1). s[i-1] == p[j-1]，看前面的结果 --> dp[i][j] = dp[i-1][j-1]
//                                 (2). p[j-1] == '?'， 通配s[i-1] --> dp[i][j] = dp[i-1][j-1]
//                                 (3). p[j-1] == '*'，两种情况 (3-1). '*'用来匹配""空串，则当dp[i][j-1]为ture，dp[i][j]等于true
//                                                              (3-2). '*'用来匹配若干个字符，(相当于*不动)则当dp[i-1][j]为ture，dp[i][j]等于true
class Solution {
    public boolean isMatch(String s, String p) {
        int s_len = s.length();
        int p_len = p.length();
        boolean[][] dp = new boolean[s_len + 1][p_len + 1];
        // base case
        dp[0][0] = true;
        // 第0行，即S为空，则p的末尾必须为"*"才可能匹配
        for(int j = 1; j < p_len + 1; j++){
            if(p.charAt(j-1) == '*' && dp[0][j-1]){
                dp[0][j] = true;
            }
        }
        // 遍历+转移
        for(int i = 1; i < s_len + 1; i++){
            for(int j = 1; j < p_len + 1; j++){
                if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(j-1) == '*' && (dp[i][j-1] || dp[i-1][j])){
                    dp[i][j] = true;
                }
            }
        }
        return dp[s_len][p_len];
    }
}
// 关键：状态转移的判断
