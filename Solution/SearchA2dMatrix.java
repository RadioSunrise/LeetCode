// site: https://leetcode-cn.com/problems/search-a-2d-matrix/

// 二分查找

// 第二次二分的另一种写法
public class SearchA2dMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 两次二分
        // 第一次先找所在的行
        int left = 0;
        int right = m - 1;
        while(left < right){
            int mid = left + ((right - left + 1) >> 1);
            if(matrix[mid][0] <= target){
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        // 找到行之后在行中进行二分查找
        // 先做一些小的判断
        int row = left;
        // System.out.println("row number is " + row);
        if(matrix[row][0] == target){
            return true;
        } else if(target < matrix[row][0]){
            // 如果这一行最小的都比 target 小
            // 则这行肯定没有
            return false;
        }

        left = 0;
        right = n - 1;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            // System.out.println("mid is " + mid);
            if(matrix[row][mid] == target){
                return true;
            } else if (matrix[row][mid] < target){
                left = mid + 1;
            } else if (matrix[row][mid] > target){
                right = mid;
            }
        }
        // 这个写法下 left == right 是结束条件，要加多一次判断
        // 最后验证
        return matrix[row][left] == target;
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 两次二分
        // 第一次先找所在的行
        int left = 0;
        int right = m - 1;
        while(left < right){
            int mid = left + ((right - left + 1) >> 1);
            if(matrix[mid][0] <= target){
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        // 找到行之后在行中进行二分查找
        // 先做一些小的判断
        int row = left;
        // System.out.println("row number is " + row);
        if(matrix[row][0] == target){
            return true;
        } else if(target < matrix[row][0]){
            // 如果这一行最小的都比 target 小
            // 则这行肯定没有
            return false;
        }

        left = 0;
        right = n - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            // System.out.println("mid is " + mid);
            if(matrix[row][mid] == target){
                return true;
            } else if (matrix[row][mid] < target){
                left = mid + 1;
            } else if (matrix[row][mid] > target){
                right = mid - 1;
            }
        }
        return false;
    }
}

