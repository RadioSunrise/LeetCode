// site: https://leetcode-cn.com/problems/generate-parentheses/

// 生成括号，用递归 + 回溯法来做
// dfs
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if(n <= 0){
            return res;
        }
        StringBuilder path = new StringBuilder();
        dfs(path, 0, 0, n, res);
        return res;
    }

    /**
     *
     * @param path
     * @param left 用了多少个左括号
     * @param right 用了多少个右括号
     * @param n
     * @param res
     */
    public void dfs(StringBuilder path, int left, int right, int n, List<String> res){
        if(left == n && right == n){
            res.add(path.toString());
            return;
        }
        // 右括号比左括号大，不合法
        if(left < right){
            return;
        }
        if(left < n){
            path.append('(');
            dfs(path, left + 1, right, n, res);
            // backtrack
            path.setLength(path.length() - 1);
        }
        if(right < n){
            path.append(')');
            dfs(path, left, right + 1, n, res);
            path.setLength(path.length() - 1);
        }
    }
}

// bfs也是可以的，但是会比较烦
