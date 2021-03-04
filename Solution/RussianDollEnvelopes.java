// site: https://leetcode-cn.com/problems/russian-doll-envelopes/

// 二维最长上升子序列

// DP1. 固定一个维度，再按照另一个维度找最长上升子序列，主要一个升序一个逆序，以及排序的原因
// O(n^2)
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        // 因为信封有两个维度 w 和 h，所以要考虑固定一个维度
        // 比如固定 w 的顺序，从小到大排，再找 h 的最长上升子序列
        // 但是如果出现了 w 相当但 h 上升的序列如[1,1],[1,2],[1,3],[1,4]
        // 最长上升子序列会是4，但其实因为 w 相等，所以最长上升序列只能是1
        // 所以同一个 w 只能选1个
        // 可以将同一个 w 的 h 按逆序排，这样同一个 w 里面就不会出现上升序列

        int len = envelopes.length;
        if(len == 0){
            return 0;
        }
        // 先排序，w 升序， h 逆序
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] e1, int[] e2){
                if(e1[0] == e2[0]){
                    return e2[1] - e1[1];
                } else {
                    return e1[0] - e2[0];
                }
            }
        });

        // 动态规划DP求最长上升子序列
        // dp[i] 表示以 envelopes[i] 结尾的最长上升子序列长度，且 envelopes[i] 一定在其中
        // 转移方程
        // dp[i] = max(dp[j]) + 1，需要满足 j < i 且 envelopes[i] > envelopes[j]
        // 表示可以放入

        int res = 1;
        int[] dp = new int[len];
        // 初值都是1，起码1个
        Arrays.fill(dp, 1);
        // 二重循环的dp
        for(int i = 1; i < len; i++){
            for(int j = 0; j < i; j++){
                if(envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }
}

// DP2. 二分查找
// O(nlogn)
// 待学习
