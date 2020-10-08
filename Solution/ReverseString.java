// site: https://leetcode-cn.com/problems/reverse-string/

// 字符串反序

class Solution {
    public void reverseString(char[] s) {
        int len = s.length;
        if(len <= 1){
            return;
        }
        for(int i = 0; i < len / 2; i++){
            char c = s[i];
            s[i] = s[len - 1 - i];
            s[len - 1 - i] = c;
        }
    }
}
// 或者两个指针相遇也行
class Solution {
    public void reverseString(char[] s) {
        int len = s.length;
        if(len <= 1){
            return;
        }
        int left = 0;
        int right = len - 1;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
