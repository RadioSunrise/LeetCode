// site: https://leetcode-cn.com/problems/flood-fill/submissions/

// 渲染（油漆桶），和岛屿问题很像，每个位置的四个方向dfs

// 结束条件不要漏了 image[row][col] == newColor，否则当newColor = keyColor的时候会不停地递归导致爆栈

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int m = image.length;
        int n = image[0].length;
        dfs(image, sr, sc, m, n, image[sr][sc], newColor);
        return image;
    }
    public void dfs(int[][] image, int row, int col, int m, int n, int keyColor, int newColor){
        // 递归结束条件
        // 越界了或者不是keyColor 或者已经染好色了（不要漏了这个）
        boolean outRange = (row >= m || row < 0 || col >= n || col < 0);
        if(outRange || image[row][col] != keyColor || image[row][col] == newColor){
            return;
        }
        // 修改颜色
        image[row][col] = newColor;
        // 四个方向递归
        dfs(image, row + 1, col, m, n, keyColor, newColor);
        dfs(image, row - 1, col, m, n, keyColor, newColor);
        dfs(image, row, col + 1, m, n, keyColor, newColor);
        dfs(image, row, col - 1, m, n, keyColor, newColor);
    }
}
