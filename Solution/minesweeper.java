// site: https://leetcode-cn.com/problems/minesweeper/submissions/

// 扫雷游戏
// 图遍历的变型问题

// dfs做法
class Solution {
    static int[][] dires = new int[][] {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        if(m == 0){
            return board;
        }
        int n = board[0].length;
        int x = click[0];
        int y = click[1];
        // click到地雷的位置
        // 标记并返回
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }
        // 递归
        dfs(board, x, y, m, n);
        return board;
    }
    /**
    * 类似于DFS的遍历
    */
    public void dfs(char[][] board, int x, int y, int m, int n){
        // 只有当前位置为E才会扩展
        if(inRange(x, y, m, n) && board[x][y] == 'E'){
            int count = 0;
            int nextX = 0;
            int nextY = 0;
            // 统计周围一圈的地雷个数
            for(int[] dir : dires){
                nextX = x + dir[0];
                nextY = y + dir[1];
                // 如果没有越界 + 遇到地雷, 增加计数
                if(inRange(nextX, nextY, m, n) && board[nextX][nextY] == 'M'){
                   ++count;
                }
            }
            // 修改当前位置
            if(count == 0){
                board[x][y] = 'B';
                // 修改为B才会继续递归
                // 8个方向递归
                for(int[] dir : dires){
                    nextX = x + dir[0];
                    nextY = y + dir[1];
                    dfs(board, nextX, nextY, m, n);
                }
            }
            else {
                board[x][y] = (char)(count + '0');
            }
        }
    }
    public boolean inRange(int x, int y, int m, int n){
        if(x >= 0 && x < m && y >=0 && y < n){
            return true;
        }
        return false;
    }
}

// 当然BFS也是可以的
