// site: https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        // 前缀和(前缀异或)
        // 令 xor[0] = 0, xor[i + 1] = x[i] XOR arr[i]
        // 即 xor[i + 1] = arr[0] XOR ... XOR arr[i]
        // 则 xor(left, right) = xor[left] XOR xor[right]

        int n = arr.length;
        int[] xor = new int[n + 1];
        xor[0] = 0;
        for (int i = 0; i < n; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = xor[queries[i][0]] ^ xor[queries[i][1] + 1];
        }
        return ans;
    }
}
