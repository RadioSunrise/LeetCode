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

// 用left和right代替start和end
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.equals("")){
            return 0;
        }
        // Sliding Window + HashMap 记录出现位置
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>(len);
        // 最长长度至少为1
        int max = 1;
        // 窗口左右边界
        int left = 0;
        int right = 0;
        int index = 01;
        // right到达字符串末尾即结束
        for(; right < len; right++){
            char c = s.charAt(right);
            // 当前字符在map是否有出现
            // 出现过
            if(map.containsKey(c)){
                // 左边界移动，移动到出现过的位置的下一个位置
                index = map.get(c);
                // left移动要保证准备移动的位置比left大，否则会往回走
                // 如 abba，到第二次遇到a，此时left为2，而准备上一次出现的a位置的下一个位置是1
                // 即希望移动到1，是不对的
                left = Math.max(left, index + 1);
            }
            // 更新map的出现下标
            map.put(c, right);
            // 挑战最大值，每一次right增加都要挑战最大值，不是只有在发现重复的时候才挑战
            // 否则如果s完全不相等就会出错
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
