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
