// site: https://leetcode-cn.com/problems/increasing-subsequences/

package leetcode;

import java.util.*;

public class IncreasingSubsequences {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, nums);
        return res;
    }
    /**
     * dfs找符合条件的子序列
     * @param pos 当前递归的位置，从这个位置开始选数字
     */
    public void dfs(int pos, int[] nums){
        // pos越界了，或者last已经选完全部数字了
        if(pos >= nums.length){
            return;
        }
        // 本位置的一个Hashset
        HashSet<Integer> set = new HashSet<>();
        // 从last开始，尝试添加到path中
        for(int i = pos; i < nums.length; i++){
            // 当前位置没选过nums[i]
            if(!set.contains(nums[i])){
                if(path.size() == 0 || nums[i] >= path.getLast()){
                    // 加入path
                    path.addLast(nums[i]);
                    // System.out.println("path add " + nums[i] + ", now size is " + path.size());
                    // 加入res
                    if(path.size() >= 2){
                        res.add(new ArrayList(path));
                    }
                    // 加入set
                    set.add(nums[i]);
                    // 递归
                    dfs(i + 1, nums);
                    // 回溯
                    path.removeLast();
                    // System.out.println("path remove " + nums[i] + ", now size is " + path.size());
                }
            }
        }
    }
    public static void main(String[] args){
        int[] nums = new int[] {4, 6, 4, 6, 4};
        IncreasingSubsequences solution = new IncreasingSubsequences();
        List<List<Integer>> res = solution.findSubsequences(nums);
        System.out.println(res);
    }
}
