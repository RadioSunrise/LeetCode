//site:

// 错误版本，这里是把左括号字符放进栈，用sum计数来统计最大的匹配长度，但是问题在于sum什么时候置0，即判断一个序列已经结束
// 例如"()(()"的时候就是得到结果为4，而不是正确结果2
class Solution_bad {
    public int longestValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        char[] s_char = s.toCharArray();
        char curr;
        int max = 0;
        int sum = 0;
        for(int i = 0; i < s_char.length; i++){
            curr = s_char[i];
            if(curr == '('){ //左括号压栈
                stack.push(curr);
            }
            else { // 遇到右括号
                if(! stack.isEmpty()){ 
                    if(stack.peek() == '('){ // 栈非空且栈顶是左括号(
                        stack.pop();
                        sum+=2;
                        max = Math.max(max, sum);
                    }
                    else {
                        sum = 0;
                    }
                }
                else{ //右括号且栈空，重新开始计数
                    sum = 0;
                }
            }
        }
        return max;
    }
}

// 所以用栈的话应该把存括号的想法改成存下标，通过下标计算匹配长度
// 长度计算不是简单的 当前索引-出栈的下标+1，而是改为 当前索引-出栈后新的栈顶索引
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 先存入-1当参照物，否则当没有匹配的右括号会无法得出长度
        // 栈底存放的是作为参照的下标
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i); // 把下标压入栈
            }
            else{
                stack.pop();
                if(stack.isEmpty()) { // 栈空，将当前的右括号当做新的参照物
                    stack.push(i);
                }
                else{
                    max = Math.max(max, i - stack.peek()); // 减去的是参照物，参照物在这里是不用弹出来的
                }
            }
        }
        return max;
    }
}
// https://leetcode-cn.com/problems/longest-valid-parentheses/solution/shou-hua-tu-jie-zhan-de-xiang-xi-si-lu-by-hyj8/
// 关键：栈底元素的设计是最后一个未匹配的右括号的下标
