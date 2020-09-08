// site: https://leetcode-cn.com/problems/edit-distance/
// reference: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bian-ji-ju-li

// 经典的编辑距离，字符不相等的时候有三种操作可选，删除、替换、插入，关键是找准哪个dp位置代表这三个操作
// dp[i][j] 表示s1[0:i]到s2[0:j]的最短编辑距离，dp表增大是把原字符串当成多一个空字符，方便后续计算
// i,j都是结尾的指针，在递归方法里面，是自顶向下地去计算，所以指针是前移的
// 插入：在i的位置插入（后面插入），所以i不变，而j匹配了这个新加入的字符，j前移一个，操作+1
// 删除：把i位置的字符删除，所以j不变，和下一个i指向的字符匹配，i前移，操作+1
// 替换：把当前位置替换，i和j都向前移动，操作+1

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals("")){
            return word2.length();
        }
        if(word2.equals("")){
            return word1.length();
        }
        char[] wordArr1 = word1.toCharArray();
        char[] wordArr2 = word2.toCharArray();
        int m = wordArr1.length;
        int n = wordArr2.length;
        // dp表((m + 1) * (n + 1))
        // 第一行第一列是空字符
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for(int j = 0; j <= n; j++){
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++){
            dp[i][0] = i;
        }
        // dp loop
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                // 字符相等，则等于dp[i - 1][j - 1]
                if(wordArr1[i - 1] == wordArr2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 三种选择选最小的
                else {
                    // 删除dp[i-1][j]，插入dp[i][j + 1]，替换dp[i - 1][j - 1]
                    // 记得+1的操作
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
