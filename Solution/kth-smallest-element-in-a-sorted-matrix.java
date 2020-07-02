//site: https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/

class Solution {
    public int kthSmallest(int[][] matrix, int k){
        int n = matrix.length;
        int left = matrix[0][0]; // 最小的元素
        int right = matrix[n-1][n-1]; // 最大的元素
        while (right > left){
            int mid = (left + right) / 2;
            if(check(matrix, k ,mid, n)){ //如果比mid小的元素大于等于k
                right = mid - 1; // !!!不能是mid-1，如果mid就是第K大的数，而right=mid-1没考虑到，就错了
            }
            else {
                left = mid +1;
            }
        }
        return left;
    }
    public  boolean check(int[][] matrix, int k, int mid, int n){
        // 判断matrix中比mid小元素的个数
        int nums = 0;
        int i = n-1, j = 0;
        while(i >= 0 && j < n){
            if(matrix[i][j] <= mid){ // 如果碰到小于等于(!!!!!不要漏了等于!!!!!)mid的值，统计这一列的个数（都比mid小)
                nums += i+1;
                j++; // 再向右移动
            }
            else{
                i--; //如果这个数比mid大，则向上移动
            }
        }
        return nums >= k;
    }
}
