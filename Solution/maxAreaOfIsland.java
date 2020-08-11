// site: https://leetcode-cn.com/problems/max-area-of-island/

// 求岛屿的最大面积，等价于求最大的连通分量

// dfs做法，遍历统计个数，会修改原数组，如果是不能修改的话需要新创建一个mark或者gridCopy
// 非递归dfs或者bfs也只可以的
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    res = Math.max(res, dfs(grid, i, j, m, n));
                }
            }
        }
        return res;
    }
    // dfs遍历并且统计遍历过程中1的个数
    public int dfs(int[][] grid, int x, int y, int m, int n){
        boolean outRange = (x < 0 || x >= m || y < 0 || y >= n);
        if(outRange || grid[x][y] != 1){
            return 0;
        }
        // 先统计自己
        int count = 1;
        // 标记成已经访问过的点
        // 不是1都可以的，非0的话之后要复原也比较方便，0的话就混在一起了
        grid[x][y] = 2;

        // 递归
        count += dfs(grid, x + 1, y, m, n);
        count += dfs(grid, x - 1, y, m, n);
        count += dfs(grid, x, y + 1, m, n);
        count += dfs(grid, x, y - 1, m, n);

        return count;
    }
}
