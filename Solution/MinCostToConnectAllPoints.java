package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * site: https://leetcode-cn.com/problems/min-cost-to-connect-all-points/
 * leetcode 1584，求连接一些点的最小开销，距离使用曼哈顿距离，用最小生成树 Kruskal 算法实现
 */
public class MinCostToConnectAllPoints {
    /**
     * 一些点的坐标(x, y)
     * 用最小生成树实现
     *
     * @param points
     * @return 连接的最小开销
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int res = 0;
        // 因为每次都会选最短的边，所以用一个最小堆来存储这些边
        PriorityQueue<Edge> heap = new PriorityQueue<>(n, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.length - o2.length;
            }
        });
        for(int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                Edge edge = new Edge(i, j, dict(points[i], points[j]));
                heap.add(edge);
            }
        }
        // Kruskal 算法
        // 每次都选最小的边，如果边的两个端点不在同个集合内，则选择这条边，选够n-1条为止
        int count = 0;
        UnionFind unionFind = new UnionFind(n);
        while (count < n - 1){
            Edge edge = heap.poll();
            int len = edge.length;
            int p1 = edge.point1;
            int p2 = edge.point2;
            // 不在同一个集合，合并成功
            if(unionFind.union(p1, p2)){
                count++;
                res += len;
            }
        }
        return res;
    }

    /**
     * 返回两个二维点的曼哈顿距离
     *
     * @param point1
     * @param point2
     * @return
     */
    public int dict(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}

/**
 * 并查集类
 */
class UnionFind {
    /**
     * parent数组
     */
    int[] parent;
    /**
     * rank数组，表示该根所在的树的高度
     */
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 路径压缩查找
     *
     * @param x
     * @return x的根
     */
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * 按秩归并
     *
     * @param x
     * @param y
     * @return true表示不在同一个集合，进行合并；false表示不需要合并
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return false;
        }
        // 将秩小的合并到秩大的（矮的树合并到高的树中）
        if (rank[rootX] < rank[rootY]) {
            rank[rootY] += rank[rootX];
            parent[rootX] = rootY;
        } else {
            rank[rootX] += rank[rootY];
            parent[rootY] = rootX;
        }
        return true;
    }
}
class Edge{
    int point1;
    int point2;
    int length;
    public Edge(int p1, int p2, int len ){
        point1 = p1;
        point2 = p2;
        length = len;
    }
}