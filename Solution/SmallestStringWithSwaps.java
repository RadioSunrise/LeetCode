// site: https://leetcode-cn.com/problems/smallest-string-with-swaps/

// reference: https://leetcode-cn.com/problems/smallest-string-with-swaps/solution/1202-jiao-huan-zi-fu-chuan-zhong-de-yuan-wgab/

class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // 同一个区间内的点可以无限交换，因此在同一个内的点按升序排序可以得到字典序
        // 最小的结果，因为区间之间有重叠区域就可以合并，具有传递性，合并之后就是同一个
        // 连通分量了
        // 可以使用并查集实现

        // 1.构造并查集
        if(pairs.size() <= 0){
            return s;
        }
        int n = s.length();
        UnionFind unionFind = new UnionFind(n);
        for(List<Integer> pair : pairs){
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 2.构造对应关系
        // 用map实现
        // key: 并查集中集合的代表元，并查集里面存放的是索引
        // value: 代表元所在的集合中的全部字符，用优先队列存放，最小堆
        char[] charArray = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>(n);
        for(int i = 0; i < n; i++){
            int root = unionFind.find(i);
            if(map.containsKey(root)){
                map.get(root).offer(charArray[i]);
            }
            else {
                PriorityQueue<Character> minHeap = new PriorityQueue<>();
                minHeap.offer(charArray[i]);
                map.put(root, minHeap);
            }
        }

        // 3.重构字符串
        // 遍历每个索引，从对应的并查集集合中取最小的字符，最后构成答案
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < n; i++){
            int root = unionFind.find(i);
            char c = map.get(root).poll();
            res.append(c);
        }
        return res.toString();
    }

    private class UnionFind{
        int[] parent;
        // 秩，类似于高度
        int[] rank;

        public UnionFind(int x){
            parent = new int[x];
            rank = new int[x];
            // 初始化
            for(int i = 0; i < x; i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // 路径压缩
        public int find(int x){
            if(x != parent[x]){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // 按秩归并
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return;
            }
            // 只有当“高度”相同的时候，合并才会导致rank增加
            if(rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            else if(rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
            }
            else if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }
        }
    }
}
