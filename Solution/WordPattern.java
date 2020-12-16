// https://leetcode-cn.com/problems/word-pattern/

// 单词规律，pattern中的模式和s中的字符一定要一一对应
// 因此使用两个map，时间复杂度才是O(n + m)，如果用一个map + 查询containsValue的方法，复杂度会变成O(n^2)

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.split(" ");
        int n = pattern.length();
        if(strs.length != n){
            return false;
        }
        // 要两个 map，因为 char 和 String 要一一对应，不能重复
        Map<Character, String> char2Str = new HashMap<>();
        Map<String, Character> str2Char = new HashMap<>();
        for(int i = 0; i < n; i++){
            char sign = pattern.charAt(i);
            // 检查 str2Char map
            if(str2Char.containsKey(strs[i]) && str2Char.get(strs[i]) != sign){
                return false;
            }
            // 检查 char2Str map
            if(char2Str.containsKey(sign) && !strs[i].equals(char2Str.get(sign))){
                return false;
            }
            // 键值对加入map
            char2Str.put(sign, strs[i]);
            str2Char.put(strs[i], sign);
        }
        return true;
    }
}
