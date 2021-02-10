// site: https://leetcode-cn.com/problems/permutation-in-string/

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        // 种类和次数相同，顺序不一定要一样
        // 双指针找区间
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];
        
        // 统计s1的种类和频率
        int pCount = 0;
        for(int i = 0; i < s1Array.length; i++){
            int index = s1Array[i] - 'a';
            if(pFreq[index] == 0){
                pCount++;
            }
            pFreq[index]++;
        }
        // 左右指针锁定区间
        int left = 0;
        int right = 0;
        // 设定一个变量winCount统计左右指针包围区间内的种类数，要和s1中的种类数相同才会增加
        int winCount = 0;
        while(right < s2Array.length){
            // right用于观察
            int index = s2Array[right] - 'a';
            // winFreq记录的是s1中出现过的，所以要判断
            if(pFreq[index] > 0){
                winFreq[index]++;
                // 同种字符出现次数一样时winCount才会++
                if(winFreq[index] == pFreq[index]){
                    winCount++;
                }
            }
            right++;

            // 当winCount == pCount时，收缩左边界，如果区间和s1的长度一样，则找到了排列
            while(pCount == winCount){
                // 区间长度相等
                if(right - left == s1Array.length){
                    return true;
                }
                // 要移动左边界了
                int indexLeft = s2Array[left] - 'a';
                if(pFreq[indexLeft] > 0){
                    winFreq[indexLeft]--;
                    // 出现次数不足了，则winCount减少
                    if(winFreq[indexLeft] < pFreq[indexLeft]){
                        winCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }
}
