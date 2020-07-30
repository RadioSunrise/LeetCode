// site: https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/

// 因为没有要求奇数和偶数按原来的顺序
// 所以可以用交换的办法
class Solution {
    public int[] exchange(int[] nums) {
        // 双指针
        int n = nums.length;
        int i = 0;
        int j = n - 1;
        int temp;
        while(i < j){
            // i遇到偶数就停止
            while(i < j && nums[i] % 2 != 0){
                i++;
            }
            // j遇到奇数就停止
            while(i < j && nums[j] % 2 != 1){
                j--;
            }
            // 交换nums的i和j
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
}

// 如果要保持原来的顺序，且空间O(1)，可以用插入排序的思想，遍历剩余未移动部分，找到奇数放到排完的段后面
class Solution {
    public int[] exchange(int[] nums) {
        // 双指针
        int n = nums.length;
        // 指针 i 遍历数组
        int i = 0;
        // k 记录已经排好的奇数的个数
        int k = -1;
        // 找到一个奇数就把它往前移动，放到k的位置，同时把元素后移
        for(; i < n; i++){
            if(nums[i] % 2 == 1){
                k++;
                int temp = nums[i];
                for(int j = i; j > k; j--){
                    nums[j] = nums[j - 1];
                }
                nums[k] = temp;
            }
            // System.out.println(Arrays.toString(nums));
        }
        return nums;
    }
}

// 空间O(n)，时间O(2n)
class Solution {
    public int[] exchange(int[] nums) {
        // 空间O(n)做法
        int n = nums.length;
        int[] res = new int[n];
        int k = 0;
        // 第一趟扫描添加奇数
        for(int num : nums){
            if(num % 2 == 1){
                res[k] = num;
                k++;
            }
        }
        // 第二趟扫描增加偶数
        for(int num : nums){
            if(num % 2 == 0){
                res[k] = num;
                k++;
            }
        }
        return res;
    }
}
