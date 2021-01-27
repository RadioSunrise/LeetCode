// site: https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/

public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        // 求删除的最大边数，等价于求最小的保留边数，所以可以从添加的角度来求解
        // 三种边里面，最重要的是类型3，所以先从类型3开始添加
        // 之后再分别用类型1和类型2添加，两种都是基于类型3的结果

        // 所以要用两个一样的uf
        UnionFind ufA = new UnionFind(n);
        UnionFind ufB = new UnionFind(n);
        int res = 0;

        // 编号改成从0开始的，每个都减1
        for(int[] edge : edges){
            edge[1]--;
            edge[2]--;
        }

        // 从公共边开始添加
        for(int [] edge : edges){
            if(edge[0] == 3){
                // 如果这边表不用添加，则表示多余，res++
                if(!ufA.union(edge[1], edge[2])){
                    res++;
                } else {
                    // 进这个else说明是要添加的，所以另一个ufB也有同步添加
                    ufB.union(edge[1], edge[2]);
                }
            }
        }

        // Alice边 和 Bob边
        // 分类单独处理
        for(int[] edge : edges){
            if(edge[0] == 1){
                if(!ufA.union(edge[1], edge[2])){
                    res++;
                }
            } else if(edge[0] == 2){
                if(!ufB.union(edge[1], edge[2])){
                    res++;
                }
            }
        }

        // 最后判断两个集合是否均为连通
        if(ufA.connect != 1 || ufB.connect != 1){
            return -1;
        } else {
            return res;
        }
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
}
