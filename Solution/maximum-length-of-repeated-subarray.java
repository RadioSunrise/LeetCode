// site: https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray

class Solution {
    public int findLength(int[] A, int[] B) {
        return FindLength(A,B);
    }
    public int FindLength(int[] A, int[] B){
        int ans = 0; //最大值ans
        int[][] dp = new int[A.length + 1][B.length + 1];
        // 初始化 base case
        for(int i = 0; i < A.length + 1; i++){
            dp[0][i] = 0;
        }
        for(int j = 0; j < B.length + 1; j++){
            dp[j][0] = 0;
        }
        for(int i = 1; i < A.length + 1; i++){
            for (int j = 1; j < B.length + 1; j++) {
                // System.out.print("A[i-1] is " + A[i-1] + ", and B[i-1] is " + B[j-1]);
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    ans = Math.max(ans, dp[i][j]); //ans比较，选大的
                }
                else{
                    dp[i][j] = 0;
                }
                
                // System.out.println(", and dp[i][j] is " + dp[i][j]);
            }
        }
        /*
        for(int i = 0; i < A.length + 1; i++){
            for (int j = 0; j < B.length + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        */
        return ans;
    }
}
// 关键：dp[i][j]是以A[i-1]和B[j-1]为末尾的公共子数组长度
// （不是子序列中最长子数组的长度，直接就是子数组，所以当A[i-1] != B[j-1]的时候，不可能以这两个数末尾的最长子数组，所以dp[]）
// 关键是状态 + dp数组的定义 + 转移方程
