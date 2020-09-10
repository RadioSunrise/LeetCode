package leetcode;

import java.util.*;

/**
 * 组合总数3
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, n, k, path, res);
        return res;
    }
    public void dfs(int begin, int target, int k, Deque<Integer> path, List<List<Integer>> res){
        if(path.size() == k && target == 0){
            res.add(new LinkedList<>(path));
            return;
        }
        else if(path.size() == k){
            return;
        }
        for(int i = begin; i <= 9; i++){
            // 剩下的数选最大的(k - path.size())个都不够target，return
            int size = path.size();
            int maxSum = (((9 - (k - size)) + 1) + 9) * (k - size) / 2;
            if(i > target || maxSum < target){
                return;
            }
            path.addLast(i);
            dfs(i + 1, target - i, k, path, res);
            path.removeLast();
        }
    }
    public static void main(String[] args){
        int k = 3;
        int n = 7;
        List<List<Integer>> res = new CombinationSum3().combinationSum3(k, n);
        System.out.println(res.toString());
    }
}
