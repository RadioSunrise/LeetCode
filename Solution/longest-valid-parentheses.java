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


// 动态规划法
// dp含义：dp[i]表示以i结尾的括号匹配的长度，因为匹配串一定是右括号结尾的，所以所有左括号的i的dp一定是0
// 转移方程：两种情况(1)."()" 和 (2)."))"，1.如果"()"的话，则dp[i] = dp[i-2] + 2
//                                        2.如果"))"的话，根据dp[i-1]来，如果s[i-1]结尾的子串的 *前一个* 是'('，那么dp[i] = dp[i-1] + 2 + dp[i-[i-1]-2]
//                                          dp[i-[i-1]-2]i-1子串的前2位，就是要匹配的左括号的前面 
class Solution_dp {
    public int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()]; // dp[i]表示以下标i字符结尾的最长有效括号的长度，如果s[i]=='('，则dp[i]=0
        for(int i = 1; i < s.length(); i++){
            //遇到右括号再考虑转移，左括号的dp必定为0
            if(s.charAt(i) == ')'){
                if(s.charAt(i - 1) == '('){ // 转移的两种情况 -- 1.如果是"()"
                    dp[i] = ((i >= 2) ? dp[i - 2] : 0) + 2;
                }
                else if(i - dp[i - 1] > 0 && s.charAt(i - dp[i-1] - 1) == '('){ // 2.如果是"）),且以i-1为结尾的匹配的前一个为("
                    dp[i] = dp[i - 1] + ((i - dp[i-1]) >= 2 ? dp[i -dp[i-1]-2] : 0) + 2;
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }
}
