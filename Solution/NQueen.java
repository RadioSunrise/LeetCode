// site: https://leetcode-cn.com/problems/n-queens/submissions/

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 递归回溯 N皇后问题

/**
 * N皇后
 */
public class NQueen {
    /**
     * N皇后问题，将所有的解放在一个List<List<String>>里面
     * @param N
     * @return
     */
    public List<List<String>> solveNQueens(int N){
        List<List<String>> res = new ArrayList<>();
        if(N <= 0){
            return res;
        }
        int[] board = new int[N];
        Arrays.fill(board, -1);
        dfs(0, board, N, res);
        return res;
    }

    /**
     * dfs回溯
     * @param row 当前行
     * @param board 棋盘
     * @param N 皇后个数
     * @param res 结果集
     */
    public void dfs(int row, int[] board, int N, List<List<String>> res){
        // 递归结束条件，N个皇后都放完了
        if(row == N){
            // 放入结果集
            res.add(boardToString(board, N));
        }
        // 遍历本行的位置，同一列和对角线检查是否有皇后，可以放则递归
        for(int i = 0; i < N; i++){
            // 列，主对角线，从对角线
            boolean columnCrash = false;
            boolean mainDiag = false;
            boolean subDiag = false;
            // 列
            for(int j = 0; j < row; j++){
                if (board[j] == i){
                    columnCrash = true;
                    break;
                }
            }
            if(columnCrash){
                continue;
            }
            // 主对和从角线
            int rowCmp = row;
            int p = i;
            for (; rowCmp >= 0 && p >= 0; rowCmp--, p--){
                if(board[rowCmp] == p){
                    mainDiag = true;
                    break;
                }
            }
            if(mainDiag){
                continue;
            }
            // 从对角线
            rowCmp = row;
            p = i;
            for (; rowCmp >= 0 && p <N; rowCmp--, p++){
                if(board[rowCmp] == p){
                    subDiag = true;
                    break;
                }
            }
            if(subDiag){
                continue;
            }

            // 三种情况都没有冲突
            // dfs递归
            board[row] = i;
            dfs(row + 1, board, N, res);
            // 回溯，重置为-1表示没有放皇后
            board[row] = -1;
        }
    }

    /**
     * 将当前棋牌状态变成一个字符串列表
     * @param board
     * @return
     */
    public List<String> boardToString(int[] board, int N){
        List<String> boardList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            char[] rowArr = new char[N];
            Arrays.fill(rowArr, '.');
            // 特定位置放皇后
            rowArr[board[i]] = 'Q';
            String temp = String.valueOf(rowArr);
            boardList.add(temp);
        }
        return boardList;
    }
    public static void main(String[] args){
        NQueen solution = new NQueen();
        int N = 4;
        List<List<String>> res = solution.solveNQueens(N);
        System.out.println(res.toString());
    }
}
