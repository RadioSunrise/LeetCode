// site: https://leetcode-cn.com/problems/multiply-strings/


// 第一种方法：模拟正常计算，nums2的每一位逐一和num1相乘再相加
// 记得要移位相加，在计算一个数字和num1相乘之前在StringBuilder里面加0
class Solution {
    /**
     * 字符串相乘，返回一个字符串
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        String res = new String();
        //num2逐位和num1相乘，乘出来是一个string
        for(int pos2 = num2.length() - 1; pos2 >=0; pos2--){
            int n2 = num2.charAt(pos2) - '0';
            // 加0
            StringBuilder sbTemp = new StringBuilder();
            for(int i = 0; i < num2.length() - pos2 - 1; i++){
                sbTemp.append(0);
            }
            // 计算num1乘n2
            String mulTemp = numberMulString(num1, n2, sbTemp);
            res = addStrings(res, mulTemp);
        }
        return res;
    }

    /**
     * 一个数n（1位）和一个字符串表示的整数相乘，返回字符串
     * @param num
     * @param n
     * @param curr 存好了一些0的StringBuilder
     * @return
     */
    public String numberMulString(String num, int n, StringBuilder curr){
        if(n != 0){
            int pos = num.length() - 1;
            int carry = 0;
            while(pos >= 0 || carry != 0){
                int n1 = (pos >= 0)? num.charAt(pos) - '0' : 0;
                int temp = n1 * n + carry;
                curr.append(temp % 10);
                carry = temp / 10;
                pos--;
            }
        }
        return curr.reverse().toString();
    }

    /**
     * 两个字符串相加，返回一个字符串
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        // 指针
        int pos1 = num1.length() - 1;
        int pos2 = num2.length() - 1;
        // 进位
        int carry = 0;
        // 结果
        StringBuilder result = new StringBuilder(num1.length() + num2.length() + 1);
        while(pos1 >= 0 || pos2 >= 0){
            // 获取数字，指针走到开头了数字就为0
            int n1 = ((pos1 < 0) ? '0' : num1.charAt(pos1)) - '0';
            int n2 = ((pos2 < 0) ? '0' : num2.charAt(pos2)) - '0';
            int n3 = n1 + n2 + carry;
            // 设置数位和进位位
            carry = n3 / 10;
            result.append((n3 % 10));
            pos1--;
            pos2--;
        }
        if(carry == 1){
            result.append(1);
        }
        return result.reverse().toString();
    }
}
