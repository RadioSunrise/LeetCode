// https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/

// 最长无重复子串

// 滑动窗口，注意细节和挑战最大值的时机
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null){
            return 0;
        }
        int n = s.length();
        if(n <= 1){
            return n;
        }
        int max = 0;
        // sliding window
        int left = 0;
        int right = 0;
        // 记录出现位置的hashMap
        int index = 0;
        HashMap<Character, Integer> appearMap = new HashMap<>();
        while(right < n){
            // 遇到出现过的字符，left才会移动
            char c = s.charAt(right);
            if(appearMap.containsKey(c)){
                index = appearMap.get(c);
                // left移动到出现位置的下一个位置，但是这个位置要比left大
                // left只会往右边走
                left = Math.max(index + 1, left);
            }
            appearMap.put(c, right);
            // 挑战最大值
            right++;
            if(right - left > max){
                max = right - left;
            }
        }
        return max;
    }
}
