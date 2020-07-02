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
