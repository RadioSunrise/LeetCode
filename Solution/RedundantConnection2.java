package leetcode;

import java.util.Arrays;

/**
 * 冗余连接
 * 第二题，有向图，给定的边N条，N个顶点的有根树是有N-1条边的，所以有一条多余的边
 * 在有向图中找一条冗余边，把这条变删掉之后剩下的结果图是一棵有根树
 * 用并查集DSU来，但是和无向图不同，要分两种情况
 * 1. 多余的边指向根节点，则图中存在环
 * 2. 多余的边指向非根节点，则这条边指向的节点有两个父节点，也不满足有根树的要求（这个节点的入度为2）
 */
public class RedundantConnection2 {
    /**
     * 并查集类
     */
    class UnionFindSet{
        private int[] parents;
        public UnionFindSet(int n){
            parents = new int[n];
            for(int i = 0; i < n; i++){
                parents[i] = i;
            }
        }

        /**
         * find函数 隔代压缩
         * @param x
         * @return
         */
        public int find(int x){
            while(x != parents[x]){
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        /**
         * 如果合并成功则返回true，如果已经在同一个集合了，返回false
         * @param x
         * @param y
         * @return
         */
        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return false;
            }
            else {
                parents[rootX] = rootY;
                return true;
            }
        }
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        if(n == 0){
            return new int[2];
        }
        int[] result = new int[2];

        // 1.入度数组，统计入度
        int[] inDegree = new int[n + 1];
        for(int[] edge : edges){
            inDegree[edge[1]]++;
        }

        // 2.删除构成入度为2的边，看看是否形成环
        // 逆序查找
        for(int i = n - 1; i >= 0; i--){
            if(inDegree[edges[i][1]] == 2){
                // 如果这条边不构成环，那就是这条边了
                if(!removeJudgeCircle(edges, n, i)){
                    return edges[i];
                }
            }
        }

        // 步骤 3：再尝试删除构成入度为 1 的边，看看是否形成环
        for (int i = n - 1; i >= 0; i--) {
            if (inDegree[edges[i][1]] == 1) {
                // 如果不构成环，这条边就是要去掉的那条边
                if (!removeJudgeCircle(edges, n, i)) {
                    return edges[i];
                }
            }
        }
        return result;
    }

    /**
     * 去掉 removeEdgeIndex 之后，剩下的边是否构成环
     * @param edges
     * @param n
     * @param removeEdgeIndex
     * @return 构成环则返回true
     */
    public boolean removeJudgeCircle(int[][] edges, int n, int removeEdgeIndex){
        UnionFindSet unionFind = new UnionFindSet(n + 1);
        for(int i = 0; i < n; i++){
            // 去掉这条边相当于跳过这条边
            if(i == removeEdgeIndex){
                continue;
            }
            if(!unionFind.union(edges[i][0], edges[i][1])){
                // 合并失败，这条边构成了环
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {3, 1}, {1, 4}};
        RedundantConnection2 solution = new RedundantConnection2();
        int[] res = solution.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(res));
    }
}
