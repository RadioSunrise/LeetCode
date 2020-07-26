// site: https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/

// 1.自己写的 dfs + 记忆化的做法
// memo矩阵记录从[i,j]位置开始的最长递增路径
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        int n = matrix[0].length;
        if(n == 0){
            return 0;
        }
        int max = 1;
        // 保存从i,j为起点位置的最长递增路径
        int[][] memo = new int[m][n];
        for(int i = 0; i < m; i++){
            Arrays.fill(memo[i], 0);
        }
        // 每个点都dfs一次
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, dfsSearchPath(matrix, i, j, m, n, memo));
            }
        }
        return max;
    }

    /**
     * dfs搜索最长递增序列
     * @param matrix 矩阵数组
     * @param i 行下标
     * @param j 列下标
     * @param m 行数
     * @param n 列数
     * @param memo 缓存矩阵
     * @return i，j位置为起点的最长递增序列
     */
    public int dfsSearchPath(int[][] matrix, int i, int j, int m, int n, int[][] memo){
        // 递归结束条件
        // 1. memo里面有
        if(memo[i][j] != 0){
            return memo[i][j];
        }
        // 判断四个方向的位置是否比当前位置大
        int currVal = matrix[i][j];
        int max = 1;
        // 上
        if(i > 0 && matrix[i-1][j] > currVal){
            max = Math.max(max, 1 + dfsSearchPath(matrix, i - 1, j, m, n, memo));
        }
        // 左
        if(j > 0 && matrix[i][j-1] > currVal){
            max = Math.max(max, 1 + dfsSearchPath(matrix, i, j - 1, m, n, memo));
        }
        // 右
        if(j < n-1 && matrix[i][j+1] > currVal){
            max = Math.max(max, 1 + dfsSearchPath(matrix, i, j + 1, m, n, memo));
        }
        // 下
        if(i < m-1 && matrix[i+1][j] > currVal){
            max = Math.max(max, 1 + dfsSearchPath(matrix, i + 1, j, m, n, memo));
        }
        memo[i][j] = max;
        return max;
    }
}


// 2.拓扑排序 + BFS
// 计算出度，每次从出度为0的位置BFS

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        int n = matrix[0].length;
        // 邻域方向
        int[][] dirs  = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        // 拓扑排序 + BFS
        // 统计出度矩阵，比相邻点小则出度+1
        int[][] outDegree = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int[] dir : dirs){
                    // 计算邻域下标
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];
                    // 判断出界和大小关系
                    boolean range = (newRow < m) && (newRow >= 0) && (newCol >=0) && (newCol < n);
                    if(range && matrix[i][j] < matrix[newRow][newCol]){
                        outDegree[i][j]++;
                    }
                }
            }
        }
        int result = 0;
        // BTS搜索，每次将出度为0的点入队（下标入队）
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                if(outDegree[i][j] == 0){
                    queue.offer(new int[] {i, j});
                }
            }
        }
        while (!queue.isEmpty()){
            // 每一层+1
            result++;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] currPos = queue.poll();
                int row = currPos[0];
                int col = currPos[1];
                // 一个出度为0的点出队之后，遍历它的邻域，调整邻域点的出度
                for (int[] dir : dirs){
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    boolean range = (newRow < m) && (newRow >= 0) && (newCol >=0) && (newCol < n);
                    // 如果currPos比较大，那么出队之后newPos的出度要-1
                    if(range && matrix[row][col] > matrix[newRow][newCol]){
                        outDegree[newRow][newCol]--;
                        // 出度减少之后如果0，则入队
                        if(outDegree[newRow][newCol] == 0){
                            queue.offer(new int[] {newRow, newCol});
                        }
                    }
                }
            }
        }
        return result;
    }
}
