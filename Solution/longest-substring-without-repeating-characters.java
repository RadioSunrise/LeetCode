// site: https://leetcode-cn.com/problems/longest-substring-without-repeating-characters

// 滑动窗口
// 这个写法的滑动窗口比较难理解，start指针是窗口的左指针，end是窗口的右指针
// start每次移动都移动到重复字符的后一位，每次map的push会更新这个字符的出现下标，所以可以直接移过去
// 而且if判断是在map更新下标前做的，不会导致跳过头

// 例如 a b c a d a b c
// end指针遇到第2个a的时候，map里面的a -> val = 0，就是之前出现的第1个a的下标0
// start就会移动到 0 + 1即第1个b的位置

// 例如 p w w w k e w
// end指针遇到第2个w的时候，start会跳到上一次w出现位置的下一个位置，即移动到2
// 而不会直接移动k的位置
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.equals(""))
        {
            return 0;
        }
        int s_len = s.length();
        int max = 1;
        int start = 0;
        int end = 0;
        // 字符 -- 出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        for(; end < s_len; end++){
            // 遇到出现过的字符
            if(map.containsKey(s.charAt(end))){
                // 遇到出现过的字符
                // start向右移动，移动到和s[end]相同字符的后1个位置
                start = Math.max(start, map.get(s.charAt(end)) + 1);
            }
            map.put(s.charAt(end), end); //放入map和更新下标
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}

