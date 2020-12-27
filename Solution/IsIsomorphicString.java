// site: https://leetcode-cn.com/problems/isomorphic-strings/

// 用两个map实现一一对应

class Solution {
    public boolean isIsomorphic(String s, String t) {
        // 两个hashMap
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int n = s.length();
        for(int i = 0; i < n; i++){
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if((s2t.containsKey(sChar) && s2t.get(sChar) != tChar) || (t2s.containsKey(tChar) && t2s.get(tChar) != sChar)){
                return false;
            }
            s2t.put(sChar, tChar);
            t2s.put(tChar, sChar);
        }
        return true;
    }
}
