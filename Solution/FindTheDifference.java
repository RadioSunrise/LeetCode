// site: https://leetcode-cn.com/problems/find-the-difference/

// 给定两个字符串 s 和 t，它们只包含小写字母。
// 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
// 请找出在 t 中被添加的字母。

// 数组统计，时间O(N)空间O(N)
class Solution {
    public char findTheDifference(String s, String t) {
        // 数组实现
        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[(int)(c - 'a')]++;
        }
        for(char c : t.toCharArray()){
            if((--count[(int)(c - 'a')]) < 0){
                return c;
            }
        }
        return '0';
    }
}

// ASCII码求和
class Solution {
    public char findTheDifference(String s, String t) {
        // ASCII码求和
        int as = 0;
        int at = 0;
        for(char c : s.toCharArray()){
            as += (int)c;
        }
        for(char c : t.toCharArray()){
            at += (int)c;
        }
        return (char) (at - as);
    }
}

// 异或找出现1次的数字
class Solution {
    public char findTheDifference(String s, String t) {
        // 位运算异或
        // 两个串合起来，类似于求只出现1次的数字
        int res = 0;
        for(char c : s.toCharArray()){
            res ^= (int)c;
        }
        for(char c : t.toCharArray()){
            res ^= (int)c;
        }
        return (char) (res);
    }
}
