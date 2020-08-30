// site: https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/

// 按要求复制到一个字符串里面
class Solution {
    public String reverseWords(String s) {
        if(s == null){
            return null;
        }
        int len = s.length();
        if(len == 0){
            return s;
        }
        // 转成一个字符串，在新字符串上操作
        char[] arr = s.toCharArray();
        char[] res = new char[len];
        int i = 0;
        while(i < len){
            // 遇到空格，res的对应位置也是空格
            if(arr[i] == ' '){
                res[i] = ' ';
                ++i;
            }
            else {
                // 记录这一段的开头
                int left = i;
                // i移动到末尾或者是空格
                while(i < len && arr[i] != ' '){
                    ++i;
                }
                // 记录结束位置，拷贝字符
                int right = i - 1;
                for(int j = left; j < i; j++){
                    res[right] = arr[j];
                    // 下标移动
                    --right;
                }
            }
        }
        return new String(res);
    }
}

// 第一版split划分，再逐个反转
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();
        for(String word : words){
            res.append(reverseString(word));
            res.append(" ");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
    public String reverseString(String word){
        int len = word.length();
        char[] arr = new char[len];
        for(int i = 0; i < len; i++){
            arr[len - 1 - i] = word.charAt(i);
        }
        String res = new String(arr);
        return res;
    }
}

