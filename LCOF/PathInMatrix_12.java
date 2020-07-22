// site: https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/submissions/

// 每个点做dfs搜索，深度优先搜索下去

// 自己的写法，有个visited数组，比较耗空间
// 可以把board里面的元素改成'/'，表示已访问，那么在递归中也会判断不等该字符
// 可以省下visited的空间
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int len = word.length();
        // 每个位置都进行dfs
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(search(board, i, j, m, n, word, 0, len)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean search(char[][] board, int i, int j, int m, int n, String word, int pos, int len){
        // 结束条件1 -- i,j,pos位置越界
        if(i < 0 || j < 0 || i >= m || j >= n){
            return false;
        }
        // 结束条件2 -- i,j位置的字符不等于word[pos]或者已经访问过了
        if(board[i][j] != word.charAt(pos)){
            return false;
        }
        // 结束条件3 -- i,j位置的字符等于word[len]
        if(board[i][j] == word.charAt(pos) && pos == len - 1){
            return true;
        }
        // 处理访问位 -- 用修改原数组的方法
        char temp = board[i][j];
        board[i][j] = '/';
        // 递归
        boolean res = search(board, i, j - 1, m, n, word, pos + 1, len) ||
                    search(board, i, j + 1, m, n, word, pos + 1, len) ||
                    search(board, i + 1, j, m, n, word, pos + 1, len) ||
                    search(board, i - 1, j, m, n, word, pos + 1, len);
        // 复原
        board[i][j] = temp;
        return res;
    }
}

// 自己写的版本
class Solution {
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int len = word.length();
        visited = new boolean[m][n];
        // 每个位置都进行dfs
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(search(board, i, j, m, n, word, 0, len)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean search(char[][] board, int i, int j, int m, int n, String word, int pos, int len){
        // 结束条件1 -- i,j,pos位置越界
        if(i < 0 || j < 0 || i >= m || j >= n){
            return false;
        }
        // 结束条件2 -- i,j位置的字符不等于word[pos]或者已经访问过了
        if(board[i][j] != word.charAt(pos) || visited[i][j] == true){
            return false;
        }
        // 结束条件3 -- i,j位置的字符等于word[len]
        if(board[i][j] == word.charAt(pos) && pos == len - 1){
            return true;
        }
        // 处理访问位
        visited[i][j] = true;
        // 递归
        boolean res = search(board, i, j - 1, m, n, word, pos + 1, len) ||
                    search(board, i, j + 1, m, n, word, pos + 1, len) ||
                    search(board, i + 1, j, m, n, word, pos + 1, len) ||
                    search(board, i - 1, j, m, n, word, pos + 1, len);
        // 复原
        visited[i][j] = false;
        return res;
    }
}
