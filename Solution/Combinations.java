package leetcode;

import java.util.*;

// 组合问题，关键是去重，和怎么剪枝使得求解更快

/**
 * 给两个数n和k，返回 1...n 中所以可能的K个数的组合
 *
 * 除了搜索下界之外，还有搜索上界，如n = 15， k = 4，当前已经选择了1个，即path.size() = 1
 * 剩余的三个位置，最后的一组是{13,14,15}，所以搜索区间到13就可以了，不用到14或者15
 * 选了14或者15的话，剩下数是不够的
 *
 * 因此根据当前已经选取的数量path.size、总共要选的数量k、总数n可以得出，搜索上界upper有
 * upper = n - 剩余要选的数量 + 1
 *       = n - (k - path.size()) + 1
 *
 * @author dell
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if(n <= 0 || k <= 0){
            return res;
        }
        boolean[] visited = new boolean[n];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, k, n, path, res);
        return res;
    }
    /**
     * dfs回溯，注意要求的是组合，要去重
     * @param begin 搜索起点，从哪个数开始搜索
     * @param k
     * @param n
     * @param path
     * @param res
     */
    public void dfs(int begin, int k, int n, Deque<Integer> path, List<List<Integer>> res){
        // 递归结束条件
        // 够k个了
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        // 从begin开始搜索
        // 到upper
        int upper = n - (k - path.size()) + 1;
        for(int i = begin; i <= upper; i++){
            // 加入path，标记已经访问过了
            path.addLast(i);
            // 递归
            dfs(i + 1, k, n, path, res);
            // 回溯
            path.removeLast();
        }
    }
    public static void main(String[] args){
        int n = 4;
        int k = 2;
        List<List<Integer>> res = new Combinations().combine(n, k);
        System.out.println(res.toString());
    }
}

// 原始版本
// 利用搜索起点（下界）剪枝，可以减少计算量，因为定了搜索起点begin，所以不会找到前面的点，所以不需要visited数组来标识那些访问过
// 不过缺少了搜索上界
/**
 * 给两个数n和k，返回 1...n 中所以可能的K个数的组合
 * @author dell
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if(n <= 0 || k <= 0){
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, k, n, path, res);
        return res;
    }
    /**
     * dfs回溯，注意要求的是组合，要去重
     * @param begin 搜索起点，从哪个数开始搜索
     * @param k
     * @param n
     * @param path
     * @param res
     */
    public void dfs(int begin, int k, int n, Deque<Integer> path, List<List<Integer>> res){
        // 递归结束条件
        // 够k个了
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        // 从begin开始搜索
        for(int i = begin; i <= n; i++){
            // 加入path，标记已经访问过了
            path.addLast(i);
            // 递归
            dfs(i + 1, k, n, path, res);
            // 回溯
            path.removeLast();
        }
    }
    public static void main(String[] args){
        int n = 4;
        int k = 2;
        List<List<Integer>> res = new Combinations().combine(n, k);
        System.out.println(res.toString());
    }
}
