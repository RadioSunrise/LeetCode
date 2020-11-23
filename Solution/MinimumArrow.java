package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * 求射爆气球的最少箭数
 */
public class MinimumArrow {
    /**
     * 第一版直接合并区间的版本
     * @param points
     * @return
     */
    public int findMinArrowShotsMerge(int[][] points) {
        // 先按照区间起点进行排序
        int n = points.length;
        if(n <= 1){
            return n;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }
                else {
                    return o1[1] - o2[1];
                }
            }
        });
        int res = 0;
        // 将两个有交集的区间合并成他们的交集
        int[] range = points[0];
        int[] curr;
        for(int i = 1; i < n; i++){
            curr = points[i];
            // 如果有交集，合并成交集
            if(curr[0] <= range[1]){
                // 因为排过序，所以points[i][0]大于等于range[0]
                range[0] = curr[0];
                range[1] = Math.min(range[1], curr[1]);
            }
            // 没有交集，则当前合并的气球可以用一支箭射爆
            else {
                res++;
                // range等于下一个区间，等待合并
                range = curr;
            }
        }
        // 剩下的最后一个区间用一支箭
        res++;
        return res;
    }
    /**
     * 第二版改进版本，排序方式改成按右边界升序排序
     * 想法是贪心算法，箭的最右的移动范围是多少，箭右移的时候纳入尽量多的气球
     * 移动的边界则是能被引爆的气球的最左边的一个
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        // 先按照区间起点进行排序
        int n = points.length;
        if(n <= 1){
            return n;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 为了防止溢出，不用o1[1] - o2[1]
                return Integer.compare(o1[1], o2[1]);
            }
        });
        // 按照右边界升序排序之后，可以确定后面的区间右侧都是大于当前区间的
        // 初始发射位置为points[0][1]右边界, 因此当后面的区间的左边界小于当前发射位置就可以一起处理
        int pos = points[0][1];
        int res = 1;
        for(int[] point : points){
            // point[0] <= pos可以一起处理
            // point[0] > pos就不可以了
            if(point[0] > pos){
                res++;
                // 更新发射位置，为当前遍历区间的右边界
                pos = point[1];
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[][] points = new int[][]{{10,16},{2,5},{1,6},{7,12}};
        // int[][] points = new int[][]{{1,2},{1,2},{1,1},{1,2}};
        int res = new MinimumArrow().findMinArrowShots(points);
        System.out.println(res);
    }
}
