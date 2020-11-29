// site: https://leetcode-cn.com/problems/largest-perimeter-triangle/

// 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。 如果不能形成任何面积不为零的三角形，返回 0。
// 先排序再尝试，因为找最大的周长，所以排序时候按相邻的三条边来尝试

class Solution {
    public int largestPerimeter(int[] A) {
        // 因为找的是最大的周长，所以排序之后用相邻的三个来判读是否能组成三角形
        int n = A.length;
        Arrays.sort(A);
        for(int i = n - 1; i >= 2; i--){
            // A[i]最大，两边之和大于最长的第三边
            if(A[i] < A[i - 1] + A[i - 2]){
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }
}
