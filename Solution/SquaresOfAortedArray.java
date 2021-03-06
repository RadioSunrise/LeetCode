// site: https://leetcode-cn.com/problems/squares-of-a-sorted-array/

// 有序数组的平方，由于存在负数，平方之后会比正数的平方大，所以可以考虑用双指针的方法做
// 先找到正负部分的分界点，然后两个指针分别向两边移动，这样可以保证负指针neg和正指针pos各自的序列是有序的（从小到大）
// 然后用有序数组合并的方式来进行
// 注意while循环的条件和内部的细节，当一个到边界了另一个继续处理

class Solution {
    public int[] sortedSquares(int[] A) {
        int negative = -1;
        int n = A.length;
        // 找到negative的边界
        for(int i = 0; i < n; i++){
            if(A[i] < 0){
                negative = i;
            }
            else{
                break;
            }
        }
        // 双指针从negative和negative + 1开始
        // neg指针向左，pos指针向右
        int[] res = new int[n];
        int neg = negative;
        int pos = negative + 1;
        int k = 0;
        // 类似于合并有序数组
        while(neg >= 0 || pos < n){
            if(neg < 0){
                res[k] = A[pos] * A[pos];
                pos++;
            }
            else if(pos == n){
                res[k] = A[neg] * A[neg];
                neg--;
            }
            else if(A[neg] * A[neg] <= A[pos] * A[pos]) {
                res[k] = A[neg] * A[neg];
                neg--;
            }
            else {
                res[k] = A[pos] * A[pos];
                pos++;
            }
            k++;
        }
        return res;
    }
}

// 双指针的另一种实现
// A数组两边的肯定是负数和正数部分各自最大的，所以双指针也可以从两边往中间移动，两个指针相遇了就结束
class Solution {
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] res = new int[n];
        int left = 0;
        int right = n - 1;
        // 倒序添加，从n - 1开始
        int k = n - 1;
        while(left <= right){
            if(A[left] * A[left] > A[right] * A[right]){
                res[k] = A[left] * A[left];
                left++;
            }
            else {
                res[k] = A[right] * A[right];
                right--;
            }
            k--;
        }
        return res;
    }
}
