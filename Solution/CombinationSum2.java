package leetcode;

import java.util.*;

/**
 * 组合总数2，数组里面有重复元素，每个数字只能用一次，组合中重复数字各自计算出现次数
 * 如[1,2,3,4], target = 4, 则结果可以是[1,1,2]但不能是[1,1,1,1]
 * 由于有重复数字，所以可能会出现同层相同选择，会重复，所以要通过剪枝来剪去这些重复的选择
 * 和组合总数1一样通过排序来剪枝，除了是为了剪去总和必定大于target的情况外，还要用来处理重复组合的问题
 * 组合问题中使用了begin来指定搜索区间，因此要在区间中考虑重复，如果和前一个位置相同则跳过
 * 所以在枚举的过程中所以要求i要大于begin，i > 0 的话考虑的是原数组中的相同，而不是本层的相同数值
 * begin指定的是搜索的起点，不要和搜索区间前面的值比较
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        int len = candidates.length;
        if (len == 0 || target <= 0) {
            return res;
        }
        // sort
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(0, candidates, target, path, res);
        return res;
    }
    /**
     * dfs + 回溯
     * @param begin 搜索起点
     * @param candidates
     * @param target
     * @param path
     * @param res
     */
    public void dfs(int begin, int[] candidates, int target, Deque<Integer> path, List<List<Integer>> res){
        if(target == 0){
            res.add(new LinkedList<>(path));
            return;
        }
        if(begin >= candidates.length){
            return;
        }
        // search from begin
        for(int i = begin; i < candidates.length; i++){
            // cut
            // 如果当前数字都比target大了，就不要搜索了，这个位置和后面的数字都不行的
            if (candidates[i] > target){
                return;
            }

            // skip the same chose in the same layer
            // 同层相同，所以要求i要大于begin
            // i > 0 的话考虑的是原数组中的相同，而不是本层的相同数值
            // begin指定的是搜索的起点，不要和搜索区间前面的值比较
            if(i > begin && (candidates[i] == candidates[i - 1])){
                continue;
            }
            // after sort
            path.addLast(candidates[i]);
            // next round search form (i + 1)
            dfs(i + 1, candidates, target - candidates[i], path, res);
            // backtrack
            path.removeLast();
        }
    }
    public static void main(String[] args){
        int[] nums = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> res = new CombinationSum2().combinationSum2(nums, target);
        System.out.println(res.toString());
    }
}
