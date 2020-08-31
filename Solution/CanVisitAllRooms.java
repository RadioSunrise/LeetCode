// site: https://leetcode-cn.com/problems/keys-and-rooms/submissions/

// 联通图问题 或者 孤岛问题
// bfs 或者 dfs 遍历看节点数量 或者 visited情况 就可以判断了

// rooms就是邻接表，只是不完整，但是可以当邻接表用

// dfs递归做法
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        // visited数组，防止死循环
        boolean[] visited = new boolean[N];
        // 从0开始dfs
        dfs(0, rooms, visited);
        // 检查是否有孤岛节点
        for(boolean b : visited){
            if(!b){
                return false;
            }
        }
        return true;
    }
    public void dfs(int room, List<List<Integer>> rooms, boolean[] visited){
        // 结束条件
        // 访问过了，退出
        /*if(visited[room]){
            return;
        }*/
        visited[room] = true;
        // 遍历相邻room
        for(int neighbour : rooms.get(room)){
            if(!visited[neighbour]){
                dfs(neighbour, rooms, visited);
            }
        }
    }
}

// bfs
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        // visited数组，防止死循环
        boolean[] visited = new boolean[N];
        // bfs
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int count = 1;
        while(!queue.isEmpty()){
            int room = queue.poll();
            for(int neighour : rooms.get(room)){
                if(!visited[neighour]){
                    visited[neighour] = true;
                    // 增加访问计数
                    count++;
                    queue.offer(neighour);
                }
            }
        }
        return (count == N);
    }
}
