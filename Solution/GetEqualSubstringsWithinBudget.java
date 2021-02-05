// site: https://leetcode-cn.com/problems/get-equal-substrings-within-budget/

// 双指针
public class GetEqualSubstringsWithinBudget {
    public int equalSubstring(String s, String t, int maxCost) {
        // 双指针实现
        // 当两个指针包括的区间内的cost值小于maxCost，可以挑战，否则指针需要移动
        int n = s.length();
        // 计算出相同位置的变化开销
        int[] differ = new int[n];
        for(int i = 0; i < n; i++){
            differ[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        // 双指针
        int maxLen = 0;
        int start = 0;
        int end = 0;
        // 当前区间的总开销
        int sum = 0;
        while(end < n){
            sum += differ[end];
            // 如果 sum > maxCost，start则需要向右移动缩减区间
            while(sum > maxCost){
                sum -= differ[start];
                start++;
            }
            // 跳出上面的while循环说明区间内是满足开销的，挑战最大值
            // 由于 maxCost >= 0，所以start最多等于end + 1，区间长为0，是可以的
            maxLen = Math.max(maxLen, end - start + 1);
            // 右指针移动
            end++;
        }
        return maxLen;
    }
}
