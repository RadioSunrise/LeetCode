package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * site: https://leetcode-cn.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {
    /** 没有括号，可能有空格隔开
     * */
    public int calculate(String s) {
        // 因为先乘除后加减，所以可以用一个栈，先算乘除，再算加减
        // 遇到 + 号把数存进去，遇到 - 号把相反数存入栈
        // 遇到 * 和 / 就立即计算结果，把结果存入栈

        // 用一个 preSign 存储这个数字之前的运算符
        // preSign 初始化为 + 号
        Deque<Integer> stack = new LinkedList<>();
        int len = s.length();
        char preSign = '+';
        int num = 0;
        int temp = 0;
        for(int i = 0; i < len; i++){
            // 可能是一个多位数，要累加
            if(Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i) - '0';
            }
            // 遇到运算符 或者 到了结尾
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == len - 1){
                System.out.println("num is " + num);
                switch(preSign){
                    case '+':
                        stack.push(num);
                        System.out.println("push " + num);
                        break;
                    case '-':
                        stack.push(-num);
                        System.out.println("push " + -num);
                        break;
                    case '*':
                        temp = stack.pop();
                        stack.push(temp * num);
                        System.out.println("push " + temp * num);
                        break;
                    default:
                        temp = stack.pop();
                        stack.push(temp / num);
                        System.out.println("push " + temp / num);
                }
                // 更新符号和num，遇到运算符就说明num已经结束了
                preSign = s.charAt(i);
                num = 0;
            }
        }
        // 乘除算完了，把栈里面的全部累加
        System.out.println("stack's size is " + stack.size());
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
    public static void main(String[] args){
        String s = "3 + 2 * 2";
        int ans = new BasicCalculatorII().calculate(s);
        System.out.println(ans);
    }
}
