// site: https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/

// 计算逆波兰表达式

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        // 栈模拟
        Deque<Integer> stack = new LinkedList<>();
        for(String str : tokens){
            // 数字就压栈
            if(isNumber(str)){
                stack.push(Integer.parseInt(str));
            } else {
                // 运算符的话就取数字运算
                // 先弹2再弹1，栈是反过来的
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch(str){
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                    default:
                }
            }
        }
        return stack.pop();
    }

    /**
    * 判断字符串是否为数字
    * 其实是判断是否为运算符
    */
    boolean isNumber(String s){
        // 是运算符就返回false
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
            return false;
        }
        return true;
    }
}
