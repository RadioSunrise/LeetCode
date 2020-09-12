// site: https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/

// 反转字符串的单词顺序
// 用逆序添加的方式来做，双指针用于指示单词的位置，注意i和j的移动与更新
// 特殊情况包括" "也要考虑到
class Solution {
    public String reverseWords(String s) {
        // 去除首位空格，标记首位的非空格位置
        int len = s.length();
        if(len == 0){
            return "";
        }
        char[] charArr = s.toCharArray();
        int start = 0;
        int end = len - 1;
        while(start < len && charArr[start] == ' '){
            start++;
        }
        while(end > 0 && charArr[end] == ' ' ){
            end--;
        }
        // 倒序搜索
        // i 和 j 两个指用来指示单词的位置（边界）
        int i = end;
        int j = end;
        StringBuilder res = new StringBuilder();
        while(i >= start){
            while(i >= start && charArr[i] != ' '){
                i--;
            }
            // [i + 1 : j]是当前单词的边界
            res.append(s.substring(i + 1, j + 1));
            res.append(' ');
            // 跳过单词之间的空格
            while(i >= start && charArr[i] == ' '){
                i--;
            }
            // j指向i，即下一个单词的结尾位置
            j = i;
        }
        // 删除最后一个多余的空格
        return res.toString().trim();
    }
}
