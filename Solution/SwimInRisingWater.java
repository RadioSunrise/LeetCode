// site: https://leetcode-cn.com/problems/swim-in-rising-water/submissions/

// 和1631很像，使用并查集，注意考察的时机，uf合并的时候要合并uf的编号，不要搞错了

public class SwimInRisingWater {
    /**
    * 上下左右四个方向
    */
    int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    /**
    * 用并查集实现
    * 因为grid是排列，所以每一个时刻，考察与这个时刻高度相等的单元格，考察单元格
    * 上下左右的邻域，如果比这一格的高度低则可以游过去，即能够连通
    * 逐个时刻进行考察，直到起点和终点可以连通
    */ 
    public int swimInWater(int[][] grid) {
        // 由于grid[i][j]是[0, n * n - 1]的排列，所以最大的时长是Time = n * n
        int n = grid.length;
        int Time = n * n;

        UnionFind uf = new UnionFind(Time);
        // 找到每个高度所对应的index，index为并查集中的一维编号
        int[] index = new int[Time];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // index数组的下标是高度，值是编号，对应坐标
                index[grid[i][j]] = i * n + j;
            }
        }
        // 逐个高度进行遍历，其实也是逐个时刻进行遍历
        for(int t = 0; t < Time; t++){
            // 先找到这时刻（高度）对应的二维坐标
            int row = index[t] / n;
            int col = index[t] % n;
            // 四个方向遍历，如果可以（游过去），则进行合并
            for(int[] dir : directions){
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                // 邻域在范围内，且高度小于当前时刻(可以从此方格游过去)
                if(inRange(newRow, newCol, n) && grid[newRow][newCol] <= t){
                    // 合并两个格，注意合并的是并查集中的编号
                    uf.union(index[t], index[grid[newRow][newCol]]);
                }
                // 如果加上这条边（加上一个连通对），起点和终点可以连通
                if(uf.connected(0, Time - 1)){
                    // 直接return，break的话还要break两次
                    return t;
                }
            }
        }
        return -1;
    }
    /**
    * 判断出界的函数
    */
    public boolean inRange(int row, int col, int n){
        if(row < 0 || row >= n || col < 0 || col >= n){
            return false;
        }
        return true;
    }
}
/**
* 并查集
*/ 
class UnionFind{
    int[] parent;
    int[] rank;

    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return;
        }
        if(rank[rootX] < rank[rootY]){
            rank[rootY] += rank[rootX];
            parent[rootX] = rootY;
        } else {
            rank[rootX] += rank[rootY];
            parent[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y){
        return (find(x) == find(y));
    }
}
