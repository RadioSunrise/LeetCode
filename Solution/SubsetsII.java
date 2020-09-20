// site: https://leetcode-cn.com/problems/subsets-ii/

// 子集问题2，nums中可能会有重复的数字，需要剪枝

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();
        // 排序，用于剪枝
        Arrays.sort(nums);
        dfs(0, nums, res, path);
        return res;
    }
    /**
    * begin搜索起点
    */
    public void dfs(int begin, int[] nums, List<List<Integer>> res, Deque<Integer> path){
        // 直接加入
        res.add(new LinkedList(path));
        for(int i = begin; i < nums.length; i++){
            // 剪枝，从begin + 1开始剪枝，因为搜索区间是从begin开始的
            if(i > begin && nums[i] == nums[i - 1]){
                continue;
            }
            path.addLast(nums[i]);
            dfs(i + 1, nums, res, path);
            path.removeLast();
        }
    }
}
