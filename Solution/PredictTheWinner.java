// site: https://leetcode-cn.com/problems/predict-the-winner/

// 预测赢家问题

// 因为过程是从两端来选取，所以是一个逐渐递归的过程

// 先手玩家的得分是正数（加分），后手玩家则是负数（减分），计算最后先手玩家赢的分数是否大于等于0
// 基本版的dfs，没有做优化
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        // 递归计算，先手玩家用剩余数组赢得的分数是否大于等于0，是则获胜
        // 先手玩家加分，后手玩家减分
        return (dfs(0, nums.length - 1, nums) >= 0);
    }
    /**
    * 递归函数，计算当前先手玩家在剩余的数组中可以赢的最大分数
    * 先手玩家加分，后手玩家减分，计算最大的累加分数值
    * @param left 左边界
    * @param right 右边界，指示当数组的范围
    */
    public int dfs(int left, int right, int[] nums){
        // 递归结束条件
        // left 等于 right，剩余一个数可以选，那么先手玩家赢的分数就是这个数字
        if(left == right){
            return nums[left];
        }
        // 两种选择
        // 选左边，之后给下一个玩家进行先手操作，所以是减去下一个玩家（后手玩家）的赢的分数
        // 调用递归的时候，就变成了后手玩家的用剩余部分的数组做先手操作了
        int selectLeft = nums[left] - dfs(left + 1, right, nums);
        // 选右边，同理，减去后手玩家用剩余部分能够赢取的分数
        int selectRight = nums[right] - dfs(left, right - 1, nums);
        // 选择最大的分数
        return Math.max(selectLeft, selectRight);
    }
}

// 用memo数组记录出现过的left、right对，因为都是以先手来考虑的，所以两个维度就可以了
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        // 递归计算，先手玩家用剩余数组赢得的分数是否大于等于0，是则获胜
        // 先手玩家加分，后手玩家减分
        int len = nums.length;
        // 记忆化数组
        Integer[][] memo = new Integer[len][len];
        return (dfs(0, len - 1, nums, memo) >= 0);
        
    }
    /**
    * 递归函数，计算当前先手玩家在剩余的数组中可以赢的最大分数
    * 先手玩家加分，后手玩家减分，计算最大的累加分数值
    * @param left 左边界
    * @param right 右边界，指示当数组的范围
    */
    public int dfs(int left, int right, int[] nums, Integer[][] memo){
        // 递归结束条件
        // memo计算过了
        if(memo[left][right] != null){
            return memo[left][right];
        }
        // left 等于 right，剩余一个数可以选，那么先手玩家赢的分数就是这个数字
        if(left == right){
            return nums[left];
        }
        // 两种选择
        // 选左边，之后给下一个玩家进行先手操作，所以是减去下一个玩家（后手玩家）的赢的分数
        // 调用递归的时候，就变成了后手玩家的用剩余部分的数组做先手操作了
        int selectLeft = nums[left] - dfs(left + 1, right, nums, memo);
        // 选右边，同理，减去后手玩家用剩余部分能够赢取的分数
        int selectRight = nums[right] - dfs(left, right - 1, nums, memo);
        // 选择最大的分数
        int result = Math.max(selectLeft, selectRight);
        // 加入memo
        memo[left][right] = result;
        return result;
    }
}

// 动态规划版
// 记忆化递归可以变成dp做法
// dp数组的定义就类似于memo中记录的内容，dp[i][j]表示先手玩家在剩余数组范围[i : j]内能够赢得的最大分数
// dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])，两种选择取最大的。
// base case : i == j，dp[i][j] = nums[i]，即对角线的位置是base case，最终我们需要的答案在dp[0][n - 1]即第一行的末尾
// 因为 i <= j，所以对角线开始，从下往上，从左到右填表
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        // 递归计算，先手玩家用剩余数组赢得的分数是否大于等于0，是则获胜
        // 先手玩家加分，后手玩家减分
        int len = nums.length;
        // dp表
        int[][] dp = new int[len][len];
        // 转移方程: dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
        // base case在对角线，最终结果在右上角，每个位置考虑下方和左方的结果
        for(int i = 0; i < len; i++){
            dp[i][i] = nums[i];
        }
        // 从下往上从左往右填，倒数第二行开始（最后一行就一个数，已经填过了）
        for(int i = len - 2; i >= 0; i--){
            for(int j = i + 1; j < len; j++){
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        // 根据右上角判断
        return dp[0][len - 1] >= 0;
    }
}
