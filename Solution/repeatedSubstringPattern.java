// site: https://leetcode-cn.com/problems/repeated-substring-pattern/

// 判断一个字符串能否由其子串重复构成

// 双倍化

// KMP


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
