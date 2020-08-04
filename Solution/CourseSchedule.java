// site: https://leetcode-cn.com/problems/course-schedule/

// 第一种直接的方法，进行拓扑排序，根据排序出来的点的个数判断是不是有向无环图图
// BFS + 拓扑排序
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // List版的邻接表
        List<List<Integer>> matrix = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            matrix.add(new ArrayList<>());
        }
        // 统计入度
        int[] inDegree = new int[numCourses];
        int edges = prerequisites.length;
        if(edges == 0){
            return true;
        }
        for(int[] edge: prerequisites){
            // 放进邻接表
            matrix.get(edge[1]).add(edge[0]);
            // 计算入度
            inDegree[edge[0]] ++;
        }
        // BFS拓扑排序
        // 统计已经拓扑排序好的节点个数
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        // 入度为0放进队列
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }
        if(queue.size() == 0){
            return false;
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                count++;
                // 获取课程
                int course = queue.poll();
                // 把这个课程的后继课程的入度减1
                List<Integer> nextCourse = matrix.get(course);
                for(int next : nextCourse){
                    inDegree[next] --;
                    if(inDegree[next] == 0){
                        queue.offer(next);
                    }
                }

                /**
                for(int j = 0; j < edges; j++){
                    if(prerequisites[j][1] == course){
                        inDegree[prerequisites[j][0]] --;
                        // 减完之后发现入度为0就加入队列
                        if(inDegree[prerequisites[j][0]] == 0){
                            queue.offer(prerequisites[j][0]);
                        }
                    }
                }
                */
            }
        }
        return (count == numCourses);
    }
}

// 第二种方法：DFS判断是否存在环
// DFS判断环需要一个辅助的flag标志
// 0表示未被访问, 1表示已被本轮DFS访问, -1表示已经被之前的DFS访问过
// 遇到1说明在同一轮dfs里面重复出现，有换。-1则表示这个节点被之前的DFS（其他起点的DFS）访问过，这个节点无环
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // List版的邻接表
        List<List<Integer>> adajoint = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adajoint.add(new ArrayList<>());
        }
        for(int[] edge: prerequisites){
            // 放进邻接表
            adajoint.get(edge[1]).add(edge[0]);
        }
        // 标志数组flag
        // 0表示未被访问
        // 1表示已被本轮DFS访问
        // -1表示已经被之前的DFS访问过
        int[] flag = new int[numCourses];

        // 每个点为起点，DFS 判断环
        // 有一个为false就返回false
        for(int i = 0; i < numCourses; i++){
            if(! dfsCir(adajoint, flag, i)){
                return false;
            }
        }
        // 返回true
        return true;
    }
    public boolean dfsCir(List<List<Integer>> adajoint, int[] flag, int vertex){
        // 结束条件
        // 1.已经被之前的DFS访问过了，在这个位置不会出现环，返回true
        if(flag[vertex] == -1){
            return true;
        }
        // 2.已被本轮DFS访问过，现在又被访问了，说明存在环，返回false
        if(flag[vertex] == 1){
            return false;
        }
        // flag = 0，还没被访问过
        // 先置1，表示本轮访问了
        flag[vertex] = 1;
        // 对临界点依次dfs，如果有false的就返回false
        List<Integer> nextCourse = adajoint.get(vertex);
        for(int next : nextCourse){
            if(! dfsCir(adajoint, flag, next)){
                return false;
            }
        }
        // for结束还没退出说明没环，本轮dfs结束，置为-1
        flag[vertex] = -1;
        return true;
    }
}
// 第一版不建立额外结构的版本
// 比较慢
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 统计入度
        int[] inDegree = new int[numCourses];
        int edges = prerequisites.length;
        if(edges == 0){
            return true;
        }
        for(int i = 0; i < edges; i++){
            inDegree[prerequisites[i][0]] ++;
        }
        // BFS拓扑排序
        // 统计已经拓扑排序好的节点个数
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }
        if(queue.size() == 0){
            return false;
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                count++;
                // 获取课程
                int course = queue.poll();
                // 把这个课程的后继课程的入度减1
                for(int j = 0; j < edges; j++){
                    if(prerequisites[j][1] == course){
                        inDegree[prerequisites[j][0]] --;
                        // 减完之后发现入度为0就加入队列
                        if(inDegree[prerequisites[j][0]] == 0){
                            queue.offer(prerequisites[j][0]);
                        }
                    }
                }
            }
        }
        return (count == numCourses);
    }
}
