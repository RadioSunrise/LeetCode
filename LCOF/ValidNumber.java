// site: https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/

// 数字可以表示为 A[.[B]][e|EC]，详细见注释

class Solution {
    /**
    * 当前扫描的下标
    */
    int currPos = 0;

    public boolean isNumber(String s) {
        // 数字可以表示为 A[.[B]][e|EC]
        // A和C都是带符号的数字（可以有+/-）
        // B是无符号的数字
        
        // 开始扫描
        if(s == null || s.length() == 0){
            return false;
        }
        // 删除后面的空格
        s = s.trim();
        
        int n = s.length();

        // 扫描A
        boolean numberic = scanInteger(s);

        // 扫描B
        // 先判断"."
        if(currPos < n && s.charAt(currPos) == '.'){
            currPos++;
            // 可以没有B (?)
            System.out.println("b: numberic is " + numberic);
            boolean checkB = scanUnSignedInteger(s);
            numberic = numberic || checkB;
            System.out.println("b: numberic is " + numberic);
        }

        // 扫描e 或者 E
        if(currPos < n && (s.charAt(currPos) == 'e' || s.charAt(currPos) == 'E')){
            currPos++;
            // 出现了e必须要有整型
            numberic = numberic && scanInteger(s);
            System.out.println("ec: numberic is " + numberic);
        }
        // 返回数字判断和i是否到达字符串结尾，因为每次都会currPos++，所以最后currPos会等于n
        System.out.println("currPos is " + currPos);
        return numberic && (currPos == n);
    }

    /**
    * 扫描整数部分，可以有 + 或者 - 号
    * @param str 待扫描字符串
    * @return 是否为一个整数
    */
    boolean scanInteger(String str){
        // 遇到 + 或 -
        if(currPos < str.length() && (str.charAt(currPos) == '+' || str.charAt(currPos) == '-')){
            currPos ++;
        }
        // 扫描完符号位之后进入无符号整数的扫描
        return scanUnSignedInteger(str);
    }

    /**
    * 扫描无符号整数部分
    * @param str 待扫描字符串
    * @return 是否为一个无符号整数
    */
    boolean scanUnSignedInteger(String str){
        // 保存当前currPos，用于比较
        int temp = currPos;
        // 符合[0, 9]，currPos移动
        while(currPos < str.length() && (str.charAt(currPos) <= '9' && str.charAt(currPos) >= '0')){
            currPos++;
        }
        return (currPos > temp);
    }
}
