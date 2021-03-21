// site: https://leetcode-cn.com/problems/set-matrix-zeroes/

// 矩阵置零，当一行或者一列里面有 0 的时候，matrix 中该行或者该列置零

// 方法1：O(m + n)的空间复杂度
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        // 空间 O(m + n)
        // 用一个行数组和列数组记录
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rowZero = new boolean[m];
        boolean[] colZero = new boolean[n];
        // 第一次遍历记录是否有0
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }
        // 第二次根据记录进行处理
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(rowZero[i] || colZero[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

// 方法2：O(1)的空间复杂度，用两个标记变量
// 用第一行和第一列代替方法1的两个数组，但是会改变原来的数值，要用两个变量来标记
// 在实际代码中，我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，
// 然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
class Solution {
    public void setZeroes(int[][] matrix) {
        // 空间 O(1)
        // 用第一行和第一列来记录，用两个变量辅助
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row0Flag = false;
        boolean col0Flag = false;
        // 第一次遍历先记录第一行和第一列是否有0
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 0){
                row0Flag = true;
                break;
            }
        }
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                col0Flag = true;
                break;
            }
        }
        // 遍历，处理一行和第一列
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                // 如果 matrix[i][j] 为 0 ，第一行和第一列里面的对应值最后也是要变零的
                // 所以是记录的时候顺便修改
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 第二次遍历，根据第一行和第一列处理
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        // 如果第一行和第一列原来就有 0 ，就把第一行和第一列全部置零
        if(row0Flag){
            for(int j = 0; j < n; j++){
                matrix[0][j] = 0;
            }
        }
        if(col0Flag){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}

// 方法3：用一个变量，另外一个变量用第一列的第一个元素来代替
