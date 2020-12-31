// site: https://leetcode-cn.com/problems/non-overlapping-intervals/

// 最小要排除多少个区间使得剩余区间不重叠，可以等价于最多留下多少个不重叠的区间
// 贪心算法，关键是右边界排序的想法

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 贪心算法
        // 每次选择可以替换的区间中右边界最小的一个，剩余的区间则是规模更小的子问题
        // 整个问题可以看做是最多可以留下多少个区间，要移除的就用n减去就可以了
        int n = intervals.length;
        if(n == 0){
            return 0;
        }
        // 按照右边界升序排序，左边界没关系
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2){
                return interval1[1] - interval2[1];
            } 
        });
        // 维护一个右边界变量right，表示已经排好了的区间的右边界
        int right = intervals[0][1];
        // keep是要留下的，初始化为1
        int keep = 1;
        for(int i = 1; i < n; i++){
            // 遍历区间的左边界大于等于right，则这个区间可以留下来
            // 并更新right
            if(intervals[i][0] >= right){
                keep++;
                right = intervals[i][1];
            }
            // 如果遇到right一样的，也只会留下一个，没问题
        }
        return n - keep;
    }
}
