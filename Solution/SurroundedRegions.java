// site: https://leetcode-cn.com/problems/surrounded-regions/

// 被包围的区域

// 要找到被'X'包围的'O'，就要判断这个'O'是不是跟边界相连，如果直接从每个'O'来遍历出去会不太方便
// 所以可以反过来从边界的'O'入手，从边界的'O'出发，dfs/bfs，能够遍历到的'O'就是和边相连的，最后不用换成'X'
// 其余的'O'就要改成'X'了
// 可以通过标记的方法，在原地把最后要留下来的'O'改成其他字符，最后扫描一遍board，将标记过的改成'O',其他的'O'变成'X'

// DFS做法
class Solution {
    public void solve(char[][] board) {
        // 从边界上的'O'出发，BFS或者DFS来找相连的'O'
        int m = board.length;
        if(m == 0){
            return;
        }
        int n = board[0].length;

        // 从边界开始dfs
        // 第一行 和 最后一行
        for(int j = 0; j < n; j++){
            dfsMark(board, 0, j, m, n);
            dfsMark(board, m - 1, j, m, n);
        }
        // 第一列 和 最后一列 (从1开始到m - 2)
        for(int i = 1; i < m - 1; i++){
            dfsMark(board, i, 0, m, n);
            dfsMark(board, i, n - 1, m ,n);
        }

        // dfs完了，遍历board把标记为'T'的复原成'O'
        // 其余的'O'变成'X'
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'T'){
                    board[i][j] = 'O';
                }
                else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
    // 从x, y开始做深度优先搜索，当前位置的字符应为'O'
    public void dfsMark(char[][] board, int x, int y, int m, int n){
        boolean inRange = (x >= m || y >= n || x < 0 || y < 0);
        if(inRange || board[x][y] != 'O'){
            return;
        }
        // 标记为T
        board[x][y] = 'T';
        // 四个方向递归
        dfsMark(board, x + 1, y, m, n);    
        dfsMark(board, x - 1, y, m, n);
        dfsMark(board, x, y - 1, m, n);
        dfsMark(board, x, y + 1, m, n);
    }
}

// BFS做法
class Solution {
    public void solve(char[][] board) {
        // 从边界上的'O'出发，BFS或者DFS来找相连的'O'
        int m = board.length;
        if(m == 0){
            return;
        }
        int n = board[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // 从边界开始bfs
        // 辅助队列
        Queue<int[]> queue = new LinkedList<>();
        // 先将边界的'O'入队
        // 第一行 和 最后一行
        for(int j = 0; j < n; j++){
            if(board[0][j] == 'O'){
                queue.offer(new int[]{0, j});
            }
            if(board[m - 1][j] == 'O'){
                queue.offer(new int[]{m - 1, j});
            }
        }
        // 第一列 和 最后一列 (从1开始到m - 2)
        for(int i = 1; i < m - 1; i++){
            if(board[i][0] == 'O'){
                queue.offer(new int[]{i, 0});
            }
            if(board[i][n - 1] == 'O'){
                queue.offer(new int[]{i, n - 1});
            }
        }

        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            board[x][y] = 'T';
            // 四个方向查看是否能入队
            for(int [] dir : dirs){
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                boolean inRange = (nextX < m && nextX >= 0 && nextY < n && nextY >= 0);
                if(inRange && board[nextX][nextY] == 'O'){
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        // bfs完了，遍历board把标记为'T'的复原成'O'
        // 其余的'O'变成'X'
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'T'){
                    board[i][j] = 'O';
                }
                else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
}
