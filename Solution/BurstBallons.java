// site: https://leetcode-cn.com/problems/burst-balloons/

// reference: https://www.cnblogs.com/niuyourou/p/11964842.html

// 1.分治的解法，def(i,j)为戳破[i+1, j-1]的气球的最大值，不包括边界i,j
// 如果包含边界值，当枚举分割点k的时候，戳破了k使得k-1和k+1变成相邻的
// 导致子问题之间出现依赖，不是独立的子问题
// 增加一个cache数组用于记忆，不用重复计算

/**
 * leetcode 312，戳气球
 * @author WU
 */
public class BurstBallons {
    /**
     * 分治法，def(i,j)为戳破[i+1, j-1]的气球的最大值，不包括边界i,j.
     * 转移方程：def(i,j) = max{def(i,k) + def(k,j) + nums[i]*nums[k]*nums[j]}
     * @param nums 气球数组
     * @param begin 开始下标
     * @param end 结束下标
     * @param cache 记忆数组，减少计算用的
     * @return
     */
    public int maxCoinsDivide(int []nums, int begin, int end, int[][] cache) {
        // 回归条件（递归结束条件），end = begin + 1
        if ((begin + 1) == end) {
            return 0;
        }
        // 避免重复计算
        if (cache[begin][end] != 0) {
            return cache[begin][end];
        }
        // 维护一个最大值max
        int max = 0;
        // 枚举分割点
        // 分割点k从(begin + 1) 到 (end-1)
        int k_max = -1;
        for(int k = begin + 1; k <= end - 1; k++){
            // 将(begin, end)分割成 (begin, k)和(k, end) 都是开区间
            // 两边递归之后，[begin + 1，end - 1](闭区间)的点已经全部戳破
            // 所以k两边的相邻点是begin和end
            int temp = maxCoinsDivide(nums, begin, k, cache) +
                    maxCoinsDivide(nums, k, end, cache) +
                    nums[begin]*nums[k]*nums[end];
            if (max < temp) {
                // 更新最大值
                max = temp;
                k_max = k;
            }
        }
        System.out.println("k is " + k_max + ", and nums[k] is " + nums[k_max]);
        // 增加到cache里面
        cache[begin][end] = max;
        // 返回
        return max;
    }

    /**
     * 递归函数maxCoinDivide的入口，处理空数组和虚拟边界问题
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        // 空数组处理
        if (nums.length == 0) {
            return 0;
        }
        // 增加虚拟边界
        int len = nums.length;
        int[] numsD = new int[len + 2];
        // 原数组的元素复制到新数组中(不包括头和尾)
        System.arraycopy(nums, 0, numsD, 1, len);
        numsD[0] = 1;
        numsD[len + 1] = 1;
        // 创建缓存数组
        // 从-1到n，有n+2个
        int[][] cache = new int[len + 2][len + 2];
        // 调用分治递归函数
        // 当前数组的下标是[0, len + 1]
        // 调用递归的边界是begin = 0, end = (len + 1)
        int result = maxCoinsDivide(numsD, 0, len + 1, cache);
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        BurstBallons bb = new BurstBallons();
        int max = bb.maxCoins(nums);
        System.out.println(max);
    }
}

// 2.DP解法
// 注意dp的遍历顺序，最后的结果在dp表的右上角，i从下到上递减，j从左到右递增
/**
     * DP动态规划做法，按照上面分治法的转移方程，cache数组就是dp表
     * @param nums
     * @return
     */
    public int maxCoinsDp(int[] nums){
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        // 增加虚拟边界
        int[] numsD = new int[len + 2];
        System.arraycopy(nums, 0, numsD, 1, len);
        numsD[0] = 1;
        numsD[len + 1] = 1;
        int newLen = len + 2;
        int[][] dp = new int[newLen][newLen];
        // 按照i递减，j递增的方向枚举分割点k
        // 最终的目标答案是dp[0, len + 1];
        for(int i = newLen - 2; i > -1; i--){
            for(int j = i + 2; j < newLen; j++){
                // 最大值max
                int max = 0;
                // 枚举分割点k，i、j相邻则dp[i][j] = 0
                // 因为初始化全为0，就不用增加特殊判断了
                for(int k = i + 1; k <= j - 1; k++){
                    int temp = dp[i][k] + dp[k][j] + numsD[i]*numsD[k]*numsD[j];
                    if(temp > max){
                        max = temp;
                    }
                }
                dp[i][j] = max;
            }
        }
        return dp[0][newLen - 1];
    }
