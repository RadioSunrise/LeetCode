// https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/

// 找出第一个出现一次的字符

// 1.无序存放的辅助空间 -- 在原序列中按序再找一次
// 要按照原来的顺序，所以可以通过hashMap或者数组来保存出现的次数
// 因为要返回的是出现一次的，所以可以用boolean值作为HashMap的value
// 大于一次的就都是false，方便判断，顺序要按照原字符串的顺序来
// HashMap版本的
class Solution {
    public char firstUniqChar(String s) {
        // 用HashMap来统计
        // 其实不同真正统计出现的数量，因为要返回的是出现次数为1的字符，所以大于1的字符必然是不正确的
        // 所以HashMap中的value也可以用boolean消耗的空间会更小一些(?)，主要是判断逻辑简单
        char[] arr = s.toCharArray();
        HashMap<Character, Boolean> map = new HashMap<>();
        for(char c : arr){
            if(!map.containsKey(c)){
                map.put(c, true);
            }
            else {
                map.put(c, false);
            }
        }
        // 按照原来的顺序，即charArray的顺序来
        for(char c : arr){
            if(map.get(c)){
                return c;
            }
        }
        return ' ';
    }
}


// 2.有序的辅助存储 -- linkedHashMap
