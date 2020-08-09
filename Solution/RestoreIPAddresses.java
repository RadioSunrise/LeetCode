// https://leetcode-cn.com/problems/restore-ip-addresses/

// 根据一个字符串划分IP地址，DFS回溯
// 关键是剪枝的判断
// 1. 在这个位置划分，剩下的数字会不会太多
// 2. 长度越界
// 3. 数字不满足范围，（包括前导0）

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        // dfs做法
        int len = s.length();
        // 首先len先判断，太小或者太大就直接返回
        if(len < 4 || len > 12){
            return res;
        }
        Deque<String> path = new ArrayDeque<>();
        dfs(s, len, 0, 0, res, path);
        return res;
    }
    /**
    * dfs枚举分割点
    * @param String
    * @param len
    * @param depth
    * @param startPos
    * @param result
    * @param path
    */
    public void dfs(String s, int len, int depth, int startPos, List<String> result, Deque<String> path){
        // 递归结束条件
        // 所以字符都用完了，而且已经分成4段了
        if(startPos == len && depth == 4){
            result.add(String.join(".", path));
        }
        // 枚举分割点，最多只能移动三个位置
        for(int i = startPos; i < startPos + 3; i++){
            // 判断这个分割点行不行
            // 1.长度越界
            if(i >= len){
                break;
            }
            // 2.分割完剩下的数字太多了
            if((len - i) > ((4 - depth)) * 3){
                continue;
            }
            // 3.判断从startPos到i之间是不是一个和范围的数
            // 如果满足数字范围的话就继续回溯
            if(validSubString(s, startPos, i)){
                // 加到path里面
                path.addLast(s.substring(startPos, i+1));
                // 递归的startPos是i+1
                dfs(s, len, depth + 1, i + 1, result, path);
                // 回溯
                path.removeLast();
            }
        }
    }
    /**
    * 判断一个subString，从[left, right]是不是一个合适范围的数字
    */
    public boolean validSubString(String s, int left, int right){
        // 判断前导0的情况
        // 如果left是0，而长度大于1
        if((right - left + 1) > 1 && s.charAt(left) == '0'){
            return false;
        }
        int num = Integer.parseInt(s.substring(left, right + 1));
        if(num < 0 || num > 255){
            return false;
        }
        return true;
    }
}
