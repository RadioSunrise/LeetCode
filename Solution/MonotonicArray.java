// https://leetcode-cn.com/problems/monotonic-array/
// 检测数组是否为单调数组（非递增或者非递减）

/**
* 两次遍历，其中一种就行
*/ 
public class MonotonicArray {
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if(len <= 2){
            return true;
        }
        // 分两次遍历
        // 检测是否为递增
        boolean increase = true;
        for(int i = 0; i < len - 1; i++){
            if(A[i] > A[i + 1]){
                increase = false;
                break;
            }
        }
        // 检测是否为递减
        boolean decrease = true;
        for(int i = 0; i < len - 1; i++){
            if(A[i] < A[i + 1]){
                decrease = false;
                break;
            }
        }
        // 如果有其中一个为true即可
        return increase || decrease;
    }
}

/**
* 一次遍历，遍历数组 AA，若既遇到了 A[i]>A[i+1]A[i]>A[i+1] 又遇到了 A[i']<A[i'+1]A[i′]<A[i′+1]，则不是单调数组
*/
class Solution {
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if(len <= 2){
            return true;
        }
        // 一次遍历，如果遇到了 A[i] > A[i+1]，又有 A[j] < A[j+1] 则不行
        boolean increase = true;
        boolean decrease = true;
        for(int i = 0; i < len - 1; i++){
            if(A[i] < A[i + 1]){
                increase = false;
            }
            if(A[i] > A[i + 1]){
                decrease = false;
            }
        }
        // 如果有其中一个为true即可
        return increase || decrease;
    }
}
