// site: https://leetcode-cn.com/problems/add-strings/

// 两个字符串相加，注意考虑进位

public class AddStrings {
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
            // 不转char也行的，AbstractStringBuilder自己会转
            result.append((n3 % 10 ));
            pos1--;
            pos2--;
        }
        if(carry == 1){
            result.append(1);
        }
        return result.reverse().toString();
    }
    public static void main(String[] args){
        int a = 9;
        char c = (char) (a + '0');
        int b = c - '0';
        System.out.println(c);
        System.out.println(b);
        String num1 = "9999";
        String num2 = "1";
        AddStrings solution = new AddStrings();
        String res = solution.addStrings(num1, num2);
        System.out.println(res);
    }
}
