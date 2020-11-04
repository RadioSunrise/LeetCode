// https://leetcode-cn.com/problems/valid-mountain-array/

// 判断数组是否为山脉数组，用双指针实现，一头一尾向中间靠拢
// 两个都是严格上升，最后看是否同一个峰，注意最后判断的时候考虑边界，方式一个不动一个移到边界

class Solution {
    public boolean validMountainArray(int[] A) {
        // 双指针实现
        int n = A.length;
        int left = 0;
        int right = n - 1;
        // left一直向上
        while(left < n - 1 && A[left] < A[left + 1]){
            left++;
        }
        // right也一直向上
        while(right > 0 && A[right - 1] > A[right]){
            right--;
        }
        // 如果left == right，则到同一个峰，满足山脉数组
        // 注意要写上left != 0和right != (n-1)防止一个不移动另一个到边界
        if(left != 0 && right != (n - 1) && left == right){
            return true;
        }
        return false;
    }
}
