// https://leetcode-cn.com/problems/subsets/

// 求给定数组nums的全部子集

// 用生成mask的方式来求, nums中无重复数字
// mask的范围从0到(2^n - 1)，用&根据二进制位来把数字加入子集
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int n = nums.length;
        if(n == 0){
            return res;
        }
        int total = (int)Math.pow(2, n);
        for(int mask = 0; mask < total; mask++){
            int temp = mask;
            List<Integer> subset = new LinkedList<>();
            int pos = 0;
            while(temp > 0){
                if((temp & 1) == 1){
                    subset.add(nums[pos]);
                }
                temp = temp >>> 1;
                pos++;
            }
            res.add(subset);
        }
        return res;
    }
}

// 回溯法1
// for循环枚举要选择的数字
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();
        if (nums.length == 0){
            return res;
        }
        dfs(0, nums, res, path);
        return res;
    }
    public void dfs(int begin, int[] nums, List<List<Integer>> res, Deque<Integer> path){
        // 直接add进去，如果是begin等于nums.length才加的话，会漏加
        res.add(new LinkedList(path));
        for(int i = begin; i < nums.length; i++){
            path.addLast(nums[i]);
            dfs(i + 1, nums, res, path);
            path.removeLast();
        }
    }
}

// 回溯法2，每个数字的选或者不选
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();
        if (nums.length == 0){
            return res;
        }
        dfs(0, nums, res, path);
        return res;
    }
    /**
    * dfs回溯，每个数字可以选或不选
    * @param index 当前index的数字，[0 : n - 1]
    */
    public void dfs(int index, int[] nums, List<List<Integer>> res, Deque<Integer> path){
        if(index == nums.length){
            res.add(new LinkedList(path));
            return;
        }

        // 当前数字的选或者不选

        // 1.选，先加入再回溯
        path.add(nums[index]);
        dfs(index + 1, nums, res, path);
        path.removeLast();

        // 2.不选，直接递归
        dfs(index + 1, nums, res, path);
    }
}
