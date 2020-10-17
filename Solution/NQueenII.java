// site: https://leetcode-cn.com/problems/n-queens-ii/

// N皇后2，不用返回结果集，统计次数就可以了

// 集合回溯法
// 用了三个HashSet来记录列、对角、从对角线上的皇后摆放情况，方便判断
// 因为不需要返回实际摆法，所以board是不用的
class Solution {
    int res = 0;
    public int totalNQueens(int n) {
        if(n <= 1){
            return 1;
        }
        // 棋盘(因为是统计个数，所以实际上是不需要的)
        // int [] board = new int[n];

        // 保存列，对角线，从对角线上哪些位置已经摆放了皇后
        // 同一个主对角方向上的特点是 (行下标 - 列下标) 相等
        // 同一个从对角方向上的特点是 (行下标 + 列下标) 相等
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diags = new HashSet<>();
        Set<Integer> subDiags = new HashSet<>();
        dfs(0, n, columns, diags, subDiags);
        return res;
    }
    public void dfs(int row, int n, Set<Integer> columns, Set<Integer> diags, Set<Integer> subDiags){
        // 结束条件
        if(row >= n){
            res++;
            return;
        }
        
        // 枚举本行的选择位置
        for(int i = 0; i < n; i++){
            // 用HashSet来查找，O(1)判断是否冲突
            if(columns.contains(i)){
                continue;
            }
            int diag = row - i;
            if(diags.contains(diag)){
                continue;
            }
            int subDiag = row + i;
            if(subDiags.contains(subDiag)){
                continue;
            }
            // 都不冲突，填入之后继续递归
            columns.add(i);
            diags.add(diag);
            subDiags.add(subDiag);

            // 递归
            dfs(row + 1, n, columns, diags, subDiags);

            // 回溯恢复
            columns.remove(i);
            diags.remove(diag);
            subDiags.remove(subDiag);
        }
    }
}

// 位运算回溯法
