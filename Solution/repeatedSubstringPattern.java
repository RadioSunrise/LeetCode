// site: https://leetcode-cn.com/problems/repeated-substring-pattern/

// 判断一个字符串能否由其子串重复构成

// 双倍化
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // 双倍字符串法
        // 如果s含有循环节，则s可以写成 s = s' s' s'... s' s' s'
        // 双倍化， 即(s+s)，如果含有循环节，则在s + s中去掉第一个字符和最后一个字符，变成S2
        // s 仍是 s2的子串，因为有循环节
        
        // 用自带的indexOf函数实现
        // index从1开始找
        int pos = (s + s).indexOf(s, 1);
        // 如果出现的不等于等于s.length()就说明有循环节，相当于不考虑最后一个字符
        return pos != s.length();
    }
}

// KMP
// 双倍化的indexOf用自己写的kmp来代替
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // 双倍字符串法
        // 如果s含有循环节，则s可以写成 s = s' s' s'... s' s' s'
        // 双倍化， 即(s+s)，如果含有循环节，则在s + s中去掉第一个字符和最后一个字符，变成S2
        // s 仍是 s2的子串，因为有循环节
        
        // kmp实现indexOf
        // 且出现位置不等于pattern.size才返回true
        int pos = kmp(s + s, s);
        if(pos == -1 || pos == s.length()){
            return false;
        }
        return true;
    }
    /**
    * specialKMP，从1位开始找，返回出现位置
    * @param query 查询串
    * @param pattern 模式串
    */
    public int kmp(String query, String pattern){
        int qLen = query.length();
        int pLen = pattern.length();
        // 求解next数组
        int[] next = new int[pLen];
        getNext(next, pattern);
        // kmp查找
        // 首位不要，i从1开始
        int i = 1; 
        int j = 0;
        while(i < qLen && j < pLen){
            // 相等或者j = -1，即没有前缀
            if(j == -1 || query.charAt(i) == pattern.charAt(j)){
                ++i;
                ++j;
            }
            else{
                j = next[j];
            }
        }
        // 模式串匹配完了
        if(j == pLen){
            // 返回出现的位置
            return (i - j);
        }
        return -1;
    }
    /**
    * 获取kmp算法的next数组
    */
    public void getNext(int[] next, String pattern){
        int pLen = pattern.length();
        // 设置首位为-1
        next[0] = -1;
        // k是next数组中的指向位置
        int k = -1;
        int j = 0;
        while(j < pLen - 1){
            if(k == -1 || pattern.charAt(k) == pattern.charAt(j)){
                ++k;
                ++j;
                // 原本是next[j + 1] = next[j] + 1 = k + 1
                // 现在k和j都+1了
                next[j] = k;
            }
            // 前缀不相等
            else {
                // 递归回到上一步
                k = next[k];
            }
        }
    }
}

// 暴力枚举
// 因为要重复构成，所以子串的起点必定是s[0]，所以可以通过枚举字符串的结尾来做，等价于枚举子串长度
// 优化的地方：整数倍长度，长度枚举到n/2就可以，比较是否相等的时候用s[j] 和 s[j - i]， 不需要从头开始比较
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // 暴力法
        // 枚举子串位置，因为子串一定是从开头开始的，所以枚举子串的结尾就可以
        // 两个条件: 1.整体长度是子串长度的整数倍 2.符合前缀（不需要跟开头比较，跟前一"节"比较即可）
        int n = s.length();
        // 至少重复一次，所以遍历到 (n / 2)就可以了
        for(int i = 1; i * 2 <= n; i++){
            // 1.第一个条件整数倍
            if(n % i == 0){
                boolean vaild = true;
                // 从i开始逐个检查
                for(int j = i; j < n; j++){
                    // 以i为一节，j和j-i比较就可以了
                    if(s.charAt(j) != s.charAt(j - i)){
                        vaild = false;
                        break;
                    }
                }
                if(vaild){
                    return true;
                }
            }
        }
        return false;
    }
}
