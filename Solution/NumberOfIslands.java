// site: https://leetcode-cn.com/problems/number-of-islands/

// 岛屿个数，可以等价为求这个图的连通分量，两种方法：1.遍历(bfs/dfs)，2.并查集

// 第一种：dfs版
// 写法是修改了原数组的，如果不能修改的话，就多开一个mark数组来标记访问位
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        
        int res = 0;
        // 循环，每个为1的点都进去dfs遍历
        // 和这个1连通的1都会变成0，所以可以这样子统计
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return res;
    }
    /**
    * dfs 搜索连通分量，搜索到的点变成0
    */
    public void dfs(char[][] grid, int x, int y, int m, int n){
        boolean outRange = (x >= m || x < 0 || y >= n || y < 0);
        if(outRange || grid[x][y] != '1'){
            return;
        }
        grid[x][y] = '0';
        // 四个方向递归
        dfs(grid, x + 1, y, m, n);
        dfs(grid, x - 1, y, m, n);
        dfs(grid, x, y + 1, m, n);
        dfs(grid, x, y - 1, m, n);
    }
}

// 第二种：bfs版
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        
        int res = 0;
        // bfs遍历
        // 用队列存一维的坐标就可以了，不用变成二维的，减少申请空间的时间
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 每个'1'都进行bfs
                if(grid[i][j] == '1'){
                    ++res;
                    grid[i][j] = '0';
                    // 存入一维坐标
                    queue.offer(i * n + j);
                    while(!queue.isEmpty()){
                        int index = queue.poll();
                        // 计算出对应的二维坐标
                        int row = index / n;
                        int col = index % n;
                        // 四个方向判断是否入队
                        if(row >= 1 && grid[row - 1][col] == '1'){
                            queue.offer((row - 1) * n + col);
                            grid[row - 1][col] = '0';
                        }
                        if(row < (m - 1) && grid[row + 1][col] == '1'){
                            queue.offer((row + 1) * n + col);
                            grid[row + 1][col] = '0';
                        }
                        if(col >= 1 && grid[row][col - 1] == '1'){
                            queue.offer(row * n + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if(col < (n - 1) && grid[row][col + 1] == '1'){
                            queue.offer(row * n + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }
}

// 第三种：并查集UnionFind
// 并查集类里面有个count，统计集合的个数，所以一开始每个1都是一个集合，合并的时候count-1
// 查找操作顺便做路径压缩
// 主函数遍历的时候，从左上角开始的，每个位置都被他的左和上合并过了，只需要合并右和下就可以了，不用四个方向都看
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        
        // 并查集对象
        UnionFind uf = new UnionFind(grid);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 每个1都和相邻的1进行合并，合并成一个集合
                if(grid[i][j] == '1'){
                    // 从左上角开始的，i 和 j 都增加，到一个特点位置的时候之前（其左上方向的点）都合并过了
                    // 所以不用往回看，只看右和下的相邻就可以了
                    // 转成一维坐标
                    if(j < n - 1 && grid[i][j + 1] == '1'){
                        uf.Union(i * n + j + 1, i * n + j);
                    }
                    if(i < m - 1 && grid[i + 1][j] == '1'){
                        uf.Union((i + 1) * n + j, i * n + j);
                    }
                }
            }
        }
        return uf.count;
        
    }
    /**
    * 内部类 -- 并查集 UnionFind
    */
    class UnionFind{
        /** 
        * 二维坐标变成一维，用一个一维数组存储parent
        */
        int parent[];

        /**
        * grid里面集合的个数
        */
        int count;

        public UnionFind(char[][] grid){
            // 初始化的时候每个1的parent都是自己
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(grid[i][j] == '1'){
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                }
            }
        }
        /**
        * 并查集查找，找到父结点，中间做路径压缩
        */ 
        public int find(int p){
            // 最后顶的parent是自己
            while(parent[p] != p){
                // 在一条链上向上移动
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        /**
        * Union 合并两个集合
        */
        public void Union(int p1, int p2){
            int parent1 = find(p1);
            int parent2 = find(p2);
            if(parent1 != parent2){
                // 合并
                parent[parent1] = parent2;
                // 两个集合并在一起了，集合数量-1
                count--;
            }
        }
    }
}
