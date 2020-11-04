// site: https://leetcode-cn.com/problems/insert-interval/

// 插入区间并保持每个区间内升序，关键是分类和合并的思想
// 原来的每个区间可以分成三类，左边没有重叠，中间有重叠和右边无重叠三类
// 左右两类可以直接加入res，而中间的一类处理则是关键，其实可以用合并更新newInterval的方式来实现合并

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        ArrayList<int[]> res = new ArrayList<>();
        // 遍历原数组
        int i = 0;
        // 在newInterval左边，没有重叠的区域，直接存入结果res
        while(i < len && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        // 有重叠的区域，intervals[i]的左边界小于新区间的右边界
        // 可以看成原区域和新区域newInterval的合并，即更新newInterval
        while(i < len && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // 将最后合并的区间即newInterval加入res
        res.add(newInterval);
        // 右边无重叠的
        while(i < len){
            res.add(intervals[i]);
            i++;
        }
        int[][] ans = new int[res.size()][2];
        for(int j = 0; j < ans.length; j++){
            ans[j] = res.get(j);
        }
        return ans;
    }
}
