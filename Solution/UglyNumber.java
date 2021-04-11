// https://leetcode-cn.com/problems/ugly-number/

// 判断一个数是不是丑数，即因子仅包括 2 / 3 / 5

class Solution {
    public boolean isUgly(int num) {
        if(num <= 0){
            return false;
        }
        while(num % 2 == 0){
            num /= 2;
        }
        while(num % 3 == 0){
            num /= 3;
        }
        while(num % 5 == 0){
            num /= 5;
        }
        return num == 1;
    }
}
