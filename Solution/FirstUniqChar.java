// https://leetcode-cn.com/problems/first-unique-character-in-a-string/

// 找出第一个不重复的字符，用数组记录

class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        char[] charArr = s.toCharArray();
        for(char c : charArr){
            count[c - 'a']++;
        }
        for(int i = 0; i < charArr.length; i++){
            if(count[charArr[i] - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }
}
