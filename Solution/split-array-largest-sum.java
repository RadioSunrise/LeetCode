// site: https://leetcode-cn.com/problems/split-array-largest-sum

// 二分查找法 + 贪心
// 首先，求解的目标即子数组的最大值maxIntervalSum是有范围的，即在区间 [max(nums),sum(nums)][max(nums),sum(nums)]
// 而且，一个maxIntervalSum对应一个分组数量m，两者是负相关的关系，如果maxIntervalSum很小，则说明分的组数m比较多，反之maxIntervalSum大，则分的组数m小
// 因此可以用二分查找的方式来
class Solution {
    public int splitArray(int[] nums, int m) {
        // 先确定 maxIntervalSum 的上下界
        // 即max(nums) 和 sum(nums)
        int left = 0;
        int right = 0;
        for(int num : nums){
            left = Math.max(left, num);
            right += num;
        }
        // 上下界为left 和 right，左闭右闭区间
        // 结束条件 left == right
        // 使用「二分查找」确定一个恰当的「子数组各自的和的最大值」，
        // 使得它对应的「子数组的分割数」恰好等于 m
        while(left < right){
            int mid = left + ((right - left) / 2);
            // 这个时候 maxIntervalSum 就是 mid
            int spilt = countSplit(nums, mid);
            // 分割数大于m，说明mid太小，左边界收缩
            if(spilt > m){
                // 下一轮搜索区间[mid + 1, right]
                left = mid + 1;
            }
            // 分割数小于等于m，说明mid太大或者刚好，右边界收缩
            // 因为我们找的是最小的 maxIntervalSum，所以当分割数等于m的时候也继续收缩，找更小的
            else {
                //下一轮的区间是[left, mid]
                right = mid;
            }
        }
        return left;
    }
    /**
     * 计算不超过 [子数组各自的和的最大值] maxIntervalSum 的分割数
     * @param nums 数组
     * @param maxIntervalSum 子数组各自的和的最大值
     * @return
     */
    public int countSplit(int[] nums, int maxIntervalSum){
        // 从nums的开头遍历，当前局部和大于 maxIntervalSum 则+1，且产生新的连续子数组
        // 起码1个分割
        int split = 1;
        int currSum = 0;
        for (int num : nums){
            // 先尝试加看看会不会超过maxIntervalSum
            if(currSum + num > maxIntervalSum){
                split ++;
                currSum = num;
            }
            else {
                currSum += num;
            }
        }
        return split;
    }
}



// dp方法
// 令dp[i][j]表示将前i个数分成j段的最大连续子数组的最小值
// 考虑第[j]段的范围，即枚举第j段的分割点k，前k个数分割成(j-1)段，剩下的为1段，则dp[i][j] = min{max{dp[k][j-1], sub(k+1,i)}}
// 意思是：前k个数分割成(j-1)段，由于我们要使得子数组中和的最大值最小，因此可以列出如下的状态转移方程
// base case: dp[0][0] = 0 比较好理解
// 由于不能分出空数组，所以i >= j才是合法的，其他不合法的位置则初始化为很大的数，
// 由于我们的目标是求出最小值，因此可以将这些状态全部初始化为一个很大的数
// 在上述的状态转移方程中，一旦我们尝试从不合法的状态 dp[k][j-1] 进行转移，那么 max{...} 将会是一个很大的数
// 就不会对最外层的min{...}产生任何影响
// 为了方便地求区间和，可以用这样的方式：令sum[i + 1] = sum[0] + ... + sum[i]，当我们要求区间和sub(k+1,i)的时候，直接sum[i] - sum[k]就可以了
class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        // (n + 1) * (m + 1)大小
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // base case
        dp[0][0] = 0;
        // 为了方便求区间和，可以用以下的方法
        int[] sub = new int[n+1];
        for(int i = 0; i < n; i++){
            sub[i + 1] = sub[i] + nums[i];
        }
        int subSum;
        // i和j从1开始
        for(int i = 1; i <= n; i++){
            // j最多到i或者m的最小值，不能超过i也不能超过m
            for(int j = 1; j <= Math.min(m, i) ; j++){
                // 枚举分割点k，从0到i-1
                for(int k = 0; k < i; k++){
                    subSum = sub[i] - sub[k];
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], subSum));
                }
            }
        }
        return dp[n][m];
    }
}

// 一开始自己写的dfs加记录数组，一个是记录分段和，另一个计算left，right，breakNum的组合结果
// 虽然能做但是很慢
class Solution {
   public int splitArray(int[] nums, int m) {
        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        Integer[][][] dp = new Integer[n][n][m];
        int min = dfsBreak(nums, 0, n - 1, m, memo, dp);
        return min;
    }

    /**
     *
     * @param nums 原数组
     * @param left 当前考虑分割段的起始下标
     * @param right 当前考虑分割段的结束下标
     * @param breakNum 当前段需要分成多少段
     * @param memo 记录分度总和的缓存数组
     * @param dp 记录组合结果
     * @return
     */
    public int dfsBreak(int[] nums, int left, int right, int breakNum, Integer[][]memo, Integer[][][]dp){
        // 结束条件
        if(dp[left][right][breakNum - 1] != null){
            return dp[left][right][breakNum - 1];
        }
        // 剩下的部分只分成1段
        if (breakNum == 1){
            // memo已经存过该分段总和
            if(memo[left][right] != null){
                return memo[left][right];
            }
            // 没有存过，计算再返回
            else{
                memo[left][right] = 0;
                for(int i = left; i <= right; i++){
                    memo[left][right] += nums[i];
                }
                return memo[left][right];
            }
        }
        // 不止1段
        int len = right - left + 1;
        int min = Integer.MAX_VALUE;
        // 枚举分割点，从 left 到 left + (len - breakNum)
        for(int i = left; i <= left + (len - breakNum); i++) {
            // 计算当前段和
            int thisSum = 0;
            if(memo[left][i] != null){
                thisSum = memo[left][i];
            }
            else {
                for(int j = left; j <= i; j++){
                    thisSum += nums[j];
                }
                memo[left][i] = thisSum;
            }
            // 递归，返回最大的段总和
            int remainMaxSum = dfsBreak(nums, i + 1, right, breakNum - 1, memo, dp);
            int currMax = Math.max(thisSum, remainMaxSum);
            min = Math.min(min, currMax);
        }
        dp[left][right][breakNum - 1] = min;
        return min;
    }
}
