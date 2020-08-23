// site: https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/

// 字符串的全排列问题，会有重复字符所以需要考虑剪枝问题

// 可以通过交换的方式构成全排列，而不是常规的path添加（path添加也是可以的，就是判断重复剪枝比较复杂，需要排序 + visited数组记录）
// 将可选字符逐个交换到当前dfs的位置，然后进行下一个位置的递归（相当于这个位置之前的已经固定好了）
// 剪枝的时候可以在本次递归中创建一个set来记录，当set已经出现过了，即当前位置已经有同样的选择，则continue进行剪枝
class Solution {
    public String[] permutation(String s) {
        char[] charArray = s.toCharArray();
        List<String> res = new ArrayList<>();
        int len = s.length();
        dfs(0, charArray, len, res);
        // 转成字符串数组再return
        return res.toArray(new String[res.size()]);
    }
    /**
    * 递归dfs函数，通过交换char[]中的数组形成排列
    * @param pos 具体位置
    * @param charArray 字符数组
    * @param len 字符长度
    * @param res 结果list
    */
    public void dfs(int pos, char[] charArray, int len, List<String> res){
        // 递归结束条件
        // 当前位置等于长度-1，最后一个位置，因为前面n-1个数排列定下来了，最后一个数就定下来了
        if(pos == len - 1){
            // 加入结果
            // String类的静态方法
            res.add(String.valueOf(charArray));
        }
        // 当前位置的值从pos开始选择，进行递归（前面的已经固定了，从后面选）
        // 用一个HashSet来进行剪枝，防止重复
        HashSet<Character> set = new HashSet<>();
        for(int i = pos; i < len; i++){
            // 当前位置的一个选择已经出现过了（需要剪枝）
            if(set.contains(charArray[i])){
                continue;
            }
            // 加到set里面
            set.add(charArray[i]);
            // 交换到pos，即选择一个字符放到pos的位置
            swap(charArray, i, pos);
            // 递归下一个位置
            dfs(pos + 1, charArray, len, res);
            // 回溯，撤销swap，再交换一次就可以了
            swap(charArray, i, pos);
        }
    }
    public void swap(char[] arr, int x, int y){
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
