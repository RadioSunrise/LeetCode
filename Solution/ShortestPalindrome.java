// https://leetcode-cn.com/problems/shortest-palindrome/

// 最短回文串，在给定串之前加字符，使得新字符串是最短的回文串

// 类似暴力做法，枚举s的分割点，最后一个用例会超时
// 因为判断t1是不是回文的时候会有重复，即找最长回文前缀t1的花费时间是主要问题
// 暴力法是O(n^2)的
class Solution {
    public String shortestPalindrome(String s) {
        // 用拼接字符串的想法来实现
        // s1 + s2 是一个回文串的特性，此处 len(s1) < len(s2)
        // 将 s2 划分为 t1 和 t2
        // 则 s1 为t2的逆序，而t1是一个回文串
        char[] charArr = s.toCharArray();
        int len = s.length();
        /*
        if(checkPalindrome(charArr, 0, len - 1)){
            return s;
        }
        */
        StringBuilder res = new StringBuilder();
        // 枚举分割点，直到t1是一个回文串为止
        for(int i = len - 1; i >= 0; i--){
            // t1是回文
            if(checkPalindrome(charArr, 0, i)){
                break;
            }
            // 还不是回文，将字符加到res中
            else {
                res.append(charArr[i]);
            }
        }
        // 接上s2
        res.append(s);
        return res.toString();
    }
    /**
    * 判断一个串的left到right是不是一个回文串
    * @param word 字符串的char数组
    * @param left 左边界
    * @param right 右边界
    */
    public boolean checkPalindrome(char[] word, int left, int right){
        int len = right - left + 1;
        for(int i  = left; i < len / 2; i++){
            if(word[left + i] != word[right - i]){
                return false;
            }
        }
        return true;
    }
}


// KMP 做法
// 因为问题是求最长回文前缀t1，则s的逆序s'中t1'就是后缀
// 考虑拼接字符串 s + s' ，问题就变成找最长相同前缀后缀长度，就是KMP中next数组的工作了
// 为了避免s = s'出现最长前缀出错的情况，如 s = "aaa"， s' = "aaa"，则 s + s' = "aaaaaa", next[2*len - 1]则是6，其实我们想要的是3
// 所以在s + s'中间加上一个分隔符"#"
class Solution {
    public String shortestPalindrome(String s) {
        // 拼接: s # s'
        String catchS = s + "#" + new StringBuilder(s).reverse();
        int maxPali = KMPLast(catchS);
        // maxPali是t1的长度，将t2部分逆序加到s上
        // 剩余部分用subString获取，maxPali指示是最长相同前缀后缀的长度，如"aacecaaa"对应的maxPali是7，subString从位置7开始取(位置从0开始)
        String t2Reverse = new StringBuilder(s.substring(maxPali + 1)).reverse().toString();
        return t2Reverse + s;
    }
    /**
    * 返回拼接字符串的next数组最后一位，即整个拼接串的最长相同前缀后缀长度
    */
    public int KMPLast(String catchS){
        int n = catchS.length();
        int[] next = new int[n];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while(j < n - 1){
            if(k == -1 || catchS.charAt(k) == catchS.charAt(j)){
                k++;
                j++;
                // 之前的next[j]就是k，next[j+1] = next[j] + 1 = k + 1
                next[j] = k;
            }
            else {
                k = next[k];
            }
        }
        // next[n - 1]是位置，next[n - 1] + 1才是长度
        return next[n - 1];
    }
}
