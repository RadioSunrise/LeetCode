// site: https://leetcode-cn.com/problems/interleaving-string/

// 用dp来判断是s3是否可以由s1和s2交错组成
// 可以把问题转换成路径问题，把s3当做最终的目标路径，路径上的点由s1和s2中的值来取，只能向下或者向右走
// 变成了能不能走到这个位置的问题(按照target路径的前[i+j]个字符走到(i,j)位置)
// 类似经典的 不同路径(unique path) 问题
// dp[i][j]的意思是 s3前i+j个字符能否有s1的前i个和s2的前j个组成, boolean类型
// 转移方程：dp[i][j]为true : (1)dp[i-1][j]为true，即可以到达上方，当s1[i] = s3[i+j]的时候，这个点也可以到达
//                            (2)dp[i][j-1]为true，即可以到达左方，当s2[j] = s3[i+j]的时候，这个点也可以到达
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        // 特判
        if ((len1 + len2) != len3) {
            return false;
        }
        // dp[i][j] 表示 s3前i+j个字符能否有s1的前i个和s2的前j个组成
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        // base case
        dp[0][0] = true;

        // 第一行，i = 0，表示不从s1中取，只取s2中的前j个字符，构成s3的前j个字符
        for(int j = 1; j <= len2; j++){
            // 当前s2的前j-1和s3的前j-1相同，且s3[j] = s2[j]
            if(dp[0][j - 1] && s3.charAt(j - 1) == s2.charAt(j - 1)) {
                dp[0][j] = true;
            }
        }
        // 第一列，j = 0，表示不从s2中取，只取s1中的前i个字符，构成s3的前i个字符
        for(int i = 1; i <= len1; i++){
            // 当前s1的前i-1和s3的前i-1相同，且s3[i] = s1[i]，dp[i][0]才等于true
            if(dp[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1)) {
                dp[i][0] = true;
            }
        }
        // 从左上到右下
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                // 可以从左dp[i][j-1]和上dp[i-1][j]达到
                // 从左到达则看s3[j+j]和s2[j]是否相同
                // 从上到达则看s3[j+j]和s1[i]是否相同
                dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i-1)) ||
                            (dp[i][j - 1] && s3.charAt(i + j -1) == s2.charAt(j - 1));
            }
        }
        return dp[len1][len2];
    }
}


// dfs+记忆化
// dfs的思路是，s3的每个字符都有两种选择，可以选s1的，也可以选s2
// 令i,j,k三个指针分别指向s1，s2和s3，(k的位置有k=i+j)当s3[k] = s1[i]的，那么尝试递归(i+1,j,k+1)
// 如果不为true，再将i和k回溯，判断j和k，s3[k] = s2[j]，那么尝试(i,j+1,k+1)
// 对于重复的子问题，可以用记忆化memo来解决，有这个(i,j)对了，就返回结果
// memo定义为Boolean类，方便判断null的情况
class Solution {
    Boolean[][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        // 特判
        if ((len1 + len2) != len3) {
            return false;
        }
        memo = new Boolean[len1 + 1][len2 + 1];
        return dfs(s1,s2,s3,0,0,0);
    }
    public Boolean dfs(String s1, String s2, String s3, int i, int j, int k){
        if(!(memo[i][j] == null)){
            return memo[i][j];
        }
        // 如果k已经到达末尾，返回true
        if(k == s3.length()){
            memo[i][j] = true;
            return true;
        }
        boolean valid = false;
        if(i < s1.length() && s3.charAt(k) == s1.charAt(i)){
            valid = dfs(s1, s2, s3, i+1, j, k+1);
        }
        if(j < s2.length() && s3.charAt(k) == s2.charAt(j)){
            valid = valid || dfs(s1, s2, s3, i, j+1, k+1);
        }
        memo[i][j] = valid;
        return valid;
    }
}
