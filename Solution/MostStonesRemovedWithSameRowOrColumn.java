// site: https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/

/**
* 用并查集找到连通分量的个数
**/
class MostStonesRemovedWithSameRowOrColumn {
    public int removeStones(int[][] stones) {
        UnionFind unionfind = new UnionFind();
        for(int [] stone : stones){
            // 合并的是横坐标 和 纵坐标
            // 保持不能相等，所以要将其中一个坐标进行映射
            unionfind.union(stone[0] + 10001, stone[1]);
        }
        // 石头个数 - 连通分量个数
        return stones.length - unionfind.getCount();
    }
    /**
    * 并查集
    */
    private class UnionFind{
        // 并查集的底层数组要保存不同的行坐标和列坐标
        // 因为会把二维坐标的行和列"分开"，映射到其中一个映射到[0, 10000]不重合的区间
        // 用数组的话需要知道有多少个不同的横坐标和纵坐标，所以底层用Map来实现
        // key:一维化的坐标，value:并查集中的根节点（父节点）
        // 父节点的意思是 某一行 或者 某一列，一个二维坐标的石头会被放到这一行或者这一列中
        Map<Integer, Integer> parent;
        // count表示连通分量的个数
        int count;
        public UnionFind(){
            this.parent = new HashMap<>();
            this.count = 0;
        }

        /**
        * Find 操作
        */
        public int find(int x){
            // 遇到一个没出现过的坐标，则新增这个坐标，让父节点为他自己
            if(!parent.containsKey(x)){
                parent.put(x, x);
                // 连通分量+1
                count++;
            }
            // 路径压缩
            if(x != parent.get(x)){
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return;
            }
            else {
                parent.put(rootX, rootY);
                // 连通分量 - 1
                count--;
            }
        }

        public int getCount(){
            return count;
        }
    }
}
