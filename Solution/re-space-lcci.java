// site: https://leetcode-cn.com/problems/re-space-lcci/

// 用动态规划做
// 和word break有点相似
// dp[i] 表示 s[0, ..., i-1]的最优未识别数
// 转移方程：当s[0, ..., i-1]的尾部是字典里的一个词word，dp[i] = dp[i-word.len]
// 因为每个word对应的dp[i]都不同，所以每次都要用Math.min来比较一下
// dp[i]最坏的情况就是s[0, ..., i-1]的尾部不是任意一个词，则在dp[i]的未识别字符+1，即dp[i] = dp[i-1] + 1
class Solution {
    public int respace(String[] dictionary, String sentence) {
        Set<String> WordSet = new HashSet<>(Arrays.asList(dictionary));
        int[] dp = new int[sentence.length() + 1];
        // base case
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++){
            // 最差的情况，i-1为末尾无法识别，dp[i] = dp[i-1] + 1
            dp[i] = dp[i-1] + 1;
            // 对每个词都进行尝试
            for(String word : WordSet){
                if(i >= word.length() && WordSet.contains(sentence.substring(i-word.length(), i))){
                    // 比较当前的最小值
                    dp[i] = Math.min(dp[i], dp[i - word.length()]);
                }
            }
        }
        return dp[sentence.length()];
    }
}
