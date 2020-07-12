// site: https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/

// 由于matrix的特殊性质，所以每次比较之后就可以往具体方向走
// 关键是出发点的选择
// 左上角：往右和往下都增大
// 左下角：往右增大，往下减小
// 右上角：往下增大，往左减小
// 右下角：往左和往上都减小
// 所以只能选择从左下和右上角出发

class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        // 从左下角开始找
        int row = n-1;
        int col = 0;
        while(row >= 0 && col < m){
            // m[i][j] > target，要小一点，向上
            if (matrix[row][col] > target){
                row --;
            }
            // m[i][j] < target，要大一点，向右
            else if (matrix[row][col] < target){
                col ++;
            }
            else{
                return true;
            }
        }
        return false;
    }
}
