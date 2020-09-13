package leetcode;

import java.lang.reflect.GenericDeclaration;
import java.util.Arrays;

/**
 * 获取一个排列，原地修改成按字典序的下一个排列（比它大的排列里最小的），如果没有比它大，就返回最小的排列
 * 流程：从后往前搜素，找到第一个相邻对(a[i - 1], a[i])，有a[i] > a[i - 1]，要交换的“小数”为a[i - 1]
 * 再从[i : end]中从后往前找，找到第一个比a[i - 1]大的数a[j]，然后交换，之后将a[j : end]逆序
 * 注意如果是全逆序序列，则i会等于0，整个数组反序即可
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        // 从后往前找第一个对顺序对
        int len = nums.length;
        if(len <= 1){
            return;
        }
        int i = len - 1;
        while(i >= 1 && nums[i - 1] >= nums[i]){
            i--;
        }
        // i == 0说明是最大的排序，逆序之后返回
        if(i == 0){
            reverse(nums, 0, len - 1);
            return;
        }
        int small = nums[i - 1];
        // 从后往前找比small大的数
        // 因为从i到结尾都是逆序，所以找到的第一个数必定是nums[i: end]中大于small的最小的数
        int j = len - 1;
        while(j >= i && nums[j] <= small){
            j--;
        }
        // 交换 small 和 big （nums[i - 1] 和 nums[j]）
        swap(nums, i - 1, j);
        // 从i到end开始逆序
        reverse(nums, i, len - 1);
    }
    public void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    public void reverse(int[] nums, int start, int end){
        int k = end;
        int i = start;
        while(i < k){
            swap(nums, i, k);
            i++;
            k--;
        }
    }
    public static void main(String[] args){
        int[] nums = {1, 1, 4, 1};
        NextPermutation solution = new NextPermutation();
        System.out.println(Arrays.toString(nums));
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
