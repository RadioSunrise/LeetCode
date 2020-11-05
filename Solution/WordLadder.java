// site: https://leetcode-cn.com/problems/word-ladder/submissions/

// 单词接龙，转化成图的最短步长问题，用bfs实现，需要建图，其中需要加入边和点，通过HashMap来加快建图
// 同时用虚拟节点，方便建图找邻接点（能够转换的点一定和同一个虚拟点相邻）
// 还不是很理解，bfs那一块要重新看

class Solution {
    // 哈希表，key是字符串，Integer是ID
    Map<String, Integer> map = new HashMap<>();
    // 邻接表表示图
    List<List<Integer>> edge = new ArrayList<>();
    // 节点个数
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 先建图
        for(String word: wordList){
            addEgde(word);
        }
        addEgde(beginWord);
        // 如果没有endWord的话那肯定不能转，返回0
        if(!map.containsKey(endWord)){
            return 0;
        }
        // dis数组，记录beginWord到每个词的步长
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = map.get(beginWord);
        int endId = map.get(endWord);
        dis[beginId] = 0;

        // bfs搜索
        // 队列里面是节点的ID
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(beginId);
        while(!queue.isEmpty()){
            int id = queue.poll();
            // 如果是endId则可以返回了
            if(id == endId){
                // 因为到endId之间还有虚拟节点，所以要除2再+1
                return dis[endId] / 2 + 1;
            }
            // id的邻域中逐个入队
            for(int idNode : edge.get(id)){
                // 本身不相连的点，bfs扩展之后向连
                if(dis[idNode] == Integer.MAX_VALUE){
                    dis[idNode] = dis[id] + 1;
                    // 入队
                    queue.offer(idNode);
                } 
            }
        }
        return 0;
    }
    /**
    * 增加一条新的边
    */ 
    public void addEgde(String word){
        addWord(word);
        // 获取id
        int id = map.get(word);
        // 添加虚拟字符串（每个位置都有26种可能，用*表示通配每次更改一个位）
        char[] arr = word.toCharArray();
        int length = arr.length;
        for(int i = 0; i < length; i++){
            char temp = arr[i];
            arr[i] = '*'; 
            String newWord = new String(arr);
            // 添加虚拟字符串
            addWord(newWord);
            int newID = map.get(newWord);
            // 加入到邻接表
            // 如果是两个可以转换的点，那么肯定有一个虚拟点同时在这两个点的邻接链内
            edge.get(id).add(newID);
            edge.get(newID).add(id);
            // 复原
            arr[i] = temp;
        }
    }

    /**
    * 增加一个新的词，为了方便利用HashMap来建表
    */
    public void addWord(String word){
        if(!map.containsKey(word)){
            // 保存这个字符串的ID
            map.put(word, nodeNum);
            nodeNum++;
            edge.add(new ArrayList<Integer>());
        }
        
    }
}
