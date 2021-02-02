// site: https://leetcode-cn.com/problems/longest-repeating-character-replacement/

// 双指针

class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        // 双指针实现
        // 枚举字符串的每一个位置作为区间的右端点，找到其满足条件的最远的左端点
        // 即区间内除了出现次数最多的字符外，其他字符的数量不超过k个
        
        // 双指针中右指针移动，如果和左指针构成的区间仍然满足，则左指针不动，右指针移动
        // 若不满足，则左指针移动一个位置，右指针再继续

        int n = s.length();
        if(n <= k){
            return n;
        }
        int[] count = new int[26];
        int left = 0;
        int right = 0;
        // 当前区间内出现最多次的字符的次数
        int max = 0;
        // 答案(不需要这个变量)
        // int res = 1;
        while(right < n){
            int index = s.charAt(right) - 'A';
            // 更新区间内的字符数
            count[index]++;
            // 更新最大值
            max = Math.max(max, count[index]);
            // check是否满足 <==> 区间内非最多字符的数量是否大于k
            if(right - left + 1 - max > k){
                // 区间内删除左指针指向的字符
                count[s.charAt(left) - 'A']--;
                // 左指针移动
                left++;
            }
            // 之所以不用挑战最大值，是因为当条件不满足的时候，两个指针都会向前移动
            // 而满足条件的时候，右指针动左指针不动，区间长len会增加
            // 因此left和right之间的距离只会增加不会减小
            // 右指针肯定会移动的，而且会多走一步
            right++;
        }
        // 因为right多走一步，所以区间长是right - left，不用+1
        return right - left;
    }
}
