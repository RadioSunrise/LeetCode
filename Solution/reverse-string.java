//site: https://leetcode-cn.com/problems/reverse-string/

class Solution {
    public void reverseString(char[] s) {
        int s_lengh = s.length;
        for(int i = 0; i < s_lengh / 2; i++)
        {
            char temp = s[i];
            s[i] = s[s_lengh - i - 1];
            s[s_lengh - i - 1] = temp;
        }
    }
}
