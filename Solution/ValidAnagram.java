// site: https://leetcode-cn.com/problems/valid-anagram/

// 判断两个字符串是不是字母异位词

// 当两个字符串中都只包含小写字母，可以用数组统计个数的方法
// 先比较长度，最后就不用再扫一遍统计是否有剩余了

// 有判断长度的数组版本
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        // 小写字母用数组实现
        int[] count = new int[26];
        int num = 0;
        // 统计s的字符
        for(char c : s.toCharArray()){
            num = c - 'a';
            count[num]++;
        }
        // 遍历t的字符
        for(char c : t.toCharArray()){
            num = c - 'a';
            // 如果有超过的则返回false
            if(count[num] == 0){
                return false;
            }
            count[num]--;
        }
        // 有长度判断，不同统计一遍看看是不是有剩余的
        return true;
    }
}

// 没有判断长度的版本
class Solution {
    public boolean isAnagram(String s, String t) {
        // 小写字母用数组实现
        int[] count = new int[26];
        int num = 0;
        // 统计s的字符
        for(char c : s.toCharArray()){
            num = c - 'a';
            count[num]++;
        }
        // 遍历t的字符
        for(char c : t.toCharArray()){
            num = c - 'a';
            // 如果有超过的则返回false
            if(count[num] == 0){
                return false;
            }
            count[num]--;
        }
        // 统计一遍看看是不是有剩余的
        for(int i : count){
            if(i > 0){
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public boolean isAnagram(String s, String t) {
        // Unicode 字符的话改成用 HashMap
        if(s.length() != t.length()){
            return false;
        }
        int count;
        Map<Character, Integer> map = new HashMap<>();
        // 统计s的字符
        for(char c : s.toCharArray()){
            count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
        // 统计t的字符
        for(char c : t.toCharArray()){
            count = map.getOrDefault(c, 0);
            if(count == 0){
                return false;
            }
            map.put(c, count - 1);
        }
        // 因为有比较长度，所以不用最后扫一遍统计是否有剩余，直接返回
        return true;
    }
}
