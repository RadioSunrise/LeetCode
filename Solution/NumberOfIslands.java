// site: https://leetcode-cn.com/problems/number-of-islands/

// 岛屿个数，可以等价为求这个图的连通分量

// 第一种：dfs版
// 写法是修改了原数组的，如果不能修改的话，就多开一个mark数组来标记访问位
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        
        int res = 0;
        // 循环，每个为1的点都进去dfs遍历
        // 和这个1连通的1都会变成0，所以可以这样子统计
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return res;
    }
    /**
    * dfs 搜索连通分量，搜索到的点变成0
    */
    public void dfs(char[][] grid, int x, int y, int m, int n){
        boolean outRange = (x >= m || x < 0 || y >= n || y < 0);
        if(outRange || grid[x][y] != '1'){
            return;
        }
        grid[x][y] = '0';
        // 四个方向递归
        dfs(grid, x + 1, y, m, n);
        dfs(grid, x - 1, y, m, n);
        dfs(grid, x, y + 1, m, n);
        dfs(grid, x, y - 1, m, n);
    }
}

// 第二种：bfs版
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        
        int res = 0;
        // bfs遍历
        // 用队列存一维的坐标就可以了，不用变成二维的，减少申请空间的时间
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 每个'1'都进行bfs
                if(grid[i][j] == '1'){
                    ++res;
                    grid[i][j] = '0';
                    // 存入一维坐标
                    queue.offer(i * n + j);
                    while(!queue.isEmpty()){
                        int index = queue.poll();
                        // 计算出对应的二维坐标
                        int row = index / n;
                        int col = index % n;
                        // 四个方向判断是否入队
                        if(row >= 1 && grid[row - 1][col] == '1'){
                            queue.offer((row - 1) * n + col);
                            grid[row - 1][col] = '0';
                        }
                        if(row < (m - 1) && grid[row + 1][col] == '1'){
                            queue.offer((row + 1) * n + col);
                            grid[row + 1][col] = '0';
                        }
                        if(col >= 1 && grid[row][col - 1] == '1'){
                            queue.offer(row * n + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if(col < (n - 1) && grid[row][col + 1] == '1'){
                            queue.offer(row * n + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }
}
