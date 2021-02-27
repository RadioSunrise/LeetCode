// site: https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/

// 和常规的双指针不同，不能直接操作，需要自己增加限定条件

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        // 不能直接用双指针求解，因为直接用没有单调性
        // 即左右指针的移动不一定存在 “分界”/“二段”的性质
        // 例子：若子串t满足条件，指针往左或者右边界移动，新串满足条件吗？
        // 结果是不一定的

        // 因为题目限定s仅由小写字母组成，所以可以使用枚举区间内字符种类的方法
        // 种类数量只有[1, 26]种
        // 当限定了区间内字符的种类，滑动双指针滑动才有单调性
        // 因为限定了区间内字符的数量，所以当右指针移动，种类增多的时候，左指针向右缩减才具有了单调性，左指针右移一定减少数量（或者不变），右指针右移一定增加数量（或不变）

        int len = s.length();
        // 统计出现次数
        int[] count = new int[26]; 
        char[] sArr = s.toCharArray();
        int res = 0;
        // 枚举区间内的字符种类
        for(int curKind = 0; curKind < 26; curKind++){
            // 在限定区间内字符种类数的情况下双指针
            // 计数清0
            Arrays.fill(count, 0);
            int left = 0;
            int right = 0;
            // totalKind 当前区间内的字符种数
            int totalKind = 0;
            // sumKind 区间内出现次数大于等于k的字符种数
            int sumKind = 0;
            while(right < len){
                int index = sArr[right] - 'a';
                count[index]++;
                if(count[index] == 1){
                    totalKind++;
                }
                // 出现次数等于k了，满足条件，sumKind++
                if(count[index] == k){
                    sumKind++;
                }
                // 当出现种类数大于curKind，左边界移动减少出现的数量
                while(totalKind > curKind){
                    int indexLeft = sArr[left] - 'a';
                    // 左指针右移
                    left++;
                    count[indexLeft]--;
                    if(count[indexLeft] == 0){
                        totalKind--;
                    }
                    // 减1之后等于k-1，则从符合变成不符合
                    if(count[indexLeft] == k - 1){
                        sumKind--;
                    }
                }
                // 当所有种类的字符都符合的时候更新答案
                if(totalKind == sumKind){
                    res = Math.max(res, right - left + 1);
                }
                // 右指针右移
                right++;
            }
        }
        return res;
    }
}
