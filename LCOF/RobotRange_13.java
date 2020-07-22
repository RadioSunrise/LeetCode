// site: https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/

// 可以通过数位变化和可达解结构发现：只往下和往右就可以走完全部的可达解了，不需要向上和向左递归

// 和12题类似，增加了判断下标的范围，而且不能在回溯阶段设置重置访问值，因为是统计能走的范围
// 自己的DFS做法，没有做搜索方向的简化，都是暴力上下左右都搜素
// 机器人从左上角出发，其实只需要向右和向下就可以到达所有的可达解，不需要向上和向左递归
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
        // search(i, j-1, m, n, k);
        // 右
        search(i, j+1, m, n, k);
        // 上
        // search(i - 1, j, m, n, k);
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
// 要调用bfsSearch函数
public int bfsSearch(int m, int n, int k){
        int res = 0;
        // BFS队列
        Queue<int[]> q = new LinkedList<>();
        // 从(0,0)开始
        q.offer(new int[] {0, 0});
        while(!q.isEmpty()){
            // 获取当前节点
            int[] temp = q.poll();
            int i = temp[0];
            int j = temp[1];
            // 本结点不符合要求，则跳过不增加下和右
            // 因为第一个结点也是要这样判断的
            if(i >= m || j >= n || visited[i][j] || changeTo(i, j) > k){
                // 跳过
                continue;
            }
            // 本节点是可达解，把右方和下方的位置入队进行尝试
            // 访问位设置
            visited[i][j] = true;
            // 计数 + 1
            res++;
            q.offer(new int[] {i + 1, j});
            q.offer(new int[] {i, j + 1});
        }
        return res;
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
