// site: https://leetcode-cn.com/problems/similar-string-groups/

public class SimilarStringGroups {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind uf = new UnionFind(n);

        // 两两判断是否相似
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // 先判断是否连通，这样快一点
                if(uf.isConnected(i, j)){
                    continue;
                }
                if(checkSimilarity(strs[i], strs[j])){
                    uf.union(i, j);
                }
            }
        }
        // 连通分量的数量就是答案
        return uf.connectSet;
    }
    /**
    * 判断两个字符串是否相似，这两个串是字母异位词
    * 由于是字母异位词，所以每个两个字符串同时遍历，位置相同而字符不同的次数大于2的时候，则必定不相似，因为两个串不会相同，所以不会出现0次
    */ 
    public boolean checkSimilarity(String s1, String s2){
        int len = s1.length();
        int count = 0;
        for(int i = 0; i < len; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
                if(count > 2){
                    return false;
                }
            }
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

    /**
    * 迭代版的路径压缩find
    */
    public int find(int x){
        // 逐层向上推
        while(x != parent[x]){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
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
        connectSet--;
        return true;
    }

    public boolean isConnected(int x, int y){
        return find(x) == find(y);
    }
}
