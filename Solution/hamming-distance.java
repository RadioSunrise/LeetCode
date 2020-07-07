//site: https://leetcode-cn.com/problems/hamming-distance

// 汉明距离

// 好的方法
// 用异或再统计的方法
class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while (xor != 0){
            if(xor % 2 == 1) //模2相当于拿二进制的最后一位
            {
                count++;
            }
            xor = xor >> 1; //右移一位等同于除2
        }
        return count;
    }
}


// 自己写的很慢方法，一路除以2来做
class Solution {
    public int hammingDistance(int x, int y) {
        int x_temp = x;
        int y_temp = y;
        int x_mod;
        int y_mod;
        int count = 0;
        while(x_temp != 0 || y_temp != 0){
            // System.out.println("x and y are: " + x_temp +" "+ y_temp);
            x_mod = x_temp % 2;
            y_mod = y_temp % 2;
            // System.out.println("x_mod and y_mod are: " + x_mod +" "+ y_mod);
            x_temp = x_temp / 2;
            y_temp = y_temp / 2;
            if(x_mod != y_mod)
            {
                count ++;
            }
        }
        return count;
    }
}
