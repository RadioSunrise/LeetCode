// https://leetcode-cn.com/problems/divisor-game/submissions/

// 由规律和归纳法可以得到，N偶数必胜而奇数必败
public boolead divisorGame(int N){
    return (N % 2 == 0);
}

// 自己的dp写法
class Solution {
    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N + 1];
        Arrays.fill(dp, false);
        dp[1] = false;
        for(int i = 2; i <= N; i++){
            // 枚举可以选的数x，dp[i] = !dp[i-x]
            for(int x = 1; x < i; x++){
                if(i % x == 0 && dp[i-x] == false){
                    dp[i] = true;
                }
            }
        }
        return dp[N];
    }
}
