// site: https://leetcode-cn.com/problems/multiply-strings/

// 第一版的垂直相加法，考虑num2中的每个数字n1和num1中的每个数字n1，结果等于n2和n1的移位相加
// 一个n位的数 和 一个m位的数 相乘，结果最多是(n + m)位
// 逐个位置累加，最后加到一个String里面

// 最终的改进版写法
class Solution {
    /**
     * 字符串相乘，返回一个字符串，用垂直累加的方法
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        // (n位数) * (m位数) 最多是 (n+m)位
        int len1 = num1.length();
        int len2 = num2.length();
        int[] result = new int[len1 + len2];
        // num2的每一位和num1的每一位相乘，将结果累加到结果数组的对应位置
        for(int i = len2 - 1; i >= 0; i--){
            int n2 = num2.charAt(i) - '0';
            for(int j = len1 - 1; j >=0; j--){
                int n1 = num1.charAt(j) - '0';
                // 应该放的两个位置：i + j + 1, i + j
                int temp = result[i + j + 1] + (n1 * n2);
                result[i + j + 1] = temp % 10;
                result[i + j] += temp /10;
            }
        }
        StringBuilder builder = new StringBuilder();
        // 从result逐个返序添加
        // 第一个位置是0就不加
        for(int i = 0; i < len1 + len2; i++){
            if(i == 0 && result[i] == 0){
                continue;
            }
            builder.append(result[i]);
        }
        return builder.toString();
    }
}

// 第一次写的比较复杂
class Solution {
    /**
     * 字符串相乘，返回一个字符串，用垂直累加的方法
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        // (n位数) * (m位数) 最多是 (n+m)位
        int len1 = num1.length();
        int len2 = num2.length();
        int[] result = new int[len1 + len2];
        // num2的每一位和num1的每一位相乘，将结果累加到结果数组的对应位置
        // 用于帮助移位的shift
        int shift2 = -1;
        for(int i = len2 - 1; i >= 0; i--){
            shift2++;
            int n2 = num2.charAt(i) - '0';
            int shift1 = -1;
            for(int j = len1 - 1; j >=0; j--){
                shift1++;
                int n1 = num1.charAt(j) - '0';
                // 计算应该放在哪两个位置
                int index1 = shift1 + shift2;
                int index2 = index1 + 1;
                result[index1] += ((n1 * n2) % 10);
                // 处理index1位置进位的问题
                result[index2] += ((n1 * n2) / 10) + (result[index1] / 10);
                result[index1] %= 10;
            }
        }
        /*for(int n : result){
            System.out.print(n + " ");
        }
        */
        System.out.println();
        StringBuilder builder = new StringBuilder();
        // 从result逐个返序添加，result中的结果是反序的
        if(result[len1 + len2 - 1] != 0){
            builder.append(result[len1 + len2 - 1]);
        }
        for(int i = result.length - 2; i >= 0; i--){
            builder.append(result[i]);
        }
        return builder.toString();
    }
}

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
