package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/evaluate-division/
 * leetcode 399 除法求值
 * 参考：https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/
 * 存在比值关系的变量可以化为同一个变量的倍数进行比值的计算，不能比的则不在一类，因此可以使用并查集的方法来实现
 * 存在比值关系的变量/点放在一类，再求出结果
 *
 * 比如equations和values中存在[a, b] , 2，则a的直接父节点为b，parent[a] = b，weight[a] = 2
 *                            [b, c], 3，则b的直接父节点为c，parent[b] = c，weight[b] = 3
 * 同一个集合中的两个变量，要求的比值就是在路径压缩之后，两条边权值的比值，因为路径压缩会让点的父节点直接指向
 * 该集合的根节点，即用同一个变量的倍数进行对比
 *
 * 关键的实现是在查询的时候路径压缩，更新权值；合并的时候也要更新权值，并且有可能要增加位置的边和权重
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationSize = equations.size();
        // 并查集最大的大小是equationSize的两倍
        UnionFind unionFind = new UnionFind(2 * equationSize);
        // 1.预处理，将变量值与数字id绑定
        Map<String, Integer> map = new HashMap<>(2 * equationSize);
        int id = 0;
        for(int i = 0; i < equationSize; i++){
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            if(!map.containsKey(var1)){
                map.put(var1, id);
                id++;
            }
            if(!map.containsKey(var2)){
                map.put(var2, id);
                id++;
            }
            // 并查集中先进行合并
            unionFind.union(map.get(var1), map.get(var2), values[i]);
        }
        // 进行查询
        int querySize = queries.size();
        double[] result = new double[querySize];
        for(int i = 0; i < querySize; i++){
            List<String> query = queries.get(i);
            String var1 = query.get(0);
            String var2 = query.get(1);
            // map里面没出现的变量则返回-1的ID
            int id1 = map.getOrDefault(var1, -1);
            int id2 = map.getOrDefault(var2, -1);
            // 获取结果
            if(id1 == -1 || id2 == -1){
                result[i] = -1.0d;
            }
            else {
                result[i] = unionFind.getResult(id1, id2);
            }
        }
        return result;
    }

    /**
     * 内部类 UnionFind并查集
      */
    private class UnionFind{
        /**
         * parent数组
         */
        private int[] parent;
        /**
         * weight数组，指向父节点的边的权重
         */
        private double[] weight;

        public UnionFind(int n){
            this.parent = new int[n];
            this.weight = new double[n];
            // 初始化
            for(int i = 0; i < n; i++){
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        /**
         * 合并函数
         * @param x 节点x
         * @param y 节点y
         * @param value x到y的边的权重值
         */
        public void union(int x, int y, double value){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return;
            }
            // 合并
            parent[rootX] = rootY;
            // 两条路可以到同一个点，value * weight[y]计算出x到rootY的总权值，再除以weight[x]得到weight[rootX]
            weight[rootX] = value * weight[y] / weight[x];
        }

        /**
         * 查找函数，包含路径压缩
         * @param x 要找根的节点
         * @return 返回根节点id
         */
        public int find(int x){
            if(x != parent[x]){
                // 递归查找，压缩路径
                // 暂存原本父节点
                int origin = parent[x];
                parent[x] = find(parent[x]);
                // 权重值连乘，因为递归的时候上层的以及乘完了，所以直接乘原本父节点的权值即可
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        /**
         * 返回比值，在同一个类则返回到父节点的权值，不在同一个类返回-1
         */
        public double getResult(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return (weight[x] / weight[y]);
            }
            else {
                return -1.0d;
            }
        }
    }
}
