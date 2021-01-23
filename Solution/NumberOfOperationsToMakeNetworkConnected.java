// site: https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected

// 等价于计算连通分量的个数m，连接这些m个分量至少需要m-1的边，如果冗余边的数量>=m-1则可以，否则不行

class NumberOfOperationsToMakeNetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        if(connections.length < n - 1){
            return -1;
        }
        // 统计冗余边
        int redundancyConnect = 0;
        // 逐条边遍历
        for(int[] connection : connections){
            int c1 = connection[0];
            int c2 = connection[1];
            // 如果合并为false，表示本条边为冗余边
            if(!uf.union(c1, c2)){
                redundancyConnect++;
            }
        }
        // 将m个连通分量连起来要m-1条边
        int connectSet = uf.getConnectSet();
        if((connectSet - 1) > redundancyConnect){
            return -1;
        }
        else {
            return connectSet - 1;
        }
    }
}
class UnionFind{
    int[] parent;
    int[] rank;
    int connectSet;
    public UnionFind(int n){
        connectSet = n;
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
    public boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return false;
        }
        if(rank[rootX] < rank[rootY]){
            rank[rootY] += rank[rootX];
            parent[rootX] = rootY;
        }
        else {
            rank[rootX] += rank[rootY];
            parent[rootY] = rootX;
        }
        connectSet--;
        return true;
    }
    // 返回连通分量
    public int getConnectSet(){
        return connectSet;
    }
}
