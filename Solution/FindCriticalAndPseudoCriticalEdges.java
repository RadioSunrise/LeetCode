/**
* site: https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
* 需要多次使用Kruskal最小生成树算法，第一次使用计算出MST的权值value
* 之后遍历每条边，判断是否为关键边和伪关键边，每次判断都要用到Kruskal
*/

public class FindCriticalAndPseudoCriticalEdges {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // 第一次用Kruskal,计算MST的权值value
        int m = edges.length;
        List<Edge> newEdges = new ArrayList<>();
        for(int i = 0; i < m; i++){
            Edge edge = new Edge(edges[i][0], edges[i][1], edges[i][2], i);
            newEdges.add(edge);
        }
        // 排序
        Collections.sort(newEdges, new Comparator<Edge>() {
            public int compare(Edge edge1, Edge edge2) {
                return edge1.weight - edge2.weight;
            }
        });
        // 最小生成树包含全部的n个点
        UnionFind uf0 = new UnionFind(n);
        int value = 0;
        for(Edge edge : newEdges){
            if(uf0.union(edge.begin, edge.end)){
                value += edge.weight;
            }
        }

        // 遍历每条边判断是不是关键边和伪关键边
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            res.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < newEdges.size(); i++){
            // 1.判读是不是关键边
            UnionFind uf = new UnionFind(n);
            int v = 0;
            // 排除这条边，用剩余的边求MST
            for(int j = 0; j < m; j++){
                if(j != i){
                    Edge edge = newEdges.get(j);
                    if(uf.union(edge.begin, edge.end)){
                        v += edge.weight;
                    }
                }
            }
            // 两个条件：(1)去除这条边直接不连通 (2)权值变大
            if(uf.connectSet != 1 || (uf.connectSet == 1 && v > value)){
                // 把边的编号加到res里面
                res.get(0).add(newEdges.get(i).index);
                // 由于关键边一定是伪关键边，如果是关键边的话就直接下一次循环，不判断是不是伪关键
                continue;
            }

            // 2.判断是不是伪关键边（不是关键边再判断是不是伪关键边）
            // 优先考虑这条边，即一开始就合并这条边，然后判断v和value的大小关系
            uf = new UnionFind(n);
            Edge edge = newEdges.get(i);
            // 直接合并，将MST的权值重置为当前边的weight，因为一定会合并进去
            uf.union(edge.begin, edge.end);
            v = edge.weight;
            // 剩余边计算MST
            for(int j = 0; j < m; j++){
                if(j != i){
                    Edge edgeRes = newEdges.get(j);
                    if(uf.union(edgeRes.begin, edgeRes.end)){
                        v += edgeRes.weight;
                    }
                }
            }
            if(v == value){
                res.get(1).add(edge.index);
            }
        }
        return res;
    }
}

class UnionFind{
    int[] parent;
    int[] rank;
    // 连通分量的个数
    int connectSet;
    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        connectSet = n;
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
        // 按秩归并
        if(rank[rootX] < rank[rootY]){
            rank[rootY] += rank[rootX];
            parent[rootX] = rootY;
        } else {
            rank[rootX] += rank[rootY];
            parent[rootY] = rootX;
        }
        // 合并之后连通分量-1
        connectSet--;
        return true;
    }
    public boolean connected(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return true;
        } else {
            return false;
        }
    }
}

class Edge{
    int begin;
    int end;
    int weight;
    // 索引
    int index;
    public Edge(int begin, int end, int weight, int index){
        this.begin = begin;
        this.end = end;
        this.weight = weight;
        this.index = index;
    }
}
