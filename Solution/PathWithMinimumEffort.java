// site: https://leetcode-cn.com/problems/path-with-minimum-effort/submissions/

// dp不行 不满足后效性
// 抽象成图问题，相邻格之间的边的权值为两点之差
// 问题的目标是找到一条最短路径，路径长度的定义是其中最大的边的权值
// 可以bfs，并查集和最短路径

public class PathWithMinimumEffort {
    /**
    * 1.并查集实现
    */
    public int minimumEffortPathUnionFind(int[][] heights) {
        // 可以用并查集实现
        // 先构造边，再对边进行排序，从短（权值小）的开始增加，直到增加一条边使得(0,0)和(m-1,n-1)连通
        // 则得到答案，为此边的权值（因此要进行排序）
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<>();
        // 遍历构造边，用数组表示，edge[0]和edge[1]为顶点的一维坐标，edge[2]为权值
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 将二维的坐标变成1维的，表示边的两个顶点坐标
                int index = i * n + j;
                // 每个点都与其上方和左方的点构造边
                // 上方
                if(i > 0){
                    edges.add(new int[] {index - n, index, Math.abs(heights[i][j] - heights[i-1][j])});
                }
                // 左方
                if(j > 0){
                    edges.add(new int[] {index - 1, index, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        // 对边进行排序
        Collections.sort(edges, new Comparator<int[]>(){
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        // 终点坐标
        int dest = m * n - 1;
        int res = 0;
        // 按权值从低到高进遍历，添加到并查集中
        UnionFind uf = new UnionFind(m * n);
        for(int[] edge : edges){
            int v1 = edge[0];
            int v2 = edge[1];
            int weight = edge[2];
            uf.union(v1, v2);
            // 加上这条边之后，起点和终点连通了，则得到答案
            if(uf.connected(0, dest)){
                res = weight;
                break;
            }
        }
        return res;
    }
}
class UnionFind{
    int[] parent;
    int[] rank;
    // 连通分量
    int connect;

    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        connect = n;
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

    public boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return false;
        }
        if(rank[rootX] < rank[rootY]){
            rank[rootY] += rank[rootX];
            parent[rootX] = rootY;
        } else {
            rank[rootX] += rank[rootY];
            parent[rootY] = rootX;
        }
        connect--;
        return true;
    }

    public boolean connected(int x, int y){
        return (find(x) == find(y));
    }
}
