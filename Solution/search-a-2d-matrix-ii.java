// site: https://leetcode-cn.com/problems/search-a-2d-matrix-ii

//从左下角开始搜索最方便，比target小的就往右走，比target大的就往上走

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0){
            return false;
        }
        int i = matrix.length-1;
        int n = matrix[0].length;
        int j = 0;
        while(i >= 0 && j < n){
            if(matrix[i][j] < target){
                j++;
            }
            else if(matrix[i][j] > target){
                i--;
            }
            else{
                return true;
            }
        }
        return false;
    }
}

//四个角的选择问题：
/*
这里是对“方法四”的“如何选出发点”的补充：

选左上角，往右走和往下走都增大，不能选

选右下角，往上走和往左走都减小，不能选

选左下角，往右走增大，往上走减小，可选

选右上角，往下走增大，往左走减小，可选
*/
