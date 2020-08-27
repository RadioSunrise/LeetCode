// site: https://leetcode-cn.com/problems/reconstruct-itinerary/

// 重新规划飞行路径，其实是找欧拉路径，即从一个顶点出发有一个路径可以不重复地遍历所以的边
// 其题目要求字典序的欧拉路径，所以会按照节点标志

// dfs来实现，关键在于什么时候将一个节点加入结果集，如果按照dfs的访问顺序来加入，第一次遇到孤岛节点（没有出度的节点），就会加入结果集
// 但是走到孤岛节点之后就无法回到其他节点了，即不符合欧拉路径的要求，所以加入结果集的顺序应该采用逆序加入（首部插入），遇到孤岛节点放在当前结果路径的首部
// 在最后顺序读取结果路径的时候，孤岛路径就在最后，则是正确的欧拉路径。
// 当然也可以res.add(start)，最后再逆序
// 由于半欧拉图和欧拉图的特性，这样的孤岛节点最多只会有1个，所以每一个节点，只会有一个死胡同节点
// 过程中记得删除遍历过的行程

// 第一版代码，需要对出度集进行排序
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<>();
        // 先把图构建出来
        // 一个HashMap，key是String，value是List
        HashMap<String, List<String>> graph = new HashMap<>();
        for(List<String> path : tickets){
            if(!graph.containsKey(path.get(0))){
                // 没遇到过的出发点
                List<String> list = new LinkedList<>();
                list.add(path.get(1));
                graph.put(path.get(0), list);
            }
            else {
                // 遇到过的，直接加入出度集
                graph.get(path.get(0)).add(path.get(1));
            }
        }
        // 按目的顶点排序
        for(List<String> list : graph.values()){
            list.sort(String::compareTo);
        }
        // dfs
        dfs("JFK", graph, res);

        return res;
    }
    /**
    * dfs遍历
    * @param start 起始位置
    * @param graph 图
    * @param res 最终结果 
    */
    public void dfs(String start, HashMap<String, List<String>> graph, List<String> res){
        // 获取这个startPos出发的list
        List<String> path = graph.get(start);
        // 因为排序过的list，按顺序选就是字典序
        while(path != null && path.size() > 0){
            String dest = path.get(0);
            // 删除这个行程
            path.remove(0);
            dfs(dest, graph, res);
        }
        // 这个点的出度边都遍历完了，加入res
        // 逆序插入（首部插入）
        res.add(0, start);
    }
}

// 第二版：第一版的改进，出度集用最小堆即优先队列来存，就不用排序了
// 删除遍历过的边也可以用出队操作来代替
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<>();
        // 先把图构建出来
        // 一个HashMap，key是String，value用优先队列不同排序
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for(List<String> path : tickets){
            String src = path.get(0);
            String dest = path.get(1);
            // 没遇到过的出发点
            if (!graph.containsKey(src)) {
                graph.put(src, new PriorityQueue<String>());
            }
            // 加入图
            graph.get(src).offer(dest);
        }
        // dfs
        dfs("JFK", graph, res);

        return res;
    }
    /**
    * dfs遍历
    * @param start 起始位置
    * @param graph 图
    * @param res 最终结果 
    */
    public void dfs(String start, Map<String, PriorityQueue<String>> graph, List<String> res){
        // 获取这个startPos出发的list
        PriorityQueue<String> path = graph.get(start);
        // 因为排序过的list，按顺序选就是字典序
        while(path != null && path.size() > 0){
            // 优先队列出队，删除这个行程
            String dest = path.poll();
            dfs(dest, graph, res);
        }
        // 这个点的出度边都遍历完了，加入res
        res.add(0, start);
    }
}
