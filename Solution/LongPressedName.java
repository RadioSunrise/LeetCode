// site: https://leetcode-cn.com/problems/long-pressed-name/

// 判断长按字符串type和name是否相等

// 用双指针实现，type中的每个字符type[j]的作用有两个
// (1) 和 name[i] 匹配
// (2) 是之前的匹配字符的长按，即type[j] = type[j - 1]
// 所以当两个条件都不满足的时候，返回false

// 循环体是j遍历完之后看i是不是被完全匹配了，是则true，否则false
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        if(typed.length() < name.length()){
            return false;
        }
        int i = 0;
        int j = 0;
        // j移动到type的尾部就结束循环
        while(j < typed.length()){
            if(i < name.length() && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }
            else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)){
                j++;
            }
            // s[i]和t[j]不同，且和前一个字符不同，返回false
            else {
                return false;
            }
        }
        // j走完了i还没走完则说明不匹配
        return i == name.length();
    }
}
