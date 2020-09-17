package leetcode;

/**
 * 冗余连接 
 * 第一题，无向图
 * 无向图找一条冗余边，把这条变删掉之后剩下的结果图是一棵有根树
 * 用并查集DSU来做
 */
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int[] result = new int[2];
        if(edges.length == 0){
            return result;
        }
        int n = edges.length;

        // 并查集的parents[]
        // 长度n + 1，多一个方便后面添加，不用-1
        int[] parents = new int[n + 1];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }

        for(int[] edge : edges){
            // 如果边的两个顶点已经在同一个集合内部，则为冗余边
            int x = edge[0];
            int y = edge[1];
            // 如果x和y已经在同一个集合
            if(find(x, parents) == find(y, parents)){
                result[0] = x;
                result[1] = y;
                return result;
            }
            else {
                union(x, y, parents);
            }
        }
        return result;
    }

    /**
     * 并查集find操作，找x的根节点，加上路径压缩
     * @param x
     * @param parents
     * @return
     */
    public int find(int x, int[] parents){
        if(parents[x] != x){
            parents[x] = find(parents[x], parents);
        }
        return parents[x];
    }

    /**
     * 将x和y所属的两个集合并起来，如果x和y不在一个集合，合并完之后返回true
     * 如果已经在同一集合里面就返回false
     * @param x
     * @param y
     * @param parents
     * @return
     */
    public void union(int x, int y, int[] parents){
        int rootX = find(x, parents);
        int rootY = find(y, parents);
        // 已经在同一个集合
        if(rootX == rootY){
            return;
        }
        else {
            // 将x的集合并到y的集合
            parents[rootX] = rootY;
        }
    }

    public static void main(String[] args){
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] edge = new RedundantConnection().findRedundantConnection(edges);
        System.out.println(edge[0] + " " + edge[1]);
    }
}
