// site: https://leetcode-cn.com/problems/unique-number-of-occurrences/

// 判断arr中每个数的出现次数是不是都不相同，用hasmap来记录出现次数，用hashset来检测重复
// set判断重复也可以用全部加入set之后，判断set的大小是否和arr大小相同来给出是否重复
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }
            else {
                map.put(num, 1);
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int num : map.values()){
            if(set.contains(num)){
                return false;
            }
            else {
                set.add(num);
            }
        }
        return true;
    }
}
