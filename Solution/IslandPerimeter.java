// site: https://leetcode-cn.com/problems/island-perimeter/

// 网格gird中只有一个岛屿，求这个岛屿的周长

// 把grid中访问过的陆地改成2，观察周长增加的规律，从陆地到海或者出界增加1，而陆地到陆地则不增加
// 所以dfs函数可以返回int，根据出界/海/已访问陆地/未访问来做，累加四个方向的边长
class Solution {
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    return dfs(grid, i, j, m, n);
                }
            }
        }
        return 0;
    }
    /**
    * dfs函数，观察岛屿的变长统计方法，dfs往四个方向移动
    * 如果从是陆地走到海或者出界了，则这个方向的边长等于1，陆地走到陆地则不增加变边长
    */
    public int dfs(int[][] grid, int row, int col, int m, int n){
        // 出界了，边长贡献1
        if(!inRange(row, col, m, n)){
            return 1;
        }
        // 如果这一个是海，因为是从陆地走过来的，则贡献1
        if(grid[row][col] == 0){
            return 1;
        }
        // 如果是已经遍历过的陆地，则不贡献也不进行下一步递归
        if(grid[row][col] == 2){
            return 0;
        }
        // grid = 2表示访问过
        grid[row][col] = 2;
        // 四个方向递归
        int sum = 0;
        sum += dfs(grid, row, col - 1, m, n);
        sum += dfs(grid, row, col + 1, m, n);
        sum += dfs(grid, row + 1, col, m, n);
        sum += dfs(grid, row - 1, col, m, n);
        return sum;
    }
    public boolean inRange(int row, int col, int m, int n){
        if(row < 0 || row >= m || col < 0 || col >= n){
            return false;
        }
        return true;
    }
}

// 自己写的第一版，dfs+visited+sum的更新，时间很慢
class Solution {
    int sum = 0;
    int[] dirX = {0, 0, -1, 1};
    int[] dirY = {1, -1, 0, 0};
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n]; 
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    dfs(grid, i, j, m, n, visited);
                    return sum;
                }
            }
        }
        return 0;
    }
    public void dfs(int[][] grid, int row, int col, int m, int n, boolean[][] visited){
        visited[row][col] = true;
        // 先加上4
        sum += 4;
        for(int i = 0; i < 4; i++){
            int nextRow = row + dirX[i];
            int nextCol = col + dirY[i];
            if(inRange(nextRow, nextCol, m, n)){
                // 无论是否访问过，相邻格是陆地都会减1
                if(grid[nextRow][nextCol] == 1){
                    sum--;
                    // 没访问过的才会递归进去
                    if(!visited[nextRow][nextCol]){
                        dfs(grid, nextRow, nextCol, m, n, visited);
                    }
                }
            }
        }
    }
    public boolean inRange(int row, int col, int m, int n){
        if(row < 0 || row >= m || col < 0 || col >= n){
            return false;
        }
        return true;
    }
}
