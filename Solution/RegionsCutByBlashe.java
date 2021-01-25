// site: https://leetcode-cn.com/problems/regions-cut-by-slashes/

public class RegionsCutByBlashe {
    /**
    * 将每个单元格分为4个小格，操作更方便，顺时针方向0,1,2,3
    * 分为4个格的好处就是无论是斜杠还是反斜杠，都不会影响区域的划分
    * 组间合并操作可以统一
    * 用并查集来合并，分为组内合并与组间合并，组间合并采用右+下的方向
    */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int num = 4 * n * n;
        UnionFind uf = new UnionFind(num);
        // 遍历网格，逐个合并
        for(int i = 0; i < n; i++){
            // 每一行都先转成字符数组
            char[] array = grid[i].toCharArray();
            for(int j = 0; j < n; j++){
                // 计算出单元格分为4个三角形后，0号单元格的index（并查集中的index）
                // 两步：先转为一维，然后乘4
                int index = 4 * (i * n + j);
                char c = array[j];
                // 按类型合并
                // 1.组内合并
                if(c == ' '){
                    // 空格将4个都合并
                    uf.union(index, index + 1);
                    uf.union(index + 1, index + 2);
                    uf.union(index + 2, index + 3);
                } else if(c == '/'){
                    // 斜杠合并 12, 03
                    uf.union(index + 1, index + 2);
                    uf.union(index, index + 3);
                } else {
                    // 反斜杠合并 01, 23
                    uf.union(index , index + 1);
                    uf.union(index + 2, index + 3);
                }

                // 2.组间合并
                // 右边
                if(j + 1 < n){
                    // 自己的1和右边的3
                    uf.union(index + 1, 4 * (i * n + j + 1) + 3);
                }
                // 下方
                if(i + 1 < n){
                    // 自己的2和下边的0
                    uf.union(index + 2, 4 * ((i + 1) * n + j));
                }
            }
        }
        return uf.connectSet;
    }
}

class UnionFind{
    int[] parent;
    int[] rank;
    // 连通分量的个数
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
            rank[rootX] += rank[rootX];
            parent[rootY] = rootX;
        }
        // 合并之后连通分量-1
        connectSet--;
    }
}
