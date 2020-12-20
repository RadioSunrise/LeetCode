package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-duplicate-letters/
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 用数组记录，栈模拟删除过程
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // 数组 + 栈
        // 两个数组记录是否出现在栈中，记录出现的个数
        boolean[] inStack = new boolean[26];
        int[] count = new int[26];
        // 扫一遍记录出现个数
        for(char c : s.toCharArray()){
            count[(int)(c - 'a')]++;
        }
        // 通过栈来实现去重
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(!inStack[(int)(c - 'a')]){
                // 如果栈空或者当前字符比栈顶字符大，则压入栈中
                if(stack.isEmpty() || stack.peek() < c){
                    stack.push(c);
                    inStack[(int)(c - 'a')] = true;
                }
                // 如果比栈顶元素小，则进行判断
                else {
                    while(!stack.isEmpty() && stack.peek() > c && count[(int)(stack.peek() - 'a')] > 0){
                        int index = (int)(stack.peek() - 'a');
                        // 如果之后还会出现，则舍弃，用后面出现的来加入结果
                        stack.pop();
                        inStack[index] = false;
                    }
                    // 将当前字符入栈
                    stack.push(c);
                    inStack[(int)(c - 'a')] = true;
                }
            }
            // 出现次数减1，count表示剩下没有遍历过的
            count[(int)(c - 'a')]--;
        }
        int len = stack.size();
        char[] res = new char[len];
        for(int i = len - 1; i >= 0; i--){
            res[i] = stack.pop();
        }
        return String.valueOf(res);
    }
    public static void main(String[] args){
        String s = "edebbed";
        String res = new RemoveDuplicateLetters().removeDuplicateLetters(s);
        System.out.println(res);
    }
}
