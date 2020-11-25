// site: https://leetcode-cn.com/problems/increasing-decreasing-string/


// 上升下降字符串，将问题转换成：最长的升序串 -> 最长的降序串 -> 重复
// 用桶来实现
class Solution {
    public String sortString(String s) {
        // 问题变成最长的升序串 -> 最长的降序串 -> 重复
        // 全小写用桶来实现
        int n = s.length();
        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[(int)(c - 'a')]++;
        }
        StringBuilder sb = new StringBuilder();
        while(sb.length() < n){
            // 顺序遍历桶找最长升序串
            for(int i = 0; i < 26; i++){
                if(count[i] > 0){
                    sb.append((char)('a' + i));
                    count[i]--;
                }
            }
            // 逆序遍历桶找最长的降序串
            for(int j = 25; j >= 0; j--){
                if(count[j] > 0){
                    sb.append((char)('a' + j));
                    count[j]--;
                }
            }
        }
        return sb.toString();
    }
}
