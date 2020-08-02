// site: https://leetcode-cn.com/problems/permutations-ii/
import java.util.*;

/**
 * 全排列问题2，nums里面有重复的数字，返回的结果需要去重
 * 所以和全排列问题1不同，需要额外的剪枝
 */
public class PermutationsII {
        /**
     * 返回无重复的全排列
     * @param nums 数组，包含需要排列的元素，可以有重复
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        // 无重复全排列的结果
        List<List<Integer>> res = new ArrayList<>();
        // 保存中间结果用的path
        Deque<Integer> path = new ArrayDeque<>(n);
        // 记录是否访问过的数组
        boolean[] visited = new boolean[n];
        // 如果数组是排好序的，那么需要剪枝的排列一定是刚刚出现过的
        // 在搜索前排序也可以代替 不剪枝对搜索排列再去重的做法
        // 对nums排序
        Arrays.sort(nums);
        dfs(nums, 0, n, visited, res, path);
        return res;
    }

    public void dfs(int[] nums, int depth, int len, boolean[] visited, List<List<Integer>> res, Deque<Integer> path){
        // 递归结束条件
        // depth从0开始的，到len说明已经所有数字都排列完了
        if(depth == len){
            // 用new方法新创建一个ArrayList进去
            // 直接add(path)则一直都对同一个path在操作
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历visited数组找没有访问过的点
        for(int i = 0; i < len; i++){
            // 没有访问过的，可以尝试递归
            if(!visited[i]){
                // 判断是否需要剪枝
                // 剪枝的条件：因为nums进来之前已经有序，如果当前位置i和前一个位置i-1的相同
                //             且i-1刚刚被撤销（被回溯，visited为false）了，则需要剪枝
                // 如果不剪枝，刚刚被撤销的前一个相同的数字还有可能会被使用，就会重复
                if(i > 0 && (nums[i] == nums[i - 1]) && !visited[i - 1]){
                    // 剪枝
                    continue;
                }

                // 不需要剪枝，递归
                path.addLast(nums[i]);
                visited[i] = true;
                dfs(nums, depth + 1, len, visited, res, path);

                // 回溯，和dfs之前要一一对应
                visited[i] = false;
                path.removeLast();
            }
        }
    }
    public static void main(String[] args){
        PermutationsII solution = new PermutationsII();
        int[] nums = {1, 2, 1};
        List<List<Integer>> res = solution.permuteUnique(nums);
        System.out.println("[");
        for(List<Integer> arr : res){
            System.out.println(arr);
        }
        System.out.println("]");
    }
}
