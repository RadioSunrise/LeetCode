package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/sort-colors/
 * 颜色排序
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 */
public class SortColor {
    /**
     * 用指针实现
     * @param nums
     */
    public void sortColorsPointer(int[] nums) {
        int len = nums.length;
    }

    /**
     * 将整个数组分为三个区间，循环变量i，类似快排的partition
     * [o, zero) 都是0
     * [zero, i) 都是1
     * [two, len - 1] 都是2
     * @param nums
     */
    public void sortPartition(int [] nums){
        int len = nums.length;
        // 初值的定义让三个区间为空
        int zero = 0;
        int two = len;
        int i = 0;

        // 当i等于two的时候就完全覆盖全部位置
        while(i < two){
            // nums[i] == 0，则zero需要增大，且需要把0移动到当前zero的位置
            // 因为zero的定义是开区间，所以zero先交换再移动，这样zero左边的都是0（不包括着zero的位置）
            // 且由于交换前zero位置的数是1，交换完i的位置是1，i再移动
            if(nums[i] == 0){
                swap(nums, i, zero);
                zero++;
                i++;
            }
            // nums[i] == 1，不需要移动数据，i继续前进
            else if(nums[i] == 1){
                i++;
            }
            // nums[i] == 2，2要放到右边，因为two是闭区间，即two的位置的数是2
            // 所以two先移动再交换，但i不要移动，因为two移动之后的位置上不知道是什么数字
            else {
                two--;
                swap(nums, i, two);
            }
        }
    }
    public void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    public static void main(String[] args){
        int[] nums = new int[]{2,0,2,1,1,0};
        SortColor solution = new SortColor();
        solution.sortPartition(nums);
        System.out.println(Arrays.toString(nums));
    }
}
