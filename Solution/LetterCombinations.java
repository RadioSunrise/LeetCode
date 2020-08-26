// site: https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/

// 电话表问题

// 回溯法来做
class Solution {
    public List<String> letterCombinations(String digits) {
        int len = digits.length();
        List<String> res = new ArrayList<>();
        if(len == 0){
            return res;
        }
        StringBuilder path = new StringBuilder();

        // 符号表
        HashMap<Character, String> phone = new HashMap<>(8);
        phone.put('2', "abc");
        phone.put('3', "def");
        phone.put('4', "ghi");
        phone.put('5', "jkl");
        phone.put('6', "mno");
        phone.put('7', "pqrs");
        phone.put('8', "tuv");
        phone.put('9', "wxyz");
    
        backTrack(0, digits, len, phone, res, path);
        return res;
        
    }
    /**
    * 回溯法
    */
    public void backTrack(int pos, String digits, int len, HashMap<Character, String> phone, List<String> res, StringBuilder path){
        // 递归结束条件
        if(pos >= len){
            // 加入结果
            res.add(path.toString());
            return;
        }
        char digit = digits.charAt(pos);
        String characters = phone.get(digit);
        if(characters == null){
            return;
        }
        char[] chars = characters.toCharArray();
        // 候选字母逐个加入path
        for(int i = 0; i < chars.length; i++){
            path.append(chars[i]);
            // 递归
            backTrack(pos + 1, digits, len, phone, res, path);
            // 回溯
            path.deleteCharAt(pos);
        }
    }
}
