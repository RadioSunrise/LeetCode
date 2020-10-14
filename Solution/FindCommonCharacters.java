// site: https://leetcode-cn.com/problems/find-common-characters/

// 统计多个字符串中共同出现的字符序列
// 用计数的方法实现

// 第二版修改版，优化了比较逻辑，count的初值用MAX_VALUE代替
class Solution {
    public List<String> commonChars(String[] A) {
        List<String> res = new LinkedList<>();
        if(A.length < 1){
            return res;
        }
        int[] count = new int[26];
        // 初值附为最大值，方便比较
        Arrays.fill(count, Integer.MAX_VALUE);
        for(String str : A){
            int[] curr = new int[26];
            for(char c: str.toCharArray()){
                curr[(int)(c - 'a')]++;
            }
            // update count
            for(int i = 0; i < 26; i++){
                count[i] = Math.min(count[i], curr[i]);
            }
        }
        for(int i = 0; i < 26; i++){
            while(count[i] > 0){
                res.add(Character.toString((char)('a' + i)));
                count[i]--;
            }
        }
        return res;
    }
}

// 第一版，比较丑，判断也复杂，会单独处理第一个字符串和很多多余的0比较
class Solution {
    public List<String> commonChars(String[] A) {
        List<String> res = new LinkedList<>();
        if(A.length < 1){
            return res;
        }
        int[] count = new int[26];
        int flag = 0;
        for(String str : A){
            int[] curr = new int[26];
            for(char c: str.toCharArray()){
                curr[(int)(c - 'a')]++;
            }
            // update count
            for(int i = 0; i < 26; i++){
                // 第一个串
                if(flag == 0){
                    count[i] = curr[i];
                }
                // 当两边都出现了，取最小的次数
                else if(count[i] > 0 && curr[i] > 0 && flag > 0){
                    count[i] = Math.min(curr[i], count[i]);
                }
                // 其中一个没出现，置为0
                else{
                    count[i] = 0;
                }
            }
            flag = 1;
        }
        for(int i = 0; i < 26; i++){
            while(count[i] > 0){
                res.add(Character.toString((char)('a' + i)));
                count[i]--;
            }
        }
        return res;
    }
}
