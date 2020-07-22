// site: https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/

// 和12题类似，增加了判断下标的范围，而且不能在回溯阶段设置重置访问值，因为是统计能走的范围
// 自己的DFS做法，没有做搜索方向的简化，都是暴力上下左右都搜素
class Solution {
    int result = 0;
    boolean[][] visited;
    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        search(0, 0, m, n, k);
        return result;
    }
    public void search(int i, int j, int m, int n, int k){
        // 结束条件 i,j越界或者已经访问过了
        if(i < 0 || i >= m || j < 0 || j >=n || changeTo(i, j) > k || visited[i][j] == true){
            return;
        }
        // 设置访问位
        visited[i][j] = true;
        // 增加计数
        result++;
        // 上下左右递归
        // 左
        search(i, j-1, m, n, k);
        // 右
        search(i, j+1, m, n, k);
        // 上
        search(i - 1, j, m, n, k);
        // 下
        search(i + 1, j, m, n, k);
    }
    public int changeTo(int num1, int num2){
        int sum = 0;
        while(num1 != 0){
            sum += num1 % 10;
            num1 = num1 / 10;
        }
        while(num2 != 0){
            sum += num2 % 10;
            num2 = num2 / 10;
        }
        return sum;
    }
}

// BFS做法
