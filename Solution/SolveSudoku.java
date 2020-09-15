package leetcode;

/**
 * 解数独，用回溯法
 */
public class SolveSudoku {
    boolean[][] row = new boolean[9][9];
    boolean[][] col = new boolean[9][9];
    boolean[][][] cell = new boolean[3][3][9];
    public void solveSudoku(char[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char c = board[i][j];
                if(c != '.'){
                    int index = (c - '0') - 1;
                    row[i][index] = true;
                    col[j][index] = true;
                    cell[i / 3][j / 3][index] = true;
                }
            }
        }
        dfs(0, 0, board);
    }

    /**
     * （x，y）位置递归填数独，按行优先填数
     * @param x 行坐标，从0开始
     * @param y
     * @param board
     * @return
     */
    public boolean dfs(int x, int y, char[][] board){
        // 终止条件
        // 行越界，说明前面的行都填好了，返回true
        if(x == 9){
            return true;
        }
        // 列越界，递归下一行
        if(y == 9){
            return dfs(x + 1, 0, board);
        }
        // 已经填过了，递归下一个位置
        if(board[x][y] != '.'){
            return dfs(x, y + 1, board);
        }
        // 枚举可以填的数字
        for(int num = 1; num <= 9; num++){
            // 存在冲突
            if(row[x][num - 1] || col[y][num] || cell[x / 3][y / 3][num - 1]){
                continue;
            }
            // 填数字
            board[x][y] = (char)(num + '0');
            row[x][num - 1] = true;
            col[y][num - 1] = true;
            cell[x / 3][y / 3][num - 1] = true;
            // 递归填下一个位置，成功了直接返回true
            if(dfs(x, y + 1, board)){
                return true;
            }
            // 回溯
            board[x][y] = '.';
            row[x][num - 1] = false;
            col[y][num - 1] = false;
            cell[x / 3][y / 3][num - 1] = false;
        }
        return false;
    }
}

// 不用记录用isValid判断的，会比较慢
class Solution {
    public void solveSudoku(char[][] board){
        dfs(0, 0, board);
    }

    /**
     * （x，y）位置递归填数独，按行优先填数
     * @param x 行坐标，从0开始
     * @param y
     * @param board
     * @return
     */
    public boolean dfs(int x, int y, char[][] board){
        // 终止条件
        // 行越界，说明前面的行都填好了，返回true
        if(x == 9){
            return true;
        }
        // 列越界，递归下一行
        if(y == 9){
            return dfs(x + 1, 0, board);
        }
        // 已经填过了，递归下一个位置
        if(board[x][y] != '.'){
            return dfs(x, y + 1, board);
        }
        // 枚举可以填的数字
        for(int num = 1; num <= 9; num++){
            // 用isValid遍历判断
            if(!isValid(x, y, num, board)){
                continue;
            }
            // 填数字
            board[x][y] = (char)(num + '0');
            // 递归填下一个位置，成功了直接返回true
            if(dfs(x, y + 1, board)){
                return true;
            }
            // 回溯
            board[x][y] = '.';
        }
        return false;
    }

    /**
     * 判断（x,y）位置是能否填num
     * @param x
     * @param y
     * @param num
     * @param board
     * @return
     */
    public boolean isValid(int x, int y, int num, char[][] board){
        char c = (char) (num + '0');
        for(int i = 0; i < 9; i++){
            if(board[x][i] == c){
                return false;
            }
            if(board[i][y] == c){
                return false;
            }
            if(board[(x / 3) * 3 + (i / 3)][(y / 3) * 3 + (i % 3)] == c){
                return false;
            }
        }
        return true;
    }
}
