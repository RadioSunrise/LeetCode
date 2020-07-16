// site: https://leetcode-cn.com/problems/is-graph-bipartite

// 判断一个图是否为二分图，用染色法
// 如果给定的无向图连通，那么我们就可以任选一个节点开始，给它染成红色。
// 随后我们对整个图进行遍历，将该节点直接相连的所有节点染成绿色，表示这些节点不能与起始节点属于同一个集合。
// 我们再将这些绿色节点直接相连的所有节点染成红色，以此类推，直到无向图中的每个节点均被染色。

// 如果我们能够成功染色，那么红色和绿色的节点各属于一个集合，这个无向图就是一个二分图；
// 如果我们未能成功染色，即在染色的过程中，某一时刻访问到了一个已经染色的节点，并且它的颜色与我们将要给它染上的颜色不相同，也就说明这个无向图不是一个二分图。

// DFS遍历
class Solution {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;
    private boolean vaild; 
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        vaild = true;
        // 对每个点没染色的都进行遍历
        for(int i = 0; i < n && vaild; i++){
            // 没染色且没结束，染成红色
            if(color[i] == UNCOLORED){
                dfs(i, RED, graph);
            }
        }
        return vaild;
    }
    /**
    * @param node 进入dfs的结点
    * @param c 将node染成color的颜色
    * @param graph 邻接表表示的图
    */
    public void dfs(int node, int c, int[][] graph){
        // 将自己染色
        color[node] = c;
        // 相邻结点要染成另一种颜色
        int cNei = (c == RED) ? GREEN : RED;
        // 遍历邻接表对应的行
        for(int neighbor : graph[node]){
            
            // 判断neighbor现在的颜色和想染的颜色是否一致
            // 即 当邻接点的颜色和自己一样，为false
            if(color[neighbor] == c){
                vaild = false;
                return;
            }
            // 没染色
            else if(color[neighbor] == UNCOLORED){
                dfs(neighbor, cNei, graph);
                /*
                if (!vaild) {
                    return;
                }
                */
            }
        }
    }
}

// BFS做法
// 每个连通分支都开一个队列
// 第一个点在入队之前染色
// 每次有结点出队，就按照结点的颜色判断邻居颜色cNei，遍历邻居结点，染色再入队
// 染过cNei颜色就不用入队了，相当于visited数组那样
class Solution {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        // 对每个点没染色的都进行遍历
        for(int i = 0; i < n; i++){
            if(color[i] == UNCOLORED){
                // 每个连通分支开一个队列
                Queue<Integer> queue = new LinkedList<>();
                // 入队 + 染成红色
                queue.offer(i);
                color[i] = RED;
                while(!queue.isEmpty()){
                    // 获取对头结点处理
                    int currNode = queue.poll();
                    int cNei = (color[currNode] == RED)? GREEN : RED;
                    // 遍历相邻点
                    for(int neighbour : graph[currNode]){
                        // 相邻结点没染色
                        if(color[neighbour] == UNCOLORED){
                            // 染色 + 入队
                            color[neighbour] = cNei;
                            queue.offer(neighbour);
                        }
                        // 已经染了其他颜色，不是二分图
                        else if(color[neighbour] != cNei){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
