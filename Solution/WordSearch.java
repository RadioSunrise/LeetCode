// site: https://leetcode-cn.com/problems/word-search/

// 单词搜索，用回溯来做
// 注意一些细节，在判断是否进入递归的时候，除了判断下一个位置是否越界之外，同时判断下一个位置是否访问过，可以减少递归的深度（不用递归进去在判断是否放过过）
// 四个方向的递归只要找到一个true就可以返回了，不要四个方向都做完再汇总，会耗很多时间

// 比较慢
class Solution {
    public int[] dirsRow = {0, 0, 1, -1};
    public int[] dirsCol = {1, -1, 0, 0};
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int len = word.length();
        if(m * n < len){
            return false;
        }
        boolean[][] visited = new boolean[m][n];
        boolean valid = false;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dfs(i, j, m, n, 0, word, board, visited)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
    *
    * @param pos 当前的字符位置
    */
    public boolean dfs(int x, int y, int m, int n, int pos, String word, char[][] board, boolean[][] visited){
        // 当前位置字符不相等，false
        if(board[x][y] != word.charAt(pos)){
            return false;
        }
        // 相等且是最后一个字符
        else if(pos == word.length() - 1){
            return true;
        }
        boolean valid = false;
        visited[x][y] = true;
        for(int i = 0; i < dirsRow.length; i++){
            int nextRow = x + dirsRow[i];
            int nextCol = y + dirsCol[i];
            // in range 且没有访问过
            if(inRange(nextRow, nextCol, m, n) && !visited[nextRow][nextCol]){
                valid |= dfs(nextRow, nextCol, m, n, pos + 1, word, board, visited);
                // 找到一个方向可以的，就跳出循环，不用四个方向都做
                if(valid){
                    break;
                }
            }
        }
        // backtrack
        visited[x][y] = false;
        return valid; 
    }
    public boolean inRange(int x, int y, int m, int n){
        if(x < 0 | x >= m || y < 0 || y >= n){
            return false;
        }
        return true;
    }
}
// 也可以修改原表来代替visited数组
class Solution {
    public int[] dirsRow = {0, 0, 1, -1};
    public int[] dirsCol = {1, -1, 0, 0};
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int len = word.length();
        if(m * n < len){
            return false;
        }
        boolean valid = false;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dfs(i, j, m, n, 0, word, board)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
    *
    * @param pos 当前的字符位置
    */
    public boolean dfs(int x, int y, int m, int n, int pos, String word, char[][] board){
        // 当前位置字符不相等，false
        if(board[x][y] != word.charAt(pos)){
            return false;
        }
        // 相等且是最后一个字符
        else if(pos == word.length() - 1){
            return true;
        }
        boolean valid = false;
        char temp = board[x][y];
        // 标记
        board[x][y] = '#';
        for(int i = 0; i < dirsRow.length; i++){
            int nextRow = x + dirsRow[i];
            int nextCol = y + dirsCol[i];
            // in range 且没有访问过
            if(inRange(nextRow, nextCol, m, n) && board[nextRow][nextCol] != '#'){
                valid |= dfs(nextRow, nextCol, m, n, pos + 1, word, board);
                // 找到一个方向可以的，就跳出循环，不用四个方向都做
                if(valid){
                    break;
                }
            }
        }
        // backtrack
        board[x][y] = temp;
        return valid; 
    }
    public boolean inRange(int x, int y, int m, int n){
        if(x < 0 | x >= m || y < 0 || y >= n){
            return false;
        }
        return true;
    }
}
