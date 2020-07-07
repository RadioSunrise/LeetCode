// site: https://leetcode-cn.com/problems/longest-common-prefix

// 横向扫描法
// 最长公共前缀初始化为strs[0]，和后续比较strs[i]，最长公共前缀会慢慢变小，用index来控制到哪个位置
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String compear = strs[0];
        int index = compear.length();
        for(int i = 1; i < strs.length; i++){
            String curr = strs[i];
            int j;
            for(j = 0; j < index && j < curr.length(); j++){
                if(curr.charAt(j) != compear.charAt(j)){
                    break;
                }
            }
            index = j;
            if(index == 0){
                return "";
            }
        }
        return compear.substring(0, index);
    }
}

//纵向扫描法
// 同一个位置i，每个strs[j]判断i位置的字符是不是相同，不相同就可以走了
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String temp = strs[0];
        for(int i = 0; i < temp.length(); i++){
            char c = temp.charAt(i);
            for(int j = 1; j < strs.length; j++){
                if(i == strs[j].length() || c != strs[j].charAt(i)){
                    return temp.substring(0, i);
                }
            }
        }
        return temp;
    }
}

