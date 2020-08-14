// site: https://leetcode-cn.com/problems/valid-parentheses/submissions/

// 字符串括号匹配，可以用栈来做

// 栈加判断，这样if的写法会有些复杂，而且不容易扩展
class Solution {
    public boolean isValid(String s) {
        // 特判1 空串
        if(s.equals("")){
            return true;
        }
        // 特判2 长度为奇数
        int len = s.length();
        if((len % 2) == 1){
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for(int ptr = 0; ptr < len; ptr++){
            char c = s.charAt(ptr);
            // 左括号
            if(c == '(' || c == '[' || c == '{'){
                // System.out.println("c is left: " + c);
                stack.push(c);
            }
            // 右括号，判断peek是不是对应的左括号，不是则返回false
            else if(c == ')' || c == ']' || c == '}'){
                // System.out.println("c is right: " + c);
                if(stack.isEmpty()){
                    return false;
                }
                char temp = stack.pop();
                // System.out.println("temp is " + temp);
                if((c == ')' && temp == '(') || (c == ']' && temp == '[') || (c == '}' && temp == '{')){
                    continue;
                }
                else {
                    return false;
                }
            }
        }
        // 如果左括号比右括号多，遍历完s之后stack不为空
        return stack.isEmpty();
    }
}

// 可以用一个Map来保存括号之间的对应关系
class Solution {
    public boolean isValid(String s) {
        // 特判1 空串
        if(s.equals("")){
            return true;
        }
        // 特判2 长度为奇数
        int len = s.length();
        if((len % 2) == 1){
            return false;
        }
        // map带初始值的定义方法
        HashMap<Character, Character> map = new HashMap<>(){{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Stack<Character> stack = new Stack<>();
        for(int ptr = 0; ptr < len; ptr++){
            char c = s.charAt(ptr);
            // 左括号, key
            if(map.containsKey(c)){
                stack.push(c);
            }
            // 右括号, value
            // 用栈顶作为key到map里面找，如果 value = c 则可以匹配
            else {
                if(stack.isEmpty() || map.get(stack.peek()) != c){
                    return false;
                }
                stack.pop();
            }
        }
        // 如果左括号比右括号多，遍历完s之后stack不为空
        return stack.isEmpty();
    }
}
