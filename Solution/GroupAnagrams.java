package leetcode;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 1.用排序实现，异位词的排序相同，如：acb, cba, bac的排序都是abc，就可以找到异位词
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if(map.containsKey(key)){
                map.get(key).add(str);
            }
            else {
                List<String> temp = new LinkedList<>();
                temp.add(str);
                map.put(key, temp);
            }
        }
        for(List<String> list : map.values()){
            res.add(list);
        }
        return res;
    }
    public static void main(String[] args){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = new GroupAnagrams().groupAnagrams(strs);
        System.out.println(res.toString());
    }
}

// 2020-12-14
// site: https://leetcode-cn.com/problems/group-anagrams/

// 字母异位词分组，用的是排序之后相同串的方法，稍微会比较慢

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 异位词在排序完之后是完全一样的，可以用排序完的字符串作为key
        // 用统计数组变成固定格式字符串也行，排序写起来方便一点
        List<List<String>> res = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        // 遍历
        for(String str : strs){
            // 排序之后加入map
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            String sortStr = String.valueOf(charArr);
            if(map.containsKey(sortStr)){
                map.get(sortStr).add(str);
            }
            else {
                List<String> list = new LinkedList<>();
                list.add(str);
                map.put(sortStr, list);
            }
        }
        // 从map中构建答案
        // 方法1，比较慢
        /*
        for(List<String> value : map.values()){
            res.add(value);
        }
        return res;
        */
        
        // 方法2，方便一些
        return new ArrayList<List<String>>(map.values());
    }
}
