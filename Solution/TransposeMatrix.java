// site: https://leetcode-cn.com/problems/transpose-matrix/

public class TransposeMatrix {
    public int[][] transpose(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // 原矩阵不一定是方阵，原来n*m，转置之后m*n
        int[][] B = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                B[i][j] = matrix[j][i];
            }
        }
        return B;
    }
}
