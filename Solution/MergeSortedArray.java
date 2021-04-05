// site: https://leetcode-cn.com/problems/merge-sorted-array/

// 合并两个有序数组，两个数组合并到第一个，用反向双指针的方法

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 因为 nums1 后面有空位
        // 想要不开辟新的空间可以用反向的双指针
        // 即从 nums1 的末尾往前面填
        int pSorted = m + n - 1;
        int pNums2 = n - 1;
        int pNums1 = m - 1;
        // 双指针选大的
        while(pNums1 >= 0 && pNums2 >= 0){
            if(nums1[pNums1] > nums2[pNums2]){
                nums1[pSorted] = nums1[pNums1];
                pNums1--;
            } else {
                nums1[pSorted] = nums2[pNums2];
                pNums2--;
            }
            pSorted--;
        }
        while(pNums1 >= 0){
            nums1[pSorted] = nums1[pNums1];
            pNums1--;
            pSorted--;
        }
        while(pNums2 >= 0){
            nums1[pSorted] = nums2[pNums2];
            pNums2--;
            pSorted--;
        }
        return;
    }
}
