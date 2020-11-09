package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定二维平面上的一组点，返回离原点(0,0)最近的k个点
 * https://leetcode-cn.com/problems/k-closest-points-to-origin/
 */
public class kClosestPointsToOrigin {
    /**
     * 最大堆实现
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosestHeap(int[][] points, int K) {
        // 最大堆实现
        int n = points.length;
        if(n <= K){
            return points;
        }
        PriorityQueue<int []> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return ((o2[0] * o2[0] + o2[1] * o2[1]) - (o1[0] * o1[0] + o1[1] * o1[1]));
            }
        });
        for(int[] point : points){
            if(maxHeap.size() < K){
                maxHeap.add(point);
            }
            else if(maxHeap.size() == K){
                int[] top = maxHeap.peek();
                if((point[0] * point[0] + point[1] * point[1]) < (top[0] * top[0] + top[1] * top[1])){
                    maxHeap.poll();
                    maxHeap.add(point);
                }
            }
        }
        int[][] res = new int[K][2];
        for(int i = 0; i < K; i++){
            res[i] = maxHeap.poll();
        }
        return res;
    }

    /**
     * 快排切分实现
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        if(n <= K){
            return points;
        }
        // 期望找到的位置是K-1，则0到K-1共K个数字
        return quickSelect(points, 0, n - 1, K - 1);
    }

    /**
     * 快排选取
     * @param points
     * @param left
     * @param right
     * @param index 希望partition返回的位置（K-1）
     * @return
     */
    public int[][] quickSelect(int[][] points, int left, int right, int index){
        if(left > right){
            return new int[0][0];
        }
        int mid = partition(points, left, right);
        if(mid == index){
            return Arrays.copyOf(points, index + 1);
        }
        else if(mid < index){
            return quickSelect(points, mid + 1, right, index);
        }
        else {
            return quickSelect(points, left, mid - 1, index);
        }
    }
    public int partition(int[][] points, int left, int right){
        int[] point = points[left];
        int pivot = point[0] * point[0] + point[1] * point[1];
        int i = left;
        int j = right;
        while (i < j){
            while(i < j && (points[j][0] * points[j][0] + points[j][1] * points[j][1]) >= pivot){
                j--;
            }
            points[i] = points[j];
            while(i < j && (points[i][0] * points[i][0] + points[i][1] * points[i][1]) <= pivot){
                i++;
            }
            points[j] = points[i];
        }
        points[i] = point;
        return i;
    }
    public static void main(String[] args){
        int[][] points = new int[][]{{3,3},{5,-1},{-2,4},{1,1},{1,1}};
        int K = 2;
        int[][] res = new kClosestPointsToOrigin().kClosest(points, K);
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i][0] + ", " + res[i][1]);
        }
    }


}
