// site: https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/

// 几个要注意的细节
// 1.回溯的思想
// 2.实现细节 (1)path问题，从头到尾用的同一个path对象，所以如果result.add那一步不复制的话，result的里面path都是同一个对象，返回根节点path就会被情况
//            (2)visited问题，用个HashSet或者boolean数组都可以的
//            (3)path问题，用ArrayList、LinkedList或者ArrayDeque都可以，在程序中只会在path的尾部进行增加和删除，所以ArrayDeque会比较简单和快

// 全排列问题
class Solution {
    /**
     * 最终的结果
     */
    static List<List<Integer>> result;
    /**
     * 标记哪些数字已经访问过了
     */
    static HashSet<Integer> visited;

    /**
     * 存放路径用的path
     * 因为我们只会在path的末尾进行插入和删除，相当于一个栈
     * 所以用ArrayDeque比较好，LinkedList和ArrayList实现List接口，比较复杂
     */
    static ArrayDeque<Integer> path;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>(nums.length);
        visited = new HashSet<>();
        path = new ArrayDeque<>(nums.length);
        dfs(nums, 0);
        return result;
    }
    /**
     * 递归函数
     * @param nums 需要全排列的数组
     * @param depth 记录当前深度
     */
    public void dfs(int[] nums, int depth){
        // 深度是[0, n-1]
        // depth = nums.length表示超过叶节点
        if (depth == nums.length){
            // 需要拷贝一份path再加进去
            // 因为一直用的同一个path，每次回到根节点都会清空path
            // 因为按值传递的关系，res中的path也一直是同一个对象
            // 所以每一次都把result里面的path清空了
            // ArrayList的构造函数可以用Collection类的对象作为参数，ArrayDeque也是可以的
            result.add(new ArrayList<>(path));
            return;
        }
        // 找一个没有访问过的点，插入到path中
        for(int i = 0; i < nums.length; i++){
            if(!visited.contains(i)){
                visited.add(i);
                // 加入到path的末尾
                path.addLast(nums[i]);
                dfs(nums, depth + 1);
                // 本轮dfs结束，回溯操作
                // 清除访问和path的元素
                visited.remove(i);
                // ArrayDeque的remove是删除head, removeLast是删除最后一个
                path.removeLast();
            }
        }
    }
}
