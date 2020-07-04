//site: https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/

public class Solution {
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] b = new int[n];
        int temp = 1;
        for(int i = 0; i < n; i++){
            b[i] = temp;
            temp = temp * A[i];
        }
        temp = 1;
        for(int i = n-1; i >= 0; i--){
            b[i] = b[i] * temp;
            temp = temp * A[i];
        }
        return b;
    }
}
// 关键：两个for，第一个for先顺序构造B[i]的左边(0 -- i-1)，第二个再构造右边(i+1 -- n)
