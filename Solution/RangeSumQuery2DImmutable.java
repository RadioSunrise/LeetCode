//site: https://leetcode-cn.com/problems/range-sum-query-2d-immutable/

public class RangeSumQuery2DImmutable {
    /**
    * 二维前缀和
    */ 
    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if(m > 0){
            int n = matrix[0].length;
            // 计算前缀和
            // preSum[i][j] 表示从左上角(0, 0) 到 (i, j) 的总和
            // 由几何图形
            // preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i][j]
            // 为了方便计算，多开一行和一列，第一行也可以用上面的公式
            preSum = new int[m + 1][n + 1];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    // 注意preSum数组的下标要调整一下
                    preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + matrix[i][j];
                }
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 按照前缀和计算
        // 注意下标的索引要+1
        // 因为子矩阵的边界也是要算的，所以注意其他区域的下标表示
        return (preSum[row2 + 1][col2 + 1] - (preSum[row2 + 1][col1] + preSum[row1][col2 + 1] - preSum[row1][col1]));
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
