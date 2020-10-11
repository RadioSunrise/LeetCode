package leetcode;

/**
 * leetcode 416
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 用0-1背包的思路来解，0-1背包的条件是不能超过背包最大容量，而本题的要求是要恰好等于sum/2，因为是二等分
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if(n <= 1){
            return false;
        }
        int sum = 0;
        int max = nums[0];
        for (int num : nums){
            sum += num;
            max = Math.max(num, max);
        }
        // 排除两种情况，总和为奇数或者最大的数比sum/2要大
        if((sum % 2 == 1) || max > sum / 2){
            return false;
        }
        int target = sum / 2;
        // 动态规划
        // dp[i][j] 表示下标[0:i]中选若干个数，能否构成j，所以有(n * target + 1)
        boolean[][] dp = new boolean[n][target + 1];

        // 转移方程
        // (1) nums[i] <= j
        // 第i个数可以选或者不选
        // 1.不选：当前i-1个数可以构成j的时候就可以 -> dp[i-1][j]=true => dp[i][j] = true
        // 2.选：当前i-1个数可以构成j - nums[i]就可以-> dp[i-1][j-nums[i]]=true => dp[i][j] = true
        // (2) nums[i] > j
        // 当nums[i] > j，nums[i]是肯定不能选的，所以dp[i][j]等于dp[i-1][j]

        // base case
        // dp[i][0] = true，都不选就可以；dp[0][nums[0]] = true，其余等于false
        dp[0][nums[0]] = true;
        for(int i = 0; i < n; i++){
            dp[i][0] = true;
        }
        // dp loop
        for(int i = 1; i < n; i++){
            for (int j = 1; j <= target; j++){
                if(nums[i] <= j){
                    dp[i][j] = dp[i-1][j] | dp[i-1][j - nums[i]];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n-1][target];
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,5,11,5,2,2};
        PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();
        System.out.println(solution.canPartition(nums));
    }
}
