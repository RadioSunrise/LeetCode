//site: https://leetcode-cn.com/problems/flipping-an-image/

// 按要求执行即可，同一行用一个j计算两个位置

public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; (j * 2) < m; j++){
                int temp = A[i][j];
                A[i][j] = 1 - A[i][m - 1 - j];
                A[i][m - 1 - j] = 1 - temp;
            }
        }
        return A;
    }
}
